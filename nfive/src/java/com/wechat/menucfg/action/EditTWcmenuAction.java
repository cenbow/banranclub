package com.wechat.menucfg.action;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.platform.common.tools.wechat.WechatUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.menucfg.util.JsonDataUtil;

/**
 * 类功能:自动代码生成模板编辑 action 模板
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

@Controller("editTWcmenuAction")
@Scope("prototype")
public class EditTWcmenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
	.getLogger(EditTWcmenuAction.class);

	@Autowired
	private ITWcmenuService tWcmenuService;
	// 入参
	private TWcmenuQueryBean tWcmenuQueryBean = new TWcmenuQueryBean();

	public String execute() throws Exception {
		try {

			TWcmenuDto tmpTWcmenuDto = new TWcmenuDto();
			tmpTWcmenuDto.setMenu_id(tWcmenuQueryBean.getMenu_id());
			tmpTWcmenuDto.setMenu_name(StringUtils.trimToNull(tWcmenuQueryBean
					.getMenu_name()));
			tmpTWcmenuDto.setMenu_desc(StringUtils.trimToNull(tWcmenuQueryBean
					.getMenu_desc()));
			tmpTWcmenuDto.setAction_type(StringUtils
					.trimToNull(tWcmenuQueryBean.getAction_type()));
			if (tWcmenuQueryBean.getText_msg() != null) {
				tmpTWcmenuDto.setText_msg(tWcmenuQueryBean.getText_msg());
			} else {
				tmpTWcmenuDto.setText_msg(null);
			}
			tmpTWcmenuDto.setMaterial_id(tWcmenuQueryBean.getMaterial_id());
			tmpTWcmenuDto.setAction_url(tWcmenuQueryBean.getAction_url());
            tmpTWcmenuDto.setAction_class(tWcmenuQueryBean.getAction_class());
			tmpTWcmenuDto.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());
			tmpTWcmenuDto.setRela_sort(tWcmenuQueryBean.getRela_sort());
			tmpTWcmenuDto
					.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			//设置是否动态模板    页面是否动态模板的输入值  1.CodeStringConstant.CS_1000_FALSE==未选中  2.CodeStringConstant.CS_1000_TRUE==选中
			if (CodeStringConstant.CS_1000_TRUE.equalsIgnoreCase(tWcmenuQueryBean.getTemplet_flag())){
				tmpTWcmenuDto.setTemplet_flag(CodeStringConstant.CS_1000_TRUE);
			}else {
				tmpTWcmenuDto.setTemplet_flag(CodeStringConstant.CS_1000_FALSE);
			}
			//设置更新的启用客服模式
			tmpTWcmenuDto.setCust_srv_flag(tWcmenuQueryBean.getCust_srv_flag());
			tWcmenuService.updatePK(tmpTWcmenuDto);
			tmpTWcmenuDto = null;
			// 输出data
			outJSOND(getResponse(), JsonDataUtil
					.parseJson("", "", "", tWcmenuQueryBean.getMenu_name(),
							String.valueOf(tWcmenuQueryBean.getRela_sort()),
							false, tWcmenuQueryBean.getAction_type(),
							tWcmenuQueryBean.getMaterial_id(), tWcmenuQueryBean
									.getAction_url()));
			return null;
		} catch (Exception ex) {
			logger.error("------编辑菜单异常------\r\n",ex);
		}
		return ERROR;
	}

	public TWcmenuQueryBean getTWcmenuQueryBean() {
		return tWcmenuQueryBean;
	}

	public void setTWcmenuQueryBean(TWcmenuQueryBean tWcmenuQueryBean) {
		this.tWcmenuQueryBean = tWcmenuQueryBean;
	}
}
