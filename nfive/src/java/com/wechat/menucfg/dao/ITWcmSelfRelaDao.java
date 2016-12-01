package com.wechat.menucfg.dao;
import java.util.List;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;

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
public interface ITWcmSelfRelaDao extends BaseDao<TWcmSelfRelaDto,String>{	

	/****
	 * 
	 * @param tWcmSelfRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWcmSelfRelaListPage(TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 根据公众号查询到菜单关系信息
	  * @param tWcmSelfRelaQueryBean
	  * @return
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 public List queryTWcmSelfRelaDtoList(TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean) throws Exception;
}
