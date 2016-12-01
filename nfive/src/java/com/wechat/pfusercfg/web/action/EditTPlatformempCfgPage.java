package com.wechat.pfusercfg.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;

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
 
@Controller("editTPlatformempCfgPage")
@Scope("prototype")
public class EditTPlatformempCfgPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TPlatformempCfgDto tPlatformempCfgDto;

	
	public String execute() throws Exception {
		try {
		    TPlatformempCfgDto paramTPlatformempCfgDto = new TPlatformempCfgDto();
		//设置主键
			paramTPlatformempCfgDto.setCfg_id(pkid);
			tPlatformempCfgDto = tPlatformempCfgService.getRow(paramTPlatformempCfgDto );
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TPlatformempCfgDto getTPlatformempCfgDto() {
		return tPlatformempCfgDto;
	}

	public final void setStudentDto(TPlatformempCfgDto tPlatformempCfgDto) {
		this.tPlatformempCfgDto = tPlatformempCfgDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
