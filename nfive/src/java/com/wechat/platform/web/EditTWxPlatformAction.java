package com.wechat.platform.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.platform.dao.ITWxPlatformDao;
import com.wechat.platform.model.TWxPlatformDto;
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
@Controller("editTWxPlatformAction")
@Scope("prototype")
public class EditTWxPlatformAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxPlatformDao wxPlatformDao;
	private TWxPlatformDto tWxPlatformDto = new TWxPlatformDto();;

	public String execute() throws Exception {
		try {

			TWxPlatformDto tmpTWxPlatformDto = new TWxPlatformDto();

			tmpTWxPlatformDto.setPlatform_id(tWxPlatformDto.getPlatform_id());
			tmpTWxPlatformDto.setPlatform_account(tWxPlatformDto.getPlatform_account());
			tmpTWxPlatformDto.setPlatform_name(tWxPlatformDto.getPlatform_name());
			tmpTWxPlatformDto.setPlatform_type(tWxPlatformDto.getPlatform_type());
			tmpTWxPlatformDto.setApp_id(tWxPlatformDto.getApp_id());
			tmpTWxPlatformDto.setApp_secret(tWxPlatformDto.getApp_secret());
			tmpTWxPlatformDto.setToken(tWxPlatformDto.getToken());

            wxPlatformDao.updatePK(tWxPlatformDto);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TWxPlatformDto getTWxPlatformDto() {
		return tWxPlatformDto;
	}

	public final void setTWxPlatformDto(TWxPlatformDto tWxPlatformDto) {
		this.tWxPlatformDto = tWxPlatformDto;
	}

}
