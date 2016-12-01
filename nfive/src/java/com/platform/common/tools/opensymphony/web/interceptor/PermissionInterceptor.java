package com.platform.common.tools.opensymphony.web.interceptor;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.platform.common.tools.opensymphony.web.context.UserContext;
import com.platform.common.tools.permission.LoginUserInfoUtil;

/**
 * @author chenjia
 * 这个类是权限控制的基类 
 * 作为所有ACTION的反射层次的前置阻挡，以保证有权限的用户能顺利通过验证
 * 没有登录的用户直接跳转登陆页面
 * 没有权限的用户自动跳转无权限页面
 */
public class PermissionInterceptor extends AbstractInterceptor  {
	
	protected static final Log log = LogFactory.getLog(PermissionInterceptor.class);
	
	//无权限检查常量
    public static final String NOPERMISSION = "nopermission";
    //无登陆常量
    public static final String NOLOGIN = "nologin";
    
    public static final String ACTION = "action";
    
    //不执行权限拦截的方法
    public static final String[] excludeMethods = new String[]{"login","logout"};
    
    public void destroy() {
        System.out.println("***************destroy *******");
    }

    public void init() {

    }

    protected void before(ActionInvocation invocation) {
        System.out.println("***************before *******");
    }

    protected void after(ActionInvocation arg0, String arg1) throws Exception {
        System.out.println("***************after ********");
    }

    public String intercept(ActionInvocation invocation) throws Exception {
	   if (log.isDebugEnabled()) {log.debug("intercept '"
                 + invocation.getProxy().getNamespace() + "/"
                 + invocation.getProxy().getActionName() + "' { ");
	   }   
	   

	   
	    //是否开启权限校验未开启属于调试模式直接放行
	    if(!LoginUserInfoUtil.CHECK_PERMISION){
		   return invocation.invoke();
		}
	    
        //查看是否在被排除的方法列表中若在直接放行
     	String method = invocation.getProxy().getMethod();
     	for(String method_ : excludeMethods)
     	{
     		if(method_.equals(method)){
     			return invocation.invoke();
     		}
     	}
         
	      //获取内部对象
	      HttpServletRequest request = UserContext.getRequest();
	      //HttpServletResponse response =UserContext.getResponse();
          //ActionContext ctx=invocation.getInvocationContext();
          
	      //原始权限URL
          String originalURL = RequestUrlUtil.buildOriginalURL(request); 
          log.info("originalUrl="+originalURL);
          
          //权限对应的action名称
          String actionStr  = parseAction(originalURL);
          
          //判断是否有用户已登陆
          if(LoginUserInfoUtil.isLogin()){
         	 return NOLOGIN;
          }
          
          //调用权限接口检查权限
          if(LoginUserInfoUtil.checkRightByUrl(actionStr)){
         	 return NOPERMISSION;
          }
         
          //通过检查后的执行
          final String result = invocation.invoke();     
          
          //度量返回链操作
          return result;
    }

    
    
    
    
    /****
     * 获得当时的URL 属性值的记录 以便以后的目录都可以返回 ADD BY CJ
     * ***/
    private String createPreUrl(HttpServletRequest request)
    {
    	String preUrl = request.getServletPath();	
    	return preUrl;
    }
    
    
    /**
     * 
     * @param originalUrl
     * @return 析出的action 
     */
    private String parseAction(String originalUrl) {       	
    	 //空条件直接置反
    	 if(!StringUtils.isNotEmpty(originalUrl)){
	    	 return "";
    	 }  	 
    	 String tmpOriginalUrl =originalUrl.substring(0, originalUrl.indexOf(ACTION)); 
    	 int bIndex=    tmpOriginalUrl.lastIndexOf("/")+1;
         int endIndex = 	originalUrl.indexOf(ACTION)-1;
         return tmpOriginalUrl.substring(bIndex, endIndex);
    }
    
    
    

}
