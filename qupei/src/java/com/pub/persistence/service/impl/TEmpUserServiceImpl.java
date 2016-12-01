package com.pub.persistence.service.impl;
import java.util.List;

import com.pub.common.local.model.dto.TEmpUserDto;
import com.pub.common.local.model.dto.TResourceDto;
import com.pub.common.local.model.dto.TRoleDto;
import com.pub.common.local.model.dto.TUserRoleRelaDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.common.encryption.Encrypter;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.common.tools.opensymphony.web.context.UserContext;
import com.pub.common.tools.permission.LoginUserInfo;
import com.pub.common.tools.permission.LoginUserInfoUtil;
import com.pub.persistence.dao.ITEmpUserDao;
import com.pub.persistence.dao.ITRoleDao;
import com.pub.persistence.model.TEmpUserQueryBean;
import com.pub.persistence.model.TResourceQueryBean;
import com.pub.persistence.model.TRoleQueryBean;
import com.pub.persistence.service.ITEmpUserService;
import com.pub.persistence.service.ITResourceService;
import com.pub.persistence.service.ITUserRoleRelaService;


/**
 * 类功能:csr员工服务实现类
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
@Service("tEmpUserService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TEmpUserServiceImpl extends GenericServiceImpl<TEmpUserDto, String> implements ITEmpUserService{
	//日志
    private static  Logger logger  = Logger.getLogger(TEmpUserServiceImpl.class);


	//private static final String DEFAULT_PASS = "000000"; //默认密码

	@Autowired
    private ITEmpUserDao   tEmpUserDao;
    @Autowired
    private ITUserRoleRelaService tUserRoleRelaService;
    @Autowired
    private ITResourceService tResourceService;
    @Autowired
    private ITRoleDao   tRoleDao;


	public BaseDao<TEmpUserDto, String> getBaseDao() {
		return tEmpUserDao;
	}


	/****
	 * @param  tEmpUserQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTEmpUserListPage(TEmpUserQueryBean tEmpUserQueryBean,PagingObject pagingObject) throws Exception
	 {
		 	return this.tEmpUserDao.queryTEmpUserListPage(tEmpUserQueryBean, pagingObject);
	 }

	 /**
	  * 员工校验
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public String checkTEmpUser(TEmpUserQueryBean tEmpUserQueryBean,String doType)
	 {
		 //编码重复性校验
		 if(!checkUserCode(tEmpUserQueryBean,doType))
		 {
			 return "{\"status\":"+false+",\"mess\":\""+"员工帐号有重复!"+"\"}";
		 }
		 return "{\"status\":"+true+",\"mess\":\""+""+"\"}";
	 }

	 /**
	  * 员工删除
	  */
	 public String deleteTEmpUser(TEmpUserDto tEmpUserDto)
	 {
		 //校验是否存在角色-员工对应数据
		 if(checkkUserRoleRela(tEmpUserDto.getUser_id()))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"您要删除的员工存在角色对应数据,请先将这些数据删除!"+"\"}";
		 }
		 //删除员工
		 super.deletePK(tEmpUserDto);
		 return "{\"status\":"+true+",\"message\":\""+""+"\"}";
	 }


	 /**
	  * 用户登录
	  */
	 public String login(TEmpUserDto tEmpUserDto)
	 {
		 //验证登录有效性
		 TEmpUserDto loginUser = checkLoginVal(tEmpUserDto);
		 if(loginUser == null)
		 {
			 return "{\"status\":"+false+",\"message\":\""+"用户名或密码有误!"+"\"}";
		 }
		 if("300000000002".equals(loginUser.getLock_flag()))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"此用户已被锁定,无法登录,请联系管理员!"+"\"}";
		 }

		 logger.debug("=======================login init["+loginUser.getUser_name()+"]==========================");

		 //1设置当前用户的权限信息
		 //1.1获取用户的资源列表
		 TResourceQueryBean tResourceQueryBean = new TResourceQueryBean();
		 tResourceQueryBean.setUser_id(loginUser.getUser_id());
		 List<TResourceDto> tResourceList = tResourceService.getResourceByUserId(tResourceQueryBean);

		 //1.2获取用户的角色列表
		 TRoleQueryBean tRoleQueryBean = new TRoleQueryBean();
		 tRoleQueryBean.setUser_id(loginUser.getUser_id());
		 List<TRoleDto> roleList = tRoleDao.getRoleByUser(tRoleQueryBean);

		 //1.2构造SESSION存留内容
		 LoginUserInfo  loginUserInfo = new LoginUserInfo(loginUser,tResourceList,roleList);
		 UserContext.getRequest().getSession().setAttribute(LoginUserInfoUtil.LOGIN_USER_INFO, loginUserInfo);


		 //2设置用户公众号信息
//		 WechatInfoUtil  wechatInfoUtil =(WechatInfoUtil)SpringContextUtil.getBean("wechatInfoUtil");
//		 wechatInfoUtil.resetSessionForCurWechatInfo();


		 logger.debug("=======================login end["+loginUser.getUser_name()+"]==========================");
		 return "{\"status\":"+true+",\"message\":\""+""+"\"}";
	 }

	 /**
	  * 用户登录切换
	  */
	 public String loginChange()
	 {
		 //重置公众号信息。
		 logger.info("=======================切换公众号，开始重置公众号参数==========================");
//		 WechatInfoUtil  wechatInfoUtil =(WechatInfoUtil)SpringContextUtil.getBean("wechatInfoUtil");
//		 wechatInfoUtil.resetSessionForCurWechatInfo();
		 logger.info("=======================切换公众号，结束重置公众号参数==========================");
		 return "{\"status\":"+true+",\"message\":\""+""+"\"}";
	 }


	 /**
	  * 密码修改
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public String changePass(TEmpUserQueryBean tEmpUserQueryBean)
	 {
		 //获取登录用户
		 TEmpUserDto tEmpUserDto = LoginUserInfoUtil.getLoginUser();

		 //验证密码是否一致
		 if(!tEmpUserDto.getPassword().equals(Encrypter.encrypt(tEmpUserQueryBean.getOld_pass())))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"输入的原密码有误，请重新确认后重新输入!"+"\"}";
		 }
		 //验证新密码是否一直
		 if(!tEmpUserQueryBean.getNew_pass().equals(tEmpUserQueryBean.getAgain_new_pass()))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"两次输入的新密码不一致，请重新确认后重新输入!"+"\"}";
		 }
		 //修改用户密码
		 TEmpUserDto tEmpUserDto_ = new TEmpUserDto();
		 tEmpUserDto_.setUser_id(tEmpUserDto.getUser_id());
		 tEmpUserDto_.setPassword(Encrypter.encrypt(tEmpUserQueryBean.getNew_pass()));
		 super.updatePK(tEmpUserDto_);
		 return "{\"status\":"+true+",\"message\":\""+"密码修改成功"+"\"}";
	 }

	 /**
	  * 密码重置
	  * @param tEmpUserQueryBean
	  * @return
	  */
	 public String resetPass(TEmpUserQueryBean tEmpUserQueryBean)
	 {
		 if(tEmpUserQueryBean.getUser_id() == null || tEmpUserQueryBean.getUser_id().trim().length() <= 0)
		 {
			 return "{\"status\":"+true+",\"message\":\""+"未选择需要密码重置的用户，请重新选择!"+"\"}";
		 }
		 TEmpUserDto tEmpUserDto = new TEmpUserDto();
		 tEmpUserDto.setUser_id(tEmpUserQueryBean.getUser_id());
		 //获取重置密码
		 String defaultPassword = "123456";
		 tEmpUserDto.setPassword(Encrypter.encrypt(defaultPassword));
		 super.updatePK(tEmpUserDto);
		 return "{\"status\":"+true+",\"message\":\""+"密码重置成功!"+"\"}";
	 }


	 /**
	  * 校验登录是否有效
	  * @param tEmpUserDto
	  * @return
	  */
	 private TEmpUserDto checkLoginVal(TEmpUserDto tEmpUserDto)
	 {
		 TEmpUserDto searchTEmpUserDto = new TEmpUserDto();
		 searchTEmpUserDto.setUser_cd(tEmpUserDto.getUser_cd());
		 searchTEmpUserDto.setPassword(Encrypter.encrypt(tEmpUserDto.getPassword()));
		 return super.getRow(searchTEmpUserDto);
	 }

	 /**
	  * 校验登录是否有效
	  * @param tEmpUserDto
	  * @return
	  */
	 private TEmpUserDto checkLoginChangeVal(TEmpUserDto tEmpUserDto)
	 {
		 TEmpUserDto searchTEmpUserDto = new TEmpUserDto();
		 searchTEmpUserDto.setUser_cd(tEmpUserDto.getUser_cd());
		 return super.getRow(searchTEmpUserDto);
	 }


	 /**
	  * 验证员工是否存在角色对应数据
	  * @param user_id
	  * @return
	  */
	 private boolean checkkUserRoleRela(String user_id)
	 {
		 TUserRoleRelaDto tUserRoleRelaDto = new TUserRoleRelaDto();
		 tUserRoleRelaDto.setUser_id(user_id);
		 List<TUserRoleRelaDto> userRoleList = this.tUserRoleRelaService.getAll(tUserRoleRelaDto);
		 return userRoleList != null && userRoleList.size() > 0;
	 }

	 /**
	  * 编码重复性校验
	  * @param tEmpUserQueryBean
	  * @param doType
	  * @return
	  */
	 private boolean checkUserCode(TEmpUserQueryBean tEmpUserQueryBean,String doType)
	 {
		 TEmpUserDto searchTEmpUserDto = null;
		 //修改校验
		 searchTEmpUserDto = new TEmpUserDto();
		 //验证编码是否重复
		 searchTEmpUserDto.setUser_cd(tEmpUserQueryBean.getUser_cd());
		 TEmpUserDto tEmpUserDto_ = this.getRow(searchTEmpUserDto);
		 //执行验证
		 return isEqual(tEmpUserQueryBean,tEmpUserDto_,doType);
	 }

	 /**
	  * 员工名校验
	  * @param tEmpUserQueryBean
	  * @param doType
	  * @return
	  */
	 private boolean checkUserName(TEmpUserQueryBean tEmpUserQueryBean,String doType)
	 {
		 TEmpUserDto searchTEmpUserDto = null;
		 //修改校验
		 searchTEmpUserDto = new TEmpUserDto();
		 //验证编码是否重复
		 searchTEmpUserDto.setUser_name(tEmpUserQueryBean.getUser_name());
		 TEmpUserDto tEmpUserDto_ = this.getRow(searchTEmpUserDto);
		 //执行验证
		 return isEqual(tEmpUserQueryBean,tEmpUserDto_,doType);
	 }

	 /**
	  *  验证页面传入的和通过某些属性查询的对象是否一致
	  * @param tEmpUserQueryBean 页面传入的对象
	  * @param queryTEmpUserDto 通过某些验证属性查询出的对象
	  * @param doType 操作类型
	  * @return
	  */
	 private boolean isEqual(TEmpUserQueryBean tEmpUserQueryBean,TEmpUserDto queryTEmpUserDto,String doType)
	 {
		 if(queryTEmpUserDto != null)
		 {
			 //针对编辑操作
			 if("edit".equals(doType) && queryTEmpUserDto.getUser_id().equals(tEmpUserQueryBean.getUser_id()))
			 {
				 return true;
			 }
			 return false;
		 }
		 return true;
	 }
	//3个参数。修改的员工CD，当前使用公众号ID，切换公众号ID
	 @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	 public void changePlatform(String loginUserCD,String platformId,String changeplatformId)
	 {
		 tEmpUserDao.changePlatform(loginUserCD, platformId, changeplatformId);
		 //增加刷新缓存逻辑
//		 WechatInfoUtil.resetSessionForCurWechatInfo();
	 }

}
