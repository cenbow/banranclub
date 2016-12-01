package com.pub.common.local.model.dto;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;

/**
 * 类功能:自动代码生成模板 DTO模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 *
 * 规约开启方式 用于规约符合我们要求的对象
 * 1. 设置   AddParentClass = true
 * 2. 类       extends BaseDtoImpl
 *
 * 规约关闭方式  用于规约不受我们控制的对象
 * 1. 设置   AddParentClass = false
 * 2. 类        implements IBaseDto
 */
@SuppressWarnings("serial")
@EntityPK(Pk = "attachs_id", defaultColumn = false, tableName = "T_COMMON_ATTACHS")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TCommonAttachsDto extends BaseDtoImpl{//
	private String attachs_id;//ATTACHS_ID
	private String reference_code;//REFERENCE_CODE
	private String function_code;//FUNCTION_CODE
	private String file_orgname;//FILE_ORGNAME
	private String file_currentname;//FILE_CURRENTNAME
	private String file_path;//FILE_PATH
	private java.math.BigDecimal file_size;//FILE_SIZE
	private String remark;//备注

   /**
	* 获取 ATTACHS_ID
	* @return
	*/
	public String getAttachs_id() {
		return attachs_id;
	}

   /**
	* 设置 ATTACHS_ID
	* @param attachs_id
	*/
 	public void setAttachs_id(String attachs_id) {
		this.attachs_id = attachs_id;
	}
   /**
	* 获取 REFERENCE_CODE
	* @return
	*/
	public String getReference_code() {
		return reference_code;
	}

   /**
	* 设置 REFERENCE_CODE
	* @param reference_code
	*/
 	public void setReference_code(String reference_code) {
		this.reference_code = reference_code;
	}
   /**
	* 获取 FUNCTION_CODE
	* @return
	*/
	public String getFunction_code() {
		return function_code;
	}

   /**
	* 设置 FUNCTION_CODE
	* @param function_code
	*/
 	public void setFunction_code(String function_code) {
		this.function_code = function_code;
	}
   /**
	* 获取 FILE_ORGNAME
	* @return
	*/
	public String getFile_orgname() {
		return file_orgname;
	}

   /**
	* 设置 FILE_ORGNAME
	* @param file_orgname
	*/
 	public void setFile_orgname(String file_orgname) {
		this.file_orgname = file_orgname;
	}
   /**
	* 获取 FILE_CURRENTNAME
	* @return
	*/
	public String getFile_currentname() {
		return file_currentname;
	}

   /**
	* 设置 FILE_CURRENTNAME
	* @param file_currentname
	*/
 	public void setFile_currentname(String file_currentname) {
		this.file_currentname = file_currentname;
	}
   /**
	* 获取 FILE_PATH
	* @return
	*/
	public String getFile_path() {
		return file_path;
	}

   /**
	* 设置 FILE_PATH
	* @param file_path
	*/
 	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
   /**
	* 获取 FILE_SIZE
	* @return
	*/
	public java.math.BigDecimal getFile_size() {
		return file_size;
	}

   /**
	* 设置 FILE_SIZE
	* @param file_size
	*/
 	public void setFile_size(java.math.BigDecimal file_size) {
		this.file_size = file_size;
	}
   /**
	* 获取 备注
	* @return
	*/
	public String getRemark() {
		return remark;
	}

   /**
	* 设置 备注
	* @param remark
	*/
 	public void setRemark(String remark) {
		this.remark = remark;
	}
}
