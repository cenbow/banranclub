package com.platform.persistence.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.dao.ITCodestringDao;
import com.platform.persistence.model.TCodestringQueryBean;
import com.platform.persistence.model.dto.TCodestringDto;
import com.platform.persistence.service.ITCodestringService;


/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tCodestringService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TCodestringServiceImpl extends GenericServiceImpl<TCodestringDto, String> implements ITCodestringService{
    @Autowired
    private ITCodestringDao   tCodestringDao;

	public BaseDao<TCodestringDto, String> getBaseDao() {
		return tCodestringDao;
	}
	

	/****
	 * @param  tCodestringQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTCodestringListPage(TCodestringQueryBean tCodestringQueryBean,PagingObject pagingObject) throws Exception
	 {	
		 	return this.tCodestringDao.queryTCodestringListPage(tCodestringQueryBean, pagingObject);
	 }
	 
	 /**
	  * 以map的形式返回codeString  key为编码   name为取值
	  * @param tCodestringDto
	  * @return
	  */
	 public Map<String,String> getCodeStringMap(TCodestringDto tCodestringDto)
	 {
		  List<TCodestringDto> codeStringList = super.getAll(tCodestringDto);
		  Map<String,String> tCodeStringMap = new HashMap<String,String>();
		  if(codeStringList == null) return tCodeStringMap;
		  for(TCodestringDto code : codeStringList)
		  {
			  tCodeStringMap.put(code.getCs_code(), code.getCs_value());
		  }
		  return tCodeStringMap;
	 }
	 
	 
	
}
