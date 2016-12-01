package com.wechat.picture.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.material.dao.ITAttachMaterialDao;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.picture.dao.ITMaterialPictureDao;
import com.wechat.picture.model.TMaterialPictureQueryBean;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;


/**
 * 类功能:图片资源管理service实现
 * <p>创建者:zhouyl</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tMaterialPictureService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TMaterialPictureServiceImpl extends GenericServiceImpl<TMaterialPictureDto, String> implements ITMaterialPictureService{
    @Autowired
    private ITMaterialPictureDao   tMaterialPictureDao;
    @Autowired
    private ITAttachMaterialDao tAttachMaterialDao;
	public BaseDao<TMaterialPictureDto, String> getBaseDao() {
		return tMaterialPictureDao;
	}
	

	/****
	 * @param  tMaterialPictureQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTMaterialPictureListPage(TMaterialPictureQueryBean tMaterialPictureQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tMaterialPictureDao.queryTMaterialPictureListPage(tMaterialPictureQueryBean, pagingObject);
	 }
	
	 /****
	  * 图片资源保存：
	  * 	1.保存素材对象
	  * 	2.将返回素材对象ID设置进图片资源对象内
	  * 	3.保存图片资源
	  * @param tMaterialPictureDto	图片资源对象
	  * @param tAttachMaterialDto	素材对象
	  * @return
	  */
	 public TMaterialPictureDto save(TMaterialPictureDto tMaterialPictureDto,TAttachMaterialDto tAttachMaterialDto){
		
		 // 保存素材对象
		 tAttachMaterialDto = tAttachMaterialDao.save(tAttachMaterialDto);
		 
		 //将素材ID设置进图片对象
		 tMaterialPictureDto.setFile_id(tAttachMaterialDto.getFile_id());
		 
		 //返回图片保存结果
		 return tMaterialPictureDao.save(tMaterialPictureDto);
	 }
	 
	 /****
	  * 图片资源更新   步骤：1.更新素材表    2.更新图片表
	  * @param tMaterialPictureDto	图片资源对象
	  * @param tAttachMaterialDto	素材对象
	  * @return	图片表受影响的条数
	  */
	 public Integer updatePK(TMaterialPictureDto tMaterialPictureDto,TAttachMaterialDto tAttachMaterialDto){
		
		 //更新素材对象
		 tAttachMaterialDao.updatePK(tAttachMaterialDto);
		
		 //返回图片资源更新结果
		 return tMaterialPictureDao.updatePK(tMaterialPictureDto);
	 }
	 
	 /****
	  * 删除图片资源
	  * 描述：分别删除该图片在图片资源表和素材表的记录
	  * @param tMaterialPictureDto	待删除的图片资源对象
	  * @param tAttachMaterialDto	待删除的素材资源对象
	  */
	 public void deletePK(TMaterialPictureDto tMaterialPictureDto,TAttachMaterialDto tAttachMaterialDto){
		
		 //删除素材对象
		 tAttachMaterialDao.deletePK(tAttachMaterialDto);
		 
		 //删除图片对象
		 tMaterialPictureDao.deletePK(tMaterialPictureDto);
	 }
}
