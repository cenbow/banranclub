package com.platform.common.tools.wechat;

import org.apache.commons.lang.StringUtils;

import com.platform.common.tools.constant.CacheKeyPrefixConstant;

/**
 * 缓存key生成类
 *
 * @author hardy
 */
public class CacheKeyUtil {


	/** 微信缓存 TOKEN KEY生成 **/
	/**
	 * accessToken key生成方法
	 *
	 * @param platform_id
	 *            公众号
	 * @return
	 */
	public static String getAccessTokenKey(String platform_id) {
		return CacheKeyPrefixConstant.ACCESSTOKEN_CACHE_KEY_PREFIX
				+ StringUtils.trimToEmpty(platform_id);
	}

	/** 微信缓存 TICKET KEY生成 **/
	/**
	 * JsapiTicket key生成方法
	 *
	 * @param platform_id 公众号
	 * @return
	 */
	public static String getJsapiTicketKey(String platform_id) {
		return CacheKeyPrefixConstant.JSAPITICKET_CACHE_KEY_PREFIX
				+ StringUtils.trimToEmpty(platform_id);
	}

	/** 微信缓存 JsApi签名 KEY生成 **/
	/**
	 * JsApi签名 key生成方法
	 *
	 * @param url
	 * @return
	 */
	public static String getWxJsApiSignKey(String url) {
		return CacheKeyPrefixConstant.WXJSAPISIGN_CACHE_KEY_PREFIX
				+ StringUtils.trimToEmpty(url);
	}

	/** 素材缓存KEY生成 **/
	/**
	 * 素材KEY生成方法
	 *
	 * @param material_id
	 *            素材(图片、音频、视频)id
	 * @return
	 */
	public static String getMeterialKey(String material_id) {
		return CacheKeyPrefixConstant.METERIAL_CACHE_KEY_PREFIX
				+ StringUtils.trimToEmpty(material_id);
	}
}
