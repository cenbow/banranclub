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
@Controller("editTWxFansAction")
@Scope("prototype")
public class EditTWxFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxFansService wxFansService;
	private TWxFansDto tWxFansDto = new TWxFansDto();;

	public String execute() throws Exception {
		try {

			TWxFansDto tmpTWxFansDto = new TWxFansDto();

			tmpTWxFansDto.setFans_id(tWxFansDto.getFans_id());
			tmpTWxFansDto.setOpenid(tWxFansDto.getOpenid());
			tmpTWxFansDto.setNickname(tWxFansDto.getNickname());
			tmpTWxFansDto.setSubscribe(tWxFansDto.getSubscribe());
			tmpTWxFansDto.setSex(tWxFansDto.getSex());
			tmpTWxFansDto.setLanguage(tWxFansDto.getLanguage());
			tmpTWxFansDto.setCity(tWxFansDto.getCity());
			tmpTWxFansDto.setProvince(tWxFansDto.getProvince());
			tmpTWxFansDto.setCountry(tWxFansDto.getCountry());
			tmpTWxFansDto.setHeadimgurl(tWxFansDto.getHeadimgurl());
			tmpTWxFansDto.setSubscribe_time(tWxFansDto.getSubscribe_time());
			tmpTWxFansDto.setUnionid(tWxFansDto.getUnionid());
			tmpTWxFansDto.setRemark(tWxFansDto.getRemark());

			wxFansService.updatePK(tWxFansDto);

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

}
