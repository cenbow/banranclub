package com.wechat.crowdsend.web.action;

import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

 /**
 * 类功能:消息详细页面
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("detailTMsgSendPage")
@Scope("prototype")
public class DetailTMsgSendPage extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITMsgSendService tMsgSendService;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	@Autowired
	private ITArticleGroupService tArticleGroupService;

	private  String     pkid;	//入参，消息发送结果表id
	private  String		msg_id; //入参，消息发送表id
	private TMsgSendDto tMsgSendDto;	//出参,消息详细
	private String material_name;//出参,素材名称
	private String material_url;//出参,素材URL  or 图文组类型
	private static final Logger logger = Logger.getLogger(DetailTMsgSendPage.class);
	private String errorMessage;//错误信息
	public String execute() throws Exception {
		try {
		    TMsgSendQueryBean paramTMsgSend = new TMsgSendQueryBean();
		    //设置主键
		    paramTMsgSend.setMsg_id(msg_id);
		    paramTMsgSend.setResult_pkid(pkid);
			tMsgSendDto = tMsgSendService.msgDetail(paramTMsgSend);

			//文本
			if(null != tMsgSendDto.getText_msg()){
				tMsgSendDto.setText_msg(URLEncoder.encode(tMsgSendDto.getText_msg(),"UTF-8").replace("+", "%20"));
				material_name="text message";
			}
			try{
			//图文组
			if(CodeStringConstant.CS_5062_SEND_TYPE_ARTCLE.equals(tMsgSendDto.getMsg_type())){
				//设置图文组信息
				TArticleGroupDto tArticleGroupDto = new TArticleGroupDto();
				tArticleGroupDto.setArticle_group_id(tMsgSendDto.getMaterial_id());
				tArticleGroupDto =  tArticleGroupService.getRow(tArticleGroupDto);
				//设置输出素材信息
				material_name=tArticleGroupDto.getAg_name();
				material_url=tArticleGroupDto.getArticle_type();
				//图片
			}else if(CodeStringConstant.CS_5062_SEND_TYPE_IMAGE.equals(tMsgSendDto.getMsg_type())){
				//取出图片信息
				TMaterialPictureDto tMaterialPictureDto = new TMaterialPictureDto();
				tMaterialPictureDto.setPicture_id(tMsgSendDto.getMaterial_id());
				tMaterialPictureDto = tMaterialPictureService.getRow(tMaterialPictureDto);
				//设置输出素材信息
				material_name=tMaterialPictureDto.getPicture_name();
				material_url=FileUploadUtil.domain_weburl+Html2TxtUtil.filePath2UrlPath(tMaterialPictureDto.getPicture_url());
			}
			}catch(NullPointerException ex){
				logger.info("Resource has been deleted");
			}
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		errorMessage = "连接好像出问题了。。。";
		return ERROR;
	}

	public final TMsgSendDto getTMsgSendDto() {
		return tMsgSendDto;
	}

	public final void setTMsgSendDto(TMsgSendDto tMsgSendDto) {
		this.tMsgSendDto = tMsgSendDto;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String materialName) {
		material_name = materialName;
	}

	public String getMaterial_url() {
		return material_url;
	}

	public void setMaterial_url(String materialUrl) {
		material_url = materialUrl;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
