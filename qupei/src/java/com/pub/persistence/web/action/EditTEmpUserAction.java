package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TEmpUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITEmpUserService;

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
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	public final TEmpUserDto getTEmpUserDto() {
		return tEmpUserDto;
	}

	public final void setTEmpUserDto(TEmpUserDto tEmpUserDto) {
		this.tEmpUserDto = tEmpUserDto;
	}

}
