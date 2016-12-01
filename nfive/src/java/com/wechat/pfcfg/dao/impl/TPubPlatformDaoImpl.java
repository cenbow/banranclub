package com.wechat.pfcfg.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.wechat.PubPlatformBean;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.pfcfg.dao.ITPubPlatformDao;
import com.wechat.pfcfg.model.TPubPlatformQueryBean;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

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
@Service("tPubPlatformDao")
public class TPubPlatformDaoImpl extends BaseDaoImpl<TPubPlatformDto, String> implements ITPubPlatformDao{
	public TPubPlatformDaoImpl() {
		super(TPubPlatformDto.class);
	}
	
	
	/****
	 * 
	 * @param tPubPlatformQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTPubPlatformListPage(TPubPlatformQueryBean tPubPlatformQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tPubPlatformQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tPubPlatform.select_tPubPlatformList_count",tPubPlatformQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 //查询列表
		 List tPubPlatformList = this.getSqlMapClientTemplate().queryForList("tPubPlatform.select_tPubPlatformList", tPubPlatformQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tPubPlatformList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	 

	   /**
	    * 用于查询当前用户可切换的公众帐号列表
	    * @param user_cd
	    * @return
	    */
	   public List<PubPlatformBean> queryPubPlatformBeanList(String user_cd){
			return this.getSqlMapClientTemplate().queryForList("tPubPlatform.select_pubPlatformBeanList",user_cd);
	   }
	 
	 //查询微信公众号名称和原始ID
	 public boolean  queryTPubPlatformName(String wechartAccount,String orgId) throws Exception {
		 
		 //查询公众号。
		 TPubPlatformDto accountParam = new TPubPlatformDto();
		 accountParam.setWechart_account(wechartAccount);
		 List<TPubPlatformDto> platFormList =  this.getAll(accountParam);
		 
		 // 如果公众号存在，返回失败，前端显示重复。
		 if(platFormList.size()>0)
		 {
			 return false; 
		 }
		 else
		 {
			 TPubPlatformDto orgIdParam = new TPubPlatformDto();
			 orgIdParam.setOrg_id(orgId);
			 List<TPubPlatformDto> platFormOrgidList =  this.getAll(orgIdParam);
		  // 如果原始ID存在，返回失败，前端显示重复。
			 if( platFormOrgidList.size()>0)
			 {
				 return false;
			 }
		  //都不存在，返回true,可保存数据。	 
			 return true;
			 
		 }
		 
	 }
	 //查询公众号表ID,如果修改的时候,公众号名称一样,返回repeatValue。成功返回公众号主键
	 public String  queryTPubPlatformId(String wechartAccount,String orgId,String platformId) throws Exception {
		 
		 String selectPlatform_id="repeatValue";
		 
		 TPubPlatformDto accountparam = new TPubPlatformDto();
		 accountparam.setWechart_account(wechartAccount);//公众号名称
		 List<TPubPlatformDto> platformList =  this.getAll(accountparam);
		 
		 TPubPlatformDto orgIdParam = new TPubPlatformDto();
		 orgIdParam.setOrg_id(orgId);//原始ID名称
		 List<TPubPlatformDto> platFormOrgidList =  this.getAll(orgIdParam);
		 
		 //如果公众号存在，获取公众号ID进行匹配
		 if(platformList.size()>0)
		 {
			 selectPlatform_id=platformList.get(0).getPlatform_id();
			//不是同一条记录,返回失败,无法更新
			 if(!selectPlatform_id.equals(platformId))
			 {
				 return selectPlatform_id;
			 }
		 }
		 //如果原始ID存在,获取原始ID进行匹配
		 if(platFormOrgidList.size()>0)
		 {
			 selectPlatform_id=platFormOrgidList.get(0).getPlatform_id();
			//不是同一条记录,返回失败,无法更新
			 if(!selectPlatform_id.equals(platformId))
			 {
				 return selectPlatform_id;
			 }
		 }

		 return platformId;

	 }
	 
	 
	 
}
