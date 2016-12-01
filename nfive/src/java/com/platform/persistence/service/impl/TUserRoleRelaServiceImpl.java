package com.platform.persistence.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.dao.ITUserRoleRelaDao;
import com.platform.persistence.model.TUserRoleRelaQueryBean;
import com.platform.persistence.model.dto.TUserRoleRelaDto;
import com.platform.persistence.service.ITUserRoleRelaService;


/**
 * 类功能:用户角色关系服务实现类
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
@Service("tUserRoleRelaService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TUserRoleRelaServiceImpl extends GenericServiceImpl<TUserRoleRelaDto, String> implements ITUserRoleRelaService{
    @Autowired
    private ITUserRoleRelaDao   tUserRoleRelaDao;

	public BaseDao<TUserRoleRelaDto, String> getBaseDao() {
		return tUserRoleRelaDao;
	}
	

	/****
	 * @param  tUserRoleRelaQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTUserRoleRelaListPage(TUserRoleRelaQueryBean tUserRoleRelaQueryBean,PagingObject pagingObject) throws Exception
	 {	
		 	return this.tUserRoleRelaDao.queryTUserRoleRelaListPage(tUserRoleRelaQueryBean, pagingObject);
	 }
	 
	 
	 /**
	  * 配置角色人员对应关系，包含关系数据的添加、删除
	  * @param tUserRoleRelaDto
	  */
	 public void configUserRoleRela(TUserRoleRelaDto tUserRoleRelaDto,String doType)
	 {
		 String roleId = tUserRoleRelaDto.getRole_id();
		 String userId = tUserRoleRelaDto.getUser_id();
		 if(roleId == null && roleId.trim().length() <= 0
				 && userId == null && userId.trim().length() <= 0)
		 {
			 return;
		 }
		 String[] roldIdArray = roleId.split(",");
		 String[] userIdArray = userId.split(",");
		 //执行保存
		 for(String roldId_ : roldIdArray)
		 {
			 for(String userId_ : userIdArray)
			 {
				 tUserRoleRelaDto.setRole_id(roldId_);
				 tUserRoleRelaDto.setUser_id(userId_);
				 saveOrUpdate(tUserRoleRelaDto,doType);
			 }
		 }
	 }
	 
	 /**
	  * 根据传入的操作类型执行保存/删除
	  * @param tUserRoleRelaDto 角色用户关系对象
	  * @param doType 操作类型
	  */
	 private void saveOrUpdate(TUserRoleRelaDto tUserRoleRelaDto,String doType)
	 {
		 if(doType.equals("add"))
		 {
			 tUserRoleRelaDto.setUser_role_id(null);
			 super.save(tUserRoleRelaDto);
		 }else if(doType.equals("delete"))
		 {
			 super.delete(tUserRoleRelaDto);
		 }
	 }
	 
	
}
