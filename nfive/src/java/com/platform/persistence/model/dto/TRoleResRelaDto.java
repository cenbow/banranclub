package com.platform.persistence.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:角色资源关系bean
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
@EntityPK(Pk = "role_res_id", defaultColumn = false, tableName = "T_ROLE_RES_RELA")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TRoleResRelaDto  extends BaseDtoImpl{//
	private java.lang.String role_res_id;//角色资源关系ID
	private java.lang.String role_id;//角色ID
	private java.lang.String res_id;//资源ID

   /**
	* 获取 角色资源关系ID
	* @return
	*/
	public java.lang.String getRole_res_id() {
		return role_res_id;
	}
	
   /**
	* 设置 角色资源关系ID
	* @param role_res_id
	*/
 	public void setRole_res_id(java.lang.String role_res_id) {
		this.role_res_id = role_res_id;
	}
   /**
	* 获取 角色ID
	* @return
	*/
	public java.lang.String getRole_id() {
		return role_id;
	}
	
   /**
	* 设置 角色ID
	* @param role_id
	*/
 	public void setRole_id(java.lang.String role_id) {
		this.role_id = role_id;
	}
   /**
	* 获取 资源ID
	* @return
	*/
	public java.lang.String getRes_id() {
		return res_id;
	}
	
   /**
	* 设置 资源ID
	* @param res_id
	*/
 	public void setRes_id(java.lang.String res_id) {
		this.res_id = res_id;
	}
}
