package com.pub.persistence.service;
import com.hercules.database.service.IGenericService;
import com.pub.common.local.model.dto.TUserRoleRelaDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TUserRoleRelaQueryBean;


/**
 * 类功能:用户角色关系服务接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITUserRoleRelaService extends IGenericService<TUserRoleRelaDto, String>{

	/****
	 *
	 * @param tUserRoleRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTUserRoleRelaListPage(TUserRoleRelaQueryBean tUserRoleRelaQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 配置角色人员对应关系
	  * @param tUserRoleRelaDto
	  */
	 public void configUserRoleRela(TUserRoleRelaDto tUserRoleRelaDto,String doType);

}
