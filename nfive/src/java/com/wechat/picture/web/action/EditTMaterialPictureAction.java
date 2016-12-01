package com.wechat.picture.web.action;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.core.utils.MediaUtil;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.material.service.ITAttachMaterialService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

 /**
 * 类功能:自动代码生成模板编辑   action 模板
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTMaterialPictureAction")
@Scope("prototype")
public class EditTMaterialPictureAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;	
	@Autowired
	private ITAttachMaterialService tAttachMaterialService;	
	//入参，从画面提取修改后的图片资源信息
	private TMaterialPictureDto tMaterialPictureDto =new TMaterialPictureDto();;	
	//上传的文件
	private File imgFile;		
	//上传的文件名
	private String imgFileFileName;		
	// 日志对象
	private static final Logger logger = Logger.getLogger(AddTMaterialPictureAction.class);
	@Autowired
	private MediaUtil mediaUtil;
	/**
	 * 执行更新操作
	 * @deprecated 
	 * 	如果选择文件则上传，然后如初文件属性，设置更新文件内容
	 * 	如果不选择文件，直接更新名称等内容
	 * @return
	 */
	public String execute() throws Exception {
		
		JSONObject json = new JSONObject();
		
		//将数据库内待修改图片数据取出
		TMaterialPictureDto tmpMaterialPictureDto = new TMaterialPictureDto();
		tmpMaterialPictureDto.setPicture_id(tMaterialPictureDto.getPicture_id());
		tmpMaterialPictureDto = tMaterialPictureService.getRow(tmpMaterialPictureDto);
		
		if(null == tmpMaterialPictureDto){
			json.put("msg", "编辑失败，图片资源不存在！");
			json.put("success", true);
			outJSOND(response, json.toString());
			return null;
		}
		
		//将数据库内待修改素材数据取出
		TAttachMaterialDto tmpAttachMaterialDto = new TAttachMaterialDto();
		tmpAttachMaterialDto.setFile_id(tmpMaterialPictureDto.getFile_id());
		tmpAttachMaterialDto = tAttachMaterialService.getRow(tmpAttachMaterialDto);
		
		int minHeight  =  200;
		int maxHeight  =  500;
		int minWidth   =  200;
		int maxWidth   =  900;	
		try {
			
			// 文件为空则不上传
			if(null != imgFile ){
				
				//图片大小限制
				if(null	!=	imgFile	&&	1*1024*1024	<=	imgFile.length()){
					json.put("msg", "图片大小不能超过1M！");
					json.put("success", false);
					outJSOND(response, json.toString());
					return null;
				}
				
				//图片格式校验
				if(!imgFileFileName.toLowerCase().endsWith("jpg")){
					System.out.print(imgFileFileName.toLowerCase());
					json.put("msg", "请上传JPG/jpg格式的图片");
					json.put("success", false);
					outJSOND(response, json.toString());
					return null;
				}
				
				File saveTmpFile=null;		
				//解析图片文件属性
				//若未输入长宽高 则使用原始图片大小
				int org_width  = tMaterialPictureDto.getPicture_wsize()!=null?tMaterialPictureDto.getPicture_wsize().intValue():0;
				int org_height  = tMaterialPictureDto.getPicture_hsize()!=null?tMaterialPictureDto.getPicture_hsize().intValue():0;
				
				if(org_width==0&&org_height==0){
					
					BufferedImage buff = ImageIO.read(imgFile);
					
					//获取参数传入文件 长宽高参数并填充
					/*不做处理*/
					tMaterialPictureDto.setPicture_hsize(new Long(buff.getHeight()));
					tMaterialPictureDto.setPicture_wsize(new Long(buff.getWidth()));
					saveTmpFile=imgFile;
				}else if(org_width!=0||org_height!=0){
					//若文件需要压缩则进行文件压缩
					//2.若输入了长宽高任意一种已经有值则按照设置压缩图片
					if(org_width<minWidth  ){org_width	=minWidth;}//设置图片最小宽度
					if(org_width>maxWidth)  {org_width 	=maxWidth;}//设置图片最大宽度
					if(org_height<minHeight){org_height  =minHeight;}//设置图片最小高度
					if(org_height>maxHeight){org_height =maxHeight;}//设置图片最大高度	
					tMaterialPictureDto.setPicture_hsize(new Long(org_height));
					tMaterialPictureDto.setPicture_wsize(new Long(org_width));			
					saveTmpFile =FileUploadUtil.zipImageFile(imgFile,org_width,org_height);
				}
				
				//临时文件转存储 并生成可供数据库保存的TAttachMaterialDto 对象
				TAttachMaterialDto tAttachMaterialDto = FileUploadUtil.bulidAttachMaterialDtoByTmpFile(saveTmpFile,imgFileFileName,CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
			
				//上传文件后修改tmpAttachMaterialDto 必要属性
				tmpAttachMaterialDto.setFile_name(tAttachMaterialDto.getFile_name());
				tmpAttachMaterialDto.setFile_postfix(tAttachMaterialDto.getFile_postfix());
				tmpAttachMaterialDto.setFile_prefix(tAttachMaterialDto.getFile_prefix());
				tmpAttachMaterialDto.setStore_path(tAttachMaterialDto.getStore_path());
				tmpAttachMaterialDto.setStoret_name(tAttachMaterialDto.getStoret_name());
				tmpAttachMaterialDto.setContent_size(tAttachMaterialDto.getContent_size());
				
					
				//上传文件后修改tmpMaterialPictureDto必要属性			
				tmpMaterialPictureDto.setPicture_url(tAttachMaterialDto.getStore_path()+tAttachMaterialDto.getStoret_name());
				tmpMaterialPictureDto.setPicture_hsize(tMaterialPictureDto.getPicture_hsize());
				tmpMaterialPictureDto.setPicture_wsize(tMaterialPictureDto.getPicture_wsize());
				mediaUtil.clearCacheAndDbData(tmpMaterialPictureDto.getPicture_id());
			
			}
			//设置tmpAttachMaterialDto 必要属性
			tmpAttachMaterialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			
			// 设置tmpMaterialPictureDto 必要属性
			tmpMaterialPictureDto.setPicture_name(tMaterialPictureDto.getPicture_name().trim());
			tmpMaterialPictureDto.setPicture_desc(tMaterialPictureDto.getPicture_desc().trim());
			tmpMaterialPictureDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			tmpMaterialPictureDto.setCache_flag(tMaterialPictureDto.getCache_flag());
			// 执行更新
			int result = tMaterialPictureService.updatePK(tmpMaterialPictureDto, tmpAttachMaterialDto);
			
			// 设置更新结果
			if(0 == result){
				json.put("msg", "数据库异常，操作失败！");
				json.put("success", false);
			}else{
				json.put("msg", "编辑成功！");
				json.put("success", true);
			}			
			outJSOND(response, json.toString());
			
			return null;
			
		// 异常处理
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
			json.put("msg", "图片上传时发生异常！");
			json.put("success", false);
			outJSOND(response, json.toString());
		}
	
		return ERROR;

	}

	public final TMaterialPictureDto getTMaterialPictureDto() {
		return tMaterialPictureDto;
	}

	public final void setStudentDto(TMaterialPictureDto tMaterialPictureDto) {
		this.tMaterialPictureDto = tMaterialPictureDto;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}
	
}
