package com.pub.common.tools.util;

import javax.xml.namespace.QName;

import com.hercules.common.encryption.Encrypter;
import com.pub.common.sms.SmsRemoteBean;
import com.pub.common.tools.constant.CodeStringConstant;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

@Service("wsClientUtil")
public class WsClientUtil {

    private static final Logger logger = Logger.getLogger(WsClientUtil.class);

    /** NameSpace */
    private static final String NAMESPACE = "http://webservice.sms.remote.ldbank.com";
    /** MethodName */
    private static final String METHODNAME = "sendSmsByObject";
    /** 优先级 */
    public static final String PRIORITY = "5";
    /** 业务类型（1是非营销 2 是营销） */
    public static final String BUSS_CLASS = "210100000001";
    /** 批次号 */
    private static final String BATCH = "caifunc";


    private  String url; // ws地址
    private  String username; // 用户名
    private  String password; // 密码

    /**
     * 发送验证码短信接口
     *
     * @param mobile(手机号)
     * @param content(内容)
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public  boolean sendSMS( String mobile, String content) {
        return sendSMS(BUSS_CLASS, mobile, content);
    }

    /**
     * 发送验证码短信接口
     *
     * @param bussCode(业务Code)
     * @param mobile(手机号)
     * @param content(内容)
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private  boolean sendSMS(String bussCode, String mobile, String content) {
        logger.info("------短信发送------start");
        boolean flag = false;
        try {
            RPCServiceClient serviceClient = new RPCServiceClient();
            serviceClient.addHeader(createHeaderOMElement());
            Options options = serviceClient.getOptions();
            options.setManageSession(Boolean.TRUE);
            options.setProperty(HTTPConstants.SO_TIMEOUT, 60000); // 设置超时时间60秒
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);
            QName opAddEntry = new QName(NAMESPACE, METHODNAME);
            SmsRemoteBean smsRemoteBean = createSmsRemoteBean(bussCode, mobile, content);
            SmsRemoteBean[] smsRemoteBeans = new SmsRemoteBean[] { smsRemoteBean };
            Object[] opAddEntryArgs = new Object[] { smsRemoteBeans, Encrypter.decrypt(username), Encrypter.decrypt(password) };
            Class[] classes = new Class[] { String.class };
            Object[] response = serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes);
            String result = (String) response[0];

            logger.info("\r\nmobile:\r\n" + mobile + "\r\ncontent:\r\n" + content + "\r\nresult:\r\n" + result);
            if (StringUtils.isBlank(result)) {
                flag = false;
            }
            flag = true;
        } catch (Throwable e) {
            logger.error("------短信发送失败------mobile：" + mobile, e);
            flag = false;
        }
        logger.info("------短信发送成功------end");
        return flag;
    }

    /**
     * 封装发送Bean
     *
     * @param bussCode(业务Code)
     * @param mobile(手机号)
     * @param content(内容)
     * @return
     */
    private SmsRemoteBean createSmsRemoteBean(String bussCode, String mobile, String content) {
        SmsRemoteBean smsRemoteBean = new SmsRemoteBean();
        // 手机号码
        smsRemoteBean.setMobile(mobile);
        // 短信内容
        smsRemoteBean.setContent(content);
        // 业务类型
        smsRemoteBean.setBuss_class(StringUtils.isNotBlank(bussCode) ? bussCode : BUSS_CLASS);
        // 优先级
        smsRemoteBean.setPriority(PRIORITY);
        // 是否必达
        smsRemoteBean.setIs_obtain(CodeStringConstant.CS_1000_NO);
        // 是否需要回复
        smsRemoteBean.setIs_reply(CodeStringConstant.CS_1000_NO);
        // 内部参考号
        smsRemoteBean.setInner_sms_code(BATCH + System.currentTimeMillis());
        // 是否语音短信
        smsRemoteBean.setIs_voice_sms(CodeStringConstant.CS_1000_NO);
        // 是否群发短信
        smsRemoteBean.setIs_sum_sms(CodeStringConstant.CS_1000_NO);
        // 批次号
        smsRemoteBean.setBatch_code(BATCH);
        // smsRemoteBean.setActivity_code(activityCode); // 活动ID
        // smsRemoteBean.setActivity_name(activityName); // 活动名
        // 是否免打扰
        smsRemoteBean.setIs_disturb(CodeStringConstant.CS_1000_NO);
        // 是否自动回复
        smsRemoteBean.setAutomatic_reply(CodeStringConstant.CS_1000_NO);
        // 备注
        smsRemoteBean.setRemarks("ecmgs业务通知类短信");

        return smsRemoteBean;
    }

    public static OMElement createHeaderOMElement() {
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace SecurityElementNamespace = factory.createOMNamespace("http://platform.webservice.ldbank.com", "wsse");
        OMElement authenticationOM = factory.createOMElement("Authentication", SecurityElementNamespace);
        return authenticationOM;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
