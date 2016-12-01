package com.pub.persistence.web.action;
import com.pub.common.local.model.dto.TResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.common.tools.permission.CacheDataSet;
import com.pub.common.tools.permission.LoginUserInfoUtil;
import com.pub.persistence.service.ITResourceService;

 /**
 * 类功能:资源数据编辑
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTResourceAction")
@Scope("prototype")
public class EditTResourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResourceService tResourceService;
	@Autowired
	private CacheDataSet cacheDataSet;

	private TResourceDto tResourceDto =new TResourceDto();;

	public String execute() throws Exception {
		try {

		      TResourceDto tmpTResourceDto = new TResourceDto();
			  tmpTResourceDto.setRes_id(tResourceDto.getRes_id());
			 //tmpTResourceDto.setRes_code(tResourceDto.getRes_code());
			  tmpTResourceDto.setRes_name(tResourceDto.getRes_name());
			  tmpTResourceDto.setRes_desc(tResourceDto.getRes_desc());
			  tmpTResourceDto.setRes_url(tResourceDto.getRes_url());
			  tmpTResourceDto.setParameter(tResourceDto.getParameter());
			  tmpTResourceDto.setTarget(tResourceDto.getTarget());
			  tmpTResourceDto.setNetwork(tResourceDto.getNetwork());
			  tmpTResourceDto.setRes_type(tResourceDto.getRes_type());
			  tmpTResourceDto.setFunc_type(tResourceDto.getFunc_type());
			  tmpTResourceDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			  tResourceService.updatePK(tmpTResourceDto);
			  //刷新缓存
			  cacheDataSet.initUrlMap();

			  outJSOND(response,"{\"status\":"+true+",\"text\":\""+tmpTResourceDto.getRes_name()+"\"}");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	public final TResourceDto getTResourceDto() {
		return tResourceDto;
	}

	public final void setTResourceDto(TResourceDto tResourceDto) {
		this.tResourceDto = tResourceDto;
	}

}
