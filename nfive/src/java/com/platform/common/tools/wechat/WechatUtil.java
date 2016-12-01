package com.platform.common.tools.wechat;

import com.wechat.platform.dao.ITWxPlatformDao;
import com.wechat.platform.model.TWxPlatformDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/***
 * 微信公众号全局配置
 * @author jinzhihong
 */
@Service("wechatUtil")
public class WechatUtil {
    // 日志
    private static Logger logger = Logger.getLogger(WechatUtil.class);


    @Autowired
    private ITWxPlatformDao wxPlatformDao;

    private static TWxPlatformDto wxPlatformDto;  //公众号基本信息


//    /****
//     *spring中定义的初始化方法
//     */
//    @SuppressWarnings("unused")
//    public void InitConfig() {
//        refreshCacheAll();
//    }
//
//
//    /**
//     *刷新全部缓存
//     */
//    @Scheduled(fixedRate = 30*1000*60)    //测试，每30分钟执行
//    private void refreshCacheAll() {
//        logger.info("=========================== 开始初始化微信公众号配置 ===========================");
//        //微信公众号基础配置
//        TWxPlatformDto param = new TWxPlatformDto();
//        wxPlatformDto = wxPlatformDao.getRow(param);
//        //获取微信调用凭证
//        wxPlatformDto.setAccess_token(WechatApiUtil.getAccessToken(wxPlatformDto.getApp_id(), wxPlatformDto.getApp_secret()));
//        //更新wxPlatformDto
//        wxPlatformDao.updatePK(wxPlatformDto);
//        logger.info("=========================== 结束初始化微信公众号配置 ===========================");
//    }


    public static TWxPlatformDto getWxPlatform(){
        return wxPlatformDto;
    }
}
