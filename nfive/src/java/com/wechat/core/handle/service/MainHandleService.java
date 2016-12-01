package com.wechat.core.handle.service;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.WeChatConstant;
import com.wechat.core.beans.message.req.BaseMessage;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.MessageUtil;

/***
 * 微信平台总控制处理类
 * 具体的实现逻辑回调用com.wechat.core.handle.logic中的相关处理类
 * @author hercuels.chen
 */
@Scope("prototype")
@Service("mainHandleService")
public class MainHandleService {
    private static Logger logger = Logger.getLogger(MainHandleService.class);
    //1 关注/取消关注事件
    @Autowired
    ILogicHandle subscribeEventHandle;
    @Autowired
    ILogicHandle unSubscribeEventHandle;

    //2   扫描带参数二维码事件  暂不做处理
    //3 上报地理位置事件  暂不做处理
    //4 自定义菜单事件
    @Autowired
    ILogicHandle menuEventHandle;

    //MSG
    @Autowired
    ILogicHandle imageMsgHandle;
    @Autowired
    ILogicHandle linkMsgHandle;
    @Autowired
    ILogicHandle locationMsgHandle;
    @Autowired
    ILogicHandle textMsgHandle;
    @Autowired
    ILogicHandle videoMsgHandle;
    @Autowired
    ILogicHandle voiceMsgHandle;

    //5 群发消息结束回调事件
    @Autowired
    ILogicHandle massendEventHandle;

    /**
     *总控处理类处理微信发来的请求
     *
     * @param request
     *@return xml
     */
    public  String  processRequest(HttpServletRequest request) {

        // xml格式的消息数据
        String respXml = null;

        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            BaseMessage reqBaseMessage = new BaseMessage();
            reqBaseMessage.setFromUserName(requestMap.get("FromUserName")); // 发送方帐号
            reqBaseMessage.setToUserName(requestMap.get("FromUserName")); // 开发者微信号

            if(requestMap.get("MsgId")!=null) {
                reqBaseMessage.setMsgId(Long.parseLong(requestMap.get("MsgId"))); // 消息ID
            }
            reqBaseMessage.setMsgType(requestMap.get("MsgType")); // 消息类型
            reqBaseMessage.setCreateTime(Long.parseLong(requestMap.get("CreateTime")));// 创建时间

            // 事件处理 MsgType = event
            if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 1 关注/取消关注事件处理
                // 1.1关注公众号
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_SUBSCRIBE)) {
                    respXml=subscribeEventHandle.doHandle(requestMap);
                }
                // 1.2取消订阅
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_UNSUBSCRIBE)) {
                    respXml=unSubscribeEventHandle.doHandle(requestMap);
                }

                // 2 扫描带参数二维码事件
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_SCAN)) {
                    //暂不做处理
                }
                // 3 上报地理位置事件
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_LOCATION)) {
                    //暂不做处理
                }

                // 4 自定义菜单事件
                //4.1点击菜单拉取消息时的事件推送
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_CLICK)) {
                    //默认菜单跳转都是CLICK 方式
                    //4.1.1 菜单处理逻辑
                    respXml=menuEventHandle.doHandle(requestMap);
                }

                //4.2点击菜单跳转链接时的事件推送
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_VIEW)) {
                    //暂不做处理
                }

                // 5推送群发结果事件
                if (eventType.equalsIgnoreCase(WeChatConstant.EVENT_TYPE_MASSSENDJOBFINISH)) {
                    respXml = massendEventHandle.doHandle(requestMap);
                }
            } else {// 接收用户消息处理
                // 1.文本消息
                if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_TEXT)) {
                    respXml=textMsgHandle.doHandle(requestMap);
                }// 2.图片消息
                else if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_IMAGE)) {
                    String serverPath = request.getSession().getServletContext().getRealPath("/");
                    requestMap.put("serverWebPath", serverPath);
                    respXml=imageMsgHandle.doHandle(requestMap);
                }
                // 3.语音消息
                else if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_VOICE)) {
                    respXml=voiceMsgHandle.doHandle(requestMap);
                }
                // 4.视频消息
                else if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_VIDEO)) {
                    respXml=videoMsgHandle.doHandle(requestMap);
                }
                // 5.地理位置消息
                else if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_LOCATION)) {
                    respXml=locationMsgHandle.doHandle(requestMap);
                }
                // 6.链接消息
                else if (reqBaseMessage.getMsgType().equals(WeChatConstant.REQ_MESSAGE_TYPE_LINK)) {
                    respXml=linkMsgHandle.doHandle(requestMap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return respXml;
    }
}
