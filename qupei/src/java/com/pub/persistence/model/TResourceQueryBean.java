package com.pub.persistence.model;

import com.pub.common.local.model.dto.TResourceDto;

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
public class TResourceQueryBean extends TResourceDto {

	private java.lang.String     orderStr;//排序字串

	private java.lang.String parent_res_id;

	private java.lang.String role_id; //角色id

	private String user_id;//用户id

	private String parent_name;

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public java.lang.String getParent_res_id() {
		return parent_res_id;
	}

	public void setParent_res_id(java.lang.String parent_res_id) {
		this.parent_res_id = parent_res_id;
	}

	public java.lang.String getRole_id() {
		return role_id;
	}

	public void setRole_id(java.lang.String role_id) {
		this.role_id = role_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parentName) {
		parent_name = parentName;
	}

}