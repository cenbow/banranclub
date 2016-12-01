package com.wechat.crowdsend.dao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.model.TMsgSendResultQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:群发管理 发送结果表操作接口
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITMsgSendResultDao extends BaseDao<TMsgSendResultDto,String>{	

	/****
	 * 
	 * @param tMsgSendResultQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendResultListPage(TMsgSendResultQueryBean tMsgSendResultQueryBean,PagingObject pagingObject) throws Exception;
}
