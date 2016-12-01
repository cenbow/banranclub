package com.wechat.replycfg.dao;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:特殊回复
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITReplySpecialDao extends BaseDao<TReplySpecialDto,String>{	

	/****
	 * 
	 * @param tReplySpecialQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTReplySpecialListPage(TReplySpecialQueryBean tReplySpecialQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /**
	  * TODO
	  * 查询当前符合条件的特殊回复对象列表
	  * @param tReplySpecialQueryBean
	  * @return
	  */
	 public List<TReplySpecialDto> queryReplySpecialList(TReplySpecialQueryBean tReplySpecialQueryBean);
    
	 /**
	  * 检查素材是否与特殊回复有关联
	  * @param tReplySpecialQueryBean
	  * @param msg_type
	  * @return
	  * @throws Exception
	  */
	public Integer checkRelated(TReplySpecialQueryBean tReplySpecialQueryBean)throws Exception;
	
	 /**
     * 检查首次关注或关键字无匹配只有一个
     * @param tReplySpecialDto
     * @return
     */
	public List<TReplySpecialDto> checktReplySpecialOnlyOne(TReplySpecialQueryBean tReplySpecialQueryBean)throws Exception;
}
