package com.wechat.article.model;
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
@SuppressWarnings("serial")
public class TArticleItemQueryBean extends TArticleItemDto{

    private java.lang.String orderStr;//排序字串
    private java.lang.String article_group_id;//图文组ID TODO 这个字段要删除
    private java.lang.String created_user_name;//创建人
    private java.lang.String updated_user_name;//更新人
    private java.util.Date updated_date_start;//更新开始时间
    private java.util.Date updated_date_end;//更新结束时间

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public java.lang.String getArticle_group_id() {
        return article_group_id;
    }

    public void setArticle_group_id(java.lang.String articleGroupId) {
        article_group_id = articleGroupId;
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

    public java.util.Date getUpdated_date_start() {
        return updated_date_start;
    }

    public void setUpdated_date_start(java.util.Date updatedDateStart) {
        updated_date_start = updatedDateStart;
    }

    public java.util.Date getUpdated_date_end() {
        return updated_date_end;
    }

    public void setUpdated_date_end(java.util.Date updatedDateEnd) {
        updated_date_end = updatedDateEnd;
    }
}