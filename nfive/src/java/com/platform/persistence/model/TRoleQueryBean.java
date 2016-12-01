package com.platform.persistence.model;
import com.platform.persistence.model.dto.TRoleDto;

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
public class TRoleQueryBean extends TRoleDto{
	
	private java.lang.String     orderStr;//排序字串
	
	private String res_id; //资源id
	
	private String user_id;//用户id
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}