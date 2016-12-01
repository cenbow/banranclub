package com.wechat.crowdsend.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.wechat.crowdsend.dao.ITMsgSendDao;
import com.wechat.crowdsend.dao.ITMsgSendGroupDao;
import com.wechat.crowdsend.dao.ITMsgSendResultDao;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendGroupDto;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.wechat.crowdsend.model.dto.TMsgSendUesrDto;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.crowdsend.web.action.CustomTMsgSendPage;
import com.wechat.fans.dao.ITWeixinFansDao;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;

/**
 * 类功能:群发管理数据库操作
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tMsgSendService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TMsgSendServiceImpl extends GenericServiceImpl<TMsgSendDto, String> implements ITMsgSendService{
    @Autowired
    private ITMsgSendDao   tMsgSendDao;
    @Autowired
    private ITMsgSendResultDao   tMsgSendResultDao;
    @Autowired
    private ITMsgSendGroupDao  tMsgSendGroupDao;
    @Autowired
    private ITWeixinFansDao  tWeixinFansDao;
    
	public BaseDao<TMsgSendDto, String> getBaseDao() {
		return tMsgSendDao;
	}
	

	/****
	 * @param  tMsgSendQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendListPage(TMsgSendQueryBean tMsgSendQueryBean,PagingObject pagingObject) throws Exception{
		
		 	return this.tMsgSendDao.queryTMsgSendListPage(tMsgSendQueryBean, pagingObject);
	 }
	 	
	 //查询当天剩余发送次数（100-x）
	 public Map<String,String> queryForLastSendTime(String plantFromId)throws Exception{
		 TMsgSendQueryBean tMsgSendQueryBean = new TMsgSendQueryBean();
		 tMsgSendQueryBean.setPlatform_id(plantFromId);
		 //返回剩余发送次数 100-已发送次数
		 return tMsgSendDao.queryForLastSendTime(tMsgSendQueryBean).get(0);
	 }
	 
	 //生成一个新的批次号
	 public String newBatchNo() throws Exception{
		 
		 return tMsgSendDao.newBatchNo();
	 }
	 
	 //新增一个发送记录
	 public void saveupdateTMsgSendDto(List<TMsgSendDto> tMsgSendDto,List<TMsgSendResultDto> tMsgSendResultDto,List<TMsgSendGroupDto> tMsgSendGroupDto,List<TMsgSendUesrDto> tMsgSendUesrDto) throws Exception{
		 	
		 //循环插入一个发送结果
		 if(null != tMsgSendResultDto){
			 for(TMsgSendResultDto obj:tMsgSendResultDto){
				 tMsgSendResultDao.save(obj);
			 }
		 }
		 
		 //批量插入发送组信息
		 if(null != tMsgSendGroupDto){
			 for(TMsgSendGroupDto obj:tMsgSendGroupDto){
				 tMsgSendGroupDao.save(obj);
			 }
		 }
		 
		 //对于多个openid 进行批次处理
		 if(null != tMsgSendUesrDto){
			 tMsgSendDao.saveMsgSend(tMsgSendUesrDto);
		 }
		 
		 for(TMsgSendDto obj:tMsgSendDto){
			 tMsgSendDao.save(obj);
		 }
	 }
	 
	 //回调处理：更新发送结果
	 public Integer updateTMsgSendDto(TMsgSendDto tMsgSendDto,TMsgSendResultDto tMsgSendResultDto) throws Exception{
		 tMsgSendResultDao.updatePK(tMsgSendResultDto);
		 return tMsgSendDao.updatePK(tMsgSendDto);
	 }
	 
	 //消息详细信息
	 public TMsgSendDto msgDetail(TMsgSendQueryBean tMsgSendQueryBean) throws Exception{
		 
		 return tMsgSendDao.msgDetail(tMsgSendQueryBean);
	 }

	 //查询单批次内发送成员信息
	 public PageResult queryTMsgSendMemberListPage(TWeixinFansQueryBean tWeixinFansQueryBean, PagingObject pagingObject)throws Exception {
		
		return tMsgSendDao.queryTMsgSendMemberListPage(tWeixinFansQueryBean, pagingObject);
	}

	 //查询发送对象列表(粉丝组)
	 public List<String> querySendTarget(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) throws Exception {
		return tMsgSendDao.querySendTarget(tFansGroupMemberRealQueryBean);
	}
	 
}
