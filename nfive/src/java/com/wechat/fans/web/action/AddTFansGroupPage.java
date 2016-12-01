package com.wechat.fans.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;

 /**
 * 类功能:跳转到新增粉丝群页面
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTFansGroupPage")
@Scope("prototype")
public class AddTFansGroupPage extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	protected static final Log log = LogFactory.getLog(AddTFansGroupPage.class);

	public String execute() throws Exception {

		try {
			return SUCCESS;
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}

}
