package com.wechat.material.datasync;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.wechat.WechatApiUtil;
import com.wechat.material.dao.ITWxNewsDao;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.service.ITWxNewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * 同步微信图文素材
 * Created by jinzhihong on 16/11/18.
 */
public class NewsSyncUtil implements Runnable {

    public static int newscount=-1;  //需要同步的记录总数
    public static int syncprogress=-1; //已同步的记录数

    public void run() {
        Log log = LogFactory.getLog(NewsSyncUtil.class);
        log.info("图文素材同步数据开始...");
        syncprogress = 0;
        newscount = 0;
        try {
            ITWxNewsService wxNewsService = SpringContextUtil.getApplicationContext().getBean(ITWxNewsService.class);
            ITWxNewsDao wxNewsDao = SpringContextUtil.getApplicationContext().getBean(ITWxNewsDao.class);
            String update_batch = wxNewsDao.newBatchNo();
            List<TWxNewsDto> wxNewsDtos= WechatApiUtil.getMeterialNews();
            newscount = wxNewsDtos.size(); //总数
            for(TWxNewsDto wxNewsDto : wxNewsDtos){
                wxNewsDto.setUpdate_batch(update_batch); //设置批次号
                wxNewsService.saveOrUpdateWxNews(wxNewsDto);
                syncprogress++;
//                Thread.sleep(10000); //测试休眠
            }
            //更新未拉取到的素材并删除
            wxNewsDao.deleteWxNews(update_batch);
        }catch (Exception ex){
            log.error("图文素材同步失败",ex);
        }
        newscount=-1;
        syncprogress=-1;
        log.info("图文素材数据同步结束...");
    }
}
