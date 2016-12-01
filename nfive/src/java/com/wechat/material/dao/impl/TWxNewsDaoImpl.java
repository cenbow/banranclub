package com.wechat.material.dao.impl;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.material.dao.ITWxNewsDao;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.model.TWxNewsQueryBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;

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
@Service("tWxNewsDao")
public class TWxNewsDaoImpl extends BaseDaoImpl<TWxNewsDto, String> implements ITWxNewsDao {

	public TWxNewsDaoImpl() {
		super(TWxNewsDto.class);
	}

	/****
	 *
	 * @param tWxNewsQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxNewsListPage(TWxNewsQueryBean tWxNewsQueryBean, PagingObject pagingObject) throws Exception {

		tWxNewsQueryBean.setOrderStr(" order by " + pagingObject.getSort_name() + " " + pagingObject.getSort_order());
		PageResult prs = new PageResult();

		// 查询记录总量信息
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWxNews.select_tWxNewsList_count", tWxNewsQueryBean);

		// 填充分页对象 以便构重新初始化对象
		pagingObject.calculatePage(count.intValue());

		// 查询列表
		List tWxNewsList = this.getSqlMapClientTemplate().queryForList("tWxNews.select_tWxNewsList", tWxNewsQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		prs.setResultList(tWxNewsList);
		prs.setPagingObject(pagingObject);

		return prs;
	}

    //生成新的批次号
    public String newBatchNo() throws Exception {
        return this.getSqlMapClientTemplate().queryForObject("tWxNews.select_batch_no").toString();
    }

    //删除不存在的素材 根据批次号
    public void deleteWxNews(String batch_no) throws Exception {
         this.getSqlMapClientTemplate().update("tWxNews.delete_newsByBatchNo",batch_no);
    }

}
