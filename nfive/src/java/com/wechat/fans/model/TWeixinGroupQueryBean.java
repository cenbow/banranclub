package com.wechat.fans.model;
import com.wechat.fans.model.dto.TWeixinGroupDto;

/**
 * 类功能:微信组
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.20</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TWeixinGroupQueryBean extends TWeixinGroupDto{
	
	private java.lang.String     orderStr;//排序字串
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
}