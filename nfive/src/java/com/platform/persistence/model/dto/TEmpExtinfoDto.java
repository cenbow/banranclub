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
@EntityPK(Pk = "ext_id", defaultColumn = false, tableName = "T_EMP_EXTINFO")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TEmpExtinfoDto  extends BaseDtoImpl{//
	private java.lang.String ext_id;//扩展ID
	private java.lang.String user_cd;//员工用户CD
	private java.lang.String ip_address;//最近一次登录IP
    private java.util.Date last_login_time;//最近一次登录时间
	private java.math.BigDecimal login_try_times;//连续登录失败次数

   /**
	* 获取 扩展ID
	* @return
	*/
	public java.lang.String getExt_id() {
		return ext_id;
	}
	
   /**
	* 设置 扩展ID
	* @param ext_id
	*/
 	public void setExt_id(java.lang.String ext_id) {
		this.ext_id = ext_id;
	}
   /**
	* 获取 员工用户CD
	* @return
	*/
	public java.lang.String getUser_cd() {
		return user_cd;
	}
	
   /**
	* 设置 员工用户CD
	* @param user_cd
	*/
 	public void setUser_cd(java.lang.String user_cd) {
		this.user_cd = user_cd;
	}
   /**
	* 获取 最近一次登录IP
	* @return
	*/
	public java.lang.String getIp_address() {
		return ip_address;
	}
	
   /**
	* 设置 最近一次登录IP
	* @param ip_address
	*/
 	public void setIp_address(java.lang.String ip_address) {
		this.ip_address = ip_address;
	}
   /**
	* 获取 最近一次登录时间
	* @return
	*/
	public java.util.Date getLast_login_time() {
		return last_login_time;
	}
	
   /**
	* 设置 最近一次登录时间
	* @param last_login_time
	*/
 	public void setLast_login_time(java.util.Date last_login_time) {
		this.last_login_time = last_login_time;
	}
   /**
	* 获取 连续登录失败次数
	* @return
	*/
	public java.math.BigDecimal getLogin_try_times() {
		return login_try_times;
	}
	
   /**
	* 设置 连续登录失败次数
	* @param login_try_times
	*/
 	public void setLogin_try_times(java.math.BigDecimal login_try_times) {
		this.login_try_times = login_try_times;
	}
}
