package com.wechat.fans.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.service.ITFansGroupMemberRealService;

 /**
 * 类功能:删除粉丝群的粉丝关系
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTFansGroupMemberRealAction")
@Scope("prototype")
public class DelTFansGroupMemberRealAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(DetailTFansGroupMemberRealPage.class);

	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	
	private  String pkids;

	public String execute() throws Exception {
		
		//返回结果提示
		JSONObject jsonInfo = new JSONObject();
		try {
			
			//自定义删除方法
			int result = tFansGroupMemberRealService.delfansByPkids(pkids).intValue();
			if (result > 0){
				jsonInfo.put("message", result+"个群成员被成功移除该组！");
				jsonInfo.put("success", true);
			}else{
				jsonInfo.put("message", "移除成员操作失败！");
				jsonInfo.put("success", false);
			}
			outJSOND(response, jsonInfo.toString());
			return null;
		} catch (Exception ex) {
			jsonInfo.put("message", "移除成员操作失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		}
		return ERROR;
	}

	public String getPkids() {
		return pkids;
	}

	public void setPkids(String pkids) {
		this.pkids = pkids;
	}



}
