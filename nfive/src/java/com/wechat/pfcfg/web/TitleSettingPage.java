package com.wechat.pfcfg.web;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
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
 
@Controller("titleSettingPage")
@Scope("prototype")
public class TitleSettingPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPubPlatformService tPubPlatformService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TPubPlatformDto tPubPlatformDto;

	public String execute() throws Exception {
		try {
		    TPubPlatformDto paramTPubPlatformDto = new TPubPlatformDto();
		    //设置主键
			paramTPubPlatformDto.setPlatform_id(pkid);
			tPubPlatformDto = tPubPlatformService.getRow(paramTPubPlatformDto );
			if(StringUtils.isNotBlank(tPubPlatformDto.getTitle_style())){
				tPubPlatformDto.setTitle_style(java.net.URLEncoder.encode(tPubPlatformDto.getTitle_style(), "utf-8").replace("+", "%20"));
			}
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TPubPlatformDto getTPubPlatformDto() {
		return tPubPlatformDto;
	}

	public final void setStudentDto(TPubPlatformDto tPubPlatformDto) {
		this.tPubPlatformDto = tPubPlatformDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
