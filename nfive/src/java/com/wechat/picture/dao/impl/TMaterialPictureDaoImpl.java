package com.wechat.picture.dao.impl;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.picture.dao.ITMaterialPictureDao;
import com.wechat.picture.model.TMaterialPictureQueryBean;
import com.wechat.picture.model.dto.TMaterialPictureDto;

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
@Service("tMaterialPictureDao")
public class TMaterialPictureDaoImpl extends BaseDaoImpl<TMaterialPictureDto, String> implements ITMaterialPictureDao{
	public TMaterialPictureDaoImpl() {
		super(TMaterialPictureDto.class);
	}
	
	
	/****
	 * 
	 * @param tMaterialPictureQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMaterialPictureListPage(TMaterialPictureQueryBean tMaterialPictureQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tMaterialPictureQueryBean.setOrderStr(" order by picture."+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tMaterialPicture.select_tMaterialPictureList_count",tMaterialPictureQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tMaterialPictureList = this.getSqlMapClientTemplate().queryForList("tMaterialPicture.select_tMaterialPictureList", tMaterialPictureQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tMaterialPictureList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	
}
