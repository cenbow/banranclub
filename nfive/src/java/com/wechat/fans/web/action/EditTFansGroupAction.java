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
 * 类功能:编辑粉丝群
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTFansGroupAction")
@Scope("prototype")
public class EditTFansGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupService tFansGroupService;
	private TFansGroupDto in_tFansGroupDto =new TFansGroupDto();
	
	
	protected static final Log log = LogFactory.getLog(EditTFansGroupAction.class);

	public String execute() throws Exception {
		
		//返回结果提示
		JSONObject jsonInfo = new JSONObject();
		try {
			
			//校验此公众号下是否存在此数据
			TFansGroupDto paramTFansGroupDto = new TFansGroupDto();
			paramTFansGroupDto.setGroup_name(in_tFansGroupDto.getGroup_name().trim());//粉丝群组名称
			paramTFansGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//公众号
			List<TFansGroupDto> checkList = tFansGroupService.checktFansGroupExist(paramTFansGroupDto);
			if (null != checkList && checkList.size() > 0 ){
				for (TFansGroupDto tFansGroupDto:checkList){
					if (!tFansGroupDto.getFans_group_id().equalsIgnoreCase(in_tFansGroupDto.getFans_group_id())){
						jsonInfo.put("message", "此记录已存在,请重新填写！");
						jsonInfo.put("success", false);
						outJSOND(response, jsonInfo.toString());
						return null;
					}
				}
			}
			
			//取出数据库原有数据
			paramTFansGroupDto = new TFansGroupDto();//重新设置参数
			paramTFansGroupDto.setFans_group_id(in_tFansGroupDto.getFans_group_id());//设置ID
			TFansGroupDto tmpTFansGroupDto = tFansGroupService.getRow(paramTFansGroupDto);
			
			//只设置需要修改数据
			tmpTFansGroupDto.setGroup_name(in_tFansGroupDto.getGroup_name().trim());//粉丝群名称
			tmpTFansGroupDto.setRemark(in_tFansGroupDto.getRemark());//粉丝群描述
			tmpTFansGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());//更新人
			tmpTFansGroupDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//公众号
			
			tFansGroupService.updatePK(tmpTFansGroupDto);
			
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

	public TFansGroupDto getIn_tFansGroupDto() {
		return in_tFansGroupDto;
	}

	public void setIn_tFansGroupDto(TFansGroupDto inTFansGroupDto) {
		in_tFansGroupDto = inTFansGroupDto;
	}
	
}
