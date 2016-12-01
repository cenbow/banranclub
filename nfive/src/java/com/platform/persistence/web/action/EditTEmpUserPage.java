package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.service.ITEmpUserService;

 /**
 * 类功能:编辑csr员工页面跳转
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTEmpUserPage")
@Scope("prototype")
public class EditTEmpUserPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TEmpUserDto tEmpUserDto;

	
	public String execute() throws Exception {
		try {
		    TEmpUserDto paramTEmpUserDto = new TEmpUserDto();
		//设置主键
			paramTEmpUserDto.setUser_id(pkid);
			tEmpUserDto = tEmpUserService.getRow(paramTEmpUserDto );
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TEmpUserDto getTEmpUserDto() {
		return tEmpUserDto;
	}

	public final void setStudentDto(TEmpUserDto tEmpUserDto) {
		this.tEmpUserDto = tEmpUserDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
