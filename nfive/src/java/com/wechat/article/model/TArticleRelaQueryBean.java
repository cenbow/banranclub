package com.wechat.article.model;
import com.wechat.article.model.dto.TArticleRelaDto;

/**
 * 类功能:图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TArticleRelaQueryBean extends TArticleRelaDto{
	
	private static final long serialVersionUID = 1L;
	private java.lang.String orderStr;//排序字串
	private java.util.Date pub_base_date;//发布基准日期

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
	
	public java.util.Date getPub_base_date() {
		return pub_base_date;
	}

	public void setPub_base_date(java.util.Date pubBaseDate) {
		pub_base_date = pubBaseDate;
	}
}