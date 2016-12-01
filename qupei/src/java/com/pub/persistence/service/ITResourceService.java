package com.pub.persistence.service;


import com.hercules.database.service.IGenericService;
import com.pub.common.local.model.dto.TResourceDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TResourceQueryBean;

import java.util.List;


/**
 * 类功能:资源服务接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITResourceService extends IGenericService<TResourceDto, String> {

	/****
	 *
	 * @param tResourceQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTResourceListPage(TResourceQueryBean tResourceQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 保存资源数据
	  * @param pid
	  * @param tResourceDto
	  */
	 public void saveResource(String pid,TResourceDto tResourceDto);

	 /**
	  * 获取编码
	  * @param tResourceQueryBean
	  * @return
	  */
	 public String getCode(TResourceQueryBean tResourceQueryBean);

	 /**
	  * 删除资源数据
	  * @param tResourceDto
	  */
	 public String deleteResource(TResourceDto tResourceDto);

	 /**
	  * 校验节点是否存在
	  * @param tResourceQueryBean
	  * @return
	  */
	 public String checkResourceVal(TResourceQueryBean tResourceQueryBean);

	 /**
	  * 校验节点是否重名
	  * @param tResourceQueryBean
	  * @return
	  */
	 public String checkResourceOnSubmit(TResourceQueryBean tResourceQueryBean,String doType);

	 public List<TResourceDto> getResourceByUserId(TResourceQueryBean tResourceQueryBean);



}
