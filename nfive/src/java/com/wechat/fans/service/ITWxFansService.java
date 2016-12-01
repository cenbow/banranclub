package com.wechat.fans.service;

import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TWxFansDto;
import com.wechat.fans.model.TWxFansQueryBean;

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
public interface ITWxFansService extends IGenericService<TWxFansDto, String> {

	/****
	 * @param tWxFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxFansListPage(TWxFansQueryBean tWxFansQueryBean, PagingObject pagingObject) throws Exception;

    /**
     * 保存或更新微信粉丝
     * @param openid
     * @return
     * @throws Exception
     */
    public void saveOrUpdateWxFans(String openid) throws Exception;

    /**
     * 微信粉丝取消关注
     * @param openid
     * @throws Exception
     */
    public void unSubscribe(String openid) throws Exception;
}
