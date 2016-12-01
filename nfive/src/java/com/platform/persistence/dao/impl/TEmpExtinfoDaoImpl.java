package com.platform.persistence.dao.impl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.platform.persistence.dao.ITEmpExtinfoDao;
import com.platform.persistence.model.dto.TEmpExtinfoDto;

/**
 * 类功能:自动代码生成模板 DaoImpl 模板
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
@Service("tEmpExtinfoDao")
public class TEmpExtinfoDaoImpl extends BaseDaoImpl<TEmpExtinfoDto, String> implements ITEmpExtinfoDao{
	public TEmpExtinfoDaoImpl() {
		super(TEmpExtinfoDto.class);
	}	
}
