package com.pub.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.model.TEmpUserQueryBean;
import com.pub.persistence.service.ITEmpUserService;

/**
 * 类功能:执行密码修改
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("changePassAction")
@Scope("prototype")
public class ChangePassAction extends BaseAction {
	private static final long serialVersionUID = -1271086268804667505L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	
	private TEmpUserQueryBean tEmpUserQueryBean = new TEmpUserQueryBean();
	
	
	/**
	 * 密码修改
	 * @return
	 */
	public String changePass()
	{
		try {
			String result = tEmpUserService.changePass(tEmpUserQueryBean);
			outJSOND(response,result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 密码重置
	 * @return
	 */
	public String resetPass()
	{
		try {
			String result = tEmpUserService.resetPass(tEmpUserQueryBean);
			outJSOND(response,result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public TEmpUserQueryBean getTEmpUserQueryBean() {
		return tEmpUserQueryBean;
	}

	public void setTEmpUserQueryBean(TEmpUserQueryBean empUserQueryBean) {
		tEmpUserQueryBean = empUserQueryBean;
	}
	
	
	
	
	
}
