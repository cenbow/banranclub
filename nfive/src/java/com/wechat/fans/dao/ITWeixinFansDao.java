package com.wechat.fans.dao;
import java.util.List;

import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.model.vo.TWeixinFansVo;
import com.hercules.database.dao.BaseDao;

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
public interface ITWeixinFansDao extends BaseDao<TWeixinFansDto,String>{	

	/****
	 * 粉丝一览
	 * @param tWeixinFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTWeixinFansListPage(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception;
     
	 /**
	   * 查询粉丝的粉丝群
	   * @param tWeixinFansQueryBean
	   * @return
	   */
  public List<TWeixinFansGroupVo> queryAllWeixinFansGroup(TWeixinFansQueryBean tWeixinFansDto) throws Exception;
	  /**
	   * 查询粉丝的详细
	   * @param tWeixinFansQueryBean
	   * @return
	   */
	public TWeixinFansVo getRowFans(TWeixinFansDto paramTWeixinFansDto) throws Exception;

	  /**
		 * 更新T_WEIXIN_FANS.WEIXIN_GROUP_ID
		 * @param tWeixinFansDto 
		 */
		public int update_Fans_Group_ID(TWeixinFansDto tWeixinFansDto);
		
		/**
		 * 更新T_WEIXIN_FANS.Subscribe
		 */
		public int updateAllFansSubscribe(TWeixinFansDto tWeixinFansDto);
		
		/**
		 * 获取有效的粉丝
		 */
		public List<TWeixinFansDto> getAllFans(TWeixinFansDto tWeixinFansDto);
		
		/**
		 * 获取某个粉丝群组下不存在的粉丝
		 * @param tWeixinFansQueryBean
		 * @param pagingObject
		 * @return
		 * @throws Exception
		 */
		 public PageResult queryTWeixinFansListPageByGroupId(TWeixinFansQueryBean tWeixinFansQueryBean,PagingObject pagingObject) throws Exception;
		
}
