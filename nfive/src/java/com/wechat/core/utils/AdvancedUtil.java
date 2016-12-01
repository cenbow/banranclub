package com.wechat.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.properties.UtilConfig;
import com.wechat.core.beans.message.resp.Article;
import com.wechat.core.beans.message.resp.Music;
import com.wechat.core.beans.message.resp.Video;
import com.wechat.core.beans.other.MassMessageResult;
import com.wechat.core.beans.other.SNSUserInfo;
import com.wechat.core.beans.other.WeixinGroup;
import com.wechat.core.beans.other.WeixinMedia;
import com.wechat.core.beans.other.WeixinOauth2Token;
import com.wechat.core.beans.other.WeixinQRCode;
import com.wechat.core.beans.other.WeixinUserInfo;
import com.wechat.core.beans.other.WeixinUserList;

/**
 * TODO 高级接口工具类
 */
public class AdvancedUtil {
	private static final Logger logger =Logger.getLogger(AdvancedUtil.class);

	/**
	 * 组装文本消息-客服
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}

	/**
	 * 组装图片消息-客服
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装语音消息-客服
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVoiceCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}

	/**
	 * 组装视频消息-客服
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param video
	 *            视频对象
	 * @return
	 */
	public static String makeVideoCustomMessage(String openId, Video video) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}}";
		return String.format(jsonMsg, openId, video.getMediaId(), video
				.getThumbMediaId(), "", null == video.getDescription() ? ""
				: video.getDescription());
	}

	/**
	 * 组装音乐客服消息
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param music
	 *            音乐对象
	 * @return
	 */
	public static String makeMusicCustomMessage(String openId, Music music) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
		jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music)
				.toString());
		// 将jsonMsg中的thumbmediaid替换为thumb_media_id
		jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
		return jsonMsg;
	}

	/**
	 * 组装图文消息-客服
	 * 
	 * @param openId
	 *            消息发送对象
	 * @param articleList
	 *            图文消息列表
	 * @return
	 */
	public static String makeNewsCustomMessage(String openId,
			List<Article> articleList) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(
				articleList).toString().replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}

	/**
	 * 发送客服消息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return
	 * @throws Exception
	 */
	public static void sendCustomMessage(String accessToken, String jsonMsg)
			throws Exception {
		System.out.println("accessToken：{" + accessToken + "}");
		System.out.println("消息内容：{" + jsonMsg + "}");
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);
		Logger.getLogger(AdvancedUtil.class).info(jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 != errorCode) {
				System.out.println("客服消息发送失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");

				throw new Exception("客服消息发送失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取网页授权凭证失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return wat;
	}

	/**
	 * 刷新网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,
			String refreshToken) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权凭证
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("刷新网页授权凭证失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return wat;
	}

	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings( { "deprecation", "unchecked" })
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 设置语言
				snsUserInfo.setLanguage(jsonObject.getString("language"));
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject
						.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取用户信息失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}",e);
			}
		}
		return snsUserInfo;
	}

	/**
	 * 创建临时带参二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param expireSeconds
	 *            二维码有效时间，单位为秒，最大不超过1800
	 * @param sceneId
	 *            场景ID
	 * @return weixinQRCode
	 */
	public static WeixinQRCode createTemporaryQRCode(String accessToken,
			int expireSeconds, int sceneId) {
		WeixinQRCode weixinQRCode = new WeixinQRCode();
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建临时带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, expireSeconds, sceneId));

		if (null != jsonObject) {
			try {
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject
						.getInt("expire_seconds"));
				weixinQRCode.setUrl(jsonObject.getString("url"));

				System.out.println("创建临时带参二维码成功 ticket:{"
						+ weixinQRCode.getTicket() + "} expire_seconds:{"
						+ weixinQRCode.getExpireSeconds() + "}");
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");

				System.out.println("创建临时带参二维码失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return weixinQRCode;
	}

	/**
	 * 创建永久带参二维码
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return weixinQRCode
	 * @throws Exception 
	 */
	public static WeixinQRCode createPermanentQRCode(String accessToken, int sceneId) throws Exception {
		WeixinQRCode weixinQRCode = new WeixinQRCode();

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonMsg, sceneId));

		if (null != jsonObject) {
			try {
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setUrl(jsonObject.getString("url"));

				System.out.println("创建永久带参二维码成功 ticket:{"
						+ weixinQRCode.getTicket() + "} url:{"
						+ weixinQRCode.getUrl() + "}");
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");

				System.out.println("创建永久带参二维码失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
				
				throw e;
			}
		}
		return weixinQRCode;
	}

	/**
	 * 创建永久带参二维码
	 * 场景值为1-64长度的字符串
	 * @param accessToken
	 *            接口访问凭证
	 * @param sceneId
	 *            场景ID
	 * @return weixinQRCode
	 * @throws Exception 
	 */
	public static WeixinQRCode createPermanentQRCode(String accessToken,String sceneId) throws Exception {
		WeixinQRCode weixinQRCode = new WeixinQRCode();

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"%s\"}}}";
		// 创建永久带参二维码
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));
		if (null != jsonObject) {
			try {
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setUrl(jsonObject.getString("url"));

				System.out.println("创建永久带参二维码成功 ticket:{"
						+ weixinQRCode.getTicket() + "} url:{"
						+ weixinQRCode.getUrl() + "}");
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");

				System.out.println("创建永久带参二维码失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
				
				throw e;
			}
		}
		return weixinQRCode;
	}
	
	/**
	 * 根据ticket换取二维码
	 * 
	 * @param ticket
	 *            二维码ticket
	 * @param savePath
	 *            保存路径
	 * @return filePath
	 */
	public static String getQRCode(String ticket) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET", CommonUtil
				.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			UtilConfig utilConfig = (UtilConfig) SpringContextUtil
					.getBean("utilConfig");
			// 将ticket作为文件名
			filePath = utilConfig.getPub_file_path() + "/qrcode/" + ticket
					+ ".jpg";

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn
					.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
			System.out.println("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			System.out.println("根据ticket换取二维码失败：{}");
			e.printStackTrace();

		}
		return filePath;
	}

	/**
	 * 根据ticket换取二维码
	 * 
	 * @param ticket
	 *            二维码ticket
	 * @param fileName
	 *            二维码文件名
	 * @param savePath
	 *            保存路径
	 * @return filePath
	 * @throws Exception 
	 */
	public static String getQRCode(String ticket, String fileName) throws Exception {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		requestUrl = requestUrl.replace("TICKET", CommonUtil
				.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			UtilConfig utilConfig = (UtilConfig) SpringContextUtil.getBean("utilConfig");
			filePath = utilConfig.getPub_file_path() + "/qrcode/" + fileName;

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
			System.out.println("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			System.out.println("根据ticket换取二维码失败：{}");
			e.printStackTrace();
			throw e;
		}
		return filePath;
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return WeixinUserInfo
	 * @throws Exception
	 */
	public static WeixinUserInfo getUserInfo(String accessToken, String openId)
			throws Exception {
		WeixinUserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				// 用户的标识
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				weixinUserInfo.setSubscribeTime(jsonObject
						.getString("subscribe_time"));
				// 昵称
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				// 用户头像
				weixinUserInfo
						.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					System.out.println("用户{" + weixinUserInfo.getOpenId()
							+ "}已取消关注");
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					System.out.println("获取用户信息失败 errcode:{" + errorCode
							+ "} errmsg:{" + errorMsg + "}");

					throw new Exception("获取用户信息失败 errcode:{" + errorCode
							+ "} errmsg:{" + errorMsg + "}");
				}
			}
		}
		return weixinUserInfo;
	}

	/**
	 * 获取关注者列表
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param nextOpenId
	 *            第一个拉取的openId，不填默认从头开始拉取
	 * @return WeixinUserList
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static WeixinUserList getUserList(String accessToken,
			String nextOpenId) throws Exception {
		WeixinUserList weixinUserList = null;

		if (null == nextOpenId)
			nextOpenId = "";

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"NEXT_OPENID", nextOpenId);
		// 获取关注者列表
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				weixinUserList = new WeixinUserList();
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNextOpenId(jsonObject
						.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				if (null != dataObject) {
					// count=0时，结果json中没有data字段
					weixinUserList.setOpenIdList(JSONArray.toList(dataObject
							.getJSONArray("openid"), List.class));
				}

			} catch (JSONException e) {
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取关注者列表失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");

				throw new Exception("获取关注者列表失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return weixinUserList;
	}

	/**
	 * 查询分组
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public static List<WeixinGroup> getGroups(String accessToken)
			throws Exception {
		List<WeixinGroup> weixinGroupList = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 查询分组
		JSONObject jsonObject = CommonUtil
				.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				weixinGroupList = JSONArray.toList(jsonObject
						.getJSONArray("groups"), WeixinGroup.class);
			} catch (JSONException e) {
				weixinGroupList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("查询分组失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");

				throw new Exception("获取关注者列表失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return weixinGroupList;
	}

	/**
	 * 创建分组
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param groupName
	 *            分组名称
	 * @return
	 */
	public static WeixinGroup createGroup(String accessToken, String groupName) {
		WeixinGroup weixinGroup = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\" : {\"name\" : \"%s\"}}";
		// 创建分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupName));

		if (null != jsonObject) {
			try {
				weixinGroup = new WeixinGroup();
				weixinGroup.setId(jsonObject.getJSONObject("group")
						.getInt("id"));
				weixinGroup.setName(jsonObject.getJSONObject("group")
						.getString("name"));
			} catch (JSONException e) {
				weixinGroup = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("创建分组失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return weixinGroup;
	}

	/**
	 * 修改分组名
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param groupId
	 *            分组id
	 * @param groupName
	 *            修改后的分组名
	 * @return true | false
	 */
	public static boolean updateGroup(String accessToken, int groupId,
			String groupName) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
		// 修改分组名
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, groupId, groupName));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				System.out.println("修改分组名成功 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			} else {
				System.out.println("修改分组名失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return result;
	}

	/**
	 * 移动用户分组
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @param groupId
	 *            分组id
	 * @return true | false
	 */
	public static boolean updateMemberGroup(String accessToken, String openId,
			int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
		// 移动用户分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, openId, groupId));

		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				System.out.println("移动用户分组成功 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			} else {

				System.out.println("移动用户分组失败 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}
		return result;
	}

	/**
	 * 查询用户所在分组
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return groupId 分组id
	 * @throws Exception
	 */
	public static int getGroupIdByUser(String accessToken, String openId)
			throws Exception {
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":\"%s\"}";
		// 查询用户所在分组
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				String.format(jsonData, openId));

		if (null != jsonObject) {
			try {
				return jsonObject.getInt("groupid");
			} catch (JSONException e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("查询用户所在分组 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");

				throw new Exception("查询用户所在分组 errcode:{" + errorCode
						+ "} errmsg:{" + errorMsg + "}");
			}
		}

		return -1;
	}

	/**
	 * 上传媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param type
	 *            媒体文件类型（image、voice、video和thumb）
	 * @param mediaFileUrl
	 *            媒体文件的url
	 */
	public static WeixinMedia uploadMedia(String accessToken, String type,
			String mediaFileUrl) {
		WeixinMedia weixinMedia = null;
		// 拼装请求地址
		String uploadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken)
				.replace("TYPE", type);

		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl
					.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();

			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl
					.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");

			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = CommonUtil.getFileExt(contentType);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream
					.write(String
							.format(
									"Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n",
									fileExt).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n",
					contentType).getBytes());

			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn
					.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			outputStream.close();
			bis.close();
			meidaConn.disconnect();

			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();

			// 使用JSON-lib解析返回结果
			JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
			weixinMedia = new WeixinMedia();
			weixinMedia.setType(jsonObject.getString("type"));
			// type等于thumb时的返回结果和其它类型不一样
			if ("thumb".equals(type))
				weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
			else
				weixinMedia.setMediaId(jsonObject.getString("media_id"));
			weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
		} catch (Exception e) {
			weixinMedia = null;
			e.printStackTrace();
			System.out.println("上传媒体文件失败：{}");
		}
		return weixinMedia;
	}

	/**
	 * 下载媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param mediaId
	 *            媒体文件标识
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * @return
	 */
	public static String getMedia(String accessToken, String mediaId,
			String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"MEDIA_ID", mediaId);
		System.out.println(requestUrl);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = CommonUtil.getFileExt(conn
					.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;

			BufferedInputStream bis = new BufferedInputStream(conn
					.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
			System.out.println("下载媒体文件成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			e.printStackTrace();
			System.out.println("下载媒体文件失败：{}");
		}
		return filePath;
	}

	/**
	 * 组装图文消息-高级群发-按分组
	 * 
	 * @param groupId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeNewsMessageMassSendAll(String groupId,
			String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\"%s\"}}";
		jsonMsg = String.format(jsonMsg, groupId, mediaId);
		return jsonMsg;
	}

	/**
	 * 组装文本消息-高级群发-按分组
	 * 
	 * @param groupId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String makeTextMessageMassSendAll(String groupId,
			String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, groupId, content);
	}

	/**
	 * 组装语音消息-高级群发-按分组
	 * 
	 * @param groupId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVoiceMessageMassSendAll(String groupId,
			String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, groupId, mediaId);
	}

	/**
	 * 组装图片消息-高级群发-按分组
	 * 
	 * @param groupId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeImageMessageMassSendAll(String groupId,
			String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, groupId, mediaId);
	}

	/**
	 * 组装视频消息-高级群发-按分组
	 * 
	 * @param groupId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVideoMessageMassSendAll(String groupId,
			String mediaId) {
		String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"msgtype\":\"mpvideo\",\"mpvideo\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, groupId, mediaId);
	}

	/**
	 * 高级群发接口-按分组
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息
	 * 
	 * @return MassMessageResult
	 * @throws Exception
	 */
	public static MassMessageResult sendMessageMassSendAll(String accessToken,
			String jsonMsg) throws Exception {
		System.out.println("消息内容：{" + jsonMsg + "}");
		MassMessageResult result = new MassMessageResult();

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		// 发送消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);
		result.setErrcode(jsonObject.getString("errcode"));
		result.setErrmsg(jsonObject.getString("errmsg"));
		if (jsonObject.containsKey("msg_id")) {
			result.setMsg_id(jsonObject.getString("msg_id"));
		}
		Logger.getLogger(AdvancedUtil.class).info(jsonMsg);
		return result;
	}

	/**
	 * 组装图文消息-高级群发-按OpenID列表
	 * 
	 * @param openIds
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeNewsMessageMassSend(String[] openIds,
			String mediaId) {
		String jsonMsg = "{\"touser\":%s,\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\"%s\"}}";
		jsonMsg = String.format(jsonMsg, JSONArray.fromObject(openIds)
				.toString(), mediaId);
		return jsonMsg;
	}

	/**
	 * 组装文本消息-高级群发-按OpenID列表
	 * 
	 * @param openIds
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public static String makeTextMessageMassSend(String[] openIds,
			String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":%s,\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, JSONArray.fromObject(openIds).toString(),
				content);
	}

	/**
	 * 组装语音消息-高级群发-按OpenID列表
	 * 
	 * @param openIds
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVoiceMessageMassSend(String[] openIds,
			String mediaId) {
		String jsonMsg = "{\"touser\":%s,\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, JSONArray.fromObject(openIds).toString(),
				mediaId);
	}

	/**
	 * 组装图片消息-高级群发-按OpenID列表
	 * 
	 * @param openIds
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeImageMessageMassSend(String[] openIds,
			String mediaId) {
		String jsonMsg = "{\"touser\":%s,\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, JSONArray.fromObject(openIds).toString(),
				mediaId);
	}

	/**
	 * 组装视频消息-高级群发-按OpenID列表
	 * 
	 * @param openIds
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public static String makeVideoMessageMassSend(String[] openIds, Video video) {
		// TODO 微信坑爹，接口中参数video 替换为mpvideo，后续出现问题将mpvideo修改为video
		String jsonMsg = "{\"touser\":%s,\"msgtype\":\"mpvideo\",\"mpvideo\":{\"media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}}";
		return String.format(jsonMsg, JSONArray.fromObject(openIds).toString(),
				video.getMediaId(), "", null == video.getDescription() ? ""
						: video.getDescription());
	}

	/**
	 * 高级群发接口-OpenID列表
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的客服消息
	 * 
	 * @return MassMessageResult
	 * @throws Exception
	 */
	public static MassMessageResult sendMessageMassSend(String accessToken,
			String jsonMsg) throws Exception {
		System.out.println("消息内容：{" + jsonMsg + "}");
		MassMessageResult result = new MassMessageResult();

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		// 发送客服消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);
		result.setErrcode(jsonObject.getString("errcode"));
		result.setErrmsg(jsonObject.getString("errmsg"));
		if (jsonObject.containsKey("msg_id")) {
			result.setMsg_id(jsonObject.getString("msg_id"));
		}
		Logger.getLogger(AdvancedUtil.class).info(jsonMsg);
		return result;
	}

	/**
	 * 模板消息接口
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式的模板消息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void sendMessageTemplate(String accessToken, String jsonMsg)
			throws Exception {
		System.out.println("消息内容：{" + jsonMsg + "}");

		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);

		// 发送模板消息
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST",
				jsonMsg);

		System.out.println(jsonObject.toString());

	}
}
