package com.pub.persistence.model;

import com.pub.common.local.model.dto.TEmpUserDto;

/**
 * 类功能:自动代码生成模板   QueryBean 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TEmpUserQueryBean extends TEmpUserDto {

	private java.lang.String     orderStr;//排序字串

	private String role_id;  //角色Id

	private String platform_id;  //公众号Id

	private String old_pass; //原有密码

	private String new_pass;//新密码

	private String again_new_pass; //重复密码

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platformId) {
		platform_id = platformId;
	}

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getOld_pass() {
		return old_pass;
	}

	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}

	public String getNew_pass() {
		return new_pass;
	}

	public void setNew_pass(String new_pass) {
		this.new_pass = new_pass;
	}

	public String getAgain_new_pass() {
		return again_new_pass;
	}

	public void setAgain_new_pass(String again_new_pass) {
		this.again_new_pass = again_new_pass;
	}


}