package com.wechat.pfusercfg.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.pfusercfg.dao.ITPlatformempCfgDao;
import com.wechat.pfusercfg.model.TPlatformempCfgQueryBean;
import com.wechat.pfusercfg.model.dto.TPlatformempCfgDto;
import com.wechat.pfusercfg.service.ITPlatformempCfgService;


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
@Service("tPlatformempCfgService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TPlatformempCfgServiceImpl extends GenericServiceImpl<TPlatformempCfgDto, String> implements ITPlatformempCfgService{
    @Autowired
    private ITPlatformempCfgDao   tPlatformempCfgDao;

	public BaseDao<TPlatformempCfgDto, String> getBaseDao() {
		return tPlatformempCfgDao;
	}
	

	/****
	 * @param  tPlatformempCfgQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTPlatformempCfgListPage(TPlatformempCfgQueryBean tPlatformempCfgQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tPlatformempCfgDao.queryTPlatformempCfgListPage(tPlatformempCfgQueryBean, pagingObject);
	 }
	 
	 /**
	  * 配置角色人员对应关系，包含关系数据的添加、删除
	  * @param tUserRoleRelaDto
	  */
	 public void configTPlatformempCfgList(TPlatformempCfgDto tPlatformempCfgDto,String doType)
	 {
		 String platformId = tPlatformempCfgDto.getPlatform_id();
		 String userId = tPlatformempCfgDto.getUser_cd();
		 if(platformId == null && platformId.trim().length() <= 0
				 && userId == null && userId.trim().length() <= 0)
		 {
			 return;
		 }
		 String[] platformIdArray = platformId.split(",");
		 String[] userIdArray = userId.split(",");
		 //执行保存
		 for(String platformId_ : platformIdArray)
		 {
			 for(String userId_ : userIdArray)
			 {
				 tPlatformempCfgDto.setPlatform_id(platformId_);
				 tPlatformempCfgDto.setUser_cd(userId_);
				 saveOrUpdate(tPlatformempCfgDto,doType);
			 }
		 }
	 }
	 
	 /**
	  * 根据传入的操作类型执行保存/删除
	  * @param tUserRoleRelaDto 角色用户关系对象
	  * @param doType 操作类型
	  */
	 private void saveOrUpdate(TPlatformempCfgDto tPlatformempCfgDto,String doType)
	 {

		 if(doType.equals("add"))
		 {
			 TPlatformempCfgDto addParam = new TPlatformempCfgDto();
			 addParam.setIs_use(CodeStringConstant.CS_1000_TRUE);//查询是否有默认绑定的参数。
			 addParam.setUser_cd(tPlatformempCfgDto.getUser_cd());//查询绑定的用户的参数。
			 List<TPlatformempCfgDto> platformempCfgList =  this.getAll(addParam);//查询是否存在一个用户有已绑定使用的公众微信号。
			 if(platformempCfgList.size()==0)
			 {//如果没有绑定并使用的微信号，默认绑定当前使用。
				 tPlatformempCfgDto.setCfg_id(null);
				 tPlatformempCfgDto.setIs_use(CodeStringConstant.CS_1000_TRUE);
				 tPlatformempCfgDto.setIs_valid(CodeStringConstant.CS_1000_TRUE);
				 super.save(tPlatformempCfgDto);
			 }else
			 {//如果有绑定并使用的微信号，默认不绑定当前使用。
				 tPlatformempCfgDto.setCfg_id(null);
				 tPlatformempCfgDto.setIs_use(CodeStringConstant.CS_1000_FALSE);
				 tPlatformempCfgDto.setIs_valid(CodeStringConstant.CS_1000_TRUE);
				 super.save(tPlatformempCfgDto);
				 
			 }
		 }else if(doType.equals("delete"))
		 {
			     super.delete(tPlatformempCfgDto);
		 }
	 }
	
	 
}
