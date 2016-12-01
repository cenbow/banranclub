package com.platform.common.tools.wechat;

import org.apache.commons.lang.StringUtils;

import com.hercules.cache.CacheUtil;
import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.constant.CacheKeyPrefixConstant;
import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.core.beans.other.WeixinMedia;
import com.wechat.core.utils.MediaUtil;

public class MeterialCacheUtil {
	// MediaUtil bean id
	private static final String MEDIA_BEAN_ID = "mediaUtil";

	/**
	 * 根据缓存素材key(CacheKeyUtil.getMeterialKey方法生成key)，公众平台号，素材类型获取meidiaid
	 * @param material_id
	 *            素材id，如果是缩略图，该值为file_id
	 * @param platform_id
	 *            公众号ID
	 * @param material_type
	 *            素材类型
	 * @return meidiaid
	 * @throws Exception
	 */
	public synchronized static String getMeterialMediaId(
			String cache_material_key, String platform_id, String material_type)
			throws Exception {
		if (StringUtils.isBlank(cache_material_key)
				|| cache_material_key
						.equals(CacheKeyPrefixConstant.METERIAL_CACHE_KEY_PREFIX)) {
			throw new Exception("cache_material_key不能为空!");
		}
		// 1. 获取缓存中的信息
		String mediaId = (String) CacheUtil.get(cache_material_key);
		// 2.1 如果缓存中不存在该素材信息，需要从db中获取素材信息，上传到微信服务器
		if (StringUtils.isBlank(mediaId)) {
			if (StringUtils.isBlank(material_type)) {
				throw new Exception("material_type不能为空!");
			}
			String material_id = cache_material_key
					.substring(CacheKeyPrefixConstant.METERIAL_CACHE_KEY_PREFIX
							.length());
			// 3. 获取media，如果没有，则上传到微信服务器
			WeixinMedia weixinMedia = null;
			if (material_type
					.equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG)) {
				weixinMedia = ((MediaUtil) SpringContextUtil.getBean(MEDIA_BEAN_ID)).uploadMaterialPicture(material_id, platform_id);
			} else if (material_type.equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_VOICE)) {
				weixinMedia = ((MediaUtil) SpringContextUtil.getBean(MEDIA_BEAN_ID)).uploadMaterialVoice(
						material_id, platform_id);
			} else if (material_type
					.equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_VIDEO)) {
				weixinMedia = ((MediaUtil) SpringContextUtil
						.getBean(MEDIA_BEAN_ID)).uploadMaterialVideo(
						material_id, platform_id);
			} else if (material_type
					.equals(CodeStringConstant.CS_5012_MATERIAL_TYPE_THUMB)) {
				weixinMedia = ((MediaUtil) SpringContextUtil
						.getBean(MEDIA_BEAN_ID)).uploadMaterialThumb(
						material_id, platform_id);
			} else {
				throw new Exception("更新素材失败!");
			}
			// 如果需要缓存
			if (CodeStringConstant.CS_1000_TRUE.equals(weixinMedia.getCache_flag())) {
				// 保存到缓存
				CacheUtil.put(cache_material_key, weixinMedia.getMediaId(),
						CacheKeyPrefixConstant.METERIAL_TX_EXPIRATION_TIME);
			}
			return weixinMedia.getMediaId();
		} else {
			return mediaId;
		}
	}
}
