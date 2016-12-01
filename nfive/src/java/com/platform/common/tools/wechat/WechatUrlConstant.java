package com.platform.common.tools.wechat;

/**
 * 微信API调用URL静态参数类
 * Created by jinzhihong on 16/11/16.
 */
public class WechatUrlConstant {

    //获取access_token的url
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //获取微信用户信息的url
    public static final String GET_WX_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

    //获取关注用户列表的url
    public static final String GET_WX_SUBSCRIBE_USERS = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    //获取永久素材的URL
    public static final String GET_WX_MATERIAL= "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

    //删除菜单URL
    public static final String MENU_DELETE_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    //创建菜单URL
    public static final String MENU_CREATE_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
}
