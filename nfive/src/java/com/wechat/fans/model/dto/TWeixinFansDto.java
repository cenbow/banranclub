package com.wechat.fans.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:粉丝
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-16</p>
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
@EntityPK(Pk = "fans_id", defaultColumn = false, tableName = "T_WEIXIN_FANS")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWeixinFansDto  extends BaseDtoImpl{//
	private java.lang.String fans_id;//粉丝ID
	private java.lang.String subscribe;//是否订阅[CS:1000]
	private java.lang.String openid;//OPENID
	private java.lang.String nick_name;//昵称
	private java.lang.String sex;//性别[CS:1015]
	private java.lang.String remark_name;//备注名
	private java.lang.String country;//国家
	private java.lang.String province;//省份
	private java.lang.String city;//城市
	private java.lang.String language;//语言
	private java.lang.String head_img_url;//用户头像
    private java.util.Date subscribe_time;//关注时间
	private java.lang.String unionid;//开放平台统一ID
	private java.lang.Long group_id;//组ID
	private java.lang.String weixin_group_id;//微信分组ID
	private java.lang.String platform_id;//公众平台号ID
	private java.lang.String remark;//备注

   /**
	* 获取 粉丝ID
	* @return
	*/
	public java.lang.String getFans_id() {
		return fans_id;
	}
	
   /**
	* 设置 粉丝ID
	* @param fans_id
	*/
 	public void setFans_id(java.lang.String fans_id) {
		this.fans_id = fans_id;
	}
   /**
	* 获取 是否订阅[CS:1000]
	* @return
	*/
	public java.lang.String getSubscribe() {
		return subscribe;
	}
	
   /**
	* 设置 是否订阅[CS:1000]
	* @param subscribe
	*/
 	public void setSubscribe(java.lang.String subscribe) {
		this.subscribe = subscribe;
	}
   /**
	* 获取 OPENID
	* @return
	*/
	public java.lang.String getOpenid() {
		return openid;
	}
	
   /**
	* 设置 OPENID
	* @param openid
	*/
 	public void setOpenid(java.lang.String openid) {
		this.openid = openid;
	}
   /**
	* 获取 昵称
	* @return
	*/
	public java.lang.String getNick_name() {
		return nick_name;
	}
	
   /**
	* 设置 昵称
	* @param nick_name
	*/
 	public void setNick_name(java.lang.String nick_name) {
		this.nick_name = nick_name;
	}
   /**
	* 获取 性别[CS:1015]
	* @return
	*/
	public java.lang.String getSex() {
		return sex;
	}
	
   /**
	* 设置 性别[CS:1015]
	* @param sex
	*/
 	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
   /**
	* 获取 备注名
	* @return
	*/
	public java.lang.String getRemark_name() {
		return remark_name;
	}
	
   /**
	* 设置 备注名
	* @param remark_name
	*/
 	public void setRemark_name(java.lang.String remark_name) {
		this.remark_name = remark_name;
	}
   /**
	* 获取 国家
	* @return
	*/
	public java.lang.String getCountry() {
		return country;
	}
	
   /**
	* 设置 国家
	* @param country
	*/
 	public void setCountry(java.lang.String country) {
		this.country = country;
	}
   /**
	* 获取 省份
	* @return
	*/
	public java.lang.String getProvince() {
		return province;
	}
	
   /**
	* 设置 省份
	* @param province
	*/
 	public void setProvince(java.lang.String province) {
		this.province = province;
	}
   /**
	* 获取 城市
	* @return
	*/
	public java.lang.String getCity() {
		return city;
	}
	
   /**
	* 设置 城市
	* @param city
	*/
 	public void setCity(java.lang.String city) {
		this.city = city;
	}
   /**
	* 获取 语言
	* @return
	*/
	public java.lang.String getLanguage() {
		return language;
	}
	
   /**
	* 设置 语言
	* @param language
	*/
 	public void setLanguage(java.lang.String language) {
		this.language = language;
	}
   /**
	* 获取 用户头像
	* @return
	*/
	public java.lang.String getHead_img_url() {
		return head_img_url;
	}
	
   /**
	* 设置 用户头像
	* @param head_img_url
	*/
 	public void setHead_img_url(java.lang.String head_img_url) {
		this.head_img_url = head_img_url;
	}
   /**
	* 获取 关注时间
	* @return
	*/
	public java.util.Date getSubscribe_time() {
		return subscribe_time;
	}
	
   /**
	* 设置 关注时间
	* @param subscribe_time
	*/
 	public void setSubscribe_time(java.util.Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
   /**
	* 获取 开放平台统一ID
	* @return
	*/
	public java.lang.String getUnionid() {
		return unionid;
	}
	
   /**
	* 设置 开放平台统一ID
	* @param unionid
	*/
 	public void setUnionid(java.lang.String unionid) {
		this.unionid = unionid;
	}
   /**
	* 获取 组ID
	* @return
	*/
	public java.lang.Long getGroup_id() {
		return group_id;
	}
	
   /**
	* 设置 组ID
	* @param group_id
	*/
 	public void setGroup_id(java.lang.Long group_id) {
		this.group_id = group_id;
	}
   /**
	* 获取 微信分组ID
	* @return
	*/
	public java.lang.String getWeixin_group_id() {
		return weixin_group_id;
	}
	
   /**
	* 设置 微信分组ID
	* @param weixin_group_id
	*/
 	public void setWeixin_group_id(java.lang.String weixin_group_id) {
		this.weixin_group_id = weixin_group_id;
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
	* 获取 备注
	* @return
	*/
	public java.lang.String getRemark() {
		return remark;
	}
	
   /**
	* 设置 备注
	* @param remark
	*/
 	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
}
