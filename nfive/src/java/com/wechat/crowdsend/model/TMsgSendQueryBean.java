package com.wechat.crowdsend.model;

import com.wechat.crowdsend.model.dto.TMsgSendDto;

/**
 * 类功能:群发管理发送表查询参数
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TMsgSendQueryBean extends TMsgSendDto{
	
	private static final long serialVersionUID = 1L;
	private java.lang.String     orderStr;//排序字串
	private java.lang.String search_sendTime_start;//发送时间（开始）
	private java.lang.String search_sendTime_end;//发送时间（结束）
	private java.lang.String fans_crowd;//粉丝群
	private java.lang.String result_pkid;//发送结果表ID
	private java.lang.String msg_code;//微信返回的msgID，用户回调时确定批次号
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public java.lang.String getFans_crowd() {
		return fans_crowd;
	}

	public void setFans_crowd(java.lang.String fansCrowd) {
		fans_crowd = fansCrowd;
	}

	public java.lang.String getSearch_sendTime_start() {
		return search_sendTime_start;
	}

	public void setSearch_sendTime_start(java.lang.String searchSendTimeStart) {
		search_sendTime_start = searchSendTimeStart;
	}

	public java.lang.String getSearch_sendTime_end() {
		return search_sendTime_end;
	}

	public void setSearch_sendTime_end(java.lang.String searchSendTimeEnd) {
		search_sendTime_end = searchSendTimeEnd;
	}

	public java.lang.String getResult_pkid() {
		return result_pkid;
	}

	public void setResult_pkid(java.lang.String resultPkid) {
		result_pkid = resultPkid;
	}

	public java.lang.String getMsg_code() {
		return msg_code;
	}

	public void setMsg_code(java.lang.String msgCode) {
		msg_code = msgCode;
	}
	
}