package com.wechat.core.handle.logic.msg;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.common.tools.wechat.WechatUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.MediaUtil;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplyKeywordService;
import com.wechat.replycfg.service.ITReplySpecialService;
import com.wechat.useraction.ActivityTraceUtils;
import com.wechat.useraction.model.dto.TUserActionDto;

@Scope("prototype")
@Service("textMsgHandle")
public class TextMsgHandle implements ILogicHandle{
    protected static final Log log =  LogFactory.getLog(TextMsgHandle.class);
    @Autowired
    private ITReplyKeywordService tReplyKeywordService;
    @Autowired
    private ITReplySpecialService tReplySpecialService;
    @Autowired
    private MediaUtil mediaUtil;    //素材管理工具类
//    @Autowired
//    private ITMsgReceivedService tMsgReceivedService;

    /***
     * 文本消息处理
     */
    public String doHandle(Map<String, String> requestMap){

            String content =requestMap.get("Content").trim().toUpperCase();//内容 转化为大写与数据库类型匹配
            // 接收方帐号（收到的OpenID） (需调换位置)
            String toUserName = requestMap.get("FromUserName");

            String platform_id = WechatUtil.getWxPlatform().getPlatform_id();

            try {
                   //4.用户行为跟踪
                TUserActionDto userAction = new TUserActionDto();
                //set userAction顺序为PLATFORM_ID,ACTION_TYPE,OPEN_ID,ACTION_TIME,MENU_ID,URL,KEYWORD
                userAction.setPlatform_id(platform_id);
                userAction.setAction_type(CodeStringConstant.CS_5064_USERACTION_MENU);
                userAction.setOpen_id(toUserName);
                userAction.setAction_time(new Date());
                userAction.setKeyword(content);
                ActivityTraceUtils.trace(userAction);

                //保存用户内容
                saveUserTextInfo(userAction,requestMap.get("Content").trim());

                //1.处理匹配到精确匹配关键字的情况
                TReplyKeywordQueryBean paramTReplyKeywordDtoBean = new TReplyKeywordQueryBean();
                //设置查询条件
                paramTReplyKeywordDtoBean.setKeyword(content);//关键字
                paramTReplyKeywordDtoBean.setPlatform_id(platform_id);//公众号ID
                paramTReplyKeywordDtoBean.setMatch_type(CodeStringConstant.CS_5053_MATCH_TYPE_ALL);//精确匹配模式
                paramTReplyKeywordDtoBean.setEffect_flag(CodeStringConstant.CS_1000_TRUE);//是否启用
                paramTReplyKeywordDtoBean.setPub_startdate(new Date());//开始时间
                paramTReplyKeywordDtoBean.setPub_enddate(new Date());//结束时间

                List<TReplyKeywordDto> exactly_tReplyKeywordDtoList = tReplyKeywordService.queryReplyKeywordDtosByAccurateMode(paramTReplyKeywordDtoBean);//获取精确匹配列表
                if (null != exactly_tReplyKeywordDtoList && exactly_tReplyKeywordDtoList.size() > 0){
                        return this.replyKeyWordHandle(toUserName,exactly_tReplyKeywordDtoList.get(0));//容错处理(有可能查询多条数据，以第一条为准,只回复一条信息)
                }

                //2.处理匹配到模糊匹配关键字的情况
                paramTReplyKeywordDtoBean.setMatch_type(CodeStringConstant.CS_5053_MATCH_TYPE_LIKE);//重新设置查询条件（模糊匹配模式）
                List<TReplyKeywordDto> blurry_tReplyKeywordDtoList = tReplyKeywordService.queryReplyKeywordDtosByLikeMode(paramTReplyKeywordDtoBean);//获取模糊匹配列表
                if (null != blurry_tReplyKeywordDtoList && blurry_tReplyKeywordDtoList.size() > 0){
                        return this.replyKeyWordHandle(toUserName, blurry_tReplyKeywordDtoList.get(0));//容错处理(有可能查询多条数据，以第一条为准,只回复一条信息)
                }

                //3.处理未匹配到任何关键字的情况
                TReplySpecialQueryBean paramTReplySpecialQueryBean = new TReplySpecialQueryBean();
                //设置查询条件特殊回复
                paramTReplySpecialQueryBean.setRule_type(CodeStringConstant.CS_5054_REPLY_RULE_NOT_KEYWORD);//关键字回复无匹配模式
                paramTReplySpecialQueryBean.setEffect_flag(CodeStringConstant.CS_1000_TRUE);//是否启用
                paramTReplySpecialQueryBean.setPub_startdate(new Date());//开始时间
                paramTReplySpecialQueryBean.setPub_enddate(new Date());//结束时间
                paramTReplySpecialQueryBean.setPlatform_id(platform_id);//公众号ID

                //容错设置回复匹配的第一条
                List<TReplySpecialDto> tReplySpecialDtoList =  tReplySpecialService.queryReplySpecialList(paramTReplySpecialQueryBean);
                if (null != tReplySpecialDtoList && tReplySpecialDtoList.size() > 0){
                    return this.specialKeyWordHandle(toUserName, tReplySpecialDtoList.get(0));//容错处理(有可能查询多条数据，以第一条为准,只回复一条信息)
                }

            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
            return null;
        }

    /**
     * 关键字回复处理
     * @param  toUserName     普通用户（公众帐号 -> 普通用户）
     * @param  tReplyKeywordDto
     * @return String(xml)
     * @throws Exception
     */
    private String replyKeyWordHandle(String toUserName,TReplyKeywordDto tReplyKeywordDto) throws Exception{

        //客服模式
        if (CodeStringConstant.CS_1000_TRUE.equals(tReplyKeywordDto.getCust_srv_flag())) {
            return mediaUtil.assembleTransCustServiceMessage(tReplyKeywordDto.getPlatform_id(), toUserName);
        }

        //关键字回复类型的图文消息  CS_5052_REPLAY_ARTICLE_MSG  = "505200000001";
        if(CodeStringConstant.CS_5052_REPLAY_ARTICLE_MSG.equalsIgnoreCase(tReplyKeywordDto.getReply_type())){
            return mediaUtil.assembleArticleGroupMessage(tReplyKeywordDto.getPlatform_id(), toUserName, tReplyKeywordDto.getMaterial_id());
        }

        //关键字回复类型的文本消息  CS_5052_REPLAY_TEXT_MSG    = "505200000002";
        if(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG.equalsIgnoreCase(tReplyKeywordDto.getReply_type()))
        {
            return mediaUtil.assembleTextMessage(toUserName, tReplyKeywordDto.getText_msg());
        }


        //关键字回复类型的图片消息  CS_5052_REPLAY_PICTURE_MSG = "505200000003";
        if(CodeStringConstant.CS_5052_REPLAY_PICTURE_MSG.equalsIgnoreCase(tReplyKeywordDto.getReply_type())){
            return mediaUtil.assembleImageMessage(tReplyKeywordDto.getPlatform_id(), toUserName, tReplyKeywordDto.getMaterial_id());
        }


        //关键字回复类型的音频消息   CS_5052_REPLAY_VOICE_MSG = "505200000004";
        if(CodeStringConstant.CS_5052_REPLAY_VOICE_MSG.equalsIgnoreCase(tReplyKeywordDto.getReply_type())){
            return mediaUtil.assembleVoiceMessage(tReplyKeywordDto.getPlatform_id(), toUserName, tReplyKeywordDto.getMaterial_id());

        }

        return null;
    }

    /**
     * 特殊关键字回复处理
     * @param  toUserName             普通用户（公众帐号 -> 普通用户）
     * @param tReplySpecialDtoList   特殊关键字回复List
     * @return
     * @throws Exception
     */
    private String specialKeyWordHandle(String toUserName,TReplySpecialDto tReplySpecialDto)  throws Exception{

        //客服模式
        if (CodeStringConstant.CS_1000_TRUE.equals(tReplySpecialDto.getCust_srv_flag())) {
            return mediaUtil.assembleTransCustServiceMessage(tReplySpecialDto.getPlatform_id(), toUserName);
        }

        //关键字回复类型的图文消息  CS_5052_REPLAY_ARTICLE_MSG  = "505200000001";
        if(CodeStringConstant.CS_5052_REPLAY_ARTICLE_MSG.equalsIgnoreCase(tReplySpecialDto.getReply_type())){
            return mediaUtil.assembleArticleGroupMessage(tReplySpecialDto.getPlatform_id(), toUserName, tReplySpecialDto.getMaterial_id());
        }

        //关键字回复类型的文本消息  CS_5052_REPLAY_TEXT_MSG    = "505200000002";
        if(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG.equalsIgnoreCase(tReplySpecialDto.getReply_type())){
            return mediaUtil.assembleTextMessage(toUserName, tReplySpecialDto.getText_msg());
        }


        //关键字回复类型的图片消息  CS_5052_REPLAY_PICTURE_MSG = "505200000003";
        if(CodeStringConstant.CS_5052_REPLAY_PICTURE_MSG.equalsIgnoreCase(tReplySpecialDto.getReply_type())){
            return mediaUtil.assembleImageMessage(tReplySpecialDto.getPlatform_id(), toUserName, tReplySpecialDto.getMaterial_id());
        }


        //关键字回复类型的音频消息   CS_5052_REPLAY_VOICE_MSG = "505200000004";
        if(CodeStringConstant.CS_5052_REPLAY_VOICE_MSG.equalsIgnoreCase(tReplySpecialDto.getReply_type())){
            return mediaUtil.assembleVoiceMessage(tReplySpecialDto.getPlatform_id(), toUserName, tReplySpecialDto.getMaterial_id());

        }
        return null;
    }

    /**
     * 保存用户留言信息
     * 2015/03/20  Zhubaoding 功能增加
     *
     * @param dto       用户交互信息
     * @param content   用户留言
     * @return
     */
    private void saveUserTextInfo(TUserActionDto dto, String content){
//    	//创建和更新用户=SYSTEM
//    	TMsgReceivedDto saveInfo = new TMsgReceivedDto();
//    	saveInfo.setPlatform_id(dto.getPlatform_id());
//    	saveInfo.setOpen_id(dto.getOpen_id());
//    	saveInfo.setMessage_type(CodeStringConstant.CS_5062_SEND_TYPE_TEXT);
//    	saveInfo.setMessage_content(content);
//    	saveInfo.setCreated_user_cd("SYSTEM");
//    	saveInfo.setUpdated_user_cd("SYSTEM");
//    	tMsgReceivedService.save(saveInfo);
    }



}
