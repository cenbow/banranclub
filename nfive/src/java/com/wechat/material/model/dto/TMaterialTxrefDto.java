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
@EntityPK(Pk = "txref_id", defaultColumn = false, tableName = "T_MATERIAL_TXREF")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TMaterialTxrefDto  extends BaseDtoImpl{//
	private java.lang.String txref_id;//关联主键
	private java.lang.String material_type;//素材附件类型[CS:5012]
	private java.lang.String material_id;//消息素材ID(不同类型关联不同表)
	private java.lang.String tx_thumb_mdeia_id;//媒体文件缩略图
	private java.lang.String tx_media_id;//媒体文件标识
	private java.lang.String tx_type;//媒体文件类型
	private java.lang.Long tx_created_at;//媒体文件上传时间
	private java.lang.Long tx_expiration_at;//媒体文件默认过期时间
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 关联主键
	* @return
	*/
	public java.lang.String getTxref_id() {
		return txref_id;
	}
	
   /**
	* 设置 关联主键
	* @param txref_id
	*/
 	public void setTxref_id(java.lang.String txref_id) {
		this.txref_id = txref_id;
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
   /**
	* 获取 消息素材ID(不同类型关联不同表)
	* @return
	*/
	public java.lang.String getMaterial_id() {
		return material_id;
	}
	
   /**
	* 设置 消息素材ID(不同类型关联不同表)
	* @param material_id
	*/
 	public void setMaterial_id(java.lang.String material_id) {
		this.material_id = material_id;
	}
   /**
	* 获取 媒体文件缩略图
	* @return
	*/
	public java.lang.String getTx_thumb_mdeia_id() {
		return tx_thumb_mdeia_id;
	}
	
   /**
	* 设置 媒体文件缩略图
	* @param tx_thumb_mdeia_id
	*/
 	public void setTx_thumb_mdeia_id(java.lang.String tx_thumb_mdeia_id) {
		this.tx_thumb_mdeia_id = tx_thumb_mdeia_id;
	}
   /**
	* 获取 媒体文件标识
	* @return
	*/
	public java.lang.String getTx_media_id() {
		return tx_media_id;
	}
	
   /**
	* 设置 媒体文件标识
	* @param tx_media_id
	*/
 	public void setTx_media_id(java.lang.String tx_media_id) {
		this.tx_media_id = tx_media_id;
	}
   /**
	* 获取 媒体文件类型
	* @return
	*/
	public java.lang.String getTx_type() {
		return tx_type;
	}
	
   /**
	* 设置 媒体文件类型
	* @param tx_type
	*/
 	public void setTx_type(java.lang.String tx_type) {
		this.tx_type = tx_type;
	}
   /**
	* 获取 媒体文件上传时间
	* @return
	*/
	public java.lang.Long getTx_created_at() {
		return tx_created_at;
	}
	
   /**
	* 设置 媒体文件上传时间
	* @param tx_created_at
	*/
 	public void setTx_created_at(java.lang.Long tx_created_at) {
		this.tx_created_at = tx_created_at;
	}
   /**
	* 获取 媒体文件默认过期时间
	* @return
	*/
	public java.lang.Long getTx_expiration_at() {
		return tx_expiration_at;
	}
	
   /**
	* 设置 媒体文件默认过期时间
	* @param tx_expiration_at
	*/
 	public void setTx_expiration_at(java.lang.Long tx_expiration_at) {
		this.tx_expiration_at = tx_expiration_at;
	}
   /**
	* 获取 公众平台号ID
	* @return
	*/
	public java.lang.String getPlatform_id() {
		return platform_id;
	}
	
   /**
	* 设置 公众平台号ID
	* @param platform_id
	*/
 	public void setPlatform_id(java.lang.String platform_id) {
		this.platform_id = platform_id;
	}
}
