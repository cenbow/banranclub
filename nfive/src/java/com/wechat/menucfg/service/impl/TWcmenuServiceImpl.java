package com.wechat.menucfg.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.platform.common.tools.wechat.WechatUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.menucfg.dao.ITWcmSelfRelaDao;
import com.wechat.menucfg.dao.ITWcmenuDao;
import com.wechat.menucfg.model.TWcmenuQueryBean;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmenuService;

/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
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

@Scope("prototype")
@Service("tWcmenuService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TWcmenuServiceImpl extends GenericServiceImpl<TWcmenuDto, String>
		implements ITWcmenuService {
	@Autowired
	private ITWcmenuDao tWcmenuDao;
	@Autowired
	private ITWcmSelfRelaDao tWcmSelfRelaDao;

	public BaseDao<TWcmenuDto, String> getBaseDao() {
		return tWcmenuDao;
	}

	/****
	 * @param tWcmenuQueryBean
	 * @param pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	public PageResult queryTWcmenuListPage(TWcmenuQueryBean tWcmenuQueryBean,
			PagingObject pagingObject) throws Exception {

		return this.tWcmenuDao.queryTWcmenuListPage(tWcmenuQueryBean,
				pagingObject);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public void addWcmSelfRela(TWcmenuQueryBean tWcmenuQueryBean)
			throws Exception {
		// 菜单表
		TWcmenuDto twd = new TWcmenuDto();
		twd.setAction_type(tWcmenuQueryBean.getAction_type());
		twd.setMenu_name(tWcmenuQueryBean.getMenu_name());
		twd.setMenu_desc(tWcmenuQueryBean.getMenu_desc());
		twd.setRela_sort(tWcmenuQueryBean.getRela_sort());
		//设置是否动态模板    页面是否动态模板的输入值  1.CodeStringConstant.CS_1000_FALSE==未选中  2.CodeStringConstant.CS_1000_TRUE==选中
		if (CodeStringConstant.CS_1000_TRUE.equalsIgnoreCase(tWcmenuQueryBean.getTemplet_flag())){
			twd.setTemplet_flag(CodeStringConstant.CS_1000_TRUE);
		}else {
			twd.setTemplet_flag(CodeStringConstant.CS_1000_FALSE);
		}
		//设置启用客服模式
		twd.setCust_srv_flag(tWcmenuQueryBean.getCust_srv_flag());
		twd.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		twd.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		twd.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());
		// 菜单类型
		String actionType = StringUtils.trimToEmpty(tWcmenuQueryBean
				.getAction_type());
		if (actionType.equals(CodeStringConstant.MENU_TYPE_NOTINPUT)) {
			twd.setAction_type(CodeStringConstant.MENU_TYPE_NOTINPUT);
		} else if (actionType.equals(CodeStringConstant.MENU_TYPE_TEXT)) {
			twd.setText_msg(tWcmenuQueryBean.getText_msg());
		} else if (actionType.equals(CodeStringConstant.MENU_TYPE_HREF)) {
			twd.setAction_url(tWcmenuQueryBean.getAction_url());

		}
        //自定义消息接口类
        else if (actionType.equals(CodeStringConstant.MENU_TYPE_ACTION)){
            twd.setAction_class(tWcmenuQueryBean.getAction_class());
        }
        //图片，音频，视频
        else if (actionType.equals(CodeStringConstant.MENU_TYPE_IMAGE)
				|| actionType.equals(CodeStringConstant.MENU_TYPE_AUDIO)
				|| actionType.equals(CodeStringConstant.MENU_TYPE_VIDEO)) {
			twd.setMaterial_id(tWcmenuQueryBean.getMaterial_id());
			twd.setMenu_code(tWcmenuQueryBean.getMaterial_id());
			//图文组
		} else if (actionType.equals(CodeStringConstant.MENU_TYPE_IMAGE_TEXT)) {
			twd.setMaterial_id(tWcmenuQueryBean.getMaterial_id());
			twd.setMenu_code(tWcmenuQueryBean.getMaterial_group_id());
		}
		tWcmenuDao.save(twd);

		// 子节点需要输出到页面
		tWcmenuQueryBean.setMenu_id(twd.getMenu_id());
		// 菜单关系表
		TWcmSelfRelaDto twsrd = new TWcmSelfRelaDto();
		twsrd.setParent_id(tWcmenuQueryBean.getParent_id());
		twsrd.setChild_id(twd.getMenu_id());
		twsrd.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());
		twsrd.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		twsrd.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		tWcmSelfRelaDao.save(twsrd);
		// 节点id需要输出到页面
		tWcmenuQueryBean.setId(twsrd.getMenu_relation_id());
	}

	public TWcmenuQueryBean getTWcmenuQueryBean(TWcmenuDto paramTWcmenuDto)
			throws Exception {
		TWcmenuQueryBean temp = new TWcmenuQueryBean();
		TWcmenuDto tWcmenuDto = tWcmenuDao.getRow(paramTWcmenuDto);
		BeanUtils.copyProperties(tWcmenuDto, temp);
		return temp;
	}

	public int selectTWcmenuListCount(TWcmenuDto paramTWcmenuDto)
			throws Exception {
		return tWcmenuDao.selectTWcmenuListCount(paramTWcmenuDto);
	}
}
