package com.wechat.fans.dao.impl;

import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;

import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.fans.dao.ITWeixinFansDao;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.model.vo.TWeixinFansVo;

/**
 * 类功能:粉丝
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tWeixinFansDao")
public class TWeixinFansDaoImpl extends BaseDaoImpl<TWeixinFansDto, String> implements ITWeixinFansDao{
	public TWeixinFansDaoImpl() {
		super(TWeixinFansDto.class);
	}
	
	
	/****
	 * 
	 * @param tWeixinFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWeixinFansListPage(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception {
	 	
		tWeixinFansQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
		if(tWeixinFansQueryBean.getFans_grouplist().length() != 0){
			String[] groupIdList = tWeixinFansQueryBean.getFans_grouplist().split(",");
			tWeixinFansQueryBean.setGroupId_list(groupIdList);
		}else{
			tWeixinFansQueryBean.setGroupId_list(null);
		}

		 PageResult prs = new PageResult(); 
	
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tWeixinFans.select_tWeixinFansList_count",tWeixinFansQueryBean);
	    	 //填充分页对象 以便构重新初始化对象
			 pagingObject.calculatePage(count.intValue());

		 //查询列表
		 List tWeixinFansList = this.getSqlMapClientTemplate().queryForList("tWeixinFans.select_tWeixinFansList", tWeixinFansQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 
		 prs.setResultList(tWeixinFansList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }

	   
	
		 /**
		   * 查询粉丝的粉丝群
		   * @param tWeixinFansQueryBean
		   * @return
		   */
	    public List<TWeixinFansGroupVo> queryAllWeixinFansGroup(TWeixinFansQueryBean tWeixinFansDto) throws Exception{
	    	String fans_group = " ";
	    	fans_group = "RE.FANS_ID='"+tWeixinFansDto.getFans_id()+"'";
	    	tWeixinFansDto.setFans_group(fans_group);
	    	 List<TWeixinFansGroupVo> tWeixinFansGroupVo = this.getSqlMapClientTemplate().queryForList("tWeixinFans.query_AllWeixinFansGroup",tWeixinFansDto );
	    	
	    	return tWeixinFansGroupVo;
	    }
	    
	    
	    /**
		   * 查询粉丝的详细
		   * @param tWeixinFansQueryBean
		   * @return
		   */
		public TWeixinFansVo getRowFans(TWeixinFansDto paramTWeixinFansDto) throws Exception{
			
			TWeixinFansVo tWeixinFansVo = (TWeixinFansVo) this.getSqlMapClientTemplate().queryForObject("tWeixinFans.query_RowWeixinFans",paramTWeixinFansDto);
			return tWeixinFansVo;
		}
		
		/**
		 * 更新T_WEIXIN_FANS.WEIXIN_GROUP_ID
		 */
		public int update_Fans_Group_ID(TWeixinFansDto tWeixinFansDto){
			return this.getSqlMapClientTemplate().update("tWeixinFans.update_fansgroupid",tWeixinFansDto);
		}

		/**
		 * 更新T_WEIXIN_FANS.Subscribe
		 */
		public int updateAllFansSubscribe(TWeixinFansDto tWeixinFansDto) {
			 return  this.getSqlMapClientTemplate().update("tWeixinFans.update_AllFansSubscribe", tWeixinFansDto);
			  
		}
		
		/**
		 * 获取有效的粉丝
		 */
		public List<TWeixinFansDto> getAllFans(TWeixinFansDto tWeixinFansDto) {
			List<TWeixinFansDto> tmpWeixinFansDto = this.getSqlMapClientTemplate().queryForList("tWeixinFans.select_AlltWeixinFans",tWeixinFansDto);
			return tmpWeixinFansDto;
		
		}

		/**
		 * 获取某群组下不存在的粉丝
		 */
		public PageResult queryTWeixinFansListPageByGroupId(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception {
			 tWeixinFansQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
			 
			 PageResult prs = new PageResult(); 
		
			 //查询记录总量信息
			 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tFansGroupMemberReal.queryTWeixinFansListPageByGroupId_Count",tWeixinFansQueryBean);
		    	 //填充分页对象 以便构重新初始化对象
				 pagingObject.calculatePage(count.intValue());

			 //查询列表
			 List tWeixinFansList = this.getSqlMapClientTemplate().queryForList("tFansGroupMemberReal.queryTWeixinFansListPageByGroupId_List", tWeixinFansQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
			 
			 prs.setResultList(tWeixinFansList);
			 prs.setPagingObject(pagingObject);
			 
			 return prs;
		}
}
