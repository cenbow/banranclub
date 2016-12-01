package com.wechat.core.handle.logic.msg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.WeChatConstant;
import com.wechat.core.beans.message.resp.TextMessage;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.MessageUtil;


@Scope("prototype")
@Service("voiceMsgHandle")
public class VoiceMsgHandle implements ILogicHandle{

    /***
     * TODO
     */
    public String doHandle(Map<String, String> requestMap) {

        // xml格式的消息数据
        String respXml = null;
        //发送方帐号
        String fromUserName = requestMap.get("FromUserName");
        //开发者微信号
        String toUserName = requestMap.get("ToUserName");
        //语音识别接口
        String recognition = null;
        recognition = requestMap.get("Recognition");
        // 回复文本消息
        // DELETE BY　ZHUBAODING 20151203  == 功能简化处理  START
        recognition = "小财听不明白哦~";
        // DELETE BY　ZHUBAODING 20151203  == 功能简化处理  END
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(WeChatConstant.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setContent(recognition);

        //将消息对象转换成xml
        respXml = MessageUtil.messageToXml(textMessage);

        //保存消息到数据库 TODO

        //反馈消息给用户 TODO

        return respXml;
    }

    /**
     * 保存语音消息
     *
     */
    class SavingVoiceMessage implements Runnable {
        private Map<String, String> requestMap;
        private String platformId;

        public SavingVoiceMessage(Map<String, String> requestMap, String platformId) {
            this.requestMap = requestMap;
            this.platformId = platformId;
        }

        public void run() {
//          //创建和更新用户=SYSTEM
//          TMsgReceivedDto saveInfo = new TMsgReceivedDto();
//          saveInfo.setPlatform_id(platformId);
//          saveInfo.setOpen_id(requestMap.get("FromUserName"));
//          saveInfo.setMessage_type(CodeStringConstant.CS_5062_SEND_TYPE_VOICE);
//          saveInfo.setMedia_id(requestMap.get("MediaId"));
//          //下载到本地的路径
//          String serverPath = requestMap.get("serverWebPath");
//          String savePath = serverPath.replaceAll("\\\\", "/") + "/tecent/download/user/voice";
//          File fileDir = new File(savePath);
//          if(!fileDir.exists()){
//              fileDir.mkdirs();//若不存在则创建目录
//          }
//          HttpURLConnection conn = null;
//          try{
//              //取得图片文件的名称(包括后缀)
//              String requestUrl = DOWNLOAD_MEDIA_URL;
//              String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil
//                      .getAccessTokenKey(platform_id));
//              requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
//                      "MEDIA_ID", media_id);
//              System.out.println(requestUrl);
//              URL url = new URL(requestUrl);
//              conn = (HttpURLConnection) url.openConnection();
//              conn.setDoInput(true);
//              conn.setRequestMethod("GET");
//              if (!savePath.endsWith("/")) {
//                  savePath += "/";
//              }
//              // 根据内容类型获取扩展名
//              String fileExt = CommonUtil.getFileExt(conn
//                      .getHeaderField("Content-Type"));
//              conn.disconnect();
//              //图像文件下载路径
//              String  filePath = savePath + media_id + fileExt;
//              String  serverSavePath = filePath;
//              filePath = filePath.substring(serverPath.length());
//              //图像文件URL访问路径
//              String imageLocalURL = utilConfig.getAction_url() + Html2TxtUtil.filePath2UrlPath(filePath);
//              saveInfo.setFile_id(imageLocalURL);
//              saveInfo.setCreated_user_cd("SYSTEM");
//              saveInfo.setUpdated_user_cd("SYSTEM");
//              tMsgReceivedService.save(saveInfo);
//              //同步图像文件到本地服务器
//              new DownloadImgThread(requestUrl,serverSavePath).start();
//
//          }catch(Exception ie){
//              if( conn != null){
//                  conn.disconnect();
//              }
//              logger.error(ie.getMessage());
//          }


        }

    }

    /**
     * 使用客服消息接口反馈用户参与情况
     *
     *
     */
    class VoiceMessageFeedback implements Runnable {

        public void run() {
            // TODO

        }

    }

}
