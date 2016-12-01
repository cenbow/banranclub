package com.platform.common.tools.opensymphony.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * <P>
 * 用于检查当前发送的信息是否从微信浏览器中发送
 * 若非微信浏览器则默认重定向到提示页
 * </P>
 * @author 陈佳
 */
public class WeChatBrowserInterceptor  extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(WeChatBrowserInterceptor.class);
	
    //非微信浏览器常量
    public  static final String NOWECHAT_BROWSER = "NOWECHAT_BROWSER";
	
	public WeChatBrowserInterceptor() {
   
    }
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
        //get request and response
        HttpServletRequest  request = ServletActionContext. getRequest();  
        HttpServletResponse response = ServletActionContext.getResponse();   
        
		String userAgent = request.getHeader("User-Agent");
		
		String result = invocation.invoke();        
	  
		//若为微信内置浏览器 则继续
		if(userAgent.contains("MicroMessenger")){
		     return result;
		}
		else{
		    //若其他浏览器则跳转必须为微信浏览器访问的提示画面
			 return NOWECHAT_BROWSER;
		}
		
	}
}
