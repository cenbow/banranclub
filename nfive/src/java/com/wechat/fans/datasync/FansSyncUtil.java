package com.wechat.fans.datasync;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.wechat.WechatApiUtil;
import com.wechat.core.beans.other.WeixinUserList;
import com.wechat.fans.service.ITWxFansService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 粉丝同步线程
 * Created by jinzhihong on 16/11/17.
 */
public class FansSyncUtil implements Runnable {

    public static int fanscount=-1;  //需要同步的记录总数
    public static int syncprogress=-1; //已同步的记录数

    public void run() {
        Log log = LogFactory.getLog(FansDataSyncUtil.class);
        log.info("粉丝数据同步开始...");
        syncprogress = 0;
        fanscount = 0;
        try {
            ITWxFansService wxFansService = SpringContextUtil.getApplicationContext().getBean(ITWxFansService.class);
            WeixinUserList weixinUserList = WechatApiUtil.getSubscribeFans("");
            fanscount = weixinUserList.getCount();
            //拉取用户大于0时
            while (weixinUserList.getCount()>0){
                log.info("粉丝数据同步 记录数:"+weixinUserList.getCount());
                for(String openid:weixinUserList.getOpenIdList()){
                    wxFansService.saveOrUpdateWxFans(openid);
                    syncprogress++;
//                    Thread.sleep(30000); //测试休眠
                }
                weixinUserList =  WechatApiUtil.getSubscribeFans(weixinUserList.getNextOpenId());
            }
        }catch (Exception ex){
            log.error("粉丝同步失败",ex);
        }
        fanscount=-1;
        syncprogress=-1;
        log.info("粉丝数据同步结束...");
    }
}
