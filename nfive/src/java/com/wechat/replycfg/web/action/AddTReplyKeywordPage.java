package com.wechat.replycfg.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;

 /**
 * 类功能:跳转到新增关键字回复规则页面
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTReplyKeywordPage")
@Scope("prototype")
public class AddTReplyKeywordPage extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(AddTReplyKeywordPage.class);

	
	//匹配类型
	private List<SelectCsBean> match_SelList;
	
	//回复类型
	private List<SelectCsBean> reply_SelList;
	
	//生效标志
	private List<SelectCsBean> effect_flag_SelList;
	
	//启用客服模式
	private List<SelectCsBean> cust_srv_flag_SelList;
	
	//出参   关键字回复规则
	private TReplyKeywordDto out_tReplyKeywordDto =  new TReplyKeywordDto();

	public String execute() throws Exception {

		try {
			
			//构造匹配类型下拉列表数据
			match_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.KEYWORK_MATCHING_TYPE,CodeStringConstant.CS_5053_MATCH_TYPE_ALL);
			
			//构造回复类型下拉列表数据
			reply_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.KEYWORK_TYPE,CodeStringConstant.CS_5052_REPLAY_TEXT_MSG);
			
			//构造生效标志下拉列表数据
			effect_flag_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,CodeStringConstant.CS_1000_TRUE);
			
			//构造启用客服模式下拉列表数据
			cust_srv_flag_SelList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,CodeStringConstant.CS_1000_FALSE);
			
			//设置匹配类型下拉列表默认选中值
			out_tReplyKeywordDto.setMatch_type(CodeStringConstant.CS_5053_MATCH_TYPE_ALL);
			
			//设置回复类型下拉列默认选中值
			out_tReplyKeywordDto.setReply_type(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG);
			
			//设置生效标志下拉列默认选中值
			out_tReplyKeywordDto.setEffect_flag(CodeStringConstant.CS_1000_TRUE);
			
			//设置默认发布开始时间
			out_tReplyKeywordDto.setPub_startdate(new Date());
			
			
			//设置默认发布结束时间
			Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.YEAR, 9999);
	        cal.set(Calendar.MONTH, 11);//月份从0开始,设置数值+1等于当前月份
	        cal.set(Calendar.DAY_OF_MONTH, 31);
	        Date date = new Date(cal.getTimeInMillis());
	        out_tReplyKeywordDto.setPub_enddate(date);
			
			
			return SUCCESS;
			
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

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

	public TReplyKeywordDto getOut_tReplyKeywordDto() {
		return out_tReplyKeywordDto;
	}

	public void setOut_tReplyKeywordDto(TReplyKeywordDto outTReplyKeywordDto) {
		out_tReplyKeywordDto = outTReplyKeywordDto;
	}

	public List<SelectCsBean> getEffect_flag_SelList() {
		return effect_flag_SelList;
	}

	public void setEffect_flag_SelList(List<SelectCsBean> effectFlagSelList) {
		effect_flag_SelList = effectFlagSelList;
	}

	public List<SelectCsBean> getCust_srv_flag_SelList() {
		return cust_srv_flag_SelList;
	}

	public void setCust_srv_flag_SelList(List<SelectCsBean> custSrvFlagSelList) {
		cust_srv_flag_SelList = custSrvFlagSelList;
	}

}
