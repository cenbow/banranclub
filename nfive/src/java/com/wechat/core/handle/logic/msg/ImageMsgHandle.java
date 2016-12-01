package com.wechat.core.handle.logic.msg;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.constant.WeChatConstant;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.message.resp.TextMessage;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.CommonUtil;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.core.utils.MessageUtil;

@Scope("prototype")
@Service("imageMsgHandle")
public class ImageMsgHandle implements ILogicHandle{

	private static Logger logger = Logger.getLogger(ImageMsgHandle.class);
//
//	@Autowired
//    private ITMsgReceivedService tMsgReceivedService;

	public static final String DOWNLOAD_USRE_IMG = "/tecent/download/user/img";

	public static final String DOWNLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";// 下载URL

	@Autowired
    private UtilConfig utilConfig;

	/***
	 * TODO
	 */
	public String doHandle(Map<String, String> requestMap) {

		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		//String respContent = "你好！";
		//保存图片消息
		//saveUserImageInfo(requestMap,platform_id);
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
		textMessage.setContent("已收到哦~");

		//将消息对象转换成xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}

//	/**
//     * 保存用户上传图片信息
//     * 2015/03/20  Zhubaoding 功能增加
//     *
//     * @param dto       用户在平台上进行上传的图片
//     * @param content   用户留言
//     * @return
//     */
//    private void saveUserImageInfo(Map<String, String> requestMap,String platform_id){
//    	//创建和更新用户=SYSTEM
//    	TMsgReceivedDto saveInfo = new TMsgReceivedDto();
//    	saveInfo.setPlatform_id(platform_id);
//    	String openId = requestMap.get("FromUserName");
//    	saveInfo.setOpen_id(openId);
//    	saveInfo.setMessage_type(CodeStringConstant.CS_5062_SEND_TYPE_IMAGE);
//    	//图片URL
//    	String pic_url = requestMap.get("PicUrl");
//    	saveInfo.setPic_url(pic_url);
//    	//图片在腾讯端保存的MediaId
//    	String media_id = requestMap.get("MediaId");
//    	saveInfo.setMedia_id(media_id);
//    	//下载到本地的路径
//    	String serverPath = requestMap.get("serverWebPath");
//    	String savePath = serverPath.replaceAll("\\\\", "/") + DOWNLOAD_USRE_IMG.substring(1);
//    	File fileDir = new File(savePath);
//		if(!fileDir.exists()){
//			fileDir.mkdirs();//若不存在则创建目录
//		}
//		HttpURLConnection conn = null;
//		try{
//			//取得图片文件的名称(包括后缀)
//			String requestUrl = DOWNLOAD_MEDIA_URL;
//			String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil
//	                .getAccessTokenKey(platform_id));
//	    	requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
//	                "MEDIA_ID", media_id);
//	    	System.out.println(requestUrl);
//			URL url = new URL(requestUrl);
//	        conn = (HttpURLConnection) url.openConnection();
//	        conn.setDoInput(true);
//	        conn.setRequestMethod("GET");
//	        if (!savePath.endsWith("/")) {
//	            savePath += "/";
//	        }
//	        // 根据内容类型获取扩展名
//	        String fileExt = CommonUtil.getFileExt(conn
//	                .getHeaderField("Content-Type"));
//	        conn.disconnect();
//	        //图像文件下载路径
//	        String  filePath = savePath + media_id + fileExt;
//	        String  serverSavePath = filePath;
//	        filePath = filePath.substring(serverPath.length());
//	        //图像文件URL访问路径
//	    	String imageLocalURL = utilConfig.getAction_url() + Html2TxtUtil.filePath2UrlPath(filePath);
//	    	saveInfo.setFile_id(imageLocalURL);
//	    	saveInfo.setCreated_user_cd("SYSTEM");
//	    	saveInfo.setUpdated_user_cd("SYSTEM");
//	    	tMsgReceivedService.save(saveInfo);
//	    	//同步图像文件到本地服务器
//	    	new DownloadImgThread(requestUrl,serverSavePath).start();
//
//		}catch(Exception ie){
//			if( conn != null){
//				conn.disconnect();
//			}
//			logger.error(ie.getMessage());
//		}
//    	//String imgRelativePath = MediaUtil.getMedia(platform_id, media_id, savePath);
//
//    }

    /**
     * 文件下载
     * @author zhubaoding
     *
     */
    class DownloadImgThread  extends Thread {

    	//图片文件的URL
    	private String requestUrl;
    	//保存本地路径
    	private String savePath;

    	public DownloadImgThread(String requestUrl ,  String savePath){
    		this.requestUrl = requestUrl;
    		this.savePath = savePath;
    	}

    	@Override
    	public void run(){
           try {
        	   	URL url = new URL(requestUrl);
        	   	HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
   	        	conn.setDoInput(true);
   	        	conn.setRequestMethod("GET");

                BufferedInputStream bis = new BufferedInputStream(conn
                        .getInputStream());
                FileOutputStream fos = new FileOutputStream(new File(savePath));
                byte[] buf = new byte[8096];
                int size = 0;
                while ((size = bis.read(buf)) != -1)
                    fos.write(buf, 0, size);
                fos.close();
                bis.close();
                conn.disconnect();
                System.out.println("下载媒体文件成功，filePath=" + savePath);
            } catch (Exception e) {
            	savePath = null;
                e.printStackTrace();
                System.out.println("下载媒体文件失败：{}");
            }
    	}
    }


}
