package com.wechat.pfcfg.dao;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.wechat.PubPlatformBean;
import com.wechat.pfcfg.model.TPubPlatformQueryBean;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
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
public interface ITPubPlatformDao extends BaseDao<TPubPlatformDto,String>{	

	/****
	 * 
	 * @param tPubPlatformQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTPubPlatformListPage(TPubPlatformQueryBean tPubPlatformQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /**
	  * 用于查询当前用户可切换的公众帐号列表
	  * @param   user_cd
	  * @return  List<PubPlatformBean>
	  */
	 public List<PubPlatformBean> queryPubPlatformBeanList(String user_cd);
	 public boolean queryTPubPlatformName(String platfromName,String orgId) throws Exception;
	 public String queryTPubPlatformId(String platfromName,String orgId,String platformId) throws Exception;
}
