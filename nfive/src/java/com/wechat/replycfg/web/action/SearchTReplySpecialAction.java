package com.wechat.replycfg.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
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
import com.platform.common.tools.wechat.PubPlatformBean;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.service.ITReplySpecialService;

 /**
 * 类功能:特殊回复列表和查询
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTReplySpecialAction")
@Scope("prototype")
public class SearchTReplySpecialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplySpecialService tReplySpecialService;
	@Autowired
	private ITPubPlatformService tPubPlatformService;
	
	private TReplySpecialQueryBean tReplySpecialQueryBean = new TReplySpecialQueryBean();
	//出参
	//特殊回复规则
	private List<SelectCsBean> rule_typeList;
	//回复类型
	private List<SelectCsBean> reply_typeList;
	//生效标志
	private List<SelectCsBean> effect_flagList;
	//生效标志下拉列表
	private List<SelectCsBean> platform_id_SelList;
	
	//出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(SearchTReplyKeywordAction.class);
    
	public String execute() throws Exception {
		try {
			//特殊回复规则下拉列表
			rule_typeList  =  CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.REPLY_RULE);
			//回复类型下拉列表
			reply_typeList  = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.KEYWORK_TYPE);
			//生效标志下拉列表
			effect_flagList  = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
			//该用户所能看见的公众号所有列表
			List<PubPlatformBean>  pubplatFormList = WechatInfoUtil.getPubPlatformBeanListFromSession();
			//构造公众号下拉列表
			platform_id_SelList = new ArrayList<SelectCsBean>();
			SelectCsBean selectCsBean;
			for(PubPlatformBean pubpalt:pubplatFormList){
				selectCsBean = new SelectCsBean();
				selectCsBean.setKey(pubpalt.getPlatform_id());
				selectCsBean.setValue(pubpalt.getPlatform_name());
				//默认选中当前用户所使用的公众号
			    String platfrom = 	WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
			    if(platfrom==null){
			    	setErrorMessage("没有登录公众号");
			    	
			    	return ERROR;
			    }
				if(pubpalt.getPlatform_id().equalsIgnoreCase(platfrom)){
					selectCsBean.setSelected(true);
				}
				
				platform_id_SelList.add(selectCsBean);
			}
			
			return SUCCESS;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
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
			if (tReplySpecialQueryBean == null) {
				tReplySpecialQueryBean = new TReplySpecialQueryBean();
			}
			tReplySpecialQueryBean.setPlatform_id( WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
            	PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			    PageResult pageResult = tReplySpecialService.queryTReplySpecialListPage(tReplySpecialQueryBean, init_pg);
			
			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				//同时翻译CsCode
				//特殊回复规则
	     	    if(d.get("RULE_TYPE") != null){
						d.put("RULE_TYPE",CodeStringUtil.tranCsValueByCsCode(d.get("RULE_TYPE").toString()));
	     	    }
	     	    //回复类型
				if(d.get("REPLY_TYPE") != null){
						d.put("REPLY_TYPE",CodeStringUtil.tranCsValueByCsCode(d.get("REPLY_TYPE").toString().trim()));
				}
				//生效标志
			    if(d.get("EFFECT_FLAG") != null){
						d.put("EFFECT_FLAG",CodeStringUtil.tranCsValueByCsCode(d.get("EFFECT_FLAG").toString().trim()));
				}
			    TPubPlatformDto tmpPubPlatformDto = new TPubPlatformDto();  
			    //显示公众号名称
			     tmpPubPlatformDto.setPlatform_id((String) d.get("PLATFORM_ID"));
			     TPubPlatformDto tPubPlatformDto = tPubPlatformService.getRow(tmpPubPlatformDto);
			     d.remove("PLATFORM_ID");
			     d.put("PLATFORM_ID",tPubPlatformDto.getPlatform_name());
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tReplySpecial_list.updateFormSubmit('"+ d.get("FREPLY_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tReplySpecial_list.detailFormSubmit('"+ d.get("FREPLY_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("DEL","<a href='javascript:return void(0);'onClick=\"tReplySpecial_list.deleteFormSubmit('"+ d.get("FREPLY_ID")+ "');return false;\"><i class='icon-no'></i></a>");
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
            
			return null;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;
	}
	
	public final TReplySpecialQueryBean getTReplySpecialQueryBean() {
		return tReplySpecialQueryBean;
	}

	public final void setTReplySpecialQueryBean(TReplySpecialQueryBean tReplySpecialQueryBean) {
		this.tReplySpecialQueryBean = tReplySpecialQueryBean;
	}

	public List<SelectCsBean> getRule_typeList() {
		return rule_typeList;
	}

	public void setRule_typeList(List<SelectCsBean> ruleTypeList) {
		rule_typeList = ruleTypeList;
	}

	public List<SelectCsBean> getReply_typeList() {
		return reply_typeList;
	}

	public void setReply_typeList(List<SelectCsBean> replyTypeList) {
		reply_typeList = replyTypeList;
	}

	public List<SelectCsBean> getEffect_flagList() {
		return effect_flagList;
	}

	public void setEffect_flagList(List<SelectCsBean> effectFlagList) {
		effect_flagList = effectFlagList;
	}

	public final List<SelectCsBean> getPlatform_id_SelList() {
		return platform_id_SelList;
	}

	public final void setPlatform_id_SelList(List<SelectCsBean> platformIdSelList) {
		platform_id_SelList = platformIdSelList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
   
}
