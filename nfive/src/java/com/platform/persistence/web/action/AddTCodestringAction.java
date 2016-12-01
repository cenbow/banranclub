package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TCodestringDto;
import com.platform.persistence.service.ITCodestringService;

 /**
 * 类功能:自动代码生成模板新增   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTCodestringAction")
@Scope("prototype")
public class AddTCodestringAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITCodestringService tCodestringService;
	private TCodestringDto tCodestringDto= new TCodestringDto();

	public String execute() throws Exception {
		try {
			tCodestringService.save(tCodestringDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

     public TCodestringDto gettCodestringDto() {
         return tCodestringDto;
     }

     public void settCodestringDto(TCodestringDto tCodestringDto) {
         this.tCodestringDto = tCodestringDto;
     }
 }
