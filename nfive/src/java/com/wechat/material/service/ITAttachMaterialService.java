package com.wechat.material.service;
import com.hercules.database.service.IGenericService;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.material.model.vo.MaterialPictureVo;
import com.wechat.material.model.vo.MaterialVideoVo;
import com.wechat.material.model.vo.MaterialVo;
import com.wechat.material.model.vo.MaterialVoiceVo;


/**
 * 类功能:自动代码生成模板   IService 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITAttachMaterialService extends IGenericService<TAttachMaterialDto, String>{

	 /**
	  * @param fildId
	  * @return
	  */
	 public String getPubFileVisitPathByFileId(String file_id);
	 
		
	 /***
	  * 通过picture_id 获取图片素材处理上传发送所需要素
	  * @param picture_id
	  * @return
	  */
	 public MaterialPictureVo queryMaterialPictureVoByPictureIdForUpload(String picture_id,String platform_id);
	 
	 
	 /***
	  * 通过video_id 获取视频素材处理上传发送所需要素
	  * @param video_id
	  * @return
	  */
	 public MaterialVideoVo queryMaterialVideoVoByVideoIdForUpload(String video_id,String platform_id);
	 
	 
	 /***
	  * 通过voice_id 获取音频素材处理上传发送所需要素
	  * @param voice_id
	  * @return
	  */
	 public MaterialVoiceVo queryMaterialVoiceByVoiceIdForUpload(String voice_id,String platform_id);
	 
	 /**
	  * 通过file_id获取素材上传要素
	  * @param file_id
	  * @param platform_id
	  * @return
	  */
	 public MaterialVo queryMaterialVoByFileIdForUpload(String file_id,String platform_id);
}
