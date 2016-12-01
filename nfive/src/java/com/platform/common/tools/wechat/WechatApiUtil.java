package com.platform.common.tools.wechat;

import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.core.beans.menu.Menu;
import com.wechat.core.beans.other.WeixinUserInfo;
import com.wechat.core.beans.other.WeixinUserList;
import com.wechat.core.utils.CommonUtil;
import com.wechat.core.utils.MyX509TrustManager;
import com.wechat.material.model.TWxNewsDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 调用微信API工具类
 * Created by jinzhihong on 16/11/16.
 */
public class WechatApiUtil {


    // 日志
    private static Logger logger = Logger.getLogger(WechatApiUtil.class);

    /**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        try {
            System.setProperty("jsse.enableSNIExtension", "false");

            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new MyX509TrustManager() };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            //信任所有域名
            conn.setHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            logger.error("连接超时",ce);
        } catch (Exception e) {
            logger.error("https请求异常",e);
        }
        return jsonObject;
    }

    /**
     * 获取接口访问凭证
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static String getAccessToken(String appid, String appsecret) {
        String access_token = null;
        String requestUrl = WechatUrlConstant.GET_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        // 发起GET请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                access_token = jsonObject.getString("access_token");
            } catch (JSONException e) {
                // 获取token失败
                logger.error("获取token失败 errcode:{"+jsonObject.getInt("errcode") + "} errmsg:{" + jsonObject.getString("errmsg") + "}");
            }
        }
        return access_token;
    }

    /**
     * 获取微信用户信息
     *
     * @param openId
     *            用户标识
     * @return WeixinUserInfo
     * @throws Exception
     */
    public static WeixinUserInfo getFansInfo(String openId)
            throws Exception {
        WeixinUserInfo weixinUserInfo = null;
        // 拼接请求地址
        String requestUrl =  WechatUrlConstant.GET_WX_USERINFO_URL.replace("ACCESS_TOKEN", WechatUtil.getWxPlatform().getAccess_token()).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                logger.error("获取微信用户信息失败",e);
            }
        }
        return weixinUserInfo;
    }

    /**
     * 获取关注粉丝列表
     * @param nextOpenId
     * @return
     * @throws Exception
     */
    public static WeixinUserList getSubscribeFans(String nextOpenId) throws Exception {
        WeixinUserList weixinUserList = null;
        if (null == nextOpenId)
            nextOpenId = "";
        // 拼接请求地址
        String requestUrl = WechatUrlConstant.GET_WX_SUBSCRIBE_USERS.replace("ACCESS_TOKEN", WechatUtil.getWxPlatform().getAccess_token()).replace("NEXT_OPENID", nextOpenId);
        // 获取关注者列表
        JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                weixinUserList = new WeixinUserList();
                weixinUserList.setTotal(jsonObject.getInt("total"));
                weixinUserList.setCount(jsonObject.getInt("count"));
                weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
                JSONObject dataObject = (JSONObject) jsonObject.get("data");
                if (null != dataObject) {
                    // count=0时，结果json中没有data字段
                    weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
                }
            } catch (JSONException e) {
                weixinUserList = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取关注者列表失败 errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
            }
        }
        return weixinUserList;
    }


    /**
     * 获取图文素材列表
     * 图文素材不会很多，一次性获取
     */
    public static List<TWxNewsDto> getMeterialNews(){

        List<TWxNewsDto> wxNewsDtos = new ArrayList<TWxNewsDto>();
        // 拼接请求地址
        String requestUrl = WechatUrlConstant.GET_WX_MATERIAL.replace("ACCESS_TOKEN", WechatUtil.getWxPlatform().getAccess_token());
        int offset = 0; //偏移位
        int count = 20; //每次获取的图文素材数量
        boolean hasNews = true; //是否还有素材的标志位
        while(hasNews){
            //组装参数
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("type","news");
            jsonParam.put("offset",offset); //偏移位
            jsonParam.put("count",count); //默认获取20个
            //获取图文素材
            JSONObject jsonObject = httpsRequest(requestUrl, "POST", jsonParam.toString());
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    offset = offset + count;
                    int total_count = jsonObject.getInt("total_count"); //总数
                    int item_count = jsonObject.getInt("item_count"); //本次获取数量
                    //如果获取的数量等于总数，或者小于每次获取数量，则置表示为hasNews = false 没有更多图文了
                    if(item_count<count||item_count==total_count){
                        hasNews = false;
                    }
                    //微信图文组列表
                    JSONArray item = jsonObject.getJSONArray("item");
                    //循环处理单个微信图文
                    for(int i = 0 ; i<item.size();i++){
                        JSONObject wxNew = item.getJSONObject(i);
                        TWxNewsDto wxNewsDto = new TWxNewsDto();
                        wxNewsDto.setMedia_id(wxNew.getString("media_id"));//素材ID
                        wxNewsDto.setUpdate_time(new Date(wxNew.getLong("update_time")*1000));

                        JSONArray news_item = wxNew.getJSONObject("content").getJSONArray("news_item");
                        wxNewsDto.setChild_news(new BigDecimal(news_item.size()-1)); //子素材数量
                        JSONObject main_new = news_item.getJSONObject(0); //取出主图文消息进行赋值
                        wxNewsDto.setTitle(main_new.getString("title"));//标题
                        wxNewsDto.setAuthor(main_new.getString("author")); //作者
                        wxNewsDto.setDigest(main_new.getString("digest"));//摘要
                        wxNewsDto.setContent(null); //内容
                        wxNewsDto.setContent_source_url(main_new.getString("content_source_url")); //原文地址
                        wxNewsDto.setThumb_media_id(main_new.getString("thumb_media_id")); //封面图片ID
                        wxNewsDto.setThumb_url(main_new.getString("thumb_url")); //封面图片URL
                        wxNewsDto.setShow_cover_pic(main_new.getInt("show_cover_pic")==0? CodeStringConstant.CS_1000_FALSE:CodeStringConstant.CS_1000_TRUE);//是否显示为封面
                        wxNewsDto.setUrl(main_new.getString("url"));//图文链接URL
                        wxNewsDtos.add(wxNewsDto);
                    }
                }catch (JSONException e) {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    logger.error("获取图文素材失败 errcode:{" + errorCode + "} errmsg:{" + errorMsg + "}");
                }
            }
        }
        return wxNewsDtos;
    }

    /**
     * 创建菜单
     *
     * @param menu
     *            菜单实例
     * @param accessToken
     *            凭证
     * @return true成功 false失败
     */
    public static boolean createMenu(Menu menu, String accessToken) {
        boolean result = false;
        String url = WechatUrlConstant.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 发起POST请求创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
        logger.info(jsonMenu);
        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
                logger.error("创建菜单失败 errcode:{" + errorCode + "} errmsg:{"
                        + errorMsg + "}");
            }
        }
        return result;
    }

    /**
     * 删除菜单
     *
     * @param accessToken
     *            凭证
     * @return true成功 false失败
     */
    public static boolean deleteMenu(String accessToken) {
        boolean result = false;
        String requestUrl = WechatUrlConstant.MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        // 发起GET请求删除菜单
        JSONObject jsonObject = CommonUtil
                .httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
                logger.error("删除菜单失败 errcode:{" + errorCode + "} errmsg:{"
                        + errorMsg + "}");
            }
        }
        return result;
    }
}
