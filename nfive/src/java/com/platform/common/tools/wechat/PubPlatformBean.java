package com.platform.common.tools.wechat;

import java.io.Serializable;

/**
 * 用于session中存放的信息
 * @author hercules.chen
 */
public class PubPlatformBean implements Serializable{
	
	private java.lang.String platform_id;// 公众平台号ID
	private java.lang.String org_id;// 原始ID
	private java.lang.String wechart_account;// 公众微信号
	private java.lang.String platform_email;// 登录邮箱
	private java.lang.String platform_name;// 公众号名称
	private java.lang.String platform_type;// 公众号类型[CS:5010]
	private java.lang.String platform_desc;// 说明信息
	private java.lang.String is_use;   //是否使用
	private java.lang.String is_valid; //是否有效
	private java.lang.String user_cd;  //当前用户
	
	public java.lang.String getPlatform_id() {
		return platform_id;
	}
	public void setPlatform_id(java.lang.String platformId) {
		platform_id = platformId;
	}
	public java.lang.String getWechart_account() {
		return wechart_account;
	}
	public void setWechart_account(java.lang.String wechartAccount) {
		wechart_account = wechartAccount;
	}
	public java.lang.String getPlatform_email() {
		return platform_email;
	}
	public void setPlatform_email(java.lang.String platformEmail) {
		platform_email = platformEmail;
	}
	public java.lang.String getPlatform_name() {
		return platform_name;
	}
	public void setPlatform_name(java.lang.String platformName) {
		platform_name = platformName;
	}
	public java.lang.String getPlatform_type() {
		return platform_type;
	}
	public void setPlatform_type(java.lang.String platformType) {
		platform_type = platformType;
	}
	public java.lang.String getPlatform_desc() {
		return platform_desc;
	}
	public void setPlatform_desc(java.lang.String platformDesc) {
		platform_desc = platformDesc;
	}
	public java.lang.String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(java.lang.String orgId) {
		org_id = orgId;
	}
	public java.lang.String getIs_use() {
		return is_use;
	}
	public void setIs_use(java.lang.String isUse) {
		is_use = isUse;
	}
	public java.lang.String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(java.lang.String isValid) {
		is_valid = isValid;
	}
	public java.lang.String getUser_cd() {
		return user_cd;
	}
	public void setUser_cd(java.lang.String userCd) {
		user_cd = userCd;
	}


	
	
	
	
		
}
