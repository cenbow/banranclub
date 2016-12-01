package com.wechat.pfusercfg.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;

 /**
 * 类功能:自动代码生成模板编辑   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTPlatformempCfgAction")
@Scope("prototype")
public class EditTPlatformempCfgAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	private TPlatformempCfgDto tPlatformempCfgDto =new TPlatformempCfgDto();;

	public String execute() throws Exception {
		try {
		    
		      TPlatformempCfgDto tmpTPlatformempCfgDto = new TPlatformempCfgDto();
			  tmpTPlatformempCfgDto.setCfg_id(tPlatformempCfgDto.getCfg_id());
			  tmpTPlatformempCfgDto.setPlatform_id(tPlatformempCfgDto.getPlatform_id());
			  tmpTPlatformempCfgDto.setUser_cd(tPlatformempCfgDto.getUser_cd());
			  tmpTPlatformempCfgDto.setIs_use(tPlatformempCfgDto.getIs_use());
			  tmpTPlatformempCfgDto.setIs_valid(tPlatformempCfgDto.getIs_valid());
			  tmpTPlatformempCfgDto.setCreated_date(tPlatformempCfgDto.getCreated_date());
			  tmpTPlatformempCfgDto.setCreated_user_cd(tPlatformempCfgDto.getCreated_user_cd());
		
			tPlatformempCfgService.updatePK(tPlatformempCfgDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TPlatformempCfgDto getTPlatformempCfgDto() {
		return tPlatformempCfgDto;
	}

	public final void setStudentDto(TPlatformempCfgDto tPlatformempCfgDto) {
		this.tPlatformempCfgDto = tPlatformempCfgDto;
	}

}
