package com.wechat.fans.dao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TFansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:同步履历
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITFansSyncHistoryDao extends BaseDao<TFansSyncHistoryDto,String>{	

	/****
	 * 
	 * @param tFansSyncHistoryQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansSyncHistoryListPage(TFansSyncHistoryQueryBean tFansSyncHistoryQueryBean,PagingObject pagingObject) throws Exception;
	
}
