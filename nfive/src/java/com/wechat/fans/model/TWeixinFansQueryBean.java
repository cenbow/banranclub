package com.wechat.fans.model;

import com.wechat.fans.model.dto.TWeixinFansDto;


/**
 * 类功能:粉丝
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TWeixinFansQueryBean extends TWeixinFansDto{
	
	private java.lang.String     orderStr;//排序字串
    private java.util.Date subscribe_time_start;//关注时间开始
    private java.util.Date subscribe_time_end;//关注时间开始
	private java.lang.String fans_grouplist; //粉丝群，用于接收页面
	private java.lang.String fanslist;   //粉丝  ，用于添加到群操作
	private java.lang.String fans_group; //粉丝群,用于数据库
	private java.lang.String group_name; //
	private java.lang.String[] groupId_list;	//openid所在粉丝群
	private java.lang.String openid_verify;//是否绑定财富账户
	private java.lang.String batch_no;//批次号
	private java.lang.String  maccount_no;//一账通账号
	private java.lang.String  saccount_no;//主账号
	private java.lang.String  fans_group_id;//粉丝群ID
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public java.util.Date getSubscribe_time_start() {
		return subscribe_time_start;
	}

	public void setSubscribe_time_start(java.util.Date subscribeTimeStart) {
		subscribe_time_start = subscribeTimeStart;
	}

	public java.util.Date getSubscribe_time_end() {
		return subscribe_time_end;
	}

	public void setSubscribe_time_end(java.util.Date subscribeTimeEnd) {
		subscribe_time_end = subscribeTimeEnd;
	}

	public java.lang.String getFans_grouplist() {
		return fans_grouplist;
	}

	public void setFans_grouplist(java.lang.String fansGrouplist) {
		fans_grouplist = fansGrouplist;
	}

	public java.lang.String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(java.lang.String groupName) {
		group_name = groupName;
	}

	public java.lang.String getFans_group() {
		return fans_group;
	}

	public void setFans_group(java.lang.String fansGroup) {
		fans_group = fansGroup;
	}

	public java.lang.String getFanslist() {
		return fanslist;
	}

	public void setFanslist(java.lang.String fanslist) {
		this.fanslist = fanslist;
	}

	public java.lang.String[] getGroupId_list() {
		return groupId_list;
	}

	public void setGroupId_list(java.lang.String[] groupIdList) {
		groupId_list = groupIdList;
	}

	public java.lang.String getOpenid_verify() {
		return openid_verify;
	}

	public void setOpenid_verify(java.lang.String openidVerify) {
		openid_verify = openidVerify;
	}

	public java.lang.String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(java.lang.String batchNo) {
		batch_no = batchNo;
	}

	public java.lang.String getFans_group_id() {
		return fans_group_id;
	}

	public void setFans_group_id(java.lang.String fansGroupId) {
		fans_group_id = fansGroupId;
	}

	public java.lang.String getMaccount_no() {
		return maccount_no;
	}

	public void setMaccount_no(java.lang.String maccountNo) {
		maccount_no = maccountNo;
	}

	public java.lang.String getSaccount_no() {
		return saccount_no;
	}

	public void setSaccount_no(java.lang.String saccountNo) {
		saccount_no = saccountNo;
	}
	
}