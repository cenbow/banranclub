package com.wechat.fans.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.service.ITFansGroupService;

 /**
 * 类功能:新增粉丝群
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTFansGroupAction")
@Scope("prototype")
public class AddTFansGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupService tFansGroupService;
	private TFansGroupDto in_tFansGroupDto= new TFansGroupDto();
	
	protected static final Log log = LogFactory.getLog(AddTFansGroupAction.class);

	public String execute() throws Exception {
		
		//返回结果提示
		JSONObject jsonInfo = new JSONObject();
		try {
			
			TFansGroupDto paramTFansGroupDto  =  new TFansGroupDto();
			paramTFansGroupDto.setGroup_name(in_tFansGroupDto.getGroup_name().trim());//设置群名称
			paramTFansGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//设置公众号
			List<TFansGroupDto> checkList = tFansGroupService.checktFansGroupExist(paramTFansGroupDto);
			
			if (null != checkList && checkList.size() > 0){
				
				jsonInfo.put("message", "此记录已存在,请重新填写！");
				jsonInfo.put("success", false);
				outJSOND(response, jsonInfo.toString());
				return null;
			}
			
			//群名称
			in_tFansGroupDto.setGroup_name(in_tFansGroupDto.getGroup_name().trim());
			//设置公众号
			in_tFansGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			//设置创建人
			in_tFansGroupDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			
			
			tFansGroupService.save(in_tFansGroupDto);
			jsonInfo.put("message", "新增操作成功");
			jsonInfo.put("success", true);
			outJSOND(response, jsonInfo.toString());
			return null;
			
		} catch (Exception ex) {
			jsonInfo.put("message", "新增操作失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}

	public TFansGroupDto getIn_tFansGroupDto() {
		return in_tFansGroupDto;
	}

	public void setIn_tFansGroupDto(TFansGroupDto inTFansGroupDto) {
		in_tFansGroupDto = inTFansGroupDto;
	}


}
