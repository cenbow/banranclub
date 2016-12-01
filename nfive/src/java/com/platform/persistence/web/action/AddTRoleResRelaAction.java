package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TRoleResRelaDto;
import com.platform.persistence.service.ITRoleResRelaService;

 /**
 * 类功能:添加角色资源关系
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTRoleResRelaAction")
@Scope("prototype")
public class AddTRoleResRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleResRelaService tRoleResRelaService;
	private TRoleResRelaDto tRoleResRelaDto= new TRoleResRelaDto();

	public String execute() throws Exception {
		try {
			tRoleResRelaService.saveTRoleResRela(tRoleResRelaDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}
	
	/**
	 * 角色配置资源
	 * @return
	 */
	public String configResourceToRole()
	{
		try {
			//设置主键
			tRoleResRelaService.configRoleResource(tRoleResRelaDto);
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

}
