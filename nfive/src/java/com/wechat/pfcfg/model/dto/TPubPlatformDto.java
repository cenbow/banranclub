package com.wechat.pfcfg.model.dto;

import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:自动代码生成模板 DTO模板
 * <p>
 * 创建者:
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 * 
 * 规约开启方式 用于规约符合我们要求的对象 1. 设置 AddParentClass = true 2. 类 extends BaseDtoImpl
 * 
 * 规约关闭方式 用于规约不受我们控制的对象 1. 设置 AddParentClass = false 2. 类 implements IBaseDto
 */
@SuppressWarnings("serial")
@EntityPK(Pk = "platform_id", defaultColumn = false, tableName = "T_PUB_PLATFORM")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TPubPlatformDto extends BaseDtoImpl {//
	private java.lang.String platform_id;// 公众平台号ID
	private java.lang.String org_id;// 原始ID
	private java.lang.String wechart_account;// 公众微信号
	private java.lang.String platform_email;// 登录邮箱
	private java.lang.String platform_name;// 公众号名称
	private java.lang.String platform_type;// 公众号类型[CS:5010]
	private java.lang.String platform_desc;// 说明信息
	private java.lang.String appid;// 应用ID
	private java.lang.String appsecret;// 应用密钥
	private java.lang.String token;// 令牌
	private java.lang.String is_display_title;// 是否显示标题部分
	private java.lang.String display_time;// 显示时间
	private java.lang.String title_style;// 标题部分样式
	
	
	public java.lang.String getDisplay_time() {
		return display_time;
	}

	public void setDisplay_time(java.lang.String displayTime) {
		display_time = displayTime;
	}

	public java.lang.String getTitle_style() {
		return title_style;
	}

	public void setTitle_style(java.lang.String titleStyle) {
		title_style = titleStyle;
	}

	public java.lang.String getIs_display_title() {
		return is_display_title;
	}

	public void setIs_display_title(java.lang.String isDisplayTitle) {
		is_display_title = isDisplayTitle;
	}


	/**
	 * 获取 公众平台号ID
	 * 
	 * @return
	 */

	public java.lang.String getPlatform_id() {
		return platform_id;
	}

	/**
	 * 设置 公众平台号ID
	 * 
	 * @param platform_id
	 */
	public void setPlatform_id(java.lang.String platform_id) {
		this.platform_id = platform_id;
	}
	
	public java.lang.String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(java.lang.String orgId) {
		org_id = orgId;
	}

	/**
	 * 获取 公众微信号
	 * 
	 * @return
	 */
	public java.lang.String getWechart_account() {
		return wechart_account;
	}

	/**
	 * 设置 公众微信号
	 * 
	 * @param wechart_account
	 */
	public void setWechart_account(java.lang.String wechart_account) {
		this.wechart_account = wechart_account;
	}

	/**
	 * 获取 登录邮箱
	 * 
	 * @return
	 */
	public java.lang.String getPlatform_email() {
		return platform_email;
	}

	/**
	 * 设置 登录邮箱
	 * 
	 * @param platform_email
	 */
	public void setPlatform_email(java.lang.String platform_email) {
		this.platform_email = platform_email;
	}

	/**
	 * 获取 公众号名称
	 * 
	 * @return
	 */
	public java.lang.String getPlatform_name() {
		return platform_name;
	}

	/**
	 * 设置 公众号名称
	 * 
	 * @param platform_name
	 */
	public void setPlatform_name(java.lang.String platform_name) {
		this.platform_name = platform_name;
	}

	/**
	 * 获取 公众号类型[CS:5010]
	 * 
	 * @return
	 */
	public java.lang.String getPlatform_type() {
		return platform_type;
	}

	/**
	 * 设置 公众号类型[CS:5010]
	 * 
	 * @param platform_type
	 */
	public void setPlatform_type(java.lang.String platform_type) {
		this.platform_type = platform_type;
	}

	/**
	 * 获取 说明信息
	 * 
	 * @return
	 */
	public java.lang.String getPlatform_desc() {
		return platform_desc;
	}

	/**
	 * 设置 说明信息
	 * 
	 * @param platform_desc
	 */
	public void setPlatform_desc(java.lang.String platform_desc) {
		this.platform_desc = platform_desc;
	}

	/**
	 * 获取 应用ID
	 * 
	 * @return
	 */
	public java.lang.String getAppid() {
		return appid;
	}

	/**
	 * 设置 应用ID
	 * 
	 * @param appid
	 */
	public void setAppid(java.lang.String appid) {
		this.appid = appid;
	}

	/**
	 * 获取 应用密钥
	 * 
	 * @return
	 */
	public java.lang.String getAppsecret() {
		return appsecret;
	}

	/**
	 * 设置 应用密钥
	 * 
	 * @param appsecret
	 */
	public void setAppsecret(java.lang.String appsecret) {
		this.appsecret = appsecret;
	}

    /**
	* 获取令牌
	* @param token
	*/
    public java.lang.String getToken() {
		return token;
	}
    /**
	* 设置令牌
	* @param token
	*/
	public void setToken(java.lang.String token) {
		this.token = token;
	}


}
