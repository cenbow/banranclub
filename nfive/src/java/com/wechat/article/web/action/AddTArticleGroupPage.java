package com.wechat.article.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.article.model.TArticleGroupQueryBean;

 /**
 * 类功能:新增图文组
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@SuppressWarnings("serial")
@Controller("addTArticleGroupPage")
@Scope("prototype")
public class AddTArticleGroupPage extends BaseAction {
    private static final Logger logger = Logger.getLogger(AddTArticleGroupPage.class);

    //出参
    //图文组对象初始化
    private TArticleGroupQueryBean tArticleGroupQueryBean;
    //启用标志
    private List<SelectCsBean> enableFlags;
    //最大条目数
    private List<SelectCsBean> maxItemCounts;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            logger.info("AddTArticleGroupPage.execute---start");

            tArticleGroupQueryBean = new TArticleGroupQueryBean();
            //图文编号
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            tArticleGroupQueryBean.setAg_code(dateFormat.format(new Date()));

            //图文组管理人
            tArticleGroupQueryBean.setAg_manager(LoginUserInfoUtil.getLoginUserCD());
            tArticleGroupQueryBean.setAg_manager_name(LoginUserInfoUtil.getLoginUser().getUser_name());
            //启用标志下拉框
            enableFlags = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);

            //最大条目数
            maxItemCounts = new ArrayList<SelectCsBean>();
            for (int i=0; i<=10; i++) {
                SelectCsBean csBean = new SelectCsBean();
                if (i==0) {
                    csBean.setKey("");
                    csBean.setValue("未输入");
                } else {
                    csBean.setKey(String.valueOf(i));
                    csBean.setValue(String.valueOf(i));
                }
                maxItemCounts.add(csBean);
            }

            logger.info("AddTArticleGroupPage.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("AddTArticleGroupPage.execute---error");
        }

        return ERROR;
    }

    public List<SelectCsBean> getMaxItemCounts() {
        return maxItemCounts;
    }

    public TArticleGroupQueryBean getTArticleGroupQueryBean() {
        return tArticleGroupQueryBean;
    }

    public List<SelectCsBean> getEnableFlags() {
        return enableFlags;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
