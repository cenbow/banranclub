package com.wechat.crowdsend.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:群发管理  群发记录表对象
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
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
@EntityPK(Pk = "msg_id", defaultColumn = false, tableName = "T_MSG_SEND")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TMsgSendDto  extends BaseDtoImpl{//
	private java.lang.String msg_id;//
	private java.lang.String batch_no;//每10000个一个批次号
	private java.lang.String platform_id;//公众平台号ID
	private java.lang.String send_dist;//[CS:5059] 微信群发，自定义群发
	private java.lang.String send_target;//[CS:5060] 全部用户，微信分组，粉丝群，活动组
	private java.lang.String send_if;//[CS:5061] 客服消息接口，高级群发接口
	private java.lang.String msg_type;//[CS:5062] 消息类型
	private java.lang.String weixin_group_id;//微信分组id
	private java.lang.String activity_group_id;//活动组id
	private java.lang.String fans_group_flg;//是否粉丝群
	private java.lang.String text_msg;//文本消息
	private java.lang.String material_id;//素材id
    private java.util.Date send_time;//发送日期
	private java.lang.String send_status;//[CS:5063] 处理中，发送成功，发送失败，审核失败
	private java.lang.Long total_count;//粉丝数（总计）
	private java.lang.Long filter_count;//发送人数（总计）
	private java.lang.Long sent_count;//成功人数（总计）
	private java.lang.Long error_count;//失败人数（总计）
	private java.lang.String remark;//消息备注
	private java.lang.String templet_flag;//是否动态模版
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getMsg_id() {
		return msg_id;
	}
	
   /**
	* 设置 
	* @param msg_id
	*/
 	public void setMsg_id(java.lang.String msg_id) {
		this.msg_id = msg_id;
	}
   /**
	* 获取 每10000个一个批次号
	* @return
	*/
	public java.lang.String getBatch_no() {
		return batch_no;
	}
	
   /**
	* 设置 每10000个一个批次号
	* @param batch_no
	*/
 	public void setBatch_no(java.lang.String batch_no) {
		this.batch_no = batch_no;
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
	* 获取 [CS:5059] 微信群发，自定义群发
	* @return
	*/
	public java.lang.String getSend_dist() {
		return send_dist;
	}
	
   /**
	* 设置 [CS:5059] 微信群发，自定义群发
	* @param send_dist
	*/
 	public void setSend_dist(java.lang.String send_dist) {
		this.send_dist = send_dist;
	}
   /**
	* 获取 [CS:5060] 全部用户，微信分组，粉丝群，活动组
	* @return
	*/
	public java.lang.String getSend_target() {
		return send_target;
	}
	
   /**
	* 设置 [CS:5060] 全部用户，微信分组，粉丝群，活动组
	* @param send_target
	*/
 	public void setSend_target(java.lang.String send_target) {
		this.send_target = send_target;
	}
   /**
	* 获取 [CS:5061] 客服消息接口，高级群发接口
	* @return
	*/
	public java.lang.String getSend_if() {
		return send_if;
	}
	
   /**
	* 设置 [CS:5061] 客服消息接口，高级群发接口
	* @param send_if
	*/
 	public void setSend_if(java.lang.String send_if) {
		this.send_if = send_if;
	}
   /**
	* 获取 [CS:5062] 消息类型
	* @return
	*/
	public java.lang.String getMsg_type() {
		return msg_type;
	}
	
   /**
	* 设置 [CS:5062] 消息类型
	* @param msg_type
	*/
 	public void setMsg_type(java.lang.String msg_type) {
		this.msg_type = msg_type;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getWeixin_group_id() {
		return weixin_group_id;
	}
	
   /**
	* 设置 
	* @param weixin_group_id
	*/
 	public void setWeixin_group_id(java.lang.String weixin_group_id) {
		this.weixin_group_id = weixin_group_id;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getActivity_group_id() {
		return activity_group_id;
	}
	
   /**
	* 设置 
	* @param activity_group_id
	*/
 	public void setActivity_group_id(java.lang.String activity_group_id) {
		this.activity_group_id = activity_group_id;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getFans_group_flg() {
		return fans_group_flg;
	}
	
   /**
	* 设置 
	* @param fans_group_flg
	*/
 	public void setFans_group_flg(java.lang.String fans_group_flg) {
		this.fans_group_flg = fans_group_flg;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getText_msg() {
		return text_msg;
	}
	
   /**
	* 设置 
	* @param text_msg
	*/
 	public void setText_msg(java.lang.String text_msg) {
		this.text_msg = text_msg;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getMaterial_id() {
		return material_id;
	}
	
   /**
	* 设置 
	* @param material_id
	*/
 	public void setMaterial_id(java.lang.String material_id) {
		this.material_id = material_id;
	}
   /**
	* 获取 
	* @return
	*/
	public java.util.Date getSend_time() {
		return send_time;
	}
	
   /**
	* 设置 
	* @param send_time
	*/
 	public void setSend_time(java.util.Date send_time) {
		this.send_time = send_time;
	}
   /**
	* 获取 [CS:5063] 处理中，发送成功，发送失败，审核失败
	* @return
	*/
	public java.lang.String getSend_status() {
		return send_status;
	}
	
   /**
	* 设置 [CS:5063] 处理中，发送成功，发送失败，审核失败
	* @param send_status
	*/
 	public void setSend_status(java.lang.String send_status) {
		this.send_status = send_status;
	}
   /**
	* 获取 粉丝数（总计）
	* @return
	*/
	public java.lang.Long getTotal_count() {
		return total_count;
	}
	
   /**
	* 设置 粉丝数（总计）
	* @param total_count
	*/
 	public void setTotal_count(java.lang.Long total_count) {
		this.total_count = total_count;
	}
   /**
	* 获取 发送人数（总计）
	* @return
	*/
	public java.lang.Long getFilter_count() {
		return filter_count;
	}
	
   /**
	* 设置 发送人数（总计）
	* @param filter_count
	*/
 	public void setFilter_count(java.lang.Long filter_count) {
		this.filter_count = filter_count;
	}
   /**
	* 获取 成功人数（总计）
	* @return
	*/
	public java.lang.Long getSent_count() {
		return sent_count;
	}
	
   /**
	* 设置 成功人数（总计）
	* @param sent_count
	*/
 	public void setSent_count(java.lang.Long sent_count) {
		this.sent_count = sent_count;
	}
   /**
	* 获取 失败人数（总计）
	* @return
	*/
	public java.lang.Long getError_count() {
		return error_count;
	}
	
   /**
	* 设置 失败人数（总计）
	* @param error_count
	*/
 	public void setError_count(java.lang.Long error_count) {
		this.error_count = error_count;
	}
   /**
	* 获取 
	* @return
	*/
	public java.lang.String getRemark() {
		return remark;
	}
	
   /**
	* 设置 
	* @param remark
	*/
 	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

public java.lang.String getTemplet_flag() {
	return templet_flag;
}

public void setTemplet_flag(java.lang.String templetFlag) {
	templet_flag = templetFlag;
}
 	
}
