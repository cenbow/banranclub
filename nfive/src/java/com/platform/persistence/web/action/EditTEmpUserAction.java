package com.platform.persistence.web.action;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.persistence.dao.ITEmpExtinfoDao;
import com.platform.persistence.model.dto.TEmpExtinfoDto;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.service.ITEmpUserService;

 /**
 * 类功能:csr员工编辑
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTEmpUserAction")
@Scope("prototype")
public class EditTEmpUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	private TEmpUserDto tEmpUserDto =new TEmpUserDto();;
	@Autowired
	private ITEmpExtinfoDao  tEmpExtinfoDao; 
	
	public String execute() throws Exception {
		try {
		    
		      TEmpUserDto tmpTEmpUserDto = new TEmpUserDto();
			  tmpTEmpUserDto.setUser_id(tEmpUserDto.getUser_id());
			  tmpTEmpUserDto.setUser_cd(tEmpUserDto.getUser_cd());
			  tmpTEmpUserDto.setUser_name(tEmpUserDto.getUser_name());
			  tmpTEmpUserDto.setMobile(tEmpUserDto.getMobile());
			  tmpTEmpUserDto.setEmail(tEmpUserDto.getEmail());
			  tmpTEmpUserDto.setLock_flag(tEmpUserDto.getLock_flag());
			  tmpTEmpUserDto.setCreated_date(tEmpUserDto.getCreated_date());
			  tmpTEmpUserDto.setCreated_user_cd(tEmpUserDto.getCreated_user_cd());
			  //获取锁定状态
			  if(tmpTEmpUserDto.getLock_flag() == null || tmpTEmpUserDto.getLock_flag().trim().length() <= 0)
			  {
				 //未锁定
				 tmpTEmpUserDto.setLock_flag("300000000001");
			  }
			  tEmpUserService.updatePK(tmpTEmpUserDto);
			  
			  //解锁的情况下,进行清空尝试次数
			  if(tmpTEmpUserDto.getLock_flag().equals("300000000001")){
				  TEmpExtinfoDto extInfo = new TEmpExtinfoDto();
				  extInfo.setUser_cd(tEmpUserDto.getUser_cd());
				  extInfo = tEmpExtinfoDao.getRow(extInfo);
				  
				  TEmpExtinfoDto updEmpExtinfoDto = new TEmpExtinfoDto();
				  updEmpExtinfoDto.setExt_id(extInfo.getExt_id());
				  updEmpExtinfoDto.setLogin_try_times(BigDecimal.ZERO);
				  updEmpExtinfoDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
				  tEmpExtinfoDao.updatePK(updEmpExtinfoDto);
			  }
			
			  return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	public final TEmpUserDto getTEmpUserDto() {
		return tEmpUserDto;
	}

	public final void setStudentDto(TEmpUserDto tEmpUserDto) {
		this.tEmpUserDto = tEmpUserDto;
	}

}
