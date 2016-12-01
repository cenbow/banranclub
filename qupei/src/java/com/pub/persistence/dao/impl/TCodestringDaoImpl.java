package com.pub.persistence.dao.impl;
import java.util.List;

import com.pub.common.local.model.dto.TCodestringDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.dao.ITCodestringDao;
import com.pub.persistence.model.TCodestringQueryBean;

/**
 * 类功能:自动代码生成模板 DaoImpl 模板
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
@Service("tCodestringDao")
public class TCodestringDaoImpl extends BaseDaoImpl<TCodestringDto, String> implements ITCodestringDao{
	public TCodestringDaoImpl() {
		super(TCodestringDto.class);
	}


	/****
	 *
	 * @param tCodestringQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTCodestringListPage(TCodestringQueryBean tCodestringQueryBean,PagingObject pagingObject) throws Exception
	 {
		tCodestringQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
		 PageResult prs = new PageResult();

		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tCodestring.select_tCodestringList_count",tCodestringQueryBean);

		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());


		 //查询列表
		 List tCodestringList = this.getSqlMapClientTemplate().queryForList("tCodestring.select_tCodestringList", tCodestringQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tCodestringList);
		 prs.setPagingObject(pagingObject);

		 return prs;
	 }

	/**
	 * 查询所有的CodeString
	 */
	public List<TCodestringDto> queryCodeStringAll(TCodestringDto codestringDto) throws Exception {
		// 查询列表
		List<TCodestringDto> codestringDtoList = this.getSqlMapClientTemplate().queryForList("Common.select_codeStringAll", codestringDto);
		return codestringDtoList;
	}


}
