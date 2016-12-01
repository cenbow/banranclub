package com.platform.common.tools.opensymphony.web.interceptor;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
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
import com.wechat.core.beans.other.SNSUserInfo;
import com.wechat.core.beans.other.WeixinOauth2Token;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

/***
 * <P>
 * 用于微信自动认证拦截
 * 配置此拦截器用于强制授权页面的拦截
 * @author 陈佳
 */
public class WeChatOpenIdUserInfoInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(WeChatOpenIdUserInfoInterceptor.class);
	

	
	@Autowired
	private UtilConfig utilConfig;
	public WeChatOpenIdUserInfoInterceptor() {
   
    }
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {   
	    //设置request response
        HttpServletRequest  request = ServletActionContext. getRequest();
        // 初始化（不同的WEB请求可能会使用同一个后台线程做操作，从而取到脏数据）
        OpenIdContext.setOpenIdUserInfo(null);

        //1.获取重定位判断标识
        String state = (String)request.getParameter(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE);

        //2.获取浏览器类型标识
        String userAgent = request.getHeader("User-Agent");
        boolean wechatBrowserFlg = userAgent != null && userAgent.contains(WeChatOpenIdKeyConstant.USER_AGENT_WECHAT);

        //3.判断是否重定向过来的链接 且 必须是微信浏览器请求
        if (wechatBrowserFlg) {
        	if(StringUtils.isEmpty(state)){
            	//若为非重定向的链接    则设置认证模式
            	request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_SCOPE, WeChatOpenIdKeyConstant.VALUE_OPENID_SCOPE_USERINFO);
                //设置再跳转URL 
            	request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_REDIRECT_URI, RequestUrlUtil.buildOriginalURL(request));
            	//设置重定位判断标识
            	request.setAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE, WeChatOpenIdKeyConstant.VALUE_OPENID_REDIRECTED);
            	return WeChatOpenIdKeyConstant.ACTION_RESULT_OPENID_CALLBACK;
            }
            else{
            	//若为重定向的链接  这再次获取用户信息刷新
                OpenIdContext.setOpenIdUserInfo(this.bulidOpenIdUserInfofoByCode(request));
            }
        }
        
        if(OpenIdContext.getOpenIdUserInfo()!=null){
            System.out.println("访问ID="+OpenIdContext.getOpenIdUserInfo().getOpenId());
        	System.out.println("访问user="+OpenIdContext.getOpenIdUserInfo().getNickname());
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
	    
	    OpenIdUserInfo openIdUserInfo = null;
		// 获取用户信息
		SNSUserInfo snsUserInfo = null;
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		// 获取公众号信息
		TPubPlatformDto tPubPlatformDto = WechatInfoUtil.getTPubPlatformDtoByPlatformId(utilConfig.getVip_platform_id());
		
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(tPubPlatformDto.getAppid(),tPubPlatformDto.getAppsecret(), code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getAccessToken();	
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
		    snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
	
		    if(snsUserInfo==null){
				//冗余错逻辑保证至少有OPENID
			    openIdUserInfo= new OpenIdUserInfo();
				openIdUserInfo.setHasdetail(false);
			    openIdUserInfo.setOpenId(openId);
		   }else
		   {
			    openIdUserInfo= new OpenIdUserInfo();
				try {
					BeanUtils.copyProperties(openIdUserInfo, snsUserInfo);
				} catch(Exception e) {
					e.printStackTrace();
				}
				openIdUserInfo.setHasdetail(true);
			    openIdUserInfo.setOpenId(openId);
		   }
		   openIdUserInfo.setScope(weixinOauth2Token.getScope());
           //注入SESSION内 容   用户信息
           request.getSession().setAttribute(WeChatOpenIdKeyConstant.SESSION_OPENID_KEY, openIdUserInfo);
            
		}
		return openIdUserInfo;
	}

    
}

	
	
