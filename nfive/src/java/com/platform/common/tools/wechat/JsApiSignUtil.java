package com.platform.common.tools.wechat;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * JS-SDK使用权限签名算法
 * 
 * @author wen.zhang
 */
@Service("jsApiSignUtil")
public class JsApiSignUtil {

	private static Logger logger = Logger.getLogger(JsApiSignUtil.class);

	/**
	 * JS-SDK签名
	 * 
	 * @param 票据
	 * @param 签名完整URL
	 * @return JsApiSignBean
	 */
	public static JsApiSignBean sign(String jsapiTicket, String url) {
		JsApiSignBean jsApiSignBean = null;

		try {
			String nonceStr = createNonceStr();		// 签名的随机串
			String timestamp = createTimestamp();	// 签名的时间戳

			// 注意这里参数名必须全部小写，且必须有序
			String string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
			logger.info(string1);

			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			String signature = byteToHex(crypt.digest());

			jsApiSignBean = new JsApiSignBean();
//			jsApiSignBean.setAppId(appId);				// 公众号的唯一标识
			jsApiSignBean.setNonceStr(nonceStr);		// 签名的随机串
			jsApiSignBean.setJsapiTicket(jsapiTicket);	// 临时票据
			jsApiSignBean.setTimestamp(timestamp);		// 签名的时间戳
			jsApiSignBean.setUrl(url);					// 签名完整URL（不进行URL转义）
			jsApiSignBean.setSignature(signature);		// SHA1签名

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("SHA-1 签名失败");
		}

		return jsApiSignBean;
	}

	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static String createNonceStr() {
		return UUID.randomUUID().toString();
	}

	public static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	public static void outPrintln(JsApiSignBean jsApiSignBean) {
//		if (jsApiSignBean != null) {
//			System.out.println("***************JS-SDK签名数据***************");
//			System.out.println("appId       ：" + jsApiSignBean.getAppId());			// 公众号的唯一标识
//			System.out.println("nonceStr    ：" + jsApiSignBean.getNonceStr());			// 签名的随机串
//			System.out.println("jsapiTicket ：" + jsApiSignBean.getJsapiTicket());		// 临时票据
//			System.out.println("timestamp   ：" + jsApiSignBean.getTimestamp());		// 签名的时间戳
//			System.out.println("url         ：" + jsApiSignBean.getUrl());				// 签名完整URL（不进行URL转义）
//			System.out.println("signature   ：" + jsApiSignBean.getSignature());		// SHA1签名
//		}
	}

	public static void main(String[] args) {
		String jsapiTicket = "bxLdikRXVbTPdHSM05e5u4RbEYQn7pNQMPrfzl8lJNb1foLDa3HIwI3BRMkQmSO_5F64VFa75uURcq6Uz7QHgA";
		String url = "http://wap.leadbank.com.cn";

		JsApiSignBean jsApiSignBean = sign(jsapiTicket, url);
		outPrintln(jsApiSignBean);
	};

}
