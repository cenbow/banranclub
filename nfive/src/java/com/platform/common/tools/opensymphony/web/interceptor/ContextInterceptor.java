package com.platform.common.tools.opensymphony.web.interceptor;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hercules.factory.SpringContextUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.platform.common.tools.opensymphony.web.context.UserContext;


/**
 * 上下文拦截器用于加载默认的上下文信息
 * @author chenjia
 */
public class ContextInterceptor extends AbstractInterceptor {
	
	
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(ContextInterceptor.class);
	public ContextInterceptor() {
   
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("intercept '"
                    + invocation.getProxy().getNamespace() + "/"
                    + invocation.getProxy().getActionName() + "' { ");
        }        
        //get request and response
        HttpServletRequest  request = ServletActionContext. getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();   
        
        //设置request response
        UserContext.setRequest(request);
        UserContext.setResponse(response);      
        
        //设置spring
        UserContext.setSpringContext(SpringContextUtil.getApplicationContext());
       
        //设置原始URL
        UserContext.setOrgUrl(RequestUrlUtil.buildOriginalURL(request));
        
        
        log.debug("UserContext 中的 request response springContext 填充完毕! "); 
        
        final String result = invocation.invoke();        
        
        
        return result;
    }

    /**
     * Save the given locale to the ActionInvocation.
     * @param invocation The ActionInvocation.
     * @param locale The locale to save.
     */
    protected void saveLocale(ActionInvocation invocation, Locale locale) {
        invocation.getInvocationContext().setLocale(locale);
    }
    
    
    
}
