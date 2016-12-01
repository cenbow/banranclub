package com.wechat.article.dao.impl;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.article.dao.ITArticleItemDao;
import com.wechat.article.model.TArticleItemQueryBean;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.vo.ArticleItemVo;

/**
 * 类功能:图文项
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
@Service("tArticleItemDao")
public class TArticleItemDaoImpl extends BaseDaoImpl<TArticleItemDto, String> implements ITArticleItemDao{
    public TArticleItemDaoImpl() {
        super(TArticleItemDto.class);
    }


    /****
    * 图文项列表
    * @param tArticleItemQueryBean
    * @param pagingObject
    * @return
    * @throws Exception
    */
    public PageResult queryTArticleItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception {

        tArticleItemQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
        PageResult prs = new PageResult();

        //查询记录总量信息
        Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("article.select_tArticleItemList_count",tArticleItemQueryBean);

        //填充分页对象 以便构重新初始化对象
        pagingObject.calculatePage(count.intValue());

        //查询列表
        List tArticleItemList = this.getSqlMapClientTemplate().queryForList("article.select_tArticleItemList", tArticleItemQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
        prs.setResultList(tArticleItemList);
        prs.setPagingObject(pagingObject);

        return prs;
    }

    /****
     * 根据图文关系取得图文项列表
     * @param tArticleItemQueryBean
     * @param pagingObject
     * @return
     * @throws Exception
     */
     public PageResult queryTArticleGroupItemListPage(TArticleItemQueryBean tArticleItemQueryBean,PagingObject pagingObject) throws Exception {

         // tArticleItemQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());
     	 //图文项的排序顺序为：有效 -> 无效 , 权重大(1开始) -> 权重小(数值大) , 更新时间最新排前面
    	 tArticleItemQueryBean.setOrderStr(" ORDER BY  ARTICLE_STATE  ASC, RELA_SORT  ASC , UPDATED_DATE DESC ");
         PageResult prs = new PageResult();

         //查询记录总量信息
         Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("article.select_tArticleGroupItemList_count",tArticleItemQueryBean);

         //填充分页对象 以便构重新初始化对象
         pagingObject.calculatePage(count.intValue());

         //查询列表
         List tArticleItemList = this.getSqlMapClientTemplate().queryForList("article.select_tArticleGroupItemList", tArticleItemQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
         prs.setResultList(tArticleItemList);
         prs.setPagingObject(pagingObject);

         return prs;
     }

    /**
     * 根据图文关系取得图文项
     * @param tArticleRelaQueryBean
     * @return ArticleItemVo
     */
	public ArticleItemVo getArticleGroupItem(TArticleRelaQueryBean tArticleRelaQueryBean) throws Exception {
		 return (ArticleItemVo)getSqlMapClientTemplate().queryForObject("article.select_getArticleGroupItem", tArticleRelaQueryBean);
	}
}
