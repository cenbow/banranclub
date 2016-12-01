package com.wechat.fans.web.action;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;


import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wechat.fans.model.TFansGroupQueryBean;
import com.wechat.fans.service.ITFansGroupService;
 /**
 * 类功能:粉丝群一览
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTFansGroupAction")
@Scope("prototype")
public class SearchTFansGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupService tFansGroupService;
	
	private TFansGroupQueryBean in_tFansGroupQueryBean = new TFansGroupQueryBean();
	
	protected static final Log log = LogFactory.getLog(SearchTFansGroupAction.class);
	
	private String errorMessage;

	public String execute() throws Exception {
		try {
			// 如果没有登录公众号  则不能查看此页面
			if(null == WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()){
				errorMessage = "公众号不存在！";
				return ERROR;
			}
			
			return SUCCESS;
		} catch (Exception ex) {
			errorMessage = "抱歉,系统出现异常！";
			log.error(ex.getMessage(),ex);
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
			if (in_tFansGroupQueryBean == null) {
				in_tFansGroupQueryBean = new TFansGroupQueryBean();
			}
			
			//设置默认查询条件  《公众号ID》
			in_tFansGroupQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tFansGroupService.queryTFansGroupListPage(
					in_tFansGroupQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("TOFANS","<a href='javascript:return void(0);' onClick=\"tFansGroup_list.tofansList('"+ d.get("FANS_GROUP_ID")+ "','"+d.get("GROUP_NAME")+"');return false;\"><i class='icon-member'></i></a>");
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tFansGroup_list.updateFormSubmit('"+ d.get("FANS_GROUP_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tFansGroup_list.detailFormSubmit('"+ d.get("FANS_GROUP_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("DEL","<a href='javascript:return void(0);'onClick=\"tFansGroup_list.delFormSubmit('"+ d.get("FANS_GROUP_ID")+ "');return false;\"><i class='icon-no'></i></a>");
				
				//说明长度过长，弹出方式
				if (null != d.get("REMARK")){
					if (d.get("REMARK").toString().length() > 20){
						d.put("REMARK","<a style='color:blue;' href='javascript:return void(0);'onClick=\"tFansGroup_list.alertremark('"+d.get("REMARK")+"');return false;\">"+d.get("REMARK").toString().substring(0, 20)+"..."+"</a>");
					}else {
						d.put("REMARK","<a style='color:blue;' href='javascript:return void(0);'onClick=\"tFansGroup_list.alertremark('"+d.get("REMARK")+"');return false;\">"+d.get("REMARK").toString()+"</a>");
					}
				}
			
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
			errorMessage = "抱歉,系统出现异常！";
			log.error(ex.getMessage(),ex);
			return ERROR;
		}
		return null;
	}

	public TFansGroupQueryBean getIn_tFansGroupQueryBean() {
		return in_tFansGroupQueryBean;
	}

	public void setIn_tFansGroupQueryBean(TFansGroupQueryBean inTFansGroupQueryBean) {
		in_tFansGroupQueryBean = inTFansGroupQueryBean;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


}
