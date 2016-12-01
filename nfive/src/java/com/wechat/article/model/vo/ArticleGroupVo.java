package com.wechat.article.model.vo;
import com.wechat.article.model.dto.TArticleGroupDto;

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
@SuppressWarnings("serial")
public class ArticleGroupVo extends TArticleGroupDto{
    private java.lang.String created_user_name;//创建人
    private java.lang.String updated_user_name;//更新人
    private java.util.Date updated_date_start;//更新开始时间
    private java.util.Date updated_date_end;//更新结束时间
    private java.lang.String ag_manager_name;//图文组管理人
    private java.lang.String article_type_name;//图文类型名称

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

    public java.lang.String getAg_manager_name() {
        return ag_manager_name;
    }

    public void setAg_manager_name(java.lang.String agManagerName) {
        ag_manager_name = agManagerName;
    }
    
    
    public java.lang.String getArticle_type_name() {
		return article_type_name;
	}

	public void setArticle_type_name(java.lang.String articleTypeName) {
		article_type_name = articleTypeName;
	}
}