package com.wechat.fans.datasync;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.fans.model.dto.TLatestfansSyncHistoryDto;
import com.wechat.fans.service.ITLatestFansService;
import com.wechat.fans.service.ITLatestfansSyncHistoryService;

/**
 * 类功能:将微信粉丝数据同步到本地数据库
 *
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

public class LatestFansDataSyncUtil implements Runnable {

    private List<String> openIdList;
    private String created_user_cd;
    private String updated_user_cd;
    private String platform_id;
	private TLatestfansSyncHistoryDto tmpFansSyncHistoryDto;


    public LatestFansDataSyncUtil(List<String> openIdList, String created_user_cd, String updated_user_cd, String platform_id, TLatestfansSyncHistoryDto tmpFansSyncHistoryDto) {
        this.openIdList = openIdList;
        this.created_user_cd = created_user_cd;
        this.updated_user_cd = updated_user_cd;
        this.platform_id = platform_id;
        this.tmpFansSyncHistoryDto = tmpFansSyncHistoryDto;
    }

    public void run() {
        Log log = LogFactory.getLog(LatestFansDataSyncUtil.class);
        log.info("最新关注粉丝数据同步开始...");

        ITLatestfansSyncHistoryService latestfansSyncHistoryService = SpringContextUtil.getApplicationContext().getBean(ITLatestfansSyncHistoryService.class);
        ITLatestFansService latestFansService = SpringContextUtil.getApplicationContext().getBean(ITLatestFansService.class);
  
        try {
            //更新数据库
            String result = latestFansService.batchInsertLatestFans(openIdList,created_user_cd, updated_user_cd,platform_id);
            
            
            //更新同步状态
            if("001".equals(result)){
                tmpFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_SUCCESS);
                tmpFansSyncHistoryDto.setSync_end_date(new Date());
                tmpFansSyncHistoryDto.setRemark(result);
                latestfansSyncHistoryService.updatePK(tmpFansSyncHistoryDto);
            }

            log.info("最新关注粉丝数据同步正常结束");
        } catch (Exception e) {
            //更新同步状态
            tmpFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ERROR);
            tmpFansSyncHistoryDto.setSync_end_date(new Date());
            tmpFansSyncHistoryDto.setRemark(e.getMessage());
            latestfansSyncHistoryService.updatePK(tmpFansSyncHistoryDto);

            log.info("最新关注粉丝数据同步异常结束" + e.getMessage());
            e.printStackTrace();
        }
    }

}


