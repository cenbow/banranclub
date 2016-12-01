package com.wechat.fans.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.fans.dao.ITFansGroupDao;
import com.wechat.fans.model.TFansGroupQueryBean;
import com.wechat.fans.model.dto.TFansGroupDto;

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
@Service("tFansGroupDao")
public class TFansGroupDaoImpl extends BaseDaoImpl<TFansGroupDto, String> implements ITFansGroupDao{
	public TFansGroupDaoImpl() {
		super(TFansGroupDto.class);
	}
	/****
	 * 
	 * @param tFansGroupQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansGroupListPage(TFansGroupQueryBean tFansGroupQueryBean,PagingObject pagingObject) throws Exception {
	 	
		 tFansGroupQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tFansGroup.select_tFansGroupList_count",tFansGroupQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tFansGroupList = this.getSqlMapClientTemplate().queryForList("tFansGroup.select_tFansGroupList", tFansGroupQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tFansGroupList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }


	/**
	 * 检查数据是否存在
	 */
	public List<TFansGroupDto> checktFansGroupExist(TFansGroupDto tFansGroupDto) throws Exception {
		
		List<TFansGroupDto> tFansGroupList = this.getSqlMapClientTemplate().queryForList("tFansGroup.select_checktFansGroupExist", tFansGroupDto);
		return tFansGroupList;
	}
	
	/**
	 * 查询所有的群组列表
	 */
	public List<TFansGroupDto> alltFansGroupName(TFansGroupQueryBean tFansGroupQueryBean)throws Exception {
		List<TFansGroupDto> tFansGroupList = this.getSqlMapClientTemplate().queryForList("tFansGroup.select_alltFansGroupName", tFansGroupQueryBean);
		return tFansGroupList;
	}
}
