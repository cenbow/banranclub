package com.platform.common.tools.constant;
/**
 * 微信常量类
 * @author hercules.chen
 */
public class WeChatConstant {

    //----------请求消息类型------------------
    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    //----------请求事件类型------------------
    //subscribe
    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    //scan
    // 事件类型：scan(用户已关注时的扫描带参数二维码)
    public static final String EVENT_TYPE_SCAN = "scan";

    //mass
    // 事件类型：masssendjobfinish(推送群发结果)
    public static final String EVENT_TYPE_MASSSENDJOBFINISH = "masssendjobfinish";
    //成功
    public static final String SEND_STATUS_SUCCESS = "sendsuccess";
    //失败
    public static final String SEND_STATUS_FAIL = "sendfail";



    //location
    // 事件类型：LOCATION(上报地理位置)
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    // menu
    // 事件类型：CLICK(自定义菜单)
    public static final String EVENT_TYPE_CLICK = "click";
    // 事件类型：VIEW(自定义菜单)
    public static final String EVENT_TYPE_VIEW = "view";
    // 事件类型：media_id 素材
    public static final String EVENT_TYPE_MEDIA_id = "media_id";
    // 事件类型：view_limited 图文素材
    public static final String EVENT_TYPE_VIEW_LIMITED = "media_id";

    //----------响应消息类型------------------
    // 响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    // 响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    // 响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    // 响应消息类型：转发客服服务
    public static final String RESP_MESSAGE_TYPE_TRANS_CUST_SERVICE = "transfer_customer_service";

    //----------------上传素材类型-----------------
    /**上传素材类型 图片**/
    public static final String UPLOAD_MEDIA_TYPE_IMAGE = "image";
    /**上传素材类型 音频**/
    public static final String UPLOAD_MEDIA_TYPE_VOICE = "voice";
    /**上传素材类型 视频**/
    public static final String UPLOAD_MEDIA_TYPE_VIDEO = "video";
    /**上传素材类型 缩略图**/
    public static final String UPLOAD_MEDIA_TYPE_THUMB = "thumb";

}
