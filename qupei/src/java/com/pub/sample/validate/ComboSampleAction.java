package com.pub.sample.validate;
import java.util.List;

import com.pub.common.tools.constant.CodeStringConstant;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.codestring.CodeStringUtil;
import com.pub.common.tools.codestring.SelectCsBean;
import com.pub.common.tools.opensymphony.web.action.BaseAction;

@Controller("comboSampleAction")
@Scope("prototype")
public class ComboSampleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
    private static  Logger logger  = Logger.getLogger(ComboSampleAction.class);

	//回复类型
	private List<SelectCsBean> reply_SelList;

	public String execute() throws Exception {
		try {

			//构造回复类型下拉列表数据
			reply_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_SEX);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public List<SelectCsBean> getReply_SelList() {
		return reply_SelList;
	}

	public void setReply_SelList(List<SelectCsBean> replySelList) {
		reply_SelList = replySelList;
	}



}


