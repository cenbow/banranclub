package com.wechat.material.service.impl;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.material.dao.ITWxNewsDao;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.model.TWxNewsQueryBean;
import com.wechat.material.service.ITWxNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;

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
@Service("tWxNewsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TWxNewsServiceImpl extends GenericServiceImpl<TWxNewsDto, String> implements ITWxNewsService {

	@Autowired
	private ITWxNewsDao tWxNewsDao;

	public BaseDao<TWxNewsDto, String> getBaseDao() {
		return tWxNewsDao;
	}

	/****
	 * @param tWxNewsQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxNewsListPage(TWxNewsQueryBean tWxNewsQueryBean, PagingObject pagingObject) throws Exception {
		return tWxNewsDao.queryTWxNewsListPage(tWxNewsQueryBean, pagingObject);
	}

    /**
     * 保存或更新图文素材信息
     * @param wxNewsDto
     * @return
     * @throws Exception
     */
    public void saveOrUpdateWxNews(TWxNewsDto wxNewsDto) throws Exception{
        TWxNewsDto temp = new TWxNewsDto();
        temp.setMedia_id(wxNewsDto.getMedia_id());
        temp = tWxNewsDao.getRow(temp);
        if(temp!=null){
            wxNewsDto.setNews_id(temp.getNews_id());
            tWxNewsDao.updatePK(wxNewsDto);
        }else{
            tWxNewsDao.save(wxNewsDto);
        }
    }

}
