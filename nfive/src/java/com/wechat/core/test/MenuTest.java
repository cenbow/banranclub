package com.wechat.core.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.hercules.cache.CacheUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.menu.Button;
import com.wechat.core.beans.menu.ClickButton;
import com.wechat.core.beans.menu.ComplexButton;
import com.wechat.core.beans.menu.Menu;
import com.wechat.core.beans.menu.ViewButton;
import com.wechat.core.beans.other.Token;
import com.wechat.core.beans.other.WechatInfo;
import com.wechat.core.utils.CommonUtil;
//import com.wechat.core.utils.MenuUtil;


/**
 * 菜单管理器类
 */
public class MenuTest {


 	/**
	 * 定义菜单结构
	 * @return
	 */
	private static Menu getMenu() {



		ViewButton btn11 = new ViewButton();
		btn11.setName("百度");
		btn11.setType("view");
		btn11.setUrl("http://www.baidu.com");

		ViewButton btn12 = new ViewButton();
		btn12.setName("官网");
		btn12.setType("view");
		btn12.setUrl("http://wap.leadbank.com.cn");




		ClickButton btn21 = new ClickButton();
		btn21.setName("项目管理");
		btn21.setType("click");
		btn21.setKey("pm");


		ClickButton btn22 = new ClickButton();
		btn22.setName("授权登录");
		btn22.setType("click");
		btn22.setKey("test");


		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("微CRM");
		mainBtn1.setSub_button(new Button[] { btn11,btn12});


		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("微IT");
		mainBtn2.setSub_button(new
				Button[] { btn21, btn22 });



		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2 });

		return menu;
	}



	public static void main(String[] args) throws Exception {
		// 获取接口访问凭证
		ApplicationContext  context = new ClassPathXmlApplicationContext("classpath*:/config/**/application-*.xml");
//		WechatInfoUtil wechatInfoUtil = (WechatInfoUtil) context.getBean("wechatInfoUtil");
//		String accessToken = WechatInfoUtil.getAccessToken("SSID20140814083940000273");
//
//
//		if (!StringUtils.isEmpty(accessToken)) {
//			MenuUtil.deleteMenu( accessToken);
//			// 创建菜单
//			boolean result = MenuUtil.createMenu(getMenu(), accessToken);
//
//			// 判断菜单创建结果
//			if (result)
//				System.out.println("菜单创建成功！");
//			else
//				System.out.println("菜单创建失败！");
//		}
		CacheUtil.remove("hello");
		CacheUtil.put("hello", "adrian", 60);
		System.out.println(CacheUtil.get("hello"));
		Thread.sleep(10000);
		System.out.println(CacheUtil.get("hello"));
		Thread.sleep(10000);
		System.out.println(CacheUtil.get("hello"));
		Thread.sleep(10000);
		System.out.println(CacheUtil.get("hello"));
		Thread.sleep(10000);
		System.out.println(CacheUtil.get("hello"));
		Thread.sleep(10000);
		System.out.println(CacheUtil.get("hello"));
		Thread.sleep(10000);
		System.out.println(CacheUtil.get("hello"));
	}
}
