package com.platform.common.tools.opensymphony.page;
import java.util.List;
public class PageResult {
	
	//结果处理对象
	List resultList;
	
	//分页处理对象
	PagingObject pagingObject;

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public PagingObject getPagingObject() {
		return pagingObject;
	}

	public void setPagingObject(PagingObject pagingObject) {
		this.pagingObject = pagingObject;
	}
	
}