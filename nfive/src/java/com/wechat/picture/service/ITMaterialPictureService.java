package com.wechat.picture.service;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.picture.model.TMaterialPictureQueryBean;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.hercules.database.service.IGenericService;


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
public interface ITMaterialPictureService extends IGenericService<TMaterialPictureDto, String>{

	/****
	 * 
	 * @param tMaterialPictureQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMaterialPictureListPage(TMaterialPictureQueryBean tMaterialPictureQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /****
	  * 自定义方法   添加图片资源至数据库
	  * 描述：将素材保存至数据库后，把返回素材对象的FILE_ID设置进图片对象内，再进行图片的保存
	  * @param tMaterialPictureDto	图片对象
	  * @param tAttachMaterialDto	素材对象
	  */
	 public TMaterialPictureDto save(TMaterialPictureDto tMaterialPictureDto,TAttachMaterialDto tAttachMaterialDto);

	 /****
	  * 自定义方法：更新图片资源
	  * 描述：更新相应的素材对象及图片资源
	  * @param tMaterialPictureDto	待更新的图片资源
	  * @param tAttachMaterialDto	待更新的素材资源
	  * @return	
	  */
	 public Integer updatePK(TMaterialPictureDto tMaterialPictureDto,TAttachMaterialDto tAttachMaterialDto);

	 /****
	  * 自定义方法：删除图片资源
	  * 描述：分别删除该图片在图片资源表和素材表的记录
	  * @param tMaterialPictureDto	待删除的图片资源对象
	  * @param tAttachMaterialDto	待删除的素材资源对象
	  */
	 public void deletePK(TMaterialPictureDto tMaterialPictureDto,TAttachMaterialDto tAttachMaterialDto);

}
