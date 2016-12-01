package com.wechat.fans.dao;

import java.util.List;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TLatestFansQueryBean;
import com.wechat.fans.model.dto.TLatestFansDto;

/**
 * 类功能:自动代码生成模板 TLatestFans 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITLatestFansDao extends BaseDao<TLatestFansDto, String> {

	/****
	 * 
	 * @param tLatestFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTLatestFansListPage(TLatestFansQueryBean tLatestFansQueryBean, PagingObject pagingObject) throws Exception;
	
	/**
	 * 清空【T_LATEST_FANS】所有记录
	 * @throws Exception
	 */
	public void truncateLatestFans() throws Exception;
	
	/**
	 * 同步最新关注粉丝更新数据库
	 * @param weixinFans
	 * @param createdUserCd
	 * @param updatedUserCd
	 * @param platformId
	 * @return
	 */
	public String batchInsertLatestFans(List<String> weixinFans, String createdUserCd, String updatedUserCd, String platformId) throws Exception;
}
