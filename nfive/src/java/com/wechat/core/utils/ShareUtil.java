package com.wechat.core.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.hercules.common.des.util.ByteUtil;
import com.hercules.common.md5.Md5;

/**
 * 微信分享相关共通工具类。
 * <P>
 * 分享字符串的规则<BR>
 * 每转发一次,增加一个OpenID,直到达到最大的OPENID数。<BR>
 * OpenID会进行加密，签名始终放在最后，签名会针对所有加密后的OPENID进行。<BR>
 * TokenStr的格式如下<BR>
 * [加密的openId1]:[加密的openId2]:[加密的openId3]:[签名]
 * </P>
 * 
 * @author LiXu
 * 
 */
public class ShareUtil {

	private static Logger logger = Logger.getLogger(ShareUtil.class);
	// 分隔字符
	private static final String SEPARATOR = ":";
	// 追踪的最大OPENID数
	private static final int MAX_OPENID_NUM = 3;
	// 签名Key
	private static final String SIGNATURE_KEY = "L1d2B3k4";

	/**
	 * 取得分享字符串。
	 * 
	 * @param shareTokenStrOld
	 *            旧的分享字符串
	 * @param openId
	 *            当前用户的OPENID
	 * @return 当前用户分享字符串
	 * @throws Exception
	 */
	public static String getShareTokenStr(String shareTokenStrOld, String openId) throws Exception {
		// 开始LOG
		logger.info("getShareTokenStr [" + shareTokenStrOld + "," + openId + "] start");
		// 如果没有OpenId，则直接使用原来的分享字符串
		if (openId == null || openId.length() == 0) {
			return shareTokenStrOld;
		}
		// 如果已经不需要扩展，就直接返回
		if (!needExtendToken(shareTokenStrOld)) {
			return shareTokenStrOld;
		}
		// Token用
		StringBuffer sbToken = new StringBuffer();
		// 签名用
		StringBuffer sbSignature = new StringBuffer();

		// 从旧的分享字符串中取得之前的OPENID信息
		if (shareTokenStrOld != null && shareTokenStrOld.length() != 0) {
			String[] strList = shareTokenStrOld.split(SEPARATOR);
			for (int i = 0; i < strList.length - 1; i++) {
				sbToken.append(strList[i]);
				sbToken.append(SEPARATOR);
				// 签名用
				sbSignature.append(strList[i]);
				sbSignature.append(SIGNATURE_KEY);
			}
		}
		// 添加当前用户的OPENID信息
		String openIdEncrypted = openId2EncryptedStr(openId);
		sbToken.append(openIdEncrypted);
		sbToken.append(SEPARATOR);
		sbSignature.append(openIdEncrypted);
		sbSignature.append(SIGNATURE_KEY);

		// 取得签名
		String signature = getSignature(sbSignature.toString());
		sbToken.append(signature);

		String shareTokenNewStr = sbToken.toString();
		// 终了LOG
		logger.info("getShareTokenStr [=" + shareTokenNewStr + "] end");

		return shareTokenNewStr;
	}

	/**
	 * 把分享字符串转换成OpenId的数组。
	 * 
	 * @param shareTokenStr
	 *            当前的分享字符串
	 * @return OpenId的数组
	 */
	public static String[] getOpenIdListFromTokenStr(String shareTokenStr) throws Exception {
		// 开始LOG
		logger.info("getOpenIdListFromTokenStr [" + shareTokenStr + "] start");

		// 空则返回NULL
		if (shareTokenStr == null || shareTokenStr.length() == 0) {
			return null;
		}
		// 不正确Token返回NULL
		if (!isValidateTokenStr(shareTokenStr)) {
			return null;
		}
		// 循环解析OpenId
		String[] strList = shareTokenStr.split(SEPARATOR);
		String[] openIdList = new String[strList.length - 1];
		for (int i = 0; i < strList.length - 1; i++) {
			openIdList[i] = encryptedStr2OpenId(strList[i]);
		}

		// 终了LOG
		logger.info("getOpenIdListFromTokenStr [=" + openIdList + "] end");

		return openIdList;
	}

	/**
	 * 判断指定的分享字符串是否合法。
	 * 
	 * @param shareTokenStr
	 *            要判断的分享字符串
	 * @return 合法返回True，不合法返回False
	 */
	public static boolean isValidateTokenStr(String shareTokenStr) {
		// 开始LOG
		logger.info("isValidateTokenStr [" + shareTokenStr + "] start");

		boolean isValidate = false;
		// 空字符串
		if (shareTokenStr == null || shareTokenStr.length() == 0) {
			isValidate = true;
		} else {
			// 分享字符串是否有值或超过最大OPENID数
			String[] strList = shareTokenStr.split(SEPARATOR);
			if (strList.length < 2 || strList.length - 1 > MAX_OPENID_NUM) {
				isValidate = false;
			} else {
				// 判断签名
				isValidate = checkSignature(strList);
			}
		}
		// 终了LOG
		logger.info("isValidateTokenStr [=" + isValidate + "] end");

		return isValidate;
	}

	/**
	 * 判断是否需要扩展分享字符串。
	 * 
	 * @param shareTokenStr
	 *            当前的分享字符串
	 * @return 需要返回True，不需要返回False
	 */
	private static boolean needExtendToken(String shareTokenStr) {
		// 空字符串
		if (shareTokenStr == null || shareTokenStr.length() == 0) {
			return true;
		}
		// 判断分享字符串是否已经达到最大OPENID数
		String[] strList = shareTokenStr.split(SEPARATOR);
		if (strList.length > MAX_OPENID_NUM) {
			return false;
		}
		return true;
	}

	/**
	 * 判断签名是否正确。
	 * 
	 * @param strList
	 *            由分享字符串拆分出来的字符串数组
	 * @return 正确返回True，不正确返回False
	 */
	private static boolean checkSignature(String[] strList) {
		// 取得当前签名
		String signature = strList[strList.length - 1];
		// 生成正确签名
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strList.length - 1; i++) {
			sb.append(strList[i]);
			sb.append(SIGNATURE_KEY);
		}
		String signatureValidate;
		try {
			signatureValidate = getSignature(sb.toString());
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		// 判断是否一致
		return signature.equals(signatureValidate);
	}

	/**
	 * 把OPENID转换成加密OPENID
	 * 
	 * @param openId
	 *            未加密OPENID
	 * @return 加密OPENID
	 * @throws Exception
	 */
	private static String openId2EncryptedStr(String openId) throws Exception {
		byte[] bytes = openId.getBytes();
		String hexStr = ByteUtil.getHexString(bytes);
		return hexStr.toLowerCase();
	}

	/**
	 * 取得分享字符串的签名
	 * 
	 * @param fromStr
	 *            要签名的字符串
	 * @return 签名
	 * @throws Exception
	 */
	private static String getSignature(String fromStr) throws Exception {
		// 使用MD5进行签名
		String signature = Md5.encode(fromStr);
		return signature.toLowerCase();
	}

	/**
	 * 把加密字符串转换成OPENID
	 * 
	 * @param encryptedStr
	 *            加密后的OPENID
	 * @return 未加密的OPENID
	 * @throws IOException
	 */
	private static String encryptedStr2OpenId(String encryptedStr) throws IOException {
		byte[] bytes = ByteUtil.hex2Bytes(encryptedStr.toUpperCase());
		String openId = new String(bytes);
		return openId;
	}

	public static void main(String[] args) throws Exception {
		String openId = "ohHIduLancjApt4eSfF_bIVFw558";
		String openId2 = "ohHIduLancjApt4eddF_bIVFw559";
		String openId3 = "ohHIduLancjApt4essF_bIVFw560";
		String openId4 = "ohHIduLancjApt22ssF_bIVFw522";
		String shareTokenStr = ShareUtil.getShareTokenStr("", openId);
		System.out.println(shareTokenStr);
		String shareTokenStr2 = ShareUtil.getShareTokenStr(shareTokenStr, openId2);
		System.out.println(shareTokenStr2);
		String shareTokenStr3 = ShareUtil.getShareTokenStr(shareTokenStr2, openId3);
		System.out.println(shareTokenStr3);
		String shareTokenStr4 = ShareUtil.getShareTokenStr(shareTokenStr3, openId4);
		System.out.println(shareTokenStr4);
		// 解析OPENID
		String[] openIdList = getOpenIdListFromTokenStr(shareTokenStr4);
		for (int i = 0; i < openIdList.length; i++) {
			System.out.println(openIdList[i]);
		}
	}
}
