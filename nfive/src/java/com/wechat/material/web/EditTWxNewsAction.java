package com.wechat.material.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.service.ITWxNewsService;
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
@Controller("editTWxNewsAction")
@Scope("prototype")
public class EditTWxNewsAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxNewsService wxNewsService;
	private TWxNewsDto tWxNewsDto = new TWxNewsDto();;

	public String execute() throws Exception {
		try {

			TWxNewsDto tmpTWxNewsDto = new TWxNewsDto();

			tmpTWxNewsDto.setNews_id(tWxNewsDto.getNews_id());
			tmpTWxNewsDto.setMedia_id(tWxNewsDto.getMedia_id());
			tmpTWxNewsDto.setTitle(tWxNewsDto.getTitle());
			tmpTWxNewsDto.setThumb_media_id(tWxNewsDto.getThumb_media_id());
			tmpTWxNewsDto.setShow_cover_pic(tWxNewsDto.getShow_cover_pic());
			tmpTWxNewsDto.setAuthor(tWxNewsDto.getAuthor());
			tmpTWxNewsDto.setDigest(tWxNewsDto.getDigest());
			tmpTWxNewsDto.setContent(tWxNewsDto.getContent());
			tmpTWxNewsDto.setUrl(tWxNewsDto.getUrl());
			tmpTWxNewsDto.setContent_source_url(tWxNewsDto.getContent_source_url());
			tmpTWxNewsDto.setUpdate_time(tWxNewsDto.getUpdate_time());

            wxNewsService.updatePK(tWxNewsDto);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public final TWxNewsDto getTWxNewsDto() {
		return tWxNewsDto;
	}

	public final void setTWxNewsDto(TWxNewsDto tWxNewsDto) {
		this.tWxNewsDto = tWxNewsDto;
	}

}
