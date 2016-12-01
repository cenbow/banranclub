package com.wechat.crowdsend.web.action;

import java.net.URLEncoder;
import java.util.*;

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
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITWeixinGroupService;


import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

 /**
 * 类功能:群发日志一览
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-24</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTMsgSendAction")
@Scope("prototype")
public class SearchTMsgSendAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMsgSendService tMsgSendService;
	@Autowired
	private ITWeixinGroupService tWeixinGroupService;
	//发送状态
	private List<SelectCsBean> send_status;
	//群发区分
	private List<SelectCsBean> send_dist;
	//群发对象
	private List<SelectCsBean> send_target;
	//群发接口
	private List<SelectCsBean> send_if;
	//消息类型
	private List<SelectCsBean> msg_type;
	private TMsgSendQueryBean tMsgSendQueryBean = new TMsgSendQueryBean();
	//微信分组下拉列表
	private List<SelectCsBean> weixin_group = new ArrayList<SelectCsBean>();
	//日志记录对象
	private static final Logger logger = Logger.getLogger(CrowdTMsgSendAction.class);
	private String errorMessage;//错误信息
	
	public String execute() throws Exception {
		try {
			//发送状态
			send_status = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5063_SEND_STATUS);
			//群发区分
			send_dist = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5059_SEND_DIST);
			//群发对象
			send_target = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5060_CROWD_SEND);
			//群发接口
			send_if = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5061_SEND_IF);
			//发送消息类型
			msg_type = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_5062_SEND_TYPE);
			//微信分组
			TWeixinGroupDto tWeixinGroupDto = new TWeixinGroupDto();
			tWeixinGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			List<TWeixinGroupDto> list = tWeixinGroupService.getAll(tWeixinGroupDto);
			if(null != list){
				SelectCsBean selectBean = null;
				//首个空项
				selectBean = new SelectCsBean();
				selectBean.setKey("");
				selectBean.setValue("未输入");
				weixin_group.add(selectBean);
				//数据项
				for(TWeixinGroupDto group:list){
					selectBean = new SelectCsBean();
					selectBean.setKey(String.valueOf(group.getWeixin_group_id()));
					selectBean.setValue(group.getGroup_name());
					weixin_group.add(selectBean);
				}
			}
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		errorMessage = "连接好像出问题了。。。";
		return ERROR;
	}
	
	/***
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getListData() {
		try {
			if (tMsgSendQueryBean == null) {
				tMsgSendQueryBean = new TMsgSendQueryBean();
			}
			//设置只查看当前公众账号内容
			tMsgSendQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tMsgSendService.queryTMsgSendListPage(tMsgSendQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				if(null == d.get("MSG")){
					d.put("MSG", "资源已被删除，无法获取资源名称。");
				}
				d.put("MSG", URLEncoder.encode(d.get("MSG").toString(),"UTF-8").replace("+", "%20"));
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tMsgSend_list.detailFormSubmit('"+ d.get("MSG_ID")+ "','"+d.get("PKID")+"');return false;\"><i class='icon-search'></i></a>");
				
           		
				//只有自定义发送才开放此功能
				if(CodeStringConstant.CS_5059_SEND_DIST_CUSTOM.equals(d.get("SEND_DIST").toString())){
           			d.put("MEMBER","<a href='javascript:return void(0);' onClick=\"tMsgSend_list.memberListForm('"+ d.get("BATCH_NO")+ "');return false;\"><i class='icon-member'></i></a>");
           		}
				d.put("SEND_DIST", CodeStringUtil.tranCsValueByCsCode(d.get("SEND_DIST").toString()));
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
	
	public final TMsgSendQueryBean getTMsgSendQueryBean() {
		return tMsgSendQueryBean;
	}

	public final void setTMsgSendQueryBean(TMsgSendQueryBean tMsgSendQueryBean) {
		this.tMsgSendQueryBean = tMsgSendQueryBean;
	}

	public List<SelectCsBean> getSend_status() {
		return send_status;
	}

	public void setSend_status(List<SelectCsBean> sendStatus) {
		send_status = sendStatus;
	}

	public List<SelectCsBean> getSend_dist() {
		return send_dist;
	}

	public void setSend_dist(List<SelectCsBean> sendDist) {
		send_dist = sendDist;
	}

	public List<SelectCsBean> getSend_target() {
		return send_target;
	}

	public void setSend_target(List<SelectCsBean> sendTarget) {
		send_target = sendTarget;
	}

	public List<SelectCsBean> getSend_if() {
		return send_if;
	}

	public void setSend_if(List<SelectCsBean> sendIf) {
		send_if = sendIf;
	}

	public List<SelectCsBean> getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(List<SelectCsBean> msgType) {
		msg_type = msgType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<SelectCsBean> getWeixin_group() {
		return weixin_group;
	}

	public void setWeixin_group(List<SelectCsBean> weixinGroup) {
		weixin_group = weixinGroup;
	}
	
}
