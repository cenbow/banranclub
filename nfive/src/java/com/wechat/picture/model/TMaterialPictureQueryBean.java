package com.wechat.picture.model;
import com.wechat.picture.model.dto.TMaterialPictureDto;

/**
 * 类功能:自动代码生成模板   QueryBean 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TMaterialPictureQueryBean extends TMaterialPictureDto{
	
	private java.lang.String     orderStr;//排序字串
	private java.util.Date search_updated_date; //最后更新时间：开始
	private java.util.Date search_updated_date1; //最后更新时间：结束
	
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public java.util.Date getSearch_updated_date() {
		return search_updated_date;
	}

	public void setSearch_updated_date(java.util.Date searchUpdatedDate) {
		search_updated_date = searchUpdatedDate;
	}

	public java.util.Date getSearch_updated_date1() {
		return search_updated_date1;
	}

	public void setSearch_updated_date1(java.util.Date searchUpdatedDate1) {
		search_updated_date1 = searchUpdatedDate1;
	}
	
	
}