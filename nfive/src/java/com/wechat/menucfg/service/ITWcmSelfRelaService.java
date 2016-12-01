package com.wechat.menucfg.service;

import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.menucfg.bean.MenuTree;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;

/**
 * 类功能:自动代码生成模板 IService 模板
 * <p>
 * 创建者:
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */
public interface ITWcmSelfRelaService extends
		IGenericService<TWcmSelfRelaDto, String> {

	/****
	 * 
	 * @param tWcmSelfRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWcmSelfRelaListPage(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean,
			PagingObject pagingObject) throws Exception;

	/**
	 * 获取菜单关系树
	 * 
	 * @param tWcmSelfRelaQueryBean
	 * @return
	 * @throws Exception
	 */
	public MenuTree queryTWcmSelfRelaDtoList(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean) throws Exception;

	/**
	 * 删除菜单，有两个步骤
	 *  1.删除tWcmSelfRela表记录
	 *  2.删除tWcmenu表记录
	 * 
	 * @param tWcmSelfRelaQueryBean
	 * @throws Exception
	 */
	public void deleteTWcmSelfRela(TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean)
			throws Exception;

	/**
	 * 根据新增公众号id和公众号账户，新增公众号菜单新增
	 * 1.向tWcmenu添加一条记录
	 * 2.向tWcmSelfRela添加一条记录
	 * @param platform_id
	 * @param wechart_account
	 * @return
	 * @throws Exception
	 */
	public boolean createTWcmenu(String platform_id, String wechart_account)
			throws Exception;
}
