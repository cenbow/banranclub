package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TRoleResRelaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITRoleResRelaService;

 /**
 * 类功能:跳转到添加角色资源关系页面
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTRoleResRelaPage")
@Scope("prototype")
public class AddTRoleResRelaPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleResRelaService tRoleResRelaService;

	private TRoleResRelaDto tRoleResRelaDto = new TRoleResRelaDto();

	public String execute() throws Exception {

		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	/**
	 * 将资源分配给角色
	 * @return
	 */
	public String configResourceToRolePage()
	{
		try {
			request.setAttribute("tRoleResRelaDto",tRoleResRelaDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}



	public TRoleResRelaDto getTRoleResRelaDto() {
		return tRoleResRelaDto;
	}

	public void setTRoleResRelaDto(TRoleResRelaDto roleResRelaDto) {
		tRoleResRelaDto = roleResRelaDto;
	}




}
