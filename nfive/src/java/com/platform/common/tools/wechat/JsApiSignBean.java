package com.platform.common.tools.wechat;

import java.io.Serializable;

/**
 * JsApiSignBean
 */
public class JsApiSignBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appId; // 公众号的唯一标识
	private String nonceStr; // 签名的随机串
	private String jsapiTicket; // 临时票据
	private String timestamp; // 签名的时间戳
	private String url; // 签名完整URL（不进行URL转义）
	private String signature; // SHA1签名

	private boolean error = false;
	private String errorMsg;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}