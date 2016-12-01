package com.wechat.core.handle.logic.event.menu;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import com.platform.common.tools.wechat.WechatUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.MediaUtil;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.useraction.ActivityTraceUtils;
import com.wechat.useraction.model.dto.TUserActionDto;

@Scope("prototype")
@Service("menuEventHandle")
public class MenuEventHandle implements ILogicHandle {
    private Logger logger = Logger.getLogger(MenuEventHandle.class);

    @Autowired
    private ITWcmenuService tWcmenuService;

    // 素材管理工具类
    @Autowired
    private MediaUtil mediaUtil;

    /*
     * 处理自定义菜单发送消息 (non-Javadoc)
     *
     * @see com.wechat.core.handle.logic.ILogicHandle#doHandle(java.util.Map,
     * java.lang.String)
     */
    public String doHandle(Map<String, String> requestMap) {
        // xml格式的消息数据
        String respXml = null;
        // 接收方帐号（收到的OpenID） (需调换位置)
        String toUserName = requestMap.get("FromUserName");

        String platform_id = WechatUtil.getWxPlatform().getPlatform_id();

        // 事件KEY值，与创建菜单时的key值对应
        String eventKey = StringUtils.trimToEmpty(requestMap.get("EventKey"));
        try {

            // 获取key对应的菜单记录
            TWcmenuDto temp = new TWcmenuDto();
            temp.setPlatform_id(platform_id);
            temp.setMenu_id(eventKey);
            TWcmenuDto tWdto = tWcmenuService.getRow(temp);
            /**
             * 根据key值判断用户点击的按钮
             */
            // 该事件不存在
            if (null == tWdto) {
                respXml = "";

            } else {
                // 客服模式（不建议在菜单中响应客服模式）
                if (CodeStringConstant.CS_1000_TRUE.equals(tWdto.getCust_srv_flag())) {
                    respXml = mediaUtil.assembleTransCustServiceMessage(platform_id, toUserName);

                } else {
                    // 文本
                    if (CodeStringConstant.MENU_TYPE_TEXT.equals(tWdto.getAction_type())) {
                        respXml = mediaUtil.assembleTextMessage(toUserName, URLDecoder.decode(tWdto.getText_msg(), "UTF-8"));
                        // 图片
                    } else if (CodeStringConstant.MENU_TYPE_IMAGE.equals(tWdto.getAction_type())) {
                        respXml = mediaUtil.assembleImageMessage(tWdto.getPlatform_id(), toUserName, tWdto.getMaterial_id());

                        // 图文
                    } else if (CodeStringConstant.MENU_TYPE_IMAGE_TEXT.equals(tWdto.getAction_type())) {
                        respXml = mediaUtil.assembleArticleGroupMessage(tWdto.getPlatform_id(), toUserName, tWdto.getMaterial_id());

                        // 音频
                    } else if (CodeStringConstant.MENU_TYPE_AUDIO.equals(tWdto.getAction_type())) {
                        respXml = mediaUtil.assembleVoiceMessage(tWdto.getPlatform_id(), toUserName, tWdto.getMaterial_id());

                    }
                        //业务执行类
                    else if (CodeStringConstant.MENU_TYPE_ACTION.equals(tWdto.getAction_type())) {
                        respXml = mediaUtil.assembleActionMessage(tWdto.getPlatform_id(), toUserName, tWdto.getAction_class());
                    }
                }
            }

//            logger.info("------menu response------\r\n" + respXml);

        } catch (Exception e) {
            logger.error("------响应自定义菜单事件异常------\r\n", e);
        }
        return respXml;
    }
}
