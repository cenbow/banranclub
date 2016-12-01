package com.platform.persistence.dao;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.model.TResourceQueryBean;
import com.platform.persistence.model.dto.TResourceDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:资源持久接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITResourceDao extends BaseDao<TResourceDto,String>{	

	/****
	 * 
	 * @param tResourceQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTResourceListPage(TResourceQueryBean tResourceQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /**
	  * 获取节点编码
	  * @param tResourceQueryBean
	  * @return
	  */
	 public String getCode(TResourceQueryBean tResourceQueryBean);
	 
	 
	 /**
	  * 通过父节点查询所有的一级子节点
	  * @param tResourceQueryBean
	  * @return
	  */
	 public List<TResourceDto> getResourceByParent(TResourceQueryBean tResourceQueryBean);
	 
	 /**
	  *  获取子节点对应的所有的父节点
	  * @param tResourceQueryBean
	  * @return
	  */
	 public List<TResourceDto> selectParentReByChild(TResourceQueryBean tResourceQueryBean);
	 
	 /**
	  * 通过用户id获取资源数据
	  * @param tResourceQueryBean
	  * @return
	  */
	 public List<TResourceDto> getResourceByUserId(TResourceQueryBean tResourceQueryBean);
	 
}
