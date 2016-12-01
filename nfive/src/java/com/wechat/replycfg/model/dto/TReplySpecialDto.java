package com.wechat.replycfg.model.dto;

import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:特殊回复
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
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
@EntityPK(Pk = "freply_id", defaultColumn = false, tableName = "T_REPLY_SPECIAL")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TReplySpecialDto  extends BaseDtoImpl{
	
	private java.lang.String freply_id;//主键id
	private java.lang.String reply_type;//回复类型
	private java.lang.String effect_flag;//生效标志
	private java.lang.String material_id;//消息素材
	private java.lang.String text_msg;//文本内容
	private java.lang.String templet_flag;//启用标志
	private java.lang.String rule_type;//特殊回复规则
	private java.lang.String platform_id;//公众号id
    private java.util.Date pub_startdate;//发布时间
    private java.util.Date pub_enddate;//截止时间
    private java.lang.String cust_srv_flag;//客服模式


   /**
	* 获取主键id 
	* @return
	*/
	public java.lang.String getFreply_id() {
		return freply_id;
	}
	
   /**
	* 设置主键id 
	* @param freply_id
	*/
 	public void setFreply_id(java.lang.String freply_id) {
		this.freply_id = freply_id;
	}
   /**
	* 获取回复类型 
	* @return
	*/
	public java.lang.String getReply_type() {
		return reply_type;
	}
	
   /**
	* 设置回复类型
	* @param reply_type
	*/
 	public void setReply_type(java.lang.String reply_type) {
		this.reply_type = reply_type;
	}
   /**
	* 获取生效标志
	* @return
	*/
	public java.lang.String getEffect_flag() {
		return effect_flag;
	}
	
   /**
	* 设置生效标志
	* @param effect_flag
	*/
 	public void setEffect_flag(java.lang.String effect_flag) {
		this.effect_flag = effect_flag;
	}
   /**
	* 获取消息素材 
	* @return
	*/
	public java.lang.String getMaterial_id() {
		return material_id;
	}
	
   /**
	* 设置消息素材 
	* @param material_id
	*/
 	public void setMaterial_id(java.lang.String material_id) {
		this.material_id = material_id;
	}
   /**
	* 获取文本内容 
	* @return
	*/
	public java.lang.String getText_msg() {
		return text_msg;
	}
	
   /**
	* 设置文本内容 
	* @param text_msg
	*/
 	public void setText_msg(java.lang.String text_msg) {
		this.text_msg = text_msg;
	}
   /**
	* 获取启用标志 
	* @return
	*/
	public java.lang.String getTemplet_flag() {
		return templet_flag;
	}
	
   /**
	* 设置启用标志 
	* @param templet_flag
	*/
 	public void setTemplet_flag(java.lang.String templet_flag) {
		this.templet_flag = templet_flag;
	}
   /**
	* 获取特殊回复规则 
	* @return
	*/
	public java.lang.String getRule_type() {
		return rule_type;
	}
	
   /**
	* 设置特殊回复规则 
	* @param rule_type
	*/
 	public void setRule_type(java.lang.String rule_type) {
		this.rule_type = rule_type;
	}
   /**
	* 获取公众号id 
	* @return
	*/
	public java.lang.String getPlatform_id() {
		return platform_id;
	}
	
   /**
	* 设置公众号id 
	* @param platform_id
	*/
 	public void setPlatform_id(java.lang.String platform_id) {
		this.platform_id = platform_id;
	}
   /**
	* 获取发布时间 
	* @return
	*/
	public java.util.Date getPub_startdate() {
		return pub_startdate;
	}
	
   /**
	* 设置发布时间 
	* @param pub_startdate
	*/
 	public void setPub_startdate(java.util.Date pub_startdate) {
		this.pub_startdate = pub_startdate;
	}
   /**
	* 获取截止时间 
	* @return
	*/
	public java.util.Date getPub_enddate() {
		return pub_enddate;
	}
	
   /**
	* 设置截止时间 
	* @param pub_enddate
	*/
 	public void setPub_enddate(java.util.Date pub_enddate) {
		this.pub_enddate = pub_enddate;
	}
 	
    /**
     * 获取 客服模式
     * @return cust_srv_flag
     */
     public java.lang.String getCust_srv_flag() {
         return cust_srv_flag;
     }
     /**
     * 设置 客服模式
     * @param cust_srv_flag
     */
     public void setCust_srv_flag(java.lang.String cust_srv_flag) {
         this.cust_srv_flag = cust_srv_flag;
     }
}
