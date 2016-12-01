package com.wechat.replycfg.model;

import com.wechat.replycfg.model.dto.TReplySpecialDto;

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
 */
public class TReplySpecialQueryBean extends TReplySpecialDto{
	
	private static final long serialVersionUID = 1L;
	
	private java.lang.String orderStr;// 排序字串
	private java.util.Date pub_startdate_start;// 发布时间
	private java.util.Date pub_startdate_end;// 截止时间
	private java.util.Date pub_enddate_start;// 最后更新时间
	private java.util.Date pub_enddate_end;// 发布时间
	private java.util.Date updated_date_start;// 截止时间
	private java.util.Date updated_date_end;// 最后更新时间
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public final java.util.Date getPub_startdate_start() {
		return pub_startdate_start;
	}

	public final void setPub_startdate_start(java.util.Date pubStartdateStart) {
		pub_startdate_start = pubStartdateStart;
	}

	public final java.util.Date getPub_startdate_end() {
		return pub_startdate_end;
	}

	public final void setPub_startdate_end(java.util.Date pubStartdateEnd) {
		pub_startdate_end = pubStartdateEnd;
	}

	public final java.util.Date getPub_enddate_start() {
		return pub_enddate_start;
	}

	public final void setPub_enddate_start(java.util.Date pubEnddateStart) {
		pub_enddate_start = pubEnddateStart;
	}

	public final java.util.Date getPub_enddate_end() {
		return pub_enddate_end;
	}

	public final void setPub_enddate_end(java.util.Date pubEnddateEnd) {
		pub_enddate_end = pubEnddateEnd;
	}

	public final java.util.Date getUpdated_date_start() {
		return updated_date_start;
	}

	public final void setUpdated_date_start(java.util.Date updatedDateStart) {
		updated_date_start = updatedDateStart;
	}

	public final java.util.Date getUpdated_date_end() {
		return updated_date_end;
	}

	public final void setUpdated_date_end(java.util.Date updatedDateEnd) {
		updated_date_end = updatedDateEnd;
	}


}