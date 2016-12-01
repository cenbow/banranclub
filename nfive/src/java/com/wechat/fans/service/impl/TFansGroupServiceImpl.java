package com.wechat.fans.service.impl;
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
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.dao.ITFansGroupDao;
import com.wechat.fans.dao.ITFansGroupMemberRealDao;
import com.wechat.fans.model.TFansGroupQueryBean;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.service.ITFansGroupService;


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
@Service("tFansGroupService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TFansGroupServiceImpl extends GenericServiceImpl<TFansGroupDto, String> implements ITFansGroupService{
    @Autowired
    private ITFansGroupDao   tFansGroupDao;//粉丝群组DAO
    @Autowired
    private ITFansGroupMemberRealDao   tFansGroupMemberRealDao;//粉丝群组成员关系DAO

	public BaseDao<TFansGroupDto, String> getBaseDao() {
		return tFansGroupDao;
	}
	

	/****
	 * @param  tFansGroupQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTFansGroupListPage(TFansGroupQueryBean tFansGroupQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tFansGroupDao.queryTFansGroupListPage(tFansGroupQueryBean, pagingObject);
	 }


	 /***
	  * 校验此数据是否存在
	  */
	public List<TFansGroupDto> checktFansGroupExist(TFansGroupDto tFansGroupDto) throws Exception{
		
		return this.tFansGroupDao.checktFansGroupExist(tFansGroupDto);
	}


	/**
	 * 删除粉丝群组
	 */
	public void deltFansGroup(TFansGroupDto tFansGroupDto) throws Exception {
		
		//删除粉丝群组成员关系
		TFansGroupMemberRealDto param_tFansGroupMemberRealDto = new TFansGroupMemberRealDto();
		param_tFansGroupMemberRealDto.setFans_group_id(tFansGroupDto.getFans_group_id());//设置群组ID
		param_tFansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//设置公众号
		this.tFansGroupMemberRealDao.delByfansGroupId(param_tFansGroupMemberRealDto);
		
		this.tFansGroupDao.deletePK(tFansGroupDto);
		
	}


	public List<TFansGroupDto> alltFansGroupName(TFansGroupQueryBean tFansGroupQueryBean) throws Exception {
		return tFansGroupDao.alltFansGroupName(tFansGroupQueryBean);
	}
	
}
