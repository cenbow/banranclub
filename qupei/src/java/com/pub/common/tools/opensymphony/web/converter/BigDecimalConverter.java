package com.pub.common.tools.opensymphony.web.converter;

/**   
 * @Project: spiritframework
 * @Description: <TODO>
 * @File <BigDecimalConverter.java>
 * @author yekai $<a href="mailto:yekairush@163.com">联系作者</a>$ 
 * @from <出自其他项目或者作者> 
 * @version 2014-8-7 上午9:38:56
 * @Modification
 */

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class BigDecimalConverter extends StrutsTypeConverter {
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		BigDecimal bd = null;
		if (BigDecimal.class == toClass) {
			String bdStr = values[0];
			if (bdStr != null && !"".equals(bdStr)) {
				bd = new BigDecimal(bdStr);
			} else {
				// bd = BigDecimal.valueOf(-1);
			}
			return bd;
		}
		return BigDecimal.ZERO;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof BigDecimal) {
			BigDecimal b = new BigDecimal(o.toString()).setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
			return b.toString();
		}
		return o.toString();
	}

}
