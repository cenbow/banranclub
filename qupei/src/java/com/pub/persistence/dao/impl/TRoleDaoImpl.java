package com.pub.persistence.dao.impl;
import java.util.List;

import com.pub.common.local.model.dto.TRoleDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.dao.ITRoleDao;
import com.pub.persistence.model.TRoleQueryBean;

/**
 * 类功能:角色持久实现类
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
@Service("tRoleDao")
public class TRoleDaoImpl extends BaseDaoImpl<TRoleDto, String> implements ITRoleDao{
	public TRoleDaoImpl() {
		super(TRoleDto.class);
	}


	/****
	 *
	 * @param tRoleQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTRoleListPage(TRoleQueryBean tRoleQueryBean,PagingObject pagingObject) throws Exception
	 {
		tRoleQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
		 PageResult prs = new PageResult();

		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tRole.select_tRoleList_count",tRoleQueryBean);

		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());


		 //查询列表
		 List tRoleList = this.getSqlMapClientTemplate().queryForList("tRole.select_tRoleList", tRoleQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tRoleList);
		 prs.setPagingObject(pagingObject);

		 return prs;
	 }

	 /**
	  * 通过用户获取角色数据
	  * @param tRoleQueryBean
	  * @return
	  */
	 public List<TRoleDto> getRoleByUser(TRoleQueryBean tRoleQueryBean)
	 {
		 return (List<TRoleDto>)getSqlMapClientTemplate().queryForList("tRole.selectRoleByUser", tRoleQueryBean);
	 }




}
