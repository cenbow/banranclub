package com.pub.persistence.dao.impl;
import java.util.List;

import com.pub.common.local.model.dto.TRoleResRelaDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.dao.ITRoleResRelaDao;
import com.pub.persistence.model.TRoleResRelaQueryBean;

/**
 * 类功能:角色资源关系持久实现类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Scope("prototype")
@Service("tRoleResRelaDao")
public class TRoleResRelaDaoImpl extends BaseDaoImpl<TRoleResRelaDto, String> implements ITRoleResRelaDao{
	public TRoleResRelaDaoImpl() {
		super(TRoleResRelaDto.class);
	}


	/****
	 *
	 * @param tRoleResRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTRoleResRelaListPage(TRoleResRelaQueryBean tRoleResRelaQueryBean,PagingObject pagingObject) throws Exception
	 {
		tRoleResRelaQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
		 PageResult prs = new PageResult();

		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tRoleResRela.select_tRoleResRelaList_count",tRoleResRelaQueryBean);

		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());


		 //查询列表
		 List tRoleResRelaList = this.getSqlMapClientTemplate().queryForList("tRoleResRela.select_tRoleResRelaList", tRoleResRelaQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tRoleResRelaList);
		 prs.setPagingObject(pagingObject);

		 return prs;
	 }




}
