package com.wechat.material.model.dto;
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
@EntityPK(Pk = "file_id", defaultColumn = false, tableName = "T_ATTACH_MATERIAL")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TAttachMaterialDto  extends BaseDtoImpl{//
	private java.lang.String file_id;//附件主键
	private java.lang.String file_name;//附件名称
	private java.lang.String file_prefix;//文件前缀
	private java.lang.String file_postfix;//文件后缀
	private java.lang.String file_desc;//文件说明
	private java.lang.Long   content_size;//内容大小
	private java.lang.String storet_name;//归档自动生成的存储名称 用于避免同目录下文件名冲突
	private java.lang.String store_path;//存储路径
	private java.lang.String store_server;//存储服务器
	private java.lang.String material_type;//素材附件类型[CS:5012]

   /**
	* 获取 附件主键
	* @return
	*/
	public java.lang.String getFile_id() {
		return file_id;
	}
	
   /**
	* 设置 附件主键
	* @param file_id
	*/
 	public void setFile_id(java.lang.String file_id) {
		this.file_id = file_id;
	}
   /**
	* 获取 附件名称
	* @return
	*/
	public java.lang.String getFile_name() {
		return file_name;
	}
	
   /**
	* 设置 附件名称
	* @param file_name
	*/
 	public void setFile_name(java.lang.String file_name) {
		this.file_name = file_name;
	}
   /**
	* 获取 文件前缀
	* @return
	*/
	public java.lang.String getFile_prefix() {
		return file_prefix;
	}
	
   /**
	* 设置 文件前缀
	* @param file_prefix
	*/
 	public void setFile_prefix(java.lang.String file_prefix) {
		this.file_prefix = file_prefix;
	}
   /**
	* 获取 文件后缀
	* @return
	*/
	public java.lang.String getFile_postfix() {
		return file_postfix;
	}
	
   /**
	* 设置 文件后缀
	* @param file_postfix
	*/
 	public void setFile_postfix(java.lang.String file_postfix) {
		this.file_postfix = file_postfix;
	}
   /**
	* 获取 文件说明
	* @return
	*/
	public java.lang.String getFile_desc() {
		return file_desc;
	}
	
   /**
	* 设置 文件说明
	* @param file_desc
	*/
 	public void setFile_desc(java.lang.String file_desc) {
		this.file_desc = file_desc;
	}
   /**
	* 获取 内容大小
	* @return
	*/
	public java.lang.Long getContent_size() {
		return content_size;
	}
	
   /**
	* 设置 内容大小
	* @param content_size
	*/
 	public void setContent_size(java.lang.Long content_size) {
		this.content_size = content_size;
	}
   /**
	* 获取 归档自动生成的存储名称 用于避免同目录下文件名冲突
	* @return
	*/
	public java.lang.String getStoret_name() {
		return storet_name;
	}
	
   /**
	* 设置 归档自动生成的存储名称 用于避免同目录下文件名冲突
	* @param storet_name
	*/
 	public void setStoret_name(java.lang.String storet_name) {
		this.storet_name = storet_name;
	}
   /**
	* 获取 存储路径
	* @return
	*/
	public java.lang.String getStore_path() {
		return store_path;
	}
	
   /**
	* 设置 存储路径
	* @param store_path
	*/
 	public void setStore_path(java.lang.String store_path) {
		this.store_path = store_path;
	}
   /**
	* 获取 存储服务器
	* @return
	*/
	public java.lang.String getStore_server() {
		return store_server;
	}
	
   /**
	* 设置 存储服务器
	* @param store_server
	*/
 	public void setStore_server(java.lang.String store_server) {
		this.store_server = store_server;
	}
   /**
	* 获取 素材附件类型[CS:5012]
	* @return
	*/
	public java.lang.String getMaterial_type() {
		return material_type;
	}
	
   /**
	* 设置 素材附件类型[CS:5012]
	* @param material_type
	*/
 	public void setMaterial_type(java.lang.String material_type) {
		this.material_type = material_type;
	}
}
