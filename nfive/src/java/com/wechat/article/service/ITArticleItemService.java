package com.wechat.article.service;
import com.hercules.database.service.IGenericService;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.model.TArticleItemQueryBean;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.model.vo.ArticleItemVo;
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
public interface ITArticleItemService extends IGenericService<TArticleItemDto, String>{

    /****
    * 图文项列表
    * @param tArticleItemQueryBean
    * @param pagingObject
    * @return
    * @throws Exception
    */
    public PageResult queryTArticleItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception;

    /**保存图文项、图文关系、图片文件（原图、封面、缩略）
    * @param tArticleItemDto
    * @param tArticleRelaDto
    * @param orgTAttachMaterialDto
    * @param coverTAttachMaterialDto
    * @param thumbnailTAttachMaterialDto
    */
    public void save(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto, TAttachMaterialDto orgTAttachMaterialDto, TAttachMaterialDto coverTAttachMaterialDto, TAttachMaterialDto thumbnailTAttachMaterialDto);

    /**更新图文项、图文关系、图片文件（原图、封面、缩略）
    * @param tArticleItemDto
    * @param tArticleRelaDto
    * @param orgTAttachMaterialDto
    * @param coverTAttachMaterialDto
    * @param thumbnailTAttachMaterialDto
    */
    public void updatePK(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto, TAttachMaterialDto orgTAttachMaterialDto, TAttachMaterialDto coverTAttachMaterialDto, TAttachMaterialDto thumbnailTAttachMaterialDto);

    /**更新图文项、图文关系
     * @param tArticleItemDto
     * @param tArticleRelaDto
     * @param orgTAttachMaterialDto
     * @param coverTAttachMaterialDto
     * @param thumbnailTAttachMaterialDto
     */
    public void updatePK(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto);

    /**删除图文项（主键）、图文关系（动态条件）
    * @param tArticleItemDto
    * @param tArticleRelaDto
    */
    public void delete(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto);

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
    * @throws Exception
    */
    public ArticleItemVo getArticleGroupItem(TArticleRelaQueryBean tArticleRelaQueryBean) throws Exception;

    /**引用图文项（更新图文项、插入图文关系）
     * @param tArticleRelaDto
    */
    public void refArticleItem(TArticleRelaDto tArticleRelaDto);

    /**引用图文项（更新图文项、插入图文关系）
    * @param tArticleItemDto
    * @param tArticleRelaDto
    */
    public void refArticleItem(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto);

    /**引用图文项（更新图文项、插入图文关系、图片文件（原图、封面、缩略））
     * @param tArticleItemDto
     * @param tArticleRelaDto
     * @param orgTAttachMaterialDto
     * @param coverTAttachMaterialDto
     * @prama thumbnailTAttachMaterialDto
     */
     public void refArticleItem(TArticleItemDto tArticleItemDto, TArticleRelaDto tArticleRelaDto, TAttachMaterialDto orgTAttachMaterialDto, TAttachMaterialDto coverTAttachMaterialDto, TAttachMaterialDto thumbnailTAttachMaterialDto);
}
