package com.wechat.core.handle.logic.event.subscribe;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.platform.common.tools.wechat.WechatUtil;
import com.wechat.fans.service.ITWxFansService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.core.utils.MediaUtil;
import com.wechat.fans.service.ITWeixinFansService;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;

@Scope("prototype")
@Service("subscribeEventHandle")
public class SubscribeEventHandle implements ILogicHandle {
	protected static final Log log = LogFactory
			.getLog(SubscribeEventHandle.class);
	@Autowired
	private ITReplySpecialService tReplySpecialService;

	// 素材管理工具类
	@Autowired
	private MediaUtil mediaUtil;
	@Autowired
	private ITWxFansService wxFansService;

	/**
	 * 首次关注订阅事件处理
	 */
	public String doHandle(Map<String, String> requestMap) {

		// 接收方帐号（收到的OpenID） (需调换位置)
		String toUserName = requestMap.get("FromUserName");

		try {
			//添加粉丝信息到粉丝信息表
            wxFansService.saveOrUpdateWxFans(toUserName);
            return mediaUtil.assembleTextMessage(toUserName,"欢迎关注NFIVE，我们致力于电竞游戏原创周边，秉持匠心，打造行业潮牌。");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 首次关注回复处理 (首次关注事件)
	 *
	 * @param toUserName
	 *            普通用户（公众帐号 -> 普通用户）
	 * @param fromUserName
	 *            公众帐号（普通用户<- 公众帐号）
	 * @param tReplySpecialDto
	 *            回复内容数据Dto
	 * @return String(xml)
	 * @throws Exception
	 */
	private String specialKeyWordHandle(String toUserName,
			TReplySpecialDto tReplySpecialDto) throws Exception {
		// 1.发送者OPENID
		String fromUserName = WechatInfoUtil.getTPubPlatformDtoByPlatformId(
				tReplySpecialDto.getPlatform_id()).getOrg_id();

		// 关键字回复类型的图文消息 CS_5052_REPLAY_ARTICLE_MSG = "505200000001";
		if (CodeStringConstant.CS_5052_REPLAY_ARTICLE_MSG
				.equalsIgnoreCase(tReplySpecialDto.getReply_type())) {
			return mediaUtil.assembleArticleGroupMessage(tReplySpecialDto
					.getPlatform_id(), toUserName, tReplySpecialDto
					.getMaterial_id());
		}

		// 关键字回复类型的文本消息 CS_5052_REPLAY_TEXT_MSG = "505200000002";
		if (CodeStringConstant.CS_5052_REPLAY_TEXT_MSG
				.equalsIgnoreCase(tReplySpecialDto.getReply_type())) {
			return mediaUtil.assembleTextMessage(toUserName, tReplySpecialDto.getText_msg());
		}

		// 关键字回复类型的图片消息 CS_5052_REPLAY_PICTURE_MSG = "505200000003";
		if (CodeStringConstant.CS_5052_REPLAY_PICTURE_MSG
				.equalsIgnoreCase(tReplySpecialDto.getReply_type())) {
			return mediaUtil.assembleImageMessage(tReplySpecialDto
					.getPlatform_id(), toUserName, tReplySpecialDto
					.getMaterial_id());
		}

		// 关键字回复类型的音频消息 CS_5052_REPLAY_VOICE_MSG = "505200000004";
		if (CodeStringConstant.CS_5052_REPLAY_VOICE_MSG
				.equalsIgnoreCase(tReplySpecialDto.getReply_type())) {
			return mediaUtil.assembleVoiceMessage(tReplySpecialDto
					.getPlatform_id(), toUserName, tReplySpecialDto
					.getMaterial_id());

		}


		return null;
	}

}
