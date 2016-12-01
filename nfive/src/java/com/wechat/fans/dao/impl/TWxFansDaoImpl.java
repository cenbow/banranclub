package com.wechat.fans.dao.impl;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.dao.ITWxFansDao;
import com.wechat.fans.model.TWxFansDto;
import com.wechat.fans.model.TWxFansQueryBean;
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
@Service("tWxFansDao")
public class TWxFansDaoImpl extends BaseDaoImpl<TWxFansDto, String> implements ITWxFansDao {

	public TWxFansDaoImpl() {
		super(TWxFansDto.class);
	}

	/****
	 *
	 * @param tWxFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxFansListPage(TWxFansQueryBean tWxFansQueryBean, PagingObject pagingObject) throws Exception {

		tWxFansQueryBean.setOrderStr(" order by " + pagingObject.getSort_name() + " " + pagingObject.getSort_order());
		PageResult prs = new PageResult();

		// 查询记录总量信息
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWxFans.select_tWxFansList_count", tWxFansQueryBean);

		// 填充分页对象 以便构重新初始化对象
		pagingObject.calculatePage(count.intValue());

		// 查询列表
		List tWxFansList = this.getSqlMapClientTemplate().queryForList("tWxFans.select_tWxFansList", tWxFansQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		prs.setResultList(tWxFansList);
		prs.setPagingObject(pagingObject);

		return prs;
	}

}
