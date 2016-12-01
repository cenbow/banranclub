package com.wechat.replycfg.web.action;


import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.wechat.replycfg.service.ITReplyKeywordService;

 /**
 * 类功能:展示关键字回复规则一览画面
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("detailTReplyKeywordPage")
@Scope("prototype")
public class DetailTReplyKeywordPage extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(DetailTReplyKeywordPage.class);


	@Autowired
	private ITReplyKeywordService tReplyKeywordService;
	@Autowired
	private ITMaterialPictureService tmaterialPictureService;
	@Autowired
	private ITArticleGroupService tArticleGroupService;

	//入参
	private  String     pkid;

	//出参  关键字回复规则
	private TReplyKeywordDto out_tReplyKeywordDto;

	//出参   素材name
	private String material_name;

	//出参   素材URL
	private String material_url;

	//出参数  翻译后的回复类型
	private String reply_type_text;

	public String execute() throws Exception {
		try {
		    TReplyKeywordDto paramTReplyKeywordDto = new TReplyKeywordDto();
		    //设置主键
			paramTReplyKeywordDto.setKreply_id(pkid);
			out_tReplyKeywordDto = tReplyKeywordService.getRow(paramTReplyKeywordDto);

			//翻译匹配类型显示在页面
			out_tReplyKeywordDto.setMatch_type(CodeStringUtil.tranCsValueByCsCode(out_tReplyKeywordDto.getMatch_type()));
			//翻译回复类型显示在页面
			reply_type_text = CodeStringUtil.tranCsValueByCsCode(out_tReplyKeywordDto.getReply_type());
			//翻译启用标志显示在页面
			out_tReplyKeywordDto.setEffect_flag(CodeStringUtil.tranCsValueByCsCode(out_tReplyKeywordDto.getEffect_flag()));
			//翻译启用客服模式显示在页面
			out_tReplyKeywordDto.setCust_srv_flag(CodeStringUtil.tranCsValueByCsCode(out_tReplyKeywordDto.getCust_srv_flag()));

			//设置文本值  转码
			if(null != out_tReplyKeywordDto.getText_msg()){
				out_tReplyKeywordDto.setText_msg(URLEncoder.encode(out_tReplyKeywordDto.getText_msg(),"UTF-8").replace("+", "%20"));
			}

			//判断素材类型ID是否存在
			if(null != out_tReplyKeywordDto.getMaterial_id() && !"".equalsIgnoreCase(out_tReplyKeywordDto.getMaterial_id().trim())){

				//图片类型
				if(CodeStringConstant.CS_5052_REPLAY_PICTURE_MSG.equalsIgnoreCase(out_tReplyKeywordDto.getReply_type())){

					//根据ID查询出图片名称设置到输出图片实体中
					TMaterialPictureDto paramPictureDto = new TMaterialPictureDto();
					paramPictureDto.setPicture_id(out_tReplyKeywordDto.getMaterial_id());
					TMaterialPictureDto result_MaterialPictureDto = tmaterialPictureService.getRow(paramPictureDto);

					//拼装URL   项目路径+url  转换反斜杠
					material_url = Html2TxtUtil.filePath2UrlPath(FileUploadUtil.domain_weburl+result_MaterialPictureDto.getPicture_url());
					material_name = result_MaterialPictureDto.getPicture_name();

				//图文组类型
				}else if (CodeStringConstant.CS_5052_REPLAY_ARTICLE_MSG.equalsIgnoreCase(out_tReplyKeywordDto.getReply_type())){

					//根据ID查询出图文组名称设置到输出图文组实体中
					TArticleGroupDto paramArticleGroupDto = new TArticleGroupDto();
					paramArticleGroupDto.setArticle_group_id(out_tReplyKeywordDto.getMaterial_id());
					TArticleGroupDto tArticleGroupDto= tArticleGroupService.getRow(paramArticleGroupDto);
					material_url = tArticleGroupDto.getArticle_type();
					material_name = tArticleGroupDto.getAg_name();
				}
			}

			return SUCCESS;
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}



	public TReplyKeywordDto getOut_tReplyKeywordDto() {
		return out_tReplyKeywordDto;
	}



	public void setOut_tReplyKeywordDto(TReplyKeywordDto outTReplyKeywordDto) {
		out_tReplyKeywordDto = outTReplyKeywordDto;
	}



	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
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



	public String getReply_type_text() {
		return reply_type_text;
	}



	public void setReply_type_text(String replyTypeText) {
		reply_type_text = replyTypeText;
	}


}
