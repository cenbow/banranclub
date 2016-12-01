package com.wechat.material.model.vo;
import com.wechat.material.model.dto.TAttachMaterialDto;
public class MaterialVoiceVo extends TAttachMaterialDto{

	//TMaterialVoiceDto part
	private java.lang.String voice_id;//素材主键
	private java.lang.String voice_name;//名称
	private java.lang.String voice_url;//URL
	private java.lang.String voice_desc;//描述
	private java.lang.String cache_flag;//是否缓存
	
	//TMaterialTxrefDto part
	private java.lang.String txref_id;//关联主键
	private java.lang.String material_type;//素材附件类型[CS:5012]
	private java.lang.String material_id;//消息素材ID(不同类型关联不同表)
	private java.lang.String tx_thumb_mdeia_id;//媒体文件缩略图
	private java.lang.String tx_media_id;//媒体文件标识
	private java.lang.String tx_type;//媒体文件类型
	private java.lang.Long   tx_created_at;//媒体文件上传时间
	private java.lang.Long   tx_expiration_at;//媒体文件默认过期时间
	private java.lang.String platform_id;//公众平台号ID
	
	public java.lang.String getVoice_id() {
		return voice_id;
	}
	public void setVoice_id(java.lang.String voiceId) {
		voice_id = voiceId;
	}
	public java.lang.String getVoice_name() {
		return voice_name;
	}
	public void setVoice_name(java.lang.String voiceName) {
		voice_name = voiceName;
	}
	public java.lang.String getVoice_url() {
		return voice_url;
	}
	public void setVoice_url(java.lang.String voiceUrl) {
		voice_url = voiceUrl;
	}
	public java.lang.String getVoice_desc() {
		return voice_desc;
	}
	public void setVoice_desc(java.lang.String voiceDesc) {
		voice_desc = voiceDesc;
	}
	public java.lang.String getTxref_id() {
		return txref_id;
	}
	public void setTxref_id(java.lang.String txrefId) {
		txref_id = txrefId;
	}
	public java.lang.String getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(java.lang.String materialType) {
		material_type = materialType;
	}
	public java.lang.String getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(java.lang.String materialId) {
		material_id = materialId;
	}
	public java.lang.String getTx_thumb_mdeia_id() {
		return tx_thumb_mdeia_id;
	}
	public void setTx_thumb_mdeia_id(java.lang.String txThumbMdeiaId) {
		tx_thumb_mdeia_id = txThumbMdeiaId;
	}
	public java.lang.String getTx_media_id() {
		return tx_media_id;
	}
	public void setTx_media_id(java.lang.String txMediaId) {
		tx_media_id = txMediaId;
	}
	public java.lang.String getTx_type() {
		return tx_type;
	}
	public void setTx_type(java.lang.String txType) {
		tx_type = txType;
	}
	public java.lang.Long getTx_created_at() {
		return tx_created_at;
	}
	public void setTx_created_at(java.lang.Long txCreatedAt) {
		tx_created_at = txCreatedAt;
	}
	public java.lang.Long getTx_expiration_at() {
		return tx_expiration_at;
	}
	public void setTx_expiration_at(java.lang.Long txExpirationAt) {
		tx_expiration_at = txExpirationAt;
	}
	public java.lang.String getPlatform_id() {
		return platform_id;
	}
	public void setPlatform_id(java.lang.String platformId) {
		platform_id = platformId;
	}
	public java.lang.String getCache_flag() {
		return cache_flag;
	}
	public void setCache_flag(java.lang.String cacheFlag) {
		cache_flag = cacheFlag;
	}
}
