package com.wechat.picture.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.picture.service.ITMaterialPictureService;

 /**
 * 类功能:打开图片资源新增窗口
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("addTMaterialPicturePage")
@Scope("prototype")
public class AddTMaterialPicturePage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	//是否缓存下拉列表
	private List<SelectCsBean> cache_FlagList;
	private String errorMessage;//错误信息
	// 日志对象
	private static final Logger logger = Logger.getLogger(AddTMaterialPictureAction.class);	
	/**
	 * 直接返回至新增页面
	 */
	public String execute() throws Exception {

		try {
			//查询是否缓存下拉列表
			cache_FlagList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,CodeStringConstant.CS_1000_FALSE);
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		errorMessage = "连接好像出问题了。。。";
		return ERROR;

	}
	public List<SelectCsBean> getCache_FlagList() {
		return cache_FlagList;
	}
	public void setCache_FlagList(List<SelectCsBean> cacheFlagList) {
		cache_FlagList = cacheFlagList;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
