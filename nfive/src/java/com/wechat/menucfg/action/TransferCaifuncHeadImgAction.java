package com.wechat.menucfg.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.other.WeixinUserInfo;
import com.wechat.core.utils.AdvancedUtil;

/**
 * 类功能:根据用户的OPENID取得用户的头像
 * 
 * <p>创建者:ZHUBAODING</p>
 * <p>创建时间:2016/04/27</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("transferCaifuncHeadImgAction")
@Scope("prototype")
public class TransferCaifuncHeadImgAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TransferCaifuncHeadImgAction.class);
		
	@Autowired
	private UtilConfig utilConfig;
	
	/** OPENID (输入) */
	private String openId;
		
	public String execute() throws Exception {
		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}
	
	
	/***
	 * 去微信服务器获取数据
	 * 
	 * 一、通过code换取网页授权access_token 
	 * 二、拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @param request
	 * @param state
	 * 
	 * @return OpenIdUserInfo
	 */
	public void getUserInfoByOpenId() throws Exception  {
		
		String echostr = "error";
		// 获取token信息
		String accessToken = WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(utilConfig.getVip_platform_id()));
		// 获取用户信息
		WeixinUserInfo weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, openId);
		if (weixinUserInfo != null && weixinUserInfo.getSubscribe() == 1) {
			//若校验成功则原样返回echostr，表示接入成功，否则接入失败
			echostr = weixinUserInfo.getHeadImgUrl();
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(echostr);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
		}			
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
