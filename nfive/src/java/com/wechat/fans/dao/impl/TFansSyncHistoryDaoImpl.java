package com.wechat.fans.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.dao.ITFansSyncHistoryDao;
import com.wechat.fans.model.TFansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;

/**
 * 类功能:同步履历
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tFansSyncHistoryDao")
public class TFansSyncHistoryDaoImpl extends BaseDaoImpl<TFansSyncHistoryDto, String> implements ITFansSyncHistoryDao{
	public TFansSyncHistoryDaoImpl() {
		super(TFansSyncHistoryDto.class);
	}
	
	
	/****
	 * 
	 * @param tFansSyncHistoryQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansSyncHistoryListPage(TFansSyncHistoryQueryBean tFansSyncHistoryQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tFansSyncHistoryQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tFansSyncHistory.select_tFansSyncHistoryList_count",tFansSyncHistoryQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tFansSyncHistoryList = this.getSqlMapClientTemplate().queryForList("tFansSyncHistory.select_tFansSyncHistoryList", tFansSyncHistoryQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tFansSyncHistoryList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
}
