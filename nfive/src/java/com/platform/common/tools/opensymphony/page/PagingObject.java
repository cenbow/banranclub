package com.platform.common.tools.opensymphony.page;

/***
 * 
 * 
 * @author Administrator
 * 
 */
public class PagingObject {
	
	//排序字段
	private String sort_name="";
	//排序命ASC or DESC 
	private String sort_order="";
    //当前页
	private int    current_page = 1;
    //每页显示数量
    private int    page_size  = 10;    
    //总页数
    private int    total_page = 0;
	//最大记录总数
    private int    total_record = 0;
	//最后一条记录
	private int    last_page_record=0;
	//当前记录
	private int    current_page_record= 0;
	
	//构造page
	public PagingObject(){
		
	}
	
	//用于计算的方法
	public void calculatePage(int total_record)
	{
		//赋予初始值
		this.total_record = total_record;
		
		//1. set  total  page 
		total_page = ( total_record - 1 ) / page_size + 1;
		
		//2. set  last   page  records
		if (total_page == 1) {
			last_page_record = total_record;
		} else {
			last_page_record = total_record - page_size * (total_page - 1);
		}	
		
		//3.check current page
		if (current_page > total_page) {
			current_page = total_page; 
		}
		if (current_page <= 0) {
			current_page = 1;
		} 
		
		//4. set current record
		if (current_page == 1 || current_page_record == last_page_record) {
			current_page_record = last_page_record;
		} else {
			current_page_record = page_size;
		}
		
	}
	
	
	
	
	public final String getSort_name() {
		return sort_name;
	}

	public final void setSort_name(String sortName) {
		sort_name = sortName;
	}

	public final String getSort_order() {
		return sort_order;
	}

	public final void setSort_order(String sortOrder) {
		sort_order = sortOrder;
	}

	public final int getCurrent_page() {
		return current_page;
	}

	public final void setCurrent_page(int currentPage) {
		current_page = currentPage;
	}

	public final int getPage_size() {
		return page_size;
	}

	public final void setPage_size(int pageSize) {
		page_size = pageSize;
	}

	public final int getTotal_page() {
		return total_page;
	}

	public final void setTotal_page(int totalPage) {
		total_page = totalPage;
	}

	public final int getTotal_record() {
		return total_record;
	}

	public final void setTotal_record(int totalRecord) {
		total_record = totalRecord;
	}

	public final int getLast_page_record() {
		return last_page_record;
	}

	public final void setLast_page_record(int lastPageRecord) {
		last_page_record = lastPageRecord;
	}

	public final int getCurrent_page_record() {
		return current_page_record;
	}

	public final void setCurrent_page_record(int currentPageRecord) {
		current_page_record = currentPageRecord;
	}

	/**
	 * 获得当前页是从第几条记录开始
	 */
	public int getCurrentStartRecordCount() {
		return page_size * (current_page - 1);
	}
	
	
}
