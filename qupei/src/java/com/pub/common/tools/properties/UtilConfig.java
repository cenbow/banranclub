package com.pub.common.tools.properties;

import org.springframework.stereotype.Service;

/***
 * 用于配置文件上下载的内容
 * 
 * @author hercuels.chen
 */
@Service("utilConfig")
public class UtilConfig {
	// web action url
	private String action_url = null;// action url

	// -----------------fileUploadUtil begin--------------------
	private String tmp_file_path = null; // 临时文件路径
	private String pub_file_path = null; // 公文永久文件路径
	private String file_servers_url = null;// 文件服务器URL

	private String ftp_storage_path = null;// 远程文件服务器Url
	// -----------------fileUploadUtil end----------------------

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String actionUrl) {
		action_url = actionUrl;
	}

	public String getTmp_file_path() {
		return tmp_file_path;
	}

	public void setTmp_file_path(String tmpFilePath) {
		tmp_file_path = tmpFilePath;
	}

	public String getPub_file_path() {
		return pub_file_path;
	}

	public void setPub_file_path(String pubFilePath) {
		pub_file_path = pubFilePath;
	}

	public String getFile_servers_url() {
		return file_servers_url;
	}

	public void setFile_servers_url(String fileServersUrl) {
		file_servers_url = fileServersUrl;
	}

	public String getFtp_storage_path() {
		return ftp_storage_path;
	}

	public void setFtp_storage_path(String ftpStoragePath) {
		ftp_storage_path = ftpStoragePath;
	}

}
