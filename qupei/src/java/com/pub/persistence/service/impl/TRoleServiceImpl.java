package com.pub.persistence.service.impl;
import java.util.List;

import com.pub.common.local.model.dto.TRoleDto;
import com.pub.common.local.model.dto.TRoleResRelaDto;
import com.pub.common.local.model.dto.TUserRoleRelaDto;
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
import com.pub.persistence.dao.ITRoleDao;
import com.pub.persistence.model.TRoleQueryBean;
import com.pub.persistence.service.ITRoleResRelaService;
import com.pub.persistence.service.ITRoleService;
import com.pub.persistence.service.ITUserRoleRelaService;


/**
 * 类功能:角色服务实现类
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
@Service("tRoleService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TRoleServiceImpl extends GenericServiceImpl<TRoleDto, String> implements ITRoleService{
    @Autowired
    private ITRoleDao   tRoleDao;
    @Autowired
    private ITRoleResRelaService tRoleResRelaService;
    @Autowired
    private ITUserRoleRelaService tUserRoleRelaService;

	public BaseDao<TRoleDto, String> getBaseDao() {
		return tRoleDao;
	}


	/****
	 * @param  tRoleQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTRoleListPage(TRoleQueryBean tRoleQueryBean,PagingObject pagingObject) throws Exception
	 {
		 	return this.tRoleDao.queryTRoleListPage(tRoleQueryBean, pagingObject);
	 }

	/**
	 * 对角色校验
	 */
	public boolean checkRole(String doType,TRoleDto tRoleDto) {
		TRoleDto searchRoleDto = null;
		//修改校验
		if(doType.equals("edit"))
		{
			searchRoleDto = new TRoleDto();
			searchRoleDto.setRole_id(tRoleDto.getRole_id());
			TRoleDto tRoleDto_ = this.getRow(searchRoleDto);
			if(tRoleDto_ != null
					&& tRoleDto_.getRole_name().equals(tRoleDto.getRole_name()))
			{
				return true;
			}
		}
		//直接通过名称查询数据库中是否有重名
		searchRoleDto = new TRoleDto();
		searchRoleDto.setRole_name(tRoleDto.getRole_name());
		List<TRoleDto> roleList = this.getAll(searchRoleDto);
		if(roleList == null || roleList.size() <= 0)
		{
			return true;
		}
		return false;
	}


	/**
	 * 对角色删除
	 */
	public String deleteRole(TRoleDto tRoleDto)
	{
		String role_id = tRoleDto.getRole_id();
		//校验角色资源关系数据
		if(checkRoleResourceRela(role_id))
		{
			 return "{\"status\":"+false+",\"message\":\""+"您要删除的角色存在资源权限数据,请先将权限数据删除!"+"\"}";
		}
		//校验角色用户关系数据
		if(checkRoleUserRela(role_id))
		{
			 return "{\"status\":"+false+",\"message\":\""+"您要删除的角色存在员工对应数据,请先将这些数据删除!"+"\"}";
		}
		//执行删除
		super.deletePK(tRoleDto);
		return "{\"status\":"+true+",\"message\":\""+""+"\"}";
	}

	/**
	 * 通过用户获取角色信息
	 * @param tRoleQueryBean
	 * @return
	 */
	public List<TRoleDto> getRoleByUser(TRoleQueryBean tRoleQueryBean)
	{
		return tRoleDao.getRoleByUser(tRoleQueryBean);
	}


	/**
	 * 校验是否存在角色权限数据
	 * @param role_id
	 * @return
	 */
	private boolean checkRoleResourceRela(String role_id)
	{
		TRoleResRelaDto tRoleResRelaDto = new TRoleResRelaDto();
		tRoleResRelaDto.setRole_id(role_id);
		List<TRoleResRelaDto> tRoleResRelaDtoList = tRoleResRelaService.getAll(tRoleResRelaDto);
		return tRoleResRelaDtoList != null && tRoleResRelaDtoList.size() > 0;
	}

	/**
	 * 校验是否存在角色用户数据
	 * @param role_id
	 * @return
	 */
	private boolean checkRoleUserRela(String role_id)
	{
		TUserRoleRelaDto tUserRoleRelaDto = new TUserRoleRelaDto();
		tUserRoleRelaDto.setRole_id(role_id);
		List<TUserRoleRelaDto> tUserRoleRelaDtoList = this.tUserRoleRelaService.getAll(tUserRoleRelaDto);
		return tUserRoleRelaDtoList != null && tUserRoleRelaDtoList.size() > 0;
	}







}
