package com.wechat.activity.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.activity.model.TActUsersDto;
import com.wechat.activity.service.ITActUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


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
@Controller("addTActUsersAction")
@Scope("prototype")
public class AddTActUsersAction extends BaseAction {

	@Autowired
	private ITActUsersService actUsersService;

	private TActUsersDto tActUsersDto = new TActUsersDto();

	public String execute() throws Exception {
		try {
            actUsersService.save(tActUsersDto);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;
	}

	public final TActUsersDto getTActUsersDto() {
		return tActUsersDto;
	}

	public final void setTActUsersDto(TActUsersDto tActUsersDto) {
		this.tActUsersDto = tActUsersDto;
	}

}
