package com.wechat.menucfg.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;

import com.hercules.common.tool.json.JsonDateFormatUtil;

public class JsonDataUtil {
	/** 响应记录条数 */
	private static final String TOTAL = "total";
	/** 响应记录内容 */
	private static final String ROWS = "rows";
	/** 响应报文 */
	private static final String DATA_MESS = "mess";
	/** 响应结果 */
	private static final String DATA_STATUS = "status";
	/** 菜单名 */
	private static final String MENU_NAME = "menu_name";
	/** 权重 */
	private static final String RELA_SORT = "rela_sort";
	/** 父节点id */
	private static final String PARENT_ID = "parent_id";
	/** 子节点id */
	private static final String CHILDREN_ID = "children_id";
	/** id */
	private static final String ID = "id";
	/** is_leaf是否是叶子节点 */
	private static final String IS_LEAF = "is_leaf";
	/** 菜单动作类型 */
	private static final String ACTION_TYPE = "action_type";
	/** 动作关联素材id */
	private static final String MATERIAL_ID = "material_id";
	/** 动作url */
	private static final String ACTION_URL = "action_url";

	/**
	 * 返回处理结果
	 * 
	 * @param mess
	 *            消息描述
	 * @param flag
	 *            成功标识
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String parseJson(String mess, boolean flag) {
		Map data = new HashMap<String, String>(2);
		data.put(DATA_MESS, StringUtils.trimToEmpty(mess));
		data.put(DATA_STATUS, flag);
		return JSONObject.fromObject(data).toString();
	}

	/**
	 * 返回处理结果
	 * 
	 * @param total
	 *            记录条数
	 * @param rows
	 *            记录集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String parseJson(int total, List<Map> rows) {
		Map data = new HashMap<String, String>(2);
		data.put(TOTAL, String.valueOf(total));
		// JsonConfig 用于解析转换的设置
		JsonConfig config = new JsonConfig();
		JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
		JSONArray json_rows = JSONArray.fromObject(rows, config);
		data.put(ROWS, StringUtils.trimToEmpty(json_rows.toString()));
		// 组合输出列表查询所需数据
		return JSONObject.fromObject(data, config).toString();
	}

	/**
	 * 返回节点参数
	 * 
	 * @param id
	 *            节点id
	 * @param parent_id
	 *            父节点
	 * @param children_id
	 *            子节点
	 * @param menu_name
	 *            菜单名
	 * @param rela_sort
	 *            权重
	 * @param is_leaf
	 *            是否是叶子节点
	 * @param action_type
	 *            菜单动作类型
	 * @param material_id
	 *            动作关联素材id
	 * @param action_url
	 *            动作url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String parseJson(String id, String parent_id,
			String children_id, String menu_name, String rela_sort,
			boolean is_leaf, String action_type, String material_id,
			String action_url) {
		Map data = new HashMap<String, String>(9);
		data.put(MENU_NAME, StringUtils.trimToEmpty(menu_name));
		data.put(RELA_SORT, StringUtils.trimToEmpty(rela_sort));
		data.put(ID, StringUtils.trimToEmpty(id));
		data.put(PARENT_ID, StringUtils.trimToEmpty(parent_id));
		data.put(CHILDREN_ID, StringUtils.trimToEmpty(children_id));
		data.put(IS_LEAF, is_leaf);
		data.put(ACTION_TYPE, StringUtils.trimToEmpty(action_type));
		data.put(MATERIAL_ID, StringUtils.trimToEmpty(material_id));
		data.put(ACTION_URL, StringUtils.trimToEmpty(action_url));
		return JSONObject.fromObject(data).toString();
	}
}
