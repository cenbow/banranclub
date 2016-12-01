package com.platform.persistence.dao;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.model.TResourceQueryBean;
import com.platform.persistence.model.TRoleResRelaQueryBean;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.model.dto.TRoleResRelaDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:角色资源关系持久接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITRoleResRelaDao extends BaseDao<TRoleResRelaDto,String>{	

	/****
	 * 
	 * @param tRoleResRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTRoleResRelaListPage(TRoleResRelaQueryBean tRoleResRelaQueryBean,PagingObject pagingObject) throws Exception;

	
}
