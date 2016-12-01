package com.wechat.pfcfg.service.impl;
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
import com.platform.common.tools.wechat.PubPlatformBean;
import com.wechat.pfcfg.dao.ITPubPlatformDao;
import com.wechat.pfcfg.model.TPubPlatformQueryBean;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;


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
@Service("tPubPlatformService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TPubPlatformServiceImpl extends GenericServiceImpl<TPubPlatformDto, String> implements ITPubPlatformService{
    @Autowired
    private ITPubPlatformDao   tPubPlatformDao;

	public BaseDao<TPubPlatformDto, String> getBaseDao() {
		return tPubPlatformDao;
	}
	

	/****
	 * @param  tPubPlatformQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTPubPlatformListPage(TPubPlatformQueryBean tPubPlatformQueryBean,PagingObject pagingObject) throws Exception{
		 	return this.tPubPlatformDao.queryTPubPlatformListPage(tPubPlatformQueryBean, pagingObject);
	 }
	
	 /**
	  * 用于查询当前用户可切换的公众帐号列表
	  * @param 	user_cd
	  * @return  List<PubPlatformBean>
	  */
	 public List<PubPlatformBean> queryPubPlatformBeanList(String user_cd){
		 return this.tPubPlatformDao.queryPubPlatformBeanList(user_cd);
	 }
	 
	 
	 //查询公众号的微信名和原始ID,避免重复添加。
	 public boolean queryTPubPlatformName(String wechartAccount,String orgId) throws Exception
	 {
			return this.tPubPlatformDao.queryTPubPlatformName(wechartAccount,orgId);
	 }
	//修改公众号名称和原始ID的时候，判断是否重复，如果是修改的原记录重复，表示可以修改，否则不能修改。
	 public String queryTPubPlatformId(String wechartAccount,String orgId ,String platformId) throws Exception
	 {
			return this.tPubPlatformDao.queryTPubPlatformId(wechartAccount,orgId,platformId);
	 }
}
