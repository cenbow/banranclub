package com.pub.common.tools.util;

import com.pub.common.local.model.dto.TCommonAttachsDto;
import com.pub.common.tools.properties.UtilConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/***
 * 文件上传工具类
 *
 * @author wen.zhang
 */
@Service("fileUploadUtil")
public class FileUploadUtil {
	@Autowired
	private UtilConfig utilConfig;

	private static Logger logger = Logger.getLogger(FileUploadUtil.class);
	public static String pub_file_path = null; // 永久文件存储地址
	public static String tmp_file_path = null; // 临时文件存储地址
	public static String ftp_file_path = null; // 远程文件存储地址

	/****
	 *spring中定义的初始化方法 用于初始化读取配置
	 */
	public void InitConfig() {
		pub_file_path = utilConfig.getPub_file_path();// 永久文件存储地址
		tmp_file_path = utilConfig.getTmp_file_path();// 临时文件存储地址
		ftp_file_path = utilConfig.getFtp_storage_path();// 远程文件存储地址
	}

	/***
	 * 获取保存文件的相对路径
	 *
	 * @param filetype
	 * @return String 文件路径
	 */
	private static String getPubStorageRelativePath(String functionCode) {
		String subPath = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).replaceAll("-", "");
		return functionCode + File.separator + subPath + File.separator;
	}

	/**
	 * 把Struts临时文件拷贝到Storage临时目录
	 *
	 * @param uploadFile Struts临时文件
	 * @throws IOException
	 */
	public static File copyFileByStrutsToTemp(File uploadFile) throws IOException {

		long fileSize = uploadFile.length();
		File destFile = null;

		if (fileSize >= 0) {
			// 准备上传文件需要存储的目录
			String storePath = getPubStorageRelativePath("struts");
			File fileDir = new File(tmp_file_path + File.separator + storePath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();// 若不存在则创建目录
			}

			String destFileName = "file_" + UUID.randomUUID().toString(); // 归档自动生成的存储名称

			try {
				// 把Struts临时文件Copy到临时目录
				destFile = new File(tmp_file_path + File.separator + storePath + File.separator + destFileName);
				FileUtils.copyFile(uploadFile, destFile);
				FileUtils.deleteQuietly(uploadFile);
				//System.out.println("have copy [" + uploadFile.getAbsoluteFile() + "]file to [" + destFile.getAbsolutePath() + "]");
			} catch (IOException e) {
				logger.error("[" + uploadFile.getAbsoluteFile() + "]save to [" + "] is failed", e);
				throw e;
			}
		}

		return destFile;
	}

	/**
	 * 提供临时文件的规范保存（通用附件）
	 *
	 * @param tmpFile临时文件
	 * @param orgname原文件名称
	 * @return TCommonAttachsDto
	 * @throws IOException
	 */
	public static TCommonAttachsDto saveUploadByTmpFile(String referenceCode, String functionCode, File tmpFile, String orgname) throws IOException {
		TCommonAttachsDto commonAttachsDto = new TCommonAttachsDto();
		commonAttachsDto.setReference_code(referenceCode); // 参考键（一般对应主表主键）
		commonAttachsDto.setFunction_code(functionCode); // FUNCTION_CODE
		commonAttachsDto.setFile_orgname(orgname); // 原始文件名
		commonAttachsDto.setFile_size(new BigDecimal(tmpFile.length())); // 文件大小

		// 准备上传文件需要存储的目录
		String storePath = getPubStorageRelativePath(functionCode);
		commonAttachsDto.setFile_path(storePath);// 相对存储路径
		File fileDir = new File(pub_file_path + File.separator + storePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();// 若不存在则创建目录
		}
		String destFileName = "file_" + UUID.randomUUID().toString(); // 归档自动生成的存储名称
		commonAttachsDto.setFile_currentname(destFileName);// 存储后文件名

		try {
			// 2.将临时文件COPY到实际存储的目录
			File destFile = new File(pub_file_path + File.separator + storePath + File.separator + destFileName);
			FileUtils.copyFile(tmpFile, destFile);
			FileUtils.deleteQuietly(tmpFile);
			//System.out.println("have copy [" + orgname + "]file to [" + destFile.getAbsolutePath() + "]");
		} catch (IOException e) {
			logger.error("[" + tmpFile.getAbsoluteFile() + "]save to [" + "] is failed", e);
			throw e;
		}

		return commonAttachsDto;
	}


	public static void main(String[] args) throws IOException {
		String fullName = "upload__2fc0b011_14ed232df21__8000_00000002.tmp";
		File tmpfile = new File("D:\\works\\server\\tomcat6.0\\webapps\\file\\temp\\storage\\" + fullName);
		FileUploadUtil.saveUploadByTmpFile("referenceCode001", "functionCode002", tmpfile, "hello.txt");

	}
}
