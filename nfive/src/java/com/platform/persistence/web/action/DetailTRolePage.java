package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TRoleDto;
import com.platform.persistence.service.ITRoleService;

 /**
 * 类功能:角色明细
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("detailTRolePage")
@Scope("prototype")
public class DetailTRolePage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleService tRoleService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TRoleDto tRoleDto;

	
	public String execute() throws Exception {
		try {
		    TRoleDto paramTRoleDto = new TRoleDto();
		//设置主键
			paramTRoleDto.setRole_id(pkid);
			tRoleDto = tRoleService.getRow(paramTRoleDto );
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TRoleDto getTRoleDto() {
		return tRoleDto;
	}

	public final void setStudentDto(TRoleDto tRoleDto) {
		this.tRoleDto = tRoleDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
