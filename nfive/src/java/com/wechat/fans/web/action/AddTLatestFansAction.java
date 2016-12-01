package com.wechat.fans.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.other.WeixinUserList;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.fans.datasync.LatestFansDataSyncUtil;
import com.wechat.fans.model.dto.TLatestfansSyncHistoryDto;
import com.wechat.fans.service.ITLatestFansService;
import com.wechat.fans.service.ITLatestfansSyncHistoryService;

/**
 * 类功能:自动代码生成模板新增   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("addTLatestFansAction")
@Scope("prototype")
public class AddTLatestFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITLatestfansSyncHistoryService latestfansSyncHistoryService;
	@Autowired
	private ITLatestFansService latestfansService;

	private static final Logger logger = Logger.getLogger(AddTLatestFansAction.class);

	public String execute() throws Exception {
		TLatestfansSyncHistoryDto tmpFansSyncHistoryDto = new TLatestfansSyncHistoryDto();
		try {		
			//1.检查是否有执行中的同步操作，如果没有则继续
			TLatestfansSyncHistoryDto checkLatestFansSyncHistoryDto = new TLatestfansSyncHistoryDto();
			checkLatestFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ONGING);
			List<TLatestfansSyncHistoryDto> checkLatestFansRST = latestfansSyncHistoryService.getAll(checkLatestFansSyncHistoryDto);
			if(checkLatestFansRST.size()==1){
				String result = "{\"status\":false,\"message\":\"有其他同步操作正在执行！\"}";
				outJSOND(this.getResponse(), result);
			}else if(checkLatestFansRST.size()>1){
				String result = "{\"status\":false,\"message\":\"数据异常！\"}";
				outJSOND(this.getResponse(), result);
			}else{
				//1.插入同步状态
		        TLatestfansSyncHistoryDto tFansSyncHistoryDto= new TLatestfansSyncHistoryDto();
		        tFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ONGING);
		        tFansSyncHistoryDto.setSync_start_date(new Date());
		        tFansSyncHistoryDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
		        tFansSyncHistoryDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		        tFansSyncHistoryDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		        tmpFansSyncHistoryDto = latestfansSyncHistoryService.save(tFansSyncHistoryDto);
		        
		        //2.删除【T_LATEST_FANS】所有记录
		        latestfansService.truncateLatestFans();
		        
		        //3.从微信服务器上取得所有的关注用户批量获取
		        //取得access token
				String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()));
				WeixinUserList weixinUserList = this.transFans(accessToken);
		        
				//4.将信息插入到最新粉丝表【T_LATEST_FANS】表中
				//后台程执行同步
				Thread fansDataSync = new Thread(new LatestFansDataSyncUtil(weixinUserList.getOpenIdList(), LoginUserInfoUtil.getLoginUserCD(),LoginUserInfoUtil.getLoginUserCD(),WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id(),tmpFansSyncHistoryDto));		
				logger.info("-------------粉丝同步开始-------------------");
				fansDataSync.start();
				String result = "{\"status\":true,\"total\":"+weixinUserList.getTotal()+"}";
				outJSOND(this.getResponse(), result);
			}
		} catch (Exception ex) {
			tmpFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ERROR);
	        tmpFansSyncHistoryDto.setSync_end_date(new Date());
	        tmpFansSyncHistoryDto.setRemark("获取token失败 errcode:{40001} errmsg:{invalid credential}");
	        latestfansSyncHistoryService.updatePK(tmpFansSyncHistoryDto);
	        logger.error("-------------粉丝同步异常-------------------",ex);
			logger.error(ex.getMessage(),ex);
		}
		
		return null;
	}
	
	/**
     * 获取微信粉丝
     *
     *@param accessToken 微信服务器访问token
     * @return List<TWeixinFansDto>
     * @throws Exception
     */
    private WeixinUserList transFans(String accessToken) throws Exception {

    	WeixinUserList weixinUserListRST = new WeixinUserList();
        List<String> allOpenIds = new ArrayList<String>();

        //获取关注者列表
        WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken, null);
        if (weixinUserList != null && weixinUserList.getOpenIdList() != null) {
        	weixinUserListRST.setTotal(weixinUserList.getTotal());
        	allOpenIds.addAll(weixinUserList.getOpenIdList());
            
        }
        while (weixinUserList != null && weixinUserList.getCount() == 10000) {
            //count是拉取的OPENID个数，最大值为10000。count为10000，说明可能还需要再拉取
            weixinUserList = AdvancedUtil.getUserList(accessToken, weixinUserList.getNextOpenId());
            if (weixinUserList != null && weixinUserList.getOpenIdList() != null) {
            	allOpenIds.addAll(weixinUserList.getOpenIdList());
            }
        }
        
        weixinUserListRST.setOpenIdList(allOpenIds);
        return weixinUserListRST;
    }

}
