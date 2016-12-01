package com.wechat.pfusercfg.web.action;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wechat.pfusercfg.model.TPlatformempCfgQueryBean;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;
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
 
@Controller("searchTPlatformempCfgAction")
@Scope("prototype")
public class SearchTPlatformempCfgAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	
	private TPlatformempCfgQueryBean tPlatformempCfgQueryBean = new TPlatformempCfgQueryBean();
	
	private String platform_id;


	public String execute() throws Exception {
		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}
	
	/***
	 * 1、获取员工列表里面绑定的公众号,页面的tPlatformempCfgQueryBean中带user_cd,进行匹配。条件在SQL中匹配
	 * 2、获取公众号页面找绑定员工，以platform_id进行匹配。条件在SQL中匹配
	 * @return
	 * @throws Exception
	 */
	public String getListData() {
		try {
			if (tPlatformempCfgQueryBean == null) {
				tPlatformempCfgQueryBean = new TPlatformempCfgQueryBean();
			}
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tPlatformempCfgService.queryTPlatformempCfgListPage(
					tPlatformempCfgQueryBean, init_pg);
			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			
			for (Map d : rows) {
				d.put("LOCK_FLAG",CodeStringUtil.tranCsValueByCsCode((String)d.get("LOCK_FLAG")));//锁定状态转换
				d.put("CIS_USE",CodeStringUtil.tranCsValueByCsCode((String)d.get("CIS_USE")));
				d.put("CIS_VALID",CodeStringUtil.tranCsValueByCsCode((String)d.get("CIS_VALID")));
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


	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platformId) {
		platform_id = platformId;
	}
	
	public final TPlatformempCfgQueryBean getTPlatformempCfgQueryBean() {
		return tPlatformempCfgQueryBean;
	}

	public final void setTPlatformempCfgQueryBean(TPlatformempCfgQueryBean tPlatformempCfgQueryBean) {
		this.tPlatformempCfgQueryBean = tPlatformempCfgQueryBean;
	}

}
