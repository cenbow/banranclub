package com.wechat.fans.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

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
@EntityPK(Pk = "fans_id", defaultColumn = false, tableName = "T_LATEST_FANS")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TLatestFansDto  extends BaseDtoImpl{//
	private java.lang.String fans_id;//粉丝ID
	private java.lang.String openid;//OPENID
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 粉丝ID
	* @return
	*/
	public java.lang.String getFans_id() {
		return fans_id;
	}
	
   /**
	* 设置 粉丝ID
	* @param fans_id
	*/
 	public void setFans_id(java.lang.String fans_id) {
		this.fans_id = fans_id;
	}
   /**
	* 获取 OPENID
	* @return
	*/
	public java.lang.String getOpenid() {
		return openid;
	}
	
   /**
	* 设置 OPENID
	* @param openid
	*/
 	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
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
