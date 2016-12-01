package com.platform.common.tools.opensymphony.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.wechat.activity.model.TActUsersDto;
import com.wechat.activity.service.ITActUsersService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.constant.WeChatOpenIdKeyConstant;
import com.platform.common.tools.opensymphony.web.context.OpenIdContext;
import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.other.SNSUserInfo;
import com.wechat.core.beans.other.WeixinOauth2Token;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

/**
 * 活动模块OpenId强制授权拦截
 *
 * 用户第一次通过此拦截器时必须强制授权（弹出授权页面）
 * 之后再通过此拦截器时将不再需要强制授权（不弹出授权页面）
 *
 * @author wen.zhang
 */
public class WeChatOpenIdUserInfoActivityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	protected static final Log logger = LogFactory.getLog(WeChatOpenIdUserInfoActivityInterceptor.class);

	@Autowired
	private UtilConfig utilConfig;

    @Autowired
    private ITActUsersService actUsersService;


	public WeChatOpenIdUserInfoActivityInterceptor() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// Request
		HttpServletRequest request = ServletActionContext.getRequest();
		// 初始化上下文
		OpenIdContext.setOpenIdUserInfo(null);
		// 是否弱授权
		boolean baseOAuth = false;

		// 获取SESSION中信息
		OpenIdUserInfo openIdUserInfo = (OpenIdUserInfo) request.getSession().getAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY);
		String  openid    = openIdUserInfo != null ? openIdUserInfo.getOpenId()   : null;  // 用户标识
		boolean hasdetail = openIdUserInfo != null ? openIdUserInfo.isHasdetail() : false; // 是否拥有详细信息

		// 获取重定位判断标识
		String state = (String) request.getParameter(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE);

		// 获取浏览器类型标识
//		String userAgent = request.getHeader("User-Agent");
//		boolean wechatBrowserFlg = userAgent != null && userAgent.contains(WeChatOpenIdKeyConstant.USER_AGENT_WECHAT);

		// 是否为微信浏览器请求（*必须在微信中使用）
//		if (wechatBrowserFlg) {
			// 如果Session中用户信息为空或者不为强授权时继续处理
			if (!hasdetail) {
//				TActUserDto actUserResultDto = null;
                TActUsersDto actUsersDto = null;

				// Session中OpenId为空时，使用弱授权获取OpenId
				if (StringUtils.isBlank(openid)) {
					if (StringUtils.isBlank(state)) {
						// 请求URL
						String askUrl = RequestUrlUtil.buildOriginalOAuthURL(request);
						// 设置认证模式
						request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_SCOPE, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_BASE);
						// 设置再跳转URL
						request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_REDIRECT_URI, askUrl);
						// 设置重定位判断标识
						request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_BASE);

						return WeChatOpenIdKeyConstant.ACTION_RESULT_OPENID_CALLBACK;
					} else {
						// 去微信服务器获取数据
						OpenIdUserInfo bulidOpenIdUserInfo = this.bulidOpenIdUserInfofoByCode(request, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_BASE);

						if (bulidOpenIdUserInfo != null) {
							openid = bulidOpenIdUserInfo.getOpenId(); // 用户标识
						}

						baseOAuth = true;
					}
				}

				// 用OpenId去DB获取用户信息
				if (StringUtils.isNotBlank(openid)) {
                    TActUsersDto actUsersDtoParam = new TActUsersDto();
                    actUsersDtoParam.setOpenid(openid);
					actUsersDto = actUsersService.getRow(actUsersDtoParam);
				}

				// DB中存在OpenId信息的时候，把DB信息放入Session，不存在时做强制强授权处理
				if (actUsersDto != null) {
					OpenIdUserInfo openIdUserInfo2 = new OpenIdUserInfo();
					openIdUserInfo2.setScope(WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_USERINFO); // 获取方式
					openIdUserInfo2.setHasdetail(true); // 是否拥有详细信息
					openIdUserInfo2.setOpenId(actUsersDto.getOpenid()); // 用户标识
					openIdUserInfo2.setNickname(actUsersDto.getNickname()); // 用户昵称
					openIdUserInfo2.setSex(this._sexOfCSToWeChat(actUsersDto.getSex())); // 性别（1是男性，2是女性，0是未知）
					openIdUserInfo2.setCountry(actUsersDto.getCountry()); // 国家
					openIdUserInfo2.setProvince(actUsersDto.getProvince()); // 省份
					openIdUserInfo2.setCity(actUsersDto.getCity()); // 城市
					openIdUserInfo2.setHeadImgUrl(actUsersDto.getHeadimgurl()); // 用户头像链接
					// 注入SESSION
					request.getSession().setAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY, openIdUserInfo2);
					// Session内容注入上下文内容
					OpenIdContext.setOpenIdUserInfo(openIdUserInfo2);
				} else {
					// 进行强授权处理
					if (StringUtils.isBlank(state) || baseOAuth) {
						// 请求URL
						String askUrl = RequestUrlUtil.buildOriginalOAuthURL(request);
						// 若为非重定向的链接，则设置认证模式
						request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_SCOPE, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_USERINFO);
						// 设置再跳转URL
						request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_REDIRECT_URI, askUrl);
						// 设置重定位判断标识
						request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_USERINFO);

						return WeChatOpenIdKeyConstant.ACTION_RESULT_OPENID_CALLBACK;
					} else {
						// 去微信服务器获取数据
						OpenIdUserInfo bulidOpenIdUserInfo = this.bulidOpenIdUserInfofoByCode(request, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_USERINFO);
						OpenIdContext.setOpenIdUserInfo(bulidOpenIdUserInfo);

						// Insert营销活动参加人员信息表
						if (bulidOpenIdUserInfo != null) {
                            TActUsersDto actUsersDtoNew = new TActUsersDto();
                            actUsersDtoNew.setOpenid(bulidOpenIdUserInfo.getOpenId());// OPENID
                            actUsersDtoNew.setNickname(bulidOpenIdUserInfo.getNickname());// 昵称
                            actUsersDtoNew.setSex(this._sexOfWeChatToCS(bulidOpenIdUserInfo.getSex()));// 性别[CS:1015]
                            actUsersDtoNew.setCountry(bulidOpenIdUserInfo.getCountry());// 国家
                            actUsersDtoNew.setProvince(bulidOpenIdUserInfo.getProvince());// 省份
                            actUsersDtoNew.setCity(bulidOpenIdUserInfo.getCity());// 城市
                            actUsersDtoNew.setHeadimgurl(bulidOpenIdUserInfo.getHeadImgUrl());// 用户头像
							actUsersService.save(actUsersDtoNew);
						}
					}
				}
			} else {
				// Session内容注入上下文中
				OpenIdContext.setOpenIdUserInfo(openIdUserInfo);
			}
//		}

		// 用户调试信息输出
		if (OpenIdContext.getOpenIdUserInfo() != null) {
			logger.info("访问OpenId：" + OpenIdContext.getOpenIdUserInfo().getOpenId());
			logger.info("访问USER  ：" + OpenIdContext.getOpenIdUserInfo().getNickname());
		}

		return invocation.invoke();
	}

	/***
	 * 去微信服务器获取数据
	 *
	 * 一、通过code换取网页授权access_token
	 * 二、拉取用户信息(需scope为 snsapi_userinfo)
	 *
	 * @param request
	 * @param state
	 *
	 * @return OpenIdUserInfo
	 */
	public OpenIdUserInfo bulidOpenIdUserInfofoByCode(HttpServletRequest request, String state) {

		OpenIdUserInfo openIdUserInfo = null;
		// 获取用户信息
		SNSUserInfo snsUserInfo = null;
		// 用户同意授权后，能获取到code
		String code = request.getParameter(WeChatOpenIdKeyConstant.PARAM_OPENID_CODE);
//		String[] codes = request.getParameterValues(WeChatOpenIdKeyConstant.PARAM_OPENID_CODE);
//		if (codes != null && codes.length > 1) {
//			code = codes[1];	// 解决强授权时取Code为弱授权Code
//		}

		// 获取公众号信息
		TPubPlatformDto tPubPlatformDto = WechatInfoUtil.getTPubPlatformDtoByPlatformId(utilConfig.getVip_platform_id());

		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 一、通过code换取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(tPubPlatformDto.getAppid(), tPubPlatformDto.getAppsecret(), code);

			if (weixinOauth2Token != null && StringUtils.isNotBlank(weixinOauth2Token.getOpenId())) {
				openIdUserInfo = new OpenIdUserInfo();
				// 用户标识
				String openId = weixinOauth2Token.getOpenId();
				// 网页授权接口访问凭证
				String accessToken = weixinOauth2Token.getAccessToken();

				// 冗余错逻辑保证至少有OPENID
				openIdUserInfo.setHasdetail(false);	// 是否拥有详细信息
				openIdUserInfo.setOpenId(openId);

				// 如果为强授权时，可获取更多用户信息
				if (WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_USERINFO.equals(state)) {

					// 二、拉取用户信息(需scope为 snsapi_userinfo)
					snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

					if (snsUserInfo != null) {
						try {
							BeanUtils.copyProperties(openIdUserInfo, snsUserInfo);
						} catch (Exception e) {
							e.printStackTrace();
						}
						openIdUserInfo.setHasdetail(true);	// 是否拥有详细信息
					}
				}

				openIdUserInfo.setScope(weixinOauth2Token.getScope());
				// 注入SESSION内 容 用户信息
				request.getSession().setAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY, openIdUserInfo);
			}
		}
		return openIdUserInfo;
	}

	/***
	 * 微信性别转CodeString性别
	 *
	 * @param weChatSex
	 * @return csSex
	 */
	private String _sexOfWeChatToCS(int sex) {
		String strSex = CodeStringConstant.CS_1015_SEX_FLAG_UNKNOWN;

		if (sex == 1) {
			strSex = CodeStringConstant.CS_1015_SEX_FLAG_MALE;
		} else if (sex == 2) {
			strSex = CodeStringConstant.CS_1015_SEX_FLAG_FEMALE;
		}

		return strSex;
	}

	/***
	 * CodeString性别转微信性别
	 *
	 * @param csSex
	 * @return weChatSex
	 */
	private int _sexOfCSToWeChat(String sex) {
		int intSex = 0;

		if (CodeStringConstant.CS_1015_SEX_FLAG_MALE.equals(sex)) {
			intSex = 1;
		} else if (CodeStringConstant.CS_1015_SEX_FLAG_FEMALE.equals(sex)) {
			intSex = 2;
		}

		return intSex;
	}

}
