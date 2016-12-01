package com.wechat.replycfg.model;

import com.wechat.replycfg.model.dto.TReplyKeywordDto;


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
public class TReplyKeywordQueryBean extends TReplyKeywordDto{
	
	private java.lang.String     orderStr;//排序字串
	
	//扩展属性
    private java.util.Date cxstartdate;  //查询开始时间
    
    private java.util.Date cxenddate;  //查询结束时间
    
    private java.lang.String img_name;  //图文名称
    
    private java.lang.String Article_name;  //图文组名称
    
    private java.lang.String platform_name;  //公众号name   （列表页面显示使用）
	
	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}
	
	/**
	* 获取 查询开始日期
	* @param pub_enddate
	*/
	public java.util.Date getCxstartdate() {
		return cxstartdate;
	}
	
	/**
	* 设置 查询开始日期
	* @param pub_enddate
	*/
	public void setCxstartdate(java.util.Date cxstartdate) {
		this.cxstartdate = cxstartdate;
	}
	
	/**
	* 获取 查询结束日期
	* @param pub_enddate
	*/
	public java.util.Date getCxenddate() {
		return cxenddate;
	}
	
	/**
	* 设置 查询开始日期
	* @param pub_enddate
	*/
	public void setCxenddate(java.util.Date cxenddate) {
		this.cxenddate = cxenddate;
	}

	/**
	* 获取查询图片名称
	* @param pub_enddate
	*/
	public java.lang.String getImg_name() {
		return img_name;
	}

	/**
	* 设置 查询图片名称
	* @param pub_enddate
	*/
	public void setImg_name(java.lang.String imgName) {
		img_name = imgName;
	}

	/**
	* 获取 查询图文组名称
	* @param pub_enddate
	*/
	public java.lang.String getArticle_name() {
		return Article_name;
	}

	/**
	* 设置 查询图文组名称
	* @param pub_enddate
	*/
	public void setArticle_name(java.lang.String articleName) {
		Article_name = articleName;
	}

	/**
	* 获取查询公众号名称
	* @param pub_enddate
	*/
	public java.lang.String getPlatform_name() {
		return platform_name;
	}

	/**
	* 设置 查询公众号名称
	* @param pub_enddate
	*/
	public void setPlatform_name(java.lang.String platformName) {
		platform_name = platformName;
	}
	
}