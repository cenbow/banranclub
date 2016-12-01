package com.wechat.material.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.material.dao.ITAttachMaterialDao;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.material.model.vo.MaterialPictureVo;
import com.wechat.material.model.vo.MaterialVideoVo;
import com.wechat.material.model.vo.MaterialVo;
import com.wechat.material.model.vo.MaterialVoiceVo;

/**
 * 类功能:自动代码生成模板 DaoImpl 模板
 * <p>
 * 创建者:
 * </p>
 * <p>
 * 创建时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */
@Scope("prototype")
@Service("tAttachMaterialDao")
public class TAttachMaterialDaoImpl extends
		BaseDaoImpl<TAttachMaterialDto, String> implements ITAttachMaterialDao {
	public TAttachMaterialDaoImpl() {
		super(TAttachMaterialDto.class);
	}

	/***
	 * 通过picture_id 获取图片素材处理上传发送所需要素
	 * 
	 * @param picture_id
	 * @return
	 */
	public MaterialPictureVo queryMaterialPictureVoByPictureIdForUpload(
			String picture_id, String platform_id) {
		Map param = new HashMap();
		param.put("PICTURE_ID", picture_id);
		param.put("PLATFORM_ID", platform_id);
		MaterialPictureVo materialPictureVo = (MaterialPictureVo) this
				.getSqlMapClientTemplate()
				.queryForObject(
						"tAttachMaterial.queryMaterialPictureVoByPictureIdForUpload",
						param);
		return materialPictureVo;
	}

	/***
	 * 通过video_id 获取图片素材处理上传发送所需要素
	 * 
	 * @param video_id
	 * @return
	 */
	public MaterialVideoVo queryMaterialVideoVoByVideoIdForUpload(
			String video_id, String platform_id) {
		Map param = new HashMap();
		param.put("VIDEO_ID", video_id);
		param.put("PLATFORM_ID", platform_id);
		MaterialVideoVo materialVideoVo = (MaterialVideoVo) this
				.getSqlMapClientTemplate()
				.queryForObject(
						"tAttachMaterial.queryMaterialVideoVoByVideoIdForUpload",
						param);
		return materialVideoVo;
	}

	/***
	 * 通过voice_id 获取图片素材处理上传发送所需要素
	 * 
	 * @param voice_id
	 * @return
	 */
	public MaterialVoiceVo queryMaterialVoiceByVoiceIdForUpload(
			String voice_id, String platform_id) {
		Map param = new HashMap();
		param.put("VOICE_ID", voice_id);
		param.put("PLATFORM_ID", platform_id);
		MaterialVoiceVo materialVoiceVo = (MaterialVoiceVo) this
				.getSqlMapClientTemplate().queryForObject(
						"tAttachMaterial.queryMaterialVoiceByVoiceIdForUpload",
						param);
		return materialVoiceVo;
	}

	public MaterialVo queryMaterialVoByFileIdForUpload(String fileId,
			String platformId) {
		Map param = new HashMap();
		param.put("FILE_ID", fileId);
		param.put("PLATFORM_ID", platformId);
		MaterialVo materialVo = (MaterialVo) getSqlMapClientTemplate()
				.queryForObject(
						"tAttachMaterial.queryMaterialVoByFileIdForUpload",
						param);
		return materialVo;
	}

}
