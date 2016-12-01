package com.pub.persistence.web.action;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.common.tools.opensymphony.page.PangingUtils;
import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.model.TRoleQueryBean;
import com.pub.persistence.service.ITRoleService;
 /**
 * 类功能:角色数据列表展示
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTRoleAction")
@Scope("prototype")
public class SearchTRoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITRoleService tRoleService;
	
	private TRoleQueryBean tRoleQueryBean = new TRoleQueryBean();

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
			if (tRoleQueryBean == null) {
				tRoleQueryBean = new TRoleQueryBean();
			}//如果不是null 去除空格
			if(StringUtils.isNotBlank(tRoleQueryBean.getRole_name()))
			{
				tRoleQueryBean.setRole_name(tRoleQueryBean.getRole_name().trim());
			}//如果不是null 去除空格
			if(StringUtils.isNotBlank(tRoleQueryBean.getRole_group()))
			{
				tRoleQueryBean.setRole_group(tRoleQueryBean.getRole_group().trim());
			}
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tRoleService.queryTRoleListPage(
					tRoleQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tRole_list.updateFormSubmit('"+ d.get("ROLE_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tRole_list.detailFormSubmit('"+ d.get("ROLE_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("RESOURCE_CONFIG","<a href='javascript:return void(0);' onClick=\"tRole_list.allotResourceFormSubmit('"+ d.get("ROLE_ID")+ "','"+d.get("ROLE_NAME")+"','"+d.get("ROLE_GROUP")+"');return false;\"><i class='icon-resource'></i></a>");
				d.put("USER_CONFIG","<a href='javascript:return void(0);' onClick=\"tRole_list.allotUserFormSubmit('"+ d.get("ROLE_ID")+ "','"+d.get("ROLE_NAME")+"','"+d.get("ROLE_GROUP")+"');return false;\"><i class='icon-user'></i></a>");
           	}

			// 3.组合输出列表查询所需数据
			// JsonConfig 用于解析转换的设置
			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

			JSONArray json_rows = JSONArray.fromObject(rows, config);
			String json = "{\"total\":\""
					+ pageResult.getPagingObject().getTotal_record()
					+ "\",\"rows\":" + json_rows.toString() + "}";
			//System.out.println(json);
			outJSOND(this.getResponse(), json);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	
	public final TRoleQueryBean getTRoleQueryBean() {
		return tRoleQueryBean;
	}

	public final void setTRoleQueryBean(TRoleQueryBean tRoleQueryBean) {
		this.tRoleQueryBean = tRoleQueryBean;
	}

}
