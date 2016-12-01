package com.platform.persistence.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.service.ITResourceService;

/**
 * 类功能:跳转到编辑资源数据页面
 * <p>
 * 创建者:
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */

@Controller("editTResourcePage")
@Scope("prototype")
public class EditTResourcePage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResourceService tResourceService;
	// 入参
	private String pkid;

	// 出参
	private TResourceDto tResourceDto;

	// 下拉框列表
	private List<SelectCsBean> resourceList;

	public String execute() throws Exception {
		try {
			TResourceDto paramTResourceDto = new TResourceDto();
			// 设置主键
			paramTResourceDto.setRes_id(pkid);
			tResourceDto = tResourceService.getRow(paramTResourceDto);
			resourceList = CodeStringUtil.getSelectCsBeanByCsType(
					CodeStringConstant.RESOURCE_FUC_TYPE, tResourceDto
							.getFunc_type());
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public TResourceDto getTResourceDto() {
		return tResourceDto;
	}

	public void setTResourceDto(TResourceDto tResourceDto) {
		this.tResourceDto = tResourceDto;
	}

	public List<SelectCsBean> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<SelectCsBean> resourceList) {
		this.resourceList = resourceList;
	}

}
