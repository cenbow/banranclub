package com.wechat.replycfg.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;

 /**
 * 类功能:检查相同回复规则只有一个处于启用状态
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("checkTReplySpecialAction")
@Scope("prototype")
public class CheckTReplySpecialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplySpecialService tReplySpecialService;
	//入参
	private  String  check_rule_type;
	//出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(CheckTReplySpecialAction.class);
	
	public String execute() throws Exception {
	   //返回结果
		String result = null;
	   //给一个公众号， 四种数据  （一个首次关注<启用，不启用>，一个无关键字匹配<启用，无启用>）
		try {
			TReplySpecialQueryBean tReplySpecialQueryBean = new TReplySpecialQueryBean();
			//设置操作人所用的公众号ID
			tReplySpecialQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			//设置特殊回复规则
			tReplySpecialQueryBean.setRule_type(check_rule_type);
			List<TReplySpecialDto>  tmpTReplySpecialDto = tReplySpecialService.checktReplySpecialOnlyOne(tReplySpecialQueryBean);
			//验证通过
             if(tmpTReplySpecialDto.size() == 0){
            	 result = "{\"status\":true,\"message\":\"特殊回复规则可进行添加\"}";
				 outJSOND(this.getResponse(), result);
					
				 return null;
             }else{
				for (int i = 0; i < tmpTReplySpecialDto.size(); i++) {
					// 验证未通过
					if (tmpTReplySpecialDto.get(i).getRule_type().equals(CodeStringConstant.CS_5054_REPLY_RULE_FIRST)
							&& tmpTReplySpecialDto.get(i).getEffect_flag().equals(CodeStringConstant.CS_1000_TRUE)) {
						result = "{\"status\":false,\"message\":\"当前的公众号已有启用的首次关注回复\"}";
						outJSOND(this.getResponse(), result);

						return null;
					} else if (tmpTReplySpecialDto.get(i).getRule_type().equals(CodeStringConstant.CS_5054_REPLY_RULE_NOT_KEYWORD)
							&& tmpTReplySpecialDto.get(i).getEffect_flag().equals(CodeStringConstant.CS_1000_TRUE)) {
						result = "{\"status\":false,\"message\":\"当前的公众号已有启用的关键字无匹配回复\"}";
						outJSOND(this.getResponse(), result);

						return null;
					}
				}
				// 验证通过
				result = "{\"status\":true,\"message\":\"特殊回复规则可进行添加\"}";
				outJSOND(this.getResponse(), result);
				return null;
             }
			//验证失败
		} catch (Exception ex) {
			result = "{\"status\":false,\"message\":\"当前的公众号已有启用首次关注回复或无关键字匹配回复\"}";
			outJSOND(this.getResponse(), result);
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}
		return ERROR;
	}

	public final String getCheck_rule_type() {
		return check_rule_type;
	}

	public final void setCheck_rule_type(String checkRuleType) {
		check_rule_type = checkRuleType;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
