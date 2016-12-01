package com.wechat.core.handle.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.platform.common.tools.wechat.WechatUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.hercules.factory.SpringContextUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.handle.service.MainHandleService;
import com.wechat.core.utils.SignUtil;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;

/***
 * 微信处理的核心类型
 *
 * @author hercules.chen
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	private static Logger logger = Logger.getLogger(CoreServlet.class);

	/**
	 * 请求校验（确认请求来自微信服务器）
	 */
	// throws ServletException, IOException
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");

		//2.检查与校验签名是否成功
		if (!SignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.error("check signature is failured!");
			return;
		}

		//若校验成功则原样返回echostr，表示接入成功，否则接入失败
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(echostr);
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
		}

	}

	/**
	 * 请求校验与处理
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 接收参数微信加密签名、 时间戳、随机数 、公众号
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");


		PrintWriter out = null;
		try {

			out = response.getWriter();
			// 请求校验
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				// 调用核心服务类接收处理请求
				MainHandleService  mainHandleService	= (MainHandleService)SpringContextUtil.getBean("mainHandleService");
				String respXml = mainHandleService.processRequest(request);
				//如果返回null值，为了避免微信客服不认可的返回值，暂修改为空字符串
				if (StringUtils.isEmpty(respXml)){
					respXml = "";
				}
                logger.info(respXml);
				out.print(respXml);
			}
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}
}
