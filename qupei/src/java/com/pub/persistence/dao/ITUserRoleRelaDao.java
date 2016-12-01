package com.pub.persistence.dao;
import com.hercules.database.dao.BaseDao;
import com.pub.common.local.model.dto.TUserRoleRelaDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TUserRoleRelaQueryBean;

/**
 * 类功能:员工角色关系持久接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITUserRoleRelaDao extends BaseDao<TUserRoleRelaDto,String>{

	/****
	 *
	 * @param tUserRoleRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTUserRoleRelaListPage(TUserRoleRelaQueryBean tUserRoleRelaQueryBean,PagingObject pagingObject) throws Exception;
}
