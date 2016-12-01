package com.wechat.activity.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.activity.model.TActUsersDto;
import com.wechat.activity.service.ITActUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


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
@Controller("editTActUsersPage")
@Scope("prototype")
public class EditTActUsersPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITActUsersService actUsersService;

	// 入参
	private String pkid;

	// 出参
	private TActUsersDto tActUsersDto;

	public String execute() throws Exception {
		try {
			TActUsersDto paramTActUsersDto = new TActUsersDto();

			//设置主键
			paramTActUsersDto.setUser_id(pkid);
			tActUsersDto = actUsersService.getRow(paramTActUsersDto);

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

	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
