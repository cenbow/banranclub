package com.wechat.fans.model;
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
@EntityPK(Pk = "fans_id", defaultColumn = false, tableName = "T_WX_FANS")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWxFansDto  extends BaseDtoImpl{//
	private String fans_id;//粉丝ID
	private String openid;//OPENID
	private String nickname;//昵称
	private String subscribe;//是否关注【1000】
	private String sex;//性别【1015】
	private String language;//语言
	private String city;//城市
	private String province;//省份
	private String country;//国家
	private String headimgurl;//头像地址
    private Date subscribe_time;//关注时间
	private String unionid;//unionid
	private String remark;//备注

   /**
	* 获取 粉丝ID
	* @return
	*/
	public String getFans_id() {
		return fans_id;
	}

   /**
	* 设置 粉丝ID
	* @param fans_id
	*/
 	public void setFans_id(String fans_id) {
		this.fans_id = fans_id;
	}
   /**
	* 获取 OPENID
	* @return
	*/
	public String getOpenid() {
		return openid;
	}

   /**
	* 设置 OPENID
	* @param openid
	*/
 	public void setOpenid(String openid) {
		this.openid = openid;
	}
   /**
	* 获取 昵称
	* @return
	*/
	public String getNickname() {
		return nickname;
	}

   /**
	* 设置 昵称
	* @param nickname
	*/
 	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
   /**
	* 获取 是否关注【1000】
	* @return
	*/
	public String getSubscribe() {
		return subscribe;
	}

   /**
	* 设置 是否关注【1000】
	* @param subscribe
	*/
 	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
   /**
	* 获取 性别【1015】
	* @return
	*/
	public String getSex() {
		return sex;
	}

   /**
	* 设置 性别【1015】
	* @param sex
	*/
 	public void setSex(String sex) {
		this.sex = sex;
	}
   /**
	* 获取 语言
	* @return
	*/
	public String getLanguage() {
		return language;
	}

   /**
	* 设置 语言
	* @param language
	*/
 	public void setLanguage(String language) {
		this.language = language;
	}
   /**
	* 获取 城市
	* @return
	*/
	public String getCity() {
		return city;
	}

   /**
	* 设置 城市
	* @param city
	*/
 	public void setCity(String city) {
		this.city = city;
	}
   /**
	* 获取 省份
	* @return
	*/
	public String getProvince() {
		return province;
	}

   /**
	* 设置 省份
	* @param province
	*/
 	public void setProvince(String province) {
		this.province = province;
	}
   /**
	* 获取 国家
	* @return
	*/
	public String getCountry() {
		return country;
	}

   /**
	* 设置 国家
	* @param country
	*/
 	public void setCountry(String country) {
		this.country = country;
	}
   /**
	* 获取 头像地址
	* @return
	*/
	public String getHeadimgurl() {
		return headimgurl;
	}

   /**
	* 设置 头像地址
	* @param headimgurl
	*/
 	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
   /**
	* 获取 关注时间
	* @return
	*/
	public Date getSubscribe_time() {
		return subscribe_time;
	}

   /**
	* 设置 关注时间
	* @param subscribe_time
	*/
 	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
   /**
	* 获取 unionid
	* @return
	*/
	public String getUnionid() {
		return unionid;
	}

   /**
	* 设置 unionid
	* @param unionid
	*/
 	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
   /**
	* 获取 备注
	* @return
	*/
	public String getRemark() {
		return remark;
	}

   /**
	* 设置 备注
	* @param remark
	*/
 	public void setRemark(String remark) {
		this.remark = remark;
	}
}
