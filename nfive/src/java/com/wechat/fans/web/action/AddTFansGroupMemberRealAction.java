package com.wechat.fans.web.action;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.service.ITFansGroupMemberRealService;

 /**
 * 类功能:自动代码生成模板新增   action 模板
 * <p>创建者:yan.guo</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTFansGroupMemberRealAction")
@Scope("prototype")
public class AddTFansGroupMemberRealAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(AddTFansGroupAction.class);

	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	private TFansGroupMemberRealDto tFansGroupMemberRealDto= new TFansGroupMemberRealDto();
	
	private String tfansgroup_pkid;//粉丝群ID
	
	private String fans_pkids;//粉丝ID

	public String execute() throws Exception {
		
		JSONObject jsonInfo = new JSONObject();
		try {
			int result = tFansGroupMemberRealService.addfansByList(tfansgroup_pkid, fans_pkids).intValue();
			
			jsonInfo.put("message", "成功添加"+result+"位群成员！");
			jsonInfo.put("success", true);
			outJSOND(response, jsonInfo.toString());
			return null;
		} catch (Exception ex) {
			jsonInfo.put("message", "添加位群成员失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}
	
	/**
	 * 增加所有粉丝到组
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addAll() throws Exception {
		
		JSONObject jsonInfo = new JSONObject();
		try {
			int result = tFansGroupMemberRealService.addfansAll(tfansgroup_pkid).intValue();
			
			jsonInfo.put("message", "成功添加"+result+"位群成员！");
			jsonInfo.put("success", true);
			outJSOND(response, jsonInfo.toString());
			return null;
		} catch (Exception ex) {
			jsonInfo.put("message", "添加位群成员失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}

	
	

	public final TFansGroupMemberRealDto getTFansGroupMemberRealDto() {
		return tFansGroupMemberRealDto;
	}

	public final void setStudentDto(TFansGroupMemberRealDto tFansGroupMemberRealDto) {
		this.tFansGroupMemberRealDto = tFansGroupMemberRealDto;
	}
	public String getTfansgroup_pkid() {
		return tfansgroup_pkid;
	}

	public void setTfansgroup_pkid(String tfansgroupPkid) {
		tfansgroup_pkid = tfansgroupPkid;
	}

	public String getFans_pkids() {
		return fans_pkids;
	}

	public void setFans_pkids(String fansPkids) {
		fans_pkids = fansPkids;
	}



}
