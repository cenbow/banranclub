package com.pub.persistence.dao;
import java.util.List;

import com.hercules.database.dao.BaseDao;
import com.pub.common.local.model.dto.TEmpUserDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TEmpUserQueryBean;

/**
 * 类功能:csr员工持久接口类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITEmpUserDao extends BaseDao<TEmpUserDto,String>{

	/****
	 *
	 * @param tEmpUserQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTEmpUserListPage(TEmpUserQueryBean tEmpUserQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 获取未被绑定过的csr帐号
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public List queryNotBindTEmpUserList(TEmpUserQueryBean tEmpUserQueryBean);
	  /**
	  * 切换公众号
	  * @param
	  * @return
	  */
	 public void changePlatform(String loginUserCD,String platformId,String changeplatformId);
}
