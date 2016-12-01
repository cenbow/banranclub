package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TRoleResRelaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITRoleResRelaService;

 /**
 * 类功能:删除角色资源关系
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("delTRoleResRelaAction")
@Scope("prototype")
public class DelTRoleResRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleResRelaService tRoleResRelaService;

	private  String pkid;

	private TRoleResRelaDto tRoleResRelaDto = new TRoleResRelaDto();

	/**
	 * 资源配置角色
	 */
	public String execute() throws Exception {
		try {
			//设置主键
			tRoleResRelaService.deleteTRoleResRela(tRoleResRelaDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}




	public final String getPkid() {
		return pkid;
	}
	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public TRoleResRelaDto getTRoleResRelaDto() {
		return tRoleResRelaDto;
	}
	public void setTRoleResRelaDto(TRoleResRelaDto roleResRelaDto) {
		tRoleResRelaDto = roleResRelaDto;
	}

}
