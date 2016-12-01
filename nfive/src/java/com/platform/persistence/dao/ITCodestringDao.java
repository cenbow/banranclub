package com.platform.persistence.dao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.model.TCodestringQueryBean;
import com.platform.persistence.model.dto.TCodestringDto;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:自动代码生成模板 IDao 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITCodestringDao extends BaseDao<TCodestringDto,String>{	

	/****
	 * 
	 * @param tCodestringQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTCodestringListPage(TCodestringQueryBean tCodestringQueryBean,PagingObject pagingObject) throws Exception;
}
