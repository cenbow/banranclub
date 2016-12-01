package com.wechat.pfusercfg.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;

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
 
@Controller("addTPlatformempCfgPage")
@Scope("prototype")
public class AddTPlatformempCfgPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	
	public String platform_id;
	public String pkid;

	public String execute() throws Exception {

		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}
	
	/**
	 * 为用户绑定公众号
	 * @return
	 */
	public String configTPlatformCfg()
	{
		try {
			request.setAttribute("userCd", pkid);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platformId) {
		platform_id = platformId;
	}

	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
