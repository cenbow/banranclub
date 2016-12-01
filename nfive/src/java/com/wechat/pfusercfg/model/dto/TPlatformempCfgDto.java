package com.wechat.pfusercfg.model.dto;
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
@EntityPK(Pk = "cfg_id", defaultColumn = false, tableName = "T_PLATFORMEMP_CFG")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TPlatformempCfgDto  extends BaseDtoImpl{//
	private java.lang.String cfg_id;//配置表ID
	private java.lang.String platform_id;//公众平台号ID
	private java.lang.String user_cd;//员工用户CD
	private java.lang.String is_use;//【CS:1000】是否使用
	private java.lang.String is_valid;//【CS:1000】是否有效

	/**
	* 获取 用户ID
	* @return
	*/

	 public java.lang.String getUser_cd() {
			return user_cd;
		}
	/**
	* 设置用户ID
	* @return
	*/
	public void setUser_cd(java.lang.String userCd) {
			user_cd = userCd;
		}

   /**
	* 获取 配置表ID
	* @return
	*/
	public java.lang.String getCfg_id() {
		return cfg_id;
	}
	
  
/**
	* 设置 配置表ID
	* @param cfg_id
	*/
 	public void setCfg_id(java.lang.String cfg_id) {
		this.cfg_id = cfg_id;
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
 
   /**
	* 获取 【CS:1000】是否使用
	* @return
	*/
	public java.lang.String getIs_use() {
		return is_use;
	}
	
   /**
	* 设置 【CS:1000】是否使用
	* @param is_use
	*/
 	public void setIs_use(java.lang.String is_use) {
		this.is_use = is_use;
	}
   /**
	* 获取 【CS:1000】是否有效
	* @return
	*/
	public java.lang.String getIs_valid() {
		return is_valid;
	}
	
   /**
	* 设置 【CS:1000】是否有效
	* @param is_valid
	*/
 	public void setIs_valid(java.lang.String is_valid) {
		this.is_valid = is_valid;
	}
}
