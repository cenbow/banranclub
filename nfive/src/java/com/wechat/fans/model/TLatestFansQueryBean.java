package com.wechat.fans.model;

import com.wechat.fans.model.dto.TLatestFansDto;

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
public class TLatestFansQueryBean extends TLatestFansDto{
	
	private String nick_name; //昵称
	private String remark_name; //备注名

	private java.lang.String orderStr;	//排序字串

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nickName) {
		nick_name = nickName;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remarkName) {
		remark_name = remarkName;
	}

}