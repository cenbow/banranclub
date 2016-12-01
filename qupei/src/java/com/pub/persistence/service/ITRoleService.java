package com.pub.persistence.service;
import com.hercules.database.service.IGenericService;
import com.pub.common.local.model.dto.TRoleDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TRoleQueryBean;


/**
 * 类功能:角色服务接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITRoleService extends IGenericService<TRoleDto, String>{

	/****
	 *
	 * @param tRoleQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTRoleListPage(TRoleQueryBean tRoleQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 对角色进行校验
	  * @return
	  */
	 public boolean checkRole(String doType,TRoleDto tRoleDto);

	 /**
	  * 角色删除
	  * @param tRoleDto
	  * @return
	  */
	 public String deleteRole(TRoleDto tRoleDto);






}
