package com.platform.persistence.service;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.model.TRoleResRelaQueryBean;
import com.platform.persistence.model.dto.TRoleResRelaDto;
import com.hercules.database.service.IGenericService;


/**
 * 类功能:角色资源关系服务接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITRoleResRelaService extends IGenericService<TRoleResRelaDto, String>{

	/****
	 * 
	 * @param tRoleResRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTRoleResRelaListPage(TRoleResRelaQueryBean tRoleResRelaQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /**
	  * 保存角色资源数据
	  */
	 public void saveTRoleResRela(TRoleResRelaDto tRoleResRelaDto);
	 
	 /**
	  * 删除角色资源数据
	  */
	 public void deleteTRoleResRela(TRoleResRelaDto tRoleResRelaDto);
	 
	 /**
	  * 为角色配置资源
	  * @param tRoleResRelaDto
	  */
	 public void configRoleResource(TRoleResRelaDto tRoleResRelaDto);
	 
}
