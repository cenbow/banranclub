package com.wechat.crowdsend.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.service.ITFansGroupService;

 /**
 * 类功能:自定义群发页面准备
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("customTMsgSendPage")
@Scope("prototype")
public class CustomTMsgSendPage extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	ITMsgSendService tMsgSendService;
	@Autowired
	ITFansGroupService tFansGroupService;
	//群发对象下拉列表
	private List<SelectCsBean> bulk_target;
	//消息类型下拉列表
	private List<SelectCsBean> msg_type;
	//粉丝群
	private List<TFansGroupDto> fans_crowd;
	//剩余发送次数
	private String last_send;
	private String last_send_video;	//视频10次
	private String last_send_artcle;//图文10次
	//统一报错信息
	private String errorMessage;
	private TMsgSendDto tMsgSendDto = new TMsgSendDto();
	private static final Logger logger = Logger.getLogger(CustomTMsgSendPage.class);
	
	public String execute() throws Exception {
		try {
			
			//当前无设置公众账号
			if(null == WechatInfoUtil.getCurPubPlatformBeanFromSession()){
				errorMessage = "当前无可用公众帐号。";
				return ERROR;
			}
			//查询剩余发送次数
			Map<String,String> last_map = tMsgSendService.queryForLastSendTime(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			last_send	=	String.valueOf(last_map.get("ALL_COUNT"));
			last_send_artcle = String.valueOf(last_map.get("ARTCLE_COUNT"));
			last_send_video = String.valueOf(last_map.get("VIDEO_COUNT"));
			//去掉自定义群发的对象
			//String[] str={CodeStringConstant.CS_5060_CROWD_SEND_ALL.substring(4, 12),CodeStringConstant.CS_5060_CROWD_SEND_WEIXIN.substring(4, 12),CodeStringConstant.CS_5060_CROWD_SEND_NOINPUT.substring(4, 12),CodeStringConstant.CS_5060_CROWD_SEND_ACTIVITY.substring(4, 12)};
			//MODIFY BY ZHUBAODING 发送给所有对象
			String[] str={CodeStringConstant.CS_5060_CROWD_SEND_WEIXIN.substring(4, 12),CodeStringConstant.CS_5060_CROWD_SEND_NOINPUT.substring(4, 12),CodeStringConstant.CS_5060_CROWD_SEND_ACTIVITY.substring(4, 12)};
			//群发对象(默认发送粉丝群)
			bulk_target	=	CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5060_CROWD_SEND,CodeStringConstant.CS_5060_CROWD_SEND_FANS,str);			
			//消息类型
			msg_type	=	CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5062_SEND_TYPE,CodeStringConstant.CS_5062_SEND_TYPE_TEXT);
			//备注
			tMsgSendDto.setRemark("MSG:"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"-");
			//群发区分
			tMsgSendDto.setSend_dist(CodeStringConstant.CS_5059_SEND_DIST_CUSTOM);
			//获取所有粉丝群
			TFansGroupDto tFansGroupDto = new TFansGroupDto();
			tFansGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			fans_crowd = tFansGroupService.getAll(tFansGroupDto);
						
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		errorMessage = "连接好像出问题了。。。";
		return ERROR;
	}
	public List<SelectCsBean> getBulk_target() {
		return bulk_target;
	}
	public void setBulk_target(List<SelectCsBean> bulkTarget) {
		bulk_target = bulkTarget;
	}
	public List<SelectCsBean> getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(List<SelectCsBean> msgType) {
		msg_type = msgType;
	}
	public TMsgSendDto getTMsgSendDto() {
		return tMsgSendDto;
	}
	public void setTMsgSendDto(TMsgSendDto tMsgSendDto) {
		this.tMsgSendDto = tMsgSendDto;
	}
	public List<TFansGroupDto> getFans_crowd() {
		return fans_crowd;
	}
	public void setFans_crowd(List<TFansGroupDto> fansCrowd) {
		fans_crowd = fansCrowd;
	}
	public String getLast_send() {
		return last_send;
	}
	public void setLast_send(String lastSend) {
		last_send = lastSend;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getLast_send_video() {
		return last_send_video;
	}
	public void setLast_send_video(String lastSendVideo) {
		last_send_video = lastSendVideo;
	}
	public String getLast_send_artcle() {
		return last_send_artcle;
	}
	public void setLast_send_artcle(String lastSendArtcle) {
		last_send_artcle = lastSendArtcle;
	}
	
}
