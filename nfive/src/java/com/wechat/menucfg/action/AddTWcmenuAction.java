package com.wechat.menucfg.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.menucfg.util.JsonDataUtil;

/**
 * 类功能:自动代码生成模板新增 action 模板
 * <p>
 * 创建者:
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */

@Controller("addTWcmenuAction")
@Scope("prototype")
public class AddTWcmenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(AddTWcmenuAction.class);

	@Autowired
	private ITWcmenuService tWcmenuService;

	private TWcmenuQueryBean tWcmenuQueryBean = new TWcmenuQueryBean();

	public String execute() throws Exception {
		try {
			
			tWcmenuService.addWcmSelfRela(tWcmenuQueryBean);
			// 输出data
			outJSOND(getResponse(), JsonDataUtil
					.parseJson(tWcmenuQueryBean.getId(), tWcmenuQueryBean
							.getParent_id(), tWcmenuQueryBean.getMenu_id(),
							tWcmenuQueryBean.getMenu_name(), String
									.valueOf(tWcmenuQueryBean.getRela_sort()),
							false, tWcmenuQueryBean.getAction_type(),
							tWcmenuQueryBean.getMaterial_id(), tWcmenuQueryBean
									.getAction_url()));
			return null;
		} catch (Exception ex) {
			logger.error("------新增菜单异常------\r\n",ex);
		}
		return ERROR;
	}

	public TWcmenuQueryBean getTWcmenuQueryBean() {
		return tWcmenuQueryBean;
	}

	public void setTWcmenuQueryBean(TWcmenuQueryBean tWcmenuQueryBean) {
		this.tWcmenuQueryBean = tWcmenuQueryBean;
	}
}
