package com.wechat.core.beans.other;

/**
 * 媒体文件信息
 */
public class WeixinMedia {
    // 媒体文件类型
    private String type;
    // 媒体文件标识或缩略图的媒体文件标识
    private String mediaId;
    // 媒体文件上传的时间
    private int createdAt;
    //是否缓存(该字段用来传递从素材表中取出的是否缓存字段)
    private String cache_flag;
    //上传结果
    private boolean success;
    //错误代码
    private String errcode;
    //错误消息
    private String errmsg;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getCache_flag() {
        return cache_flag;
    }

    public void setCache_flag(String cacheFlag) {
        cache_flag = cacheFlag;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
