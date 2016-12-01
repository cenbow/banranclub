package com.wechat.platform.model;
import com.hercules.database.dto.impl.BaseDtoImpl;
import com.hercules.database.util.Constant;
import com.hercules.dto.annotation.AddParentClass;
import com.hercules.dto.annotation.EntityPK;
import java.util.Date;

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
@EntityPK(Pk = "platform_id", defaultColumn = false, tableName = "T_WX_PLATFORM")
@AddParentClass(AddParentClass = true, DefaultValueClass = Constant.DEFAULT_COLUMN_DEFAULT_CLASEENAME)
public class TWxPlatformDto  extends BaseDtoImpl{//
	private String platform_id;//公众号业务ID
	private String platform_account;//公众号账号
	private String platform_name;//公众号名称
	private String platform_type;//公众号类型【5010】
	private String app_id;//应用ID
	private String app_secret;//应用秘钥
	private String token;//令牌
    private String access_token;//接口调用凭证

   /**
	* 获取 公众号业务ID
	* @return
	*/
	public String getPlatform_id() {
		return platform_id;
	}

   /**
	* 设置 公众号业务ID
	* @param platform_id
	*/
 	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}
   /**
	* 获取 公众号账号
	* @return
	*/
	public String getPlatform_account() {
		return platform_account;
	}

   /**
	* 设置 公众号账号
	* @param platform_account
	*/
 	public void setPlatform_account(String platform_account) {
		this.platform_account = platform_account;
	}
   /**
	* 获取 公众号名称
	* @return
	*/
	public String getPlatform_name() {
		return platform_name;
	}

   /**
	* 设置 公众号名称
	* @param platform_name
	*/
 	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}
   /**
	* 获取 公众号类型【5010】
	* @return
	*/
	public String getPlatform_type() {
		return platform_type;
	}

   /**
	* 设置 公众号类型【5010】
	* @param platform_type
	*/
 	public void setPlatform_type(String platform_type) {
		this.platform_type = platform_type;
	}
   /**
	* 获取 应用ID
	* @return
	*/
	public String getApp_id() {
		return app_id;
	}

   /**
	* 设置 应用ID
	* @param app_id
	*/
 	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
   /**
	* 获取 应用秘钥
	* @return
	*/
	public String getApp_secret() {
		return app_secret;
	}

   /**
	* 设置 应用秘钥
	* @param app_secret
	*/
 	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}
   /**
	* 获取 令牌
	* @return
	*/
	public String getToken() {
		return token;
	}

   /**
	* 设置 令牌
	* @param token
	*/
 	public void setToken(String token) {
		this.token = token;
	}

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
