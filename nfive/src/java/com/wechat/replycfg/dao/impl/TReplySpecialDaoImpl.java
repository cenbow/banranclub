package com.wechat.replycfg.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.replycfg.dao.ITReplySpecialDao;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.model.dto.TReplySpecialDto;

/**
 * 类功能:特殊回复
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tReplySpecialDao")
public class TReplySpecialDaoImpl extends BaseDaoImpl<TReplySpecialDto, String> implements ITReplySpecialDao{
	public TReplySpecialDaoImpl() {
		super(TReplySpecialDto.class);
	}
	
	
	/****
	 * 
	 * @param tReplySpecialQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTReplySpecialListPage(TReplySpecialQueryBean tReplySpecialQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tReplySpecialQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tReplySpecial.select_tReplySpecialList_count",tReplySpecialQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tReplySpecialList = this.getSqlMapClientTemplate().queryForList("tReplySpecial.select_tReplySpecialList", tReplySpecialQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tReplySpecialList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	 
	 
	 /**
	  * TODO
	  * 查询当前符合条件的特殊回复对象列表
	  * @param tReplySpecialQueryBean
	  * @return
	  */
	 public List<TReplySpecialDto> queryReplySpecialList(TReplySpecialQueryBean tReplySpecialQueryBean){
		 
		 List<TReplySpecialDto>  replySpecialList= this.getSqlMapClientTemplate().queryForList("tReplySpecial.select_queryReplySpecialList",tReplySpecialQueryBean);
		
		 return  replySpecialList;
	 }

	 /**
	  * 检查素材是否与特殊回复有关联
	  * @param tReplySpecialQueryBean
	  * @param msg_type
	  * @return
	  * @throws Exception
	  */
	public Integer checkRelated(TReplySpecialQueryBean tReplySpecialQueryBean)throws Exception{
		Integer checkCount= (Integer) this.getSqlMapClientTemplate().queryForObject("tReplySpecial.select_checkRelated",tReplySpecialQueryBean);
			
		 return  checkCount;
	}
	 /**
     * 检查首次关注或关键字无匹配只有一个
     * @param tReplySpecialDto
     * @return
     */
	public List<TReplySpecialDto> checktReplySpecialOnlyOne(TReplySpecialQueryBean tReplySpecialQueryBean)throws Exception{
		 return this.getSqlMapClientTemplate().queryForList("tReplySpecial.select_tReplySpecialOnlyOneList",tReplySpecialQueryBean);
		
	}
	 
}
