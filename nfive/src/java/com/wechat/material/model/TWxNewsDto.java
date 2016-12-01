package com.wechat.material.model;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

/**
 * 类功能:自动代码生成模板 DTO模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
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
@EntityPK(Pk = "news_id", defaultColumn = false, tableName = "T_WX_NEWS")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWxNewsDto  extends BaseDtoImpl{//
	private String news_id;//粉丝ID
	private String media_id;//MEDIA_ID
	private String title;//标题
	private String thumb_media_id;//封面图片素材ID
	private String thumb_url;//封面图片素材URL
	private String show_cover_pic;//是否显示为封面【1000】
	private String author;//作者
	private String digest;//单图文摘要
	private byte[] content;//图文内容
	private String url;//图文素材URL
	private String content_source_url;//原文地址
    private Date update_time;//最后更新时间
	private java.math.BigDecimal child_news;//子素材数量
	private String update_batch;//更新批次

   /**
	* 获取 粉丝ID
	* @return
	*/
	public String getNews_id() {
		return news_id;
	}

   /**
	* 设置 粉丝ID
	* @param news_id
	*/
 	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
   /**
	* 获取 MEDIA_ID
	* @return
	*/
	public String getMedia_id() {
		return media_id;
	}

   /**
	* 设置 MEDIA_ID
	* @param media_id
	*/
 	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
   /**
	* 获取 标题
	* @return
	*/
	public String getTitle() {
		return title;
	}

   /**
	* 设置 标题
	* @param title
	*/
 	public void setTitle(String title) {
		this.title = title;
	}
   /**
	* 获取 封面图片素材ID
	* @return
	*/
	public String getThumb_media_id() {
		return thumb_media_id;
	}

   /**
	* 设置 封面图片素材ID
	* @param thumb_media_id
	*/
 	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
   /**
	* 获取 封面图片素材URL
	* @return
	*/
	public String getThumb_url() {
		return thumb_url;
	}

   /**
	* 设置 封面图片素材URL
	* @param thumb_url
	*/
 	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
   /**
	* 获取 是否显示为封面【1000】
	* @return
	*/
	public String getShow_cover_pic() {
		return show_cover_pic;
	}

   /**
	* 设置 是否显示为封面【1000】
	* @param show_cover_pic
	*/
 	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
   /**
	* 获取 作者
	* @return
	*/
	public String getAuthor() {
		return author;
	}

   /**
	* 设置 作者
	* @param author
	*/
 	public void setAuthor(String author) {
		this.author = author;
	}
   /**
	* 获取 单图文摘要
	* @return
	*/
	public String getDigest() {
		return digest;
	}

   /**
	* 设置 单图文摘要
	* @param digest
	*/
 	public void setDigest(String digest) {
		this.digest = digest;
	}
   /**
	* 获取 图文内容
	* @return
	*/
	public byte[] getContent() {
		return content;
	}

   /**
	* 设置 图文内容
	* @param content
	*/
 	public void setContent(byte[] content) {
		this.content = content;
	}
   /**
	* 获取 图文素材URL
	* @return
	*/
	public String getUrl() {
		return url;
	}

   /**
	* 设置 图文素材URL
	* @param url
	*/
 	public void setUrl(String url) {
		this.url = url;
	}
   /**
	* 获取 原文地址
	* @return
	*/
	public String getContent_source_url() {
		return content_source_url;
	}

   /**
	* 设置 原文地址
	* @param content_source_url
	*/
 	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
   /**
	* 获取 最后更新时间
	* @return
	*/
	public Date getUpdate_time() {
		return update_time;
	}

   /**
	* 设置 最后更新时间
	* @param update_time
	*/
 	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
   /**
	* 获取 子素材数量
	* @return
	*/
	public java.math.BigDecimal getChild_news() {
		return child_news;
	}

   /**
	* 设置 子素材数量
	* @param child_news
	*/
 	public void setChild_news(java.math.BigDecimal child_news) {
		this.child_news = child_news;
	}
   /**
	* 获取 更新批次
	* @return
	*/
	public String getUpdate_batch() {
		return update_batch;
	}

   /**
	* 设置 更新批次
	* @param update_batch
	*/
 	public void setUpdate_batch(String update_batch) {
		this.update_batch = update_batch;
	}
}
