package com.wechat.fans.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.TWxFansDto;
import com.wechat.fans.service.ITWxFansService;
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
@Controller("delTWxFansAction")
@Scope("prototype")
public class DelTWxFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxFansService wxFansService;

	private String pkid;

	public String execute() throws Exception {
		try {
			TWxFansDto paramTWxFansDto = new TWxFansDto();

			//设置主键
			paramTWxFansDto.setFans_id(pkid);

            wxFansService.deletePK(paramTWxFansDto);
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
