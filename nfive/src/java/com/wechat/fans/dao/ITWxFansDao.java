package com.wechat.fans.dao;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TWxFansDto;
import com.wechat.fans.model.TWxFansQueryBean;

/**
 * 类功能:自动代码生成模板 TWxFans 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITWxFansDao extends BaseDao<TWxFansDto, String> {

	/****
	 *
	 * @param tWxFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxFansListPage(TWxFansQueryBean tWxFansQueryBean, PagingObject pagingObject) throws Exception;

}
