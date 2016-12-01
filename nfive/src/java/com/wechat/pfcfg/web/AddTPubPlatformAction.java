package com.wechat.pfcfg.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.menucfg.service.ITWcmSelfRelaService;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;

/**
 * 类功能:自动代码生成模板新增 action 模板
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

@Controller("addTPubPlatformAction")
@Scope("prototype")
public class AddTPubPlatformAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	// 日志
	private static final Logger logger = Logger
			.getLogger(AddTPubPlatformAction.class);

	@Autowired
	private ITPubPlatformService tPubPlatformService;
	@Autowired
	private ITWcmSelfRelaService tWcmSelfRelaService;
	@Autowired
	private WechatInfoUtil wechatInfoUtil;
	private TPubPlatformDto tPubPlatformDto = new TPubPlatformDto();

	public String execute() throws Exception {
		try {
			boolean result = true;
			// 判断是否存在相同的公众号
			if (checkTPubPlatformIdAction().equals("error")) {
				result = false;
				outJSOND(response, "{\"status\":" + result + ",\"mess\":\""
						+ "已存在相同的公众号ID或原始ID，请您重新输入：）" + "\"}");
				return null;
			}
			// 判断前台页面的未输入，如果选择未输入获得""。等于""返回前台选择公众号。
			if (tPubPlatformDto.getPlatform_type().equals("")) {
				result = false;
				outJSOND(response, "{\"status\":" + result + ",\"mess\":\""
						+ "请选择公众号类型!" + "\"}");
				return null;
			}

			String userCd = LoginUserInfoUtil.getLoginUserCD();
			tPubPlatformDto.setCreated_user_cd(userCd);// 保存新建用户
			tPubPlatformDto.setUpdated_user_cd(userCd);// 保存更新用户
			tPubPlatformService.save(tPubPlatformDto);

			// 新增公众号。将新增的公众号信息，放入缓存。
			wechatInfoUtil.InitConfig();

//			// 创建公众号菜单根节点。
//			tWcmSelfRelaService.createTWcmenu(tPubPlatformDto.getPlatform_id(),
//					tPubPlatformDto.getWechart_account());
			outJSOND(response, "{\"status\":" + result + ",\"mess\":\""
					+ "保存成功!" + "\"}");
		} catch (Exception e) {
			logger.error("------创建公众号异常------", e);
		}

		return ERROR;

	}

	// 查询公众号是否存在
	public String checkTPubPlatformIdAction() {
		boolean result = false;
		String message = "";
		try {
			// 查询公众号名称，验证是否重复添加。
			result = tPubPlatformService.queryTPubPlatformName(tPubPlatformDto
					.getWechart_account(), tPubPlatformDto.getOrg_id());
			if (!result)// 如果重复添加，返回失败。
			{
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
			message = "数据校验出错!";
		}
		return SUCCESS;
	}

	public final TPubPlatformDto getTPubPlatformDto() {
		return tPubPlatformDto;
	}

	public final void setStudentDto(TPubPlatformDto tPubPlatformDto) {
		this.tPubPlatformDto = tPubPlatformDto;
	}

}
