package com.platform.common.tools.opensymphony.web.interceptor;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.platform.common.tools.constant.WeChatOpenIdKeyConstant;
/***
 * <p>用于URL的处理</p>
 * @author 陈佳
 *
 */
public class RequestUrlUtil {
	/**
     * 组装完整的url整句，包括条件与条件的值，其中排除了若干条件的组装
     * 这个是一个预留接口 
     * @param request
     * @return url整句
     */
    public static String buildOriginalURL(HttpServletRequest request) {     	
        StringBuffer originalURL = new StringBuffer();
        originalURL.append( request.getScheme()+"://"+request.getServerName());
        if(80!=request.getServerPort()){
        	 originalURL.append( ":"+request.getServerPort());
        }
        originalURL.append(request.getContextPath());
        originalURL.append(request.getServletPath());   
        
        
        request.getQueryString();       
        Map parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            originalURL.append("?");
            for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                    String[] values = (String[]) parameters.get(key);
                    for (int i = 0; i < values.length; i++) {
                        originalURL.append(key).append("=").append(values[i]).append("&");
                   }
            }
            return originalURL.substring(0, originalURL.length() - 1).toString() ;
        }else
            return originalURL.toString();
    }
    
	/**
     * 组装相对url，包括条件与条件的值，其中排除了若干条件的组装
     * 这个是一个预留接口 
     * @param request
     * @return url整句
     */
    public static String buildOriginalRelativeURL(HttpServletRequest request) {     	
        StringBuffer originalURL = new StringBuffer();
//        originalURL.append( request.getScheme()+"://"+request.getServerName());
//        if(80!=request.getServerPort()){
//        	 originalURL.append( ":"+request.getServerPort());
//        }
//        originalURL.append(request.getContextPath());
        originalURL.append(request.getServletPath().replace("/", ""));   
        
        
        request.getQueryString();       
        Map parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            originalURL.append("?");
            for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key = (String) iter.next();
                    String[] values = (String[]) parameters.get(key);
                    for (int i = 0; i < values.length; i++) {
                        originalURL.append(key).append("=").append(values[i]).append("&");
                   }
            }
            return originalURL.substring(0, originalURL.length() - 1).toString() ;
        }else
            return originalURL.toString();
    }

	/**
     * 组装完整的URL整句，包括条件与条件的值，其中排除了若干条件的组装
     * 由于微信网页OAuth2.0授权会主动添加code，state两属性，获取原始URL时需要剔除这两个属性
     * 
     * 这个是一个预留接口 
     * @param request
     * @return url整句
     */
	public static String buildOriginalOAuthURL(HttpServletRequest request) {
		StringBuffer originalURL = new StringBuffer();
		originalURL.append(request.getScheme() + "://" + request.getServerName());
		if (80 != request.getServerPort()) {
			originalURL.append(":" + request.getServerPort());
		}
		originalURL.append(request.getContextPath());
		originalURL.append(request.getServletPath());

		request.getQueryString();
		Map parameters = request.getParameterMap();
		if (parameters != null && parameters.size() > 0) {
			originalURL.append("?");
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();

				if (!WeChatOpenIdKeyConstant.PARAM_OPENID_CODE.equalsIgnoreCase(StringUtils.trimToNull(key))
						&& !WeChatOpenIdKeyConstant.PARAM_OPENID_STATE.equalsIgnoreCase(StringUtils.trimToNull(key))) {
					String[] values = (String[]) parameters.get(key);
					for (int i = 0; i < values.length; i++) {
						originalURL.append(key).append("=").append(values[i]).append("&");
					}
				}

			}
			return originalURL.substring(0, originalURL.length() - 1).toString();
		} else
			return originalURL.toString();
	}
}
