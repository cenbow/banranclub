package com.wechat.article.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.dao.ITArticleGroupDao;
import com.wechat.article.dao.ITArticleRelaDao;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;

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

@Scope("prototype")
@Service("tArticleGroupService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TArticleGroupServiceImpl extends GenericServiceImpl<TArticleGroupDto, String> implements ITArticleGroupService{
    @Autowired
    private ITArticleGroupDao tArticleGroupDao;
    @Autowired
    private ITArticleRelaDao tArticleRelaDao;

    public BaseDao<TArticleGroupDto, String> getBaseDao() {
        return tArticleGroupDao;
    }

    /****
     * 取得图文组列表
    * @param  tArticleGroupQueryBean
    * @param  pagingObject
    * @return PageResult
    * @throws Exception
    */
    public PageResult queryTArticleGroupListPage(TArticleGroupQueryBean tArticleGroupQueryBean,PagingObject pagingObject) throws Exception{

        return this.tArticleGroupDao.queryTArticleGroupListPage(tArticleGroupQueryBean, pagingObject);
    }

    /**
     * 取得图文组基本信息（转换了相应的创建人、更新人名称）
     *
     * @param tArticleGroupQueryBean
     * @return TArticleGroupQueryBean
     * @throws Exception
     */
    public TArticleGroupQueryBean getArticleGroup(TArticleGroupQueryBean tArticleGroupQueryBean) throws Exception {

        return tArticleGroupDao.getArticleGroup(tArticleGroupQueryBean);
    }

    /**删除图文组（主键）、图文关系（动态条件）
     * @param tArticleGroupDto
     * @param tArticleRelaDto
     */
    public void delete(TArticleGroupDto tArticleGroupDto, TArticleRelaDto tArticleRelaDto) {
        // 1.删除图文组
        tArticleGroupDao.deletePK(tArticleGroupDto);
        // 2.删除图文关系（article_group_id关联的图文项全部删除）
        tArticleRelaDao.delete(tArticleRelaDto);
    }

    /**
     * 取得指定ID的图文组中符合发布条件的图文项
     * @param articleGroupId 图文组ID
     * @param pubBaseDate 发布基准日期
     * 
     * @return List<TArticleItemDto>
     */
	public List<ArticleItemVo> getArticleGroupPublishableItems(String articleGroupId, Date pubBaseDate)throws Exception {

		return tArticleGroupDao.getArticleGroupPublishableItems(articleGroupId, pubBaseDate);
	}

    /**
     * 取得指定ID的图文组中符合发布条件的图文项
     * @param articleGroupId
     * @return List<TArticleItemDto>
     * @throws Exception 
     */
	public List<ArticleItemVo> getArticleGroupPublishableItems(String articleGroupId) throws Exception {
		
		return getArticleGroupPublishableItems(articleGroupId, new Date());
	}

}
