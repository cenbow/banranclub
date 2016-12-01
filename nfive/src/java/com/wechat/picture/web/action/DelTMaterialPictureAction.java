package com.wechat.picture.web.action;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.checkmaterial.CheckMaterial;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

 /**
 * 类功能:删除图片资源
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTMaterialPictureAction")
@Scope("prototype")
public class DelTMaterialPictureAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	
	//图片资源表主键ID
	private  String pkid;

	//素材表主键
	private String file_id;
	
	// 日志对象
	private static final Logger logger = Logger.getLogger(DelTMaterialPictureAction.class);
	
	public String execute() throws Exception {
		JSONObject jo = new JSONObject();	
		try {
			
			//分别设置待删除资源的主键ID
			TMaterialPictureDto paramTMaterialPictureDto = new TMaterialPictureDto();
			TAttachMaterialDto paramTAttachMaterialDto = new TAttachMaterialDto();
			paramTAttachMaterialDto.setFile_id(file_id);
			paramTMaterialPictureDto.setPicture_id(pkid);
			
			String exits = CheckMaterial.checkMaterialExist(pkid);
			
			//在其他数据表无关联记录
			if(null	==	exits	||	"".equals(exits)){
				//分别删除图片资源表和素材表内的记录
				tMaterialPictureService.deletePK(paramTMaterialPictureDto,paramTAttachMaterialDto);
				
				//设置删除成功信息
				jo.put("msg", "删除成功！");
				jo.put("success", true);
				
			//在其他数据表有关联记录
			}else{
				jo.put("msg", exits);
				jo.put("success", false);
			}
			
			outJSOND(response, jo.toString());
			
			return null;
		
		//设置删除失败信息
		} catch (Exception ex) {
			
			logger.error(ex.getMessage(),ex);
			jo.put("msg", "数据库异常，操作失败!");
			jo.put("success", false);
			outJSOND(response, jo.toString());
		}
		
		return ERROR;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}


	public String getFile_id() {
		return file_id;
	}


	public void setFile_id(String fileId) {
		file_id = fileId;
	}
	
}
