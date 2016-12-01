package com.pub.common.tools.web.util;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.cache.CacheUtil;
import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.common.tools.opensymphony.web.context.UserContext;
import com.pub.common.tools.verifycode.CacheKeyUtil;
import com.pub.common.tools.verifycode.ImageVerifyCodeUtil;

/**
 * 类功能:获取图形验证码
 * 
 * @author wen.zhang
 */
@Controller("imageVerifyCodeAction")
@Scope("prototype")
public class ImageVerifyCodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ImageVerifyCodeAction.class);

	/** 图片输出流 */
	private ByteArrayInputStream imageStream;

	/** 验证码失效时间（秒） */
	private final static int VERIFY_CODE_TIME_SECOND = 120;

	/**
	 * 生成图形验证码
	 * 
	 * <!-- 图形验证码 -->
	 * <img title="点击刷新" id="validRefresh" src="imageVerifyCode.action">
	 * 
	 * // 刷新图片验证码
	 * $("#validRefresh").click(function(){
	 *     $("#validRefresh").attr("src", "imageVerifyCode.action?time=" + Math.random());
	 * });
	 * 
	 * @throws Exception
	 */
	public String execute() throws Exception {
		try {
			// 图形验证码
			String verifyCode = ImageVerifyCodeUtil.getVerifyCode();
			imageStream = ImageVerifyCodeUtil.getImageAsInputStream(verifyCode);

			// 图像验证码CacheKey
			HttpSession session = UserContext.getRequest().getSession();
			String cacheKey = CacheKeyUtil.getAccountImageVerifyCodeKey(session.getId());

			// 放入缓存
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, VERIFY_CODE_TIME_SECOND);
			CacheUtil.put(cacheKey, verifyCode, calendar.getTime());

			logger.info("图形验证码  CacheKey：" + cacheKey + ", VerifyCode：" + verifyCode);
		} catch (Exception e) {
			logger.error("获取验证码失败!");
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

}

