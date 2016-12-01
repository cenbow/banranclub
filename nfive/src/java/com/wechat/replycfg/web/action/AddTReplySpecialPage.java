package com.wechat.replycfg.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.replycfg.model.dto.TReplySpecialDto;

 /**
 * 类功能:跳转特殊回复添加页面
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTReplySpecialPage")
@Scope("prototype")
public class AddTReplySpecialPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	//出参
	//特殊回复规则
	private List<SelectCsBean> rule_typeList;
	//回复类型
	private List<SelectCsBean> reply_typeList;
	//生效标志
	private List<SelectCsBean> effect_flagList;
	
	private TReplySpecialDto tReplySpecialDto = new TReplySpecialDto();
	
	protected static final Logger logger = Logger.getLogger(AddTReplySpecialPage.class);
	//出错信息
    private String errorMessage;
    
	public String execute() throws Exception {

		try {
			//特殊回复规则下拉列表
			rule_typeList  =  CodeStringUtil.getSelectCsBeanByCsType(
					CodeStringConstant.REPLY_RULE,CodeStringConstant.CS_5054_REPLY_RULE_FIRST);
			//回复类型下拉列表
			reply_typeList  = CodeStringUtil.getSelectCsBeanByCsType(
					CodeStringConstant.KEYWORK_TYPE,CodeStringConstant.CS_5052_REPLAY_TEXT_MSG);
			//生效标志下拉列表
			effect_flagList  = CodeStringUtil.getSelectCsBeanByCsType(
					CodeStringConstant.TRUE_FALSE_FLAG,CodeStringConstant.CS_1000_TRUE);
			
			return SUCCESS;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;
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

	public final TReplySpecialDto getTReplySpecialDto() {
		return tReplySpecialDto;
	}

	public final void setTReplySpecialDto(TReplySpecialDto tReplySpecialDto) {
		this.tReplySpecialDto = tReplySpecialDto;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
