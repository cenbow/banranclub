package com.platform.common.tools.constant;

/**
 * 
 * @author Administrator
 * 管理OPENID 在SESSION 和  MEMCACHE缓存中的KEY
 */
public class WeChatOpenIdKeyConstant{
	
	//SESSION_OPENID
    public static final String SESSION_OPENID_KEY = "$LD$_$SKEY$_OPENID";//当前用户SESSION OPENID 信息KEY
    
    
    //REQUEST_OPDENID
    public static final String PARAM_OPENID_REDIRECT_URI  = "redirect_uri";//回调参数
    public static final String PARAM_OPENID_STATE = "state";
    public static final String PARAM_OPENID_IN_CACHE = "inCache"; //是否存入缓存
    public static final String PARAM_OPENID_SCOPE = "scope";
    public static final String PARAM_OPENID_CODE  = "code";
    
    public static final String VALUE_OPENID_REDIRECTED = "true";                //是否已经是回调模式
    public static final String VALUE_OPENID_SCOPE_BASE = "snsapi_base";         //是scope snsapi_base
    public static final String VALUE_OPENID_SCOPE_USERINFO= "snsapi_userinfo";  //是scope snsapi_userinfo
    
    //CACHE_OPENID_KEYINFO
    public static final String CACHE_OPENID_KEY = "$LD$_$MKEY$_OPENID";//当前用户CACHE OPENID 信息KEY
    
    //ACTION_RESULT
	public static final String ACTION_RESULT_OPENID_CALLBACK="openid_callback";
	
	  //登录后保存在session中的用户信息
	public static final String USER_LOGIN_SESSION="$LD$_$LOGIN$_MAIN";

	// User-Agent（微信）
	public static final String USER_AGENT_WECHAT = "MicroMessenger";
	
	// 访问微信服务器
	public static final String ACCESS_WECHART_SERVER_ERR = "weixin_conn_error";
	
}
