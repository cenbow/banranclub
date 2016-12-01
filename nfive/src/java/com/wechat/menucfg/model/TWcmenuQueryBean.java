package com.wechat.menucfg.model;

import com.wechat.menucfg.model.dto.TWcmenuDto;

/**
 * 类功能:自动代码生成模板 QueryBean 模板
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
public class TWcmenuQueryBean extends TWcmenuDto {

	private static final long serialVersionUID = 3121630496483236548L;

	private java.lang.String orderStr;// 排序字串

	private String parent_id;// 父节点

	private String material_group_id;// 图文组id

	private String id;// 节点关系id

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaterial_group_id() {
		return material_group_id;
	}

	public void setMaterial_group_id(String materialGroupId) {
		material_group_id = materialGroupId;
	}

}