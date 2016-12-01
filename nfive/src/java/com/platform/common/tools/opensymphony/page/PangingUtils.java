package com.platform.common.tools.opensymphony.page;

import org.springframework.util.StringUtils;

import com.platform.common.tools.opensymphony.web.context.UserContext;

public class PangingUtils {
  
	public static PagingObject getPagingObjectFormRequest()
	{
		PagingObject pagingObject = new PagingObject();
		String  page = UserContext.getRequest().getParameter("page");
		String  rows = UserContext.getRequest().getParameter("rows");
		String  sort = UserContext.getRequest().getParameter("sort");
		String  order  = UserContext.getRequest().getParameter("order");
		
		pagingObject.setCurrent_page(StringUtils.isEmpty(page)?1:Integer.parseInt(page));
		pagingObject.setPage_size(StringUtils.isEmpty(rows)?10:Integer.parseInt(rows));
		pagingObject.setSort_name(sort);
		pagingObject.setSort_order(StringUtils.isEmpty(order)?"asc":order);
		return pagingObject;
	}
}
