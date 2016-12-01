package com.wechat.fans.model.dto;
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
@EntityPK(Pk = "history_id", defaultColumn = false, tableName = "T_LATESTFANS_SYNC_HISTORY")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TLatestfansSyncHistoryDto  extends BaseDtoImpl{//
	private java.lang.String history_id;//履历ID
    private java.util.Date sync_start_date;//同步开始时间
    private java.util.Date sync_end_date;//同步结束时间
	private java.lang.String sync_state;//同步状态【5065】
	private java.lang.String remark;//备注说明
	private java.lang.String platform_id;//公众平台号ID

   /**
	* 获取 履历ID
	* @return
	*/
	public java.lang.String getHistory_id() {
		return history_id;
	}
	
   /**
	* 设置 履历ID
	* @param history_id
	*/
 	public void setHistory_id(java.lang.String history_id) {
		this.history_id = history_id;
	}
   /**
	* 获取 同步开始时间
	* @return
	*/
	public java.util.Date getSync_start_date() {
		return sync_start_date;
	}
	
   /**
	* 设置 同步开始时间
	* @param sync_start_date
	*/
 	public void setSync_start_date(java.util.Date sync_start_date) {
		this.sync_start_date = sync_start_date;
	}
   /**
	* 获取 同步结束时间
	* @return
	*/
	public java.util.Date getSync_end_date() {
		return sync_end_date;
	}
	
   /**
	* 设置 同步结束时间
	* @param sync_end_date
	*/
 	public void setSync_end_date(java.util.Date sync_end_date) {
		this.sync_end_date = sync_end_date;
	}
   /**
	* 获取 同步状态【5065】
	* @return
	*/
	public java.lang.String getSync_state() {
		return sync_state;
	}
	
   /**
	* 设置 同步状态【5065】
	* @param sync_state
	*/
 	public void setSync_state(java.lang.String sync_state) {
		this.sync_state = sync_state;
	}
   /**
	* 获取 备注说明
	* @return
	*/
	public java.lang.String getRemark() {
		return remark;
	}
	
   /**
	* 设置 备注说明
	* @param remark
	*/
 	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
   /**
	* 获取 公众平台号ID
	* @return
	*/
	public java.lang.String getPlatform_id() {
		return platform_id;
	}
	
   /**
	* 设置 公众平台号ID
	* @param platform_id
	*/
 	public void setPlatform_id(java.lang.String platform_id) {
		this.platform_id = platform_id;
	}
}
