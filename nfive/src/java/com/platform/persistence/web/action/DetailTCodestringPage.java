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
 
@Controller("detailTCodestringPage")
@Scope("prototype")
public class DetailTCodestringPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITCodestringService tCodestringService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TCodestringDto tCodestringDto;

	
	public String execute() throws Exception {
		try {
		    TCodestringDto paramTCodestringDto = new TCodestringDto();
		//设置主键
			paramTCodestringDto.setCs_code(pkid);
			tCodestringDto = tCodestringService.getRow(paramTCodestringDto );
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
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
