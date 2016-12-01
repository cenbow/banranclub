package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TResSelfRelaDto;
import com.platform.persistence.service.ITResSelfRelaService;

 /**
 * 类功能:删除资源关系
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTResSelfRelaAction")
@Scope("prototype")
public class DelTResSelfRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResSelfRelaService tResSelfRelaService;
	
	private  String pkid;

	public String execute() throws Exception {
		try {
			TResSelfRelaDto paramTResSelfRelaDto = new TResSelfRelaDto();
			
		//设置主键
			paramTResSelfRelaDto.setRes_relation_id(pkid);
			tResSelfRelaService.deletePK(paramTResSelfRelaDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
