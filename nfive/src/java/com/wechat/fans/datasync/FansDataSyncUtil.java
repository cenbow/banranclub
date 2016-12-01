package com.wechat.fans.datasync;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.core.beans.other.WeixinGroup;
import com.wechat.core.beans.other.WeixinUserInfo;
import com.wechat.core.beans.other.WeixinUserList;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITFansSyncHistoryService;

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

public class FansDataSyncUtil implements Runnable {

    private String accessToken;
    private String created_user_cd;
    private String updated_user_cd;
    private String platform_id;
	private TFansSyncHistoryDto tmpFansSyncHistoryDto;


    public FansDataSyncUtil(String accessToken, String created_user_cd, String updated_user_cd, String platform_id, TFansSyncHistoryDto tmpFansSyncHistoryDto) {
        this.accessToken = accessToken;
        this.created_user_cd = created_user_cd;
        this.updated_user_cd = updated_user_cd;
        this.platform_id = platform_id;
        this.tmpFansSyncHistoryDto = tmpFansSyncHistoryDto;
    }

    public void run() {
        Log log = LogFactory.getLog(FansDataSyncUtil.class);
        log.info("粉丝数据同步开始...");

        ITFansSyncHistoryService tFansSyncHistoryService = SpringContextUtil.getApplicationContext().getBean(ITFansSyncHistoryService.class);
  
        try {
            //取得分组
            List<TWeixinGroupDto> weixinGroups = transGroup(accessToken);
            //取得粉丝
            List<TWeixinFansDto>  weixinFans = transFans(accessToken);
            //更新数据库
            String result = tFansSyncHistoryService.updateFansAndGroup(weixinGroups, weixinFans,created_user_cd, updated_user_cd,platform_id);
            //更新同步状态
            if(result != null){
                tmpFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_SUCCESS);
                tmpFansSyncHistoryDto.setSync_end_date(new Date());
                tmpFansSyncHistoryDto.setRemark(result);
                tFansSyncHistoryService.updatePK(tmpFansSyncHistoryDto);
            }

            log.info("粉丝数据同步正常结束");
        } catch (Exception e) {
            //更新同步状态
            tmpFansSyncHistoryDto.setSync_state(CodeStringConstant.CS_5065_SYNC_ERROR);
            tmpFansSyncHistoryDto.setSync_end_date(new Date());
            tmpFansSyncHistoryDto.setRemark(e.getMessage());
            tFansSyncHistoryService.updatePK(tmpFansSyncHistoryDto);

            log.info("粉丝数据同步异常结束" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 转换微信粉丝
     *
     *@param accessToken 微信服务器访问token
     * @return List<TWeixinFansDto>
     * @throws Exception
     */
    private List<TWeixinFansDto> transFans(String accessToken) throws Exception {

        List<TWeixinFansDto> weixinFans = new ArrayList<TWeixinFansDto>();
        List<String> allOpenIds = new ArrayList<String>();

        //获取关注者列表
        WeixinUserList weixinUserList = AdvancedUtil.getUserList(accessToken, null);
        if (weixinUserList != null && weixinUserList.getOpenIdList() != null) {
            allOpenIds.addAll(weixinUserList.getOpenIdList());
        }
        while (weixinUserList != null && weixinUserList.getCount() == 10000) {
            //count是拉取的OPENID个数，最大值为10000。count为10000，说明可能还需要再拉取
            weixinUserList = AdvancedUtil.getUserList(accessToken, weixinUserList.getNextOpenId());
            if (weixinUserList != null && weixinUserList.getOpenIdList() != null) {
                allOpenIds.addAll(weixinUserList.getOpenIdList());
            }
        }

        //获取用户基本信息（包括UnionID机制）
        for (String openId : allOpenIds) {
            WeixinUserInfo weixinUserInfo = AdvancedUtil.getUserInfo(accessToken, openId);

            if (weixinUserInfo != null && weixinUserInfo.getSubscribe() != 0) {
                 //取消关注的不再返回
                TWeixinFansDto weixinFansDto = new TWeixinFansDto();

                weixinFansDto.setSubscribe(CodeStringConstant.CS_1000_TRUE);
                weixinFansDto.setOpenid(weixinUserInfo.getOpenId());
                weixinFansDto.setUnionid(weixinUserInfo.getUnionId());
                weixinFansDto.setNick_name(weixinUserInfo.getNickname());
                if (weixinUserInfo.getSex() == 1) {
                    //男
                    weixinFansDto.setSex(CodeStringConstant.CS_1015_SEX_FLAG_MALE);
                } else if (weixinUserInfo.getSex() == 2) {
                    //女
                    weixinFansDto.setSex(CodeStringConstant.CS_1015_SEX_FLAG_FEMALE);
                } else {
                    weixinFansDto.setSex(CodeStringConstant.CS_1015_SEX_FLAG_UNKNOWN);
                }
                weixinFansDto.setCountry(weixinUserInfo.getCountry());
                weixinFansDto.setProvince(weixinUserInfo.getProvince());
                weixinFansDto.setCity(weixinUserInfo.getCity());
                weixinFansDto.setLanguage(weixinUserInfo.getLanguage());
                weixinFansDto.setHead_img_url(weixinUserInfo.getHeadImgUrl());
                BigInteger big = new BigInteger(weixinUserInfo.getSubscribeTime()).multiply(new BigInteger("1000"));
                weixinFansDto.setSubscribe_time(new Date(big.longValue()));

                //查询用户所在分组
                //int groupId = AdvancedUtil.getGroupIdByUser(accessToken, openId);
                //weixinFansDto.setGroup_id(Long.valueOf(groupId));

                weixinFans.add(weixinFansDto);
            }
        }

        return weixinFans;
    }

    /**
     * 转换微信分组
     *
     *@param accessToken 微信服务器访问token
     * @return List<TWeixinGroupDto>
     * @throws Exception
     */
    private List<TWeixinGroupDto> transGroup(String accessToken) throws Exception {
        List<TWeixinGroupDto> weixinGroupDtos = new ArrayList<TWeixinGroupDto>();
        List<WeixinGroup> weixinGroups = AdvancedUtil.getGroups(accessToken);
        if (weixinGroups != null) {
            for (WeixinGroup weixinGroup : weixinGroups) {
                TWeixinGroupDto weixinGroupDto = new TWeixinGroupDto();
                weixinGroupDto.setGroup_id(Long.valueOf(weixinGroup.getId()));
                weixinGroupDto.setGroup_name(weixinGroup.getName());

                weixinGroupDtos.add(weixinGroupDto);
            }
        }

        return weixinGroupDtos;
    }

}


