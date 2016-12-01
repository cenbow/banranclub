package com.wechat.platform.dao.impl;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.platform.dao.ITWxPlatformDao;
import com.wechat.platform.model.TWxPlatformDto;
import com.wechat.platform.model.TWxPlatformQueryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;

/**
 * 类功能:自动代码生成模板 DaoImpl 模板
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
@Service("tWxPlatformDao")
public class TWxPlatformDaoImpl extends BaseDaoImpl<TWxPlatformDto, String> implements ITWxPlatformDao {

	public TWxPlatformDaoImpl() {
		super(TWxPlatformDto.class);
	}

	/****
	 *
	 * @param tWxPlatformQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxPlatformListPage(TWxPlatformQueryBean tWxPlatformQueryBean, PagingObject pagingObject) throws Exception {

		tWxPlatformQueryBean.setOrderStr(" order by " + pagingObject.getSort_name() + " " + pagingObject.getSort_order());
		PageResult prs = new PageResult();

		// 查询记录总量信息
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWxPlatform.select_tWxPlatformList_count", tWxPlatformQueryBean);

		// 填充分页对象 以便构重新初始化对象
		pagingObject.calculatePage(count.intValue());

		// 查询列表
		List tWxPlatformList = this.getSqlMapClientTemplate().queryForList("tWxPlatform.select_tWxPlatformList", tWxPlatformQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		prs.setResultList(tWxPlatformList);
		prs.setPagingObject(pagingObject);

		return prs;
	}

}
