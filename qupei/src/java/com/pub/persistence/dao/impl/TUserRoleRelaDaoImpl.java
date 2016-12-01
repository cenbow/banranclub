package com.pub.persistence.dao.impl;
import java.util.List;

import com.pub.common.local.model.dto.TUserRoleRelaDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.dao.ITUserRoleRelaDao;
import com.pub.persistence.model.TUserRoleRelaQueryBean;

/**
 * 类功能:员工角色关系持久实现类
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
@Service("tUserRoleRelaDao")
public class TUserRoleRelaDaoImpl extends BaseDaoImpl<TUserRoleRelaDto, String> implements ITUserRoleRelaDao{
	public TUserRoleRelaDaoImpl() {
		super(TUserRoleRelaDto.class);
	}


	/****
	 *
	 * @param tUserRoleRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTUserRoleRelaListPage(TUserRoleRelaQueryBean tUserRoleRelaQueryBean,PagingObject pagingObject) throws Exception
	 {
		tUserRoleRelaQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
		 PageResult prs = new PageResult();

		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tUserRoleRela.select_tUserRoleRelaList_count",tUserRoleRelaQueryBean);

		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());


		 //查询列表
		 List tUserRoleRelaList = this.getSqlMapClientTemplate().queryForList("tUserRoleRela.select_tUserRoleRelaList", tUserRoleRelaQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tUserRoleRelaList);
		 prs.setPagingObject(pagingObject);

		 return prs;
	 }

}
