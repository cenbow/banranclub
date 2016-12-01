package com.wechat.core.beans.share;

public class ShareObject {
	
	//分享图片URL
	private String imgUrl;
	
	//分享链接
	private String lineLink;
	
	//分享标题
	private String shareTitle;
	
	//分享描述
	private String descContent;
	
	//分享Appid
	private String appid;
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getLineLink() {
		return lineLink;
	}

	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public String getDescContent() {
		return descContent;
	}

	public void setDescContent(String descContent) {
		this.descContent = descContent;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

}
