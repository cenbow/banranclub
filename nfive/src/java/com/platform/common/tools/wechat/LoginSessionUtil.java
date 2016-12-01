package com.platform.common.tools.wechat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hercules.common.encryption.Encrypter;
import com.platform.common.tools.constant.CacheKeyPrefixConstant;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.context.OpenIdContext;
import com.platform.common.tools.opensymphony.web.context.UserContext;
import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;


/***
 * Session缓存
 *
 * @author wen.zhang
 *
 */
@Service("loginSessionUtil")
public class LoginSessionUtil {
	private static final Logger logger = Logger.getLogger(LoginSessionUtil.class);





	/**
	 * 获取LoginSession信息
	 *
	 * @return loginSessionBean
	 * @throws Exception
	 */
	public LoginSessionBean getLoginSession() throws Exception {
		// 获取SESSION信息 ServletActionContext.getRequest();
		HttpSession session = UserContext.getRequest().getSession();
		LoginSessionBean loginSessionBean = (LoginSessionBean) session.getAttribute(CacheKeyPrefixConstant.SESSION_WEB_KEY);

		// 当Session不存在时，如果为微信浏览器访问，用OpenId做一次模拟登录
		if (loginSessionBean == null) {
			OpenIdUserInfo openIdUserInfo = OpenIdContext.getOpenIdUserInfo();
			if (openIdUserInfo != null) {
				String openId = openIdUserInfo.getOpenId();
			}
		}

		return loginSessionBean;
	}

	/**
	 * 初始化/刷新LoginSession信息
	 *
	 * @param maccountNo
	 * @return boolean
	 * @throws Exception
	 */
	public boolean putLoginSession(String maccountNo) throws Exception {
		boolean result = false;

		if (StringUtils.isNotBlank(maccountNo)) {
			HttpSession session = UserContext.getRequest().getSession();
		}

		return result;
	}

	/**
	 * 注销LoginSession信息
	 *
	 * @return boolean
	 * @throws Exception
	 */
	public boolean clearLoginSession() throws Exception {
		boolean result = true;

		HttpSession session = UserContext.getRequest().getSession();
//		LoginSessionBean loginSessionBean = (LoginSessionBean) session.getAttribute(CacheKeyPrefixConstant.SESSION_WEB_KEY);

		// ① HttpSession注销
		session.setAttribute(CacheKeyPrefixConstant.SESSION_WEB_KEY, null);
		session.invalidate();

		// ② MemcacheSession注销 TODO
//		if (loginSessionBean != null && loginSessionBean.getTMainAccountDto() != null) {
//			String cacheKey = CacheKeyUtil.getLoginSessionCodeKey(loginSessionBean.getTMainAccountDto().getMaccount_no());
//			CacheUtil.remove(cacheKey);
//		}

		return result;
	}
}
