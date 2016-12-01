package com.wechat.fans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.dao.ITLatestFansDao;
import com.wechat.fans.model.TLatestFansQueryBean;
import com.wechat.fans.model.dto.TLatestFansDto;
import com.wechat.fans.service.ITLatestFansService;

/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Scope("prototype")
@Service("tLatestFansService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TLatestFansServiceImpl extends GenericServiceImpl<TLatestFansDto, String> implements ITLatestFansService {

	@Autowired
	private ITLatestFansDao tLatestFansDao;

	public BaseDao<TLatestFansDto, String> getBaseDao() {
		return tLatestFansDao;
	}

	/****
	 * @param tLatestFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTLatestFansListPage(TLatestFansQueryBean tLatestFansQueryBean, PagingObject pagingObject) throws Exception {
		return tLatestFansDao.queryTLatestFansListPage(tLatestFansQueryBean, pagingObject);
	}
	
	/**
	 * 同步最新关注粉丝更新数据库
	 * @param weixinFans
	 * @param createdUserCd
	 * @param updatedUserCd
	 * @param platformId
	 * @return
	 */
	public String batchInsertLatestFans(List<String> weixinFans, String createdUserCd, String updatedUserCd, String platformId) throws Exception{
		return tLatestFansDao.batchInsertLatestFans(weixinFans, createdUserCd, updatedUserCd, platformId);
	}

	/**
	 * 清空【T_LATEST_FANS】所有记录
	 * @throws Exception
	 */
	public void truncateLatestFans() throws Exception {
		tLatestFansDao.truncateLatestFans();
	}

}
