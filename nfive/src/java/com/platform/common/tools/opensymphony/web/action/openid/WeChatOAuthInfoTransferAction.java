package com.platform.common.tools.opensymphony.web.action.openid;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.cache.CacheUtil;
import com.platform.common.tools.constant.WeChatOpenIdKeyConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.opensymphony.web.context.UserContext;
import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;
import com.wechat.core.utils.CommonUtil;

@Controller("weChatOAuthInfoTransferAction")
@Scope("prototype")
public class WeChatOAuthInfoTransferAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			String to = (String) request.getParameter("to");
			String sessionId = (String) request.getParameter("sid");

			OpenIdUserInfo openIdUserInfo = (OpenIdUserInfo) request.getSession().getAttribute(
					WeChatOpenIdKeyConstant.SESSION_OPENID_KEY);
			String openid = openIdUserInfo != null ? openIdUserInfo.getOpenId() : null;

			// 把OpenId存入缓存，以sessionId+"_openId"为Key。
			String cacheKey = sessionId + "_openId";
			Boolean flag = CacheUtil.put(cacheKey, openid, new Integer(60));

			StringBuffer sb = new StringBuffer();
			String toDecodeURL = java.net.URLDecoder.decode(to, "utf-8");
			sb.append(toDecodeURL);
			if (toDecodeURL.indexOf("?") > 0) {
				sb.append("&");
			} else {
				sb.append("?");
			}
			sb.append("sid=").append(sessionId).append("&inCache=1");
			String transferUrl = sb.toString();

			System.out.println("transferUrl=" + transferUrl);

			this.getResponse().sendRedirect(transferUrl);

			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;
	}

}
