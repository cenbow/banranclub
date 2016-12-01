package com.pub.common.tools.opensymphony.web.context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.hercules.factory.SpringContextUtil;

/***
 * @author CHENJIA
 */
public class UserContext {
	
	//获取信息
	private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
    //反馈信息
	private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	//Spring信息
	private static ThreadLocal<ApplicationContext> springContext = new ThreadLocal<ApplicationContext>();
	
	//辅助类获取当前查询条件以及方式的URL
	private static ThreadLocal<String>   orgUrl =   new ThreadLocal<String>();
	
	
	
	private UserContext() {
		
	}

	
	public static HttpServletRequest getRequest() {
		return request.get();
	}

	public static void setRequest(HttpServletRequest request) {
		UserContext.request.set(request);
	}

	public static HttpServletResponse getResponse() {
		return response.get();
	}

	public static void setResponse(HttpServletResponse response) {
		UserContext.response.set(response);
	}

	public static ApplicationContext getSpringContext() {
		return SpringContextUtil.getApplicationContext();
	}

	public static void setSpringContext(ApplicationContext springContext) {
		UserContext.springContext.set(springContext);
	}

	
    
	/***
	 * 获取原始全条件URL信息
	 * */
	public static String getOrgUrl() {
		return UserContext.orgUrl.get();
	}

	/***
	 * 设置原始全条件URL信息
	 * */
	public static void setOrgUrl(String orgUrl) {
		UserContext.orgUrl.set(orgUrl);
	}
	
	
	

}
