package com.wechat.fans.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:微信分组
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-16</p>
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
@EntityPK(Pk = "weixin_group_id", defaultColumn = false, tableName = "T_WEIXIN_GROUP")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWeixinGroupDto  extends BaseDtoImpl{//
	private java.lang.String weixin_group_id;//微信分组ID
	private java.lang.Long group_id;//组ID
	private java.lang.String group_name;//组名称
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 微信分组ID
	* @return
	*/
	public java.lang.String getWeixin_group_id() {
		return weixin_group_id;
	}
	
   /**
	* 设置 微信分组ID
	* @param weixin_group_id
	*/
 	public void setWeixin_group_id(java.lang.String weixin_group_id) {
		this.weixin_group_id = weixin_group_id;
	}
   /**
	* 获取 组ID
	* @return
	*/
	public java.lang.Long getGroup_id() {
		return group_id;
	}
	
   /**
	* 设置 组ID
	* @param group_id
	*/
 	public void setGroup_id(java.lang.Long group_id) {
		this.group_id = group_id;
	}
   /**
	* 获取 组名称
	* @return
	*/
	public java.lang.String getGroup_name() {
		return group_name;
	}
	
   /**
	* 设置 组名称
	* @param group_name
	*/
 	public void setGroup_name(java.lang.String group_name) {
		this.group_name = group_name;
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
