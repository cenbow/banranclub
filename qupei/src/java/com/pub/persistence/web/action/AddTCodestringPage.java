package com.pub.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITCodestringService;

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
 
@Controller("addTCodestringPage")
@Scope("prototype")
public class AddTCodestringPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITCodestringService tCodestringService;

	public String execute() throws Exception {

		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

}
