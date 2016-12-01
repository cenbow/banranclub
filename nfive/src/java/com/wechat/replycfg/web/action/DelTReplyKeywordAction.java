package com.wechat.replycfg.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.wechat.replycfg.service.ITReplyKeywordService;

 /**
 * 类功能:删除关键字回复规则
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTReplyKeywordAction")
@Scope("prototype")
public class DelTReplyKeywordAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(AddTReplyKeywordPage.class);

	@Autowired
	private ITReplyKeywordService tReplyKeywordService;
	
	private  String pkid;

	public String execute() throws Exception {
		
		
		//返回结果输出
		JSONObject jsonInfo = new JSONObject();
		try {
			
			TReplyKeywordDto paramTReplyKeywordDto = new TReplyKeywordDto();
			
			//设置主键
			paramTReplyKeywordDto.setKreply_id(pkid);
			//删除数据
			tReplyKeywordService.deletePK(paramTReplyKeywordDto);
			
			
			jsonInfo.put("message", "删除操作成功！");
			jsonInfo.put("success", true);
			outJSOND(response, jsonInfo.toString());
			
			return null;
		} catch (Exception ex) {
			jsonInfo.put("message", "删除操作失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		}
		
		return ERROR;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
