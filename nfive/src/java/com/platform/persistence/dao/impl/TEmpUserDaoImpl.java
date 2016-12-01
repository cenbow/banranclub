package com.platform.persistence.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.dao.ITEmpUserDao;
import com.platform.persistence.model.TEmpUserQueryBean;
import com.platform.persistence.model.dto.TEmpUserDto;

/**
 * 类功能:csr员工持久实现类 
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tEmpUserDao")
public class TEmpUserDaoImpl extends BaseDaoImpl<TEmpUserDto, String> implements ITEmpUserDao{
	public TEmpUserDaoImpl() {
		super(TEmpUserDto.class);
	}
	
	
	/****
	 * 
	 * @param tEmpUserQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTEmpUserListPage(TEmpUserQueryBean tEmpUserQueryBean,PagingObject pagingObject) throws Exception
	 {	
		tEmpUserQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tEmpUser.select_tEmpUserList_count",tEmpUserQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tEmpUserList = this.getSqlMapClientTemplate().queryForList("tEmpUser.select_tEmpUserList", tEmpUserQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tEmpUserList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	 
	 /**
	  * 获取未被绑定过的csr帐号
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public List queryNotBindTEmpUserList(TEmpUserQueryBean tEmpUserQueryBean)
	 {
		 List tEmpUserList = this.getSqlMapClientTemplate().queryForList("tEmpUser.select_notBind_tEmpUserList", tEmpUserQueryBean);
		 return tEmpUserList;
	 }
	 
	
	 public void changePlatform(String loginUserCD,String platformId,String changeplatformId)
	 {
		 Map currPlatform = new HashMap();
		 currPlatform.put("loginUserCD",loginUserCD);
		 currPlatform.put("platformId",changeplatformId);
		 currPlatform.put("isUse",CodeStringConstant.CS_1000_TRUE);
	     getSqlMapClientTemplate().update("tEmpUser.update_platformId",currPlatform);//切换公众号
	     
		 Map pubPlatform = new HashMap();
		 pubPlatform.put("loginUserCD",loginUserCD);
		 pubPlatform.put("platformId",platformId);
		 pubPlatform.put("isUse",CodeStringConstant.CS_1000_FALSE);
	     getSqlMapClientTemplate().update("tEmpUser.update_platformId",pubPlatform);//当前公众号
	 }
	 
	 
	 /**
	  * 登录成功后更新用户扩展表信息
	  * @param loginUserCD
	  * @return
	  */
	 public int updateLoginInfoWhenSuccess(Map paramMap){
		 return getSqlMapClientTemplate().update("tEmpUser.update_login_success",paramMap);
	 }
	 
	 /**
	  * 密码不正确更新用户扩展表信息
	  * @param loginUserCD
	  * @return
	  */
	 public int updateLoginInfoWhenFailure(Map paramMap){
		 return getSqlMapClientTemplate().update("tEmpUser.update_login_failure",paramMap);
	 }
	 	 
}
