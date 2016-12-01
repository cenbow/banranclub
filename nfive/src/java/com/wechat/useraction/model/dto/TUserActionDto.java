package com.wechat.useraction.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:用户行为
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-29</p>
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
@EntityPK(Pk = "action_id", defaultColumn = false, tableName = "T_USER_ACTION")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TUserActionDto  extends BaseDtoImpl{//
    private java.lang.String action_id;//
    private java.lang.String platform_id;//公众平台号ID
    private java.lang.String action_type;//[CS:5064]菜单，图文项，关键字
    private java.lang.String action_value;
    private java.lang.String open_id;//操作人
    private java.util.Date action_time;//操作时间
    private java.lang.String menu_id;//菜单ID
    private java.lang.String url;//
    private java.lang.String keyword;//关键字

   /**
    * 获取
    * @return
    */
    public java.lang.String getAction_id() {
        return action_id;
    }

   /**
    * 设置
    * @param action_id
    */
    public void setAction_id(java.lang.String action_id) {
        this.action_id = action_id;
    }
   /**
    * 获取 公众平台号ID
    * @return
    */
    public java.lang.String getPlatform_id() {
        return platform_id;
    }

   /**
    * 设置 公众平台号ID
    * @param platform_id
    */
    public void setPlatform_id(java.lang.String platform_id) {
        this.platform_id = platform_id;
    }
   /**
    * 获取 [CS:5064]菜单，图文项，关键字
    * @return
    */
    public java.lang.String getAction_type() {
        return action_type;
    }

   /**
    * 设置 [CS:5064]菜单，图文项，关键字
    * @param action_type
    */
    public void setAction_type(java.lang.String action_type) {
        this.action_type = action_type;
    }
    public java.lang.String getAction_value() {
        return action_value;
    }

    public void setAction_value(java.lang.String actionValue) {
        action_value = actionValue;
    }
   /**
    * 获取 操作人
    * @return
    */
    public java.lang.String getOpen_id() {
        return open_id;
    }

   /**
    * 设置 操作人
    * @param open_id
    */
    public void setOpen_id(java.lang.String open_id) {
        this.open_id = open_id;
    }
   /**
    * 获取 操作时间
    * @return
    */
    public java.util.Date getAction_time() {
        return action_time;
    }

   /**
    * 设置 操作时间
    * @param action_time
    */
    public void setAction_time(java.util.Date action_time) {
        this.action_time = action_time;
    }
   /**
    * 获取 菜单ID
    * @return
    */
    public java.lang.String getMenu_id() {
        return menu_id;
    }

   /**
    * 设置 菜单ID
    * @param menu_id
    */
    public void setMenu_id(java.lang.String menu_id) {
        this.menu_id = menu_id;
    }
   /**
    * 获取
    * @return
    */
    public java.lang.String getUrl() {
        return url;
    }

   /**
    * 设置
    * @param url
    */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }
   /**
    * 获取 关键字
    * @return
    */
    public java.lang.String getKeyword() {
        return keyword;
    }

   /**
    * 设置 关键字
    * @param keyword
    */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }
}
