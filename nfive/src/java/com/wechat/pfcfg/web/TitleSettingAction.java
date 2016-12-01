package com.wechat.pfcfg.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
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
@Controller("titleSettingAction")
@Scope("prototype")
public class TitleSettingAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPubPlatformService tPubPlatformService;
	@Autowired
	private WechatInfoUtil wechatInfoUtil;
	
	private TPubPlatformDto tPubPlatformDto = new TPubPlatformDto();

	public String execute() throws Exception {
		boolean result = true;
		
		try {
			  tPubPlatformService.updatePK(tPubPlatformDto);
			  wechatInfoUtil.InitConfig();
			  outJSOND(response,"{\"status\":"+result+",\"mess\":\""+"保存成功!"+"\"}");
			  
		} catch (IOException e) {
			result = false;
			outJSOND(response,"{\"status\":"+result+",\"mess\":\""+"保存失败!"+"\"}");
			e.printStackTrace();
		}
		return null;

	}
	
	public final TPubPlatformDto getTPubPlatformDto() {
		return tPubPlatformDto;
	}

	public final void setStudentDto(TPubPlatformDto tPubPlatformDto) {
		this.tPubPlatformDto = tPubPlatformDto;
	}

}
