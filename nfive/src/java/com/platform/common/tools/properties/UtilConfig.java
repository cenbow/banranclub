package com.platform.common.tools.properties;

import org.springframework.stereotype.Service;

/***
 * 用于配置文件上下载的内容
 * @author hercuels.chen
 */
@Service("utilConfig")
public class UtilConfig {
	//web action url 
	private String action_url = null;//action url
	
	//用于会员服务绑定的公众号
	private String vip_platform_id =null;
	//用于会员服务绑定的公众号对应的域名（如果和当前域名不一致会使用OpenId转发）
	private String vip_platform_domain =null;
	
	//-----------------fileUploadUtil  begin--------------------
	private String domain_weburl=null;//后台文件服务器域名
	private String wap_file_weburl=null;//前台文件服务器域名
	private String tmp_file_path=null; //临时文件路径
	private String pub_file_path=null; //公文永久文件路径
	private String sys_file_path =null;//系统文件路径
	//-----------------fileUploadUtil  end----------------------

	//-------------------wechat media start-----------------
	private String upload_media_url   =  null;//上传URL
	private String download_media_url =  null;//下载URL
	//-------------------wechat media end-----------------
	

	//-------------------wechat menu start-----------------
	private String menu_create_url = null;	// 菜单创建（POST）
	private String menu_get_url = null;	// 菜单查询（GET）
	private String menu_delete_url = null;	// 菜单删除（GET）
	//-------------------wechat menu end-----------------
	
	//-------------------wechat crm change start-----------------
    public String util_ueditor_wechat_path;//crm文件在wechat的文件路径
    public String util_ueditor_crm_path;//crm文件路径
    public String fp_show_photo_crm_path;	//理财师照片路径(crm)
    public String fp_show_photo_wechat_path;//理财师照片路径(wechat)
    //-------------------wechat crm change end-----------------
	
    private String msg_interval;
    private String msg_gp_cnt;
    private String msg_gp_interval;
    
    
	//--------------------get set-------------------------------
	
    
    

	public String getTmp_file_path() {
		return tmp_file_path;
	}

	public String getVip_platform_id() {
		return vip_platform_id;
	}

	public void setVip_platform_id(String vipPlatformId) {
		vip_platform_id = vipPlatformId;
	}

	public String getDomain_weburl() {
		return domain_weburl;
	}

	public void setDomain_weburl(String domainWeburl) {
		domain_weburl = domainWeburl;
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

	public String getSys_file_path() {
		return sys_file_path;
	}

	public void setSys_file_path(String sysFilePath) {
		sys_file_path = sysFilePath;
	}

	public String getUpload_media_url() {
		return upload_media_url;
	}

	public void setUpload_media_url(String uploadMediaUrl) {
		upload_media_url = uploadMediaUrl;
	}

	public String getDownload_media_url() {
		return download_media_url;
	}

	public void setDownload_media_url(String downloadMediaUrl) {
		download_media_url = downloadMediaUrl;
	}

	public String getMenu_create_url() {
		return menu_create_url;
	}

	public void setMenu_create_url(String menuCreateUrl) {
		menu_create_url = menuCreateUrl;
	}

	public String getMenu_get_url() {
		return menu_get_url;
	}

	public void setMenu_get_url(String menuGetUrl) {
		menu_get_url = menuGetUrl;
	}

	public String getMenu_delete_url() {
		return menu_delete_url;
	}

	public void setMenu_delete_url(String menuDeleteUrl) {
		menu_delete_url = menuDeleteUrl;
	}

	public String getAction_url() {
		return action_url;
	}

	public void setAction_url(String actionUrl) {
		action_url = actionUrl;
	}

	public String getWap_file_weburl() {
		return wap_file_weburl;
	}

	public void setWap_file_weburl(String wapFileWeburl) {
		wap_file_weburl = wapFileWeburl;
	}

	public String getUtil_ueditor_wechat_path() {
		return util_ueditor_wechat_path;
	}

	public void setUtil_ueditor_wechat_path(String utilUeditorWechatPath) {
		util_ueditor_wechat_path = utilUeditorWechatPath;
	}

	public String getUtil_ueditor_crm_path() {
		return util_ueditor_crm_path;
	}

	public void setUtil_ueditor_crm_path(String utilUeditorCrmPath) {
		util_ueditor_crm_path = utilUeditorCrmPath;
	}

	public String getMsg_interval() {
		return msg_interval;
	}

	public void setMsg_interval(String msgInterval) {
		msg_interval = msgInterval;
	}

	public String getMsg_gp_cnt() {
		return msg_gp_cnt;
	}

	public void setMsg_gp_cnt(String msgGpCnt) {
		msg_gp_cnt = msgGpCnt;
	}

	public String getMsg_gp_interval() {
		return msg_gp_interval;
	}

	public void setMsg_gp_interval(String msgGpInterval) {
		msg_gp_interval = msgGpInterval;
	}

	/**
	 * @return the fp_show_photo_crm_path
	 */
	public String getFp_show_photo_crm_path() {
		return fp_show_photo_crm_path;
	}

	/**
	 * @param fpShowPhotoCrmPath the fp_show_photo_crm_path to set
	 */
	public void setFp_show_photo_crm_path(String fpShowPhotoCrmPath) {
		fp_show_photo_crm_path = fpShowPhotoCrmPath;
	}

	/**
	 * @return the fp_show_photo_wechat_path
	 */
	public String getFp_show_photo_wechat_path() {
		return fp_show_photo_wechat_path;
	}

	/**
	 * @param fpShowPhotoWechatPath the fp_show_photo_wechat_path to set
	 */
	public void setFp_show_photo_wechat_path(String fpShowPhotoWechatPath) {
		fp_show_photo_wechat_path = fpShowPhotoWechatPath;
	}

	/**
	 * @return the vip_platform_domain
	 */
	public String getVip_platform_domain() {
		return vip_platform_domain;
	}

	/**
	 * @param vipPlatformDomain the vip_platform_domain to set
	 */
	public void setVip_platform_domain(String vipPlatformDomain) {
		vip_platform_domain = vipPlatformDomain;
	}

		
}
