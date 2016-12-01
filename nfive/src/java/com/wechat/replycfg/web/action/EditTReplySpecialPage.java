package com.wechat.replycfg.web.action;

import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
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
 * 类功能:跳转特殊回复编辑页面
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTReplySpecialPage")
@Scope("prototype")
public class EditTReplySpecialPage extends BaseAction {
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
    List<ArticleItemVo> tArticleItemVo;
	// 特殊回复规则
	private List<SelectCsBean> rule_typeList;
	// 回复类型
	private List<SelectCsBean> reply_typeList;
	// 生效标志
	private List<SelectCsBean> effect_flagList;
	//出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(EditTReplySpecialPage.class);

	public String execute() throws Exception {
		try {

		    TReplySpecialDto paramTReplySpecialDto = new TReplySpecialDto();
		   //设置特殊回复规则主键
			paramTReplySpecialDto.setFreply_id(pkid);
			tReplySpecialDto = tReplySpecialService.getRow(paramTReplySpecialDto);

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
				//设置图文组主键
				paramTArticleGroupDto.setArticle_group_id(tReplySpecialDto.getMaterial_id());
				//获取图文组
				TArticleGroupDto tArticleGroupDto =	tArticleGroupService.getRow(paramTArticleGroupDto);

				material_name = tArticleGroupDto.getAg_name();
				material_url = tArticleGroupDto.getArticle_type();

			}
			//特殊回复规则下拉列表
			rule_typeList  =  CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.REPLY_RULE, tReplySpecialDto.getRule_type());
			//回复类型下拉列表
			reply_typeList  = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.KEYWORK_TYPE, tReplySpecialDto.getReply_type());
			//生效标志下拉列表
			effect_flagList  = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG, tReplySpecialDto.getEffect_flag());

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

	public List<SelectCsBean> getRule_typeList() {
		return rule_typeList;
	}

	public void setRule_typeList(List<SelectCsBean> ruleTypeList) {
		rule_typeList = ruleTypeList;
	}

	public List<SelectCsBean> getReply_typeList() {
		return reply_typeList;
	}

	public void setReply_typeList(List<SelectCsBean> replyTypeList) {
		reply_typeList = replyTypeList;
	}

	public List<SelectCsBean> getEffect_flagList() {
		return effect_flagList;
	}

	public void setEffect_flagList(List<SelectCsBean> effectFlagList) {
		effect_flagList = effectFlagList;
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
