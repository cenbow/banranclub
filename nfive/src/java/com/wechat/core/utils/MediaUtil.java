package com.wechat.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.wechat.WechatUtil;
import com.wechat.core.busin.BaseBusinessHandel;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hercules.cache.CacheUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.constant.WeChatConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.beans.message.resp.Article;
import com.wechat.core.beans.message.resp.Image;
import com.wechat.core.beans.message.resp.ImageMessage;
import com.wechat.core.beans.message.resp.NewsMessage;
import com.wechat.core.beans.message.resp.TextMessage;
import com.wechat.core.beans.message.resp.TransCustServiceMessage;
import com.wechat.core.beans.message.resp.Video;
import com.wechat.core.beans.message.resp.VideoMessage;
import com.wechat.core.beans.message.resp.Voice;
import com.wechat.core.beans.message.resp.VoiceMessage;
import com.wechat.core.beans.other.WeixinMedia;
import com.wechat.material.model.dto.TMaterialTxrefDto;
import com.wechat.material.model.vo.MaterialPictureVo;
import com.wechat.material.model.vo.MaterialVideoVo;
import com.wechat.material.model.vo.MaterialVo;
import com.wechat.material.model.vo.MaterialVoiceVo;
import com.wechat.material.service.ITAttachMaterialService;
import com.wechat.material.service.ITMaterialTxrefService;

/**
 * @author hercules.chen 用于媒体文件上传下载的工具类
 */
@Service("mediaUtil")
public class MediaUtil {
    private static final Logger logger = Logger.getLogger(MediaUtil.class);
    private static String upload_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";// 上传URL
    private static String download_media_url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";// 下载URL
    private static String domain_weburl = "";
    private static String action_url = "";

    // 多媒体文件上传到腾讯服务器上的保存时间(3天)
    private static final int TX_EXPIRATION_TIME = 3 * 24 * 3600 - 200;

    @Autowired
    private UtilConfig utilConfig;
    @Autowired
    private ITAttachMaterialService tAttachMaterialService;
    @Autowired
    private ITMaterialTxrefService tMaterialTxrefService;
    @Autowired
    private ITArticleGroupService tArticleGroupService;

    /****
     *spring中定义的初始化方法 用于初始化读取配置
     */
    public void InitConfig() {
        action_url = utilConfig.getAction_url();
        domain_weburl = utilConfig.getDomain_weburl();
        upload_media_url = utilConfig.getUpload_media_url();
        download_media_url = utilConfig.getDownload_media_url();
    }

    /**
     * 获取 WeixinMedia
     *
     * @param material_id
     *            素材id
     * @param paltform_id
     *            公众号id
     * @param material_type
     *            素材类型
     * @return WeixinMedia
     * @throws Exception
     */
    private WeixinMedia uploadMaterial(String material_id, String paltform_id,
            String material_type) throws Exception {
        // mediaid
        String mediaId;
        // 过期时间
        Long txExpirationAt;
        // 微信服务器返回上传素材类型
        String txType;
        // 素材上传时间
        Long txCreatedAt;
        // 存储路径
        String storePath;
        // 存储名称
        String storeName;
        // 时效表id
        String txrefId;
        // 文件类型
        String fileType;
        // 是否缓存
        String cacheFlag;
        // 根据素材类型获取参数
        logger.info("响应消息的素材类型为：" + material_type);
        // 图片
        if (material_type.equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG)) {
            MaterialPictureVo materialPictureVo = tAttachMaterialService
                    .queryMaterialPictureVoByPictureIdForUpload(material_id,
                            paltform_id);
            mediaId = materialPictureVo.getTx_media_id();
            txExpirationAt = materialPictureVo.getTx_expiration_at();
            txType = materialPictureVo.getTx_type();
            txCreatedAt = materialPictureVo.getTx_created_at();
            storePath = materialPictureVo.getStore_path();
            storeName = materialPictureVo.getStoret_name();
            txrefId = materialPictureVo.getTxref_id();
            fileType = WeChatConstant.UPLOAD_MEDIA_TYPE_IMAGE;
            cacheFlag = materialPictureVo.getCache_flag();
            // 音频
        } else if (material_type
                .equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_VOICE)) {
            MaterialVoiceVo materialVoicVo = tAttachMaterialService
                    .queryMaterialVoiceByVoiceIdForUpload(material_id,
                            paltform_id);
            mediaId = materialVoicVo.getTx_media_id();
            txExpirationAt = materialVoicVo.getTx_expiration_at();
            txType = materialVoicVo.getTx_type();
            txCreatedAt = materialVoicVo.getTx_created_at();
            storePath = materialVoicVo.getStore_path();
            storeName = materialVoicVo.getStoret_name();
            txrefId = materialVoicVo.getTxref_id();
            fileType = WeChatConstant.UPLOAD_MEDIA_TYPE_VOICE;
            cacheFlag = materialVoicVo.getCache_flag();
            // 视频
        } else if (material_type
                .equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_VIDEO)) {
            MaterialVideoVo materialVideoVo = tAttachMaterialService
                    .queryMaterialVideoVoByVideoIdForUpload(material_id,
                            paltform_id);
            mediaId = materialVideoVo.getTx_media_id();
            txExpirationAt = materialVideoVo.getTx_expiration_at();
            txType = materialVideoVo.getTx_type();
            txCreatedAt = materialVideoVo.getTx_created_at();
            storePath = materialVideoVo.getStore_path();
            storeName = materialVideoVo.getStoret_name();
            txrefId = materialVideoVo.getTxref_id();
            fileType = WeChatConstant.UPLOAD_MEDIA_TYPE_VIDEO;
            cacheFlag = materialVideoVo.getCache_flag();
        } else {
            throw new Exception("素材类型错误");
        }
        // 服务器上传对象
        WeixinMedia weixinMedia;
        long tempTime = System.currentTimeMillis() / 1000;
        // 上传到微信服务器的素材未过期
        if (StringUtils.isNotEmpty(txrefId) && txExpirationAt > tempTime) {
            // 若当在服务器端有缓存则直接返回 weixinMedia 信息
            weixinMedia = new WeixinMedia();
            logger.info("-------------------保存在数据库的素材ID：" + mediaId);
            weixinMedia.setMediaId(mediaId);
            weixinMedia.setType(txType);
            weixinMedia.setCreatedAt(txCreatedAt.intValue());
            weixinMedia.setCache_flag(cacheFlag);
            return weixinMedia;
            // 文件已过期
        } else {
            // 多媒体文件绝对路径
            String filePath = FileUploadUtil.pub_file_path + File.separator
                    + storePath + File.separator + storeName;
            TMaterialTxrefDto tMaterialTxrefDto = new TMaterialTxrefDto();
            // 1.如果时效表上的数据为空，保存数据到时效表
            if (StringUtils.isEmpty(txrefId)) {
                tMaterialTxrefDto.setMaterial_id(material_id);
                tMaterialTxrefDto.setMaterial_type(material_type);
                tMaterialTxrefDto.setPlatform_id(paltform_id);
                tMaterialTxrefDto = tMaterialTxrefService.save(tMaterialTxrefDto);
                txrefId =tMaterialTxrefDto.getTxref_id();
            }
            // 2.重新上载到微信服务器端
            weixinMedia = uploadMedia(paltform_id, filePath, fileType);
            weixinMedia.setCache_flag(cacheFlag);
            // 3.更新时效表
            tMaterialTxrefDto.setTxref_id(txrefId);
            tMaterialTxrefDto.setTx_media_id(weixinMedia.getMediaId());
            tMaterialTxrefDto.setTx_created_at(tempTime);
            tMaterialTxrefDto.setTx_expiration_at(Long.valueOf(tempTime
                    + TX_EXPIRATION_TIME));
            tMaterialTxrefDto.setTx_type(weixinMedia.getType());
            tMaterialTxrefService.updatePK(tMaterialTxrefDto);
        }
        // 3.再次返回上载的关联信息
        return weixinMedia;
    }

    /***
     * 获取Picture WeixinMedia
     *
     * @param picture_id
     * @param platform_id
     * @return WeixinMedia
     * @throws Exception
     */
    public WeixinMedia uploadMaterialPicture(String picture_id,
            String platform_id) throws Exception {
        return uploadMaterial(picture_id, platform_id,
                CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
    }

    /**
     * 获取Video WeixinMedia
     *
     * @param voice_id
     *            音频id
     * @param platform_id
     *            公众号
     * @return WeixinMedia
     * @throws Exception
     */
    public WeixinMedia uploadMaterialVideo(String video_id, String platform_id)
            throws Exception {
        return uploadMaterial(video_id, platform_id,
                CodeStringConstant.CS_5012_MATERIAL_TYPE_VIDEO);
    }

    /**
     * 获取Voice WeixinMedia
     *
     * @param voice_id
     *            音频id
     * @param platform_id
     *            公众号
     * @return WeixinMedia
     * @throws Exception
     */
    public WeixinMedia uploadMaterialVoice(String voice_id, String platform_id)
            throws Exception {
        return uploadMaterial(voice_id, platform_id,
                CodeStringConstant.CS_5012_MATERIAL_TYPE_VOICE);
    }

    /**
     * 获取Thumb(缩略图) WeixinMedia
     *
     * @param thumb_id
     *            缩略图thumb_id
     * @param platform_id
     *            公众号
     * @return WeixinMedia
     * @throws Exception
     */
    public WeixinMedia uploadMaterialThumb(String thumb_id, String platform_id)
            throws Exception {
        MaterialVo materialVo = tAttachMaterialService
                .queryMaterialVoByFileIdForUpload(thumb_id, platform_id);
        // mediaid
        String mediaId;
        // 过期时间
        Long txExpirationAt;
        // 微信服务器返回上传素材类型
        String txType;
        // 素材上传时间
        Long txCreatedAt;
        // 存储路径
        String storePath;
        // 存储名称
        String storeName;
        // 时效表id
        String txrefId;
        // 文件类型
        String fileType;
        // 根据素材类型获取参数
        mediaId = materialVo.getTx_media_id();
        txExpirationAt = materialVo.getTx_expiration_at();
        txType = materialVo.getTx_type();
        txCreatedAt = materialVo.getTx_created_at();
        storePath = materialVo.getStore_path();
        storeName = materialVo.getStoret_name();
        txrefId = materialVo.getTxref_id();
        fileType = WeChatConstant.UPLOAD_MEDIA_TYPE_THUMB;

        // 服务器上传对象
        WeixinMedia weixinMedia;
        // 上传到微信服务器的素材未过期
        long tempTime = System.currentTimeMillis() / 1000;
        if (StringUtils.isNotEmpty(txrefId) && txExpirationAt > tempTime) {
            // 若当在服务器端有缓存则直接返回 weixinMedia 信息
            weixinMedia = new WeixinMedia();
            weixinMedia.setMediaId(mediaId);
            weixinMedia.setType(txType);
            weixinMedia.setCreatedAt(txCreatedAt.intValue());
            return weixinMedia;
            // 文件已过期
        } else {
            // 多媒体文件绝对路径
            String filePath = FileUploadUtil.pub_file_path + File.separator
                    + storePath + File.separator + storeName;
            // 1.重新上载到微信服务器端
            weixinMedia = uploadMedia(platform_id, filePath, fileType);
            // 2.1如果时效表上的数据为空，保存数据到时效表
            if (StringUtils.isEmpty(txrefId)) {
                TMaterialTxrefDto tMaterialTxrefDto = new TMaterialTxrefDto();
                tMaterialTxrefDto.setMaterial_id(thumb_id);
                tMaterialTxrefDto
                        .setMaterial_type(CodeStringConstant.CS_5012_MATERIAL_TYPE_THUMB);
                tMaterialTxrefDto.setTx_media_id(weixinMedia.getMediaId());
                tMaterialTxrefDto.setTx_created_at(tempTime);
                tMaterialTxrefDto.setTx_expiration_at(Long.valueOf(tempTime
                        + TX_EXPIRATION_TIME));
                tMaterialTxrefDto.setTx_type(weixinMedia.getType());
                tMaterialTxrefDto.setPlatform_id(platform_id);
                tMaterialTxrefService.save(tMaterialTxrefDto);
            } else if (StringUtils.isNotEmpty(txrefId)
                    && txExpirationAt < tempTime) {
                // 2.2如果时效表上数据已经过期
                TMaterialTxrefDto tMaterialTxrefDto = new TMaterialTxrefDto();
                tMaterialTxrefDto.setTxref_id(txrefId);
                tMaterialTxrefDto.setTx_media_id(weixinMedia.getMediaId());
                tMaterialTxrefDto.setTx_created_at(tempTime);
                tMaterialTxrefDto.setTx_expiration_at(Long.valueOf(tempTime
                        + TX_EXPIRATION_TIME));
                tMaterialTxrefDto.setTx_type(weixinMedia.getType());
                tMaterialTxrefService.updatePK(tMaterialTxrefDto);
            }
        }
        // 3.再次返回上载的关联信息
        return weixinMedia;
    }

    /**
     * 文件上传到微信服务器
     *
     * @param platform_id
     *            公众号标识
     * @param fileType
     *            媒体文件类型（image、voice、video和thumb）
     * @param filePath
     *            文件路径
     * @return JSONObject
     * @throws Exception
     */
    public WeixinMedia uploadMedia(String platform_id, String filePath,
            String fileType) throws Exception {
        WeixinMedia weixinMedia = null;
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }
        // 拼装请求地址
        String uploadMediaUrl = upload_media_url;
        String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil
                .getAccessTokenKey(platform_id));
        uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken)
                .replace("TYPE", fileType);
        URL urlObj = new URL(uploadMediaUrl);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存
        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                + BOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);
        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        out = null;

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con
                    .getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            // 使用JSON-lib解析返回结果
            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
            logger.info("------------上传多媒体视频返回值：" + jsonObject.toString());
            weixinMedia = new WeixinMedia();
            weixinMedia.setType(jsonObject.getString("type"));
            // type等于thumb时的返回结果和其它类型不一样
            if ("thumb".equals(fileType)) {
                weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
            } else {
                weixinMedia.setMediaId(jsonObject.getString("media_id"));
            }
            weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));

        } catch (IOException e) {
            weixinMedia = null;
            System.out.println("上传媒体文件失败：{}");
            e.printStackTrace();
            throw new IOException("数据读取异常");
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (out != null) {
                out.close();
            }

        }
        return weixinMedia;
    }

    // --响应的多媒体消息的拼装---

    /**
     * 拼装text响应报文
     *
     *            平台公众号ID
     * @param toUserName
     *            接受者openid
     * @param txtMessage
     *            文本消息
     * @return
     * @throws Exception
     */
    public String assembleTextMessage(String toUserName,
            String txtMessage) throws Exception {
        String fromUserName = WechatUtil.getWxPlatform().getPlatform_account();
        TextMessage tm = new TextMessage();
        tm.setFromUserName(fromUserName);
        tm.setToUserName(toUserName);
        tm.setCreateTime(System.currentTimeMillis() / 1000);
        tm.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
        tm.setContent(txtMessage);
        return MessageUtil.messageToXml(tm);
    }

    /**
     * 拼装图片消息
     *
     * @param platform_id
     *            平台公众号ID
     * @param toUserName
     *            接受者openid
     * @param picture_id
     *            图片ID
     * @return
     * @throws Exception
     */
    public String assembleImageMessage(String platform_id, String toUserName,
            String picture_id) throws Exception {
        String fromUserName = WechatInfoUtil.getTPubPlatformDtoByPlatformId(
                platform_id).getOrg_id();
        // 获取服务器返回图片id
        String media_id = uploadMaterialPicture(picture_id, platform_id)
                .getMediaId();
        // 拼装报文
        ImageMessage im = new ImageMessage();
        im.setToUserName(toUserName);
        im.setFromUserName(fromUserName);
        im.setCreateTime(System.currentTimeMillis() / 1000);
        im.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_IMAGE);
        Image i = new Image();
        i.setMediaId(media_id);
        im.setImage(i);
        return MessageUtil.messageToXml(im);
    }

    /**
     * 拼装图文组消息
     *
     * @param platform_id
     *            平台公众号ID
     * @param toUserName
     *            接受者openid
     * @param article_group_id
     *            图文组ID
     * @return
     * @throws Exception
     */
    public String assembleArticleGroupMessage(String platform_id,
            String toUserName, String article_group_id) throws Exception {
        String fromUserName = WechatInfoUtil.getTPubPlatformDtoByPlatformId(
                platform_id).getOrg_id();
        // 获取图文消息集合(已经排序)
        List<ArticleItemVo> list = tArticleGroupService
                .getArticleGroupPublishableItems(article_group_id);
        // 响应图文消息集合
        List<Article> articleList = new ArrayList<Article>();
        for (ArticleItemVo articleItemVo : list) {
            int i = 0;
            Article article = new Article();
            article.setTitle(articleItemVo.getTitle());
            article.setDescription(articleItemVo.getSummary());
            // 必要的图片路径拼装
            if (i == 0) {
                // 封面图
                article.setPicUrl(domain_weburl + Html2TxtUtil.filePath2UrlPath(articleItemVo.getCover_store_path() + articleItemVo.getCover_store_name()));
            } else {
                // 缩略图
                article.setPicUrl(domain_weburl + Html2TxtUtil.filePath2UrlPath(articleItemVo.getThumbnail_store_path()+ articleItemVo.getThumbnail_store_name()));
            }
            // 设定图文URL
            if (articleItemVo.getOrg_flag().equalsIgnoreCase(CodeStringConstant.CS_1000_TRUE)) {
                // 使用外部原生链接
                article.setUrl(articleItemVo.getOrg_url());
            } else {
                // 使用自定义内容
                article.setUrl(action_url + "showArticleItemContentAction.action?article_item_id=" + articleItemVo.getArticle_item_id() + "&platform_id=" + platform_id);
            }
            articleList.add(article);
            i++;
        }
        // 创建图文消息
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(toUserName);
        newsMessage.setFromUserName(fromUserName);
        newsMessage.setCreateTime(System.currentTimeMillis() / 1000);
        newsMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setArticleCount(articleList.size());
        newsMessage.setArticles(articleList);
        return MessageUtil.messageToXml(newsMessage);
    }

    /**
     * 拼装音频组消息
     *
     * @param platform_id
     *            平台公众号ID
     * @param toUserName
     *            接受者openid
     * @param article_group_id
     *            图文组ID
     * @return
     * @throws Exception
     */
    public String assembleVoiceMessage(String platform_id, String toUserName,
            String voice_id) throws Exception {
        String fromUserName = WechatInfoUtil.getTPubPlatformDtoByPlatformId(
                platform_id).getOrg_id();
        String media_id = uploadMaterialVoice(voice_id, platform_id)
                .getMediaId();

        // 组装音频消息
        VoiceMessage voiceMessage = new VoiceMessage();
        voiceMessage.setFromUserName(fromUserName);
        voiceMessage.setToUserName(toUserName);
        voiceMessage.setCreateTime(System.currentTimeMillis() / 1000);
        voiceMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_VOICE);
        Voice voice = new Voice();
        voice.setMediaId(media_id);
        voiceMessage.setVoice(voice);
        return MessageUtil.messageToXml(voiceMessage);
    }

    /**
     * 拼装 客服转发服务 响应报文
     *
     * @param platform_id
     *            平台公众号ID
     * @param toUserName
     *            接受者openid
     * @return
     * @throws Exception
     */
    public String assembleTransCustServiceMessage(String platform_id,
            String toUserName) throws Exception {
        String fromUserName = WechatInfoUtil.getTPubPlatformDtoByPlatformId(
                platform_id).getOrg_id();
        TransCustServiceMessage tcsm = new TransCustServiceMessage();
        tcsm.setFromUserName(fromUserName);
        tcsm.setToUserName(toUserName);
        tcsm.setCreateTime(System.currentTimeMillis() / 1000);
        tcsm.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_TRANS_CUST_SERVICE);

        return MessageUtil.messageToXml(tcsm);
    }

    /**
     * 执行自定义消息回复
     * @param platform_id 平台id
     * @param toUserName  发送人
     * @param actionClass 自定义消息业务执行类
     * @return
     */
    public String assembleActionMessage(String platform_id,String toUserName,String actionClass){
        try {
            Class onwClass = Class.forName(actionClass);
            BaseBusinessHandel baseBusinessHandel =(BaseBusinessHandel) SpringContextUtil
                    .getApplicationContext().getBean(onwClass.newInstance().getClass());
            baseBusinessHandel.setPlatform_id(platform_id);
            baseBusinessHandel.setToUserName(toUserName);
            return baseBusinessHandel.business();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载媒体文件
     *
     * @param accessToken
     *            接口访问凭证
     * @param mediaId
     *            媒体文件标识
     * @param savePath
     *            文件在服务器上的存储路径
     * @return
     */
    public static String getMedia(String platform_id, String mediaId,  String savePath) {
        String filePath = null;
        // 拼接请求地址
        String requestUrl = download_media_url;
        try {
        	String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil
                    .getAccessTokenKey(platform_id));
        	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
                    "MEDIA_ID", mediaId);
        	System.out.println(requestUrl);

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 根据内容类型获取扩展名
            String fileExt = CommonUtil.getFileExt(conn
                    .getHeaderField("Content-Type"));
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;

            BufferedInputStream bis = new BufferedInputStream(conn
                    .getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();
            conn.disconnect();
            System.out.println("下载媒体文件成功，filePath=" + filePath);
        } catch (Exception e) {
            filePath = null;
            e.printStackTrace();
            System.out.println("下载媒体文件失败：{}");
        }
        return filePath;
    }

    /**
     * 删除缓存和[媒体素材服务器资源关联]T_TXREF_MATERIA表中对于的数据
     *
     * @素材文件修改后，需要调用改接口
     *
     * @param material_id
     */
    public void clearCacheAndDbData(String material_id) {
        /** 移除缓存 */
        String cache_material_id = CacheKeyUtil.getMeterialKey(StringUtils
                .trimToEmpty(material_id));
        try {
            CacheUtil.remove(cache_material_id);
        } catch (Exception e) {
            logger
                    .error("------material_id:" + material_id + "移除缓存异常------",
                            e);
        }
        /** 删除时效表数据 */
        TMaterialTxrefDto temp = new TMaterialTxrefDto();
        temp.setMaterial_id(material_id);
        try {
            tMaterialTxrefService.delete(temp);
        } catch (Exception e) {
            logger.error(
                    "------material_id:" + material_id + "删除时效表数据异常------", e);
        }
    }

}
