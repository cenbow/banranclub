package com.pub.persistence.dao.impl;
import java.util.List;

import com.pub.common.local.model.dto.TResSelfRelaDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.dao.ITResSelfRelaDao;
import com.pub.persistence.model.TResSelfRelaQueryBean;

/**
 * 类功能:资源关系持久实现类
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
@Service("tResSelfRelaDao")
public class TResSelfRelaDaoImpl extends BaseDaoImpl<TResSelfRelaDto, String> implements ITResSelfRelaDao{
	public TResSelfRelaDaoImpl() {
		super(TResSelfRelaDto.class);
	}


	/****
	 *
	 * @param tResSelfRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTResSelfRelaListPage(TResSelfRelaQueryBean tResSelfRelaQueryBean,PagingObject pagingObject) throws Exception
	 {
		tResSelfRelaQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
		 PageResult prs = new PageResult();

		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tResSelfRela.select_tResSelfRelaList_count",tResSelfRelaQueryBean);

		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());


		 //查询列表
		 List tResSelfRelaList = this.getSqlMapClientTemplate().queryForList("tResSelfRela.select_tResSelfRelaList", tResSelfRelaQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tResSelfRelaList);
		 prs.setPagingObject(pagingObject);

		 return prs;
	 }



	 /****
		 * 获取所有的资源关系数据
		 * @param tResSelfRelaQueryBean
		 * @param pagingObject
		 * @return
		 * @throws Exception
		 */
		 public List queryTResSelfRelaList(TResSelfRelaQueryBean tResSelfRelaQueryBean) throws Exception
		 {
			 //查询列表
			 List tResSelfRelaList = this.getSqlMapClientTemplate().queryForList("tResSelfRela.select_tResSelfRelaList_resource", tResSelfRelaQueryBean);
			 return tResSelfRelaList;
		 }

		 /**
		  * 通过父资源id删除与子资源对应的关系数据 逻辑删除
		  */
		 public void deleteResRelaByParentId(TResSelfRelaQueryBean tResSelfRelaQueryBean)
		 {
			 this.getSqlMapClientTemplate().delete("tResSelfRela.deleteResRelaByParentId", tResSelfRelaQueryBean);
		 }

}
