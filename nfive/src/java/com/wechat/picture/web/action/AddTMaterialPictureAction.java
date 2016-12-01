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
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;

/**
 * 类功能:图片资源新增处理类
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("addTMaterialPictureAction")
@Scope("prototype")
public class AddTMaterialPictureAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	//从页面注入对象信息
	private TMaterialPictureDto tMaterialPictureDto= new TMaterialPictureDto();
	//上传的文件
	private File imgFile;	
	//上传的文件名
	private String imgFileFileName;		
	// 日志对象
	private static final Logger logger = Logger.getLogger(AddTMaterialPictureAction.class);
	
	/**
	 * 1.上传文件
	 * 2.保存至素材表：
	 * 3.保存至图片表：
	 * @return 返回
	 */
	public String execute() throws Exception {
		
		JSONObject json = new JSONObject();
		
		//图片大小限制
		if(null	!=	imgFile	&&	1*1024*1024	<=	imgFile.length()){
			json.put("msg", "图片大小不能超过1M！");
			json.put("success", false);
			outJSOND(response, json.toString());
			return null;
		}
		
		//图片格式校验
		if(!imgFileFileName.toLowerCase().endsWith("jpg")){
			json.put("msg", "请上传JPG/jpg格式的图片");
			json.put("success", false);
			outJSOND(response, json.toString());
			return null;
		}
		
		int minHeight  =  200;
		int maxHeight  =  500;
		int minWidth   =  200;
		int maxWidth   =  900;	
		try {
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

			//设置tAttachMaterialDto 必要属性
			tAttachMaterialDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			tAttachMaterialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			
			//设置tMaterialPictureDto必要属性
			tMaterialPictureDto.setPicture_name(tMaterialPictureDto.getPicture_name().trim());
			tMaterialPictureDto.setPicture_desc(tMaterialPictureDto.getPicture_desc().trim());
			tMaterialPictureDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			tMaterialPictureDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			tMaterialPictureDto.setPicture_url(tAttachMaterialDto.getStore_path()+tAttachMaterialDto.getStoret_name());
			tMaterialPictureDto = tMaterialPictureService.save(tMaterialPictureDto, tAttachMaterialDto);
			
			//输出保存结果
			if(null == tMaterialPictureDto){
				json.put("msg", "数据库保存异常，添加失败！");
				json.put("success", false);
			}else{
				json.put("msg", "添加成功！");
				json.put("success", true);
			}
			outJSOND(response, json.toString());
			
			return null;
		} catch (Exception ex) {
			
			//错误时打印日志
			logger.error(ex.getMessage(),ex);
			json.put("msg", "图片上传时发生异常 ！");
			json.put("success", false);
			
			outJSOND(response, json.toString());
		}

		return ERROR;
	}
	
	//---set get---
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
