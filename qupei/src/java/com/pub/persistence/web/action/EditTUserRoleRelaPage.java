package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TUserRoleRelaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITUserRoleRelaService;

 /**
 * 类功能:跳转到编辑员工角色关系页面
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTUserRoleRelaPage")
@Scope("prototype")
public class EditTUserRoleRelaPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITUserRoleRelaService tUserRoleRelaService;

	//入参
	private  String     pkid;

	//出参
	private TUserRoleRelaDto tUserRoleRelaDto;


	public String execute() throws Exception {
		try {
		    TUserRoleRelaDto paramTUserRoleRelaDto = new TUserRoleRelaDto();
		//设置主键
			paramTUserRoleRelaDto.setUser_role_id(pkid);
			tUserRoleRelaDto = tUserRoleRelaService.getRow(paramTUserRoleRelaDto );
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


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
