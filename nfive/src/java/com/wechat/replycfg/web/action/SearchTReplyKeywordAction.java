package com.wechat.replycfg.web.action;

import java.util.List;
import java.util.Map;

import com.platform.common.tools.wechat.WechatUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

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
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.service.ITReplyKeywordService;
 /**
 * 类功能:关键字一览列表
 * <p>创建者:gy</p>
 * <p>创建时间:2014-08-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("searchTReplyKeywordAction")
@Scope("prototype")
public class SearchTReplyKeywordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(SearchTReplyKeywordAction.class);


	@Autowired
	private ITReplyKeywordService tReplyKeywordService;

	//入参 关键字回复规则
	private TReplyKeywordQueryBean in_tReplyKeywordQueryBean = new TReplyKeywordQueryBean();

	private List<SelectCsBean> match_SelList;//匹配类型下拉列表

	private List<SelectCsBean> reply_SelList;//回复类型下拉列表

	private List<SelectCsBean> effect_flag_SelList;//生效标志下拉列表

	private List<SelectCsBean> cust_srv_flag_SelList;//启用客服模式

	private String errorMessage;

	public String execute() throws Exception {

		try {

			//构造匹配类型下拉列表数据
			match_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.KEYWORK_MATCHING_TYPE);

			//构造回复类型下拉列表数据
			reply_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.KEYWORK_TYPE);

			//构造生效标志下拉列表数据
			effect_flag_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);

			//构造启用客服模式下拉列表数据
			cust_srv_flag_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);

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
	public String getListData() throws Exception{

		try {
			if (in_tReplyKeywordQueryBean == null) {
				in_tReplyKeywordQueryBean = new TReplyKeywordQueryBean();
			}

			//设置公众号如果用户没有指定公众号  则默认查询此时登陆的公众号
			if (null == in_tReplyKeywordQueryBean.getPlatform_id()){
				in_tReplyKeywordQueryBean.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());
			}
			//将关键字设置为大写
			if (null != in_tReplyKeywordQueryBean.getKeyword()){
				in_tReplyKeywordQueryBean.setKeyword(in_tReplyKeywordQueryBean.getKeyword().toUpperCase());
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tReplyKeywordService.queryTReplyKeywordListPage(
					in_tReplyKeywordQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {

				//同时翻译CsCode
				if(null != d.get("MATCH_TYPE")){
					d.put("MATCH_TYPE",CodeStringUtil.tranCsValueByCsCode(d.get("MATCH_TYPE").toString()));//匹配类型
				}
				if (null != d.get("REPLY_TYPE")){

					d.put("REPLY_TYPE",CodeStringUtil.tranCsValueByCsCode(d.get("REPLY_TYPE").toString()));//回复类型
				}
				if (null != d.get("EFFECT_FLAG")){
					d.put("EFFECT_FLAG",CodeStringUtil.tranCsValueByCsCode(d.get("EFFECT_FLAG").toString()));//生效标志
				}
				if (null != d.get("CUST_SRV_FLAG")){
					d.put("CUST_SRV_FLAG",CodeStringUtil.tranCsValueByCsCode(d.get("CUST_SRV_FLAG").toString()));//启用客服模式
				}
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tReplyKeyword_list.updateFormSubmit('"+ d.get("KREPLY_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tReplyKeyword_list.detailFormSubmit('"+ d.get("KREPLY_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("DEL","<a href='javascript:return void(0);'onClick=\"tReplyKeyword_list.delFormSubmit('"+ d.get("KREPLY_ID")+ "');return false;\"><i class='icon-no'></i></a>");
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


	public TReplyKeywordQueryBean getIn_tReplyKeywordQueryBean() {
		return in_tReplyKeywordQueryBean;
	}

	public void setIn_tReplyKeywordQueryBean(
			TReplyKeywordQueryBean inTReplyKeywordQueryBean) {
		in_tReplyKeywordQueryBean = inTReplyKeywordQueryBean;
	}

	public List<SelectCsBean> getMatch_SelList() {
		return match_SelList;
	}

	public void setMatch_SelList(List<SelectCsBean> matchSelList) {
		match_SelList = matchSelList;
	}

	public List<SelectCsBean> getReply_SelList() {
		return reply_SelList;
	}

	public void setReply_SelList(List<SelectCsBean> replySelList) {
		reply_SelList = replySelList;
	}

	public List<SelectCsBean> getEffect_flag_SelList() {
		return effect_flag_SelList;
	}

	public void setEffect_flag_SelList(List<SelectCsBean> effectFlagSelList) {
		effect_flag_SelList = effectFlagSelList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<SelectCsBean> getCust_srv_flag_SelList() {
		return cust_srv_flag_SelList;
	}

	public void setCust_srv_flag_SelList(List<SelectCsBean> custSrvFlagSelList) {
		cust_srv_flag_SelList = custSrvFlagSelList;
	}

}
