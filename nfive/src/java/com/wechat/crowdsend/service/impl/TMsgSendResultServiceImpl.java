package com.wechat.crowdsend.service.impl;
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
import com.wechat.crowdsend.dao.ITMsgSendResultDao;
import com.wechat.crowdsend.model.TMsgSendResultQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.wechat.crowdsend.service.ITMsgSendResultService;


/**
 * 类功能:群发结果
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tMsgSendResultService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TMsgSendResultServiceImpl extends GenericServiceImpl<TMsgSendResultDto, String> implements ITMsgSendResultService{
    @Autowired
    private ITMsgSendResultDao   tMsgSendResultDao;

	public BaseDao<TMsgSendResultDto, String> getBaseDao() {
		return tMsgSendResultDao;
	}
	

	/****
	 * @param  tMsgSendResultQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendResultListPage(TMsgSendResultQueryBean tMsgSendResultQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tMsgSendResultDao.queryTMsgSendResultListPage(tMsgSendResultQueryBean, pagingObject);
	 }
	
}
