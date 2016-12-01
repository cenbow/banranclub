package com.wechat.commonselect.web.action;


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

import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.TWeixinGroupQueryBean;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITWeixinFansService;
import com.wechat.fans.service.ITWeixinGroupService;
/**
 * 类功能:选择粉丝
 * <p>创建者:yan.guo</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("selectTWeixinFansAction")
@Scope("prototype")
public class SelectTWeixinFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWeixinFansService tWeixinFansService;
	@Autowired
	private ITWeixinGroupService tWeixinGroupService;

	//入参
	private TWeixinFansQueryBean tWeixinFansQueryBean = new TWeixinFansQueryBean();
    //出参
	private List<SelectCsBean> sexList;
	private List<SelectCsBean> subscribeList;
	private List<SelectCsBean> boundList;
	private List<SelectCsBean> wixinGroup = new ArrayList<SelectCsBean>();
	
	private String tfansgroup_pkid;//粉丝群ID
	
	private static final Logger logger = Logger.getLogger(SelectTWeixinFansAction.class);
	
	public String execute() throws Exception {
		try {	
			//如果没有登录公众号  则不能查看此页面
			if(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id() == null){
				return ERROR;
			}
			//性别选择下拉列表
			sexList  =  CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_1015_SEX_FLAG,CodeStringConstant.CS_1015_SEX_FLAG_NOINPUT);
			//是关注用户下拉列表
		    subscribeList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
			//一账通绑定下拉列表
			boundList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
		    //微信组
			TWeixinGroupQueryBean tWeixinGroupQueryBean =new TWeixinGroupQueryBean();
			tWeixinGroupQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			List<TWeixinGroupDto> wixinGroupList = tWeixinGroupService.getAllWxGroup(tWeixinGroupQueryBean);
			
			//构造微信组下拉列表
			SelectCsBean noinput_cs = new SelectCsBean();
			noinput_cs.setKey("");
			noinput_cs.setValue("未输入");
			wixinGroup.add(noinput_cs);
			for(TWeixinGroupDto group:wixinGroupList){
				SelectCsBean cs = new SelectCsBean();
				
				cs.setKey(String.valueOf(group.getWeixin_group_id()));
				
				cs.setValue(group.getGroup_name());
				
				wixinGroup.add(cs);
			}
			
			return SUCCESS;
		} catch (Exception ex) {
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
			//设置默认查询条件  《公众号ID》
			tWeixinFansQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			
			//设置粉丝群ID
			tWeixinFansQueryBean.setFans_group_id(tfansgroup_pkid);
			
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			
			PageResult pageResult = tWeixinFansService.queryTWeixinFansListPageByGroupId(
					tWeixinFansQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				if(null !=d.get("OPENID_VERIFY")){
					d.put("OPENID_VERIFY",  CodeStringUtil.tranCsValueByCsCode(d.get("OPENID_VERIFY").toString()));
				}
				d.put("SEX",  CodeStringUtil.tranCsValueByCsCode(d.get("SEX").toString()));
				if(d.get("MOBILE") != null){ 
						String moblie = Encrypter.decrypt(d.get("MOBILE").toString());
						moblie = moblie.substring(0,3)+"****"+moblie.substring(7);
					    d.put("MOBILE", moblie);
		        }
				if (null != d.get("HEAD_IMG_URL")){
	                d.put("HEAD_IMG_URL", "<img width="+30+" height="+20+" src='"+d.get("HEAD_IMG_URL")+"'>");
				}

				// 2.自定义按钮设置在此处
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tWeixinFans_list.detailFormSubmit('"+ d.get("FANS_ID")+ "');return false;\"><i class='icon-search'></i></a>");
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
			logger.error(ex.getMessage(),ex);
		}

		return null;
	}
	
	public final TWeixinFansQueryBean getTWeixinFansQueryBean() {
		return tWeixinFansQueryBean;
	}

	public final void setTWeixinFansQueryBean(TWeixinFansQueryBean tWeixinFansQueryBean) {
		this.tWeixinFansQueryBean = tWeixinFansQueryBean;
	}



	public List getSexList() {
		return sexList;
	}

	public void setSexList(List sexList) {
		this.sexList = sexList;
	}

	public List getSubscribeList() {
		return subscribeList;
	}

	public void setSubscribeList(List subscribeList) {
		this.subscribeList = subscribeList;
	}

	public List getBoundList() {
		return boundList;
	}

	public void setBoundList(List boundList) {
		this.boundList = boundList;
	}

	public List<SelectCsBean> getWixinGroup() {
		return wixinGroup;
	}

	public void setWixinGroup(List<SelectCsBean> wixinGroup) {
		this.wixinGroup = wixinGroup;
	}

	public String getTfansgroup_pkid() {
		return tfansgroup_pkid;
	}

	public void setTfansgroup_pkid(String tfansgroupPkid) {
		tfansgroup_pkid = tfansgroupPkid;
	}
   
}
