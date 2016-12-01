package com.wechat.article.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.wechat.article.dao.ITArticleRelaDao;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.service.ITArticleRelaService;


/**
 * 类功能:图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tArticleRelaService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TArticleRelaServiceImpl extends GenericServiceImpl<TArticleRelaDto, String> implements ITArticleRelaService{
    @Autowired
    private ITArticleRelaDao   tArticleRelaDao;

	public BaseDao<TArticleRelaDto, String> getBaseDao() {
		return tArticleRelaDao;
	}
	
}
