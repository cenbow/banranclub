package com.wechat.core.handle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.platform.common.tools.constant.WeChatConstant;
import com.wechat.core.beans.message.resp.Article;
import com.wechat.core.beans.message.resp.NewsMessage;
import com.wechat.core.beans.message.resp.TextMessage;
import com.wechat.core.beans.other.WechatInfo;
import com.wechat.core.utils.MessageUtil;


/**
 * 核心服务类
 */
public class  CoreHandleService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
	

		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
			
			
			// 事件推送
			if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(WeChatConstant.EVENT_TYPE_SUBSCRIBE)) {
					textMessage.setContent("您好，欢迎关注财富农场CRM系统！我们致力于打造精品应用，为德晟的员工供便捷的CRM系统。体验从这里开始！");
					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(WeChatConstant.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				// 自定义菜单点击事件
				else if (eventType.equals(WeChatConstant.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("pm")) {
						Article article = new Article();
						article.setTitle("百度");
						article.setDescription("百度");
						article.setPicUrl("");
						article.setUrl("www.baidu.com");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("test")) {			
						Article article = new Article();
						article.setTitle("用户认证");
						article.setDescription("使用用户认证");
						article.setPicUrl("");
						article.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7f080c7e62df0ae9&redirect_uri=http%3A%2F%2F140.206.75.36%2Fld_crm_ws%2FweChatOAuthAction.action&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					}
				}
			}
			// 当用户发消息时
			else {
				textMessage.setContent("请通过菜单使用网址导航服务！");

		
					// 文本消息
					if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_TEXT)) {
						respContent = "您发送的是文本消息！";
					
					}
					// 图片消息
					else if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_IMAGE)) {
						respContent = "您发送的是图片消息！";
					}
					// 语音消息
					else if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_VOICE)) {
						respContent = "您发送的是语音消息！";
					}
					// 视频消息
					else if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_VIDEO)) {
						respContent = "您发送的是视频消息！";
					}
					// 地理位置消息
					else if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_LOCATION)) {
						respContent = "您发送的是地理位置消息！";
					}
					// 链接消息
					else if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_LINK)) {
						respContent = "您发送的是链接消息！";
					}
					// 事件推送
					else if (msgType.equals(WeChatConstant.REQ_MESSAGE_TYPE_EVENT)) {
						// 事件类型
						String eventType = requestMap.get("Event");
						// 关注
						if (eventType.equals(WeChatConstant.EVENT_TYPE_SUBSCRIBE)) {
							respContent = "谢谢您的关注！";
						}
						// 取消关注
						else if (eventType.equals(WeChatConstant.EVENT_TYPE_UNSUBSCRIBE)) {
							// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
						}
						// 扫描带参数二维码
						else if (eventType.equals(WeChatConstant.EVENT_TYPE_SCAN)) {
							// TODO 处理扫描带参数二维码事件
						}
						// 上报地理位置
						else if (eventType.equals(WeChatConstant.EVENT_TYPE_LOCATION)) {
							// TODO 处理上报地理位置事件
						}
						// 自定义菜单
						else if (eventType.equals(WeChatConstant.EVENT_TYPE_CLICK)) {
							// TODO 处理菜单点击事件
						}
					}
					// 设置文本消息的内容
					textMessage.setContent(respContent);
					// 将文本消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
