package com.pub.persistence.service;
import java.util.Map;

import com.hercules.database.service.IGenericService;
import com.pub.common.local.model.dto.TCodestringDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TCodestringQueryBean;


/**
 * 类功能:自动代码生成模板   IService 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITCodestringService extends IGenericService<TCodestringDto, String>{

	/****
	 *
	 * @param tCodestringQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTCodestringListPage(TCodestringQueryBean tCodestringQueryBean,PagingObject pagingObject) throws Exception;

	 /**
	  * 以map的形式返回codeString  key为编码   name为取值
	  * @param tCodestringDto
	  * @return
	  */
	 public Map<String,String> getCodeStringMap(TCodestringDto tCodestringDto);
}
