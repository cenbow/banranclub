package com.wechat.pfcfg.web;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
 /**
 * 类功能:自动代码生成模板新增   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTPubPlatformPage")
@Scope("prototype")
public class AddTPubPlatformPage extends BaseAction {
	private static final long serialVersionUID = 1L;

    private List<SelectCsBean> accountTypes;

	public String execute() throws Exception {

		try {

			accountTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.ACCOUNT_TYPE);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}
	
	public List<SelectCsBean> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<SelectCsBean> accountTypes) {
		this.accountTypes = accountTypes;
	}

}
