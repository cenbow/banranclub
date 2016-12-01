package com.wechat.article.dao;
import com.hercules.database.dao.BaseDao;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.model.TArticleItemQueryBean;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.vo.ArticleItemVo;

/**
 * 类功能:图文项
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITArticleItemDao extends BaseDao<TArticleItemDto,String>{

    /****
    * 图文项列表
    * @param tArticleItemQueryBean
    * @param pagingObject
    * @return
    * @throws Exception
    */
    public PageResult queryTArticleItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception;
    
    /****
    * 根据图文关系取得图文项列表
    * @param tArticleItemQueryBean
    * @param pagingObject
    * @return
    * @throws Exception
    */
    public PageResult queryTArticleGroupItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception;
    
    /**
     * 根据图文关系取得图文项
     * @param tArticleRelaQueryBean
     * @return ArticleItemVo
     */
    public ArticleItemVo getArticleGroupItem(TArticleRelaQueryBean tArticleRelaQueryBean) throws Exception;
}


