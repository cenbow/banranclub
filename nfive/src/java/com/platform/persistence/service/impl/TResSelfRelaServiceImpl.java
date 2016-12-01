package com.platform.persistence.service.impl;
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
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.persistence.dao.ITResSelfRelaDao;
import com.platform.persistence.dao.ITResourceDao;
import com.platform.persistence.model.TResSelfRelaQueryBean;
import com.platform.persistence.model.TResourceQueryBean;
import com.platform.persistence.model.dto.TResSelfRelaDto;
import com.platform.persistence.model.dto.TResourceDto;
import com.platform.persistence.service.ITResSelfRelaService;
import com.platform.persistence.util.resource.tree.Tree;
import com.platform.persistence.util.resource.tree.TreeUtil;


/**
 * 类功能:资源关系服务实现类
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
@Service("tResSelfRelaService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TResSelfRelaServiceImpl extends GenericServiceImpl<TResSelfRelaDto, String> implements ITResSelfRelaService{
    @Autowired
    private ITResSelfRelaDao   tResSelfRelaDao;
    @Autowired
    private ITResourceDao   tResourceDao;

	public BaseDao<TResSelfRelaDto, String> getBaseDao() {
		return tResSelfRelaDao;
	}
	

	/****
	 * @param  tResSelfRelaQueryBean
	 * @param  pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	 public PageResult queryTResSelfRelaListPage(TResSelfRelaQueryBean tResSelfRelaQueryBean,PagingObject pagingObject) throws Exception
	 {	
		 	return this.tResSelfRelaDao.queryTResSelfRelaListPage(tResSelfRelaQueryBean, pagingObject);
	 }
	 
	 /**
	  * 将资源关系数据转换为tree
	  */
	 public Tree queryTResSelfRelaList(TResSelfRelaQueryBean tResSelfRelaQueryBean) throws Exception
	 {
		 List list = tResSelfRelaDao.queryTResSelfRelaList(tResSelfRelaQueryBean);
		 //转换为树集合
		 List<Tree> treeList = convertTResSelfRelaToTree(list);
		 //将树集合组装为树形结构
		 Tree tree = TreeUtil.getTreeStructure(treeList);
		 return tree;
	 }
	 
	 
	 /**
	  * 修改资源节点结构
	  */
	 public void updateResRela(TResSelfRelaDto tResSelfRelaDto)
	 {
		 //获取资源数据
		 TResourceDto tResourceDto = new TResourceDto();
		 tResourceDto.setRes_id(tResSelfRelaDto.getChild_res_id());
		 //重新设置编码
		 TResourceQueryBean tResourceQueryBean = new TResourceQueryBean();
		 tResourceQueryBean.setParent_res_id(tResSelfRelaDto.getParent_res_id());
		 tResourceDto.setRes_code(tResourceDao.getCode(tResourceQueryBean));
		 //修改资源编码
		 tResourceDao.updatePK(tResourceDto);
		 //通过child_id获取资源关系记录 
		 TResSelfRelaDto resSearch = new TResSelfRelaDto();
		 resSearch.setChild_res_id(tResSelfRelaDto.getChild_res_id());
		 resSearch = this.getRow(resSearch);
		 //修改资源关系
		 tResSelfRelaDto.setRes_relation_id(resSearch.getRes_relation_id());
		 super.updatePK(tResSelfRelaDto);
	 }
	 
	 /**
	  * 通过父资源id删除与子资源对应的关系数据 逻辑删除
	  */
	 public void deleteResRelaByParentId(TResSelfRelaQueryBean tResSelfRelaQueryBean)
	 {
		 tResSelfRelaDao.deleteResRelaByParentId(tResSelfRelaQueryBean);
	 }
	 
	 /**
	  * 转换为树集合
	  * @param data
	  * @return
	  */
	 private List<Tree> convertTResSelfRelaToTree(List data)
	 {
		 List<Tree> treeList = new ArrayList<Tree>();
		 for(int i = 0;data!=null&&i<data.size();i++)
		 {
			 Map map = (Map)data.get(i);
			 Tree tree = new Tree(map.get("CHILD_RES_ID").toString()
					 	,map.get("PARENT_RES_ID").toString()
					 	,map.get("RES_NAME").toString()
					 	,map.get("RES_CODE").toString()
					 	,map.get("CHECKED").toString().equals("true")?true:false);
			 treeList.add(tree);
		 }
		 return treeList;
	 }
	 
	
}
