package com.pub.common.tools.codestring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pub.common.local.model.dto.TCodestringDto;
import com.pub.common.tools.constant.CodeStringConstant;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pub.persistence.dao.ITCodestringDao;

/**
 * @author jia.chen
 */
@Service("codeStringUtil")
public class CodeStringUtil {

	private static Logger logger = Logger.getLogger(CodeStringUtil.class);
	private static HashMap<String, List<TCodestringDto>> csListMap = new HashMap<String, List<TCodestringDto>>();

	@Autowired
	private ITCodestringDao tCodestringDao;

	/****
	 *spring中定义的初始化方法
	 *
	 * @throws Exception
	 */
	public void InitConfig() throws Exception {
		this.refreshCacheAll();
	}

	/**
	 *刷新全部缓存
	 *
	 * @throws Exception
	 */
	private void refreshCacheAll() throws Exception {
		logger
				.info("===========================start [codeString refresh]===========================");
		// 获取所有数据
		List<TCodestringDto> codeStringList = tCodestringDao
				.queryCodeStringAll(new TCodestringDto());
		// 数据启动时，加载缓存中。
		for (TCodestringDto cs : codeStringList) {
			String csType = cs.getCs_type();
			List<TCodestringDto> tempList = new ArrayList<TCodestringDto>();
			for (TCodestringDto temp : codeStringList) {
				if (temp.getCs_type().equals(csType)) {
					tempList.add(temp);
				}
			}
			csListMap.put(csType, tempList);
		}
		logger
				.info("===========================end [codeString refresh]===========================");
	}

	/**
	 *刷新csType局部缓存
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void refreshCacheByCsType(String csType) throws Exception {
		logger.info("codeString refresh[" + csType + "]start");
		TCodestringDto param = new TCodestringDto();
		param.setCs_type(csType);
		List<TCodestringDto> codeStringList = tCodestringDao
				.queryCodeStringAll(param);
		List<TCodestringDto> tmplist = new ArrayList<TCodestringDto>();
		for (TCodestringDto cs : codeStringList) {
			tmplist.add(cs);
		}
		csListMap.remove(csType);
		csListMap.put(csType, tmplist);
		logger.info("codeString refresh[" + csType + "]end");
	}

	/**
	 * 去除排除值 并获取CS列表
	 *
	 * @param csType
	 * @param excludeArray
	 * @return
	 */
	public static List<TCodestringDto> findCsListBycsTypeByExclude(
			String csType, String[] excludeArray) {
		List<TCodestringDto> list = new ArrayList<TCodestringDto>();

		// csType不能为空
		if (StringUtils.isBlank(csType)) {
			return list;
		}

		List<TCodestringDto> tempList = csListMap.get(csType);
		if (tempList != null) {
			for (TCodestringDto tCodestringDto : tempList) {
				if (excludeArray != null) {
					// 判断是否添加到list中
					boolean continueFlag = true;
					// 不需要在列表中显示的code
					for (String temp : excludeArray) {
						if (temp.length() == 12) {
							temp = temp.substring(4, 12);
						}
						if (tCodestringDto.getCs_code().substring(4, 12)
								.equals(temp)) {
							continueFlag = false;
							break;
						}
					}
					if (!continueFlag) {
						continue;
					}
				}
				list.add(tCodestringDto);
			}
		}

		return list;
	}

	/**
	 * 通过cstype 获取供页面控件显示的下拉框的值
	 *
	 * @author liyi.wang
	 * @param csType
	 */
	public static List<SelectCsBean> getSelectCsBeanByCsType(String csType) {

		return getSelectCsBeanByCsType(csType, null);
	}

	/**
	 * 通过cstype 获取供页面控件显示的下拉框的值
	 *
	 * @author liyi.wang
	 * @param csType
	 * @param selectedValue
	 *            需要下拉框选中的值
	 */
	public static List<SelectCsBean> getSelectCsBeanByCsType(String csType,
			String selectedValue) {

		return getSelectCsBeanByCsType(csType, selectedValue, null);
	}

	/**
	 * 通过cstype 获取供页面控件显示的下拉框的值
	 *
	 * @author liyi.wang
	 * @param csType
	 * @param selectedValue
	 *            需要下拉框选中的值
	 * @param removeValue
	 *            需要下拉列表屏蔽掉的option
	 */
	public static List<SelectCsBean> getSelectCsBeanByCsType(String csType,
			String selectedValue, String[] removeValue) {
		// logger.info("获取供页面控件显示的下拉框的值codeString ["+csType+"] ["+selectedValue+"]["+removeValue+"]start");

		// 设定排除列表
		String[] excludeArray = new String[] {
				CodeStringConstant.PLATFORM_ERROR,
				CodeStringConstant.PLATFORM_OTHER };
		excludeArray = (String[]) ArrayUtils.addAll(excludeArray, removeValue);

		// 查找DTO列表
		List<TCodestringDto> csDtos = findCsListBycsTypeByExclude(csType,
				excludeArray);

		// 转换成画面下拉框项，并设定选择项
		List<SelectCsBean> csSelects = new ArrayList<SelectCsBean>();
		if (csDtos != null) {
			for (TCodestringDto csDto : csDtos) {
				SelectCsBean csSelect = new SelectCsBean();
				if (csDto
						.getCs_code()
						.substring(4, 12)
						.equals(
								CodeStringConstant.PLATFORM_NOTINPUT)) {
					csSelect.setKey("");
				} else {
					csSelect.setKey(csDto.getCs_code());
				}

				csSelect.setValue(csDto.getCs_value());
				if (csDto.getCs_code().equals(selectedValue)) {
					csSelect.setSelected(true);
				} else {
					csSelect.setSelected(false);
				}

				csSelects.add(csSelect);
			}
		}

		// logger.info("获取供页面控件显示的下拉框的值codeString ["+csType+"] ["+selectedValue+"]["+removeValue+"]end");

		return csSelects;
	}

	/***
	 * 外部方法 通过 csCode 翻译 csValue 的方法
	 *
	 * @param csCode
	 * @return csValue
	 */
	public static String tranCsValueByCsCode(String csCode) {
		// logger.info("翻译csValue的方法codeString ["+csCode+"] start");

		// 判断csCode是否为null
		if (StringUtils.isBlank(csCode)) {
			return csCode;
		}

		// 根据csListMap的csCode，返回
		List<TCodestringDto> codestrings = csListMap
				.get(csCode.substring(0, 4));
		// 取出csCode对应的记录，循环遍历取出csValue
		if (codestrings != null) {
			for (TCodestringDto codestring : codestrings) {
				if (codestring.getCs_code().equals(csCode)) {
					return codestring.getCs_value();
				}
			}
		}

		// logger.info("翻译csValue的方法codeString ["+csCode+"] end");

		return csCode;
	}

	/**
	 * 一览查询中有CHECKBOX的情况下,取得初期化的SQL条件
	 *
	 * @param selectCsBeanList
	 * @return
	 */
	public static String getInitialCheckedQueryString(
			List<SelectCsBean> selectCsBeanList) {
		if (selectCsBeanList == null || selectCsBeanList.size() == 0) {
			return "";
		}
		String retValue = "";
		for (SelectCsBean bean : selectCsBeanList) {
			retValue = retValue + "," + "'" + bean.getKey() + "'";
		}
		return retValue.substring(1);
	}
}
