package com.platform.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.model.TEmpUserQueryBean;
import com.platform.persistence.model.dto.TEmpUserDto;

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
	 
	 /**
	  * 登录成功后更新用户扩展表信息
	  * @param loginUserCD/ipaddress
	  * @return
	  */
	 public int updateLoginInfoWhenSuccess(Map paramMap);
	 
	 /**
	  * 密码不正确更新用户扩展表信息
	  * @param loginUserCD/ipaddress
	  * @return
	  */
	 public int updateLoginInfoWhenFailure(Map paramMap);

}
