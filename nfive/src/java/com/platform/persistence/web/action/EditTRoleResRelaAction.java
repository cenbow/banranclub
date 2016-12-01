package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TRoleResRelaDto;
import com.platform.persistence.service.ITRoleResRelaService;

 /**
 * 类功能:角色资源关系编辑
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTRoleResRelaAction")
@Scope("prototype")
public class EditTRoleResRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleResRelaService tRoleResRelaService;
	private TRoleResRelaDto tRoleResRelaDto =new TRoleResRelaDto();;

	public String execute() throws Exception {
		try {
		    
		      TRoleResRelaDto tmpTRoleResRelaDto = new TRoleResRelaDto();
			  tmpTRoleResRelaDto.setRole_res_id(tRoleResRelaDto.getRole_res_id());
			  tmpTRoleResRelaDto.setRole_id(tRoleResRelaDto.getRole_id());
			  tmpTRoleResRelaDto.setRes_id(tRoleResRelaDto.getRes_id());
			  tmpTRoleResRelaDto.setCreated_date(tRoleResRelaDto.getCreated_date());
			  tmpTRoleResRelaDto.setCreated_user_cd(tRoleResRelaDto.getCreated_user_cd());
		
			tRoleResRelaService.updatePK(tRoleResRelaDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TRoleResRelaDto getTRoleResRelaDto() {
		return tRoleResRelaDto;
	}

	public final void setStudentDto(TRoleResRelaDto tRoleResRelaDto) {
		this.tRoleResRelaDto = tRoleResRelaDto;
	}

}
