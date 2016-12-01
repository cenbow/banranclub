package com.wechat.fans.service.impl;

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
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.dao.ITFansGroupMemberRealDao;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.service.ITFansGroupMemberRealService;
import com.wechat.fans.service.ITWeixinFansService;


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
@Service("tFansGroupMemberRealService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TFansGroupMemberRealServiceImpl extends GenericServiceImpl<TFansGroupMemberRealDto, String> implements ITFansGroupMemberRealService{
    @Autowired
    private ITFansGroupMemberRealDao   tFansGroupMemberRealDao;
    @Autowired
    private ITWeixinFansService   tWeixinFansService;

	public BaseDao<TFansGroupMemberRealDto, String> getBaseDao() {
		return tFansGroupMemberRealDao;
	}
	

	/****
	 * @param  tFansGroupMemberRealQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTFansGroupMemberRealListPage(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tFansGroupMemberRealDao.queryTFansGroupMemberRealListPage(tFansGroupMemberRealQueryBean, pagingObject);
	 }


	 /**
	  * 
	  */
	public Integer delfansByPkids(String pkids) throws Exception {
		return this.tFansGroupMemberRealDao.delfansByPkids(pkids);
		
	}
	/****
	 * 查询粉丝所在的粉丝群
	 */
	 public PageResult queryTFansGroupList(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tFansGroupMemberRealDao.queryTFansGroupList(tFansGroupMemberRealQueryBean, pagingObject);
	 }


	public Integer addfansByList(String fansGroupPkids, String fansPkids) throws Exception {
		
		int result = 0;
		String[] fansPkidArr = fansPkids.split("\\$");
		for(String fanspkid:fansPkidArr){
			TWeixinFansDto param_tWeixinFansDto = new TWeixinFansDto();
			param_tWeixinFansDto.setFans_id(fanspkid);
			//1.查询出该粉丝
			TWeixinFansDto result_tWeixinFansDto = this.tWeixinFansService.getRow(param_tWeixinFansDto);
			
			if (null != result_tWeixinFansDto){
				TFansGroupMemberRealDto tFansGroupMemberRealDto = new TFansGroupMemberRealDto();
				tFansGroupMemberRealDto.setFans_group_id(fansGroupPkids);//设置粉丝群组ID
				tFansGroupMemberRealDto.setFans_id(fanspkid);//设置粉丝ID
				tFansGroupMemberRealDto.setMember_name(result_tWeixinFansDto.getNick_name());//设置粉丝名称
				tFansGroupMemberRealDto.setStar_member(CodeStringConstant.CS_1000_FALSE);//设置是否星标成员
				tFansGroupMemberRealDto.setMember_memo(null);
				tFansGroupMemberRealDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());//设置创建人
				tFansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
				TFansGroupMemberRealDto result_tFansGroupMemberRealDto = this.tFansGroupMemberRealDao.save(tFansGroupMemberRealDto);
				if (null != result_tFansGroupMemberRealDto.getReal_id()){
					result = result + 1;
				}
			}
		}
		return result;
	}
	
	/**
	 * 增加所有粉丝
	 */
	public Integer addfansAll(String fansGroupPkids) throws Exception {
		int result = 0;
		//取得所有粉丝
		TWeixinFansDto param_tWeixinFansDto = new TWeixinFansDto();
		List<TWeixinFansDto>  tWeixinFansDtoList = tWeixinFansService.getAll(param_tWeixinFansDto);	
		
		//删除当前组的粉丝
		TFansGroupMemberRealDto delDto = new TFansGroupMemberRealDto();
		delDto.setFans_group_id(fansGroupPkids);
		tFansGroupMemberRealDao.delete(delDto);
		
		for(TWeixinFansDto fan:tWeixinFansDtoList){
			TFansGroupMemberRealDto tFansGroupMemberRealDto = new TFansGroupMemberRealDto();
			tFansGroupMemberRealDto.setFans_group_id(fansGroupPkids);//设置粉丝群组ID
			tFansGroupMemberRealDto.setFans_id(fan.getFans_id());//设置粉丝ID
			tFansGroupMemberRealDto.setMember_name(fan.getNick_name());//设置粉丝名称
			tFansGroupMemberRealDto.setStar_member(CodeStringConstant.CS_1000_FALSE);//设置是否星标成员
			tFansGroupMemberRealDto.setMember_memo(null);
			tFansGroupMemberRealDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());//设置创建人
			tFansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			TFansGroupMemberRealDto result_tFansGroupMemberRealDto = this.tFansGroupMemberRealDao.save(tFansGroupMemberRealDto);
			if (null != result_tFansGroupMemberRealDto.getReal_id()){
				result = result + 1;
			}
		}
		return result;
	}
	
	
	 /**
	  * 查询粉丝已有的粉丝群
	  */
	 public List<TFansGroupMemberRealDto> getFansGroup(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) {
		return this.tFansGroupMemberRealDao.getFansGroup(tFansGroupMemberRealQueryBean);
	}
}
