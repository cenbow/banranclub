package com.wechat.fans.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.dto.TFansGroupDto;
import com.wechat.fans.service.ITFansGroupService;

 /**
 * 类功能:跳转到编辑粉丝群页面
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTFansGroupPage")
@Scope("prototype")
public class EditTFansGroupPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupService tFansGroupService;
	
	//入参
	private  String     pkid;
	
	//出参
	private TFansGroupDto out_tFansGroupDto = new TFansGroupDto();
	
	protected static final Log log = LogFactory.getLog(EditTFansGroupPage.class);
	
	public String execute() throws Exception {
		try {
		    TFansGroupDto paramTFansGroupDto = new TFansGroupDto();
		    paramTFansGroupDto.setFans_group_id(pkid);//设置主键
		    out_tFansGroupDto = tFansGroupService.getRow(paramTFansGroupDto);
		    
			return SUCCESS;
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}


	
	
	public TFansGroupDto getOut_tFansGroupDto() {
		return out_tFansGroupDto;
	}

	public void setOut_tFansGroupDto(TFansGroupDto outTFansGroupDto) {
		out_tFansGroupDto = outTFansGroupDto;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
