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
@Controller("detailTWxPlatformPage")
@Scope("prototype")
public class DetailTWxPlatformPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxPlatformDao wxPlatformDao;

	// 入参
	private String pkid;

	// 出参
	private TWxPlatformDto tWxPlatformDto;

	public String execute() throws Exception {
		try {
			TWxPlatformDto paramTWxPlatformDto = new TWxPlatformDto();

			//设置主键
			paramTWxPlatformDto.setPlatform_id(pkid);

			tWxPlatformDto = wxPlatformDao.getRow(paramTWxPlatformDto);
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

	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
