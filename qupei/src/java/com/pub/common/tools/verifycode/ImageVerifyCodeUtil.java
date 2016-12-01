package com.pub.common.tools.verifycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * 图形验证码生成工具
 * 
 * @author wen.zhang
 */
public class ImageVerifyCodeUtil {	

	private static Logger logger = Logger.getLogger(ImageVerifyCodeUtil.class);

	/** 随机产生的字符串 */
	private final static String RANDOM_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/** 字符个数 */
	private final static int IMAGE_LENGTH = 5;
	/** 图片宽 */
	private final static int IMAGE_WIDTH = 100;
	/** 图片高 */
	private final static int IMAGE_HEIGHT = 25;
	/** 图片线条 */
	private final static int IMAGE_LINE = 6;

	/**
	 * 获取验证码（字符串）
	 */
	public static String getVerifyCode() {
		StringBuffer verifyCode = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < IMAGE_LENGTH; i++) {
			String rand = String.valueOf(_getRandomString(random.nextInt(RANDOM_STRING.length())));
			verifyCode.append(rand);
		}

		return verifyCode.toString();
	}

	/**
	 * 获取验证码（流格式）
	 * 
	 * @param verifyCode 验证码
	 * @return ByteArrayInputStream 图片流
	 */
	public static ByteArrayInputStream getImageAsInputStream(String verifyCode) {
		BufferedImage image = generateImageCode(verifyCode, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_LINE, true, Color.WHITE, Color.BLACK, null);
		return convertImageToStream(image);
	}

	/**
	 * 将BufferedImage转换成ByteArrayInputStream
	 * 
	 * @param image 图片
	 * @return ByteArrayInputStream 流
	 */
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		/**
		 * 解决com.sun.image.codec.jpeg为sun内部使用，并且与Win平台相关的问题
		 */
//		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
//		try {
//			jpeg.encode(image);
//			byte[] bts = bos.toByteArray();
//			inputStream = new ByteArrayInputStream(bts);
//		} catch (ImageFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		try {
			ImageIO.write(image, "JPEG", bos);
			byte[] bts = bos.toByteArray();
			inputStream = new ByteArrayInputStream(bts);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}

	/**
	 * 生成验证码图片
	 * 
	 * @return
	 */
	private static BufferedImage generateImageCode(String textCode, int width, int height, int interLine, boolean randomLocation, Color backColor, Color foreColor, Color lineColor) {
		// 创建内存图像
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(null == backColor ? generateRandomColor() : backColor);
		graphics.fillRect(0, 0, width, height);
		// 画干扰线
		Random random = new Random();
		if (interLine > 0) {
			int x = 0, y = 0, x1 = width, y1 = 0;
			for (int i = 0; i < interLine; i++) {
				graphics.setColor(null == lineColor ? generateRandomColor() : lineColor);
				y = random.nextInt(height);
				y1 = random.nextInt(height);
				graphics.drawLine(x, y, x1, y1);
			}
		}
		// 设定字体
		int fsize = (int) (height * 0.8);
		int fx = height - fsize;
		int fy = fsize;
		graphics.setFont(new Font("Default", Font.PLAIN, fsize));
		for (int i = 0; i < textCode.length(); i++) {
			fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;
			graphics.setColor(null == foreColor ? generateRandomColor() : foreColor);
			graphics.drawString(textCode.charAt(i) + "", fx, fy);
			fx += fsize * 0.9;
		}
		graphics.dispose();
		return bufferedImage;
	}

	/**
	 * 生成随机颜色
	 */
	private static Color generateRandomColor() {
		Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	/**
	 * 获取随机的字符
	 */
	private static String _getRandomString(int num) {
		return String.valueOf(RANDOM_STRING.charAt(num));
	}

}
