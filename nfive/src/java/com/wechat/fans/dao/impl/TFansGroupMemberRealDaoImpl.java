package com.wechat.fans.dao.impl;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.permission.LoginUserInfoUtil;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.fans.dao.ITFansGroupMemberRealDao;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;

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
@Service("tFansGroupMemberRealDao")
public class TFansGroupMemberRealDaoImpl extends BaseDaoImpl<TFansGroupMemberRealDto, String> implements ITFansGroupMemberRealDao{
	public TFansGroupMemberRealDaoImpl() {
		super(TFansGroupMemberRealDto.class);
	}
	
	
	/****
	 * 
	 * @param tFansGroupMemberRealQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansGroupMemberRealListPage(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tFansGroupMemberRealQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tFansGroupMemberReal.select_tFansGroupMemberRealList_count",tFansGroupMemberRealQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tFansGroupMemberRealList = this.getSqlMapClientTemplate().queryForList("tFansGroupMemberReal.select_tFansGroupMemberRealList", tFansGroupMemberRealQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tFansGroupMemberRealList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }


	 /**
	  * 根据粉丝群组ID删除该组关系
	  */
	public void delByfansGroupId(TFansGroupMemberRealDto tFansGroupMemberRealDto) throws Exception{
		this.getSqlMapClientTemplate().update("tFansGroupMemberReal.del_delByfansGroupId",tFansGroupMemberRealDto);
	}


	public Integer delfansByPkids(String pkids) throws Exception {
		
		//解析pkids
		String[] pkidStr = pkids.split("\\$");
		//拼装sql条件
		StringBuffer delsqlStr = new StringBuffer();
		delsqlStr.append("AND (T_FANS_GROUP_MEMBER_REAL.REAL_ID = '"+pkidStr[0]+"'");
		for (int i=1;i<pkidStr.length;i++){
			delsqlStr.append(" OR T_FANS_GROUP_MEMBER_REAL.REAL_ID = '"+pkidStr[i]+"'");
		}
		delsqlStr.append(")");
		
		TFansGroupMemberRealQueryBean param_tFansGroupMemberRealQueryBean = new TFansGroupMemberRealQueryBean();
		param_tFansGroupMemberRealQueryBean.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		param_tFansGroupMemberRealQueryBean.setDelsqlStr(delsqlStr.toString());
		
		return this.getSqlMapClientTemplate().update("tFansGroupMemberReal.del_delfansByPkids",param_tFansGroupMemberRealQueryBean);
	}
	
	   
	/****
	 * 查询粉丝所在的粉丝群
	 */
     public PageResult queryTFansGroupList(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean,PagingObject pagingObject){
    		
 		tFansGroupMemberRealQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
 		 PageResult prs = new PageResult(); 
 		 
 		 //查询记录总量信息
 		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tFansGroupMemberReal.query_tFansGroupAndFansList_cnt",tFansGroupMemberRealQueryBean);
 		 
 		 //填充分页对象 以便构重新初始化对象
 		 pagingObject.calculatePage(count.intValue());

 		 //查询列表
 		 List tFansGroupMemberRealList = this.getSqlMapClientTemplate().queryForList("tFansGroupMemberReal.query_tFansGroupAndFansList", tFansGroupMemberRealQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
 		 prs.setResultList(tFansGroupMemberRealList);
 		 prs.setPagingObject(pagingObject);
 		 
 		 return prs;
     }

     /**
	  * 查询粉丝已有的粉丝群
	  */
	public List<TFansGroupMemberRealDto> getFansGroup(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) {
		
		return this.getSqlMapClientTemplate().queryForList("tFansGroupMemberReal.query_getFansGroup",tFansGroupMemberRealQueryBean);
	};

	
}
