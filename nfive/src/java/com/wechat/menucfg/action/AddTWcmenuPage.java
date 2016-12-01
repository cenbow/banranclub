package com.wechat.menucfg.action;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;

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

@Controller("addTWcmenuPage")
@Scope("prototype")
public class AddTWcmenuPage extends BaseAction {
	private static final Logger logger = Logger.getLogger(AddTWcmenuPage.class);
	private static final long serialVersionUID = 1L;
	private String parent_id;

	public String execute() throws Exception {

		try {
			// 获取下拉框节点
			List<SelectCsBean> menuTypeItems = CodeStringUtil
					.getSelectCsBeanByCsType(CodeStringConstant.MENU_TYPE,
							CodeStringConstant.MENU_TYPE_NOTINPUT);
			for (SelectCsBean selectCsBean : menuTypeItems) {
				if (StringUtils.isBlank(selectCsBean.getKey())) {
					selectCsBean.setKey(CodeStringConstant.MENU_TYPE_NOTINPUT);
					break;
				}
			}
			//构造启用客服模式下拉列表数据
			List<SelectCsBean>	cust_srv_flag_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,CodeStringConstant.CS_1000_FALSE);
			
			
			request.setAttribute("menuTypeItems", menuTypeItems);
			request.setAttribute("cust_srv_flag_SelList", cust_srv_flag_SelList);
			return SUCCESS;
		} catch (Exception ex) {
			logger.error("------新增菜单异常------\r\n" + ex);
		}

		return ERROR;

	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

}
