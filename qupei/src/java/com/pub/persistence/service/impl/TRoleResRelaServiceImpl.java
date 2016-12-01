package com.pub.persistence.service.impl;
import java.util.List;
import java.util.Map;

import com.pub.common.local.model.dto.TRoleResRelaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.pub.common.tools.opensymphony.page.PageResult;
import com.pub.common.tools.opensymphony.page.PagingObject;
import com.pub.persistence.dao.ITResourceDao;
import com.pub.persistence.dao.ITRoleResRelaDao;
import com.pub.persistence.model.TResourceQueryBean;
import com.pub.persistence.model.TRoleResRelaQueryBean;
import com.pub.persistence.service.ITRoleResRelaService;



/**
 * 类功能:角色资源关系服务实现类
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
@Service("tRoleResRelaService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TRoleResRelaServiceImpl extends GenericServiceImpl<TRoleResRelaDto, String> implements ITRoleResRelaService{
    @Autowired
    private ITRoleResRelaDao   tRoleResRelaDao;
    @Autowired
    private ITResourceDao tResourceDao;

	public BaseDao<TRoleResRelaDto, String> getBaseDao() {
		return tRoleResRelaDao;
	}


	/****
	 * @param  tRoleResRelaQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTRoleResRelaListPage(TRoleResRelaQueryBean tRoleResRelaQueryBean,PagingObject pagingObject) throws Exception
	 {
		 	return this.tRoleResRelaDao.queryTRoleResRelaListPage(tRoleResRelaQueryBean, pagingObject);
	 }

	 /**
	  * 为资源配置角色
	  */
	 public void saveTRoleResRela(TRoleResRelaDto tRoleResRelaDto)
	 {

		 if(tRoleResRelaDto.getRes_id() == null
				 || tRoleResRelaDto.getRes_id().trim().length() <= 0
				 || tRoleResRelaDto.getRole_id() == null
				 || tRoleResRelaDto.getRole_id().trim().length() <= 0)
		 {
			 return;
		 }
	 	//拼装角色、资源关系数据
		String roleId = tRoleResRelaDto.getRole_id();
		if(roleId != null && roleId.trim().length() > 0)
		{
			String[] roleIdArray = roleId.split(",");
			for(String rid : roleIdArray)
			{
				tRoleResRelaDto.setRole_res_id(null);
				tRoleResRelaDto.setRole_id(rid);
				super.save(tRoleResRelaDto);
				//获取父节点Id并判断是否已经将此权限赋予了该角色
				TResourceQueryBean tResourceQueryBean = new TResourceQueryBean();
				tResourceQueryBean.setRes_id(tRoleResRelaDto.getRes_id());
				tResourceQueryBean.setRole_id(rid);
				List resourceList = tResourceDao.selectParentReByChild(tResourceQueryBean);
				for(Map map : (List<Map>)resourceList)
				{
					//如果未手动赋予父节点角色则由后台自动绑定
					if(map.get("ISVAL").toString().equals("false"))
					{
						TRoleResRelaDto parentTRoleResRelaDto = new TRoleResRelaDto();
						parentTRoleResRelaDto.setRes_id(map.get("RES_ID").toString());
						parentTRoleResRelaDto.setRole_id(rid);
						super.save(parentTRoleResRelaDto);
					}
				}
			}
		}
	 }

	 /**
	  * 为资源移除角色
	  */
	 public void deleteTRoleResRela(TRoleResRelaDto tRoleResRelaDto)
	 {
		 if(tRoleResRelaDto.getRes_id() == null
				 || tRoleResRelaDto.getRes_id().trim().length() <= 0)
		 {
			 return;
		 }
	 	//拼装角色、资源关系数据
		String roleId = tRoleResRelaDto.getRole_id();
		if(roleId != null && roleId.trim().length() > 0)
		{
			String[] roleIdArray = roleId.split(",");
			for(String rid : roleIdArray)
			{
				tRoleResRelaDto.setRole_id(rid);
				super.delete(tRoleResRelaDto);
			}
		}
	 }


	 /**
	  * 为角色配置资源
	  * @param tRoleResRelaDto
	  */
	 public void configRoleResource(TRoleResRelaDto tRoleResRelaDto)
	 {
		 if(tRoleResRelaDto.getRole_id() == null
				 || tRoleResRelaDto.getRole_id().trim().length() <= 0)
		 {
			 return;
		 }
		 //通过角色id将原有的关联关系删除
		 TRoleResRelaDto delRoleResRela = new TRoleResRelaDto();
		 delRoleResRela.setRole_id(tRoleResRelaDto.getRole_id());
		 super.delete(delRoleResRela);
		 //保存新数据
		 String res_id = tRoleResRelaDto.getRes_id();
		 if(res_id != null && res_id.trim().length() > 0)
			{
				String[] resIdArray = res_id.split(",");
				for(String rid : resIdArray)
				{
					tRoleResRelaDto.setRole_res_id(null);
					tRoleResRelaDto.setRes_id(rid);
					super.save(tRoleResRelaDto);
				}
			}
	 }

}
