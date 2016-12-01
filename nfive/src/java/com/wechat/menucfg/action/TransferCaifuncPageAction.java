package com.wechat.menucfg.action;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.opensymphony.web.context.OpenIdContext;
import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;

/**
 * 类功能:跳转到财富农场
 * <p>创建者:ZHUBAODING</p>
 * <p>创建时间:2016/04/26</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("transferCaifuncPageAction")
@Scope("prototype")
public class TransferCaifuncPageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TransferCaifuncPageAction.class);
		
	/** OPENID */
	private String openId;
	
	public String execute() throws Exception {
		try {
			OpenIdUserInfo openIdUserInfo = OpenIdContext.getOpenIdUserInfo();
			openId = openIdUserInfo.getOpenId();
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
