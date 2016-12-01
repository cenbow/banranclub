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
import com.wechat.replycfg.dao.ITReplyKeywordDao;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.wechat.replycfg.service.ITReplyKeywordService;


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
@Service("tReplyKeywordService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TReplyKeywordServiceImpl extends GenericServiceImpl<TReplyKeywordDto, String> implements ITReplyKeywordService{
    @Autowired
    private ITReplyKeywordDao   tReplyKeywordDao;

	public BaseDao<TReplyKeywordDto, String> getBaseDao() {
		return tReplyKeywordDao;
	}
	

	/****
	 * @param  tReplyKeywordQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTReplyKeywordListPage(TReplyKeywordQueryBean tReplyKeywordQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tReplyKeywordDao.queryTReplyKeywordListPage(tReplyKeywordQueryBean, pagingObject);
	 }
	 

	 /***
	  *  模糊匹配模式的查询条件
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> queryReplyKeywordDtosByLikeMode(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception
	 {
		 return this.tReplyKeywordDao.queryReplyKeywordDtosByLikeMode(tReplyKeywordQueryBean);
	 }
	 
	 
	 /***
	  *  精确匹配模式的查询条件
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> queryReplyKeywordDtosByAccurateMode(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception
	 {
		 return this.tReplyKeywordDao.queryReplyKeywordDtosByAccurateMode(tReplyKeywordQueryBean);
	 }

	 /***
	  * 检查相同条件的数据是否存在
	  */
	public List<TReplyKeywordDto> checkTReplyKeywordDto(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception {
		return this.tReplyKeywordDao.checkTReplyKeywordDto(tReplyKeywordQueryBean);
	}


	/**
	 * 自定义更新方法
	 */
	public Integer updateTReplyKeywordDto(TReplyKeywordDto tReplyKeywordDto) throws Exception {
		return this.tReplyKeywordDao.updateTReplyKeywordDto(tReplyKeywordDto);
	}


	/***
	 * 查询总数方法
	 */
	public Integer countTReplyKeywordList(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception {
		return tReplyKeywordDao.countTReplyKeywordList(tReplyKeywordQueryBean);
	}
	
}
