package com.wechat.core.beans.message.resp;
import com.platform.common.tools.constant.CodeStringConstant;

/**
 * 文本消息
 */
public class TextMessage extends BaseMessage {
	
	// 回复的消息内容
	private String Content;

	// 消息类型
	private String MsgType = CodeStringConstant.CS_5052_REPLAY_TEXT_MSG;
	
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	
}
