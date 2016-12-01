package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TResSelfRelaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITResSelfRelaService;

 /**
 * 类功能:添加资源关系数据
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTResSelfRelaAction")
@Scope("prototype")
public class AddTResSelfRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResSelfRelaService tResSelfRelaService;
	private TResSelfRelaDto tResSelfRelaDto= new TResSelfRelaDto();

	public String execute() throws Exception {
		try {
			tResSelfRelaService.save(tResSelfRelaDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TResSelfRelaDto getTResSelfRelaDto() {
		return tResSelfRelaDto;
	}

	public final void setTResSelfRelaDto(TResSelfRelaDto tResSelfRelaDto) {
		this.tResSelfRelaDto = tResSelfRelaDto;
	}

}
