package com.pub.persistence.dao.impl;

import com.pub.common.local.model.dto.TCommonAttachsDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.pub.persistence.dao.ITCommonAttachsDao;

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
@Service("commonAttachsDao")
public class TCommonAttachsDaoImpl extends BaseDaoImpl<TCommonAttachsDto, String> implements ITCommonAttachsDao {

	public TCommonAttachsDaoImpl() {
		super(TCommonAttachsDto.class);
	}

}
