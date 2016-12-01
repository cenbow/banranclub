package com.pub.persistence.web.action;

import java.util.List;

import com.pub.common.local.model.dto.TRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.common.tools.permission.LoginUserInfoUtil;
import com.pub.persistence.service.ITRoleService;
 /**
 * 类功能:角色数据编辑
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTRoleAction")
@Scope("prototype")
public class EditTRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleService tRoleService;
	private TRoleDto tRoleDto =new TRoleDto();;

	public String execute() throws Exception {
		try {
			 TRoleDto tmpTRoleDto = new TRoleDto();
			 tmpTRoleDto.setRole_id(tRoleDto.getRole_id());
			 List<TRoleDto> roleList = tRoleService.getAll(tmpTRoleDto);
			 if(roleList != null && roleList.size() > 0)
			 {
				 tmpTRoleDto = roleList.get(0);
				 tmpTRoleDto.setRole_name(tRoleDto.getRole_name());
				 tmpTRoleDto.setRole_desc(tRoleDto.getRole_desc());
				 tmpTRoleDto.setRole_group(tRoleDto.getRole_group());
				 tmpTRoleDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
				 tRoleService.updatePK(tmpTRoleDto);
			 }
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	public final TRoleDto getTRoleDto() {
		return tRoleDto;
	}

	public final void setTRoleDto(TRoleDto tRoleDto) {
		this.tRoleDto = tRoleDto;
	}

}
