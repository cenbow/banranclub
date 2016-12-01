package com.wechat.fans.model;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;

/**
 * 类功能:   粉丝群关系QueryBean 
 * <p>创建者:guoyan</p>
 * <p>创建时间:2014-09-17</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TFansGroupMemberRealQueryBean extends TFansGroupMemberRealDto{
	
	private java.lang.String     orderStr;//排序字串
	
	//扩展属性
	private java.lang.String nick_name;//昵称  对应微信粉丝群的昵称
	
	private java.lang.String remark_name;//备注名 对应微信粉丝群的备注名
	
	private java.util.Date in_group_startdate;//查询的入群开始时间
	
	private java.util.Date in_group_enddate;//查询的入群开始时间
	
	private java.lang.String   delsqlStr;//自定义删除方法条件拼接
	
	private java.lang.String subscribe;//是否订阅
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public java.lang.String getNick_name() {
		return nick_name;
	}

	public void setNick_name(java.lang.String nickName) {
		nick_name = nickName;
	}

	public java.lang.String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(java.lang.String remarkName) {
		remark_name = remarkName;
	}

	public java.util.Date getIn_group_startdate() {
		return in_group_startdate;
	}

	public void setIn_group_startdate(java.util.Date inGroupStartdate) {
		in_group_startdate = inGroupStartdate;
	}

	public java.util.Date getIn_group_enddate() {
		return in_group_enddate;
	}

	public void setIn_group_enddate(java.util.Date inGroupEnddate) {
		in_group_enddate = inGroupEnddate;
	}

	public java.lang.String getDelsqlStr() {
		return delsqlStr;
	}

	public void setDelsqlStr(java.lang.String delsqlStr) {
		this.delsqlStr = delsqlStr;
	}

	public java.lang.String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(java.lang.String subscribe) {
		this.subscribe = subscribe;
	}
	
}