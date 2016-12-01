package com.wechat.menucfg.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.service.ITWcmSelfRelaService;
import com.wechat.menucfg.util.JsonDataUtil;

/**
 * 类功能:自动代码生成模板删除 action 模板
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

@Controller("delTWcmSelfRelaAction")
@Scope("prototype")
public class DelTWcmSelfRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(DelTWcmSelfRelaAction.class);

	@Autowired
	private ITWcmSelfRelaService tWcmSelfRelaService;

	private TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean = new TWcmSelfRelaQueryBean();

	public String execute() throws Exception {
		try {
			tWcmSelfRelaService.deleteTWcmSelfRela(tWcmSelfRelaQueryBean);
			outJSOND(getResponse(), JsonDataUtil.parseJson("删除菜单成功!",
					Boolean.TRUE));
			return null;
		} catch (Exception ex) {
			logger.error("------删除菜单异常------\r\n", ex);
		}
		return ERROR;
	}

	public TWcmSelfRelaQueryBean getTWcmSelfRelaQueryBean() {
		return tWcmSelfRelaQueryBean;
	}

	public void setTWcmSelfRelaQueryBean(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean) {
		this.tWcmSelfRelaQueryBean = tWcmSelfRelaQueryBean;
	}

}
