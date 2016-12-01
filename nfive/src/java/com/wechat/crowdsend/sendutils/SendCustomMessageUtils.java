package com.wechat.crowdsend.sendutils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.MeterialCacheUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.core.beans.message.resp.Article;
import com.wechat.core.beans.message.resp.Music;
import com.wechat.core.beans.message.resp.Video;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.picture.model.dto.TMaterialPictureDto;


/**
 * 类功能:发送客服消息
 * <p>创建者:liyi.wang</p>
 * <p>创建时间:2014-09-22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class SendCustomMessageUtils {

    /**
     * 发送文本消息
     *
     * @param platformId
     * @param openId
     * @param content
     * @return
     * @throws Exception
     */
    public static void sendCustomMessage(String platformId, String openId, String content) throws Exception {
        if (StringUtils.isNotBlank(platformId) && StringUtils.isNotBlank(openId)) {
            String jsonMsg = AdvancedUtil.makeTextCustomMessage(openId, content);
            String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(platformId));

            AdvancedUtil.sendCustomMessage(accessToken, jsonMsg);
        }
    }

    /**
     * 发送文本消息
     *
     * @param openId
     * @param content
     * @return
     * @throws Exception
     */
    public static void sendCustomMessage(String openId, String content) throws Exception {
        sendCustomMessage(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id(), openId, content);
    }

    /**
     * 发送图片消息
     *
     * @param openId
     * @param materialPicture
     * @return
     * @throws Exception
     */
    public static void sendCustomMessage(String openId, TMaterialPictureDto materialPicture) throws Exception {
        String platformId = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
        String mediaId = MeterialCacheUtil.getMeterialMediaId(CacheKeyUtil.getMeterialKey(materialPicture.getPicture_id()), platformId, CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
        String jsonMsg = AdvancedUtil.makeImageCustomMessage(openId, mediaId);
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(platformId));

        AdvancedUtil.sendCustomMessage(accessToken, jsonMsg);
    }


    /**
     * 发送客服图文消息
     *
     * @param openId
     * @param articleItems
     * @throws Exception
     */
    public static void sendCustomMessage(String openId, List<ArticleItemVo> articleItems) throws Exception {
        UtilConfig utilConfig = SpringContextUtil.getApplicationContext().getBean(UtilConfig.class);
        List<Article> articleList = new ArrayList<Article>();
        for (int i=0; i<articleItems.size(); i++) {
            ArticleItemVo articleItem = articleItems.get(i);
            Article article = new Article();
            article.setTitle(articleItem.getTitle());
            article.setDescription(articleItem.getSummary());
            article.setUrl(articleItem.getTitle());
            article.setPicUrl(articleItem.getTitle());
            //图片URL
            if (i == 0) {
                //封面图
                article.setPicUrl(utilConfig.getDomain_weburl() + Html2TxtUtil.filePath2UrlPath(articleItem.getCover_store_path() + articleItem.getCover_store_name()));
            } else {
                //缩略图
                article.setPicUrl(utilConfig.getDomain_weburl() + Html2TxtUtil.filePath2UrlPath(articleItem.getThumbnail_store_path() + articleItem.getThumbnail_store_name()));
            }

            //设定图文URL
            if (articleItem.getOrg_flag().equalsIgnoreCase(CodeStringConstant.CS_1000_TRUE)) {
                //使用外部原生链接
                article.setUrl(articleItem.getOrg_url());
            } else {
                //使用自定义内容
                String platform_id = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
                article.setUrl(utilConfig.getAction_url() + "showArticleItemContentAction.action?article_item_id=" + articleItem.getArticle_item_id() + "&platform_id=" + platform_id);
            }

            articleList.add(article);
        }

        String jsonMsg = AdvancedUtil.makeNewsCustomMessage(openId, articleList);
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()));
        AdvancedUtil.sendCustomMessage(accessToken, jsonMsg);
    }

}
