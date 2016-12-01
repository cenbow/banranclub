package com.wechat.article.web.action;

import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.dto.TArticleRelaDto;

 /**
 * 类功能:新增图文项
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
@Controller("addTArticleItemPage")
@Scope("prototype")
public class AddTArticleItemPage extends BaseAction {

    private static final Logger logger = Logger.getLogger(AddTArticleItemPage.class);

    //图文组列表查询（入/出）参数
    private TArticleGroupQueryBean tArticleGroupQueryBean = new TArticleGroupQueryBean();

    //出参
    //图文项目状态
    private List<SelectCsBean> articleStates;
    //图文项类型
    private List<SelectCsBean> articleTypes;
    //使用原生链接
    private String orgFlagTrue = CodeStringConstant.CS_1000_TRUE;
    private String orgFlagFalse = CodeStringConstant.CS_1000_FALSE;

    private TArticleRelaDto articleRelaDto = new TArticleRelaDto();

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleGroupQueryBean.getArticle_group_id())) {
                //无效的访问
                setErrorMessage("无效的访问");
                return ERROR;
            }

            logger.info("AddTArticleItemPage.execute---start");

            //图文项目状态
            articleStates = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_RELEASE_STATE);
            //图文项类型
            articleTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_PEOJECT_TYPE);

            //设置默认发布期间
            Calendar rightNow = Calendar.getInstance();
            //当前日期
            //rightNow.add(Calendar.DAY_OF_YEAR, 1);
            articleRelaDto.setPub_startdate(rightNow.getTime());
            //9999-12-31
            rightNow.set(9999, 11, 31);
            articleRelaDto.setPub_enddate(rightNow.getTime());

            logger.info("AddTArticleItemPage.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("AddTArticleItemPage.execute---error");
        }

        return ERROR;
    }

    public List<SelectCsBean> getArticleStates() {
        return articleStates;
    }

    public List<SelectCsBean> getArticleTypes() {
        return articleTypes;
    }

    public TArticleGroupQueryBean getTArticleGroupQueryBean() {
        return tArticleGroupQueryBean;
    }

    public void setTArticleGroupQueryBean(TArticleGroupQueryBean tArticleGroupQueryBean) {
        this.tArticleGroupQueryBean = tArticleGroupQueryBean;
    }

    public String getOrgFlagTrue() {
        return orgFlagTrue;
    }

    public String getOrgFlagFalse() {
        return orgFlagFalse;
    }

    public TArticleRelaDto getArticleRelaDto() {
        return articleRelaDto;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
