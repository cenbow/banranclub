package com.platform.common.tools.wechat;

import java.io.Serializable;


/**
 * LoginSessionBean 模板
 */
public class LoginSessionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean autoLoginFlag = false; // 是否为微信免登录方式登录

	public boolean isAutoLoginFlag() {
		return autoLoginFlag;
	}

	public void setAutoLoginFlag(boolean autoLoginFlag) {
		this.autoLoginFlag = autoLoginFlag;
	}

}