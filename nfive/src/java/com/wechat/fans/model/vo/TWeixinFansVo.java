package com.wechat.fans.model.vo;

import com.wechat.fans.model.dto.TWeixinFansDto;

/**
 * 类功能:粉丝
 * <p>创建者:liyi.wang</p>
 * <p>创建时间:2014.9.17</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
public class TWeixinFansVo extends TWeixinFansDto {
    private static final long serialVersionUID = 1L;

    private String financial;  //理财账号
    private String fund;      //基金账号
    private String maccount_no; //一帐通账号
    private String mobile; //手机号
    private String gup_name; //微信组名称
    private String maccount_id;//一帐通ID
    private String openid_verify;//微信绑定标识
    
	public String getFinancial() {
		return financial;
	}
	public void setFinancial(String financial) {
		this.financial = financial;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getMaccount_no() {
		return maccount_no;
	}
	public void setMaccount_no(String maccountNo) {
		maccount_no = maccountNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGup_name() {
		return gup_name;
	}
	public void setGup_name(String gupName) {
		gup_name = gupName;
	}
	public String getMaccount_id() {
		return maccount_id;
	}
	public void setMaccount_id(String maccountId) {
		maccount_id = maccountId;
	}
	public String getOpenid_verify() {
		return openid_verify;
	}
	public void setOpenid_verify(String openidVerify) {
		openid_verify = openidVerify;
	}
  

}
