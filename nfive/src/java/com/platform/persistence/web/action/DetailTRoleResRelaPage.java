package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TRoleResRelaDto;
import com.platform.persistence.service.ITRoleResRelaService;

 /**
 * 类功能:角色资源关系明细
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("detailTRoleResRelaPage")
@Scope("prototype")
public class DetailTRoleResRelaPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleResRelaService tRoleResRelaService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TRoleResRelaDto tRoleResRelaDto;

	
	public String execute() throws Exception {
		try {
		    TRoleResRelaDto paramTRoleResRelaDto = new TRoleResRelaDto();
		//设置主键
			paramTRoleResRelaDto.setRole_res_id(pkid);
			tRoleResRelaDto = tRoleResRelaService.getRow(paramTRoleResRelaDto );
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TRoleResRelaDto getTRoleResRelaDto() {
		return tRoleResRelaDto;
	}

	public final void setStudentDto(TRoleResRelaDto tRoleResRelaDto) {
		this.tRoleResRelaDto = tRoleResRelaDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
