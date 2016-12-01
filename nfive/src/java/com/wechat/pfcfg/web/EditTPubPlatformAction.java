package com.wechat.pfcfg.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;

 /**
 * 类功能:自动代码生成模板编辑   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTPubPlatformAction")
@Scope("prototype")
public class EditTPubPlatformAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPubPlatformService tPubPlatformService;
	@Autowired
	private WechatInfoUtil wechatInfoUtil;
	
	private TPubPlatformDto tPubPlatformDto =new TPubPlatformDto();

	public String execute() throws Exception {
		boolean result = true;
		//判断修改的公众号和原始ID是否存在
		if(checkTPubPlatformIdAction().equals("error"))
		{
			result = false;
			outJSOND(response,"{\"status\":"+result+",\"mess\":\""+"已存在相同的公众号ID或原始ID，请您重新修改: )"+"\"}");
			return null;
		}
		
		//判断前台页面的未输入，如果选择未输入获得""。等于""返回前台选择公众号。
		if(tPubPlatformDto.getPlatform_type().equals(""))
		{
			result = false;
			outJSOND(response,"{\"status\":"+result+",\"mess\":\""+"请选择公众号类型!"+"\"}");
			return null;
		}
		
		try {//获取当前登录人，
			  String userCd = LoginUserInfoUtil.getLoginUser().getUser_cd();
		      TPubPlatformDto tmpTPubPlatformDto = new TPubPlatformDto();
			  tmpTPubPlatformDto.setPlatform_id(tPubPlatformDto.getPlatform_id());
			  tmpTPubPlatformDto.setPlatform_name(tPubPlatformDto.getPlatform_name());
			  tmpTPubPlatformDto.setPlatform_desc(tPubPlatformDto.getPlatform_desc());
			  tmpTPubPlatformDto.setUpdated_user_cd(userCd);
			  tPubPlatformService.updatePK(tPubPlatformDto);
			  wechatInfoUtil.InitConfig();
			outJSOND(response,"{\"status\":"+result+",\"mess\":\""+"保存成功!"+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	//修改公众号名称和原始ID的时候，判断是否重复，如果是修改的原记录重复，表示可以修改，否则不能修改。
	public  String checkTPubPlatformIdAction() {
		String result = "SUCCESS";
		try {
			//获得公众号ID，判断是否
			result=tPubPlatformService.queryTPubPlatformId(tPubPlatformDto.getWechart_account(),tPubPlatformDto.getOrg_id(),tPubPlatformDto.getPlatform_id());
			if(tPubPlatformDto.getPlatform_id().equals(result))
			{
				return "SUCCESS";
			}
			else
			{
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public final TPubPlatformDto getTPubPlatformDto() {
		return tPubPlatformDto;
	}

	public final void setStudentDto(TPubPlatformDto tPubPlatformDto) {
		this.tPubPlatformDto = tPubPlatformDto;
	}

}
