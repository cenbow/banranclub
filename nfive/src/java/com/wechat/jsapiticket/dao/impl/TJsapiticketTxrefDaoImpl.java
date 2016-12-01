package com.wechat.jsapiticket.dao.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.wechat.jsapiticket.dao.ITJsapiticketTxrefDao;
import com.wechat.jsapiticket.model.dto.TJsapiticketTxrefDto;

/**
 * 类功能:自动代码生成模板 DaoImpl 模板
 */
@Scope("prototype")
@Service("tJsapiticketTxrefDao")
public class TJsapiticketTxrefDaoImpl extends BaseDaoImpl<TJsapiticketTxrefDto, String> implements
		ITJsapiticketTxrefDao {

	public TJsapiticketTxrefDaoImpl() {
		super(TJsapiticketTxrefDto.class);
	}

}
