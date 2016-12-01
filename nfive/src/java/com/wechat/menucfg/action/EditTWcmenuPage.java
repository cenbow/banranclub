package com.wechat.menucfg.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.service.ITWxNewsService;
import org.apache.commons.lang.StringUtils;
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
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

/**
 * 类功能:自动代码生成模板编辑 action 模板
 * <p>
 * 创建者:
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */

@Controller("editTWcmenuPage")
@Scope("prototype")
public class EditTWcmenuPage extends BaseAction {
	private static final Logger logger = Logger.getLogger(AddTWcmenuPage.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWcmenuService tWcmenuService;
	@Autowired
	private ITMaterialPictureService tmaterialPictureService;
	@Autowired
	private ITArticleGroupService tArticleGroupService;
    @Autowired
    private ITWxNewsService wxNewsService;

	// 主键
	private String pkid;
	// 出参
	private TWcmenuQueryBean tWcmenuQueryBean = new TWcmenuQueryBean();
	// 出参  下拉框
	List<SelectCsBean> menuTypeItems;
	//出参 启用客服模式
	private List<SelectCsBean>	cust_srv_flag_SelList;
	// 出参 素材名称
	private String material_name;
	// 出参 素材URL
	private String material_url;

	public String execute() throws Exception {
		try {
			TWcmenuDto paramTWcmenuDto = new TWcmenuDto();
			// 设置主键
			paramTWcmenuDto.setMenu_id(pkid);
			tWcmenuQueryBean = tWcmenuService
					.getTWcmenuQueryBean(paramTWcmenuDto);
			// 获取下拉框节点
			menuTypeItems = CodeStringUtil.getSelectCsBeanByCsType(
					CodeStringConstant.MENU_TYPE, tWcmenuQueryBean
							.getAction_type());
			if (tWcmenuQueryBean.getText_msg() != null) {
				tWcmenuQueryBean.setText_msg(URLDecoder.decode(tWcmenuQueryBean.getText_msg(), "UTF-8"));
			}
			for (SelectCsBean selectCsBean : menuTypeItems) {
				if (StringUtils.isBlank(selectCsBean.getKey())) {
					selectCsBean.setKey(CodeStringConstant.MENU_TYPE_NOTINPUT);
					break;
				}
			}
			//构造启用客服模式下拉列表数据
			cust_srv_flag_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,tWcmenuQueryBean.getCust_srv_flag());

			//图片类型
			if(CodeStringConstant.MENU_TYPE_IMAGE.equals(tWcmenuQueryBean.getAction_type())){
				//根据ID查询出图片名称设置到输出图片实体中
				TMaterialPictureDto paramPictureDto = new TMaterialPictureDto();
				paramPictureDto.setPicture_id(tWcmenuQueryBean.getMaterial_id());
				TMaterialPictureDto result_MaterialPictureDto = tmaterialPictureService.getRow(paramPictureDto);
//				material_url = URLEncoder.encode(result_MaterialPictureDto.getPicture_url(), "UTF-8");
				if (result_MaterialPictureDto != null) {
					material_url = Html2TxtUtil.filePath2UrlPath(FileUploadUtil.domain_weburl + result_MaterialPictureDto.getPicture_url()); // 图片URL
					material_name = result_MaterialPictureDto.getPicture_name();
				}
			//图文组类型
			}else if (CodeStringConstant.MENU_TYPE_IMAGE_TEXT.equals(tWcmenuQueryBean.getAction_type())){
				//根据ID查询出图文组名称设置到输出图文组实体中
                TWxNewsDto wxNewsDto = new TWxNewsDto();
                wxNewsDto.setNews_id(tWcmenuQueryBean.getMaterial_id());
                wxNewsDto = wxNewsService.getRow(wxNewsDto);

				if (wxNewsDto != null) {
//					material_url = tArticleGroupDto.getArticle_type();
					material_name = wxNewsDto.getTitle();
				}
			}
			return SUCCESS;
		} catch (Exception ex) {
			logger.error("------编辑菜单异常------\r\n", ex);
		}

		return ERROR;

	}

	public TWcmenuQueryBean getTWcmenuQueryBean() {
		return tWcmenuQueryBean;
	}

	public void setTWcmenuQueryBean(TWcmenuQueryBean tWcmenuQueryBean) {
		this.tWcmenuQueryBean = tWcmenuQueryBean;
	}

	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public List<SelectCsBean> getMenuTypeItems() {
		return menuTypeItems;
	}

	public void setMenuTypeItems(List<SelectCsBean> menuTypeItems) {
		this.menuTypeItems = menuTypeItems;
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

	public List<SelectCsBean> getCust_srv_flag_SelList() {
		return cust_srv_flag_SelList;
	}

	public void setCust_srv_flag_SelList(List<SelectCsBean> custSrvFlagSelList) {
		cust_srv_flag_SelList = custSrvFlagSelList;
	}

}
