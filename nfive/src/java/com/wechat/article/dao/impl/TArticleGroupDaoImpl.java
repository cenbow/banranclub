package com.wechat.article.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.dao.ITArticleGroupDao;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.model.vo.ArticleItemVo;

/**
 * 类功能:图文组
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Scope("prototype")
@Service("tArticleGroupDao")
public class TArticleGroupDaoImpl extends BaseDaoImpl<TArticleGroupDto, String> implements ITArticleGroupDao{
	public TArticleGroupDaoImpl() {
		super(TArticleGroupDto.class);
	}

	/****
	*
	* @param tArticleGroupQueryBean
	* @param pagingObject
	* @return
	* @throws Exception
	*/
    public PageResult queryTArticleGroupListPage(TArticleGroupQueryBean tArticleGroupQueryBean,PagingObject pagingObject) throws Exception {

        tArticleGroupQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
        PageResult prs = new PageResult();

        //查询记录总量信息
        Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("article.select_tArticleGroupList_count",tArticleGroupQueryBean);

        //填充分页对象 以便构重新初始化对象
        pagingObject.calculatePage(count.intValue());

        //查询列表
        List tArticleGroupList = this.getSqlMapClientTemplate().queryForList("article.select_tArticleGroupList", tArticleGroupQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
        prs.setResultList(tArticleGroupList);
        prs.setPagingObject(pagingObject);

        return prs;
    }

    /**
     * 取得图文组基本信息
     * 
     * @param tArticleGroupQueryBean
     * @return TArticleGroupQueryBean
     * @throws Exception
     */
    public TArticleGroupQueryBean getArticleGroup(TArticleGroupQueryBean tArticleGroupQueryBean) throws Exception {

		return (TArticleGroupQueryBean)getSqlMapClientTemplate().queryForObject("article.select_getArticleGroup", tArticleGroupQueryBean);
	}
    
    /**
     * 取得指定图文组中符合发布条件的图文项
     * @param articleGroupId
     * @param pubBaseDate
     * 
     */
    @SuppressWarnings("unchecked")
	public List<ArticleItemVo> getArticleGroupPublishableItems(String articleGroupId, Date pubBaseDate) throws Exception {
    	Map<String,Object> param = new HashMap<String,Object>(2);
    	param.put("article_group_id", articleGroupId);
    	param.put("pub_base_date", pubBaseDate);
    	
		return (List<ArticleItemVo>) getSqlMapClientTemplate().queryForList("article.select_getArticleGroupPublishableItems", param);
    }

}
