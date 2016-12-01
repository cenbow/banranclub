package com.wechat.material.web;

import java.util.List;
import java.util.Map;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.model.TWxNewsQueryBean;
import com.wechat.material.service.ITWxNewsService;
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
@Controller("searchTWxNewsAction")
@Scope("prototype")
public class SearchTWxNewsAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxNewsService wxNewsService;

	private TWxNewsQueryBean tWxNewsQueryBean = new TWxNewsQueryBean();

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
			if (tWxNewsQueryBean == null) {
				tWxNewsQueryBean = new TWxNewsQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = wxNewsService.queryTWxNewsListPage(tWxNewsQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tWxNews_list.updateFormSubmit('"+ d.get("NEWS_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tWxNews_list.detailFormSubmit('"+ d.get("NEWS_ID")+ "');return false;\"><i class='icon-search'></i></a>");
                if(d.get("URL") != null){
                    d.put("URL", "<a href='"+d.get("URL")+"'>"+d.get("URL")+"</a>");
//                    d.put("URL", "123");
                }
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

	public final TWxNewsQueryBean getTWxNewsQueryBean() {
		return tWxNewsQueryBean;
	}

	public final void setTWxNewsQueryBean(TWxNewsQueryBean tWxNewsQueryBean) {
		this.tWxNewsQueryBean = tWxNewsQueryBean;
	}

}
