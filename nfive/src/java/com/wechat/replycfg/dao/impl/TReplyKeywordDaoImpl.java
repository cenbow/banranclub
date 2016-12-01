package com.wechat.replycfg.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.replycfg.dao.ITReplyKeywordDao;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;

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
@Service("tReplyKeywordDao")
public class TReplyKeywordDaoImpl extends BaseDaoImpl<TReplyKeywordDto, String> implements ITReplyKeywordDao{
	public TReplyKeywordDaoImpl() {
		super(TReplyKeywordDto.class);
	}
	
	
	/****
	 * 分页查询
	 * @param tReplyKeywordQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTReplyKeywordListPage(TReplyKeywordQueryBean tReplyKeywordQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tReplyKeywordQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tReplyKeyword.select_tReplyKeywordList_count",tReplyKeywordQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tReplyKeywordList = this.getSqlMapClientTemplate().queryForList("tReplyKeyword.select_tReplyKeywordList", tReplyKeywordQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tReplyKeywordList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }

	 
	 /***
	  *  模糊匹配模式的查询条件
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> queryReplyKeywordDtosByLikeMode(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception {
		 List<TReplyKeywordDto>  replyKeywordlList= this.getSqlMapClientTemplate().queryForList("tReplyKeyword.select_queryReplyKeywordDtosByLikeMode",tReplyKeywordQueryBean);
		 return replyKeywordlList;
	 }
	 
	 
	 
	 /***
	  *  精确匹配模式的查询条件
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> queryReplyKeywordDtosByAccurateMode(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception {
		 List<TReplyKeywordDto>  replyKeywordlList= this.getSqlMapClientTemplate().queryForList("tReplyKeyword.select_queryReplyKeywordDtosByAccurateMode",tReplyKeywordQueryBean);
		 return replyKeywordlList;
	 }
	 
	/***
	 * 检查相同条件的数据是否存在
	 */
	public List<TReplyKeywordDto> checkTReplyKeywordDto(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception {
		 List<TReplyKeywordDto>  replyKeywordlList= this.getSqlMapClientTemplate().queryForList("tReplyKeyword.select_checkTReplyKeywordDto",tReplyKeywordQueryBean);
		 return replyKeywordlList;
	}

	/***
	 * 自定义更新方法
	 */
	public Integer updateTReplyKeywordDto(TReplyKeywordDto tReplyKeywordDto) throws Exception {
		int result = this.getSqlMapClientTemplate().update("tReplyKeyword.update_updateTReplyKeywordDto",tReplyKeywordDto);
		return result;
	}


	/***
	 * 校验此素材ID是否被引用
	 */
	public Integer countTReplyKeywordList(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception{
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tReplyKeyword.select_countTReplyKeywordList_count",tReplyKeywordQueryBean);
		return count;
	}
	 
	 

	
}
