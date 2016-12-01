package com.wechat.activity.service.impl;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.activity.dao.ITActUsersDao;
import com.wechat.activity.model.TActUsersDto;
import com.wechat.activity.model.TActUsersQueryBean;
import com.wechat.activity.service.ITActUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;


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
@Service("tActUsersService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TActUsersServiceImpl extends GenericServiceImpl<TActUsersDto, String> implements ITActUsersService {

	@Autowired
	private ITActUsersDao tActUsersDao;

	public BaseDao<TActUsersDto, String> getBaseDao() {
		return tActUsersDao;
	}

	/****
	 * @param tActUsersQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTActUsersListPage(TActUsersQueryBean tActUsersQueryBean, PagingObject pagingObject) throws Exception {
		return tActUsersDao.queryTActUsersListPage(tActUsersQueryBean, pagingObject);
	}

}
