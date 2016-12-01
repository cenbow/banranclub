package com.wechat.material.web;

import java.util.List;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//import com.gtcore.rmtserver.IGtCoreRmtTWxNewsService;

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
@Controller("addTWxNewsPage")
@Scope("prototype")
public class AddTWxNewsPage extends BaseAction {
	private static final long serialVersionUID = 1L;

//	@Autowired
//	private IGtCoreRmtTWxNewsService gtCoreRmtTWxNewsService;

	private List<SelectCsBean> rule_typeList;

	public String execute() throws Exception {

		try {

			// 特殊回复规则下拉列表
			rule_typeList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.REPLY_RULE);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public List<SelectCsBean> getRule_typeList() {
		return rule_typeList;
	}

	public void setRule_typeList(List<SelectCsBean> ruleTypeList) {
		rule_typeList = ruleTypeList;
	}

}
