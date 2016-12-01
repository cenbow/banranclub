package com.wechat.replycfg.web.action;

import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;

 /**
 * 类功能:特殊回复详细
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("detailTReplySpecialPage")
@Scope("prototype")
public class DetailTReplySpecialPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplySpecialService tReplySpecialService;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	@Autowired
	private ITArticleGroupService tArticleGroupService;
	// 入参
	private String pkid;

	// 出参
	private TReplySpecialDto tReplySpecialDto;
	private String material_name;
    private String material_url;
    private String reply_type;
    private List<ArticleItemVo> tArticleItemVo ;
  //出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(DetailTReplySpecialPage.class);

	public String execute() throws Exception {
		try {
		    TReplySpecialDto paramTReplySpecialDto = new TReplySpecialDto();
		    //设置主键
			paramTReplySpecialDto.setFreply_id(pkid);
			tReplySpecialDto = tReplySpecialService.getRow(paramTReplySpecialDto );

			/*
			 * 1.回复类型是文本    2.回复类型是图片、图文、音频、视频类型进行处理
			 */
			//回复类型是文本
	    	if(tReplySpecialDto.getText_msg() != null){
			   tReplySpecialDto.setText_msg(URLEncoder.encode(tReplySpecialDto.getText_msg(), "UTF-8").replace("+", "%20"));
	     	}
			//回复类型是图片
			if (CodeStringConstant.CS_5052_REPLAY_PICTURE_MSG.equals(tReplySpecialDto.getReply_type())) {
				//获取图片
				TMaterialPictureDto paramTMaterialPictureDto = new TMaterialPictureDto();
				//设置图片主键
				paramTMaterialPictureDto.setPicture_id(tReplySpecialDto.getMaterial_id());
				TMaterialPictureDto	tMaterialPictureDto = tMaterialPictureService.getRow(paramTMaterialPictureDto);
				material_name = tMaterialPictureDto.getPicture_name();
				material_url = FileUploadUtil.domain_weburl+ Html2TxtUtil.filePath2UrlPath(tMaterialPictureDto.getPicture_url().toString());

			}
			//回复类型是图文
			if (CodeStringConstant.CS_5052_REPLAY_ARTICLE_MSG.equals(tReplySpecialDto.getReply_type())) {

		        //获取图文组
		        TArticleGroupDto paramTArticleGroupDto = new TArticleGroupDto();
			    //设置图文组主键
				paramTArticleGroupDto.setArticle_group_id(tReplySpecialDto.getMaterial_id());
				//获取图文组
				TArticleGroupDto tArticleGroupDto =	tArticleGroupService.getRow(paramTArticleGroupDto);

				material_name = tArticleGroupDto.getAg_name();
				material_url = tArticleGroupDto.getArticle_type();

			}

			//将特殊回复规则csCode翻译csValue
			tReplySpecialDto.setRule_type(CodeStringUtil.tranCsValueByCsCode(tReplySpecialDto.getRule_type()));
			//将回复类型csCode翻译csValue
	         reply_type =	CodeStringUtil.tranCsValueByCsCode(tReplySpecialDto.getReply_type());
			//将生效标志csCode翻译csValue
			tReplySpecialDto.setEffect_flag(CodeStringUtil.tranCsValueByCsCode(tReplySpecialDto.getEffect_flag()));

			return SUCCESS;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;

	}

	public final TReplySpecialDto getTReplySpecialDto() {
		return tReplySpecialDto;
	}

	public final void setStudentDto(TReplySpecialDto tReplySpecialDto) {
		this.tReplySpecialDto = tReplySpecialDto;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public final String getMaterial_name() {
		return material_name;
	}

	public final void setMaterial_name(String materialName) {
		material_name = materialName;
	}

	public final String getMaterial_url() {
		return material_url;
	}

	public final void setMaterial_url(String materialUrl) {
		material_url = materialUrl;
	}

	public final String getReply_type() {
		return reply_type;
	}

	public final void setReply_type(String replyType) {
		reply_type = replyType;
	}

	public final List<ArticleItemVo> getTArticleItemVo() {
		return tArticleItemVo;
	}

	public final void setTArticleItemVo(List<ArticleItemVo> tArticleItemVo) {
		this.tArticleItemVo = tArticleItemVo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
