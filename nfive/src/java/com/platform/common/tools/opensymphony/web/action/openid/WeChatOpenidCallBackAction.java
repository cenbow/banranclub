package com.platform.common.tools.opensymphony.web.action.openid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.WeChatOpenIdKeyConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.utils.CommonUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;

/**
 * <p>创建者:陈佳</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("weChatOpenidCallBackAction")
@Scope("prototype")
public class WeChatOpenidCallBackAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UtilConfig utilConfig;
	private String  redirect_uri;	//获取传递过来的跳转链接
	private String  scope; //获取传递模式
	private String  state; // 获取重定位判断标识
	
	public String execute() throws Exception {
	    try {
		//获取客户服务（唯一）公众号信息
		TPubPlatformDto tPubPlatformDto =WechatInfoUtil.getTPubPlatformDtoByPlatformId(utilConfig.getVip_platform_id());
		redirect_uri = (String)request.getAttribute("redirect_uri");
		scope = (String)request.getAttribute("scope");
		state = (String)request.getAttribute(WeChatOpenIdKeyConstant.PARAM_OPENID_STATE);
		//授权URL oauth2_url
		String oauth2_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				    + tPubPlatformDto.getAppid()
					+ "&redirect_uri="
					+ CommonUtil.urlEncodeUTF8(redirect_uri)
					+ "&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
			System.out.println(oauth2_url);
			this.getResponse().sendRedirect(oauth2_url);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirectUri) {
		redirect_uri = redirectUri;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	
	
}
