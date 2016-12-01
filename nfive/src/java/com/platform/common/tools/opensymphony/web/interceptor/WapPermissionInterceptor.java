package com.platform.common.tools.opensymphony.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.platform.common.tools.opensymphony.web.context.UserContext;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.LoginSessionBean;
import com.platform.common.tools.wechat.LoginSessionUtil;
import com.wechat.core.utils.CommonUtil;

/**
 * @author wen.zhang 
 * 
 * 这个类是权限控制的基类 
 * 作为所有手机端ACTION的反射层次的前置阻挡，以保证有权限的用户能顺利通过验证
 * 没有登录的用户直接跳转手机登陆页面 
 */
public class WapPermissionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(WapPermissionInterceptor.class);

	// 不执行权限拦截的方法
	public static final String[] excludeMethods = new String[] { "login", "logout" };

	// Wap无登陆常量
	public static final String WAP_NO_LOGIN = "wap_login";

	@Autowired
	private LoginSessionUtil loginSessionUtil;

	public String intercept(ActionInvocation invocation) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("intercept '" + invocation.getProxy().getNamespace() + "/"
					+ invocation.getProxy().getActionName() + "' { ");
		}

		// 是否开启权限校验未开启属于调试模式直接放行
		if (!LoginUserInfoUtil.CHECK_PERMISION) {
			return invocation.invoke();
		}

		// 查看是否在被排除的方法列表中若在直接放行
		String method = invocation.getProxy().getMethod();
		for (String method_ : excludeMethods) {
			if (method_.equals(method)) {
				return invocation.invoke();
			}
		}

		// 判断是否有用户已登陆
		LoginSessionBean loginSessionBean = loginSessionUtil.getLoginSession();
		if (loginSessionBean == null) {
			// 获取内部对象
			HttpServletRequest request = ServletActionContext.getRequest();

			// 原始URL
			String originalURL = RequestUrlUtil.buildOriginalRelativeURL(request);
			log.info("原始URL：" + originalURL);

			// 登陆页面登陆成功后跳转URL
			request.setAttribute("redirect_url", CommonUtil.urlEncodeUTF8(originalURL));

			return WAP_NO_LOGIN;
		}

		// 通过检查后的执行
		final String result = invocation.invoke();

		// 度量返回链操作
		return result;
	}

}
