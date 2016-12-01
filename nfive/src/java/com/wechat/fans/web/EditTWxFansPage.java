package com.wechat.fans.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.TWxFansDto;
import com.wechat.fans.service.ITWxFansService;
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
@Controller("editTWxFansPage")
@Scope("prototype")
public class EditTWxFansPage extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxFansService wxFansService;

	// 入参
	private String pkid;

	// 出参
	private TWxFansDto tWxFansDto;

	public String execute() throws Exception {
		try {
			TWxFansDto paramTWxFansDto = new TWxFansDto();

			//设置主键
			paramTWxFansDto.setFans_id(pkid);
			tWxFansDto = wxFansService.getRow(paramTWxFansDto);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TWxFansDto getTWxFansDto() {
		return tWxFansDto;
	}

	public final void setTWxFansDto(TWxFansDto tWxFansDto) {
		this.tWxFansDto = tWxFansDto;
	}

	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
