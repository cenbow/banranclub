package com.wechat.jsapiticket.web.action;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.cache.CacheUtil;
import com.platform.common.tools.constant.CacheKeyPrefixConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.JsApiSignBean;
import com.platform.common.tools.wechat.JsApiSignUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

/**
 * 类功能：JS-SDK使用权限签名
 */
@Controller("jsapiSignAction")
@Scope("prototype")
public class JsapiSignAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(JsapiSignAction.class);

	@Autowired
	private UtilConfig utilConfig;

	// 【入参】请求URL
	private String url;
    // 【入参】jsonpCallback
    private String jsonpCallBack;

	public String execute() throws Exception {

		JsApiSignBean jsApiSignBean = new JsApiSignBean();
		String signUrl = StringUtils.trimToEmpty(getUrl()); // 请求URL

		if (StringUtils.isNotBlank(signUrl)) {
			try {
				signUrl = StringUtils.substringBefore(signUrl, "#"); // 不包含#及其后面部分

				String jsApiSignKey = CacheKeyUtil.getWxJsApiSignKey(signUrl);	// JsApi签名key
				jsApiSignBean = (JsApiSignBean) CacheUtil.get(jsApiSignKey);	// 从缓存中获取JsApiSignBean

				if (jsApiSignBean == null) {
					// 获取临时票据
					String jsapiTicket = WechatInfoUtil.getJsapiTicket(CacheKeyUtil.getJsapiTicketKey(utilConfig.getVip_platform_id()));

					if (StringUtils.isNotBlank(jsapiTicket)) {
						jsApiSignBean = JsApiSignUtil.sign(jsapiTicket, signUrl);

						if (jsApiSignBean != null) {
							// 获取客户服务（唯一）公众号信息
							TPubPlatformDto tPubPlatformDto = WechatInfoUtil.getTPubPlatformDtoByPlatformId(utilConfig.getVip_platform_id());
							String appId = tPubPlatformDto.getAppid(); // 公众号AppId
							jsApiSignBean.setAppId(appId);

							// 放置到缓存中
							CacheUtil.put(jsApiSignKey, jsApiSignBean, CacheKeyPrefixConstant.JSAPITICKET_TX_EXPIRATION_TIME - 1200);
							logger.info("微信JsApi签名缓存成功");
						}
					} else {
						jsApiSignBean = new JsApiSignBean();
						jsApiSignBean.setError(true);
						jsApiSignBean.setErrorMsg("JSSDK临时票据获取失败");
						logger.error(jsApiSignBean.getErrorMsg());
					}
				}

				// 打印Log
				JsApiSignUtil.outPrintln(jsApiSignBean);
			} catch (Exception ex) {
				jsApiSignBean = new JsApiSignBean();
				jsApiSignBean.setError(true);
				jsApiSignBean.setErrorMsg("微信JS签名失败");
				logger.error(jsApiSignBean.getErrorMsg());
				ex.printStackTrace();
			}
		} else {
			jsApiSignBean.setError(true);
			jsApiSignBean.setErrorMsg("请求URL不能为空");
			logger.error(jsApiSignBean.getErrorMsg());
		}
        String json = JSONObject.fromObject(jsApiSignBean).toString();
        if(StringUtils.isNotEmpty(jsonpCallBack)){
            json = jsonpCallBack+"("+json+")";
        }

		outJSOND(this.getResponse(), json);
		return null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public String getJsonpCallBack() {
        return jsonpCallBack;
    }

    public void setJsonpCallBack(String jsonpCallBack) {
        this.jsonpCallBack = jsonpCallBack;
    }
}
