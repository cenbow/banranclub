package com.platform.common.tools.opensymphony.web.context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.platform.common.tools.opensymphony.web.context.vo.OpenIdUserInfo;

/****
 *
 * @author hercuels.chen
 * <p>
 * 获取从微信服务器获取的客户OPENID相关信息
 * snsUserInfo
 * </p>
 */
public class OpenIdContext {
    // 获取信息
    private static ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
    // 反馈信息
    private static ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
    // 微信关注的用户信息
    private static ThreadLocal<OpenIdUserInfo> openIdUserInfo = new ThreadLocal<OpenIdUserInfo>();

    public OpenIdContext() {

    }

    public static HttpServletRequest getRequest() {
        return request.get();
    }

    public static void setRequest(HttpServletRequest request) {
        OpenIdContext.request.set(request);
    }

    public static HttpServletResponse getResponse() {
        return response.get();
    }

    public static void setResponse(HttpServletResponse response) {
        OpenIdContext.response.set(response);
    }

    public static OpenIdUserInfo getOpenIdUserInfo() {
    	return openIdUserInfo.get();
    }

    public static void setOpenIdUserInfo(OpenIdUserInfo openIdUserInfo) {
        OpenIdContext.openIdUserInfo.set(openIdUserInfo);
    }

}
