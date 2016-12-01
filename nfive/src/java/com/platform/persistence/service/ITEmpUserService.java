package com.platform.persistence.service;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.model.TEmpUserQueryBean;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.hercules.database.service.IGenericService;
import java.util.*;
/**
 * 类功能:csr员工服务接口类
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITEmpUserService extends IGenericService<TEmpUserDto, String>{

	/****
	 * 
	 * @param tEmpUserQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTEmpUserListPage(TEmpUserQueryBean tEmpUserQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /**
	  * 员工校验
	  * @param tEmpUserQueryBean
	  * @param doType
	  * @return
	  */
	 public String checkTEmpUser(TEmpUserQueryBean tEmpUserQueryBean,String doType);
	 
	 
	 public String deleteTEmpUser(TEmpUserDto tEmpUserDto);
	 
	 /**
	  * 用户登录
	  * @param tEmpUserDto
	  * @return
	  */
	 public String login(TEmpUserDto tEmpUserDto, String ipAddress);
	 
	 /**
	  * 用户切换登录
	  * @param
	  * @return
	  */
	 public String loginChange();
	 
	 /**
	  * 密码修改
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public String changePass(TEmpUserQueryBean tEmpUserQueryBean);
	 
	 /**
	  * 密码重置
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public String resetPass(TEmpUserQueryBean tEmpUserQueryBean);
	 
	 /**
	  * 更新切换公众号
	  * @param loginUserCD,platformId,changeplatformId
	  * @return
	  */
	 public void changePlatform(String loginUserCD,String platformId,String changeplatformId);
	 
}
