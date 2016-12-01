package com.wechat.fans.service;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TFansGroupQueryBean;
import com.wechat.fans.model.dto.TFansGroupDto;
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
public interface ITFansGroupService extends IGenericService<TFansGroupDto, String>{

	/****
	 * 
	 * @param tFansGroupQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansGroupListPage(TFansGroupQueryBean tFansGroupQueryBean,PagingObject pagingObject) throws Exception;
	 
	 
	 
	 /**
	  * 校验此数据是否存在
	  * @param tFansGroupDto
	  * @return
	  */
	 public List<TFansGroupDto> checktFansGroupExist(TFansGroupDto tFansGroupDto) throws Exception;
	 
	 /**
	  * 删除粉丝群组
	  * @param tFansGroupDto
	  * @return
	  */
	 public void deltFansGroup(TFansGroupDto tFansGroupDto) throws Exception;
	 
	 
	 /**
	  * 查询所有粉丝群
	  * @param tFansGroupDto
	  * @return
	  * @throws Exception
	  */
	 public List<TFansGroupDto> alltFansGroupName(TFansGroupQueryBean tFansGroupQueryBean) throws Exception;

}
