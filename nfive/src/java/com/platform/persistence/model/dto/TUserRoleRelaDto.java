package com.platform.persistence.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:用户角色关系bean
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
@EntityPK(Pk = "user_role_id", defaultColumn = false, tableName = "T_USER_ROLE_RELA")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TUserRoleRelaDto  extends BaseDtoImpl{//
	private java.lang.String user_role_id;//用户角色关系ID
	private java.lang.String role_id;//角色ID
	private java.lang.String user_id;//用户ID

   /**
	* 获取 用户角色关系ID
	* @return
	*/
	public java.lang.String getUser_role_id() {
		return user_role_id;
	}
	
   /**
	* 设置 用户角色关系ID
	* @param user_role_id
	*/
 	public void setUser_role_id(java.lang.String user_role_id) {
		this.user_role_id = user_role_id;
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
	* 获取 用户ID
	* @return
	*/
	public java.lang.String getUser_id() {
		return user_id;
	}
	
   /**
	* 设置 用户ID
	* @param user_id
	*/
 	public void setUser_id(java.lang.String user_id) {
		this.user_id = user_id;
	}
}
