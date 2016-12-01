package com.wechat.picture.web.action;

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
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

 /**
 * 类功能:查询待编辑图片资源并跳转至编辑图片资源画面
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTMaterialPicturePage")
@Scope("prototype")
public class EditTMaterialPicturePage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMaterialPictureService tMaterialPictureService;	
	//是否缓存下拉列表
	private List<SelectCsBean> cache_FlagList;
	//入参，用于查询待修改的图片信息
	private  String     pkid;	
	//出参，将要修改的图片信息反馈到页面
	private TMaterialPictureDto tMaterialPictureDto;
	// 日志对象
	private static final Logger logger = Logger.getLogger(AddTMaterialPictureAction.class);
	private String errorMessage;//错误信息
	//查询要修改的图片的详细信息
	public String execute() throws Exception {
		try {
			
			
		    TMaterialPictureDto paramTMaterialPictureDto = new TMaterialPictureDto();
		    //设置主键
			paramTMaterialPictureDto.setPicture_id(pkid);
			tMaterialPictureDto = tMaterialPictureService.getRow(paramTMaterialPictureDto );
			tMaterialPictureDto.setPicture_url(FileUploadUtil.domain_weburl+Html2TxtUtil.filePath2UrlPath(tMaterialPictureDto.getPicture_url()));
			//去掉空格，避免页面显示错位
			if(null!=tMaterialPictureDto.getPicture_desc()&&"".equals(tMaterialPictureDto.getPicture_desc().trim())){
				tMaterialPictureDto.setPicture_desc(tMaterialPictureDto.getPicture_desc().trim());
			}
			//查询是否缓存下拉列表
			cache_FlagList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,tMaterialPictureDto.getCache_flag());
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		errorMessage = "连接好像出问题了。。。";
		return ERROR;

	}

	public final TMaterialPictureDto getTMaterialPictureDto() {
		return tMaterialPictureDto;
	}

	public final void setStudentDto(TMaterialPictureDto tMaterialPictureDto) {
		this.tMaterialPictureDto = tMaterialPictureDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public List<SelectCsBean> getCache_FlagList() {
		return cache_FlagList;
	}

	public void setCache_FlagList(List<SelectCsBean> cacheFlagList) {
		cache_FlagList = cacheFlagList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
