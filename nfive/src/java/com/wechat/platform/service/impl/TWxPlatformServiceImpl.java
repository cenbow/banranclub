package com.wechat.platform.service.impl;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.platform.dao.ITWxPlatformDao;
import com.wechat.platform.model.TWxPlatformDto;
import com.wechat.platform.model.TWxPlatformQueryBean;
import com.wechat.platform.service.ITWxPlatformService;
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
@Service("tWxPlatformService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TWxPlatformServiceImpl extends GenericServiceImpl<TWxPlatformDto, String> implements ITWxPlatformService {

	@Autowired
	private ITWxPlatformDao tWxPlatformDao;

	public BaseDao<TWxPlatformDto, String> getBaseDao() {
		return tWxPlatformDao;
	}

	/****
	 * @param tWxPlatformQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxPlatformListPage(TWxPlatformQueryBean tWxPlatformQueryBean, PagingObject pagingObject) throws Exception {
		return tWxPlatformDao.queryTWxPlatformListPage(tWxPlatformQueryBean, pagingObject);
	}

}
