package com.wechat.menucfg.dao.impl;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.menucfg.dao.ITWcmenuDao;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.model.dto.TWcmenuDto;

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
@Service("tWcmenuDao")
public class TWcmenuDaoImpl extends BaseDaoImpl<TWcmenuDto, String> implements ITWcmenuDao{
	public TWcmenuDaoImpl() {
		super(TWcmenuDto.class);
	}
	
	
	/****
	 * 
	 * @param tWcmenuQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWcmenuListPage(TWcmenuQueryBean tWcmenuQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tWcmenuQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWcmenu.select_tWcmenuList_count",tWcmenuQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tWcmenuList = this.getSqlMapClientTemplate().queryForList("tWcmenu.select_tWcmenuList", tWcmenuQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tWcmenuList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }


	public int selectTWcmenuListCount(TWcmenuDto paramTWcmenuDto)
			throws Exception {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("tWcmenu.select_tWcmenuList_count", paramTWcmenuDto);
	}
	
}
