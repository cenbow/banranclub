package com.wechat.replycfg.service;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.hercules.database.service.IGenericService;


/**
 * 类功能:自动代码生成模板   IService 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITReplyKeywordService extends IGenericService<TReplyKeywordDto, String>{

	/****
	 * 
	 * @param tReplyKeywordQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTReplyKeywordListPage(TReplyKeywordQueryBean tReplyKeywordQueryBean,PagingObject pagingObject) throws Exception;
	 

	 /***
	  *  模糊匹配模式的查询条件
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> queryReplyKeywordDtosByLikeMode(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception;
	 
	 
	 
	 /***
	  *  精确匹配模式的查询条件
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> queryReplyKeywordDtosByAccurateMode(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception;
	 
	 /***
	  * 检查相同条件数据是否存在
	  * @param tReplyKeywordQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<TReplyKeywordDto> checkTReplyKeywordDto(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception;
	 
	 /**
	  * 自定义更新《关键字回复规则》方法
	  * @param tmpTReplyKeywordDto
	  * @return
	  * @throws Exception
	  */
	 public Integer updateTReplyKeywordDto(TReplyKeywordDto tmpTReplyKeywordDto) throws Exception;
	 
	 /**
	  * 查询当前条件下总数方法
	  * @return
	  */
	 public Integer countTReplyKeywordList(TReplyKeywordQueryBean tReplyKeywordQueryBean) throws Exception;
	 
}
