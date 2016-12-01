package com.wechat.article.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 * 
 * 规约开启方式 用于规约符合我们要求的对象
 * 1. 设置   AddParentClass = true
 * 2. 类       extends BaseDtoImpl
 * 
 * 规约关闭方式  用于规约不受我们控制的对象
 * 1. 设置   AddParentClass = false
 * 2. 类        implements IBaseDto
 */
@SuppressWarnings("serial")
@EntityPK(Pk = "rela_id", defaultColumn = false, tableName = "T_ARTICLE_RELA")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TArticleRelaDto  extends BaseDtoImpl{//
	private java.lang.String rela_id;//图文关联ID
	private java.lang.String article_group_id;//图文组ID
	private java.lang.String article_item_id;//图文ID
	private java.lang.Long rela_sort;//权重
    private java.util.Date pub_startdate;//发布开始日期
    private java.util.Date pub_enddate;//发布截至日期

   /**
	* 获取 图文关联ID
	* @return
	*/
	public java.lang.String getRela_id() {
		return rela_id;
	}
	
   /**
	* 设置 图文关联ID
	* @param rela_id
	*/
 	public void setRela_id(java.lang.String rela_id) {
		this.rela_id = rela_id;
	}
   /**
	* 获取 图文组ID
	* @return
	*/
	public java.lang.String getArticle_group_id() {
		return article_group_id;
	}
	
   /**
	* 设置 图文组ID
	* @param article_group_id
	*/
 	public void setArticle_group_id(java.lang.String article_group_id) {
		this.article_group_id = article_group_id;
	}
   /**
	* 获取 图文ID
	* @return
	*/
	public java.lang.String getArticle_item_id() {
		return article_item_id;
	}
	
   /**
	* 设置 图文ID
	* @param article_item_id
	*/
 	public void setArticle_item_id(java.lang.String article_item_id) {
		this.article_item_id = article_item_id;
	}
   /**
	* 获取 权重
	* @return
	*/
	public java.lang.Long getRela_sort() {
		return rela_sort;
	}
	
   /**
	* 设置 权重
	* @param rela_sort
	*/
 	public void setRela_sort(java.lang.Long rela_sort) {
		this.rela_sort = rela_sort;
	}
   /**
	* 获取 发布开始日期
	* @return
	*/
	public java.util.Date getPub_startdate() {
		return pub_startdate;
	}
	
   /**
	* 设置 发布开始日期
	* @param pub_startdate
	*/
 	public void setPub_startdate(java.util.Date pub_startdate) {
		this.pub_startdate = pub_startdate;
	}
   /**
	* 获取 发布截至日期
	* @return
	*/
	public java.util.Date getPub_enddate() {
		return pub_enddate;
	}
	
   /**
	* 设置 发布截至日期
	* @param pub_enddate
	*/
 	public void setPub_enddate(java.util.Date pub_enddate) {
		this.pub_enddate = pub_enddate;
	}
}
