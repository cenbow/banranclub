package com.platform.persistence.service.impl;
import java.util.List;

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
import com.platform.persistence.dao.ITResourceDao;
import com.platform.persistence.model.TResourceQueryBean;
import com.platform.persistence.model.dto.TResSelfRelaDto;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.model.dto.TRoleResRelaDto;
import com.platform.persistence.service.ITResSelfRelaService;
import com.platform.persistence.service.ITResourceService;
import com.platform.persistence.service.ITRoleResRelaService;


/**
 * 类功能:资源服务实现类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tResourceService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TResourceServiceImpl extends GenericServiceImpl<TResourceDto, String> implements ITResourceService{
    @Autowired
    private ITResourceDao   tResourceDao;
    @Autowired
    private ITResSelfRelaService tResSelfRelaService;
    @Autowired
    private ITRoleResRelaService tRoleResRelaService;

	public BaseDao<TResourceDto, String> getBaseDao() {
		return tResourceDao;
	}
	

	/****
	 * @param  tResourceQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTResourceListPage(TResourceQueryBean tResourceQueryBean,PagingObject pagingObject) throws Exception
	 {	
		 	return this.tResourceDao.queryTResourceListPage(tResourceQueryBean, pagingObject);
	 }
	 
	 /**
	  * 资源保存
	  * @param pid
	  * @param tResourceDto
	  */
	 public void saveResource(String pid,TResourceDto tResourceDto)
	 {
		 //重置资源编码为防止滞后
		 TResourceQueryBean tResourceQueryBean = new TResourceQueryBean();
		 tResourceQueryBean.setParent_res_id(pid);
		 tResourceDto.setRes_code(tResourceDao.getCode(tResourceQueryBean));
		 //保存资源
		 super.save(tResourceDto);
		 //保存资源关系
		 TResSelfRelaDto tResSelfRelaDto = new TResSelfRelaDto();
		 tResSelfRelaDto.setChild_res_id(tResourceDto.getRes_id());
		 tResSelfRelaDto.setParent_res_id(pid);
		 tResSelfRelaDto.setCreated_user_cd(tResourceDto.getCreated_user_cd());
		 tResSelfRelaDto.setRes_type(tResourceDto.getRes_type());
		 tResSelfRelaService.save(tResSelfRelaDto);
	 }
	 
	 /**
	  * 删除资源以及对应的资源关系数据
	  */
	 public String deleteResource(TResourceDto tResourceDto)
	 {
		 //根节点不允许删除
		 if(tResourceDto.getRes_id().equals("1"))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"根节点不允许删除!"+"\"}";
		 }
		 //判断是否有子节点
		 if(isChild(tResourceDto.getRes_id()))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"您要删除的资源包含子节点,请先对子节点进行删除!"+"\"}";
		 }
		 //判断是否存在资源-角色关系
		 if(isRoleRela(tResourceDto.getRes_id()))
		 {
			 return "{\"status\":"+false+",\"message\":\""+"您要删除的资源包含角色权限，请先对权限数据删除!"+"\"}";
		 }
		 //获取资源对应的父关系并执行删除
		 TResSelfRelaDto tResSelfRelaDto = new TResSelfRelaDto();
		 tResSelfRelaDto.setChild_res_id(tResourceDto.getRes_id());
		 tResSelfRelaService.delete(tResSelfRelaDto);
		//删除资源数据
		 super.delete(tResourceDto);
		 return "{\"status\":"+true+",\"message\":\""+"删除成功!"+"\"}";
	 }
	 
	 /**
	  * 获取code
	  */
	 public String getCode(TResourceQueryBean tResourceQueryBean)
	 {
		 return tResourceDao.getCode(tResourceQueryBean);
	 }
	 
	 /**
	  * 验证节点有效性
	  */
	 public String checkResourceVal(TResourceQueryBean tResourceQueryBean)
	 {
		 boolean isVal = true;
		 String message = "";
		 if(tResourceQueryBean == null || tResourceQueryBean.getRes_id() == null
				 || tResourceQueryBean.getRes_id().length() <= 0)
		 {
			 isVal = false;
			 message = "节点未选中,请刷新后重新操作!";
		 }
		 //验证节点是否存在
		 TResourceDto tResourceDto_ = new TResourceDto();
		 tResourceDto_.setRes_id(tResourceQueryBean.getRes_id());
		 tResourceDto_ = super.getRow(tResourceDto_);
		 if(tResourceDto_ == null || tResourceDto_.getRes_id() == null
				 	|| tResourceDto_.getRes_id().trim().length() <= 0)
		 {
			 isVal = false;
			 message = "选中的节点无效,请刷新后重新操作!";
		 }
		 return "{\"status\":"+isVal+",\"mess\":\""+message+"\"}";
	 }
	 
	 /**
	  * 校验节点是否重名 同一父节点下的所有子节点不允许重名
	  */
	 public String checkResourceOnSubmit(TResourceQueryBean tResourceQueryBean,String doType)
	 {
		 String message = "";
		 if(!checkResourceName(tResourceQueryBean,doType))
		 {
			 message = "名称有重复,请确认后重新输入!";
			 return "{\"status\":"+false+",\"mess\":\""+message+"\"}";
		 }
		 return "{\"status\":"+true+",\"mess\":\""+message+"\"}";
	 }
	 
	 /**
	  * 通过用户id获取对应的权限数据
	  */
	 public List<TResourceDto> getResourceByUserId(TResourceQueryBean tResourceQueryBean)
	 {
		 return tResourceDao.getResourceByUserId(tResourceQueryBean);
	 }
	 
	 /**
	  * 判断是否有子节点 有则返回true
	  * @param parentId 要删除的节点id
	  * @return
	  */
	 private boolean isChild(String parent_res_id)
	 {
		 TResSelfRelaDto tResSelfRelaDto = new TResSelfRelaDto();
		 tResSelfRelaDto.setParent_res_id(parent_res_id);
		 List<TResSelfRelaDto> list = tResSelfRelaService.getAll(tResSelfRelaDto);
		 return list != null && list.size() > 0;
	 }
	 
	 /**
	  * 判断是否已经将此资源分配给了相应的角色
	  * @param res_id
	  * @return
	  */
	 private boolean isRoleRela(String res_id)
	 {
		 TRoleResRelaDto tRoleResRelaDto = new TRoleResRelaDto();
		 tRoleResRelaDto.setRes_id(res_id);
		 List<TRoleResRelaDto> tRoleResRelaDtoList = this.tRoleResRelaService.getAll(tRoleResRelaDto);
		 return tRoleResRelaDtoList != null && tRoleResRelaDtoList.size() > 0;
	 }
	 
	 
	 
	 /**
	  * 资源重名校验
	  * @return
	  */
	 private boolean checkResourceName(TResourceQueryBean tResourceQueryBean,String doType)
	 {
		 boolean isVal = false;
		 List<TResourceDto> resourceList = tResourceDao.getResourceByParent(tResourceQueryBean);
		 if(resourceList == null || resourceList.size() == 0)
		 {
			 isVal = true;
		 }else
		 {
			 //编辑时排除未修改的情况
			 TResourceDto tResourceDto = resourceList.get(0);
			 if("edit".equals(doType) && tResourceDto.getRes_id().equals(tResourceQueryBean.getRes_id()))
			 {
				 isVal = true;
			 }
		 }
		 return isVal;
	 }
	 
	 
	 
	 
}
