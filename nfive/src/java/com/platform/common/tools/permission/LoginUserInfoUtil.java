package com.platform.common.tools.permission;
import java.util.Map;

import com.platform.common.tools.opensymphony.web.context.UserContext;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.model.dto.TRoleDto;

/***
 * 登陆用户
 * 用户记录登录用户的基本信息
 * TODO 先实逻辑权限登录异常之后再补充
 * @author jia.chen
 */
public class LoginUserInfoUtil{
	
	//权限校验开启标识  false为未开启
	public static final boolean CHECK_PERMISION = true;
	
	//存放在SESSION 中的   LoginUserInfo KEY
	public static final String LOGIN_USER_INFO = "WECHAT$LOGIN_USER_INFO$$CJ_1";
	

	
	
	/**
	 * 验证用户是否登录
	 * @param session
	 * @return
	 */
	public static boolean isLogin(){
		return UserContext.getRequest().getSession().getAttribute(LOGIN_USER_INFO)==null;
	}
	
	/**
	 * 获取登录用户
	 * @return
	 */
	public static TEmpUserDto getLoginUser(){
		LoginUserInfo loginUserInfo =LoginUserInfoUtil.getLoginUserInfo();
		if(	loginUserInfo!=null&&loginUserInfo.getLoginUser() != null){
			return loginUserInfo.getLoginUser();
		}
		return null;
	}
	
	/**
	 * 获取登录用户 USERCD
	 * @param request
	 * @return
	 */
	public static String getLoginUserCD()
	{
		TEmpUserDto empUserDto = LoginUserInfoUtil.getLoginUser();
        if(empUserDto!=null){
        	return empUserDto.getUser_cd();
        }
		return null;
	}
	

	/**
	 * 通过编码验证用户权限
	 * @return true:权限验证通过  false验证未通过
	 */
	public static boolean checkRightByResCode(String resCode){
		 Map<String, TResourceDto>  curResIdResourceMap= LoginUserInfoUtil.getCurResCodeResourceMap();
		 return curResIdResourceMap.get(resCode)==null;
	}
	
	/**
	 * 通过url验证用户权限
	 * @return true:权限验证通过  false验证未通过
	 */
	public static boolean checkRightByUrl(String url)
	{
		//验证权限是否在控制列表中
		if(CacheDataSet.allUrlMap.get(url) == null){
			return false;
		}	
		//获取登录用户url权限
		Map<String, TResourceDto> curResUrlResourceMap = LoginUserInfoUtil.getCurResUrlResourceMap();
		return curResUrlResourceMap.get(url) == null;
	}
	
	
	/***
	 * 资源MAP列表    KEY=RES_CODE
	 * @return
	 */
	private static Map<String, TResourceDto> getCurResCodeResourceMap(){
		LoginUserInfo loginUserInfo = getLoginUserInfo();
		if(loginUserInfo!=null){
			return loginUserInfo.getCurResCodeResourceMap();
		}
		return null;
	}
	
	/** 
	 * 资源MAP列表   KEY=RES_URL
	 * @return
	 */
	private static Map<String, TResourceDto> getCurResUrlResourceMap(){
		LoginUserInfo loginUserInfo = getLoginUserInfo();
		if(loginUserInfo!=null){
			return loginUserInfo.getCurResUrlResourceMap();
		}
		return null;
	}
	
	/**
	 * 角色MAP列表 KEY = ROLE_ID
	 * @return
	 */
	private static  Map<String, TRoleDto> getCurRoleMap(){
		LoginUserInfo loginUserInfo = getLoginUserInfo();
		if(loginUserInfo!=null){
			return loginUserInfo.getCurRoleMap();
		}
		return null;
	}
	
	/**
	 * LoginUserInfo
	 * @return
	 */
	private static  LoginUserInfo getLoginUserInfo(){
		LoginUserInfo loginUserInfo = (LoginUserInfo)UserContext.getRequest().getSession().getAttribute(LOGIN_USER_INFO);
		return loginUserInfo;
	}
	
	
	/**
	 * 用户登出,清除session中记录的用户登录以及权限数据
	 */
	public static void logout(){
		UserContext.getRequest().getSession().removeAttribute(LOGIN_USER_INFO);
	}
	
}
