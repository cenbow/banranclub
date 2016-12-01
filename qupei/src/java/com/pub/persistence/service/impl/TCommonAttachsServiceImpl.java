package com.pub.persistence.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.pub.common.local.model.dto.TCommonAttachsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.hercules.common.encryption.Encrypter;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.pub.common.tools.permission.LoginUserInfoUtil;
import com.pub.common.tools.util.FileUploadUtil;
import com.pub.persistence.dao.ITCommonAttachsDao;
import com.pub.persistence.service.ITCommonAttachsService;

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
@Service("commonAttachsService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TCommonAttachsServiceImpl extends GenericServiceImpl<TCommonAttachsDto, String> implements ITCommonAttachsService {

	@Autowired
	private ITCommonAttachsDao commonAttachsDao;

	public BaseDao<TCommonAttachsDto, String> getBaseDao() {
		return commonAttachsDao;
	}

	/****
	 * 处理文件上传
	 *
	 * @param referenceCode 业务表主键
	 * @param functionCode 业务字符串
	 * @param addFiles 新增的文件
	 * @param delFiles 删除的文件
	 * @return
	 * @throws Exception
	 */
	public boolean processUpload(String referenceCode, String functionCode, String addFiles, String delFiles) throws Exception {
		try {
			if (StringUtils.isNotBlank(referenceCode) && StringUtils.isNotBlank(functionCode)) {

				if (StringUtils.isNotBlank(delFiles)) {
					String[] attachs = delFiles.split(",");

					for (String attachsId : attachs) {
						TCommonAttachsDto commonAttachsDto = new TCommonAttachsDto();
						commonAttachsDto.setAttachs_id(attachsId);
						commonAttachsDao.deletePK(commonAttachsDto);
					}
				}

				if (StringUtils.isNotBlank(addFiles)) {
					List<Map> addFilesMap = JSONArray.parseArray(addFiles, Map.class);

					for (Map mapFile : addFilesMap) {
						String orgname = String.valueOf(mapFile.get("name"));
						String path = String.valueOf(mapFile.get("path"));

						TCommonAttachsDto commonAttachsDto = FileUploadUtil.saveUploadByTmpFile(referenceCode, functionCode, new File(Encrypter.decrypt(path)), orgname);
						commonAttachsDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
						commonAttachsDao.save(commonAttachsDto);
					}
				}

				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
