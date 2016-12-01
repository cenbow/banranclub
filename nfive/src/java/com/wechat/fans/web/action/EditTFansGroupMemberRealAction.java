package com.wechat.fans.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.service.ITFansGroupMemberRealService;

 /**
 * 类功能:编辑粉丝群关系
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTFansGroupMemberRealAction")
@Scope("prototype")
public class EditTFansGroupMemberRealAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	protected static final Log log = LogFactory.getLog(EditTFansGroupMemberRealAction.class);
	
	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	private TFansGroupMemberRealDto in_tFansGroupMemberRealDto =new TFansGroupMemberRealDto();;

	public String execute() throws Exception {
		
		//返回结果提示
		JSONObject jsonInfo = new JSONObject();
		try {
		    
			  //设置参数  查询保存数据
			  TFansGroupMemberRealDto param_tFansGroupMemberRealDto = new TFansGroupMemberRealDto();
			  param_tFansGroupMemberRealDto.setReal_id(in_tFansGroupMemberRealDto.getReal_id());//关系ID
			  param_tFansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//公众号
		      TFansGroupMemberRealDto tmpTFansGroupMemberRealDto =tFansGroupMemberRealService.getRow(param_tFansGroupMemberRealDto);
		      
		      //设置成员名称
			  tmpTFansGroupMemberRealDto.setMember_name(in_tFansGroupMemberRealDto.getMember_name().trim());
			  //设置标星成员
			  tmpTFansGroupMemberRealDto.setStar_member(in_tFansGroupMemberRealDto.getStar_member());
			  //设置成员备注
			  tmpTFansGroupMemberRealDto.setMember_memo(in_tFansGroupMemberRealDto.getMember_memo());
			  //设置更新人
			  tmpTFansGroupMemberRealDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			  //设置公众号
			  tmpTFansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//公众号
		
			  tFansGroupMemberRealService.updatePK(tmpTFansGroupMemberRealDto);
			  
			  jsonInfo.put("message", "编辑操作成功！");
			  jsonInfo.put("success", true);
			  outJSOND(response, jsonInfo.toString());
			  return null;
		} catch (Exception ex) {
			jsonInfo.put("message", "编辑操作失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}

	public TFansGroupMemberRealDto getIn_tFansGroupMemberRealDto() {
		return in_tFansGroupMemberRealDto;
	}

	public void setIn_tFansGroupMemberRealDto(
			TFansGroupMemberRealDto inTFansGroupMemberRealDto) {
		in_tFansGroupMemberRealDto = inTFansGroupMemberRealDto;
	}
}
