package com.platform.common.tools.opensymphony.web.context.vo;

import java.io.Serializable;
import java.util.List;

/***
 * <p>openId 有关的所有用户信息</p>
 * @author hercules.chen
 *
 */
public class OpenIdUserInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	//获取方式
	private String  scope;
	//是否拥有详细信息
	private boolean hasdetail;
	// 用户标识
	private String openId;
	// 用户昵称
	private String nickname;
	// 性别（1是男性，2是女性，0是未知）
	private int sex;
	// 国家
	private String country;
	// 省份
	private String province;
	// 城市
	private String city;
	// 用户头像链接
	private String headImgUrl;
	// 用户特权信息
	private List<String> privilegeList;

	public boolean isHasdetail() {
		return hasdetail;
	}

	public void setHasdetail(boolean hasdetail) {
		this.hasdetail = hasdetail;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public List<String> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}

}
