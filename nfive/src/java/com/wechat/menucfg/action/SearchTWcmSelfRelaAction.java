package com.wechat.menucfg.action;

import com.platform.common.tools.wechat.WechatUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.menucfg.bean.MenuTree;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.service.ITWcmSelfRelaService;

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

@Controller("searchTWcmSelfRelaAction")
@Scope("prototype")
public class SearchTWcmSelfRelaAction extends BaseAction {
	private static final Logger logger = Logger
			.getLogger(SearchTWcmSelfRelaAction.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWcmSelfRelaService tWcmSelfRelaService;

	private TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean = new TWcmSelfRelaQueryBean();

	public String execute() throws Exception {
		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	/***
	 *
	 * @return
	 * @throws Exception
	 */
	public String getListData() {

		try {
			if (tWcmSelfRelaQueryBean == null) {
				tWcmSelfRelaQueryBean = new TWcmSelfRelaQueryBean();
			}
			tWcmSelfRelaQueryBean.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());
			// 获取树
			MenuTree tree = tWcmSelfRelaService
					.queryTWcmSelfRelaDtoList(tWcmSelfRelaQueryBean);
			// 3.组合输出列表查询所需数据
			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
			JSONArray json_rows = JSONArray.fromObject(tree, config);
			logger.info("------叶子节点json格式:\r\n" + json_rows.toString());
			outJSOND(this.getResponse(), json_rows.toString());

		} catch (Exception ex) {
			logger.error("------获取菜单树结构异常------\r\n",ex);
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
