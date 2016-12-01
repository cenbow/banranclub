package com.pub.common.tools.web.util;

import java.sql.Timestamp;

/**
 * 类功能:文件上传
 */
public class FileVo {

	private String attachsId;
	private String path;
	private String url;
	private String thumbnailUrl;
	private String name;
	private String type;
	private String size;
	private String deleteUrl;
	private String deleteType;
	private Timestamp createdDate;// 记录创建时间
	private String createdUserCd;// 创建用户
	private String remark;	// 备注

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getDeleteType() {
		return deleteType;
	}

	public void setDeleteType(String deleteType) {
		this.deleteType = deleteType;
	}

	public String getAttachsId() {
		return attachsId;
	}

	public void setAttachsId(String attachsId) {
		this.attachsId = attachsId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUserCd() {
		return createdUserCd;
	}

	public void setCreatedUserCd(String createdUserCd) {
		this.createdUserCd = createdUserCd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}