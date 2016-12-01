package com.wechat.core.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.utils.CommonUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
public class AuthoTest {

	public static void main(String args[]) {
		{

			// 获取接口访问凭证
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/**/application-*.xml");
			WechatInfoUtil wechatInfoUtil = (WechatInfoUtil) context.getBean("wechatInfoUtil");
			TPubPlatformDto pubPlatformDto = WechatInfoUtil.getTPubPlatformDtoByPlatformId("SSID20140813083940000272");
			String redirect_uri = "http://game.leadbank.com.cn/wechat/weChatOAuthAction.action";
			
			
			// oauth2_url
			String oauth2_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+ pubPlatformDto.getAppid()
					+ "&redirect_uri="
					+ CommonUtil.urlEncodeUTF8(redirect_uri)
					+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			System.out.println(oauth2_url);

		}
	}
}
