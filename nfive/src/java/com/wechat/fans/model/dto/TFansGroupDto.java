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
@EntityPK(Pk = "fans_group_id", defaultColumn = false, tableName = "T_FANS_GROUP")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TFansGroupDto  extends BaseDtoImpl{//
	private java.lang.String fans_group_id;//粉丝群ID
	private java.lang.String group_name;//群名称
	private java.lang.String remark;//备注说明
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 粉丝群ID
	* @return
	*/
	public java.lang.String getFans_group_id() {
		return fans_group_id;
	}
	
   /**
	* 设置 粉丝群ID
	* @param fans_group_id
	*/
 	public void setFans_group_id(java.lang.String fans_group_id) {
		this.fans_group_id = fans_group_id;
	}
   /**
	* 获取 群名称
	* @return
	*/
	public java.lang.String getGroup_name() {
		return group_name;
	}
	
   /**
	* 设置 群名称
	* @param group_name
	*/
 	public void setGroup_name(java.lang.String group_name) {
		this.group_name = group_name;
	}
   /**
	* 获取 备注说明
	* @return
	*/
	public java.lang.String getRemark() {
		return remark;
	}
	
   /**
	* 设置 备注说明
	* @param remark
	*/
 	public void setRemark(java.lang.String remark) {
		this.remark = remark;
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
