package com.wechat.pfusercfg.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;

 /**
 * 类功能:自动代码生成模板删除   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTPlatformempCfgAction")
@Scope("prototype")
public class DelTPlatformempCfgAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	
	private TPlatformempCfgDto tPlatformempCfgDto= new TPlatformempCfgDto();

	public String execute() throws Exception {
		
		try {
			System.out.println("hello");
			tPlatformempCfgService.configTPlatformempCfgList(tPlatformempCfgDto, "delete");
			outJSOND(response,"{\"status\":"+true+",\"mess\":\"\"}");
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;

	}

	public TPlatformempCfgDto getTPlatformempCfgDto() {
		return tPlatformempCfgDto;
	}



	public void setTPlatformempCfgDto(TPlatformempCfgDto tPlatformempCfgDto) {
		this.tPlatformempCfgDto = tPlatformempCfgDto;
	}



}
