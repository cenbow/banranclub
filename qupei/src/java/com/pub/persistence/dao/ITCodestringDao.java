package com.pub.persistence.dao;
import java.util.List;

import com.hercules.database.dao.BaseDao;
import com.pub.common.local.model.dto.TCodestringDto;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.model.TCodestringQueryBean;

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

	/**
	 * 查询所有的CodeString
	 */
	public List<TCodestringDto> queryCodeStringAll(TCodestringDto codestringDto) throws Exception;

}
