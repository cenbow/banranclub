package com.wechat.fans.service;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.model.vo.TWeixinFansVo;
import com.hercules.database.service.IGenericService;

/**
 * 类功能:粉丝
 * <p>
 * 创建者:zhaoshengsheng
 * </p>
 * <p>
 * 创建时间:2014.9.22
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
public interface ITWeixinFansService extends
		IGenericService<TWeixinFansDto, String> {

	/****
	 * 粉丝一览
	 *
	 * @param tWeixinFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWeixinFansListPage(
			TWeixinFansQueryBean tWeixinFansQueryBean, PagingObject pagingObject)
			throws Exception;

	/**
	 * 查询粉丝的粉丝群
	 *
	 * @param tWeixinFansQueryBean
	 * @return
	 */
	public List<TWeixinFansGroupVo> queryAllWeixinFansGroup(
			TWeixinFansQueryBean tWeixinFansDto) throws Exception;

	/**
	 * 查询粉丝的详细
	 *
	 * @param tWeixinFansQueryBean
	 * @return
	 */
	public TWeixinFansVo getRowFans(TWeixinFansDto paramTWeixinFansDto)
			throws Exception;

	/**
	 * 获取某个群组下不存在的粉丝
	 */
	public PageResult queryTWeixinFansListPageByGroupId(
			TWeixinFansQueryBean tWeixinFansQueryBean, PagingObject pagingObject)
			throws Exception;

	/**
	 * 保存或者是更新微信粉丝表
	 */
	public void saveOrUpdateTWeixinFans(String openid) throws Exception;
}
