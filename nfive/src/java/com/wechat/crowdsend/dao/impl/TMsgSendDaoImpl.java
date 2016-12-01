package com.wechat.crowdsend.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.crowdsend.dao.ITMsgSendDao;
import com.wechat.crowdsend.model.TMsgSendQueryBean;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendUesrDto;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.TWeixinFansQueryBean;

import  org.springframework.context.annotation.Scope;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 类功能:群发管理 发送记录表数据库操作
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
@Service("tMsgSendDao")
public class TMsgSendDaoImpl extends BaseDaoImpl<TMsgSendDto, String> implements ITMsgSendDao{
	public TMsgSendDaoImpl() {
		super(TMsgSendDto.class);      
	}
	
	
	/****
	 * 
	 * @param tMsgSendQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTMsgSendListPage(TMsgSendQueryBean tMsgSendQueryBean,PagingObject pagingObject) throws Exception {
	 	String sort_name=pagingObject.getSort_name()==null?"SEND_TIME":pagingObject.getSort_name();
		tMsgSendQueryBean.setOrderStr(" order by "+ sort_name +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tMsgSend.select_tMsgSendList_count",tMsgSendQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 //查询列表
		 List tMsgSendList = this.getSqlMapClientTemplate().queryForList("tMsgSend.select_tMsgSendList", tMsgSendQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tMsgSendList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	 
	 /****
		 * 
		 * @param tMsgSendQueryBean
		 * @param pagingObject
		 * @return
		 * @throws Exception
		 */
		 public PageResult queryTMsgSendMemberListPage(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception {
		 	String sort_name=pagingObject.getSort_name()==null?"SUBSCRIBE_TIME":pagingObject.getSort_name();
		 	tWeixinFansQueryBean.setOrderStr(" order by "+ sort_name +" "+pagingObject.getSort_order());	
			 PageResult prs = new PageResult(); 
			 
			 //查询记录总量信息
			 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tMsgSend.select_tMsgSendMemberList_count",tWeixinFansQueryBean);
			 
			 //填充分页对象 以便构重新初始化对象
			 pagingObject.calculatePage(count.intValue());
			 
			 //查询列表
			 List tMsgSendMemberList = this.getSqlMapClientTemplate().queryForList("tMsgSend.select_tMsgSendMemberList", tWeixinFansQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
			 prs.setResultList(tMsgSendMemberList);
			 prs.setPagingObject(pagingObject);
			 
			 return prs;
		 }
	 public List<Map<String,String>> queryForLastSendTime(TMsgSendQueryBean tMsgSendQueryBean) throws Exception {
		 
		return this.getSqlMapClientTemplate().queryForList("tMsgSend.select_sendtime",tMsgSendQueryBean);
	 }
	 
	 //生成新的批次号
	 public String newBatchNo() throws Exception {
			 
		 return this.getSqlMapClientTemplate().queryForObject("tMsgSend.select_batch_no").toString();
	 }
	 
	 
	 
	 //消息详细
	 public TMsgSendDto msgDetail(TMsgSendQueryBean tMsgSendQueryBean) throws Exception {
		 
		 return (TMsgSendDto) this.getSqlMapClientTemplate().queryForObject("tMsgSend.sel_msgDetail", tMsgSendQueryBean);
	 }
	 
	 //批量插入openid
	 public void saveMsgSend(final List<TMsgSendUesrDto> tMsgSendUesrDto) throws Exception {
			// 执行回调  
			    this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {  
			        // 实现回调接口  
			        public Object doInSqlMapClient(SqlMapExecutor executor)  
			                throws SQLException {  
			            // 开始批处理  
			            executor.startBatch();  
			            for (TMsgSendUesrDto obj : tMsgSendUesrDto) {  
			                // 插入操作  
			                executor.insert("tMsgSend.insert_user", obj);  
			            }  
			            // 执行批处理  
			            executor.executeBatch();  
			            return null;  
			        }  
			    });  
	 }


	public List<String> querySendTarget(TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) throws Exception {
		
		return this.getSqlMapClientTemplate().queryForList("tMsgSend.select_customSendTarget",tFansGroupMemberRealQueryBean);
	}
}
