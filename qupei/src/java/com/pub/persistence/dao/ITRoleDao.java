package com.pub.persistence.dao;
import java.util.List;

import com.hercules.database.dao.BaseDao;
import com.pub.common.local.model.dto.TRoleDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TRoleQueryBean;

/**
 * 类功能:角色持久接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITRoleDao extends BaseDao<TRoleDto,String>{

	/****
	 *
	 * @param tRoleQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTRoleListPage(TRoleQueryBean tRoleQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 通过用户获取角色数据
	  * @param tRoleQueryBean
	  * @return
	  */
	 public List<TRoleDto> getRoleByUser(TRoleQueryBean tRoleQueryBean);
}
