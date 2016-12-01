package com.wechat.pfusercfg.service;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.pfusercfg.model.TPlatformempCfgQueryBean;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;
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
public interface ITPlatformempCfgService extends IGenericService<TPlatformempCfgDto, String>{

	/****
	 * 
	 * @param tPlatformempCfgQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTPlatformempCfgListPage(TPlatformempCfgQueryBean tPlatformempCfgQueryBean,PagingObject pagingObject) throws Exception;

	 
	 /**
	  * 配置角色人员对应关系
	  * @param tPlatformempCfgDto
	  @param doType
	 */
	 public void configTPlatformempCfgList(TPlatformempCfgDto tPlatformempCfgDto,String doType);
}
