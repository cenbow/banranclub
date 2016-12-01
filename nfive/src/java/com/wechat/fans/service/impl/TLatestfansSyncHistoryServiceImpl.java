package com.wechat.fans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.dao.ITLatestfansSyncHistoryDao;
import com.wechat.fans.model.TLatestfansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TLatestfansSyncHistoryDto;
import com.wechat.fans.service.ITLatestfansSyncHistoryService;

/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Scope("prototype")
@Service("tLatestfansSyncHistoryService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TLatestfansSyncHistoryServiceImpl extends GenericServiceImpl<TLatestfansSyncHistoryDto, String> implements ITLatestfansSyncHistoryService {

	@Autowired
	private ITLatestfansSyncHistoryDao tLatestfansSyncHistoryDao;

	public BaseDao<TLatestfansSyncHistoryDto, String> getBaseDao() {
		return tLatestfansSyncHistoryDao;
	}

	/****
	 * @param tLatestfansSyncHistoryQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTLatestfansSyncHistoryListPage(TLatestfansSyncHistoryQueryBean tLatestfansSyncHistoryQueryBean, PagingObject pagingObject) throws Exception {
		return tLatestfansSyncHistoryDao.queryTLatestfansSyncHistoryListPage(tLatestfansSyncHistoryQueryBean, pagingObject);
	}

}
