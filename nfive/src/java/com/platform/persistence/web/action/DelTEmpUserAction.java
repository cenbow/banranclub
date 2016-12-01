package com.platform.persistence.web.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.service.ITEmpUserService;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;

 /**
 * 类功能:删除员工
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTEmpUserAction")
@Scope("prototype")
public class DelTEmpUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	
	@Autowired
	private ITPlatformempCfgService tPlatformempCfgService;
	
	private  String pkid;
	
	private  String userCd;

	public String execute() throws Exception {
		try {
			TEmpUserDto paramTEmpUserDto = new TEmpUserDto();
			TPlatformempCfgDto tPlatformempCfgDto = new TPlatformempCfgDto();
			//设置主键
			paramTEmpUserDto.setUser_id(pkid);
			tPlatformempCfgDto.setUser_cd(userCd);
			List <TPlatformempCfgDto> resultInfo=tPlatformempCfgService.getAll(tPlatformempCfgDto);
			if(resultInfo.size()>0)//如果删除的员工号已绑定会员，那么无法删除
			{
				outJSOND(response, "{\"status\":"+false+",\"message\":\"\"}");
			}else//如果删除的员工号未绑定会员，那么可以删除
			{
				String jsonMsg = tEmpUserService.deleteTEmpUser(paramTEmpUserDto);
				JSONObject  jsonObject = JSONObject.fromObject(jsonMsg);
				if("".equals(jsonObject.get("message"))){
					outJSOND(response, "{\"status\":"+true+",\"message\":\"\"}");
				}else{
					outJSOND(response, "{\"status\":"+false+",\"message\":\"\"}");
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			return null;
	}

	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
}
