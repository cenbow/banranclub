package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TEmpUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITEmpUserService;

 /**
 * 类功能:删除员工
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("delTEmpUserAction")
@Scope("prototype")
public class DelTEmpUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;

	private  String pkid;
	private  String userCd;

	public String execute() throws Exception {
		try {
			TEmpUserDto paramTEmpUserDto = new TEmpUserDto();
			// 设置主键
			paramTEmpUserDto.setUser_id(pkid);
			tEmpUserService.deleteTEmpUser(paramTEmpUserDto);
			outJSOND(response, "{\"status\":" + true + ",\"message\":\"\"}");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
}
