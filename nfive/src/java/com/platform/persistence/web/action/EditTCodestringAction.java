package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TCodestringDto;
import com.platform.persistence.service.ITCodestringService;

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
 
@Controller("editTCodestringAction")
@Scope("prototype")
public class EditTCodestringAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITCodestringService tCodestringService;
	private TCodestringDto tCodestringDto =new TCodestringDto();;

	public String execute() throws Exception {
		try {
		    
		      TCodestringDto tmpTCodestringDto = new TCodestringDto();
			  tmpTCodestringDto.setCs_code(tCodestringDto.getCs_code());
			  tmpTCodestringDto.setCs_type(tCodestringDto.getCs_type());
			  tmpTCodestringDto.setCs_sub_type(tCodestringDto.getCs_sub_type());
			  tmpTCodestringDto.setCs_name(tCodestringDto.getCs_name());
			  tmpTCodestringDto.setCs_value(tCodestringDto.getCs_value());
			  tmpTCodestringDto.setCs_desc(tCodestringDto.getCs_desc());
			  tmpTCodestringDto.setCreated_date(tCodestringDto.getCreated_date());
			  tmpTCodestringDto.setCreated_user_cd(tCodestringDto.getCreated_user_cd());
		
			tCodestringService.updatePK(tCodestringDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TCodestringDto getTCodestringDto() {
		return tCodestringDto;
	}

	public final void setStudentDto(TCodestringDto tCodestringDto) {
		this.tCodestringDto = tCodestringDto;
	}

}
