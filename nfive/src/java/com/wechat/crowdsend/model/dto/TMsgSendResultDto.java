package com.wechat.crowdsend.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:群发管理 发送结果表对象
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
@EntityPK(Pk = "pkid", defaultColumn = false, tableName = "T_MSG_SEND_RESULT")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TMsgSendResultDto  extends BaseDtoImpl{//
	private java.lang.String pkid;//
	private java.lang.String batch_no;//主表内批次号
	private java.lang.String msg_code;//删除群发内容时需要提供（只能删除图文消息和视频消息）
	private java.lang.String err_code;//错误码
	private java.lang.String err_msg;//错误信息
	private java.lang.String send_status;//由【事件推送群发结果】来回写为"send success"或"send fail"或"err(num)"。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败，具体原因......
	private java.lang.Long total_count;//由【事件推送群发结果】来回写group_id下粉丝数；或者openid_list中的粉丝数
	private java.lang.Long filter_count;//由【事件推送群发结果】来回写排除发送时过滤（指特定地区、性别的过滤）、用户设置拒收、用户接收已超4条的过滤后，准备发送的粉丝数，原则上，FILTER_COUNT = SENT_COUNT + ERROR_COUNT
	private java.lang.Long sent_count;//由【事件推送群发结果】来回写发送成功的粉丝数
	private java.lang.Long error_count;//由【事件推送群发结果】来回写发送失败的粉丝数

   /**
	* 获取 
	* @return
	*/
	public java.lang.String getPkid() {
		return pkid;
	}
	
   /**
	* 设置 
	* @param pkid
	*/
 	public void setPkid(java.lang.String pkid) {
		this.pkid = pkid;
	}
   /**
	* 获取 主表内批次号
	* @return
	*/
	public java.lang.String getBatch_no() {
		return batch_no;
	}
	
   /**
	* 设置 主表内批次号
	* @param batch_no
	*/
 	public void setBatch_no(java.lang.String batch_no) {
		this.batch_no = batch_no;
	}
   /**
	* 获取 删除群发内容时需要提供（只能删除图文消息和视频消息）
	* @return
	*/
	public java.lang.String getMsg_code() {
		return msg_code;
	}
	
   /**
	* 设置 删除群发内容时需要提供（只能删除图文消息和视频消息）
	* @param msg_code
	*/
 	public void setMsg_code(java.lang.String msg_code) {
		this.msg_code = msg_code;
	}
   /**
	* 获取 错误码
	* @return
	*/
	public java.lang.String getErr_code() {
		return err_code;
	}
	
   /**
	* 设置 错误码
	* @param err_code
	*/
 	public void setErr_code(java.lang.String err_code) {
		this.err_code = err_code;
	}
   /**
	* 获取 错误信息
	* @return
	*/
	public java.lang.String getErr_msg() {
		return err_msg;
	}
	
   /**
	* 设置 错误信息
	* @param err_msg
	*/
 	public void setErr_msg(java.lang.String err_msg) {
		this.err_msg = err_msg;
	}
   /**
	* 获取 由【事件推送群发结果】来回写为"send success"或"send fail"或"err(num)"。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败，具体原因......
	* @return
	*/
	public java.lang.String getSend_status() {
		return send_status;
	}
	
   /**
	* 设置 由【事件推送群发结果】来回写为"send success"或"send fail"或"err(num)"。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败，具体原因......
	* @param send_status
	*/
 	public void setSend_status(java.lang.String send_status) {
		this.send_status = send_status;
	}
   /**
	* 获取 由【事件推送群发结果】来回写group_id下粉丝数；或者openid_list中的粉丝数
	* @return
	*/
	public java.lang.Long getTotal_count() {
		return total_count;
	}
	
   /**
	* 设置 由【事件推送群发结果】来回写group_id下粉丝数；或者openid_list中的粉丝数
	* @param total_count
	*/
 	public void setTotal_count(java.lang.Long total_count) {
		this.total_count = total_count;
	}
   /**
	* 获取 由【事件推送群发结果】来回写排除发送时过滤（指特定地区、性别的过滤）、用户设置拒收、用户接收已超4条的过滤后，准备发送的粉丝数，原则上，FILTER_COUNT = SENT_COUNT + ERROR_COUNT
	* @return
	*/
	public java.lang.Long getFilter_count() {
		return filter_count;
	}
	
   /**
	* 设置 由【事件推送群发结果】来回写排除发送时过滤（指特定地区、性别的过滤）、用户设置拒收、用户接收已超4条的过滤后，准备发送的粉丝数，原则上，FILTER_COUNT = SENT_COUNT + ERROR_COUNT
	* @param filter_count
	*/
 	public void setFilter_count(java.lang.Long filter_count) {
		this.filter_count = filter_count;
	}
   /**
	* 获取 由【事件推送群发结果】来回写发送成功的粉丝数
	* @return
	*/
	public java.lang.Long getSent_count() {
		return sent_count;
	}
	
   /**
	* 设置 由【事件推送群发结果】来回写发送成功的粉丝数
	* @param sent_count
	*/
 	public void setSent_count(java.lang.Long sent_count) {
		this.sent_count = sent_count;
	}
   /**
	* 获取 由【事件推送群发结果】来回写发送失败的粉丝数
	* @return
	*/
	public java.lang.Long getError_count() {
		return error_count;
	}
	
   /**
	* 设置 由【事件推送群发结果】来回写发送失败的粉丝数
	* @param error_count
	*/
 	public void setError_count(java.lang.Long error_count) {
		this.error_count = error_count;
	}
}
