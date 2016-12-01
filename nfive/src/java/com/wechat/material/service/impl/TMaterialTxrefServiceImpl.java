package com.wechat.material.service.impl;
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
import com.wechat.material.dao.ITMaterialTxrefDao;
import com.wechat.material.model.dto.TMaterialTxrefDto;
import com.wechat.material.service.ITMaterialTxrefService;



/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tMaterialTxrefService")
@Transactional (propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TMaterialTxrefServiceImpl extends GenericServiceImpl<TMaterialTxrefDto, String> implements ITMaterialTxrefService{
    @Autowired
    private ITMaterialTxrefDao   tMaterialTxrefDao;

	public BaseDao<TMaterialTxrefDto, String> getBaseDao() {
		return tMaterialTxrefDao;
	}
	

	
}
