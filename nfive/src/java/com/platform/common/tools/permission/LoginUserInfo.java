package com.platform.common.tools.permission;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.platform.persistence.model.TResourceQueryBean;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.model.dto.TRoleDto;

/***
 * 登陆用户信息
 * 用户记录登录用户的基本信息
 * @author jia.chen
 */
public class LoginUserInfo implements  Serializable{
	
	//用户信息
	private  TEmpUserDto 	loginUser;
	//资源列表 RES_ID=KEY
	private  Map<String,TResourceDto>  curResCodeResourceMap = null;
	//资源列表 RES_URL=KEY
	private  Map<String,TResourceDto>  curResUrlResourceMap = null;
	//角色列表 
	private  Map<String,TRoleDto>  	 curRoleMap	=  null; 
	
	 
	
	/**
	 * 
	 * @param empUserDto
	 * @param tResourceList
	 * @param roleList
	 */
	public LoginUserInfo(TEmpUserDto loginUser,List<TResourceDto> tResourceList,List<TRoleDto> roleList){
		this.initData(loginUser,tResourceList,roleList);
	}
	
	private LoginUserInfo(){};

	/***
	 * 
	 * @param empUserDto
	 * @param tResourceList
	 * @param roleList
	 */
	private void initData(TEmpUserDto loginUser,List<TResourceDto> tResourceList,List<TRoleDto> roleList)
	{
		this.loginUser =loginUser;		
		//运用 curResIdResourceMap 初始化  URL_KEY列表
		curResCodeResourceMap =	 new  Hashtable<String,TResourceDto>();
		curResUrlResourceMap =   new  Hashtable<String,TResourceDto>();
		curRoleMap			 = new Hashtable<String,TRoleDto>();
		 
		 //获取绑定的权限信息
		 TResourceQueryBean tResourceQueryBean = new TResourceQueryBean();
		 tResourceQueryBean.setUser_id(loginUser.getUser_id());
		
		 //初始化资源数据
		 if(tResourceList != null){
			 for(TResourceDto tResourceDto : tResourceList){
				 if(StringUtils.isNotEmpty(tResourceDto.getRes_code())){
					 curResCodeResourceMap.put(tResourceDto.getRes_code(), tResourceDto);
				 }
				 
				 if(StringUtils.isNotEmpty(tResourceDto.getRes_url())){
					 curResUrlResourceMap.put(tResourceDto.getRes_url(), tResourceDto);
				 }
			 }
		 }
		 
		 //初始化角色数据
		 if(roleList != null)
		 {
			 for(TRoleDto role : roleList){	 
				 if(StringUtils.isNotEmpty(role.getRole_id())){
					 curRoleMap.put(role.getRole_id(),role);
				 }
			 }
		 }
		 
	
	}

	
	//对外的获取

	
	

	public TEmpUserDto getLoginUser() {
		return loginUser;
	}

	public Map<String, TResourceDto> getCurResCodeResourceMap() {
		return curResCodeResourceMap;
	}

	public  Map<String, TResourceDto> getCurResUrlResourceMap() {
		return curResUrlResourceMap;
	}

	public  Map<String, TRoleDto> getCurRoleMap() {
		return curRoleMap;
	}

	
	
	
}
