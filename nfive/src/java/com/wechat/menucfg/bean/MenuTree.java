package com.wechat.menucfg.bean;

import java.util.List;

public class MenuTree {
	/** 节点id */
	private String id;
	/** 节点显示文本 */
	private String text;
	/** 父节点id */
	private String parent_id;
	/** 子节点id */
	private String children_id;
	/** 权重 */
	private String rela_sort;
	/** 是否是叶子节点 */
	private boolean is_leaf;
	/** 菜单动作类型 */
	private String action_type;
	/** 动作关联素材id */
	private String material_id;
	/** 动作url */
	private String action_url;
	/** 子节点集合 */
	private List<MenuTree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public String getChildren_id() {
		return children_id;
	}

	public void setChildren_id(String childrenId) {
		children_id = childrenId;
	}

	public boolean isIs_leaf() {
		return is_leaf;
	}

	public void setIs_leaf(boolean isLeaf) {
		is_leaf = isLeaf;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public String getRela_sort() {
		return rela_sort;
	}

	public void setRela_sort(String relaSort) {
		rela_sort = relaSort;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String actionType) {
		action_type = actionType;
	}

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String materialId) {
		material_id = materialId;
	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String actionUrl) {
		action_url = actionUrl;
	}
}
