package com.wechat.activity.web;

import java.util.List;
import java.util.Map;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.activity.model.TActUsersQueryBean;
import com.wechat.activity.service.ITActUsersService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;

/**
 * 类功能:自动代码生成模板查询   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("searchTActUsersAction")
@Scope("prototype")
public class SearchTActUsersAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITActUsersService actUsersService;

	private TActUsersQueryBean tActUsersQueryBean = new TActUsersQueryBean();

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
			if (tActUsersQueryBean == null) {
				tActUsersQueryBean = new TActUsersQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = actUsersService.queryTActUsersListPage(tActUsersQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tActUsers_list.updateFormSubmit('"+ d.get("USER_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tActUsers_list.detailFormSubmit('"+ d.get("USER_ID")+ "');return false;\"><i class='icon-search'></i></a>");
			}

			// 3.组合输出列表查询所需数据
			// JsonConfig 用于解析转换的设置
			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

			JSONArray json_rows = JSONArray.fromObject(rows, config);
			String json = "{\"total\":\"" + pageResult.getPagingObject().getTotal_record() + "\",\"rows\":" + json_rows.toString() + "}";
			outJSOND(this.getResponse(), json);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public final TActUsersQueryBean getTActUsersQueryBean() {
		return tActUsersQueryBean;
	}

	public final void setTActUsersQueryBean(TActUsersQueryBean tActUsersQueryBean) {
		this.tActUsersQueryBean = tActUsersQueryBean;
	}

}
