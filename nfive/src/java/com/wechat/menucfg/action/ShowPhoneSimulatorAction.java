package com.wechat.menucfg.action;

import java.net.URLDecoder;
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
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;


/**
 * 类功能:自动代码生成模板编辑   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("showPhoneSimulatorAction")
@Scope("prototype")
public class ShowPhoneSimulatorAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SearchTWcmSelfRelaAction.class);

	@Autowired
	private ITWcmenuService tWcmenuService;

	@Autowired
	private ITMaterialPictureService ITMaterialPictureService;

	@Autowired
	private ITArticleGroupService tArticleGroupService;


	/** 入力参数 */
	private String action_type;		// 菜单动作类型
	private String menu_Id;			// 菜单ID
	private String material_id;		// 动作关联素材id

	/** 出力参数 */
	private String text_msg; 		// 文本内容
	private String picture_url;		// 图片URL
	private String article_type;	// 图文素材类型【CS:5051】1.单图文 2.多图文
	private String media_url;		//音频/视频媒体URL信息

	public String execute() throws Exception {
		try {
			if (action_type != null) {

				if (CodeStringConstant.MENU_TYPE_TEXT.equals(action_type)) {	// 文本内容
					TWcmenuDto tmpTWcmenuDto = new TWcmenuDto();
					tmpTWcmenuDto.setMenu_id(menu_Id); // 设置主键
					TWcmenuDto resultDto = tWcmenuService.getRow(tmpTWcmenuDto);

					if (resultDto != null) {
						text_msg = resultDto.getText_msg(); // 文本内容
					}
					logger.info("------文本内容:\r\n" + text_msg);
				} else if (CodeStringConstant.MENU_TYPE_IMAGE.equals(action_type)) {	// 图片素材
					TMaterialPictureDto tMaterialPictureDto = new TMaterialPictureDto();
					tMaterialPictureDto.setPicture_id(material_id); // 图片素材主键
					TMaterialPictureDto resultDto = ITMaterialPictureService.getRow(tMaterialPictureDto);

					if (resultDto != null) {
						picture_url = Html2TxtUtil.filePath2UrlPath(FileUploadUtil.domain_weburl + resultDto.getPicture_url()); // 图片URL
					}
					logger.info("------图片URL:\r\n" + picture_url);
				} else if (CodeStringConstant.MENU_TYPE_IMAGE_TEXT.equals(action_type)) { // 图文素材
					TArticleGroupDto tArticleGroupDto = new TArticleGroupDto();
					tArticleGroupDto.setArticle_group_id(material_id); // 图文素材主键
					TArticleGroupDto resultDto = tArticleGroupService.getRow(tArticleGroupDto);

					if (resultDto != null) {
						article_type = resultDto.getArticle_type(); // 图文素材类型【CS:5051】
					}
					logger.info("------图文素材类型:\r\n" + article_type);
				}
			}

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String actionType) {
		action_type = actionType;
	}

	public String getMenu_Id() {
		return menu_Id;
	}

	public void setMenu_Id(String menuId) {
		menu_Id = menuId;
	}

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String materialId) {
		material_id = materialId;
	}

	public String getText_msg() {
		return text_msg;
	}

	public void setText_msg(String textMsg) {
		text_msg = textMsg;
	}

	public String getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String pictureUrl) {
		picture_url = pictureUrl;
	}

	public String getArticle_type() {
		return article_type;
	}

	public void setArticle_type(String articleType) {
		article_type = articleType;
	}

	public String getMedia_url() {
		return media_url;
	}

	public void setMedia_url(String mediaUrl) {
		media_url = mediaUrl;
	}

}
