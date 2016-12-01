package com.wechat.material.service;

import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.model.TWxNewsQueryBean;

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
public interface ITWxNewsService extends IGenericService<TWxNewsDto, String> {

	/****
	 * @param tWxNewsQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxNewsListPage(TWxNewsQueryBean tWxNewsQueryBean, PagingObject pagingObject) throws Exception;

    /**
     * 保存或更新图文素材信息
     * @param wxNewsDto
     * @return
     * @throws Exception
     */
    public void saveOrUpdateWxNews(TWxNewsDto wxNewsDto) throws Exception;

}
