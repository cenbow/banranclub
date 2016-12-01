package com.wechat.article.dao;
import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.dto.TArticleRelaDto;

/**
 * 类功能:图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITArticleRelaDao extends BaseDao<TArticleRelaDto,String>{	

	/****
	 * 
	 * @param tArticleRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTArticleRelaListPage(TArticleRelaQueryBean tArticleRelaQueryBean,PagingObject pagingObject) throws Exception;
}
