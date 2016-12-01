package com.wechat.crowdsend.dao;
import java.util.List;
import java.util.Map;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendUesrDto;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.hercules.database.dao.BaseDao;

/**
 * 类功能:群发管理 发送表操作接口
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITMsgSendDao extends BaseDao<TMsgSendDto,String>{	

	/****
	 * 
	 * @param tMsgSendQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendListPage(TMsgSendQueryBean tMsgSendQueryBean,PagingObject pagingObject) throws Exception;
	 /**
	  * 查询发送对象列表
	  * @param TWeixinFansQueryBean 发送对象所在粉丝组
	  * @return	发送对象openid列表
	  * @throws Exception
	  */
	 public List<String> querySendTarget(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) throws Exception;
	 /****
		 * 查询单批次内发送成员信息
		 * @param tMsgSendQueryBean	粉丝信息
		 * @param pagingObject
		 * @return
		 * @throws Exception
		 */
	 public PageResult queryTMsgSendMemberListPage(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception;
	 /**
	  * 查询剩余发送次数
	  * @param tMsgSendQueryBean
	  * @return
	  * @throws Exception
	  */
	 public List<Map<String,String>> queryForLastSendTime(TMsgSendQueryBean tMsgSendQueryBean)throws Exception;
	 
	 /**
	  * 生成新的批次号
	  * @return
	  * @throws Exception
	  */
	 public String newBatchNo() throws Exception;
	 
	 
	 /**
	  * 查询消息详细内容
	  * @param tMsgSendQueryBean 消息主表id，发送结果表id
	  * @return 消息详细内容
	 * @throws Exception 
	  */
	 public TMsgSendDto msgDetail(TMsgSendQueryBean tMsgSendQueryBean) throws Exception;
	 
	 /**
	  * 自定义发送时：对于发送列表批次插入
	  * @param tMsgSendUesrDto	发送对象列表
	  * @throws Exception
	  */
	 public void saveMsgSend(final List<TMsgSendUesrDto> tMsgSendUesrDto) throws Exception;
}
