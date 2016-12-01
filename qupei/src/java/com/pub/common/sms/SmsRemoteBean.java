package com.pub.common.sms;

/**
 * 发送短信请求对象
 *
 * @author wen.zhang
 */
public class SmsRemoteBean {

	private String mobile; // 手机号码
	private String content;// 短信内容
	private String buss_class;// 业务类型
	private String priority;// 优先级
	private String is_obtain;// 是否必达
	private String is_reply;// 是否需要回复
	private String inner_sms_code;// 内部参考号
	private String is_voice_sms;// 是否语音短信
	private String is_sum_sms;// 是否群发短信
	private String batch_code;// 批次号
	private String activity_code;// 活动ID
	private String activity_name;// 活动名
	private String is_disturb;// 是否免打扰
	private String automatic_reply;// 是否自动回复
	private String reply_content;// 自动回复内容
	private String remarks;// 备用字段
	private String bi_key;// 能唯一识别客户的标识
	private String remarks1;// 备用字段1
	private String remarks2;// 备用字段2
	private String remarks3;// 备用字段3

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBuss_class() {
		return buss_class;
	}

	public void setBuss_class(String bussClass) {
		buss_class = bussClass;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getIs_obtain() {
		return is_obtain;
	}

	public void setIs_obtain(String isObtain) {
		is_obtain = isObtain;
	}

	public String getIs_reply() {
		return is_reply;
	}

	public void setIs_reply(String isReply) {
		is_reply = isReply;
	}

	public String getInner_sms_code() {
		return inner_sms_code;
	}

	public void setInner_sms_code(String innerSmsCode) {
		inner_sms_code = innerSmsCode;
	}

	public String getIs_voice_sms() {
		return is_voice_sms;
	}

	public void setIs_voice_sms(String isVoiceSms) {
		is_voice_sms = isVoiceSms;
	}

	public String getIs_sum_sms() {
		return is_sum_sms;
	}

	public void setIs_sum_sms(String isSumSms) {
		is_sum_sms = isSumSms;
	}

	public String getBatch_code() {
		return batch_code;
	}

	public void setBatch_code(String batchCode) {
		batch_code = batchCode;
	}

	public String getActivity_code() {
		return activity_code;
	}

	public void setActivity_code(String activityCode) {
		activity_code = activityCode;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activityName) {
		activity_name = activityName;
	}

	public String getIs_disturb() {
		return is_disturb;
	}

	public void setIs_disturb(String isDisturb) {
		is_disturb = isDisturb;
	}

	public String getAutomatic_reply() {
		return automatic_reply;
	}

	public void setAutomatic_reply(String automaticReply) {
		automatic_reply = automaticReply;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String replyContent) {
		reply_content = replyContent;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBi_key() {
		return bi_key;
	}

	public void setBi_key(String biKey) {
		bi_key = biKey;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public String getRemarks3() {
		return remarks3;
	}

	public void setRemarks3(String remarks3) {
		this.remarks3 = remarks3;
	}

}
