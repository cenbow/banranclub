package com.platform.persistence.web.action;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;


import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.TCodestringQueryBean;
import com.platform.persistence.service.ITCodestringService;
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
 
@Controller("searchTCodestringAction")
@Scope("prototype")
public class SearchTCodestringAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITCodestringService tCodestringService;
	
	private TCodestringQueryBean tCodestringQueryBean = new TCodestringQueryBean();

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
			if (tCodestringQueryBean == null) {
				tCodestringQueryBean = new TCodestringQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tCodestringService.queryTCodestringListPage(
					tCodestringQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tCodestring_list.updateFormSubmit('"+ d.get("CS_CODE")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tCodestring_list.detailFormSubmit('"+ d.get("CS_CODE")+ "');return false;\"><i class='icon-search'></i></a>");
           		}

			// 3.组合输出列表查询所需数据
			// JsonConfig 用于解析转换的设置
			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

			JSONArray json_rows = JSONArray.fromObject(rows, config);
			String json = "{\"total\":\""
					+ pageResult.getPagingObject().getTotal_record()
					+ "\",\"rows\":" + json_rows.toString() + "}";
			System.out.println(json);
			outJSOND(this.getResponse(), json);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	public final TCodestringQueryBean getTCodestringQueryBean() {
		return tCodestringQueryBean;
	}

	public final void setTCodestringQueryBean(TCodestringQueryBean tCodestringQueryBean) {
		this.tCodestringQueryBean = tCodestringQueryBean;
	}

}
