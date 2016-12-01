package com.wechat.article.dao.impl;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.dao.ITArticleRelaDao;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.dto.TArticleRelaDto;

/**
 * 类功能:图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tArticleRelaDao")
public class TArticleRelaDaoImpl extends BaseDaoImpl<TArticleRelaDto, String> implements ITArticleRelaDao{
	public TArticleRelaDaoImpl() {
		super(TArticleRelaDto.class);
	}
	
	
	/****
	 * 
	 * @param tArticleRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTArticleRelaListPage(TArticleRelaQueryBean tArticleRelaQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tArticleRelaQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("article.select_tArticleRelaList_count",tArticleRelaQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tArticleRelaList = this.getSqlMapClientTemplate().queryForList("article.select_tArticleRelaList", tArticleRelaQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tArticleRelaList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	
}
