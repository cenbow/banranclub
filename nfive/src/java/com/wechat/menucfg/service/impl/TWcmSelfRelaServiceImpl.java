package com.wechat.menucfg.service.impl;

import java.util.List;

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
import com.wechat.menucfg.bean.MenuTree;
import com.wechat.menucfg.dao.ITWcmSelfRelaDao;
import com.wechat.menucfg.dao.ITWcmenuDao;
import com.wechat.menucfg.model.TWcmSelfRelaQueryBean;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmSelfRelaService;
import com.wechat.menucfg.util.MenuTreeUtil;

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
@Service("tWcmSelfRelaService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TWcmSelfRelaServiceImpl extends
		GenericServiceImpl<TWcmSelfRelaDto, String> implements
		ITWcmSelfRelaService {
	@Autowired
	private ITWcmSelfRelaDao tWcmSelfRelaDao;
	@Autowired
	private ITWcmenuDao tWcmenuDao;

	public BaseDao<TWcmSelfRelaDto, String> getBaseDao() {
		return tWcmSelfRelaDao;
	}

	/****
	 * @param tWcmSelfRelaQueryBean
	 * @param pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	public PageResult queryTWcmSelfRelaListPage(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean,
			PagingObject pagingObject) throws Exception {

		return this.tWcmSelfRelaDao.queryTWcmSelfRelaListPage(
				tWcmSelfRelaQueryBean, pagingObject);
	}

	@SuppressWarnings("unchecked")
	public MenuTree queryTWcmSelfRelaDtoList(
			TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean) throws Exception {
		// 对象集合
		List list = tWcmSelfRelaDao
				.queryTWcmSelfRelaDtoList(tWcmSelfRelaQueryBean);
		// 获取树父节点
		MenuTree rootTree = new MenuTree();
		// 将对象几何转换成二叉树集合
		List<MenuTree> treeList = MenuTreeUtil.convertObjectListToTreeList(
				list, rootTree);
		// 组装树结构
		MenuTreeUtil.assemblyTree(rootTree, treeList);
		return rootTree;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public void deleteTWcmSelfRela(TWcmSelfRelaQueryBean tWcmSelfRelaQueryBean)
			throws Exception {
		TWcmenuDto arg0 = new TWcmenuDto();
		arg0.setMenu_id(tWcmSelfRelaQueryBean.getChild_id());
		tWcmenuDao.deletePK(arg0);
		TWcmSelfRelaDto arg1 = new TWcmSelfRelaDto();
		arg1.setMenu_relation_id(tWcmSelfRelaQueryBean.getMenu_relation_id());
		tWcmSelfRelaDao.deletePK(arg1);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public boolean createTWcmenu(String platformId, String wechartAccount)
			throws Exception {
		TWcmenuDto tWcmenuDto = new TWcmenuDto();
		tWcmenuDto.setPlatform_id(platformId);
		tWcmenuDto.setMenu_name(wechartAccount);
		tWcmenuDto.setAction_type(CodeStringConstant.MENU_TYPE_NOTINPUT);
		tWcmenuDto.setMenu_desc("根节点");
		tWcmenuDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		tWcmenuDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		tWcmenuDto.setRela_sort(0l);
		tWcmenuDao.save(tWcmenuDto);
		
		TWcmSelfRelaDto tWcmSelfRelaDto = new TWcmSelfRelaDto();
		//此处写死0,在MenuTreeUtil中构造菜单树的时候，是根据该参数来的
		tWcmSelfRelaDto.setParent_id(MenuTreeUtil.MAIN_PARENT_ID);
		tWcmSelfRelaDto.setChild_id(tWcmenuDto.getMenu_id());
		tWcmSelfRelaDto.setPlatform_id(platformId);
		tWcmSelfRelaDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		tWcmSelfRelaDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
		tWcmSelfRelaDao.save(tWcmSelfRelaDto);
		return true;
	}
}
