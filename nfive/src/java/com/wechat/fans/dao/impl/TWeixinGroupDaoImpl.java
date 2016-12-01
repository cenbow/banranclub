package com.wechat.fans.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.fans.dao.ITWeixinGroupDao;
import com.wechat.fans.model.TWeixinGroupQueryBean;
import com.wechat.fans.model.dto.TWeixinGroupDto;

/**
 * 类功能:微信组
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.20</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tWeixinGroupDao")
public class TWeixinGroupDaoImpl extends BaseDaoImpl<TWeixinGroupDto, String> implements ITWeixinGroupDao{
	public TWeixinGroupDaoImpl() {
		super(TWeixinGroupDto.class);
	}
	
	
	/****
	 * 
	 * @param tWeixinGroupQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWeixinGroupListPage(TWeixinGroupQueryBean tWeixinGroupQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tWeixinGroupQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWeixinGroup.select_tWeixinGroupList_count",tWeixinGroupQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());

		 //查询列表
		 List tWeixinGroupList = this.getSqlMapClientTemplate().queryForList("tWeixinGroup.select_tWeixinGroupList", tWeixinGroupQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tWeixinGroupList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	 
	 /**
	  * 获得有效的微信组
	  */
	public List<TWeixinGroupDto> getAllWxGroup(TWeixinGroupQueryBean tWeixinGroupQueryBean) {
		 List<TWeixinGroupDto>  tmpWeixinGroupDto = this.getSqlMapClientTemplate().queryForList("tWeixinGroup.select_AlltWeixinGroup",tWeixinGroupQueryBean);
		 return tmpWeixinGroupDto;
	}
}
