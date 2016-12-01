package com.wechat.crowdsend.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.dao.ITMsgSendUesrDao;
import com.wechat.crowdsend.model.TMsgSendUesrQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendUesrDto;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;

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
@Service("tMsgSendUesrDao")
public class TMsgSendUesrDaoImpl extends BaseDaoImpl<TMsgSendUesrDto, String> implements ITMsgSendUesrDao{
	public TMsgSendUesrDaoImpl() {
		super(TMsgSendUesrDto.class);
	}
	
	
	/****
	 * 
	 * @param tMsgSendUesrQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendUesrListPage(TMsgSendUesrQueryBean tMsgSendUesrQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tMsgSendUesrQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tMsgSendUesr.select_tMsgSendUesrList_count",tMsgSendUesrQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tMsgSendUesrList = this.getSqlMapClientTemplate().queryForList("tMsgSendUesr.select_tMsgSendUesrList", tMsgSendUesrQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tMsgSendUesrList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	
}
