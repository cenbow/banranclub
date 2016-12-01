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
@Controller("editTActUsersAction")
@Scope("prototype")
public class EditTActUsersAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITActUsersService actUsersService;
	private TActUsersDto tActUsersDto = new TActUsersDto();;

	public String execute() throws Exception {
		try {

			TActUsersDto tmpTActUsersDto = new TActUsersDto();

			tmpTActUsersDto.setUser_id(tActUsersDto.getUser_id());
			tmpTActUsersDto.setOpenid(tActUsersDto.getOpenid());
			tmpTActUsersDto.setNickname(tActUsersDto.getNickname());
			tmpTActUsersDto.setSubscribe(tActUsersDto.getSubscribe());
			tmpTActUsersDto.setSex(tActUsersDto.getSex());
			tmpTActUsersDto.setLanguage(tActUsersDto.getLanguage());
			tmpTActUsersDto.setCity(tActUsersDto.getCity());
			tmpTActUsersDto.setProvince(tActUsersDto.getProvince());
			tmpTActUsersDto.setCountry(tActUsersDto.getCountry());
			tmpTActUsersDto.setHeadimgurl(tActUsersDto.getHeadimgurl());
			tmpTActUsersDto.setSubscribe_time(tActUsersDto.getSubscribe_time());
			tmpTActUsersDto.setUnionid(tActUsersDto.getUnionid());
			tmpTActUsersDto.setRemark(tActUsersDto.getRemark());
			tmpTActUsersDto.setAddress(tActUsersDto.getAddress());
			tmpTActUsersDto.setMobile(tActUsersDto.getMobile());

            actUsersService.updatePK(tActUsersDto);

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
