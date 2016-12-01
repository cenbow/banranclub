package com.wechat.crowdsend.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.dao.ITMsgSendGroupDao;
import com.wechat.crowdsend.model.TMsgSendGroupQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendGroupDto;

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
@Service("tMsgSendGroupDao")
public class TMsgSendGroupDaoImpl extends BaseDaoImpl<TMsgSendGroupDto, String> implements ITMsgSendGroupDao{
	public TMsgSendGroupDaoImpl() {
		super(TMsgSendGroupDto.class);
	}
	
	
	/****
	 * 
	 * @param tMsgSendGroupQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendGroupListPage(TMsgSendGroupQueryBean tMsgSendGroupQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tMsgSendGroupQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tMsgSendGroup.select_tMsgSendGroupList_count",tMsgSendGroupQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tMsgSendGroupList = this.getSqlMapClientTemplate().queryForList("tMsgSendGroup.select_tMsgSendGroupList", tMsgSendGroupQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tMsgSendGroupList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	
}
