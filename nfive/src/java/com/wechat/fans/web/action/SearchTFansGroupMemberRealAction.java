package com.wechat.fans.web.action;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;


import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.service.ITFansGroupMemberRealService;
 /**
 * 类功能:粉丝群成员一览
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTFansGroupMemberRealAction")
@Scope("prototype")
public class SearchTFansGroupMemberRealAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(SearchTFansGroupMemberRealAction.class);
	
	//粉丝群和粉丝关系Service
	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	
	//粉丝群主键
	private String tfansgroup_pkid;
	
	private List<SelectCsBean> star_member_select;
	
	private TFansGroupMemberRealQueryBean in_tFansGroupMemberRealQueryBean = new TFansGroupMemberRealQueryBean();

	private String errorMessage;
	
	
	public String execute() throws Exception {
		try {
			// 如果没有登录公众号  则不能查看此页面
			if(null == WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()){
				errorMessage = "公众号不存在！";
				return ERROR;
			}
			//构造下拉列表<标星成员CS:1000>
			star_member_select = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
			
			return SUCCESS;
		} catch (Exception ex) {
			errorMessage ="抱歉,系统出现异常！";
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
			if (in_tFansGroupMemberRealQueryBean == null) {
				in_tFansGroupMemberRealQueryBean = new TFansGroupMemberRealQueryBean();
			}

			//设置当前公众号
			in_tFansGroupMemberRealQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			//设置当前粉丝群组的ID
			in_tFansGroupMemberRealQueryBean.setFans_group_id(tfansgroup_pkid);
			//设置是否订阅（CS:1000，默认为是）
			//in_tFansGroupMemberRealQueryBean.setSubscribe(CodeStringConstant.CS_1000_TRUE);
			
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tFansGroupMemberRealService.queryTFansGroupMemberRealListPage(
					in_tFansGroupMemberRealQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tFansGroupMemberReal_list.updateFormSubmit('"+ d.get("REAL_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tFansGroupMemberReal_list.detailFormSubmit('"+ d.get("REAL_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				//将头像的URL转换成img图像代码显示页面
				d.put("HEAD_IMG_URL","<img src='"+ d.get("HEAD_IMG_URL")+ "' height='24px;' />");
				
				//翻译性别
				if (null != d.get("SEX")){
					d.put("SEX", CodeStringUtil.tranCsValueByCsCode(d.get("SEX").toString()));
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
			errorMessage ="抱歉,系统出现异常！";
			log.error(ex.getMessage(),ex);
			return ERROR;
		}

		return null;
	}

	public String getTfansgroup_pkid() {
		return tfansgroup_pkid;
	}

	public void setTfansgroup_pkid(String tfansgroupPkid) {
		tfansgroup_pkid = tfansgroupPkid;
	}

	public List<SelectCsBean> getStar_member_select() {
		return star_member_select;
	}

	public void setStar_member_select(List<SelectCsBean> starMemberSelect) {
		star_member_select = starMemberSelect;
	}

	public TFansGroupMemberRealQueryBean getIn_tFansGroupMemberRealQueryBean() {
		return in_tFansGroupMemberRealQueryBean;
	}

	public void setIn_tFansGroupMemberRealQueryBean(
			TFansGroupMemberRealQueryBean inTFansGroupMemberRealQueryBean) {
		in_tFansGroupMemberRealQueryBean = inTFansGroupMemberRealQueryBean;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
