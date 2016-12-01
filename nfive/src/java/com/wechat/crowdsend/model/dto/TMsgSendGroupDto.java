package com.wechat.crowdsend.model.dto;
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
@EntityPK(Pk = "pkid", defaultColumn = false, tableName = "T_MSG_SEND_GROUP")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TMsgSendGroupDto  extends BaseDtoImpl{//
	private java.lang.String pkid;//
	private java.lang.String batch_no;//
	private java.lang.String group_id;//
	private java.lang.String group_name;//冗余字段

   /**
	* 获取 
	* @return
	*/
	public java.lang.String getPkid() {
		return pkid;
	}
	
   /**
	* 设置 
	* @param pkid
	*/
 	public void setPkid(java.lang.String pkid) {
		this.pkid = pkid;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getBatch_no() {
		return batch_no;
	}
	
   /**
	* 设置 
	* @param batch_no
	*/
 	public void setBatch_no(java.lang.String batch_no) {
		this.batch_no = batch_no;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getGroup_id() {
		return group_id;
	}
	
   /**
	* 设置 
	* @param group_id
	*/
 	public void setGroup_id(java.lang.String group_id) {
		this.group_id = group_id;
	}
   /**
	* 获取 冗余字段
	* @return
	*/
	public java.lang.String getGroup_name() {
		return group_name;
	}
	
   /**
	* 设置 冗余字段
	* @param group_name
	*/
 	public void setGroup_name(java.lang.String group_name) {
		this.group_name = group_name;
	}
}
