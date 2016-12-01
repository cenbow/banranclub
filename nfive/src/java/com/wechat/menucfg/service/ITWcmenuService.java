package com.wechat.menucfg.service;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.hercules.database.service.IGenericService;

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
public interface ITWcmenuService extends IGenericService<TWcmenuDto, String> {

	/****
	 * 
	 * @param tWcmenuQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWcmenuListPage(TWcmenuQueryBean tWcmenuQueryBean,
			PagingObject pagingObject) throws Exception;

	/**
	 * 增加菜单节点，有两个步骤 1.向TWcmenu表增加一条记录 2.向TWcmSelfRela表增加一条记录
	 * 
	 * @param tWcmenuQueryBean
	 * @return
	 * @throws Exception
	 */
	public void addWcmSelfRela(TWcmenuQueryBean tWcmenuQueryBean)
			throws Exception;

	/**
	 * 查询记录
	 * 
	 * @param paramTWcmenuDto
	 * @return
	 * @throws Exception
	 */
	public TWcmenuQueryBean getTWcmenuQueryBean(TWcmenuDto paramTWcmenuDto)
			throws Exception;

	/**
	 * 查询相同记录的条数
	 * @param paramTWcmenuDto
	 * @return
	 * @throws Exception
	 */
	public int selectTWcmenuListCount(TWcmenuDto paramTWcmenuDto) throws Exception;
}
