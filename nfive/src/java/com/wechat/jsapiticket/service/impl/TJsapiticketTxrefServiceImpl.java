package com.wechat.jsapiticket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.wechat.jsapiticket.dao.ITJsapiticketTxrefDao;
import com.wechat.jsapiticket.model.dto.TJsapiticketTxrefDto;
import com.wechat.jsapiticket.service.ITJsapiticketTxrefService;

/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 */
@Scope("prototype")
@Service("tJsapiticketTxrefService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TJsapiticketTxrefServiceImpl extends GenericServiceImpl<TJsapiticketTxrefDto, String> implements
		ITJsapiticketTxrefService {

	@Autowired
	private ITJsapiticketTxrefDao tJsapiticketTxrefDao;

	public BaseDao<TJsapiticketTxrefDto, String> getBaseDao() {
		return tJsapiticketTxrefDao;
	}

}
