package com.wechat.crowdsend.sendutils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.MeterialCacheUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.core.beans.message.resp.Video;
import com.wechat.core.beans.other.MassMessageResult;
import com.wechat.core.beans.other.WeixinMedia;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.core.utils.CommonUtil;
import com.wechat.picture.model.dto.TMaterialPictureDto;

/**
 * 类功能:群发消息
 * <p>创建者:liyi.wang</p>
 * <p>创建时间:2014-09-24</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class SendMassMessageUtils {

    /**
     * 发送文本消息-根据分组进行群发
     *
     * @param groupId
     * @param content
     * @return
     * @throws Exception
     */
    public static MassMessageResult sendMessageMassSendAll(String groupId, String content) throws Exception {
        String jsonMsg = AdvancedUtil.makeTextMessageMassSendAll(groupId, content);
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()));

        return AdvancedUtil.sendMessageMassSendAll(accessToken, jsonMsg);
    }

    /**
     * 发送图片消息-根据分组进行群发
     *
     * @param groupId
     * @param materialPicture
     * @return
     * @throws Exception
     */
    public static MassMessageResult sendMessageMassSendAll(String groupId, TMaterialPictureDto materialPicture) throws Exception {
        String platformId = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
        String mediaId = MeterialCacheUtil.getMeterialMediaId(CacheKeyUtil.getMeterialKey(materialPicture.getPicture_id()), platformId, CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
        String jsonMsg = AdvancedUtil.makeImageMessageMassSendAll(groupId, mediaId);
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(platformId));

        return AdvancedUtil.sendMessageMassSendAll(accessToken, jsonMsg);
    }


    /**
     * 发送图文消息-根据分组进行群发
     *
     * @param groupId
     * @param articleItems
     * @throws Exception
     */
    public static MassMessageResult sendMessageMassSendAll(String groupId, List<ArticleItemVo> articleItems) throws Exception {
        String platformId = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(platformId));

        List<MassMsgArticle> articleList = new ArrayList<MassMsgArticle>();
        for (ArticleItemVo articleItem : articleItems) {
            MassMsgArticle article = new MassMsgArticle();
            article.setThumb_media_id(MeterialCacheUtil.getMeterialMediaId(CacheKeyUtil.getMeterialKey(articleItem.getPic_thumbnail()), platformId, CodeStringConstant.CS_5012_MATERIAL_TYPE_THUMB));//缩略图文件T_ATTACH_MATERIAL.FILE_ID
            article.setAuthor(articleItem.getAuthor());
            article.setTitle(articleItem.getTitle());
            article.setDigest(articleItem.getSummary());
            article.setShow_cover_pic("0");//不显示封面图片
            //article.setShow_cover_pic("1");//显示封面图片
            if (articleItem.getOrg_flag().equalsIgnoreCase(CodeStringConstant.CS_1000_TRUE)) {
                //使用外部原生链接
                article.setContent_source_url(articleItem.getOrg_url());
            } else {
                //使用自定义内容
            	UtilConfig utilConfig = SpringContextUtil.getApplicationContext().getBean(UtilConfig.class);
            	String content = articleItem.getContent().replaceAll("/?(\\.\\./)+", utilConfig.getAction_url());
                article.setContent(content);
            }

            articleList.add(article);
        }

        //图文素材上传
        WeixinMedia weixinMedia = uploadNews(accessToken, articleList);
        if (weixinMedia.isSuccess()) {
            //发送消息
            String jsonMsg = AdvancedUtil.makeNewsMessageMassSendAll(groupId, weixinMedia.getMediaId());
            return AdvancedUtil.sendMessageMassSendAll(accessToken, jsonMsg);
        } else {
            MassMessageResult messageResult = new MassMessageResult();
            messageResult.setErrcode(weixinMedia.getErrcode());
            messageResult.setErrmsg(weixinMedia.getErrmsg());
            return messageResult;
        }

    }

    /**
     * 发送文本消息-根据OpenID列表群发
     *
     * @param openIds
     * @param content
     * @return
     * @throws Exception
     */
    public static MassMessageResult sendMessageMassSend(String[] openIds, String content) throws Exception {
        String jsonMsg = AdvancedUtil.makeTextMessageMassSend(openIds, content);
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()));

        return AdvancedUtil.sendMessageMassSend(accessToken, jsonMsg);
    }

    /**
     * 发送图片消息-根据OpenID列表群发
     *
     * @param openIds
     * @param materialPicture
     * @return
     * @throws Exception
     */
    public static MassMessageResult sendMessageMassSend(String[] openIds, TMaterialPictureDto materialPicture) throws Exception {
        String platformId = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
        String mediaId = MeterialCacheUtil.getMeterialMediaId(CacheKeyUtil.getMeterialKey(materialPicture.getPicture_id()), platformId, CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
        String jsonMsg = AdvancedUtil.makeImageMessageMassSend(openIds, mediaId);
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(platformId));

        return AdvancedUtil.sendMessageMassSend(accessToken, jsonMsg);
    }




    /**
     * 发送图文消息-根据OpenID列表群发
     *
     * @param openIds
     * @param articleItems
     * @throws Exception
     */
    public static MassMessageResult sendMessageMassSend(String[] openIds, List<ArticleItemVo> articleItems) throws Exception {
        String platformId = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(platformId));

        List<MassMsgArticle> articleList = new ArrayList<MassMsgArticle>();
        for (ArticleItemVo articleItem : articleItems) {
            MassMsgArticle article = new MassMsgArticle();
            article.setThumb_media_id(MeterialCacheUtil.getMeterialMediaId(CacheKeyUtil.getMeterialKey(articleItem.getPic_thumbnail()), platformId, CodeStringConstant.CS_5012_MATERIAL_TYPE_THUMB));//缩略图文件T_ATTACH_MATERIAL.FILE_ID
            article.setAuthor(articleItem.getAuthor());
            article.setTitle(articleItem.getTitle());
            article.setDigest(articleItem.getSummary());
            article.setShow_cover_pic("0");
            if (articleItem.getOrg_flag().equalsIgnoreCase(CodeStringConstant.CS_1000_TRUE)) {
                article.setContent_source_url(articleItem.getOrg_url());
            } else {
                //使用自定义内容
            	UtilConfig utilConfig = SpringContextUtil.getApplicationContext().getBean(UtilConfig.class);
            	String content = articleItem.getContent().replaceAll("/?(\\.\\./)+", utilConfig.getAction_url());
                article.setContent(content);
            }

            articleList.add(article);
        }

        //图文素材上传
        WeixinMedia weixinMedia = uploadNews(accessToken, articleList);
        if (weixinMedia.isSuccess()) {
            //发送消息
            String jsonMsg = AdvancedUtil.makeNewsMessageMassSend(openIds, weixinMedia.getMediaId());
            return AdvancedUtil.sendMessageMassSend(accessToken, jsonMsg);
        } else {
        	MassMessageResult result = new MassMessageResult();
        	result.setErrcode(weixinMedia.getErrcode());
        	result.setErrmsg(weixinMedia.getErrmsg());
        	return result;
        }

    }

    /**
     * 2次上传视频，群发接口用
     *
     * @param accessToken
     * @param video
     * @return WeixinMedia
     * @throws Exception
     */
    private static WeixinMedia uploadVideo(String accessToken, Video video) throws Exception {
        WeixinMedia weixinMedia = new WeixinMedia();

        String requestUrl = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        String jsonMsg = "{\"media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}";
        jsonMsg = String.format(jsonMsg, video.getMediaId(), "", null == video.getDescription()?"":video.getDescription());

        // 发送消息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
        //ADD BY ZHUBAODING 2015/02/04
        if(jsonObject == null){
        	weixinMedia.setSuccess(false);
        	weixinMedia.setErrmsg("SendMassMessageUtils#uploadVideo微信服务器连接失败!");
        	return weixinMedia;
        }
        //ADD END 2015/02/04
        try {
            if(jsonObject.containsKey("errcode")) {
                //上传失败
                weixinMedia.setSuccess(false);
                weixinMedia.setErrcode(jsonObject.getString("errcode"));
                weixinMedia.setErrmsg(jsonObject.getString("errmsg"));
            } else {
                //上传成功
                weixinMedia.setSuccess(true);
                weixinMedia.setType(jsonObject.getString("type"));
                weixinMedia.setMediaId(jsonObject.getString("media_id"));
                weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return weixinMedia;
    }

    /**
     * 上传图文消息素材，群发接口用
     *
     * @param accessToken
     * @param articles
     * @return WeixinMedia
     * @throws Exception
     */
    private static WeixinMedia uploadNews(String accessToken, List<MassMsgArticle> articles) throws Exception {
        WeixinMedia weixinMedia = new WeixinMedia();

        String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        String jsonMsg = "{\"articles\":%s}";
        jsonMsg = String.format(jsonMsg, JSONArray.fromObject(articles).toString().replaceAll("\"", "\\\""));

        // 发送消息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);

        //ADD BY ZHUBAODING 2015/02/04 == NULL判断 BEGIN
        if(jsonObject == null){
        	weixinMedia.setSuccess(false);
        	weixinMedia.setErrmsg("SendMassMessageUtils#uploadNews微信服务器链接异常!");
        	return weixinMedia;
        }
      //ADD  NULL判断 END
        try {
            if(jsonObject.containsKey("errcode")) {
                //上传失败
                weixinMedia.setSuccess(false);
                weixinMedia.setErrcode(jsonObject.getString("errcode"));
                weixinMedia.setErrmsg(jsonObject.getString("errmsg"));
            } else {
                //上传成功
                weixinMedia.setSuccess(true);
                weixinMedia.setType(jsonObject.getString("type"));
                weixinMedia.setMediaId(jsonObject.getString("media_id"));
                weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return weixinMedia;
    }

    public static void main(String[] args) {


        List<MassMsgArticle> articleList = new ArrayList<MassMsgArticle>();
        for (int i=0; i<2;i++) {
            MassMsgArticle article = new MassMsgArticle();
            article.setThumb_media_id("Thumb_media_id" + i);
            article.setAuthor("author" + i);
            article.setTitle("title" + i);
            article.setContent_source_url("contentSourceUrl" + i);
            article.setContent("content" + i);
            article.setDigest("digest" + i);
            article.setShow_cover_pic("showCoverPic" + i);

            articleList.add(article);
        }


        String jsonMsg = "{\"articles\":%s}";
        jsonMsg = String.format(jsonMsg, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));

        System.out.print(jsonMsg);
    }
}
