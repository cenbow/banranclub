package com.wechat.activity.dao.impl;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.activity.dao.ITActUsersDao;
import com.wechat.activity.model.TActUsersDto;
import com.wechat.activity.model.TActUsersQueryBean;
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
@Service("tActUsersDao")
public class TActUsersDaoImpl extends BaseDaoImpl<TActUsersDto, String> implements ITActUsersDao {

	public TActUsersDaoImpl() {
		super(TActUsersDto.class);
	}

	/****
	 *
	 * @param tActUsersQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTActUsersListPage(TActUsersQueryBean tActUsersQueryBean, PagingObject pagingObject) throws Exception {

		tActUsersQueryBean.setOrderStr(" order by " + pagingObject.getSort_name() + " " + pagingObject.getSort_order());
		PageResult prs = new PageResult();

		// 查询记录总量信息
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tActUsers.select_tActUsersList_count", tActUsersQueryBean);

		// 填充分页对象 以便构重新初始化对象
		pagingObject.calculatePage(count.intValue());

		// 查询列表
		List tActUsersList = this.getSqlMapClientTemplate().queryForList("tActUsers.select_tActUsersList", tActUsersQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		prs.setResultList(tActUsersList);
		prs.setPagingObject(pagingObject);

		return prs;
	}

}
