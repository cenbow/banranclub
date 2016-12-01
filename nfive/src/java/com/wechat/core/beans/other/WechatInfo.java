package com.wechat.core.beans.other;

/***
 * 公众微信号上下文 
 * 
 * @author jia.chen
 */
public class WechatInfo{
	private java.lang.String account;		//公众微信号
	private java.lang.String name;			//公众号名称
	private java.lang.String type;			//公众号类型[CS:5010]
	private java.lang.String appid;			//应用ID
	private java.lang.String appsecret;		//应用密钥
	private java.lang.String token;			//令牌
	
	

	
	public java.lang.String getAccount() {
		return account;
	}
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getAppid() {
		return appid;
	}
	public void setAppid(java.lang.String appid) {
		this.appid = appid;
	}
	public java.lang.String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(java.lang.String appsecret) {
		this.appsecret = appsecret;
	}
	public java.lang.String getToken() {
		return token;
	}
	public void setToken(java.lang.String token) {
		this.token = token;
	}
	
	
	
	
}
