package com.wechat.menucfg.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

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
@EntityPK(Pk = "menu_id", defaultColumn = false, tableName = "T_WCMENU")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWcmenuDto  extends BaseDtoImpl{//
    private java.lang.String menu_id;//菜单ID
    private java.lang.String menu_code;//菜单代码
    private java.lang.String menu_name;//菜单名称
    private java.lang.String menu_desc;//资源描述
    private java.lang.String action_type;//菜单动作类型[CS:5011]  1链接页面2图文消息3文本消息4图片消息5音频消息6视频消息7自定义消息
    private java.lang.String text_msg;//工作关联文本
    private java.lang.String material_id;//动作关联素材ID
    private java.lang.String cust_srv_flag;//客服模式
    private java.lang.String action_url;//动作URL（仅能用于链接）
    private java.lang.String action_class;//自定义消息(仅用作执行业务类)
    private java.lang.String platform_id;//公众平台号ID
    private java.lang.Long rela_sort;//
    private java.lang.String templet_flag;//是否动态模版[CS:1000]
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
    * 获取 菜单代码
    * @return
    */
    public java.lang.String getMenu_code() {
        return menu_code;
    }

   /**
    * 设置 菜单代码
    * @param menu_code
    */
    public void setMenu_code(java.lang.String menu_code) {
        this.menu_code = menu_code;
    }
   /**
    * 获取 菜单名称
    * @return
    */
    public java.lang.String getMenu_name() {
        return menu_name;
    }

   /**
    * 设置 菜单名称
    * @param menu_name
    */
    public void setMenu_name(java.lang.String menu_name) {
        this.menu_name = menu_name;
    }
   /**
    * 获取 资源描述
    * @return
    */
    public java.lang.String getMenu_desc() {
        return menu_desc;
    }

   /**
    * 设置 资源描述
    * @param menu_desc
    */
    public void setMenu_desc(java.lang.String menu_desc) {
        this.menu_desc = menu_desc;
    }
   /**
    * 获取 菜单动作类型[CS:5011]  1链接页面2图文消息3文本消息4图片消息5音频消息6视频消息
    * @return
    */
    public java.lang.String getAction_type() {
        return action_type;
    }

   /**
    * 设置 菜单动作类型[CS:5011]  1链接页面2图文消息3文本消息4图片消息5音频消息6视频消息
    * @param action_type
    */
    public void setAction_type(java.lang.String action_type) {
        this.action_type = action_type;
    }
   /**
    * 获取 工作关联文本
    * @return
    */
    public java.lang.String getText_msg() {
        return text_msg;
    }

   /**
    * 设置 工作关联文本
    * @param text_msg
    */
    public void setText_msg(java.lang.String text_msg) {
        this.text_msg = text_msg;
    }
   /**
    * 获取 动作关联素材ID
    * @return
    */
    public java.lang.String getMaterial_id() {
        return material_id;
    }

   /**
    * 设置 动作关联素材ID
    * @param material_id
    */
    public void setMaterial_id(java.lang.String material_id) {
        this.material_id = material_id;
    }
    /**
    * 获取 客服模式
    * @return cust_srv_flag
    */
    public java.lang.String getCust_srv_flag() {
        return cust_srv_flag;
    }
    /**
    * 设置 客服模式
    * @param cust_srv_flag
    */
    public void setCust_srv_flag(java.lang.String cust_srv_flag) {
       this.cust_srv_flag = cust_srv_flag;
    }
   /**
    * 获取 动作URL（仅能用于链接）
    * @return
    */
    public java.lang.String getAction_url() {
        return action_url;
    }

   /**
    * 设置 动作URL（仅能用于链接）
    * @param action_url
    */
    public void setAction_url(java.lang.String action_url) {
        this.action_url = action_url;
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
    * 获取
    * @return
    */
    public java.lang.Long getRela_sort() {
        return rela_sort;
    }

   /**
    * 设置
    * @param rela_sort
    */
    public void setRela_sort(java.lang.Long rela_sort) {
        this.rela_sort = rela_sort;
    }
   /**
    * 获取 是否动态模版[CS:1000]
    * @return
    */
    public java.lang.String getTemplet_flag() {
        return templet_flag;
    }

   /**
    * 设置 是否动态模版[CS:1000]
    * @param templet_flag
    */
    public void setTemplet_flag(java.lang.String templet_flag) {
        this.templet_flag = templet_flag;
    }


    public String getAction_class() {
        return action_class;
    }

    public void setAction_class(String action_class) {
        this.action_class = action_class;
    }
}
