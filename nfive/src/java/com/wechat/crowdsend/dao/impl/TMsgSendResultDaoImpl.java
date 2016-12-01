package com.wechat.crowdsend.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.dao.ITMsgSendResultDao;
import com.wechat.crowdsend.model.TMsgSendResultQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;

/**
 * 类功能:群发管理  发送结果表操作
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tMsgSendResultDao")
public class TMsgSendResultDaoImpl extends BaseDaoImpl<TMsgSendResultDto, String> implements ITMsgSendResultDao{
	public TMsgSendResultDaoImpl() {
		super(TMsgSendResultDto.class);
	}
	
	
	/****
	 * 
	 * @param tMsgSendResultQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendResultListPage(TMsgSendResultQueryBean tMsgSendResultQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tMsgSendResultQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tMsgSendResult.select_tMsgSendResultList_count",tMsgSendResultQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tMsgSendResultList = this.getSqlMapClientTemplate().queryForList("tMsgSendResult.select_tMsgSendResultList", tMsgSendResultQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tMsgSendResultList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	
}
