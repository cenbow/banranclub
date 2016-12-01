package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.persistence.model.dto.TResSelfRelaDto;
import com.platform.persistence.service.ITResSelfRelaService;

 /**
 * 类功能:资源关系数据编辑
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTResSelfRelaAction")
@Scope("prototype")
public class EditTResSelfRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResSelfRelaService tResSelfRelaService;
	private TResSelfRelaDto tResSelfRelaDto =new TResSelfRelaDto();;

	public String execute() throws Exception {
		boolean boo = true;
		try {
		      TResSelfRelaDto tmpTResSelfRelaDto = new TResSelfRelaDto();
			  tmpTResSelfRelaDto.setParent_res_id(tResSelfRelaDto.getParent_res_id());
			  tmpTResSelfRelaDto.setChild_res_id(tResSelfRelaDto.getChild_res_id());
			  tmpTResSelfRelaDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			  tResSelfRelaService.updateResRela(tmpTResSelfRelaDto);
		} catch (Exception ex) {
			boo = false;
			ex.printStackTrace();
		}
		outJSOND(response,"{\"status\":"+boo+",\"text\":\""+""+"\"}"); 
		return null;
	}

	public final TResSelfRelaDto getTResSelfRelaDto() {
		return tResSelfRelaDto;
	}

	public final void setStudentDto(TResSelfRelaDto tResSelfRelaDto) {
		this.tResSelfRelaDto = tResSelfRelaDto;
	}

}
