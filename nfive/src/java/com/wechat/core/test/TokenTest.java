package com.wechat.core.test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.other.WechatInfo;
import com.wechat.core.utils.MyX509TrustManager;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;


public class TokenTest {


	
	public static void main(String[] args)throws Exception
	{
		
		// 获取接口访问凭证
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/**/application-*.xml");
		WechatInfoUtil wechatInfoUtil = (WechatInfoUtil) context.getBean("wechatInfoUtil");
		TPubPlatformDto pubPlatformDto = WechatInfoUtil.getTPubPlatformDtoByPlatformId("SSID20140813083940000272");
		
		String tokenUrl ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+pubPlatformDto.getAppid()+"&secret="+pubPlatformDto.getAppsecret();
		URL url = new URL(tokenUrl);
		HttpsURLConnection conn =(HttpsURLConnection) url.openConnection();
		TrustManager[] tm  ={new MyX509TrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		conn.setSSLSocketFactory(ssf);
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		
		InputStream inputStream = conn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader buffereReader = new BufferedReader(inputStreamReader);
		
		StringBuffer buffer = new StringBuffer();
		String str = null;
		
		while((str= buffereReader.readLine())!=null){
			buffer.append(str);
		}
		
		buffereReader.close();
		inputStreamReader.close();
		inputStream.close();
		conn.disconnect();
		
		//输出返回结果
		System.out.println(buffer);
		
	}
}
