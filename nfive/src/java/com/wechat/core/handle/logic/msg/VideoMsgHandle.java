package com.wechat.core.handle.logic.msg;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.WeChatConstant;
import com.wechat.core.beans.message.resp.TextMessage;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.MessageUtil;


@Scope("prototype")
@Service("videoMsgHandle")
public class VideoMsgHandle  implements ILogicHandle{

	/***
	 * TODO
	 */
	public String doHandle(Map<String, String> requestMap) {

		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "你好！";

		//发送方帐号
		String fromUserName = requestMap.get("FromUserName");
		//开发者微信号
		String toUserName = requestMap.get("ToUserName");

		// 回复文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("TODO VideoMsgHandle");

		//将消息对象转换成xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;


	}

}
