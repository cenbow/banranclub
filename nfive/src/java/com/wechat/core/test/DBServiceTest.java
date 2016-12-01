package com.wechat.core.test;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;

public class DBServiceTest {

	private static ApplicationContext context = null;
	private static ITArticleGroupService tArticleGroupService = null;
	static {
		context = new ClassPathXmlApplicationContext(
				"classpath*:/config/**/application-*.xml");
		tArticleGroupService = (ITArticleGroupService) context
				.getBean("tArticleGroupService");
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		List<ArticleItemVo> list=tArticleGroupService
		.getArticleGroupPublishableItems("SSID20140821095545000773");
        for (ArticleItemVo articleItemVo : list) {
			System.out.println( ToStringBuilder.reflectionToString(articleItemVo,
							ToStringStyle.MULTI_LINE_STYLE));
		}

	}

}
