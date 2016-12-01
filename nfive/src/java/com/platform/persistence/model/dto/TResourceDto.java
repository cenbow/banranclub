package com.platform.persistence.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:资源bean
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
@EntityPK(Pk = "res_id", defaultColumn = false, tableName = "T_RESOURCE")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TResourceDto  extends BaseDtoImpl{//
	private java.lang.String res_id;//资源ID
	private java.lang.String res_code;//资源代码
	private java.lang.String res_name;//资源名称
	private java.lang.String res_desc;//资源描述
	private java.lang.String res_url;//资源路径
	private java.lang.String parameter;//资源参数
	private java.lang.String target;//资源目标
	private java.lang.String network;//资源网络
	private java.lang.String res_type;//[CS:3001]资源类型区分
	private java.lang.String func_type;//[CS:3003]功能类型区分

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
   /**
	* 获取 资源代码
	* @return
	*/
	public java.lang.String getRes_code() {
		return res_code;
	}
	
   /**
	* 设置 资源代码
	* @param res_code
	*/
 	public void setRes_code(java.lang.String res_code) {
		this.res_code = res_code;
	}
   /**
	* 获取 资源名称
	* @return
	*/
	public java.lang.String getRes_name() {
		return res_name;
	}
	
   /**
	* 设置 资源名称
	* @param res_name
	*/
 	public void setRes_name(java.lang.String res_name) {
		this.res_name = res_name;
	}
   /**
	* 获取 资源描述
	* @return
	*/
	public java.lang.String getRes_desc() {
		return res_desc;
	}
	
   /**
	* 设置 资源描述
	* @param res_desc
	*/
 	public void setRes_desc(java.lang.String res_desc) {
		this.res_desc = res_desc;
	}
   /**
	* 获取 资源路径
	* @return
	*/
	public java.lang.String getRes_url() {
		return res_url;
	}
	
   /**
	* 设置 资源路径
	* @param res_url
	*/
 	public void setRes_url(java.lang.String res_url) {
		this.res_url = res_url;
	}
   /**
	* 获取 资源参数
	* @return
	*/
	public java.lang.String getParameter() {
		return parameter;
	}
	
   /**
	* 设置 资源参数
	* @param parameter
	*/
 	public void setParameter(java.lang.String parameter) {
		this.parameter = parameter;
	}
   /**
	* 获取 资源目标
	* @return
	*/
	public java.lang.String getTarget() {
		return target;
	}
	
   /**
	* 设置 资源目标
	* @param target
	*/
 	public void setTarget(java.lang.String target) {
		this.target = target;
	}
   /**
	* 获取 资源网络
	* @return
	*/
	public java.lang.String getNetwork() {
		return network;
	}
	
   /**
	* 设置 资源网络
	* @param network
	*/
 	public void setNetwork(java.lang.String network) {
		this.network = network;
	}
   /**
	* 获取 [CS:3001]资源类型区分
	* @return
	*/
	public java.lang.String getRes_type() {
		return res_type;
	}
	
   /**
	* 设置 [CS:3001]资源类型区分
	* @param res_type
	*/
 	public void setRes_type(java.lang.String res_type) {
		this.res_type = res_type;
	}
   /**
	* 获取 [CS:3003]功能类型区分
	* @return
	*/
	public java.lang.String getFunc_type() {
		return func_type;
	}
	
   /**
	* 设置 [CS:3003]功能类型区分
	* @param func_type
	*/
 	public void setFunc_type(java.lang.String func_type) {
		this.func_type = func_type;
	}
}
