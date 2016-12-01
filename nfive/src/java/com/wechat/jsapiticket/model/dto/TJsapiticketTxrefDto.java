package com.wechat.jsapiticket.model.dto;

import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:自动代码生成模板 DTO模板
 */
@SuppressWarnings("serial")
@EntityPK(Pk = "jsapiticket_id", defaultColumn = false, tableName = "T_JSAPITICKET_TXREF")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TJsapiticketTxrefDto extends BaseDtoImpl {//
	private java.lang.String jsapiticket_id;// 主键
	private java.lang.String platform_id;// 公众平台号ID
	private java.lang.String jsapiticket;// JSAPITICKET
	private java.lang.Long tx_created_time;// 获取时间
	private java.lang.Long tx_expiration_time;// 过期时间

	/**
	 * 获取 主键
	 * 
	 * @return
	 */
	public java.lang.String getJsapiticket_id() {
		return jsapiticket_id;
	}

	/**
	 * 设置 主键
	 * 
	 * @param jsapiticket_id
	 */
	public void setJsapiticket_id(java.lang.String jsapiticket_id) {
		this.jsapiticket_id = jsapiticket_id;
	}

	/**
	 * 获取 公众平台号ID
	 * 
	 * @return
	 */
	public java.lang.String getPlatform_id() {
		return platform_id;
	}

	/**
	 * 设置 公众平台号ID
	 * 
	 * @param platform_id
	 */
	public void setPlatform_id(java.lang.String platform_id) {
		this.platform_id = platform_id;
	}

	/**
	 * 获取 JSAPITICKET
	 * 
	 * @return
	 */
	public java.lang.String getJsapiticket() {
		return jsapiticket;
	}

	/**
	 * 设置 JSAPITICKET
	 * 
	 * @param jsapiticket
	 */
	public void setJsapiticket(java.lang.String jsapiticket) {
		this.jsapiticket = jsapiticket;
	}

	/**
	 * 获取 获取时间
	 * 
	 * @return
	 */
	public java.lang.Long getTx_created_time() {
		return tx_created_time;
	}

	/**
	 * 设置 获取时间
	 * 
	 * @param tx_created_time
	 */
	public void setTx_created_time(java.lang.Long tx_created_time) {
		this.tx_created_time = tx_created_time;
	}

	/**
	 * 获取 过期时间
	 * 
	 * @return
	 */
	public java.lang.Long getTx_expiration_time() {
		return tx_expiration_time;
	}

	/**
	 * 设置 过期时间
	 * 
	 * @param tx_expiration_time
	 */
	public void setTx_expiration_time(java.lang.Long tx_expiration_time) {
		this.tx_expiration_time = tx_expiration_time;
	}
}
