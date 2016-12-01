package com.pub.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.model.TResourceQueryBean;
import com.pub.persistence.service.ITResourceService;

/**
 * 类功能:资源校验
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("checkTResourceAction")
@Scope("prototype")
public class CheckTResourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ITResourceService tResourceService;
	private TResourceQueryBean tResourceQueryBean= new TResourceQueryBean();
	private String doType;
	
	public String execute() throws Exception {
		try {
			String result = tResourceService.checkResourceVal(tResourceQueryBean);
			outJSOND(response,result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 提交操作对应的资源校验 
	 * @return
	 * @throws Exception
	 */
	public String checkResource()throws Exception 
	{
		try {
			String result = tResourceService.checkResourceOnSubmit(tResourceQueryBean, doType);
			outJSOND(response,result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}


	public TResourceQueryBean getTResourceQueryBean() {
		return tResourceQueryBean;
	}


	public void setTResourceQueryBean(TResourceQueryBean resourceQueryBean) {
		tResourceQueryBean = resourceQueryBean;
	}


	
	
}
