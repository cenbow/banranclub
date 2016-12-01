package com.wechat.pfcfg.web;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.pfcfg.model.TPubPlatformQueryBean;
import com.wechat.pfcfg.service.ITPubPlatformService;

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

@Controller("searchTPubPlatformAction")
@Scope("prototype")
public class SearchTPubPlatformAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPubPlatformService tPubPlatformService;

	private TPubPlatformQueryBean tPubPlatformQueryBean = new TPubPlatformQueryBean();

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
			if (tPubPlatformQueryBean == null) {
				tPubPlatformQueryBean = new TPubPlatformQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tPubPlatformService.queryTPubPlatformListPage(
					tPubPlatformQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();


			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tPubPlatform_list.detailFormSubmit('"+ d.get("PLATFORM_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tPubPlatform_list.updateFormSubmit('"+ d.get("PLATFORM_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				//公众号类型转换
				d.put("PLATFORM_TYPE",CodeStringUtil.tranCsValueByCsCode((String)d.get("PLATFORM_TYPE")));
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

	public final TPubPlatformQueryBean getTPubPlatformQueryBean() {
		return tPubPlatformQueryBean;
	}

	public final void setTPubPlatformQueryBean(TPubPlatformQueryBean tPubPlatformQueryBean) {
		this.tPubPlatformQueryBean = tPubPlatformQueryBean;
	}

}
