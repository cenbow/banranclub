package com.wechat.crowdsend.web.action;

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
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITFansGroupService;
import com.wechat.fans.service.ITWeixinGroupService;

 /**
 * 类功能:查看一个批次内单个发送粉丝群信息
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTMsgSendMemberAction")
@Scope("prototype")
public class SearchTMsgSendMemberAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMsgSendService tMsgSendService;
	@Autowired
	private ITFansGroupService tFansGroupService;
	@Autowired
	private ITWeixinGroupService tWeixinGroupService;
	private String errorMessage;//错误信息
	//是否关注下拉列表
	private List<SelectCsBean> subscribe_flg;
	//是否绑定一账通下拉列表
	private List<SelectCsBean> oneAccount_flg;
	//性别下拉列表
	private List<SelectCsBean> sex_flg;
	//微信分组
	private List<SelectCsBean> weixin_group;
	//用户群组
	private List<TFansGroupDto> fans_group;
	//批次号
	private String batch_no;
	//本地粉丝群
	private String fans_crowds;
	//入参，查询参数
	private TWeixinFansQueryBean tWeixinFansQueryBean = new TWeixinFansQueryBean();
	//日志记录对象
	private static final Logger logger = Logger.getLogger(CrowdTMsgSendAction.class);
	public String execute() throws Exception {
		try {
			//是否关注下拉列表获取
			subscribe_flg = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG, CodeStringConstant.CS_1000_TRUE);
			//获取是否绑定一账通下拉列表
			oneAccount_flg = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
			//获取性别下拉列表
			sex_flg = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_1015_SEX_FLAG);
			//获取本地微信分组
			TWeixinGroupDto tWeixinGroupDto = new TWeixinGroupDto();
			tWeixinGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			List<TWeixinGroupDto> list = tWeixinGroupService.getAll(tWeixinGroupDto);
			weixin_group = new ArrayList<SelectCsBean>();
			SelectCsBean cs = new SelectCsBean();
			cs.setKey("");
			cs.setValue("未输入");
			weixin_group.add(cs);
			for(TWeixinGroupDto group:list){
				cs = new SelectCsBean();
				cs.setKey(String.valueOf(group.getWeixin_group_id()));
				cs.setValue(group.getGroup_name());
				weixin_group.add(cs);
			}
			//获取本地用户群组
			TFansGroupDto tFansGroupDto = new TFansGroupDto();
			tFansGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			fans_group = tFansGroupService.getAll(tFansGroupDto);
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
			if (tWeixinFansQueryBean == null) {
				tWeixinFansQueryBean = new TWeixinFansQueryBean();
			}
			
			if(tWeixinFansQueryBean.getFans_grouplist() != null && !"".equals(tWeixinFansQueryBean.getFans_grouplist())){
				tWeixinFansQueryBean.setGroupId_list(tWeixinFansQueryBean.getFans_grouplist().split(","));
			}else{
				tWeixinFansQueryBean.setGroupId_list(null);
			}
			tWeixinFansQueryBean.setBatch_no(batch_no);
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tMsgSendService.queryTMsgSendMemberListPage(tWeixinFansQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tMsgSendMember_list.fansDetail('"+ d.get("FANS_ID")+ "');return false;\"><i class='icon-search'></i></a>");
           		if(null != d.get("HEAD_IMG_URL")){
           			d.put("HEAD_IMG", "<img src=\""+d.get("HEAD_IMG_URL").toString()+"\" width=\"75px;\" height=\"30px;\"></img>");
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
			logger.error(ex.getMessage(), ex);
		}

		return null;
	}
	
	public ITMsgSendService getTMsgSendService() {
		return tMsgSendService;
	}


	public void setTMsgSendService(ITMsgSendService tMsgSendService) {
		this.tMsgSendService = tMsgSendService;
	}


	public List<SelectCsBean> getSubscribe_flg() {
		return subscribe_flg;
	}


	public void setSubscribe_flg(List<SelectCsBean> subscribeFlg) {
		subscribe_flg = subscribeFlg;
	}


	public List<SelectCsBean> getOneAccount_flg() {
		return oneAccount_flg;
	}


	public void setOneAccount_flg(List<SelectCsBean> oneAccountFlg) {
		oneAccount_flg = oneAccountFlg;
	}


	public List<SelectCsBean> getSex_flg() {
		return sex_flg;
	}


	public void setSex_flg(List<SelectCsBean> sexFlg) {
		sex_flg = sexFlg;
	}


	public List<SelectCsBean> getWeixin_group() {
		return weixin_group;
	}


	public void setWeixin_group(List<SelectCsBean> weixinGroup) {
		weixin_group = weixinGroup;
	}


	public List<TFansGroupDto> getFans_group() {
		return fans_group;
	}


	public void setFans_group(List<TFansGroupDto> fansGroup) {
		fans_group = fansGroup;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batchNo) {
		batch_no = batchNo;
	}

	public TWeixinFansQueryBean getTWeixinFansQueryBean() {
		return tWeixinFansQueryBean;
	}

	public void setTWeixinFansQueryBean(TWeixinFansQueryBean tWeixinFansQueryBean) {
		this.tWeixinFansQueryBean = tWeixinFansQueryBean;
	}

	public String getFans_crowds() {
		return fans_crowds;
	}

	public void setFans_crowds(String fansCrowds) {
		fans_crowds = fansCrowds;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
