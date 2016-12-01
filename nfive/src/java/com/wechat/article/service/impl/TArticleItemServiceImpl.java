package com.wechat.article.service.impl;
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
import com.wechat.article.dao.ITArticleItemDao;
import com.wechat.article.dao.ITArticleRelaDao;
import com.wechat.article.model.TArticleItemQueryBean;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.material.dao.ITAttachMaterialDao;
import com.wechat.material.model.dto.TAttachMaterialDto;

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

@Scope("prototype")
@Service("tArticleItemService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TArticleItemServiceImpl extends GenericServiceImpl<TArticleItemDto, String> implements ITArticleItemService{
    @Autowired
    private ITArticleItemDao tArticleItemDao;
    @Autowired
    private ITArticleRelaDao tArticleRelaDao;
    @Autowired
    private ITAttachMaterialDao tAttachMaterialDao;

    public BaseDao<TArticleItemDto, String> getBaseDao() {
        return tArticleItemDao;
    }

    /****
     * 图文项列表
     * @param  tArticleItemQueryBean
     * @param  pagingObject
     * @return PageResult
     * @throws Exception
    */
    public PageResult queryTArticleItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception{

        return this.tArticleItemDao.queryTArticleItemListPage(tArticleItemQueryBean, pagingObject);
    }

    /**保存图文项、图文关系、图片文件（原图、封面、缩略）
     * @param tArticleItemDto
     * @param tArticleRelaDto
     * @param orgTAttachMaterialDto
     * @param coverTAttachMaterialDto
     * @param thumbnailTAttachMaterialDto
     */
    public void save(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto, TAttachMaterialDto orgTAttachMaterialDto, TAttachMaterialDto coverTAttachMaterialDto, TAttachMaterialDto thumbnailTAttachMaterialDto) {
        //1.保存图片
        tAttachMaterialDao.save(orgTAttachMaterialDto);
        tAttachMaterialDao.save(coverTAttachMaterialDto);
        tAttachMaterialDao.save(thumbnailTAttachMaterialDto);

        //2.保存图文项
        tArticleItemDto.setPic_org(orgTAttachMaterialDto.getFile_id());
        tArticleItemDto.setPic_cover(coverTAttachMaterialDto.getFile_id());
        tArticleItemDto.setPic_thumbnail(thumbnailTAttachMaterialDto.getFile_id());
        tArticleItemDao.save(tArticleItemDto);

        //3.设置图文项ID
        tArticleRelaDto.setArticle_item_id(tArticleItemDto.getArticle_item_id());

        //4.保存图文关系
        tArticleRelaDao.save(tArticleRelaDto);
    }

    /**更新图文项、图文关系、图片文件（原图、封面、缩略）
     * @param tArticleItemDto
     * @param tArticleRelaDto
     * @param orgTAttachMaterialDto
     * @param coverTAttachMaterialDto
     * @param thumbnailTAttachMaterialDto
     */
    public void updatePK(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto, TAttachMaterialDto orgTAttachMaterialDto, TAttachMaterialDto coverTAttachMaterialDto, TAttachMaterialDto thumbnailTAttachMaterialDto) {
        //1.保存图片
        tAttachMaterialDao.save(orgTAttachMaterialDto);
        tAttachMaterialDao.save(coverTAttachMaterialDto);
        tAttachMaterialDao.save(thumbnailTAttachMaterialDto);

        //2.保存图文项
        tArticleItemDto.setPic_org(orgTAttachMaterialDto.getFile_id());
        tArticleItemDto.setPic_cover(coverTAttachMaterialDto.getFile_id());
        tArticleItemDto.setPic_thumbnail(thumbnailTAttachMaterialDto.getFile_id());
        tArticleItemDao.updatePK(tArticleItemDto);

        //3.保存图文关系
        tArticleRelaDao.updatePK(tArticleRelaDto);
    }

    /**更新图文项、图文关系
     * @param tArticleItemDto
     * @param tArticleRelaDto
     * @param orgTAttachMaterialDto
     * @param coverTAttachMaterialDto
     * @param thumbnailTAttachMaterialDto
     */
    public void updatePK(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto) {
        //1.保存图文项
        tArticleItemDao.updatePK(tArticleItemDto);

        //2.保存图文关系
        tArticleRelaDao.updatePK(tArticleRelaDto);
    }

    /**删除图文项（主键）、图文关系（动态条件）
    * @param tArticleItemDto
    * @param tArticleRelaDto
    */
    public void delete(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto) {
        //1.删除图文项
        tArticleItemDao.deletePK(tArticleItemDto);
        //2.删除图文关系（article_item_id关联的图文项全部删除）
        tArticleRelaDao.delete(tArticleRelaDto);
    }

    /****
    * 根据图文关系取得图文项列表
    * @param  tArticleItemQueryBean
    * @param  pagingObject
    * @return PageResult
    * @throws Exception
    */
    public PageResult queryTArticleGroupItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception{

        return this.tArticleItemDao.queryTArticleGroupItemListPage(tArticleItemQueryBean, pagingObject);
    }

    /**
    * 根据图文关系取得图文项
    * @param tArticleRelaQueryBean
    * @return ArticleItemVo
    * @throws Exception
    */
    public ArticleItemVo getArticleGroupItem(TArticleRelaQueryBean tArticleRelaQueryBean) throws Exception {
        return tArticleItemDao.getArticleGroupItem(tArticleRelaQueryBean);
    }

    /**引用图文项（插入图文关系）
     * @param tArticleRelaDto
    */
    public void refArticleItem(TArticleRelaDto tArticleRelaDto) {
         tArticleRelaDao.save(tArticleRelaDto);
    }

    /**引用图文项（更新图文项，插入图文关系）
    * @param tArticleItemDto
    * @param tArticleRelaDto
    */
    public void refArticleItem(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto) {
        //1.更新图文项
        tArticleItemDao.updatePK(tArticleItemDto);
        //2.插入图文关系
        tArticleRelaDao.save(tArticleRelaDto);
    }

    /**引用图文项（更新图文项、插入图文关系、图片文件（原图、封面、缩略））
     * @param tArticleItemDto
     * @param tArticleRelaDto
     * @param orgTAttachMaterialDto
     * @param coverTAttachMaterialDto
     * @param thumbnailTAttachMaterialDto
     */
    public void refArticleItem(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto, TAttachMaterialDto orgTAttachMaterialDto, TAttachMaterialDto coverTAttachMaterialDto, TAttachMaterialDto thumbnailTAttachMaterialDto) {
        //1.更新图文项
        tArticleItemDao.updatePK(tArticleItemDto);

        //2.插入图文关系
        tArticleRelaDao.save(tArticleRelaDto);

        //3.保存图片
        tAttachMaterialDao.save(orgTAttachMaterialDto);
        tAttachMaterialDao.save(coverTAttachMaterialDto);
        tAttachMaterialDao.save(thumbnailTAttachMaterialDto);

    }
}
