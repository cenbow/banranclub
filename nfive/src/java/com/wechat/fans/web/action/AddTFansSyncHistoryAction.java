package com.wechat.fans.web.action;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.datasync.FansDataSyncUtil;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;
import com.wechat.fans.service.ITFansSyncHistoryService;


 /**
 * 类功能:同步履历
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTFansSyncHistoryAction")
@Scope("prototype")
public class AddTFansSyncHistoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansSyncHistoryService tFansSyncHistoryService;
	
	private static final Logger logger = Logger.getLogger(AddTFansSyncHistoryAction.class);

	@Override
	public String execute() {
		TFansSyncHistoryDto tmpFansSyncHistoryDto = new TFansSyncHistoryDto();
		try {		
			//1.检查是否有执行中的同步操作，如果没有则继续
			//插入同步状态
	        TFansSyncHistoryDto tFansSyncHistoryDto= new TFansSyncHistoryDto();
	        tFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ONGING);
	        tFansSyncHistoryDto.setSync_start_date(new Date());
	        tFansSyncHistoryDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
	        tFansSyncHistoryDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
	        tFansSyncHistoryDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
	        tmpFansSyncHistoryDto = tFansSyncHistoryService.save(tFansSyncHistoryDto);
			//2.执行同步操作
			//取得access token
			String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()));
			//后台程执行同步
			Thread fansDataSync = new Thread(new FansDataSyncUtil(accessToken, LoginUserInfoUtil.getLoginUserCD(),LoginUserInfoUtil.getLoginUserCD(),WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id(),tmpFansSyncHistoryDto));		
			logger.info("-------------粉丝同步开始-------------------");
			fansDataSync.start();
			String result = "{\"status\":true}";
			outJSOND(this.getResponse(), result);
		} catch (Exception ex) {
			tmpFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ERROR);
	        tmpFansSyncHistoryDto.setSync_end_date(new Date());
	        tmpFansSyncHistoryDto.setRemark("获取token失败 errcode:{40001} errmsg:{invalid credential}");
	        tFansSyncHistoryService.updatePK(tmpFansSyncHistoryDto);
	        logger.error("-------------粉丝同步异常-------------------",ex);
			logger.error(ex.getMessage(),ex);
		}
		
		return null;
	}

}
