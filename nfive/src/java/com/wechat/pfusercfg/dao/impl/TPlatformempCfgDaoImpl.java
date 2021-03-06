package com.wechat.pfusercfg.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.pfusercfg.dao.ITPlatformempCfgDao;
import com.wechat.pfusercfg.model.TPlatformempCfgQueryBean;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;

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
@Service("tPlatformempCfgDao")
public class TPlatformempCfgDaoImpl extends BaseDaoImpl<TPlatformempCfgDto, String> implements ITPlatformempCfgDao{
	public TPlatformempCfgDaoImpl() {
		super(TPlatformempCfgDto.class);
	}
	
	
	/****
	 * 
	 * @param tPlatformempCfgQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTPlatformempCfgListPage(TPlatformempCfgQueryBean tPlatformempCfgQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tPlatformempCfgQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tPlatformempCfg.select_tPlatformempCfgList_count",tPlatformempCfgQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tPlatformempCfgList = this.getSqlMapClientTemplate().queryForList("tPlatformempCfg.select_tPlatformempCfgList", tPlatformempCfgQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tPlatformempCfgList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	
}
