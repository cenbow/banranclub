package com.platform.common.tools.opensymphony.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.platform.common.tools.constant.WeChatOpenIdKeyConstant;
import com.platform.common.tools.opensymphony.web.context.OpenIdContext;
import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.other.WeixinOauth2Token;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

/***
 * <P>
 * 用于微信自动认证拦截
 * 配置此拦截器的画面如果发现SESSION不存在OPENID 相关信息
 * 则默认使用二次跳转的方式再次获取OPENID
 * 1.存入浏览器SESSION主动维持登录状态
 * 拦截逻辑
 *  A首次访问  = 拦截启动
 * （1.OPENID为空
 *   2.二次访问标志位空 （ STATE）
 *  ）
 *  B判断为二次回调访问  = 放弃拦截
 * （1.OPENID为空
 *   2.二次访问标志位为特定值（）
 *  ）
 *  C判断为有已OPENID =  放弃拦截
 * （
 * 	1.OPENID不为空
 *  ）
 * </P>
 * @author 陈佳
 */
public class WeChatOpenIdBaseActivityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(WeChatOpenIdBaseActivityInterceptor.class);
	
	@Autowired
	private UtilConfig utilConfig;
	public WeChatOpenIdBaseActivityInterceptor() {
   
    }
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {   
	    //设置request response
        HttpServletRequest  request = ServletActionContext. getRequest(); 
        // 初始化（不同的WEB请求可能会使用同一个后台线程做操作，从而取到脏数据）
        OpenIdContext.setOpenIdUserInfo(null);
        
        //1.获取SESSION中 OPENID信息
        OpenIdUserInfo openIdUserInfo  = (OpenIdUserInfo)request.getSession().getAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY);
        String openid   =  openIdUserInfo!=null?openIdUserInfo.getOpenId():null;
        
        //2.获取重定位判断标识
        String state = (String)request.getParameter(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE);

        //3.获取浏览器类型标识
//        String userAgent = request.getHeader("User-Agent");
//        boolean wechatBrowserFlg = userAgent != null && userAgent.contains(WeChatOpenIdKeyConstant.USER_AGENT_WECHAT);

        //4.判定拦截逻辑
        if(openIdUserInfo==null&&(StringUtils.isEmpty(state))){
            //A首次访问  = 拦截启动 1.OPENID为空   2.二次访问标志位空 （ STATE）
        	//设置认证模式
        	request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_SCOPE, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_BASE);
            //设置再跳转URL 
        	request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_REDIRECT_URI, RequestUrlUtil.buildOriginalURL(request));
        	//设置重定位判断标识
        	request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE, WeChatOpenIdKeyConstant.VALUE_OPENID_REDIRECTED);
        	return WeChatOpenIdKeyConstant.ACTION_RESULT_OPENID_CALLBACK;
        }
        else if(openIdUserInfo==null&&(StringUtils.isNotEmpty(state)))
        {
            //B判断为二次回调访问   = 放弃拦截   1.OPENID为空  2.二次访问标志位为特定值（）
        	OpenIdContext.setOpenIdUserInfo(this.bulidOpenIdUserInfofoByCode(request));
        }
        else if(StringUtils.isNotEmpty(openid)){
            //C判断为有已OPENID   放弃拦截   1.OPENID不为空
        	//SESION内容注入上下文内容
        	OpenIdContext.setOpenIdUserInfo((OpenIdUserInfo)request.getSession().getAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY));
        }
        
        if(OpenIdContext.getOpenIdUserInfo()!=null){
            System.out.println("访问ID="+OpenIdContext.getOpenIdUserInfo().getOpenId());
        	System.out.println("访问user="+OpenIdContext.getOpenIdUserInfo().getNickname());
        }else{
        	System.out.println("WeChatOpenIdBaseActivityInterceptor#intercept取不到用户信息");
        	//微信服务器链接不上显示系统忙画面
        	return WeChatOpenIdKeyConstant.ACCESS_WECHART_SERVER_ERR;
        }
    	String result = invocation.invoke();   
        return result;
	}
	
	/***
	 * 
	 * @param request
	 * @return
	 */
	public OpenIdUserInfo bulidOpenIdUserInfofoByCode(HttpServletRequest request) {
	    
	    OpenIdUserInfo openIdUserInfo = new OpenIdUserInfo();
		// 获取用户信息
		//SNSUserInfo snsUserInfo = null;
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		// 获取公众号信息
		TPubPlatformDto tPubPlatformDto = WechatInfoUtil.getTPubPlatformDtoByPlatformId(utilConfig.getVip_platform_id());
		
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(tPubPlatformDto.getAppid(),tPubPlatformDto.getAppsecret(), code);
			//ADD BY ZHUBAODING 2015/02/05 注释了不需要访问的代码
			if(weixinOauth2Token != null){
				// 网页授权接口访问凭证
				//	String accessToken = weixinOauth2Token.getAccessToken();	
					// 用户标识
					String openId = weixinOauth2Token.getOpenId();
//				    snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
//				    if(snsUserInfo==null){
//						//冗余错逻辑保证至少有OPENID
//					    openIdUserInfo= new OpenIdUserInfo();
//						openIdUserInfo.setHasdetail(false);
//					    openIdUserInfo.setOpenId(openId);
//				   }else
//				   {
//					    openIdUserInfo= new OpenIdUserInfo();
//						try {
//							BeanUtils.copyProperties(openIdUserInfo, snsUserInfo);
//						} catch(Exception e) {
//							e.printStackTrace();
//						}
//						openIdUserInfo.setHasdetail(true);
//					    openIdUserInfo.setOpenId(openId);
//				   }
				   openIdUserInfo.setOpenId(openId);
				   openIdUserInfo.setScope(weixinOauth2Token.getScope());
		           //注入SESSION内 容   用户信息
		           request.getSession().setAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY, openIdUserInfo);
			}
		}
		
		return openIdUserInfo;
	}
}
