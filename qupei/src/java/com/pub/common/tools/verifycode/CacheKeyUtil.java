package com.pub.common.tools.verifycode;

import org.apache.commons.lang.StringUtils;

import com.pub.common.tools.constant.CacheKeyPrefixConstant;

/**
 * 缓存key生成类
 * 
 * @author wen.zhang
 */
public class CacheKeyUtil {

	/**
	 * 图像验证码KEY
	 * 
	 * @param key
	 * @return
	 */
	public static String getAccountImageVerifyCodeKey(String key) {
		return CacheKeyPrefixConstant.ACCOUNT_IMAGE_VERIFYCODE_KEY_PREFIX + StringUtils.trimToEmpty(key);
	}

}
