package com.pub.persistence.service;

import com.hercules.database.service.IGenericService;
import com.pub.common.local.model.dto.TCommonAttachsDto;

/**
 * 类功能:自动代码生成模板   IService 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public interface ITCommonAttachsService extends IGenericService<TCommonAttachsDto, String> {

	/****
	 * 处理文件上传
	 *
	 * @param referenceCode
	 *            业务表主键
	 * @param functionCode
	 *            业务字符串
	 * @param addFiles
	 *            新增的文件
	 * @param delFiles
	 *            删除的文件
	 * @return
	 * @throws Exception
	 */
	public boolean processUpload(String referenceCode, String functionCode, String addFiles, String delFiles) throws Exception;


}
