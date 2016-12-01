package com.pub.common.tools.permission;

public class ConvertCipherText {

	
	/**
	 * 手机端中间四位显示*号
	 * 
	 * @param mobileObject
	 * @return
	 */
	public static  String  cliperMobileText(Object mobileObject){
		if( mobileObject ==  null ){
			return "";
		}
		String mobileNo = (String)mobileObject;
		//仅显示前14位数字后面使用XXX代替
        if(mobileNo.length() >= 11){
        	String newMobileNo  = mobileNo.substring(0,4)+"****"+mobileNo.substring(8);
        	return newMobileNo;
        }
        return mobileNo; 
	}
	
	
	/**
	 * 身份证仅显示前4位+**+后四位信息
	 * 
	 * @param mobileObject
	 * @return
	 */
	public static  String  cliperCertificationText(Object certificationObject){
		if( certificationObject ==  null ){
			return "";
		}
		String certificationNo = (String)certificationObject;
		//仅显示前仅显示前4位数字和后4位数字
        if(certificationNo.length()>8){
            String  newCertificationNo = certificationNo.substring(0,4);
            for(int i=4;i<certificationNo.length()-4;i++){
                newCertificationNo = newCertificationNo + "*";
            }
            newCertificationNo = newCertificationNo+certificationNo.substring(certificationNo.length()-4);
            return newCertificationNo;
        }
        return certificationNo;
	}
	
	
	/**
	 * 银行卡显示前后四位信息
	 * 
	 * @param mobileObject
	 * @return
	 */
	public static  String  cliperBankCardText(Object bankCardObject){
		if( bankCardObject ==  null ){
			return "";
		}
		String bankCardNo = (String)bankCardObject;
		//仅显示前仅显示前4位数字和后4位数字
        if(bankCardNo.length()>8){
            String  newBankCardText = bankCardNo.substring(0,4);
            for(int i=4;i<bankCardNo.length()-4;i++){
            	newBankCardText = newBankCardText + "*";
            }
            newBankCardText = newBankCardText+bankCardNo.substring(bankCardNo.length()-4);
            return newBankCardText;
        }
        return bankCardNo;
	}
	
	
}
