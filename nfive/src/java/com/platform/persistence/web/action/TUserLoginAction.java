package com.platform.persistence.web.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.activity.ActivityUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.PubPlatformBean;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.service.ITEmpUserService;

/**
 * 类功能:用户登录
 * <p>
 * 创建者:zhangzhiqiang
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */
@Controller("tUserLoginAction")
@Scope("prototype")
public class TUserLoginAction extends BaseAction {
	private static final long serialVersionUID = -6379668889811321384L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	//登录帐号
	private String usercd;  

	//登录密码
	private String password;
	
	//登录跳转key
	private String forward;

	private TEmpUserDto tEmpUserDto = new TEmpUserDto();

	//入参 切换默认绑定的platform_id.
	private String platform_id ;

	/**
	 * 欢迎画面
	 * @return
	 */
	public String welcome()
	{
		return SUCCESS;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	public String login() {
		try {
			if(StringUtils.isNotBlank(usercd) || StringUtils.isNotBlank(password))
			{
				tEmpUserDto.setUser_cd(usercd);
				tEmpUserDto.setPassword(password);
			}
			String ipAddress = ActivityUtil.getIpAddress(this.getRequest());
			String result = tEmpUserService.login(tEmpUserDto,ipAddress);
			if(StringUtils.isBlank(usercd))
			{
				outJSOND(response, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(forward))
		{
			return SUCCESS;	
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * 用户登录切换
	 * @return
	 */
	public String loginChange() {
		try {
			String result = tEmpUserService.loginChange();
			outJSOND(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return SUCCESS;	

	}
	
	//用户切换公众号
	public String loginChangePlatform() {
		try {
			PubPlatformBean pubPlatformBean=WechatInfoUtil.getCurPubPlatformBeanFromSession();//从登录用户的SESSION获取当前作用的公众号
			//3个参数。修改的员工CD，当前使用公众号ID，切换公众号ID
			tEmpUserService.changePlatform(LoginUserInfoUtil.getLoginUserCD(), pubPlatformBean.getPlatform_id(), platform_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 用户登出
	 * @return
	 */
	public String logout() {
		try {
			LoginUserInfoUtil.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public TEmpUserDto getTEmpUserDto() {
		return tEmpUserDto;
	}

	public void setTEmpUserDto(TEmpUserDto empUserDto) {
		tEmpUserDto = empUserDto;
	}
	
	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platformId) {
		platform_id = platformId;
	}
	
	public String getUsercd() {
		return usercd;
	}

	public void setUsercd(String usercd) {
		this.usercd = usercd;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}
}
