package com.pub.common.local.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:角色bean
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
@EntityPK(Pk = "role_id", defaultColumn = false, tableName = "T_ROLE")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TRoleDto extends BaseDtoImpl{//
	private String role_id;//角色ID
	private String role_name;//角色名称
	private String role_desc;//角色描述
	private String role_group;//角色分组

   /**
	* 获取 角色ID
	* @return
	*/
	public String getRole_id() {
		return role_id;
	}

   /**
	* 设置 角色ID
	* @param role_id
	*/
 	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
   /**
	* 获取 角色名称
	* @return
	*/
	public String getRole_name() {
		return role_name;
	}

   /**
	* 设置 角色名称
	* @param role_name
	*/
 	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
   /**
	* 获取 角色描述
	* @return
	*/
	public String getRole_desc() {
		return role_desc;
	}

   /**
	* 设置 角色描述
	* @param role_desc
	*/
 	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public String getRole_group() {
		return role_group;
	}

	public void setRole_group(String role_group) {
		this.role_group = role_group;
	}

}
