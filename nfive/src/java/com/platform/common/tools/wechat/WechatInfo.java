package com.platform.common.tools.wechat;
import java.io.Serializable;
import java.util.List;

import com.wechat.pfcfg.model.dto.TPubPlatformDto;
/***
 * SESSION中登录用户微信上下文
 * @author jia.chen
 */
public class WechatInfo implements  Serializable{
	
	//当前公众号信息
	private  PubPlatformBean 		 curPubPlatformBean;
	
	//可访问的公众号列表信息
	private  List<PubPlatformBean>   pubPlatformBeanList = null;

	/***
	 * 初始化方法
	 * @param curPubPlatform
	 * @param pubPlatformList
	 */
	public WechatInfo(PubPlatformBean curPubPlatformBean,   List<PubPlatformBean>  pubPlatformBeanList)
	{
		this.curPubPlatformBean =curPubPlatformBean;
		this.pubPlatformBeanList =pubPlatformBeanList;
	}

	public PubPlatformBean getCurPubPlatformBean() {
		return curPubPlatformBean;
	}

	public void setCurPubPlatformBean(PubPlatformBean curPubPlatformBean) {
		this.curPubPlatformBean = curPubPlatformBean;
	}

	public List<PubPlatformBean> getPubPlatformBeanList() {
		return pubPlatformBeanList;
	}

	public void setPubPlatformBeanList(List<PubPlatformBean> pubPlatformBeanList) {
		this.pubPlatformBeanList = pubPlatformBeanList;
	}
	
	
	
	
	
	
}
