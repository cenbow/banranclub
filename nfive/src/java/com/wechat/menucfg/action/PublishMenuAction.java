package com.wechat.menucfg.action;

import java.util.List;

import com.platform.common.tools.wechat.*;
import com.wechat.core.beans.menu.*;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.service.ITWxNewsService;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.constant.WeChatConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.menucfg.bean.MenuTree;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmSelfRelaService;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.menucfg.util.JsonDataUtil;

/**
 * 类功能:自动代码生成模板查询 action 模板
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

@Controller("publishMenuAction")
@Scope("prototype")
public class PublishMenuAction extends BaseAction {
	private static final Logger logger = Logger
			.getLogger(PublishMenuAction.class);

	private static final long serialVersionUID = -2230294236409386842L;

	@Autowired
	private ITWcmSelfRelaService tWcmSelfRelaService;

	@Autowired
	private ITWcmenuService tWcmenuService;

    @Autowired
    private ITWxNewsService wxNewsService;

	public void publish() throws Exception {
		TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean = null;
		try {
			tWcmSelfRelaQueryBean = new TWcmSelfRelaQueryBean();
			tWcmSelfRelaQueryBean.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());
		} catch (Exception e) {
			logger.error(WechatUtil.getWxPlatform().getPlatform_name()
					+ "------获取公众号异常------\r\n", e);
			outJSOND(getResponse(), JsonDataUtil.parseJson("获取公众号异常",
					Boolean.FALSE));
			return;
		}

		// 获取树
		MenuTree tree = null;
		try {
			tree = tWcmSelfRelaService
					.queryTWcmSelfRelaDtoList(tWcmSelfRelaQueryBean);
		} catch (Exception ex) {
			logger.error(WechatUtil.getWxPlatform().getPlatform_name()
					+ "------发布菜单异常------\r\n", ex);
			outJSOND(getResponse(), JsonDataUtil.parseJson("数据库异常",
					Boolean.FALSE));
			return;
		}
		// 获取菜单
		Menu menu = null;
		try {
			menu = assembleMenu(tree);
		} catch (Exception ex) {
			logger.error(WechatUtil.getWxPlatform().getPlatform_name()
					+ "------发布菜单异常------\r\n", ex);
			outJSOND(getResponse(), JsonDataUtil.parseJson(ex.getMessage(),
					Boolean.FALSE));
			return;
		}

		logger.info("------公众号:" + WechatUtil.getWxPlatform().getPlatform_name()
				+ "发布菜单------\r\n" + JSONArray.fromObject(menu));

		try {
			// 停用菜单动作可以在这里添加
			boolean deleteflag = WechatApiUtil.deleteMenu(WechatUtil.getWxPlatform().getAccess_token());

			if (deleteflag) {
				// 发布菜单
				boolean createflag = WechatApiUtil.createMenu(menu, WechatUtil.getWxPlatform().getAccess_token());
				if (createflag) {
					outJSOND(getResponse(), JsonDataUtil.parseJson("发布菜单成功!",
							Boolean.TRUE));
				} else {
					outJSOND(getResponse(), JsonDataUtil.parseJson("发布菜单失败!",
							Boolean.FALSE));
				}
			} else {
				outJSOND(getResponse(), JsonDataUtil.parseJson("发布菜单失败!",
						Boolean.FALSE));
			}
		} catch (Exception ex) {
			logger.error(WechatUtil.getWxPlatform().getPlatform_name()
					+ "------发布菜单异常------\r\n", ex);
			outJSOND(getResponse(), JsonDataUtil.parseJson("微信服务器异常!",
					Boolean.FALSE));
			return;
		}
	}

	/***
	 * 根据菜单树拼装菜单
	 *
	 * @param tree
	 * @return
	 * @throws Exception
	 */
	private Menu assembleMenu(MenuTree tree) throws Exception {
		List<MenuTree> list = tree.getChildren();
		Menu menu = new Menu();
		// 创建一级菜单
		Button[] firstButton = new Button[list.size()];
		menu.setButton(firstButton);
		// 封装报文
		for (int i = 0; i < list.size(); i++) {
			MenuTree firstTemp = list.get(i);
			// 如果是叶子节点
			if (null == firstTemp.getChildren()
					|| 0 == firstTemp.getChildren().size()) {
				// 获取菜单项
				TWcmenuDto dto = new TWcmenuDto();
				dto.setMenu_id(firstTemp.getChildren_id());
				dto = tWcmenuService.getRow(dto);
				String actionType = dto.getAction_type();
				// 链接页面
				if (CodeStringConstant.MENU_TYPE_HREF.equals(actionType)) {
					ViewButton v = new ViewButton();
					v.setName(firstTemp.getText());
					v.setType(WeChatConstant.EVENT_TYPE_VIEW);
					v.setUrl(dto.getAction_url());
					firstButton[i] = v;
					// 文本框、音頻，視頻
				} else if (CodeStringConstant.MENU_TYPE_TEXT.equals(actionType)
						|| CodeStringConstant.MENU_TYPE_AUDIO
								.equals(actionType)
						|| CodeStringConstant.MENU_TYPE_VIDEO
								.equals(actionType)
                        || CodeStringConstant.MENU_TYPE_ACTION
                        .equals(actionType)) {
					ClickButton cb = new ClickButton();
					cb.setName(firstTemp.getText());
					cb.setType(WeChatConstant.EVENT_TYPE_CLICK);
					cb.setKey(firstTemp.getChildren_id());
					firstButton[i] = cb;
				}else if(CodeStringConstant.MENU_TYPE_IMAGE.equals(actionType)){
                    MediaButton mb = new MediaButton();
                    mb.setName(firstTemp.getText());
                    mb.setType(WeChatConstant.EVENT_TYPE_MEDIA_id);
                    mb.setMedia_id(""); //todo
                }else if(CodeStringConstant.MENU_TYPE_IMAGE_TEXT.equals(actionType)){
                    MediaButton mb = new MediaButton();
                    mb.setName(firstTemp.getText());
                    mb.setType(WeChatConstant.EVENT_TYPE_MEDIA_id);
                    TWxNewsDto wxNewsDto = new TWxNewsDto();
                    wxNewsDto.setNews_id(firstTemp.getMaterial_id());
                    wxNewsDto = wxNewsService.getRow(wxNewsDto);
                    mb.setMedia_id(wxNewsDto.getMedia_id());
                    firstButton[i] = mb;
                }
                else {
					throw new Exception("[" + dto.getMenu_name() + "]菜单类型有误!");
				}
				// 如果不是叶子节点
			} else {
				ComplexButton cb = new ComplexButton();
				cb.setName(firstTemp.getText());
				List<MenuTree> sunList = firstTemp.getChildren();
				// 创建二级菜单
				Button[] secondeButton = new Button[sunList.size()];
				cb.setSub_button(secondeButton);
				firstButton[i] = cb;
				// 遍历二级节点
				for (int j = 0; j < sunList.size(); j++) {
					// 微信二级菜单的显示逻辑和我们页面是相反的，故此处修改成这样
					MenuTree secondTemp = sunList.get(sunList.size() - 1 - j);
					// 获取菜单项
					TWcmenuDto dto = new TWcmenuDto();
					dto.setMenu_id(secondTemp.getChildren_id());
					dto = tWcmenuService.getRow(dto);
					String actionType = dto.getAction_type();
					// 链接页面
					if (CodeStringConstant.MENU_TYPE_HREF.equals(actionType)) {
						ViewButton v = new ViewButton();
						v.setName(secondTemp.getText());
						v.setType(WeChatConstant.EVENT_TYPE_VIEW);
						v.setUrl(dto.getAction_url());
						secondeButton[j] = v;
						// 文本框、图片、图文、音頻，視頻
					} else if (CodeStringConstant.MENU_TYPE_TEXT.equals(actionType)
							|| CodeStringConstant.MENU_TYPE_AUDIO
									.equals(actionType)
							|| CodeStringConstant.MENU_TYPE_VIDEO
									.equals(actionType)
                            || CodeStringConstant.MENU_TYPE_ACTION
                            .equals(actionType)) {
						ClickButton cb1 = new ClickButton();
						cb1.setName(secondTemp.getText());
						cb1.setType(WeChatConstant.EVENT_TYPE_CLICK);
						cb1.setKey(secondTemp.getChildren_id());
						secondeButton[j] = cb1;
					}else if(CodeStringConstant.MENU_TYPE_IMAGE.equals(actionType)){
                        MediaButton mb = new MediaButton();
                        mb.setName(secondTemp.getText());
                        mb.setType(WeChatConstant.EVENT_TYPE_MEDIA_id);
                        mb.setMedia_id(""); //todo
                    }else if(CodeStringConstant.MENU_TYPE_IMAGE_TEXT.equals(actionType)){
                        MediaButton mb = new MediaButton();
                        mb.setName(secondTemp.getText());
                        mb.setType(WeChatConstant.EVENT_TYPE_MEDIA_id);
                        TWxNewsDto wxNewsDto = new TWxNewsDto();
                        wxNewsDto.setNews_id(secondTemp.getMaterial_id());
                        wxNewsDto = wxNewsService.getRow(wxNewsDto);
                        mb.setMedia_id(wxNewsDto.getMedia_id());
                        secondeButton[j] = mb;
                    }
                    else {
						throw new Exception("[" + secondTemp.getText()
								+ "]菜单类型有误!");
					}
				}
			}
		}
		return menu;
	}
}
