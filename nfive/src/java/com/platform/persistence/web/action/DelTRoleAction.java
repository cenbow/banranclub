package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TRoleDto;
import com.platform.persistence.service.ITRoleService;

 /**
 * 类功能:删除角色
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTRoleAction")
@Scope("prototype")
public class DelTRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleService tRoleService;
	
	private  String pkid;

	public String execute() throws Exception {
		String result = "";
		try {
			TRoleDto paramTRoleDto = new TRoleDto();
			//设置主键
			paramTRoleDto.setRole_id(pkid);
			result = tRoleService.deleteRole(paramTRoleDto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		outJSOND(response,result);
		
		return null;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
