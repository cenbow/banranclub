package com.wechat.fans.web.action;


import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.encryption.Encrypter;
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

import com.wechat.fans.model.TFansGroupQueryBean;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.TWeixinGroupQueryBean;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITFansGroupService;
import com.wechat.fans.service.ITWeixinFansService;
import com.wechat.fans.service.ITWeixinGroupService;
/**
 * 类功能:粉丝一览和查询
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("searchTWeixinFansAction")
@Scope("prototype")
public class SearchTWeixinFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWeixinFansService tWeixinFansService;
	@Autowired
	private ITFansGroupService tFansGroupService;
	@Autowired
	private ITWeixinGroupService tWeixinGroupService;

	//入参
	private TWeixinFansQueryBean tWeixinFansQueryBean = new TWeixinFansQueryBean();
    //出参
	private List<SelectCsBean> sexList;
	private List<SelectCsBean> subscribeList;
	private List<SelectCsBean> boundList;
	private List<SelectCsBean> wixinGroup;
	private List<TFansGroupDto> fansGroup;
	//出错信息
    private String errorMessage;
	private static final Logger logger = Logger.getLogger(SearchTWeixinFansAction.class);

	public String execute() throws Exception {
		try {
			//性别选择下拉列表
			sexList  =  CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_1015_SEX_FLAG);
			//是关注用户下拉列表
		    subscribeList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
			//一账通绑定下拉列表
			boundList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
		    //微信组
			TWeixinGroupQueryBean tWeixinGroupQueryBean =new TWeixinGroupQueryBean();
			List<TWeixinGroupDto> list = tWeixinGroupService.getAllWxGroup(tWeixinGroupQueryBean);
			wixinGroup = new ArrayList<SelectCsBean>();
			SelectCsBean cs = new SelectCsBean();
			cs.setKey("");
			cs.setValue("未输入");
			wixinGroup.add(cs);
			for(TWeixinGroupDto group:list){
				cs = new SelectCsBean();
				cs.setKey(String.valueOf(group.getWeixin_group_id()));
				cs.setValue(group.getGroup_name());
				wixinGroup.add(cs);
			}
			//粉丝群
			TFansGroupQueryBean tFansGroupQueryBean= new TFansGroupQueryBean();
			fansGroup = tFansGroupService.alltFansGroupName(tFansGroupQueryBean);

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
			if (tWeixinFansQueryBean == null) {
				tWeixinFansQueryBean = new TWeixinFansQueryBean();
			}
			if(tWeixinFansQueryBean.getFans_grouplist() == null){
				tWeixinFansQueryBean.setFans_grouplist("");
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();

			PageResult pageResult = tWeixinFansService.queryTWeixinFansListPage(
					tWeixinFansQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				if(d.get("OPENID_VERIFY") != null){
					d.put("OPENID_VERIFY",  CodeStringUtil.tranCsValueByCsCode(d.get("OPENID_VERIFY").toString()));
				}
				if(d.get("SUBSCRIBE") != null){
					d.put("SUBSCRIBE",  CodeStringUtil.tranCsValueByCsCode(d.get("SUBSCRIBE").toString()));
				}
				d.put("SEX",  CodeStringUtil.tranCsValueByCsCode(d.get("SEX").toString()));
				if(d.get("MOBILE") != null){
					    d.put("MOBILE", Encrypter.decrypt(d.get("MOBILE").toString()).replace(Encrypter.decrypt(d.get("MOBILE").toString()).substring(3,7), "****"));
		        }
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tWeixinFans_list.updateFormSubmit('"+ d.get("FANS_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tWeixinFans_list.detailFormSubmit('"+ d.get("FANS_ID")+ "');return false;\"><i class='icon-search'></i></a>");
                if(d.get("HEAD_IMG_URL") != null){
                	d.put("HEAD_IMG_URL", "<img width="+30+" height="+20+" src='"+d.get("HEAD_IMG_URL")+"'>");
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

			return null;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;
	}

	public final TWeixinFansQueryBean getTWeixinFansQueryBean() {
		return tWeixinFansQueryBean;
	}

	public final void setTWeixinFansQueryBean(TWeixinFansQueryBean tWeixinFansQueryBean) {
		this.tWeixinFansQueryBean = tWeixinFansQueryBean;
	}

	public List<SelectCsBean> getSexList() {
		return sexList;
	}

	public void setSexList(List<SelectCsBean> sexList) {
		this.sexList = sexList;
	}

	public List<SelectCsBean> getSubscribeList() {
		return subscribeList;
	}

	public void setSubscribeList(List<SelectCsBean> subscribeList) {
		this.subscribeList = subscribeList;
	}

	public List<SelectCsBean> getBoundList() {
		return boundList;
	}

	public void setBoundList(List<SelectCsBean> boundList) {
		this.boundList = boundList;
	}

	public List<SelectCsBean> getWixinGroup() {
		return wixinGroup;
	}

	public void setWixinGroup(List<SelectCsBean> wixinGroup) {
		this.wixinGroup = wixinGroup;
	}

	public List<TFansGroupDto> getFansGroup() {
		return fansGroup;
	}

	public void setFansGroup(List<TFansGroupDto> fansGroup) {
		this.fansGroup = fansGroup;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
