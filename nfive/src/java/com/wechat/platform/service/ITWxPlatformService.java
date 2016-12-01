package com.wechat.platform.service;


import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.platform.model.TWxPlatformDto;
import com.wechat.platform.model.TWxPlatformQueryBean;

/**
 * 类功能:自动代码生成模板   IService 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITWxPlatformService extends IGenericService<TWxPlatformDto, String> {

	/****
	 * @param tWxPlatformQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxPlatformListPage(TWxPlatformQueryBean tWxPlatformQueryBean, PagingObject pagingObject) throws Exception;

}
