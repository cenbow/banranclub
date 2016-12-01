package com.wechat.crowdsend.web.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.core.beans.other.MassMessageResult;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.wechat.crowdsend.sendutils.SendMassMessageUtils;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITWeixinGroupService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;


 /**
 * 类功能:微信群发处理类
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("crowdTMsgSendAction")
@Scope("prototype")
public class CrowdTMsgSendAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMsgSendService tMsgSendService;
	@Autowired
	private ITArticleGroupService tArticleGroupService;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	@Autowired
	private ITWeixinGroupService tWeixinGroupService;
	@Autowired
	private ITArticleItemService tArticleItemService;
	//页面提交参数
	private TMsgSendDto tMsgSendDto= new TMsgSendDto();
	//日志记录对象
	private static final Logger logger = Logger.getLogger(CrowdTMsgSendAction.class);

	//处理实体方法
	public String execute() throws Exception {
		JSONObject json = new JSONObject();
		try {

			//设置发送记录的基本信息
			//创建人
			tMsgSendDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			//更新人
			tMsgSendDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			//当前公众号ID
			tMsgSendDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			//保存明文信息
			tMsgSendDto.setText_msg(tMsgSendDto.getText_msg() == null ? null :URLDecoder.decode(tMsgSendDto.getText_msg(), "UTF-8"));

			//待保存对象：发送结果表对象列表
			List<TMsgSendResultDto> toSaveResult = new ArrayList<TMsgSendResultDto>();
			List<TMsgSendDto> toSaveSend = new ArrayList<TMsgSendDto>();
			//待保存对象：发送表对象信息

			TMsgSendDto tmpMsgSendDto = null;
			try{
				//全部用户：循环当前微信分组
				if(CodeStringConstant.CS_5060_CROWD_SEND_ALL.equals(tMsgSendDto.getSend_target())){
					TWeixinGroupDto tWeixinGroupDto = new TWeixinGroupDto();
					tWeixinGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
					List<TWeixinGroupDto> list= tWeixinGroupService.getAll(tWeixinGroupDto);
					for(TWeixinGroupDto group :list){
						//设置微信分组ID
						tmpMsgSendDto = new TMsgSendDto();
						tmpMsgSendDto.setWeixin_group_id(String.valueOf(group.getWeixin_group_id()));
						TMsgSendResultDto tmpMsgSendResultDto = new TMsgSendResultDto();
						this.sendMsg(tMsgSendDto,tmpMsgSendDto, tmpMsgSendResultDto,toSaveResult,toSaveSend);
					}
				//指定分组
				}else{
					//设置微信分组ID
					tmpMsgSendDto = new TMsgSendDto();
					tmpMsgSendDto.setWeixin_group_id(tMsgSendDto.getWeixin_group_id());
					TMsgSendResultDto tmpMsgSendResultDto = new TMsgSendResultDto();
					this.sendMsg(tMsgSendDto,tmpMsgSendDto, tmpMsgSendResultDto,toSaveResult,toSaveSend);
				}
			//本地异常，未成功发送至微信服务器，不保存数据库
			}catch(Exception ex){
				logger.error(ex.getMessage(), ex);
				json.put("success", false);
				json.put("msg", ex.getMessage());
				outJSOND(response, json.toString());
				return null;
			}
			//进行数据库保存
			tMsgSendService.saveupdateTMsgSendDto(toSaveSend, toSaveResult,null,null);
			json.put("success", true);
			json.put("msg", "消息已成功发送至服务器，具体发送结果请在群发日志一览画面内查看，确认前往查看？");
			outJSOND(response, json.toString());
			return null;

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			json.put("success", false);
			json.put("msg", "系统异常，请稍后重试。");
			outJSOND(response, json.toString());
			return null;
		}
	}

	/**
	 * 处理多条循环发送
	 * @param tMsgSendDto	页面提交参数
	 * @param tmpMsgSendDto 待保存发送表对象
	 * @param tmpMsgSendResultDto	待保存发送结果表对象
	 * @throws Exception
	 */
	public void sendMsg(TMsgSendDto tMsgSendDto,TMsgSendDto tmpMsgSendDto,TMsgSendResultDto tmpMsgSendResultDto,
			List<TMsgSendResultDto> toSaveResult,List<TMsgSendDto> toSaveSend) throws Exception{
		MassMessageResult massMessageResult = null;
		String msgType = tMsgSendDto.getMsg_type();
		String batch_no = tMsgSendService.newBatchNo();
		TWeixinGroupDto tWeixinGroupDto = new TWeixinGroupDto();
		tWeixinGroupDto.setWeixin_group_id(tmpMsgSendDto.getWeixin_group_id());
		String group_id = String.valueOf(tWeixinGroupService.getRow(tWeixinGroupDto).getGroup_id());
		if(CodeStringConstant.CS_5062_SEND_TYPE_TEXT.equals(msgType)){
			massMessageResult = SendMassMessageUtils.sendMessageMassSendAll(group_id, Html2TxtUtil.removeHTMLTag(URLDecoder.decode(tMsgSendDto.getText_msg(),"UTF-8")));
		//图文
		}else if(CodeStringConstant.CS_5062_SEND_TYPE_ARTCLE.equals(msgType)){
			List<ArticleItemVo> articleItems = tArticleGroupService.getArticleGroupPublishableItems(tMsgSendDto.getMaterial_id());
			for (ArticleItemVo articleItem : articleItems) {
				 TArticleItemDto tmp = new TArticleItemDto();
				 tmp.setArticle_item_id(articleItem.getArticle_item_id());
				 tmp = tArticleItemService.getRow(tmp);
				 articleItem.setContent(null==tmp.getContent()?"":tmp.getContent());
			 }
			massMessageResult = SendMassMessageUtils.sendMessageMassSendAll(group_id, articleItems);
		//图片
		}else if(CodeStringConstant.CS_5062_SEND_TYPE_IMAGE.equals(msgType)){
			TMaterialPictureDto tMaterialPictureDto = new TMaterialPictureDto();
			tMaterialPictureDto.setPicture_id(tMsgSendDto.getMaterial_id());
			tMaterialPictureDto = tMaterialPictureService.getRow(tMaterialPictureDto);
			massMessageResult = SendMassMessageUtils.sendMessageMassSendAll(group_id, tMaterialPictureDto);
		}
		//thow exception
		if (StringUtils.isNotBlank(massMessageResult.getErrcode()) && !"0".equals(massMessageResult.getErrcode())) {
			throw new Exception("[" + massMessageResult.getErrcode() + "]" + massMessageResult.getErrmsg());
		}

		//设置主表发送状态与信息类型
		tmpMsgSendDto.setBatch_no(batch_no);
		tmpMsgSendDto.setCreated_user_cd(tMsgSendDto.getCreated_user_cd());
		tmpMsgSendDto.setFans_group_flg(tMsgSendDto.getFans_group_flg());
		tmpMsgSendDto.setMaterial_id(tMsgSendDto.getMaterial_id());
		tmpMsgSendDto.setPlatform_id(tMsgSendDto.getPlatform_id());
		tmpMsgSendDto.setRemark(tMsgSendDto.getRemark());
		tmpMsgSendDto.setSend_dist(tMsgSendDto.getSend_dist());
		tmpMsgSendDto.setSend_if(tMsgSendDto.getSend_if());
		tmpMsgSendDto.setSend_target(tMsgSendDto.getSend_target());
		tmpMsgSendDto.setTemplet_flag(tMsgSendDto.getTemplet_flag());
		tmpMsgSendDto.setText_msg(URLDecoder.decode(tMsgSendDto.getText_msg(),"UTF-8"));
		tmpMsgSendDto.setUpdated_user_cd(tMsgSendDto.getUpdated_user_cd());
		tmpMsgSendDto.setMsg_type(tMsgSendDto.getMsg_type());

		//设置发送结果表内容
		tmpMsgSendResultDto.setBatch_no(batch_no);
		tmpMsgSendResultDto.setCreated_user_cd(tMsgSendDto.getCreated_user_cd());
		tmpMsgSendResultDto.setUpdated_user_cd(tMsgSendDto.getUpdated_user_cd());

		//设置调用接口后设置数据
		tmpMsgSendDto.setSend_status("0".equals(massMessageResult.getErrcode())?CodeStringConstant.CS_5063_SEND_STATUS_PROCESSING:CodeStringConstant.CS_5063_SEND_STATUS_AUDITFAILURE);	//如果失败，直接保存为失败，默认为处理中
		tmpMsgSendResultDto.setErr_msg(massMessageResult.getErrmsg());
		tmpMsgSendResultDto.setMsg_code(massMessageResult.getErrcode());
		tmpMsgSendResultDto.setSend_status("0".equals(massMessageResult.getErrcode())?CodeStringConstant.CS_5063_SEND_STATUS_PROCESSING:CodeStringConstant.CS_5063_SEND_STATUS_AUDITFAILURE);
		tmpMsgSendResultDto.setMsg_code(massMessageResult.getMsg_id());

		//增加数据库记录数
		toSaveResult.add(tmpMsgSendResultDto);
		toSaveSend.add(tmpMsgSendDto);
	}
	public final TMsgSendDto getTMsgSendDto() {
		return tMsgSendDto;
	}

	public final void setStudentDto(TMsgSendDto tMsgSendDto) {
		this.tMsgSendDto = tMsgSendDto;
	}
}
