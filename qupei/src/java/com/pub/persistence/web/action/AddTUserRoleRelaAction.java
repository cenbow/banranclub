package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TUserRoleRelaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITUserRoleRelaService;

 /**
 * 类功能:添加用户角色关系
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTUserRoleRelaAction")
@Scope("prototype")
public class AddTUserRoleRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITUserRoleRelaService tUserRoleRelaService;
	private TUserRoleRelaDto tUserRoleRelaDto= new TUserRoleRelaDto();

	public String execute() throws Exception {
		try {
			tUserRoleRelaService.configUserRoleRela(tUserRoleRelaDto,"add");
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}





	public final TUserRoleRelaDto getTUserRoleRelaDto() {
		return tUserRoleRelaDto;
	}

	public final void setTUserRoleRelaDto(TUserRoleRelaDto tUserRoleRelaDto) {
		this.tUserRoleRelaDto = tUserRoleRelaDto;
	}

}
