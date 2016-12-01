package com.wechat.fans.service;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TFansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.hercules.database.service.IGenericService;


/**
  * 类功能:同步履历
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITFansSyncHistoryService extends IGenericService<TFansSyncHistoryDto, String>{

	/****
	 * 
	 * @param tFansSyncHistoryQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansSyncHistoryListPage(TFansSyncHistoryQueryBean tFansSyncHistoryQueryBean,PagingObject pagingObject) throws Exception;
     
	 /**
      * 更新粉丝和微信组
      * @param weixinGroups
      * @param weixinFans
      * @param createdUserCd
      * @param updatedUserCd
      * @param platformId
      * @return
      * @throws Exception
      */
	 public String updateFansAndGroup(List<TWeixinGroupDto> weixinGroups,List<TWeixinFansDto> weixinFans, String createdUserCd, String updatedUserCd, String platformId) throws Exception;
     

}
