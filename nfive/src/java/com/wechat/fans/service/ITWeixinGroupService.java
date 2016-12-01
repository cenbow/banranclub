package com.wechat.fans.service;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TWeixinGroupQueryBean;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.hercules.database.service.IGenericService;


/**
 * 类功能:微信组
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.20</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITWeixinGroupService extends IGenericService<TWeixinGroupDto, String>{

	/****
	 * 
	 * @param tWeixinGroupQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWeixinGroupListPage(TWeixinGroupQueryBean tWeixinGroupQueryBean,PagingObject pagingObject) throws Exception;
     
	 /**
	  * 获得有效的微信组
	  */
	 public List<TWeixinGroupDto> getAllWxGroup(TWeixinGroupQueryBean tWeixinGroupQueryBean);
}
