package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TResSelfRelaDto;
import com.platform.persistence.service.ITResSelfRelaService;

 /**
 * 类功能:跳转到编辑资源关系数据页面
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTResSelfRelaPage")
@Scope("prototype")
public class EditTResSelfRelaPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResSelfRelaService tResSelfRelaService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TResSelfRelaDto tResSelfRelaDto;

	
	public String execute() throws Exception {
		try {
		    TResSelfRelaDto paramTResSelfRelaDto = new TResSelfRelaDto();
		//设置主键
			paramTResSelfRelaDto.setRes_relation_id(pkid);
			tResSelfRelaDto = tResSelfRelaService.getRow(paramTResSelfRelaDto );
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TResSelfRelaDto getTResSelfRelaDto() {
		return tResSelfRelaDto;
	}

	public final void setStudentDto(TResSelfRelaDto tResSelfRelaDto) {
		this.tResSelfRelaDto = tResSelfRelaDto;
	}
	
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
