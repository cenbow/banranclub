package com.wechat.crowdsend.model;

import com.wechat.crowdsend.model.dto.TMsgSendResultDto;

/**
 * 类功能:群发管理 发送结果表查询对象
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TMsgSendResultQueryBean extends TMsgSendResultDto{
	
	
	private static final long serialVersionUID = 1L;
	private java.lang.String     orderStr;//排序字串
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
}