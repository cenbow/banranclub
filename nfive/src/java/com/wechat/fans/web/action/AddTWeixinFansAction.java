package com.wechat.fans.web.action;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.service.ITFansGroupMemberRealService;

 /**
 * 类功能:添加到粉丝群
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTWeixinFansAction")
@Scope("prototype")
public class AddTWeixinFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
    //入参
    private TWeixinFansGroupVo tWeixinFansGroupVo = new TWeixinFansGroupVo();
    //出错信息
    private String errorMessage;
    private static final Logger logger = Logger.getLogger(AddTWeixinFansAction.class);
    
	public String execute() throws Exception {
		 //返回结果
		String result = null;
		try {
			
			String[] fanslist =  tWeixinFansGroupVo.getFans_id().split(",");
			String[] namelist = tWeixinFansGroupVo.getMember_name().split(",");
			for(int i=0;i<fanslist.length;i++){
				//查询粉丝已添加的群		        
				TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean = new TFansGroupMemberRealQueryBean();
				tFansGroupMemberRealQueryBean.setFans_id(fanslist[i]);
				tFansGroupMemberRealQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
				List<TFansGroupMemberRealDto> tmpFansGroupMemberRealDto  =tFansGroupMemberRealService.getFansGroup(tFansGroupMemberRealQueryBean);	
		        TFansGroupMemberRealDto paramTFansGroupMemberRealDto = new TFansGroupMemberRealDto();
		        //将已有的群存放进Set
			    Set<String> set = new TreeSet<String>();
				for(int j=0;j<tmpFansGroupMemberRealDto.size();j++){
					  set.add(tmpFansGroupMemberRealDto.get(j).getFans_group_id());
			   	}
				if(set.add(tWeixinFansGroupVo.getFans_group_id())){
					//设置粉丝群id
					paramTFansGroupMemberRealDto.setFans_group_id(tWeixinFansGroupVo.getFans_group_id());
					//设置粉丝id
					paramTFansGroupMemberRealDto.setFans_id(fanslist[i]);
					//设置成员名称
					paramTFansGroupMemberRealDto.setMember_name(namelist[i]);
					//设置标星成员
					paramTFansGroupMemberRealDto.setStar_member(CodeStringConstant.CS_1000_FALSE);
					//设置创建人
					paramTFansGroupMemberRealDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
					//设置更新人
					paramTFansGroupMemberRealDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
					//设置公众号平台号id
					paramTFansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
				
				}
				
		         tFansGroupMemberRealService.save(paramTFansGroupMemberRealDto);
			}
			result = "{\"status\":true,\"message\":\"添加到群成功\"}";
			outJSOND(this.getResponse(), result);
			
			return null;
		} catch (Exception ex) {
			result = "{\"status\":true,\"message\":\"添加到群失败\"}";
			outJSOND(this.getResponse(), result);
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}
		
		return ERROR;
	}

	public TWeixinFansGroupVo getTWeixinFansGroupVo() {
		return tWeixinFansGroupVo;
	}

	public void setTWeixinFansGroupVo(TWeixinFansGroupVo tWeixinFansGroupVo) {
		this.tWeixinFansGroupVo = tWeixinFansGroupVo;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
