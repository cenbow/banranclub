package com.wechat.menucfg.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.wechat.menucfg.bean.MenuTree;

public class MenuTreeUtil {
	// 主节点的父节点id
	public static final String MAIN_PARENT_ID = "ROOT";

	/***
	 * 将对象集合转换成二叉树集合，同时找出父节点
	 * 
	 * @param list
	 * @param rootTree
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MenuTree> convertObjectListToTreeList(List list,
			MenuTree rootTree) {
		List<MenuTree> treeList = new ArrayList<MenuTree>();
		for (int i = 0; list != null && i < list.size(); i++) {
			Map map = (Map) list.get(i);
			MenuTree mt = new MenuTree();
			mt.setId(map.get("MENU_RELATION_ID").toString());
			mt.setParent_id(map.get("PARENT_ID").toString());
			mt.setChildren_id(map.get("CHILD_ID").toString());
			mt.setText(map.get("MENU_NAME").toString());
			mt.setRela_sort(map.get("RELA_SORT").toString());
			mt.setAction_type(map.get("ACTION_TYPE") != null ? map.get("ACTION_TYPE").toString() : null);
			mt.setMaterial_id(map.get("MATERIAL_ID") != null ? map.get("MATERIAL_ID").toString() : null);
			mt.setAction_url(map.get("ACTION_URL") != null ? map.get("ACTION_URL").toString() : null);
			mt.setIs_leaf(Boolean.FALSE);
			if (mt.getParent_id().equals(MAIN_PARENT_ID)) {
				BeanUtils.copyProperties(mt, rootTree);
			}
			treeList.add(mt);
		}
		return treeList;
	}

	/**
	 * 以根节点为基点，向下组装树结构
	 * 
	 * @return
	 */
	public static void assemblyTree(MenuTree rootTree, List<MenuTree> treeList) {
		// 获取子节点
		List<MenuTree> children = new ArrayList<MenuTree>();
		for (int i = 0; i < treeList.size(); i++) {
			MenuTree tree = treeList.get(i);
			if (tree.getParent_id().equals(rootTree.getChildren_id())) {
				children.add(tree);
			}
		}
		if (children.size() > 0) {
			rootTree.setChildren(children);
		} else {
			rootTree.setIs_leaf(Boolean.TRUE);
		}
		// 递归获取节点
		for (int i = 0; i < children.size(); i++) {
			MenuTree childTree = children.get(i);
			assemblyTree(childTree, treeList);
		}
	}

}
