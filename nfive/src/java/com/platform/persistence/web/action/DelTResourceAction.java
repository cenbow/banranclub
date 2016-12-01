package com.platform.persistence.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.CacheDataSet;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.service.ITResourceService;

 /**
 * 类功能:删除资源
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("delTResourceAction")
@Scope("prototype")
public class DelTResourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResourceService tResourceService;
	@Autowired
	private CacheDataSet cacheDataSet;
	
	private  String pkid;

	public String execute() throws Exception {
		String result = "";
		try {
			TResourceDto paramTResourceDto = new TResourceDto();
			//设置主键
			paramTResourceDto.setRes_id(pkid);
			result = tResourceService.deleteResource(paramTResourceDto);
			//刷新缓存
			cacheDataSet.initUrlMap();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		outJSOND(response,result); 
		return null;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

}
