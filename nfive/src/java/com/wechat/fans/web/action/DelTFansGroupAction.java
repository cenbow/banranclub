package com.wechat.fans.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.service.ITFansGroupService;

 /**
 * 类功能:删除粉丝群
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTFansGroupAction")
@Scope("prototype")
public class DelTFansGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupService tFansGroupService;
	
	//主键参数
	private  String pkid;
	
	protected static final Log log = LogFactory.getLog(DelTFansGroupAction.class);

	public String execute() throws Exception {
		
		//返回结果提示
		JSONObject jsonInfo = new JSONObject();
		try {
			TFansGroupDto paramTFansGroupDto = new TFansGroupDto();
			
			// 自定义删除方法，删除群组的同时需要删除群组下所有的成员关系
			paramTFansGroupDto.setFans_group_id(pkid);//设置主键
			tFansGroupService.deltFansGroup(paramTFansGroupDto);
			
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
