package com.wechat.replycfg.model.dto;

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
@EntityPK(Pk = "kreply_id", defaultColumn = false, tableName = "T_REPLY_KEYWORD")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TReplyKeywordDto  extends BaseDtoImpl{//
	private java.lang.String kreply_id;//主键ID
	private java.lang.String keyword;//关键字
	private java.lang.String match_type;//匹配模式【CS:5053】
	private java.lang.String reply_type;//回复类型【CS:5052】
	private java.lang.String material_id;//消息素材ID(不同类型关联不同表)
	private java.lang.String text_msg;//文本内容
	private java.lang.String templet_flag;//是否动态模版[CS:1000]
    private java.util.Date pub_startdate;//发布开始日期
    private java.util.Date pub_enddate;//发布截至日期
    private java.lang.String platform_id;//公众账号ID
    private java.lang.String effect_flag;//生效标志
    private java.lang.String cust_srv_flag;//启用客服模式[CS:1000]
    
    

   /**
	* 获取 主键ID
	* @return
	*/
	public java.lang.String getKreply_id() {
		return kreply_id;
	}
	
   /**
	* 设置 主键ID
	* @param kreply_id
	*/
 	public void setKreply_id(java.lang.String kreply_id) {
		this.kreply_id = kreply_id;
	}
   /**
	* 获取 关键字
	* @return
	*/
	public java.lang.String getKeyword() {
		return keyword;
	}
	
   /**
	* 设置 关键字
	* @param keyword
	*/
 	public void setKeyword(java.lang.String keyword) {
		this.keyword = keyword;
	}
   /**
	* 获取 匹配模式【CS:5053】
	* @return
	*/
	public java.lang.String getMatch_type() {
		return match_type;
	}
	
   /**
	* 设置 匹配模式【CS:5053】
	* @param match_type
	*/
 	public void setMatch_type(java.lang.String match_type) {
		this.match_type = match_type;
	}
   /**
	* 获取 回复类型【CS:5052】
	* @return
	*/
	public java.lang.String getReply_type() {
		return reply_type;
	}
	
   /**
	* 设置 回复类型【CS:5052】
	* @param reply_type
	*/
 	public void setReply_type(java.lang.String reply_type) {
		this.reply_type = reply_type;
	}
   /**
	* 获取 消息素材ID(不同类型关联不同表)
	* @return
	*/
	public java.lang.String getMaterial_id() {
		return material_id;
	}
	
   /**
	* 设置 消息素材ID(不同类型关联不同表)
	* @param material_id
	*/
 	public void setMaterial_id(java.lang.String material_id) {
		this.material_id = material_id;
	}
   /**
	* 获取 文本内容
	* @return
	*/
	public java.lang.String getText_msg() {
		return text_msg;
	}
	
   /**
	* 设置 文本内容
	* @param text_msg
	*/
 	public void setText_msg(java.lang.String text_msg) {
		this.text_msg = text_msg;
	}
   /**
	* 获取 是否动态模版[CS:1000]
	* @return
	*/
	public java.lang.String getTemplet_flag() {
		return templet_flag;
	}
	
   /**
	* 设置 是否动态模版[CS:1000]
	* @param templet_flag
	*/
 	public void setTemplet_flag(java.lang.String templet_flag) {
		this.templet_flag = templet_flag;
	}
   /**
	* 获取 发布开始日期
	* @return
	*/
	public java.util.Date getPub_startdate() {
		return pub_startdate;
	}
	
   /**
	* 设置 发布开始日期
	* @param pub_cxstartdate
	*/
 	public void setPub_startdate(java.util.Date pub_startdate) {
		this.pub_startdate = pub_startdate;
	}
   /**
	* 获取 发布截至日期
	* @return
	*/
	public java.util.Date getPub_enddate() {
		return pub_enddate;
	}
	
   /**
	* 设置 发布截至日期
	* @param pub_enddate
	*/
 	public void setPub_enddate(java.util.Date pub_enddate) {
		this.pub_enddate = pub_enddate;
	}

 	/**
	* 获取 公众账号
	* @param pub_enddate
	*/
	public java.lang.String getPlatform_id() {
		return platform_id;
	}
	
	/**
	* 设置 公众账号
	* @param pub_enddate
	*/
	public void setPlatform_id(java.lang.String platformId) {
		platform_id = platformId;
	}

	/**
	*获取生效标志
	* @param pub_enddate
	*/
	public java.lang.String getEffect_flag() {
		return effect_flag;
	}

	/**
	*设置生效标志
	* @param pub_enddate
	*/
	public void setEffect_flag(java.lang.String effectFlag) {
		effect_flag = effectFlag;
	}

	/**
	 * 获取启用客服模式[CS:1000]
	 * @return the cust_srv_flag
	 */
	public java.lang.String getCust_srv_flag() {
		return cust_srv_flag;
	}

	/**
	 * 设置启用客服模式[CS:1000]
	 * @param custSrvFlag the cust_srv_flag to set
	 */
	public void setCust_srv_flag(java.lang.String custSrvFlag) {
		cust_srv_flag = custSrvFlag;
	}
}
