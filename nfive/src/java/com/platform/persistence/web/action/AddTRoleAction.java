package com.platform.persistence.web.action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.persistence.model.dto.TRoleDto;
import com.platform.persistence.service.ITRoleService;
 /**
 * 类功能:添加角色数据
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTRoleAction")
@Scope("prototype")
public class AddTRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleService tRoleService;
	private TRoleDto tRoleDto= new TRoleDto();

	public String execute() throws Exception {
		try {
			tRoleDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			tRoleService.save(tRoleDto);
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

}
