package com.platform.persistence.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:资源关系bean
 * <p>创建者:zhangzhiqiang</p>
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
@EntityPK(Pk = "res_relation_id", defaultColumn = false, tableName = "T_RES_SELF_RELA")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TResSelfRelaDto  extends BaseDtoImpl{//
	private java.lang.String res_relation_id;//关系id
	private java.lang.String parent_res_id;//父级id
	private java.lang.String child_res_id;//子id
	private java.lang.String res_type;//

   /**
	* 获取 
	* @return
	*/
	public java.lang.String getRes_relation_id() {
		return res_relation_id;
	}
	
   /**
	* 设置 
	* @param res_relation_id
	*/
 	public void setRes_relation_id(java.lang.String res_relation_id) {
		this.res_relation_id = res_relation_id;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getParent_res_id() {
		return parent_res_id;
	}
	
   /**
	* 设置 
	* @param parent_res_id
	*/
 	public void setParent_res_id(java.lang.String parent_res_id) {
		this.parent_res_id = parent_res_id;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getChild_res_id() {
		return child_res_id;
	}
	
   /**
	* 设置 
	* @param child_res_id
	*/
 	public void setChild_res_id(java.lang.String child_res_id) {
		this.child_res_id = child_res_id;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getRes_type() {
		return res_type;
	}
	
   /**
	* 设置 
	* @param res_type
	*/
 	public void setRes_type(java.lang.String res_type) {
		this.res_type = res_type;
	}
}
