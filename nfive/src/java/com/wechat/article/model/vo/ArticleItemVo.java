package com.wechat.article.model.vo;
import com.wechat.article.model.dto.TArticleItemDto;

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
public class ArticleItemVo extends TArticleItemDto{
    private static final long serialVersionUID = 1L;
    private String article_url;       //图文链接
    private String pic_org_url;       //封面图原图URL
    private String pic_cover_url;     //封面图处理图URL  （900*500）
    private String pic_thumbnail_url; //封面图缩略图URL （200*200）
    private java.lang.String org_store_name;//存储名称（原图）
    private java.lang.String org_store_path;//存储路径（原图）
    private java.lang.String cover_store_name;//存储名称（封面图）
    private java.lang.String cover_store_path;//存储路径（封面图）
    private java.lang.String thumbnail_store_name;//存储名称（缩略图）
    private java.lang.String thumbnail_store_path;//存储路径（缩略图）
    private java.lang.String rela_id;//图文关联ID
    private java.lang.String article_group_id;//图文组ID
    private java.lang.Long rela_sort;//权重
    private java.lang.String created_user_name;//创建人
    private java.lang.String updated_user_name;//更新人

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String articleUrl) {
        article_url = articleUrl;
    }

    public String getPic_org_url() {
        return pic_org_url;
    }

    public void setPic_org_url(String picOrgUrl) {
        pic_org_url = picOrgUrl;
    }
    public String getPic_cover_url() {
        return pic_cover_url;
    }

    public void setPic_cover_url(String picCoverUrl) {
        pic_cover_url = picCoverUrl;
    }
    public String getPic_thumbnail_url() {
        return pic_thumbnail_url;
    }

    public void setPic_thumbnail_url(String picThumbnailUrl) {
        pic_thumbnail_url = picThumbnailUrl;
    }

    public java.lang.String getOrg_store_name() {
        return org_store_name;
    }

    public void setOrg_store_name(java.lang.String orgStoreName) {
        org_store_name = orgStoreName;
    }

    public java.lang.String getOrg_store_path() {
        return org_store_path;
    }

    public void setOrg_store_path(java.lang.String orgStorePath) {
        org_store_path = orgStorePath;
    }

    public java.lang.String getCover_store_name() {
        return cover_store_name;
    }

    public void setCover_store_name(java.lang.String coverStoreName) {
        cover_store_name = coverStoreName;
    }

    public java.lang.String getCover_store_path() {
        return cover_store_path;
    }

    public void setCover_store_path(java.lang.String coverStorePath) {
        cover_store_path = coverStorePath;
    }

    public java.lang.String getThumbnail_store_name() {
        return thumbnail_store_name;
    }

    public void setThumbnail_store_name(java.lang.String thumbnailStoreName) {
        thumbnail_store_name = thumbnailStoreName;
    }

    public java.lang.String getThumbnail_store_path() {
        return thumbnail_store_path;
    }

    public void setThumbnail_store_path(java.lang.String thumbnailStorePath) {
        thumbnail_store_path = thumbnailStorePath;
    }

    public java.lang.String getRela_id() {
        return rela_id;
    }
    public void setRela_id(java.lang.String relaId) {
        rela_id = relaId;
    }

    public java.lang.String getArticle_group_id() {
        return article_group_id;
    }
    public void setArticle_group_id(java.lang.String articleGroupId) {
        article_group_id = articleGroupId;
    }

    public java.lang.Long getRela_sort() {
        return rela_sort;
    }
    public void setRela_sort(java.lang.Long relaSort) {
        rela_sort = relaSort;
    }

    public java.lang.String getCreated_user_name() {
        return created_user_name;
    }
    public void setCreated_user_name(java.lang.String createdUserName) {
        created_user_name = createdUserName;
    }
    public java.lang.String getUpdated_user_name() {
        return updated_user_name;
    }

    public void setUpdated_user_name(java.lang.String updatedUserName) {
        updated_user_name = updatedUserName;
    }

}
