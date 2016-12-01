package com.wechat.crowdsend.dao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.model.TMsgSendGroupQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendGroupDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:自动代码生成模板 IDao 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITMsgSendGroupDao extends BaseDao<TMsgSendGroupDto,String>{	

	/****
	 * 
	 * @param tMsgSendGroupQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendGroupListPage(TMsgSendGroupQueryBean tMsgSendGroupQueryBean,PagingObject pagingObject) throws Exception;
}
