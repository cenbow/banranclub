package com.wechat.menucfg.dao;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:自动代码生成模板 IDao 模板
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
public interface ITWcmenuDao extends BaseDao<TWcmenuDto, String> {

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
	 * 查询记录总数
	 * 
	 * @param paramTWcmenuDto
	 * @return
	 * @throws Exception
	 */
	public int selectTWcmenuListCount(TWcmenuDto paramTWcmenuDto)
			throws Exception;
}
