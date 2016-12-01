package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TRoleDto;
import com.platform.persistence.service.ITRoleService;

/**
 * 类功能:角色校验
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("checkTRoleAction")
@Scope("prototype")
public class CheckTRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ITRoleService tRoleService;
	private TRoleDto tRoleDto = new TRoleDto();
	private String doType;
	
	public String execute() throws Exception {
		try {
			boolean is_val = tRoleService.checkRole(doType, tRoleDto);
			String message = "";
			if(!is_val)
			{
				message = "角色名称有重复,请重新输入!";
			}
			outJSOND(response,"{\"status\":"+is_val+",\"message\":\""+message+"\"}");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public final TRoleDto getTRoleDto() {
		return tRoleDto;
	}

	public final void setStudentDto(TRoleDto tRoleDto) {
		this.tRoleDto = tRoleDto;
	}


	public String getDoType() {
		return doType;
	}


	public void setDoType(String doType) {
		this.doType = doType;
	}
	
}
