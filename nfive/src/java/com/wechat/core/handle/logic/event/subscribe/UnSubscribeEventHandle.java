package com.wechat.core.handle.logic.event.subscribe;
import java.util.Map;

import com.wechat.fans.service.ITWxFansService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.wechat.core.handle.logic.ILogicHandle;
import com.wechat.replycfg.service.ITReplySpecialService;

@Scope("prototype")
@Service("unSubscribeEventHandle")
public class UnSubscribeEventHandle implements ILogicHandle{

	protected static final Log log =  LogFactory.getLog(UnSubscribeEventHandle.class);

	@Autowired
	private ITReplySpecialService tReplySpecialService;
    @Autowired
    private ITWxFansService wxFansService;

    /**
     * 取消关注订阅事件处理
     */
	public String doHandle(Map<String, String> requestMap) {
        // 接收方帐号（收到的OpenID） (需调换位置)
        String toUserName = requestMap.get("FromUserName");
        try {
            //微信粉丝取消关注
            wxFansService.unSubscribe(toUserName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
		return null;
	}
}

