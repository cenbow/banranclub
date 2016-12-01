package com.wechat.replycfg.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;

 /**
 * 类功能:删除特殊回复
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTReplySpecialAction")
@Scope("prototype")
public class DelTReplySpecialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplySpecialService tReplySpecialService;
	//入参
	private  String pkid;
	//出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(DelTReplySpecialAction.class);
	
	public String execute() throws Exception {
	     //返回结果
		String result = null;
		try {
			TReplySpecialDto paramTReplySpecialDto = new TReplySpecialDto();
		    //设置主键
			paramTReplySpecialDto.setFreply_id(pkid);
			tReplySpecialService.deletePK(paramTReplySpecialDto);
			//删除成功
			result = "{\"status\":true,\"message\":\"特殊回复规则删除成功\"}";
			outJSOND(this.getResponse(), result);
			
			return null;
		} catch (Exception ex) {
			//删除失败
			result = "{\"status\":false,\"message\":\"特殊回复规则删除失败\"}";
			outJSOND(this.getResponse(), result);
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}
		
		return ERROR;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
