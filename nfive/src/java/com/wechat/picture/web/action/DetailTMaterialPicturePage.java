package com.wechat.picture.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

 /**
 * 类功能:查询图片资源详细信息
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("detailTMaterialPicturePage")
@Scope("prototype")
public class DetailTMaterialPicturePage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	
	//入参，查询图片详细信息
	private  String     pkid;	
	//出参，反馈到页面的图片信息
	private TMaterialPictureDto tMaterialPictureDto;
	// 日志对象
	private static final Logger logger = Logger.getLogger(AddTMaterialPictureAction.class);
	
	public String execute() throws Exception {
		try {
		    TMaterialPictureDto paramTMaterialPictureDto = new TMaterialPictureDto();
		   
		    //设置主键并查询图片信息
			paramTMaterialPictureDto.setPicture_id(pkid);
			tMaterialPictureDto = tMaterialPictureService.getRow(paramTMaterialPictureDto );
			//图片连接
			tMaterialPictureDto.setPicture_url(FileUploadUtil.domain_weburl+Html2TxtUtil.filePath2UrlPath(tMaterialPictureDto.getPicture_url()));
			//是否缓存
			if(null != tMaterialPictureDto.getCache_flag()){
				tMaterialPictureDto.setCache_flag(CodeStringUtil.tranCsValueByCsCode(tMaterialPictureDto.getCache_flag()));
			}
			
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}

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

}
