package com.wechat.article.service;
import java.util.Date;
import java.util.List;

import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.model.dto.TArticleRelaDto;
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
public interface ITArticleGroupService extends IGenericService<TArticleGroupDto, String>{

    /****
    *取得图文组列表
    * @param tArticleGroupQueryBean
    * @param pagingObject
    * @return
    * @throws Exception
    */
    public PageResult queryTArticleGroupListPage(TArticleGroupQueryBean tArticleGroupQueryBean,PagingObject pagingObject) throws Exception;

    /**
     * 取得图文组基本信息（转换了相应的创建人、更新人名称）
     *
     * @param tArticleGroupQueryBean
     * @return TArticleGroupQueryBean
     * @throws Exception
     */
    public TArticleGroupQueryBean getArticleGroup(TArticleGroupQueryBean tArticleGroupQueryBean) throws Exception;

    /**删除图文组（主键）、图文关系（动态条件）
     * @param tArticleGroupDto
     * @param tArticleRelaDto
     */
     public void delete(TArticleGroupDto tArticleGroupDto, TArticleRelaDto tArticleRelaDto);
     
     
     /**
      * 取得指定图文组中符合发布条件的图文项
      * @param articleGroupId 图文组ID
      * @param pubBaseDate 发布基准日期
      * 
      * @return List<TArticleItemDto>
     * @throws Exception 
      */
     public List<ArticleItemVo> getArticleGroupPublishableItems(String articleGroupId, Date pubBaseDate) throws Exception;
     
     /**
      * 取得指定ID的图文组中符合发布条件的图文项
      * @param articleGroupId
      * @return List<TArticleItemDto>
     * @throws Exception 
      */
     public List<ArticleItemVo> getArticleGroupPublishableItems(String articleGroupId) throws Exception;
}
