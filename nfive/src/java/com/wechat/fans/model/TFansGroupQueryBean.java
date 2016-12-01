package com.wechat.fans.model;
import com.wechat.fans.model.dto.TFansGroupDto;

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
public class TFansGroupQueryBean extends TFansGroupDto{
	
	private java.lang.String     orderStr;//排序字串
	
	
	private java.util.Date cxstartdate;
	private java.util.Date cxenddate;
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public java.util.Date getCxstartdate() {
		return cxstartdate;
	}

	public void setCxstartdate(java.util.Date cxstartdate) {
		this.cxstartdate = cxstartdate;
	}

	public java.util.Date getCxenddate() {
		return cxenddate;
	}

	public void setCxenddate(java.util.Date cxenddate) {
		this.cxenddate = cxenddate;
	}

	
}