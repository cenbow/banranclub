package com.wechat.article.dao;
import java.util.Date;
import java.util.List;

import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.model.vo.ArticleItemVo;

/**
 * 类功能:图文组
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITArticleGroupDao extends BaseDao<TArticleGroupDto,String>{

    /****
    *图文项列表
    * @param tArticleGroupQueryBean
    * @param pagingObject
    * @return
    * @throws Exception
    */
    public PageResult queryTArticleGroupListPage(TArticleGroupQueryBean tArticleGroupQueryBean,PagingObject pagingObject) throws Exception;
    
    /**
     * 取得图文组基本信息
     * 
     * @param tArticleGroupQueryBean
     * @return TArticleGroupQueryBean
     * @throws Exception
     */
    public TArticleGroupQueryBean getArticleGroup(TArticleGroupQueryBean tArticleGroupQueryBean) throws Exception;
    
    /**
     * 取得指定图文组中符合发布条件的图文项
     * @param articleGroupId
     * @param pubBaseDate
     * @return
     * @throws Exception
     */
    public List<ArticleItemVo> getArticleGroupPublishableItems(String articleGroupId, Date pubBaseDate) throws Exception;
}
