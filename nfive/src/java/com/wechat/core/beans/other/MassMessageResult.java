package com.wechat.core.beans.other;

/**
 * 类功能:高级群发接口结果对象
 *
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-24</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

public class MassMessageResult {
    private String errcode; //错误码
    private String errmsg; //错误信息
    private String msg_id; //消息ID

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
    public String getMsg_id() {
        return msg_id;
    }
    public void setMsg_id(String msgId) {
        msg_id = msgId;
    }
}
