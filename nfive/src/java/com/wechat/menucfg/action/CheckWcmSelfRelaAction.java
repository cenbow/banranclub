package com.wechat.menucfg.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;
import com.wechat.menucfg.service.ITWcmSelfRelaService;
import com.wechat.menucfg.util.JsonDataUtil;

/**
 * 类功能:自动代码生成模板查询 action 模板
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

@Controller("checkWcmSelfRelaAction")
@Scope("prototype")
public class CheckWcmSelfRelaAction extends BaseAction {
	private static final Logger logger = Logger
			.getLogger(CheckWcmSelfRelaAction.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWcmSelfRelaService tWcmSelfRelaService;

	private TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean = new TWcmSelfRelaQueryBean();

	public String execute() throws Exception {
		try {
			if (tWcmSelfRelaQueryBean == null) {
				outJSOND(getResponse(), JsonDataUtil.parseJson("页面传值异常!",
						Boolean.FALSE));
			}
			TWcmSelfRelaDto tsrd = new TWcmSelfRelaDto();
			tsrd.setMenu_relation_id(tWcmSelfRelaQueryBean
					.getMenu_relation_id());
			// 查询数据库是否存在该记录
			tsrd = tWcmSelfRelaService.getRow(tsrd);
			if (null == tsrd) {
				outJSOND(getResponse(), JsonDataUtil.parseJson("该菜单节点不存在!",
						Boolean.FALSE));
			}
			outJSOND(getResponse(), JsonDataUtil.parseJson("查询成功!",
					Boolean.TRUE));
		} catch (Exception e) {
			logger.error("------校验菜单节点异常------\r\n" + e);
		}
		return null;
	}

	public TWcmSelfRelaQueryBean getTWcmSelfRelaQueryBean() {
		return tWcmSelfRelaQueryBean;
	}

	public void setTWcmSelfRelaQueryBean(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean) {
		this.tWcmSelfRelaQueryBean = tWcmSelfRelaQueryBean;
	}

}
