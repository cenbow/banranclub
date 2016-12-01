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
import com.wechat.fans.dao.ITWeixinGroupDao;
import com.wechat.fans.model.TWeixinGroupQueryBean;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITWeixinGroupService;


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

@Scope("prototype")
@Service("tWeixinGroupService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TWeixinGroupServiceImpl extends GenericServiceImpl<TWeixinGroupDto, String> implements ITWeixinGroupService{
    @Autowired
    private ITWeixinGroupDao   tWeixinGroupDao;

	public BaseDao<TWeixinGroupDto, String> getBaseDao() {
		return tWeixinGroupDao;
	}
	

	/****
	 * @param  tWeixinGroupQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTWeixinGroupListPage(TWeixinGroupQueryBean tWeixinGroupQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tWeixinGroupDao.queryTWeixinGroupListPage(tWeixinGroupQueryBean, pagingObject);
	 }
	 /**
	  * 获得有效的微信组
	  */
	 public List<TWeixinGroupDto> getAllWxGroup(TWeixinGroupQueryBean tWeixinGroupQueryBean) {
			return this.tWeixinGroupDao.getAllWxGroup(tWeixinGroupQueryBean);
	}
}
