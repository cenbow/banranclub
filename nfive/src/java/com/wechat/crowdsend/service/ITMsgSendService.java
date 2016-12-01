package com.wechat.crowdsend.service;
import java.util.List;
import java.util.Map;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendGroupDto;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.wechat.crowdsend.model.dto.TMsgSendUesrDto;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.hercules.database.service.IGenericService;


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
public interface ITMsgSendService extends IGenericService<TMsgSendDto, String>{

	/****
	 * 
	 * @param tMsgSendQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendListPage(TMsgSendQueryBean tMsgSendQueryBean,PagingObject pagingObject) throws Exception;
	 
	 /****
		 * 查询单批次内发送成员信息
		 * @param tMsgSendQueryBean
		 * @param pagingObject
		 * @return
		 * @throws Exception
		 */
	 public PageResult queryTMsgSendMemberListPage(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception;
	 /**
	  * 创建发送表与发送结果表记录
	  * @param tMsgSendDto	发送表对象
	  * @param tMsgSendResultDto	发送结果表对象
	  * @return	若返回值会null 则保存失败
	  * @throws Exception
	  */
	 public void saveupdateTMsgSendDto(List<TMsgSendDto> tMsgSendDto,List<TMsgSendResultDto> tMsgSendResultDto,List<TMsgSendGroupDto> tMsgSendGroupDto,List<TMsgSendUesrDto> tMsgSendUesrDto) throws Exception;
	 
	 /**
	  * 收到发送结果后更新主表记录
	  * @param tMsgSendDto	待更新内容
	  * @return	受影响条数
	  * @throws Exception
	  */
	 public Integer updateTMsgSendDto(TMsgSendDto tMsgSendDto,TMsgSendResultDto tMsgSendResultDto) throws Exception;
	 
	 /**
	  * 生成b批次号
	  * @return 批次号
	  * @throws Exception
	  */
	 public String newBatchNo() throws Exception;

	 /**
	  * 查询当日剩余发送次数
	  * @param plantFromId	当前公众号ID
	  * @return	剩余发送次数
	  * @throws Exception
	  */
	 public Map<String,String> queryForLastSendTime(String plantFromId) throws Exception;

	 /**
	  * 查询消息详细信息
	  * @param tMsgSendQueryBean 消息发送主表id，消息发送结果表id
	  * @return 消息详细信息
	 * @throws Exception 
	  */
	 public TMsgSendDto msgDetail(TMsgSendQueryBean tMsgSendQueryBean) throws Exception;
	 
	 /**
	  * 查询发送对象列表
	  * @param tWeixinFansQueryBean 粉丝所在粉丝群id
	  * @return	发送对象openid列表
	  * @throws Exception
	  */
	 public List<String> querySendTarget(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) throws Exception;
	 
}
