package com.wechat.fans.model.vo;

import com.wechat.fans.model.dto.TFansGroupMemberRealDto;

/**
 * 类功能:粉丝群
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.17</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TWeixinFansGroupVo extends TFansGroupMemberRealDto{
   

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.lang.String group_name;//群组名称
	private java.lang.String remark;//群组说明

	public java.lang.String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(java.lang.String groupName) {
		group_name = groupName;
	}
	public java.lang.String getRemark() {
		return remark;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}
