package com.wechat.picture.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:自动代码生成模板 DTO模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 * 
 * 规约开启方式 用于规约符合我们要求的对象
 * 1. 设置   AddParentClass = true
 * 2. 类       extends BaseDtoImpl
 * 
 * 规约关闭方式  用于规约不受我们控制的对象
 * 1. 设置   AddParentClass = false
 * 2. 类        implements IBaseDto
 */
@SuppressWarnings("serial")
@EntityPK(Pk = "picture_id", defaultColumn = false, tableName = "T_MATERIAL_PICTURE")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TMaterialPictureDto  extends BaseDtoImpl{//
	private java.lang.String picture_id;//图片素材主键
	private java.lang.String picture_name;//图片名称
	private java.lang.String picture_url;//图片URL
	private java.lang.Long picture_wsize;//图片规格宽
	private java.lang.Long picture_hsize;//图片规格高
	private java.lang.String picture_desc;//图片描述
	private java.lang.String file_id;//关联文件ID
	private java.lang.String cache_flag;//是否缓存

   /**
	* 获取 图片素材主键
	* @return
	*/
	public java.lang.String getPicture_id() {
		return picture_id;
	}
	
   /**
	* 设置 图片素材主键
	* @param picture_id
	*/
 	public void setPicture_id(java.lang.String picture_id) {
		this.picture_id = picture_id;
	}
   /**
	* 获取 图片名称
	* @return
	*/
	public java.lang.String getPicture_name() {
		return picture_name;
	}
	
   /**
	* 设置 图片名称
	* @param picture_name
	*/
 	public void setPicture_name(java.lang.String picture_name) {
		this.picture_name = picture_name;
	}
   /**
	* 获取 图片URL
	* @return
	*/
	public java.lang.String getPicture_url() {
		return picture_url;
	}
	
   /**
	* 设置 图片URL
	* @param picture_url
	*/
 	public void setPicture_url(java.lang.String picture_url) {
		this.picture_url = picture_url;
	}
   /**
	* 获取 图片规格宽
	* @return
	*/
	public java.lang.Long getPicture_wsize() {
		return picture_wsize;
	}
	
   /**
	* 设置 图片规格宽
	* @param picture_wsize
	*/
 	public void setPicture_wsize(java.lang.Long picture_wsize) {
		this.picture_wsize = picture_wsize;
	}
   /**
	* 获取 图片规格高
	* @return
	*/
	public java.lang.Long getPicture_hsize() {
		return picture_hsize;
	}
	
   /**
	* 设置 图片规格高
	* @param picture_hsize
	*/
 	public void setPicture_hsize(java.lang.Long picture_hsize) {
		this.picture_hsize = picture_hsize;
	}
   /**
	* 获取 图片描述
	* @return
	*/
	public java.lang.String getPicture_desc() {
		return picture_desc;
	}
	
   /**
	* 设置 图片描述
	* @param picture_desc
	*/
 	public void setPicture_desc(java.lang.String picture_desc) {
		this.picture_desc = picture_desc;
	}
   /**
	* 获取 关联文件ID
	* @return
	*/
	public java.lang.String getFile_id() {
		return file_id;
	}
	
   /**
	* 设置 关联文件ID
	* @param file_id
	*/
 	public void setFile_id(java.lang.String file_id) {
		this.file_id = file_id;
	}
 	/**
	* 获取 是否缓存
	* @param file_id
	*/
	public java.lang.String getCache_flag() {
		return cache_flag;
	}
	/**
	* 设置 是否缓存
	* @param file_id
	*/
	public void setCache_flag(java.lang.String cacheFlag) {
		cache_flag = cacheFlag;
	}
 	
}
