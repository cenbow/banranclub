package com.wechat.article.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

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
@EntityPK(Pk = "article_group_id", defaultColumn = false, tableName = "T_ARTICLE_GROUP")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TArticleGroupDto  extends BaseDtoImpl{//
	private java.lang.String article_group_id;//图文组ID
	private java.lang.String ag_code;//图文组编号
	private java.lang.String ag_name;//图文组名称
	private java.lang.String ag_manager;//图文组管理人
	private java.lang.String article_type;//图文素材类型【CS:5051】1.单图文 2.多图文
	private java.lang.String enable_agflag;//启用标志【CS:1000】
	private java.math.BigDecimal max_items_count;//最大条目数,图片组发布时显示的最大项目数量

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
	* 获取 图文组编号
	* @return
	*/
	public java.lang.String getAg_code() {
		return ag_code;
	}
	
   /**
	* 设置 图文组编号
	* @param ag_code
	*/
 	public void setAg_code(java.lang.String ag_code) {
		this.ag_code = ag_code;
	}
   /**
	* 获取 图文组名称
	* @return
	*/
	public java.lang.String getAg_name() {
		return ag_name;
	}
	
   /**
	* 设置 图文组名称
	* @param ag_name
	*/
 	public void setAg_name(java.lang.String ag_name) {
		this.ag_name = ag_name;
	}
   /**
	* 获取 图文组管理人
	* @return
	*/
	public java.lang.String getAg_manager() {
		return ag_manager;
	}
	
   /**
	* 设置 图文组管理人
	* @param ag_manager
	*/
 	public void setAg_manager(java.lang.String ag_manager) {
		this.ag_manager = ag_manager;
	}
   /**
	* 获取 图文素材类型【CS:5051】1.单图文 2.多图文
	* @return
	*/
	public java.lang.String getArticle_type() {
		return article_type;
	}
	
   /**
	* 设置 图文素材类型【CS:5051】1.单图文 2.多图文
	* @param article_type
	*/
 	public void setArticle_type(java.lang.String article_type) {
		this.article_type = article_type;
	}
   /**
	* 获取 启用标志【CS:1000】
	* @return
	*/
	public java.lang.String getEnable_agflag() {
		return enable_agflag;
	}
	
   /**
	* 设置 启用标志【CS:1000】
	* @param enable_agflag
	*/
 	public void setEnable_agflag(java.lang.String enable_agflag) {
		this.enable_agflag = enable_agflag;
	}
   /**
	* 获取 最大条目数,图片组发布时显示的最大项目数量
	* @return
	*/
	public java.math.BigDecimal getMax_items_count() {
		return max_items_count;
	}
	
   /**
	* 设置 最大条目数,图片组发布时显示的最大项目数量
	* @param max_items_count
	*/
 	public void setMax_items_count(java.math.BigDecimal max_items_count) {
		this.max_items_count = max_items_count;
	}
}
