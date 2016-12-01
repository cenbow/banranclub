package com.wechat.material.service.impl;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.wechat.material.dao.ITAttachMaterialDao;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.material.model.vo.MaterialPictureVo;
import com.wechat.material.model.vo.MaterialVideoVo;
import com.wechat.material.model.vo.MaterialVo;
import com.wechat.material.model.vo.MaterialVoiceVo;
import com.wechat.material.service.ITAttachMaterialService;


/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tAttachMaterialService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TAttachMaterialServiceImpl extends GenericServiceImpl<TAttachMaterialDto, String> implements ITAttachMaterialService{
	  @Autowired
	  private ITAttachMaterialDao   tAttachMaterialDao;
	
	  public BaseDao<TAttachMaterialDto, String> getBaseDao() {
			return tAttachMaterialDao;
	  }

	 /**
	  * 通过file_id 获取文件访问路径
	  * @param fildId
	  * @return
	  */
	 public String getPubFileVisitPathByFileId(String file_id){
		 TAttachMaterialDto param = new TAttachMaterialDto();
		 param.setFile_id(file_id);
		 TAttachMaterialDto tmpTAttachMaterialDto = tAttachMaterialDao.getRow(param);
		 return  FileUploadUtil.pub_file_path+File.separator+tmpTAttachMaterialDto.getStore_path()+File.separator+tmpTAttachMaterialDto.getStoret_name();
	 }	 
		
	 /***
	  * 通过picture_id 获取图片素材处理上传发送所需要素
	  * @param picture_id
	  * @return
	  */
	 public MaterialPictureVo queryMaterialPictureVoByPictureIdForUpload(String picture_id,String platform_id)
	 {
		 return tAttachMaterialDao.queryMaterialPictureVoByPictureIdForUpload(picture_id, platform_id);
	 }
	 
	 /***
	  * 通过video_id 获取图片素材处理上传发送所需要素
	  * @param video_id
	  * @return
	  */
	 public MaterialVideoVo queryMaterialVideoVoByVideoIdForUpload(String video_id,String platform_id)
	 {
		 return tAttachMaterialDao.queryMaterialVideoVoByVideoIdForUpload(video_id, platform_id);
	 }
	 
	 /***
	  * 通过voice_id 获取图片素材处理上传发送所需要素
	  * @param voice_id
	  * @return
	  */
	 public MaterialVoiceVo queryMaterialVoiceByVoiceIdForUpload(String voice_id,String platform_id)
	 {
		 return tAttachMaterialDao.queryMaterialVoiceByVoiceIdForUpload(voice_id, platform_id);
	 }
	 
	 /**
	  * 通过file_id 获取素材处理上传发送所需要素
	  * @param file_id
	  * @param platform_id
	  * @return
	  */
	 public MaterialVo queryMaterialVoByFileIdForUpload(String file_id,String platform_id)
	 {
		 return tAttachMaterialDao.queryMaterialVoByFileIdForUpload(file_id, platform_id);
	 }
	 
	 
	
}
