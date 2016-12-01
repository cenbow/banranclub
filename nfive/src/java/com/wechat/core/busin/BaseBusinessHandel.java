package com.wechat.core.busin;


/**
 * 业务处理抽象类
 * Created by jinzhihong on 16/2/3.
 */
public abstract class BaseBusinessHandel {

    private String platform_id;
    private String toUserName;
    /**
     * 返回回复字符串
     * @return
     */
    public abstract String business();

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
}
