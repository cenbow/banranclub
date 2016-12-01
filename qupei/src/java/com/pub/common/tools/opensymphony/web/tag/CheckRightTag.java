package com.pub.common.tools.opensymphony.web.tag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pub.common.tools.permission.LoginUserInfoUtil;




/**
 * 自定义权限标签,根据用户登录时存放的权限来判断标签中的内容是否显示
 * @author zhangzhiqiang
 *
 */
public class CheckRightTag extends TagSupport {
	private static final long serialVersionUID = 6207684887683382979L;
	//日志记录器
	protected static final Log log = LogFactory.getLog(CheckRightTag.class);    
	//资源编码
	private String resCode;
	//资源地址
	private String url;
	
	public int doStartTag() throws JspException
    { 
		//是否开启权限校验
		if(!LoginUserInfoUtil.CHECK_PERMISION)
		{
			//显示标签内部的内容
			return TagSupport.EVAL_BODY_INCLUDE;
		}
		HttpServletRequest  request =(HttpServletRequest)this.pageContext.getRequest();
		HttpSession session = request.getSession();
		//登录验证
		if(LoginUserInfoUtil.isLogin())
		{
			log.info("用户未登录，校验权限失败");
			//隐藏标签内部的内容
			return TagSupport.SKIP_BODY;
		}
		//编码判断
		if(resCode == null || resCode.trim().length() <= 0)
		{
			log.info("编码未传入，校验权限失败");
			return TagSupport.SKIP_BODY;
		}
		
//		//判断session员工号里，是否有绑定的当前作用的公众号
//		if(WechatInfoUtil.getCurPubPlatformBeanFromSession()!=null)
//		{//StringUtils.isNotBlank( .getWechart_account()
//			if(StringUtils.isNotBlank(WechatInfoUtil.getCurPubPlatformBeanFromSession().getWechart_account()))
//			{
//				request.setAttribute("wechartAccount",WechatInfoUtil.getCurPubPlatformBeanFromSession().getWechart_account());
//			}
//			else
//			{
//				request.setAttribute("wechartAccount","无绑定公众号");
//			}
//		}
//		else
//		{
//			request.setAttribute("wechartAccount","无绑定公众号");
//		}
//		//判断session员工号里，是否有绑定的当前切换的公众号
//		if(WechatInfoUtil.getPubPlatformBeanListFromSession().size()>1)
//		{	//所有已绑定的公众号LIST
//			List<PubPlatformBean> pubPlatformBeanList =WechatInfoUtil.getPubPlatformBeanListFromSession();
//			//去除当前默认绑定并当前使用的所有公众号
//			List<PubPlatformBean> platformBeanList= new ArrayList<PubPlatformBean>();
//			PubPlatformBean pubPlatformBean = new PubPlatformBean();
//			for(PubPlatformBean  tmp_pubPlatformBean:pubPlatformBeanList)
//			{
//				if(!(tmp_pubPlatformBean.getWechart_account().equals(WechatInfoUtil.getCurPubPlatformBeanFromSession().getWechart_account())))
//				{	
//					pubPlatformBean =  tmp_pubPlatformBean;
//					platformBeanList.add(pubPlatformBean);
//				}
//			}
//			request.setAttribute("pubWechartAccount",platformBeanList);
//		}
//		else
//		{
//			request.setAttribute("pubWechartAccount","");
//		}
		//导航拦上面的登录用户名
		request.setAttribute("loginUser",LoginUserInfoUtil.getLoginUser().getUser_name());
		
		String[] resCodeArray = resCode.split(",");
		for(String resCode_ : resCodeArray){
			//权限校验
			if(!LoginUserInfoUtil.checkRightByResCode(resCode))
			{
				log.info("登录的用户"+LoginUserInfoUtil.getLoginUserCD()+"对资源["+resCode_+"]没有权限!");
				return TagSupport.SKIP_BODY;
			}
		}

		
		log.info("登录的用户"+LoginUserInfoUtil.getLoginUserCD()+"对资源["+resCode+"]的权限校验通过");
		
		return TagSupport.EVAL_BODY_INCLUDE;
    }
	

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
	
}
