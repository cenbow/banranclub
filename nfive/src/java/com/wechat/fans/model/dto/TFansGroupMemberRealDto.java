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
@EntityPK(Pk = "real_id", defaultColumn = false, tableName = "T_FANS_GROUP_MEMBER_REAL")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TFansGroupMemberRealDto  extends BaseDtoImpl{//
	private java.lang.String real_id;//关系ID
	private java.lang.String fans_group_id;//粉丝群ID
	private java.lang.String fans_id;//粉丝ID
	private java.lang.String member_name;//成员名称
	private java.lang.String star_member;//标星成员[CS:1000]
	private java.lang.String member_memo;//成员备注
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 关系ID
	* @return
	*/
	public java.lang.String getReal_id() {
		return real_id;
	}
	
   /**
	* 设置 关系ID
	* @param real_id
	*/
 	public void setReal_id(java.lang.String real_id) {
		this.real_id = real_id;
	}
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
	* 获取 成员名称
	* @return
	*/
	public java.lang.String getMember_name() {
		return member_name;
	}
	
   /**
	* 设置 成员名称
	* @param member_name
	*/
 	public void setMember_name(java.lang.String member_name) {
		this.member_name = member_name;
	}
   /**
	* 获取 标星成员[CS:1000]
	* @return
	*/
	public java.lang.String getStar_member() {
		return star_member;
	}
	
   /**
	* 设置 标星成员[CS:1000]
	* @param star_member
	*/
 	public void setStar_member(java.lang.String star_member) {
		this.star_member = star_member;
	}
   /**
	* 获取 成员备注
	* @return
	*/
	public java.lang.String getMember_memo() {
		return member_memo;
	}
	
   /**
	* 设置 成员备注
	* @param member_memo
	*/
 	public void setMember_memo(java.lang.String member_memo) {
		this.member_memo = member_memo;
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
