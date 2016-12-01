package com.pub.persistence.service;
import com.hercules.database.service.IGenericService;
import com.pub.common.local.model.dto.TResSelfRelaDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TResSelfRelaQueryBean;
import com.pub.persistence.util.resource.tree.Tree;


/**
 * 类功能:资源关系服务接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITResSelfRelaService extends IGenericService<TResSelfRelaDto, String>{

	/****
	 *
	 * @param tResSelfRelaQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTResSelfRelaListPage(TResSelfRelaQueryBean tResSelfRelaQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 获取并组装资源树
	  * @param tResSelfRelaQueryBean
	  * @return
	  * @throws Exception
	  */
	 public Tree queryTResSelfRelaList(TResSelfRelaQueryBean tResSelfRelaQueryBean) throws Exception;

	 /**
	  * 通过资源子id修改数据
	  */
	 public void updateResRela(TResSelfRelaDto tResSelfRelaDto);

	 /**
	  * 通过父资源id删除与子资源对应的关系数据 逻辑删除
	  */
	 public void deleteResRelaByParentId(TResSelfRelaQueryBean tResSelfRelaQueryBean);

}
