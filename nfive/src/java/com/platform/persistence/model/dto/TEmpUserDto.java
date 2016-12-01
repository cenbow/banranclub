package com.platform.persistence.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:csr员工bean
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
@EntityPK(Pk = "user_id", defaultColumn = false, tableName = "T_EMP_USER")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TEmpUserDto  extends BaseDtoImpl{//
	private java.lang.String user_id;//员工用户ID
	private java.lang.String user_cd;//员工用户CD
	private java.lang.String user_name;//用户名
	private java.lang.String password;//用户密码
	private java.lang.String mobile;//手机
	private java.lang.String email;//电子邮件
	private java.lang.String lock_flag;//[CS:3000]用户锁定标识

   /**
	* 获取 员工用户ID
	* @return
	*/
	public java.lang.String getUser_id() {
		return user_id;
	}
	
   /**
	* 设置 员工用户ID
	* @param user_id
	*/
 	public void setUser_id(java.lang.String user_id) {
		this.user_id = user_id;
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
	* 获取 用户名
	* @return
	*/
	public java.lang.String getUser_name() {
		return user_name;
	}
	
   /**
	* 设置 用户名
	* @param user_name
	*/
 	public void setUser_name(java.lang.String user_name) {
		this.user_name = user_name;
	}
   /**
	* 获取 用户密码
	* @return
	*/
	public java.lang.String getPassword() {
		return password;
	}
	
   /**
	* 设置 用户密码
	* @param password
	*/
 	public void setPassword(java.lang.String password) {
		this.password = password;
	}
   /**
	* 获取 手机
	* @return
	*/
	public java.lang.String getMobile() {
		return mobile;
	}
	
   /**
	* 设置 手机
	* @param mobile
	*/
 	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}
   /**
	* 获取 电子邮件
	* @return
	*/
	public java.lang.String getEmail() {
		return email;
	}
	
   /**
	* 设置 电子邮件
	* @param email
	*/
 	public void setEmail(java.lang.String email) {
		this.email = email;
	}
   /**
	* 获取 [CS:3000]用户锁定标识
	* @return
	*/
	public java.lang.String getLock_flag() {
		return lock_flag;
	}
	
   /**
	* 设置 [CS:3000]用户锁定标识
	* @param lock_flag
	*/
 	public void setLock_flag(java.lang.String lock_flag) {
		this.lock_flag = lock_flag;
	}
 	
}
