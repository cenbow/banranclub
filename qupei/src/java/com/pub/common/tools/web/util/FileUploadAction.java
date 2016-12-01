package com.pub.common.tools.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pub.common.local.model.dto.TCommonAttachsDto;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.encryption.Encrypter;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.common.tools.properties.UtilConfig;
import com.pub.common.tools.util.FileUploadUtil;
import com.pub.persistence.dao.ITCommonAttachsDao;

/**
 * 类功能: 文件上传
 *
 * @author wen.zhang
 */
@Controller("fileUploadAction")
@Scope("prototype")
public class FileUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FileUploadAction.class);

	/** 上传的文件 */
	private File uploadFile;
	/** 上传的文件名 */
	private String uploadFileFileName;
	/** 附件表主键 */
	private String attachsId;

	/** 参考键（一般对应主表主键） */
	private String referenceCode;
	/** FUNCTION_CODE */
	private String functionCode;

	@Autowired
	private ITCommonAttachsDao commonAttachsDao;

	@Autowired
	private UtilConfig utilConfig;

	// 图片文件后缀一览
	private static final String[] IMG_EXT_LIST = { "JPG", "JPEG", "BMP", "GIF", "PNG", "PJPEG", "X-PNG" };

	// 可预览文件后缀一览
	private static final String[] PREVIEW_EXT_LIST = { "PDF", "TXT", "CSV", "HTML", "HTM", "DOC", "DOCX", "XLS", "XLSX", "PPT", "PPTX", "MP3", "WAV", "MP4", "AVI", "SWF", "MOV", "ASF", "RM", "MPG",
			"MKV", "ASX" };

	/***
	 * 文件列表
	 *
	 * @param fileVo
	 **/
	public void fileList() throws Exception {
		List<FileVo> fileList = new ArrayList<FileVo>();

		if (StringUtils.isNotBlank(referenceCode) && StringUtils.isNotBlank(functionCode)) {
			TCommonAttachsDto commonAttachsDto = new TCommonAttachsDto();
			commonAttachsDto.setReference_code(referenceCode);
			commonAttachsDto.setFunction_code(functionCode);
			List<TCommonAttachsDto> commonAttachsResultList = commonAttachsDao.getAll(commonAttachsDto);

			if (commonAttachsResultList != null) {
				for (TCommonAttachsDto attachsDto : commonAttachsResultList) {
					FileVo fileVo = new FileVo();
					fileVo.setAttachsId(attachsDto.getAttachs_id());
					fileVo.setUrl("fileDownload.action?attachsId=" + attachsDto.getAttachs_id());
					if (isPicture(attachsDto.getFile_orgname())) {
						fileVo.setType("image/png");
						fileVo.setThumbnailUrl(utilConfig.getAction_url() + "/images/image.png");
					} else {
						fileVo.setType("");
						fileVo.setThumbnailUrl(utilConfig.getAction_url() + "/images/attachment.png");
					}
					fileVo.setName(attachsDto.getFile_orgname());
					fileVo.setSize(attachsDto.getFile_size().toString());
					fileVo.setDeleteUrl("fileDel.action?attachsId=" + attachsDto.getAttachs_id());
					fileVo.setDeleteType("GET");

					fileVo.setCreatedUserCd(attachsDto.getCreated_user_cd());
					fileVo.setCreatedDate(attachsDto.getCreated_date());
					fileList.add(fileVo);
				}
			}
		}

		JsonConfig config = new JsonConfig();
		JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
		JSONArray json_rows = JSONArray.fromObject(fileList, config);

		String json = "{\"files\":" + json_rows.toString() + "}";
		outJSOND(this.getResponse(), json);
	}

	/***
	 * 财富农场上传文件列表
	 *
	 * @param fileVo
	 **/
	public void caifuncFileList() throws Exception {
		List<FileVo> fileList = new ArrayList<FileVo>();

		if (StringUtils.isNotBlank(referenceCode) && StringUtils.isNotBlank(functionCode)) {
			TCommonAttachsDto commonAttachsDto = new TCommonAttachsDto();
			commonAttachsDto.setReference_code(referenceCode);
			commonAttachsDto.setFunction_code(functionCode);
			List<TCommonAttachsDto> commonAttachsResultList = commonAttachsDao.getAll(commonAttachsDto);

			if (commonAttachsResultList != null) {
				for (TCommonAttachsDto attachsDto : commonAttachsResultList) {
					FileVo fileVo = new FileVo();
					fileVo.setAttachsId(attachsDto.getAttachs_id());
					fileVo.setUrl(utilConfig.getFtp_storage_path() + attachsDto.getFile_path().replace("\\", "/") + attachsDto.getFile_currentname());
					fileVo.setName(attachsDto.getFile_orgname());
					fileVo.setSize(attachsDto.getFile_size().toString());
					fileVo.setCreatedDate(attachsDto.getCreated_date());
					fileVo.setRemark(attachsDto.getRemark());
					fileList.add(fileVo);
				}
			}
		}

		JsonConfig config = new JsonConfig();
		JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
		JSONArray json_rows = JSONArray.fromObject(fileList, config);

		String json = "{\"files\":" + json_rows.toString() + "}";
		outJSOND(this.getResponse(), json);
	}

	/***
	 * 文件上传
	 *
	 * @param fileVo
	 **/
	public void fileUpload() throws Exception {
		String json = "{}";

		List<FileVo> fileList = new ArrayList<FileVo>();

		long fileSize = uploadFile.length();

		if (fileSize >= 0) {
			// 把Struts临时文件拷贝到Storage临时目录（Struts临时文件会被不定时清理）
			File destFile = FileUploadUtil.copyFileByStrutsToTemp(uploadFile);

			FileVo fileVo = new FileVo();

			String pathEncrypt = "";
			if (destFile != null) {
				pathEncrypt = Encrypter.encrypt(destFile.getPath());
			}

			fileVo.setPath(pathEncrypt);
			fileVo.setUrl("fileDownload.action");
			fileVo.setName(uploadFileFileName);
			fileVo.setSize(String.valueOf(fileSize));
			fileVo.setDeleteUrl("fileDel.action?path=" + pathEncrypt);
			fileVo.setDeleteType("GET");
			fileList.add(fileVo);

			if (isPicture(uploadFileFileName)) {
				fileVo.setType("image/png");
				fileVo.setThumbnailUrl(utilConfig.getAction_url() + "/images/image.png");
			} else {
				fileVo.setType("");
				fileVo.setThumbnailUrl(utilConfig.getAction_url() + "/images/attachment.png");
			}

			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
			JSONArray json_rows = JSONArray.fromObject(fileList, config);

			json = "{\"files\":" + json_rows.toString() + "}";
		} else {
			json = "[{\"error\":'Failed to get stored file size.'}]";
		}

		//System.out.println(json);
		outJSOND(this.getResponse(), json);
	}

	/***
	 * 文件删除
	 *
	 * @param fileVo
	 **/
	public void fileDel() throws Exception {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Map<String, Object> success = new HashMap<String, Object>();
		success.put("success", true);
		results.add(success);

		JsonConfig config = new JsonConfig();
		JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
		JSONArray json_rows = JSONArray.fromObject(results, config);
		outJSOND(this.getResponse(), json_rows.toString());
	}

	/***
	 * 文件下载
	 *
	 * @param fileVo
	 **/
	public void fileDownload() throws Exception {
		if (StringUtils.isNotBlank(attachsId)) {
			TCommonAttachsDto commonAttachsDto = new TCommonAttachsDto();
			commonAttachsDto.setAttachs_id(attachsId);
			TCommonAttachsDto commonAttachsResultDto = commonAttachsDao.getRow(commonAttachsDto);

			if (commonAttachsResultDto != null) {
				String filePath = utilConfig.getPub_file_path() + File.separator + commonAttachsResultDto.getFile_path() + File.separator + commonAttachsResultDto.getFile_currentname();

				try {
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Type", "charset=UTF-8");
					response.setHeader("Content-Disposition", getHttpHeader(commonAttachsResultDto.getFile_orgname(), response, request));

					InputStream is = new FileInputStream(filePath);
					IOUtils.copy(is, response.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String getHttpHeader(final String fileName, final HttpServletResponse response, HttpServletRequest request1) throws UnsupportedEncodingException {
		String browser = request1.getHeader("user-agent");
		String httpHeader = "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8");

		if (browser.indexOf("MSIE") != -1 || browser.indexOf("Chrome") != -1) {
			httpHeader = "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"";
		} else if (browser.indexOf("Safari") != -1) {
			httpHeader = "attachment; filename=" + new String(fileName.getBytes(), "ISO-8859-1");
		}
		return httpHeader;
	}

	/***
	 * 判断附件是否是图片
	 *
	 * @param fileVo
	 **/
	public boolean isPicture(String fileNmae) throws Exception {
		if (StringUtils.isNotBlank(fileNmae)) {
			String fileSuffix = fileNmae.substring(fileNmae.lastIndexOf(".") + 1, fileNmae.length());
			for (String suffix : IMG_EXT_LIST) {
				if (suffix.equalsIgnoreCase(fileSuffix)) {
					return true;
				}
			}
		}
		return false;
	}

	/***
	 * 判断附件是否可以预览
	 *
	 * @param fileVo
	 **/
	public boolean canPreview(String fileNmae) throws Exception {
		if (StringUtils.isNotBlank(fileNmae)) {
			String fileSuffix = fileNmae.substring(fileNmae.lastIndexOf(".") + 1, fileNmae.length());
			for (String suffix : PREVIEW_EXT_LIST) {
				if (suffix.equalsIgnoreCase(fileSuffix)) {
					return true;
				}
			}
		}
		return false;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getAttachsId() {
		return attachsId;
	}

	public void setAttachsId(String attachsId) {
		this.attachsId = attachsId;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

}
