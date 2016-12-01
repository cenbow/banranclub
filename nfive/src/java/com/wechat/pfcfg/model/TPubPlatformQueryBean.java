package com.wechat.pfcfg.model;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

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
public class TPubPlatformQueryBean extends TPubPlatformDto{
	
	private java.lang.String     orderStr;//排序字串
	
	private String user_id;  //用户Id
	
	private String user_cd;  //用户cd

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId;
	}
	
	public String getUser_cd() {
		return user_cd;
	}

	public void setUser_cd(String userCd) {
		user_cd = userCd;
	}
	
	
}