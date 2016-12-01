package com.wechat.pfcfg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;
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
 
@Controller("delTPubPlatformAction")
@Scope("prototype")
public class DelTPubPlatformAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITPubPlatformService tPubPlatformService;
	@Autowired
	private WechatInfoUtil wechatInfoUtil;
	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	
	private  String pkid;

	public String execute() throws Exception {
		try {
			TPubPlatformDto paramTPubPlatformDto = new TPubPlatformDto();
			TPlatformempCfgDto tPlatformempCfgDto = new TPlatformempCfgDto();
			tPlatformempCfgDto.setPlatform_id(pkid);
			//如果公众号已经绑定，不能删除。
			List<TPlatformempCfgDto> platFormList = tPlatformempCfgService.getAll(tPlatformempCfgDto);
			
			if(platFormList.size()>0)
			{
				outJSOND(response,"{\"status\":"+false+",\"mess\":\""+"已存在相同的公众号ID或原始ID，请您重新输入：）"+"\"}");
				return null;
			}
			
			//设置主键
			paramTPubPlatformDto.setPlatform_id(pkid);
			tPubPlatformService.deletePK(paramTPubPlatformDto);
			//刷新缓存
			wechatInfoUtil.InitConfig();
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
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
