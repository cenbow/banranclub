package com.wechat.menucfg.dao.impl;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.menucfg.dao.ITWcmSelfRelaDao;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;

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
@Service("tWcmSelfRelaDao")
public class TWcmSelfRelaDaoImpl extends BaseDaoImpl<TWcmSelfRelaDto, String> implements ITWcmSelfRelaDao{
	public TWcmSelfRelaDaoImpl() {
		super(TWcmSelfRelaDto.class);
	}
	
	
	/****
	 * 
	 * @param tWcmSelfRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWcmSelfRelaListPage(TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tWcmSelfRelaQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWcmSelfRela.select_tWcmSelfRelaList_count",tWcmSelfRelaQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tWcmSelfRelaList = this.getSqlMapClientTemplate().queryForList("tWcmSelfRela.select_tWcmSelfRelaList", tWcmSelfRelaQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tWcmSelfRelaList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }


	public List queryTWcmSelfRelaDtoList(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean) throws Exception {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("tWcmSelfRela.select_tWcmSelfRelaDto_list", tWcmSelfRelaQueryBean);
	}
	
}
