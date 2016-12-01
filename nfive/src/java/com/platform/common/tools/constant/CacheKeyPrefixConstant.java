package com.platform.common.tools.constant;

/***
 * @author 陈佳 CACHE 添加前缀的常量 用于区分不同业务
 */
public class CacheKeyPrefixConstant {

	// ------------------缓存参数-----------------
	/** 微信缓存 TOKEN KEY **/
	// 微信操作令牌缓存key前缀
	public static final String ACCESSTOKEN_CACHE_KEY_PREFIX = "ACCESSTOKEN_$_";
	// 微信操作令牌缓存中失效的时间(2小时)
	public static final int ACCESSTOKEN_TX_EXPIRATION_TIME = 2 * 3600 - 600;

	/** 微信缓存 TICKET KEY **/
	// 微信JS接口的临时票据缓存key前缀
	public static final String JSAPITICKET_CACHE_KEY_PREFIX = "JSAPITICKET_$_";
	// 微信JsApi签名缓存key前缀
	public static final String WXJSAPISIGN_CACHE_KEY_PREFIX = "WXJSAPISIGN_$_";
	// 微信JS接口的临时票据缓存中失效的时间(2小时)
	public static final int JSAPITICKET_TX_EXPIRATION_TIME = 2 * 3600 - 600;

	/** SESSION KEY **/
	// WEB Session缓存 KEY
	public static final String SESSION_WEB_KEY = "$LD$_SESSION_$_WEB_KEY_$"; // 当前用户SESSION 信息KEY
	// Memcache SESSION缓存key前缀
	public static final String SESSION_MEMCACHE_KEY_PREFIX = "$LD$_SESSION_$_MEMCACHE_KEY_$_";
	// Memcache SESSION缓存失效时间(2小时)**/
	public static final int SESSION_MEMCACHE_EXPIRATION_TIME = 120 * 60;

	/** 素材缓存KEY **/
	// 微信素材缓存key前缀
	public static final String METERIAL_CACHE_KEY_PREFIX = "METERIAL_$_";
	// 多媒体文件上传到腾讯服务器上的失效时间(3天)
	public static final int METERIAL_TX_EXPIRATION_TIME = 3 * 24 * 3600 - 600;

	/** 验证码缓存KEY **/
	// 手机验证码缓存key（账户模块）
	public static final String ACCOUNT_MOBILE_VERIFYCODE_CACHE_KEY_PREFIX = "ACCOUNT_MOBILE_VC_$_";
	// 修改密码验证码缓存key（账户模块）
	public static final String CHANGEPASSWORD_MOBILE_VERIFYCODE_CACHE_KEY_PREFIX = "CHANGEPASSWORD_MOBILE_VC_$_";
	// 图片验证码缓存key（账户模块）
	public static final String ACCOUNT_IMAGE_VERIFYCODE_CACHE_KEY_PREFIX = "ACCOUNT_IMAGE_VC_$_";
	// 手机VS图片验证码缓存失效时间(5分钟)**/
	public static final int MOBILEVERIFYCODE_EXPIRATION_TIME = 5 * 60;
	/**crm账户绑定微信账户缓存key*/
	public static final String ACCOUNT_CRMACCOUNT_BIND_WECHATACCOUNT_KEY_PREFIX = "ACCOUNT_CRMBINDWECHAT_$_";	
	/**修改账户密码缓存key*/
	public static final String ACCOUNT_CHANGEPASSWORD_KEY_PREFIX = "ACCOUNT_CHANGEPASSWORD_$_";

}
