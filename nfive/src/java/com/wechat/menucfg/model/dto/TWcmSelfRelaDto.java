package com.wechat.menucfg.model.dto;
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
@EntityPK(Pk = "menu_relation_id", defaultColumn = false, tableName = "T_WCM_SELF_RELA")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWcmSelfRelaDto  extends BaseDtoImpl{//
	private java.lang.String menu_relation_id;//菜单结构关系ID
	private java.lang.String parent_id;//父菜单ID
	private java.lang.String child_id;//子菜单ID
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 菜单结构关系ID
	* @return
	*/
	public java.lang.String getMenu_relation_id() {
		return menu_relation_id;
	}
	
   /**
	* 设置 菜单结构关系ID
	* @param menu_relation_id
	*/
 	public void setMenu_relation_id(java.lang.String menu_relation_id) {
		this.menu_relation_id = menu_relation_id;
	}
   /**
	* 获取 父菜单ID
	* @return
	*/
	public java.lang.String getParent_id() {
		return parent_id;
	}
	
   /**
	* 设置 父菜单ID
	* @param parent_id
	*/
 	public void setParent_id(java.lang.String parent_id) {
		this.parent_id = parent_id;
	}
   /**
	* 获取 子菜单ID
	* @return
	*/
	public java.lang.String getChild_id() {
		return child_id;
	}
	
   /**
	* 设置 子菜单ID
	* @param child_id
	*/
 	public void setChild_id(java.lang.String child_id) {
		this.child_id = child_id;
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
