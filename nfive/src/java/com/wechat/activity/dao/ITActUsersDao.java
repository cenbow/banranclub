package com.wechat.activity.dao;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.activity.model.TActUsersDto;
import com.wechat.activity.model.TActUsersQueryBean;

/**
 * 类功能:自动代码生成模板 TActUsers 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITActUsersDao extends BaseDao<TActUsersDto, String> {

	/****
	 *
	 * @param tActUsersQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTActUsersListPage(TActUsersQueryBean tActUsersQueryBean, PagingObject pagingObject) throws Exception;

}
