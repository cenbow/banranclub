package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.TEmpUserQueryBean;
import com.platform.persistence.service.ITEmpUserService;
 /**
 * 类功能:用户校验
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("checkTEmpUserAction")
@Scope("prototype")
public class CheckTEmpUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	
	private TEmpUserQueryBean tEmpUserQueryBean = new TEmpUserQueryBean();
	
	private String doType;
	
	public String execute() throws Exception {
		try {
			String result = tEmpUserService.checkTEmpUser(tEmpUserQueryBean, doType);
			outJSOND(response,result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	public final TEmpUserQueryBean getTEmpUserQueryBean() {
		return tEmpUserQueryBean;
	}

	public final void setTEmpUserQueryBean(TEmpUserQueryBean tEmpUserQueryBean) {
		this.tEmpUserQueryBean = tEmpUserQueryBean;
	}

	public String getDoType() {
		return doType;
	}

	public void setDoType(String doType) {
		this.doType = doType;
	}
	
	

}
