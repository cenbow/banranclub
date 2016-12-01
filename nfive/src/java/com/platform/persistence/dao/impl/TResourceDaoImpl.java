package com.platform.persistence.dao.impl;
import java.math.BigInteger;
import java.util.List;
import  org.springframework.context.annotation.Scope;
import  org.springframework.stereotype.Service;
import  com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.dao.ITResourceDao;
import com.platform.persistence.model.TResourceQueryBean;
import com.platform.persistence.model.dto.TResourceDto;

/**
 * 类功能:资源持久实现类
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p> 
 */
@Scope("prototype")
@Service("tResourceDao")
public class TResourceDaoImpl extends BaseDaoImpl<TResourceDto, String> implements ITResourceDao{
	public TResourceDaoImpl() {
		super(TResourceDto.class);
	}
	
	
	/****
	 * 
	 * @param tResourceQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	 public PageResult queryTResourceListPage(TResourceQueryBean tResourceQueryBean,PagingObject pagingObject) throws Exception
	 {	
		tResourceQueryBean.setOrderStr(" order by "+ pagingObject.getSort_name() +" "+pagingObject.getSort_order());	
		 PageResult prs = new PageResult(); 
		 
		 //查询记录总量信息
		 Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tResource.select_tResourceList_count",tResourceQueryBean);
		 
		 //填充分页对象 以便构重新初始化对象
		 pagingObject.calculatePage(count.intValue());
		 
		 
		 //查询列表
		 List tResourceList = this.getSqlMapClientTemplate().queryForList("tResource.select_tResourceList", tResourceQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		 prs.setResultList(tResourceList);
		 prs.setPagingObject(pagingObject);
		 
		 return prs;
	 }
	 
	 
	
	 
	 /**
	  * 获取资源编码
	  * @param tResourceQueryBean
	  * @return
	  */
	 public String getCode(TResourceQueryBean tResourceQueryBean)
	 {
		 //编码前缀
		 String prefix = "RS00";
		 //通过父id获取最大的子code
		 Object maxCode = this.getSqlMapClientTemplate().queryForObject("tResource.select_max_code",tResourceQueryBean);
		 if(maxCode != null && maxCode.toString().trim().length() > 0)
		 {
			 BigInteger bigInteger = new BigInteger(maxCode.toString().replace(prefix,""));
			 bigInteger = bigInteger.add(new BigInteger("1"));
			 return prefix + bigInteger.toString();
		 }
		 //查询父code
		 tResourceQueryBean.setRes_id(tResourceQueryBean.getParent_res_id());
		 String parentCode = this.getSqlMapClientTemplate().queryForObject("tResource.select_code",tResourceQueryBean).toString();
		 return parentCode + "001";
	 }
	 
	 /**
	  * 通过父节点查询所有的一级子节点
	  * @param tResourceQueryBean
	  * @return
	  */
	 public List<TResourceDto> getResourceByParent(TResourceQueryBean tResourceQueryBean)
	 {
		 List<TResourceDto> tResourceList = (List<TResourceDto>)this.getSqlMapClientTemplate().queryForList("tResource.selectResourceByParentRe", tResourceQueryBean);
		 return tResourceList;
	 }
	 
	 
	 /**
	  *  获取子节点对应的父节点
	  * @param tResourceQueryBean
	  * @return
	  */
	 public List<TResourceDto> selectParentReByChild(TResourceQueryBean tResourceQueryBean)
	 {
		 List list = (List<TResourceDto>)this.getSqlMapClientTemplate().queryForList("tResource.selectParentReByChildAndRole", tResourceQueryBean);
		 return list;
	 }

	 /**
	  * 通过用户id获取资源数据
	  */
	 public List<TResourceDto> getResourceByUserId(TResourceQueryBean resourceQueryBean){
		 List list = (List<TResourceDto>)this.getSqlMapClientTemplate().queryForList("tResource.selectResourceByUserId", resourceQueryBean);
		 return list;
	 }
	 
	 
	 
	 
}
