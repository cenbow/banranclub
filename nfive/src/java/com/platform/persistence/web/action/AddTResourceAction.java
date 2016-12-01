package com.platform.persistence.web.action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.CacheDataSet;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.service.ITResourceService;
 /**
 * 类功能:添加资源数据
 * <p>创建者:zhangzhqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTResourceAction")
@Scope("prototype")
public class AddTResourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResourceService tResourceService;

	@Autowired
	private CacheDataSet cacheDataSet;

	private TResourceDto tResourceDto= new TResourceDto();
	private String parent_res_id;

	public String execute() throws Exception {
		boolean is_val = false;
		try {
			tResourceDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			tResourceService.saveResource(parent_res_id, tResourceDto);
			//刷新缓存
			cacheDataSet.initUrlMap();
			is_val = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		outJSOND(response,"{\"status\":"+is_val+",\"test\":\""+tResourceDto.getRes_name()+"\"}");
		return null;
	}

     public TResourceDto gettResourceDto() {
         return tResourceDto;
     }

     public void settResourceDto(TResourceDto tResourceDto) {
         this.tResourceDto = tResourceDto;
     }

     public String getParent_res_id() {
		return parent_res_id;
	}

	public void setParent_res_id(String parent_res_id) {
		this.parent_res_id = parent_res_id;
	}

}
