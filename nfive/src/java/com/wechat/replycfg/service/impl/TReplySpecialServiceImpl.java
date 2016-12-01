package com.wechat.replycfg.service.impl;
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
import com.wechat.replycfg.dao.ITReplySpecialDao;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;


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

@Scope("prototype")
@Service("tReplySpecialService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TReplySpecialServiceImpl extends GenericServiceImpl<TReplySpecialDto, String> implements ITReplySpecialService{
    @Autowired
    private ITReplySpecialDao   tReplySpecialDao;

	public BaseDao<TReplySpecialDto, String> getBaseDao() {
		return tReplySpecialDao;
	}
	

	/****
	 * @param  tReplySpecialQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTReplySpecialListPage(TReplySpecialQueryBean tReplySpecialQueryBean,PagingObject pagingObject) throws Exception{
		 	return this.tReplySpecialDao.queryTReplySpecialListPage(tReplySpecialQueryBean, pagingObject);
	 }
	 

	 /**
	  * TODO
	  * 查询当前符合条件的特殊回复对象列表
	  * @param tReplySpecialQueryBean
	  * @return
	  */
	 public List<TReplySpecialDto> queryReplySpecialList(TReplySpecialQueryBean tReplySpecialQueryBean){
		 return tReplySpecialDao.queryReplySpecialList(tReplySpecialQueryBean);
	 }

   
	 
	 /**
	  * 检查素材是否与特殊回复有关联
	  * @param tAttachMaterialDto
	  * @param msg_type
	  * @return
	  * @throws Exception
	  */
	public Integer checkRelated(TReplySpecialQueryBean tReplySpecialQueryBean)throws Exception {

		return tReplySpecialDao.checkRelated(tReplySpecialQueryBean);
	}
	 /**
     * 检查首次关注或关键字无匹配只有一个
     * @param tReplySpecialDto
     * @return
     */
	public List<TReplySpecialDto> checktReplySpecialOnlyOne(TReplySpecialQueryBean tReplySpecialQueryBean)throws Exception{
		
		return  tReplySpecialDao.checktReplySpecialOnlyOne(tReplySpecialQueryBean);
	}
}
