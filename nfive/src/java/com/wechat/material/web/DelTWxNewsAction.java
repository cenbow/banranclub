package com.wechat.material.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.service.ITWxNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * 类功能:自动代码生成模板删除   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("delTWxNewsAction")
@Scope("prototype")
public class DelTWxNewsAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxNewsService wxNewsService;

	private String pkid;

	public String execute() throws Exception {
		try {
			TWxNewsDto paramTWxNewsDto = new TWxNewsDto();

			//设置主键
			paramTWxNewsDto.setNews_id(pkid);

            wxNewsService.deletePK(paramTWxNewsDto);
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
