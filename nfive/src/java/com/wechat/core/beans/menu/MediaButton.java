package com.wechat.core.beans.menu;

/**
 * Created by jinzhihong on 16/11/19.
 */
public class MediaButton extends Button {
    private String type;
    private String media_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
