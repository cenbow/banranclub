package com.platform.common.tools.checkmaterial;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hercules.factory.SpringContextUtil;
import com.wechat.menucfg.model.dto.TWcmenuDto;
import com.wechat.menucfg.service.ITWcmenuService;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.TReplySpecialQueryBean;
import com.wechat.replycfg.service.ITReplyKeywordService;
import com.wechat.replycfg.service.ITReplySpecialService;

/**
 * 类功能:根据资源素材ID检查该素材是否被引用
 * 
 * <p>
 * 创建者: yan.guo
 * </p>
 * <p>
 * 创建时间:2014-9-1
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
public class CheckMaterial {

	protected static final Log log = LogFactory.getLog(CheckMaterial.class);

	/***
	 * 检查素材是否被引用
	 * 
	 * @param material_id
	 *            素材ID
	 * @return
	 */
	public static String checkMaterialExist(String material_id) {

		// 返回内容
		String result_msg = null;

		// 判断关键字回复类型是否为空,防止传入空ID
		if (material_id == null || "".equalsIgnoreCase(material_id)) {
			result_msg = "未传入相应的素材ID！";
			return result_msg;
		}

		// 关键字回复规则
		ITReplyKeywordService tReplyKeywordService = SpringContextUtil
				.getApplicationContext().getBean(ITReplyKeywordService.class);
		// 设置参数
		TReplyKeywordQueryBean param_tReplyKeywordQueryBean = new TReplyKeywordQueryBean();
		param_tReplyKeywordQueryBean.setMaterial_id(material_id);// 素材ID
		try {
			Integer result_count = tReplyKeywordService
					.countTReplyKeywordList(param_tReplyKeywordQueryBean);
			if (null != result_count && result_count > 0) {
				result_msg = "关键字回复规则中有" + result_count + "条引用数据,请先删除引用数据！";
				return result_msg;
			}
		} catch (Exception e) {
			result_msg = "关键字回复规则校验出现异常！";
		}

		// 防止异常继续执行以下操作
		if (null != result_msg && !"".equalsIgnoreCase(result_msg)) {
			return result_msg;
		}

		// 特殊关键字
		ITReplySpecialService tReplySpecialService = SpringContextUtil
				.getApplicationContext().getBean(ITReplySpecialService.class);
		TReplySpecialQueryBean tReplySpecialQueryBean = new TReplySpecialQueryBean();

		tReplySpecialQueryBean.setMaterial_id(material_id);// 素材ID
		Integer result_tReplySpecialDto_count;
		try {
			result_tReplySpecialDto_count = tReplySpecialService
					.checkRelated(tReplySpecialQueryBean);
			if (null != result_tReplySpecialDto_count
					&& result_tReplySpecialDto_count > 0) {
				result_msg = "特殊回复规则中有" + result_tReplySpecialDto_count
						+ "条引用数据,请先删除引用数据！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result_msg = "特殊回复规则校验出现异常！";
		}

		// 防止异常继续执行以下操作
		if (null != result_msg && !"".equalsIgnoreCase(result_msg)) {
			return result_msg;
		}

		// 菜单
		ITWcmenuService tWcmenuService = SpringContextUtil
				.getApplicationContext().getBean(ITWcmenuService.class);
		TWcmenuDto tWcmenuDto = new TWcmenuDto();
		tWcmenuDto.setMaterial_id(StringUtils.trimToNull(material_id));// 素材ID
		Integer count = 0;
		try {
			count = tWcmenuService.selectTWcmenuListCount(tWcmenuDto);
		} catch (Exception e) {
			log.error("查询菜单校验异常", e);
			result_msg = "查询菜单校验异常";
			return result_msg;
		}
		if (count > 0) {
			result_msg = "菜单占用该项,请先停用菜单！";
		}

		return result_msg;
	}
}
