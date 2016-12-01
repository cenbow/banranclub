package com.platform.common.tools.wechat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hercules.cache.CacheUtil;
import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.constant.CacheKeyPrefixConstant;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.context.UserContext;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.core.beans.other.Token;
import com.wechat.core.utils.CommonUtil;
import com.wechat.jsapiticket.model.dto.TJsapiticketTxrefDto;
import com.wechat.jsapiticket.service.ITJsapiticketTxrefService;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;

/***
 * 用于系统中微信公众号
 * @author jia.chen
 */
@Service("wechatInfoUtil")
public class WechatInfoUtil{
	// 日志
	private static Logger logger = Logger.getLogger(WechatInfoUtil.class);
	// 存放在SESSION的KEY
	public static final String SESSION_WECHAT_INFO_KEY = "WECHAT$WECHAT_INFO_KEY_1";
	// accessToken db service bean id
	private static final String tTxrefAccesstokenService_BEAN_ID = "tTxrefAccesstokenService";
	// 公众号信息
	private static TPubPlatformDto pubPlatformDto = new TPubPlatformDto();

	@Autowired
	ITPubPlatformService tPubPlatformService;

	/****
	 *spring中定义的初始化方法
	 */
	@SuppressWarnings("unused")
	public void InitConfig() {
		refreshCacheAll();
	}

	/**
	 *刷新全部缓存
	 */
	private void refreshCacheAll() {
		logger.info("===========================start [wechat refresh]===========================");
        //微信公众号
		TPubPlatformDto param = new TPubPlatformDto();
        pubPlatformDto = tPubPlatformService.getRow(param);

		logger.info("===========================end  [wechat refresh] ===========================");
	}

	/**
	 * 检查公众号是否有效(在配置中)
	 *
	 * @param platform_id
	 * @return
	 */
	public static boolean checkPlatformId(String platform_id) {
		if (StringUtils.isEmpty(platform_id)) {
			return false;
		}
		return pubPlatformDto!=null&&platform_id.equals(pubPlatformDto.getPlatform_id());
	}


	/**
	 * 获取微信公众号信息
	 *
	 * @return
	 */
	private static WechatInfo getWechatInfoFromSession() {
		return (WechatInfo) UserContext.getRequest().getSession().getAttribute(
				SESSION_WECHAT_INFO_KEY);
	}

	/**
	 * 从缓存中获取当前公众号配置的详细信息
	 *
	 * @param platform_id
	 * @return TPubPlatformDto
	 */
	public static TPubPlatformDto getTPubPlatformDtoByPlatformId(
			String platform_id) {
		return pubPlatformDto;
	}

	/**
	 * 从登录用户的SESSION获取当前作用的公众号
	 *
	 * @return PubPlatformBean
	 */
	public static PubPlatformBean getCurPubPlatformBeanFromSession() {
		WechatInfo wechatInfo = WechatInfoUtil.getWechatInfoFromSession();
		if (wechatInfo != null) {
			return wechatInfo.getCurPubPlatformBean();
		}
		return null;
	}

	/***
	 * 从登录用户的SESSION获取当前可切换公众号列表
	 *
	 * @return List<PubPlatformBean>
	 */
	public static List<PubPlatformBean> getPubPlatformBeanListFromSession() {
		WechatInfo wechatInfo = WechatInfoUtil.getWechatInfoFromSession();
		if (wechatInfo != null) {
			return wechatInfo.getPubPlatformBeanList();
		}
		return null;
	}

	/**
	 * 重置微信公众号信息 platform_id("SSID20140813083940000272");
	 * wechart_account("leadtest"); platform_name("德晟技术");
	 * appid("wxe8fd050ecf82ac28");
	 * appsecret("cd4f7bcaeef7a479f0914b4b44a07a7b"); token("zhenwudi");
	 */
	public static void resetSessionForCurWechatInfo() {

		logger
				.info("===========================start [codeString refresh]===========================");
		// 静态方法,加载顺序tPubPlatformService未实例化,从容器中取出。
		ITPubPlatformService tPubPlatformServiceTemp = (ITPubPlatformService) SpringContextUtil
				.getBean("tPubPlatformService");

		// 1. 获取当前用户可访问的公众号列表TODO
		List<PubPlatformBean> pubPlatformBeanList = tPubPlatformServiceTemp
				.queryPubPlatformBeanList(LoginUserInfoUtil.getLoginUserCD());

		// 2. 获取当前正在使用的的公众号ID
		String curPlatformId = null;
		if (getCurPubPlatformBeanFromSession() != null) {
			curPlatformId = getCurPubPlatformBeanFromSession().getPlatform_id();
		};
		// 1.若之前没有选择公众号ID 则默认使用数据库默认绑定的公众号
		// 2.若之前已经有选择的公众号ID 则查看是否还存在于可选列表中不存在也为空
		PubPlatformBean curPubPlatformBean = null;
		PubPlatformBean defalutPubPlatformBean = null;

		for (PubPlatformBean tmp_pubPlatformBean : pubPlatformBeanList) {
			if (StringUtils.isNotEmpty(curPlatformId)
					&& tmp_pubPlatformBean.getPlatform_id().equalsIgnoreCase(
							curPlatformId)) {
				curPubPlatformBean = tmp_pubPlatformBean;
			}
			if (CodeStringConstant.CS_1000_TRUE
					.equalsIgnoreCase(tmp_pubPlatformBean.getIs_use())) {
				defalutPubPlatformBean = tmp_pubPlatformBean;
			}
		}
		// 3.若在最后没有默认使用的公众号 且 也没有数据库配置的公众号 则选择第一个公众号为默认公众号
		if (pubPlatformBeanList.size() > 0 && curPubPlatformBean == null
				&& defalutPubPlatformBean == null) {
			defalutPubPlatformBean = pubPlatformBeanList.get(0);
		}

		// 4.重新组装WechatInfo

		WechatInfo wechatInfo = null;
		if (defalutPubPlatformBean != null) {
			wechatInfo = new WechatInfo(defalutPubPlatformBean,
					pubPlatformBeanList);
		} else {
			wechatInfo = new WechatInfo(
					curPubPlatformBean == null ? defalutPubPlatformBean
							: curPubPlatformBean, pubPlatformBeanList);
		}

		// 4.刷新SESSION 内 WechatInfo内容
		UserContext.getRequest().getSession().removeAttribute(
				SESSION_WECHAT_INFO_KEY); // 删除原有内容
		UserContext.getRequest().getSession().setAttribute(
				SESSION_WECHAT_INFO_KEY, wechatInfo);// 添加内容
		logger
				.info("===========================end [codeString refresh]=============================");

	}

	/**
	 * 根据accessTokenKey(该key需要调用CacheKeyUtil工具生成)获取accesstoken
	 * @param accessTokenKey
	 * @return
	 * @throws Exception
	 */
	public synchronized static String getAccessToken(String accessTokenKey) throws Exception{
        //		// 为空或者是tokenkey不能为空
//		if (StringUtils.isBlank(accessTokenKey)||
//			accessTokenKey.equals(CacheKeyPrefixConstant.ACCESSTOKEN_CACHE_KEY_PREFIX)) {
//			throw new Exception("accessTokenKey不能为空!");
//		}
//		//获取公众号id
//		String platform_id=accessTokenKey.substring(CacheKeyPrefixConstant.ACCESSTOKEN_CACHE_KEY_PREFIX.length());
//		if(!checkPlatformId(platform_id)){
//			throw new Exception("公众号信息不存在!");
//		}
//		//从缓存中获取accessToken
//		String accessToken=(String) CacheUtil.get(accessTokenKey);
//		if(StringUtils.isNotBlank(accessToken)){
//			return accessToken;
//			//如果accessToken为空
//		}else{
//			//获取时效表信息
//			ITAccesstokenTxrefService  tTxrefAccesstokenService=(ITAccesstokenTxrefService) SpringContextUtil.getBean(tTxrefAccesstokenService_BEAN_ID);
//			TAccesstokenTxrefDto tTxrefAccesstokenDto=new TAccesstokenTxrefDto();
//			tTxrefAccesstokenDto.setPlatform_id(platform_id);
//			tTxrefAccesstokenDto=tTxrefAccesstokenService.getRow(tTxrefAccesstokenDto);
//
//			long nowTime=System.currentTimeMillis()/1000;
//			//如果时效表信息不存在
//			if(null==tTxrefAccesstokenDto){
//				// 1.调用微信服务器获取accessToken
//				Token token = CommonUtil.getToken(getTPubPlatformDtoByPlatformId(platform_id).getAppid(),
//						                          getTPubPlatformDtoByPlatformId(platform_id).getAppsecret());
//				//ADD BY ZHUBAODING 2015/02/04
//				if(token==null){
//					throw new Exception("WechatInfoUtil#getAccessToken微信服务器链接异常,未取得token!");
//				}
//				//AND END 2015/02/04
//				// 2.保存到数据库
//				tTxrefAccesstokenDto=new TAccesstokenTxrefDto();
//				tTxrefAccesstokenDto.setAccesstoken(token.getAccessToken());
//				tTxrefAccesstokenDto.setPlatform_id(platform_id);
//				tTxrefAccesstokenDto.setTx_created_time(nowTime);
//				tTxrefAccesstokenDto.setTx_expiration_time(nowTime+CacheKeyPrefixConstant.ACCESSTOKEN_TX_EXPIRATION_TIME);
//				tTxrefAccesstokenDto.setCreated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
//				tTxrefAccesstokenDto.setUpdated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
//				try {
//					logger.info("保存新的TOKEN："+token.getAccessToken());
//					tTxrefAccesstokenService.save(tTxrefAccesstokenDto);
//				} catch (Exception e1) {
//					logger.error("------保存数据库Accesstoken异常-------platform_id:"+platform_id, e1);
//				}
//				// 3.放置到缓存中
//				try {
//					logger.info("放入缓存的新TOKEN："+token.getAccessToken());
//					CacheUtil.put(accessTokenKey, token.getAccessToken(),CacheKeyPrefixConstant.ACCESSTOKEN_TX_EXPIRATION_TIME);
//				} catch (Exception e) {
//					logger.error("------缓存Accesstoken异常-------platform_id:"+platform_id, e);
//				}
//			    return token.getAccessToken();
//			}else{
//				Long tx_expiration_time=tTxrefAccesstokenDto.getTx_expiration_time();
//				//如果没有过期，则上传到缓存中
//				if(tx_expiration_time>nowTime){
//					try {
//						logger.info("缓存未过期，上传至缓存："+tTxrefAccesstokenDto.getAccesstoken());
//						CacheUtil.put(accessTokenKey, tTxrefAccesstokenDto.getAccesstoken(), (int)(tx_expiration_time-nowTime));
//					} catch (Exception e) {
//						logger.error("------缓存Accesstoken异常-------platform_id:"+platform_id, e);
//					}
//					return tTxrefAccesstokenDto.getAccesstoken();
//				}else{
//					// 1.调用微信服务器获取accessToken
//					Token token = CommonUtil.getToken(
//							StringUtils.trimToEmpty(getTPubPlatformDtoByPlatformId(platform_id).getAppid()),
//							StringUtils.trimToEmpty(getTPubPlatformDtoByPlatformId(platform_id).getAppsecret()));
//					//ADD BY ZHUBAODING 2015/02/04  BEGIN
//					if (token == null){
//						throw new Exception("WechatInfoUtil#getAccessToken2微信服务器链接异常,未取得accessToken!");
//					}
//					//ADD END 2015/02/04 END
//
//					// 2.更新数据库记录
//					tTxrefAccesstokenDto.setAccesstoken(token.getAccessToken());
//					tTxrefAccesstokenDto.setTx_created_time(nowTime);
//					tTxrefAccesstokenDto.setTx_expiration_time(nowTime+CacheKeyPrefixConstant.ACCESSTOKEN_TX_EXPIRATION_TIME);
//					try {
//						logger.info("TOKEN过期，更新TOKEN内容："+token.getAccessToken());
//						tTxrefAccesstokenService.updatePK(tTxrefAccesstokenDto);
//					} catch (Exception e1) {
//						logger.error("------更新数据库Accesstoken异常-------platform_id:"+platform_id, e1);
//					}
//					// 3.放置到缓存中
//					try {
//						logger.info("TOKEN过期，更新TOKEN内容放入缓存的TOKEN："+token.getAccessToken());
//						CacheUtil.put(accessTokenKey, token.getAccessToken(),CacheKeyPrefixConstant.ACCESSTOKEN_TX_EXPIRATION_TIME);
//					} catch (Exception e) {
//						logger.error("------缓存Accesstoken异常-------platform_id:"+platform_id, e);
//					}
//					return tTxrefAccesstokenDto.getAccesstoken();
//				}
//			}
//		}
        return null;
    }




	/**
	 * 使用AccessToken获取JsapiTicket（有效期7200秒）
	 * jsapi_ticket是公众号用于调用微信JS接口的临时票据
	 *
	 * @param jsapiTicketKey
	 * @return jsapiTicket
	 * @throws Exception
	 */
	public synchronized static String getJsapiTicket(String jsapiTicketKey) throws Exception {

		// Key为空或者不规范
		if (StringUtils.isBlank(jsapiTicketKey)
				|| CacheKeyPrefixConstant.JSAPITICKET_CACHE_KEY_PREFIX.equalsIgnoreCase(StringUtils.trimToEmpty(jsapiTicketKey))) {
			throw new Exception("jsapiTicketKey不能为空或者不规范");
		}

		// 获取公众号ID
		String platform_id = jsapiTicketKey.substring(CacheKeyPrefixConstant.JSAPITICKET_CACHE_KEY_PREFIX.length());
		if (!checkPlatformId(platform_id)) {
			throw new Exception("公众号信息不存在!");
		}

		// 从缓存中获取JsapiTicket
		String jsapiTicket = (String) CacheUtil.get(jsapiTicketKey);

		if (StringUtils.isBlank(jsapiTicket)) {
			long nowTime = System.currentTimeMillis() / 1000;

			try {
				// 获取JSAPITICKET时效表信息
				ITJsapiticketTxrefService jsapiticketTxrefService = (ITJsapiticketTxrefService) SpringContextUtil.getBean("tJsapiticketTxrefService");
				TJsapiticketTxrefDto jsapiticketTxrefParam = new TJsapiticketTxrefDto();
				jsapiticketTxrefParam.setPlatform_id(platform_id);
				TJsapiticketTxrefDto jsapiticketTxrefRelust = jsapiticketTxrefService.getRow(jsapiticketTxrefParam);

				// 如果时效表信息不存在
				if (jsapiticketTxrefRelust == null) {

					// 调用微信服务器获取JsapiTicket
					Token token = CommonUtil.getJsapiTicket(getAccessToken(CacheKeyUtil.getAccessTokenKey(platform_id)));

					if (token != null) {
						// 保存到数据库
						TJsapiticketTxrefDto jsapiticketTxrefDto = new TJsapiticketTxrefDto();
						jsapiticketTxrefDto.setJsapiticket(token.getJsapiTicket());
						jsapiticketTxrefDto.setPlatform_id(platform_id);
						jsapiticketTxrefDto.setTx_created_time(nowTime);
						jsapiticketTxrefDto.setTx_expiration_time(nowTime + CacheKeyPrefixConstant.JSAPITICKET_TX_EXPIRATION_TIME);
						jsapiticketTxrefDto.setCreated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
						jsapiticketTxrefDto.setUpdated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
						jsapiticketTxrefService.save(jsapiticketTxrefDto);

						// 放置到缓存中
						CacheUtil.put(jsapiTicketKey, token.getJsapiTicket(), CacheKeyPrefixConstant.JSAPITICKET_TX_EXPIRATION_TIME);

						jsapiTicket = token.getJsapiTicket();
					}

				} else {
					Long tx_expiration_time = jsapiticketTxrefRelust.getTx_expiration_time();
					// 如果没有过期，则上传到缓存中
					if (tx_expiration_time > nowTime) {
						CacheUtil.put(jsapiTicketKey, jsapiticketTxrefRelust.getJsapiticket(), (int) (tx_expiration_time - nowTime));
						jsapiTicket = jsapiticketTxrefRelust.getJsapiticket();
					} else {
						// 调用微信服务器获取JsapiTicket
						Token token = CommonUtil.getJsapiTicket(getAccessToken(CacheKeyUtil.getAccessTokenKey(platform_id)));

						if (token != null) {
							// 更新数据库记录
							TJsapiticketTxrefDto jsapiticketTxrefDto = new TJsapiticketTxrefDto();
							jsapiticketTxrefDto.setJsapiticket_id(jsapiticketTxrefRelust.getJsapiticket_id());
							jsapiticketTxrefDto.setPlatform_id(platform_id);
							jsapiticketTxrefDto.setJsapiticket(token.getJsapiTicket());
							jsapiticketTxrefDto.setTx_created_time(nowTime);
							jsapiticketTxrefDto.setTx_expiration_time(nowTime + CacheKeyPrefixConstant.JSAPITICKET_TX_EXPIRATION_TIME);
							jsapiticketTxrefDto.setCreated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
							jsapiticketTxrefDto.setUpdated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
							jsapiticketTxrefService.updatePK(jsapiticketTxrefDto);

							// 放置到缓存中
							CacheUtil.put(jsapiTicketKey, token.getJsapiTicket(), CacheKeyPrefixConstant.JSAPITICKET_TX_EXPIRATION_TIME);
							jsapiTicket = token.getJsapiTicket();
						}
					}
				}
			} catch (Exception e) {
				logger.error("获取JsapiTicket异常     platform_id:" + platform_id, e);
			}
		}

		return jsapiTicket;
	}
}