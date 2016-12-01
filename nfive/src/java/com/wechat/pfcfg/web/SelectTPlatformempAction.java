package com.wechat.pfcfg.web;

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

import com.wechat.pfcfg.model.TPubPlatformQueryBean;
import com.wechat.pfcfg.service.ITPubPlatformService;
 /**
 * 类功能:员工数据展示
 * <p>创建者:leon.guo</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("selectTPlatformempAction")
@Scope("prototype")
public class SelectTPlatformempAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPubPlatformService tPubPlatformService;
	
	private TPubPlatformQueryBean tPubPlatformQueryBean = new TPubPlatformQueryBean();

	public String user_cd;

	public String execute() throws Exception {
		try {
			request.setAttribute("tPubPlatformQueryBean",tPubPlatformQueryBean);
			request.setAttribute("user_cd", tPubPlatformQueryBean.getUser_cd());//员工CD号。
			//单选/多选  true时为单选
			request.setAttribute("singleSelect",request.getParameter("singleSelect"));
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

			tPubPlatformQueryBean.setUser_cd(user_cd);
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tPubPlatformService.queryTPubPlatformListPage(
					tPubPlatformQueryBean, init_pg);

			// 1.遍历增加自定义内容 
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tPlatformempCfg_list.updateFormSubmit('"+ d.get("CFG_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tPlatformempCfg_list.detailFormSubmit('"+ d.get("CFG_ID")+ "');return false;\"><i class='icon-search'></i></a>");
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

	public TPubPlatformQueryBean getTPubPlatformQueryBean() {
		return tPubPlatformQueryBean;
	}

	public void setTPubPlatformQueryBean(TPubPlatformQueryBean tPubPlatformQueryBean) {
		this.tPubPlatformQueryBean = tPubPlatformQueryBean;
	}

	public String getUser_cd() {
		return user_cd;
	}

	public void setUser_cd(String userCd) {
		user_cd = userCd;
	}

}
