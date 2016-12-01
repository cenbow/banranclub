package com.wechat.material.dao;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.model.TWxNewsQueryBean;

/**
 * 类功能:自动代码生成模板 TWxNews 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITWxNewsDao extends BaseDao<TWxNewsDto, String> {

	/****
	 *
	 * @param tWxNewsQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxNewsListPage(TWxNewsQueryBean tWxNewsQueryBean, PagingObject pagingObject) throws Exception;

    //生成新的批次号
    public String newBatchNo() throws Exception ;


    //删除不存在的素材 根据批次号
    public void deleteWxNews(String batch_no) throws Exception ;

}
