package com.platform.persistence.model.dto;
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
@EntityPK(Pk = "cs_code", defaultColumn = false, tableName = "T_CODESTRING")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TCodestringDto  extends BaseDtoImpl{//
	private java.lang.String cs_code;//参数代码
	private java.lang.String cs_type;//参数类型
	private java.lang.String cs_sub_type;//参数类型项下的子类值,应用可依次进行加载
	private java.lang.String cs_name;//参数名称
	private java.lang.String cs_value;//参数值
	private java.lang.String cs_desc;//参数描述

   /**
	* 获取 参数代码
	* @return
	*/
	public java.lang.String getCs_code() {
		return cs_code;
	}
	
   /**
	* 设置 参数代码
	* @param cs_code
	*/
 	public void setCs_code(java.lang.String cs_code) {
		this.cs_code = cs_code;
	}
   /**
	* 获取 参数类型
	* @return
	*/
	public java.lang.String getCs_type() {
		return cs_type;
	}
	
   /**
	* 设置 参数类型
	* @param cs_type
	*/
 	public void setCs_type(java.lang.String cs_type) {
		this.cs_type = cs_type;
	}
   /**
	* 获取 参数类型项下的子类值,应用可依次进行加载
	* @return
	*/
	public java.lang.String getCs_sub_type() {
		return cs_sub_type;
	}
	
   /**
	* 设置 参数类型项下的子类值,应用可依次进行加载
	* @param cs_sub_type
	*/
 	public void setCs_sub_type(java.lang.String cs_sub_type) {
		this.cs_sub_type = cs_sub_type;
	}
   /**
	* 获取 参数名称
	* @return
	*/
	public java.lang.String getCs_name() {
		return cs_name;
	}
	
   /**
	* 设置 参数名称
	* @param cs_name
	*/
 	public void setCs_name(java.lang.String cs_name) {
		this.cs_name = cs_name;
	}
   /**
	* 获取 参数值
	* @return
	*/
	public java.lang.String getCs_value() {
		return cs_value;
	}
	
   /**
	* 设置 参数值
	* @param cs_value
	*/
 	public void setCs_value(java.lang.String cs_value) {
		this.cs_value = cs_value;
	}
   /**
	* 获取 参数描述
	* @return
	*/
	public java.lang.String getCs_desc() {
		return cs_desc;
	}
	
   /**
	* 设置 参数描述
	* @param cs_desc
	*/
 	public void setCs_desc(java.lang.String cs_desc) {
		this.cs_desc = cs_desc;
	}
}
