package com.pub.persistence.web.action;

import java.util.List;

import com.pub.common.tools.constant.CodeStringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.codestring.CodeStringUtil;
import com.pub.common.tools.codestring.SelectCsBean;
import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.model.TResourceQueryBean;
import com.pub.persistence.service.ITResourceService;

/**
 * 类功能:跳转到添加资源数据页面
 * <p>
 * 创建者:zhangzhiqiang
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

@Controller("addTResourcePage")
@Scope("prototype")
public class AddTResourcePage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResourceService tResourceService;
	// 入参、出参
	private TResourceQueryBean tResourceQueryBean = new TResourceQueryBean();
	// 下拉框列表
	private List<SelectCsBean> resourceList;
	// 获取当前节点的编码
	private String code;

	public String execute() throws Exception {
		try {
			resourceList = CodeStringUtil.getSelectCsBeanByCsType(
					CodeStringConstant.RESOURCE_FUC_TYPE,
					CodeStringConstant.RESOURCE_FUC_NOT_INPUT);
			// 获取当前节点的编码
			code = tResourceService.getCode(tResourceQueryBean);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	public TResourceQueryBean getTResourceQueryBean() {
		return tResourceQueryBean;
	}

	public void setTResourceQueryBean(TResourceQueryBean tResourceQueryBean) {
		this.tResourceQueryBean = tResourceQueryBean;
	}

	public List<SelectCsBean> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<SelectCsBean> resourceList) {
		this.resourceList = resourceList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
