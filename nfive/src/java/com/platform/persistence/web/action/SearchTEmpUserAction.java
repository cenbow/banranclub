package com.platform.persistence.web.action;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.TEmpUserQueryBean;
import com.platform.persistence.model.dto.TCodestringDto;
import com.platform.persistence.service.ITCodestringService;
import com.platform.persistence.service.ITEmpUserService;
 /**
 * 类功能:员工数据列表展示
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTEmpUserAction")
@Scope("prototype")
public class SearchTEmpUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	@Autowired
	private ITCodestringService tCodestringService;
	
	private TEmpUserQueryBean tEmpUserQueryBean = new TEmpUserQueryBean();

	/**
	 * 员工查询选择页面。
	 * @return
	 */
	public String execute() throws Exception {
		try {//查询未锁定的记录，公共页面会员选择出参。
			request.setAttribute("lock_flag", CodeStringConstant.CS_3000_LOCK_FLAG_KEY);
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
			if (tEmpUserQueryBean == null) {
				tEmpUserQueryBean = new TEmpUserQueryBean();
			}
			//如果不是null 去除空格
			if(StringUtils.isNotBlank(tEmpUserQueryBean.getUser_cd()))
			{
				tEmpUserQueryBean.setUser_cd(tEmpUserQueryBean.getUser_cd().trim());
			}//如果不是null 去除空格
			if(StringUtils.isNotBlank(tEmpUserQueryBean.getUser_name()))
			{
				tEmpUserQueryBean.setUser_name(tEmpUserQueryBean.getUser_name().trim());
			}
			//如果不是null 去除空格
			if(StringUtils.isNotBlank(tEmpUserQueryBean.getMobile()))
			{
				tEmpUserQueryBean.setMobile(tEmpUserQueryBean.getMobile().trim());
			}
			//如果不是null 去除空格
			if(StringUtils.isNotBlank(tEmpUserQueryBean.getEmail()))
			{
				tEmpUserQueryBean.setEmail(tEmpUserQueryBean.getEmail().trim());
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tEmpUserService.queryTEmpUserListPage(
					tEmpUserQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				//设定锁定状态
				TCodestringDto tc = new TCodestringDto();
				tc.setCs_code(d.get("LOCK_FLAG").toString());
				tc = tCodestringService.getRow(tc);
				if(tc != null)
				{
					d.put("LOCK_FLAG", tc.getCs_value());
				}
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tEmpUser_list.updateFormSubmit('"+ d.get("USER_ID")+ "','"+d.get("USER_CD")+"');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tEmpUser_list.detailFormSubmit('"+ d.get("USER_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("ALLOT","<a href='javascript:return void(0);'onClick=\"tEmpUser_list.allotFormSubmit('"+ d.get("USER_ID")+ "','"+d.get("USER_CD")+"','"+d.get("USER_NAME")+"');return false;\"><i class='icon-setting'></i></a>");
				d.put("RESETPASS","<a href='javascript:return void(0);'onClick=\" tEmpUser_list.resetPassFormSubmit('"+ d.get("USER_ID")+ "');return false;\"><i class='icon-setting'></i></a>");
				//传递USER_CD,作为和公众号的绑定条件
				d.put("USER_CONFIG","<a href='javascript:return void(0);' onClick=\"tEmpUser_list.platFormSubmit('"+ d.get("USER_CD")+ "');return false;\"><i class='icon-setting'></i></a>");
				//测试页面，测试员工选择
				
				//d.put("SELECTEMP","<a href='javascript:return void(0);' onClick=\"tEmpUser_list.platFormSelectSubmit('"+ d.get("USER_CD")+ "');return false;\"><i class='icon-setting'></i></a>");
                
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
	
	
	/***
	 * 公用方法，独立分开，员工选择查询
	 * @return
	 * @throws Exception
	 */
	public String getPublicEmpListData(){

		try {
			if (tEmpUserQueryBean == null) {
				tEmpUserQueryBean = new TEmpUserQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tEmpUserService.queryTEmpUserListPage(
					tEmpUserQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				//设定锁定状态
				TCodestringDto tc = new TCodestringDto();
				tc.setCs_code(d.get("LOCK_FLAG").toString());
				tc = tCodestringService.getRow(tc);
				if(tc != null)
				{
					d.put("LOCK_FLAG", tc.getCs_value());
				}
				// 2.自定义按钮设置在此处
				d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"tConfig_list.doSelect('"+ d.get("USER_CD") +"','"+ d.get("USER_NAME") +"')\"/>");

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
	
	public final TEmpUserQueryBean getTEmpUserQueryBean() {
		return tEmpUserQueryBean;
	}

	public final void setTEmpUserQueryBean(TEmpUserQueryBean tEmpUserQueryBean) {
		this.tEmpUserQueryBean = tEmpUserQueryBean;
	}

}
