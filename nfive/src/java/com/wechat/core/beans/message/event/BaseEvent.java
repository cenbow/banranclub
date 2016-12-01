package com.wechat.core.beans.message.event;

/**
 * 事件基类
 */
public class BaseEvent {

	// 开发者微信号
	private String  FoUserName;
	
	// 发送方帐号（一个OpenID）
	private String  CromUserName;
	
	// 消息创建时间 （整型）
	private long    CreateTime;
	
	// 消息类型
	private String  MsgType;
	
	// 事件类型
	private String  Event;
	
	public String getFoUserName() {
		return FoUserName;
	}
	public void setFoUserName(String foUserName) {
		FoUserName = foUserName;
	}
	public String getCromUserName() {
		return CromUserName;
	}
	public void setCromUserName(String cromUserName) {
		CromUserName = cromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}

	

}
