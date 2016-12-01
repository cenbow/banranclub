package com.wechat.core.handle.logic.event.mass;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.platform.common.tools.constant.CodeStringConstant;
import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.wechat.crowdsend.service.ITMsgSendResultService;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;

/**
 * 类功能:群发消息结束回调
 * <p>创建者:liyi.wang</p>
 * <p>创建时间:2014-09-24</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("massendEventHandle")
public class MassendEventHandle implements ILogicHandle {
    protected static final Log log = LogFactory.getLog(MassendEventHandle.class);
    @Autowired
	private ITMsgSendService tMsgSendService;
	@Autowired
	private ITMsgSendResultService tMsgSendResultService;
	@Autowired
	private ITPubPlatformService tPubPlatformService;
    /**
     * 群发消息结束回调事件处理
     */
    public String doHandle(Map<String, String> requestMap) {
        System.out.println("群发消息结束回调事件处理---开始");
        try {
			String status;
			//判断发送结果，赋予本地code
			if(requestMap.get("Status").contains("success")){
				status=CodeStringConstant.CS_5063_SEND_STATUS_SUCCESS;
			}else if(requestMap.get("Status").contains("fail")){
				status=CodeStringConstant.CS_5063_SEND_STATUS_FAILED;
			}else{
				status=CodeStringConstant.CS_5063_SEND_STATUS_AUDITFAILURE;
				System.out.println(requestMap.get("Status"));
			}
			//获取公众号id
			TPubPlatformDto tPubPlatformDto = new TPubPlatformDto();
			tPubPlatformDto.setOrg_id(requestMap.get("ToUserName"));
			tPubPlatformDto=tPubPlatformService.getRow(tPubPlatformDto);
	        log.info("MSGID: "+requestMap.get("MsgID")+",PLANTFORM_ID:"+tPubPlatformDto.getPlatform_id()+",status:"+requestMap.get("Status"));

			//设置待更新发送表对象查询条件
			TMsgSendQueryBean tMsgSendQueryBean = new TMsgSendQueryBean();
			tMsgSendQueryBean.setPlatform_id(tPubPlatformDto.getPlatform_id());
			tMsgSendQueryBean.setMsg_code(requestMap.get("MsgID"));
			//由于详细查询内部分信息被其他项占用显示，不能作为更新对象用
			TMsgSendDto tmpMsgSendDto = tMsgSendService.msgDetail(tMsgSendQueryBean);

			TMsgSendDto tMsgSendDto = new TMsgSendDto();
			tMsgSendDto.setMsg_id(tmpMsgSendDto.getMsg_id());
			tMsgSendDto = tMsgSendService.getRow(tMsgSendDto);

			//设置待更新发送结果表对象查询条件
			TMsgSendResultDto tmpTMsgSendResultDto = new TMsgSendResultDto();
			tmpTMsgSendResultDto.setMsg_code(requestMap.get("MsgID"));
			tmpTMsgSendResultDto.setBatch_no(tmpMsgSendDto.getBatch_no());
			tmpTMsgSendResultDto = tMsgSendResultService.getRow(tmpTMsgSendResultDto);

			//设置待更新发送表对象内容
			tMsgSendDto.setSend_status(status);
			tMsgSendDto.setSend_time(new Date(Long.valueOf(requestMap.get("CreateTime"))*1000));
			tMsgSendDto.setError_count(null==tMsgSendDto.getError_count()?0:tMsgSendDto.getError_count()+new Long(requestMap.get("ErrorCount")));
			tMsgSendDto.setFilter_count(null==tMsgSendDto.getFilter_count()?0:tMsgSendDto.getFilter_count()+new Long(requestMap.get("FilterCount")));
			tMsgSendDto.setSent_count(null == tMsgSendDto.getSent_count()?0:tMsgSendDto.getSent_count()+new Long(requestMap.get("SentCount")));
			tMsgSendDto.setTotal_count(new Long(requestMap.get("TotalCount")));

			//设置待更新发送结果对象内容
			tmpTMsgSendResultDto.setSend_status(status);
			tmpTMsgSendResultDto.setSent_count(new Long(requestMap.get("SentCount")));
			tmpTMsgSendResultDto.setTotal_count(new Long(requestMap.get("TotalCount")));
			tmpTMsgSendResultDto.setError_count(new Long(requestMap.get("ErrorCount")));
			tmpTMsgSendResultDto.setFilter_count(new Long(requestMap.get("FilterCount")));

			//更新操作
			tMsgSendService.updateTMsgSendDto(tMsgSendDto, tmpTMsgSendResultDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

        System.out.println("群发	事件处理---结束");

        return null;
    }
}
