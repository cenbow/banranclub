package com.platform.common.tools.opensymphony.web.action.openid;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.opensymphony.web.context.OpenIdContext;
import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;
import com.wechat.core.beans.other.SNSUserInfo;


@Controller("weChatOAuthInfoAction")
@Scope("prototype")
public class WeChatOAuthInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	OpenIdUserInfo openIdUserInfo;

	public String execute() throws Exception {
		try {
			//微信关注用户内容获取示例
			openIdUserInfo = OpenIdContext.getOpenIdUserInfo();
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public OpenIdUserInfo getOpenIdUserInfo() {
		return openIdUserInfo;
	}

	public void setOpenIdUserInfo(OpenIdUserInfo openIdUserInfo) {
		this.openIdUserInfo = openIdUserInfo;
	}
	
	
	
	
}
