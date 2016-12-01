package com.wechat.article.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

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
@EntityPK(Pk = "article_item_id", defaultColumn = false, tableName = "T_ARTICLE_ITEM")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TArticleItemDto  extends BaseDtoImpl{//
    private java.lang.String article_item_id;//图文ID
    private java.lang.String title;//标题
    private java.lang.String author;//作者
    private java.lang.String article_type;//图文项类型[CS:5055] 图文项的业务类型
    private java.lang.String article_state;//图文项目状态[CS:5056]1.草稿状态2.完稿待审核3.审核通过4.审核未通过5.强制无效
    private java.util.Date pub_startdate;//发布开始日期
    private java.util.Date pub_enddate;//发布截至日期
    private java.lang.String summary;//摘要
    private java.lang.String pic_org;//封面图原图附件ID
    private java.lang.String pic_cover;//封面图处理图附件ID （900*500）
    private java.lang.String pic_thumbnail;//封面图缩略图附件ID （200*200）
    private java.lang.String org_flag;//微信图文链接启用标志[CS:1000]
    private java.lang.String org_url;//微信图文链接
    private java.lang.String content;//正文内容
    private java.lang.String templet_flag;//是否动态模版[CS:1000]  默认为否  为以后动态标签替换做准备
    private java.lang.String cite_url;//推广链接
    private java.lang.String remark;//备注说明

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
    * 获取 标题
    * @return
    */
    public java.lang.String getTitle() {
        return title;
    }

   /**
    * 设置 标题
    * @param title
    */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }
   /**
    * 获取 作者
    * @return
    */
    public java.lang.String getAuthor() {
        return author;
    }

   /**
    * 设置 作者
    * @param author
    */
    public void setAuthor(java.lang.String author) {
        this.author = author;
    }
   /**
    * 获取 图文项类型[CS:5055] 图文项的业务类型
    * @return
    */
    public java.lang.String getArticle_type() {
        return article_type;
    }

   /**
    * 设置 图文项类型[CS:5055] 图文项的业务类型
    * @param article_type
    */
    public void setArticle_type(java.lang.String article_type) {
        this.article_type = article_type;
    }
   /**
    * 获取 图文项目状态[CS:5056]1.草稿状态2.完稿待审核3.审核通过4.审核未通过5.强制无效
    * @return
    */
    public java.lang.String getArticle_state() {
        return article_state;
    }

   /**
    * 设置 图文项目状态[CS:5056]1.草稿状态2.完稿待审核3.审核通过4.审核未通过5.强制无效
    * @param article_state
    */
    public void setArticle_state(java.lang.String article_state) {
        this.article_state = article_state;
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
   /**
    * 获取 摘要
    * @return
    */
    public java.lang.String getSummary() {
        return summary;
    }

   /**
    * 设置 摘要
    * @param summary
    */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }
   /**
    * 获取 封面图原图附件ID
    * @return
    */
    public java.lang.String getPic_org() {
        return pic_org;
    }

   /**
    * 设置 封面图原图附件ID
    * @param pic_org
    */
    public void setPic_org(java.lang.String pic_org) {
        this.pic_org = pic_org;
    }
   /**
    * 获取 封面图处理图附件ID （900*500）
    * @return
    */
    public java.lang.String getPic_cover() {
        return pic_cover;
    }

   /**
    * 设置 封面图处理图附件ID （900*500）
    * @param pic_cover
    */
    public void setPic_cover(java.lang.String pic_cover) {
        this.pic_cover = pic_cover;
    }
   /**
    * 获取 封面图缩略图附件ID （200*200）
    * @return
    */
    public java.lang.String getPic_thumbnail() {
        return pic_thumbnail;
    }

   /**
    * 设置 封面图缩略图附件ID （200*200）
    * @param pic_thumbnail
    */
    public void setPic_thumbnail(java.lang.String pic_thumbnail) {
        this.pic_thumbnail = pic_thumbnail;
    }

   /**
    * 获取 微信图文链接启用标志[CS:1000]
    * @return
    */
    public java.lang.String getOrg_flag() {
        return org_flag;
    }

   /**
    * 设置 微信图文链接启用标志[CS:1000]
    * @param org_flag
    */
    public void setOrg_flag(java.lang.String org_flag) {
        this.org_flag = org_flag;
    }
   /**
    * 获取 微信图文链接
    * @return
    */
    public java.lang.String getOrg_url() {
        return org_url;
    }

   /**
    * 设置 微信图文链接
    * @param org_url
    */
    public void setOrg_url(java.lang.String org_url) {
        this.org_url = org_url;
    }
   /**
    * 获取 正文内容
    * @return
    */
    public java.lang.String getContent() {
        return content;
    }

   /**
    * 设置 正文内容
    * @param content
    */
    public void setContent(java.lang.String content) {
        this.content = content;
    }
   /**
    * 获取 是否动态模版[CS:1000]  默认为否  为以后动态标签替换做准备
    * @return
    */
    public java.lang.String getTemplet_flag() {
        return templet_flag;
    }

   /**
    * 设置 是否动态模版[CS:1000]  默认为否  为以后动态标签替换做准备
    * @param templet_flag
    */
    public void setTemplet_flag(java.lang.String templet_flag) {
        this.templet_flag = templet_flag;
    }
   /**
    * 获取 推广链接
    * @return
    */
    public java.lang.String getCite_url() {
        return cite_url;
    }

   /**
    * 设置 推广链接
    * @param cite_url
    */
    public void setCite_url(java.lang.String cite_url) {
        this.cite_url = cite_url;
    }
   /**
    * 获取 备注说明
    * @return
    */
    public java.lang.String getRemark() {
        return remark;
    }

   /**
    * 设置 备注说明
    * @param remark
    */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
}
