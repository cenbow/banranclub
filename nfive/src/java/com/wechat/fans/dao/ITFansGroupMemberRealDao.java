package com.wechat.fans.dao;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.hercules.database.dao.BaseDao;

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
public interface ITFansGroupMemberRealDao extends BaseDao<TFansGroupMemberRealDto,String>{	

	/****
	 * 
	 * @param tFansGroupMemberRealQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTFansGroupMemberRealListPage(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /***
	  * 根据粉丝群组ID删除该群组关系
	  */
	 public void delByfansGroupId(TFansGroupMemberRealDto tFansGroupMemberRealDto) throws Exception;
	 
	 /**
	  * 自定义删除粉丝方法，多条删除
	  */
	 public Integer delfansByPkids(String pkids) throws Exception;
      
		/****
		 * 查询粉丝所在的粉丝群
		 */
     public PageResult queryTFansGroupList(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean,PagingObject pagingObject);
     
     /**
	  * 查询粉丝已有的粉丝群
	  */
	 public List<TFansGroupMemberRealDto> getFansGroup(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean);
}
