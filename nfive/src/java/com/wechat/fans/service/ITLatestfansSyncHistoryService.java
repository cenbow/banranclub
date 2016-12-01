package com.wechat.fans.service;

import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TLatestfansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TLatestfansSyncHistoryDto;

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
public interface ITLatestfansSyncHistoryService extends IGenericService<TLatestfansSyncHistoryDto, String> {

	/****
	 * @param tLatestfansSyncHistoryQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTLatestfansSyncHistoryListPage(TLatestfansSyncHistoryQueryBean tLatestfansSyncHistoryQueryBean, PagingObject pagingObject) throws Exception;
	

}
