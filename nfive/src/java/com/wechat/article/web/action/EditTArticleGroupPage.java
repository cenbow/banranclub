package com.wechat.article.web.action;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.service.ITArticleGroupService;

 /**
 * 类功能:编辑图文组
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTArticleGroupPage")
@Scope("prototype")
public class EditTArticleGroupPage extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EditTArticleGroupPage.class);

    @Autowired
    private ITArticleGroupService tArticleGroupService;

    //入参
    private TArticleGroupQueryBean inArticleGroupQueryBean = new TArticleGroupQueryBean();

    //出参
    //图文组对象初始化
    private TArticleGroupQueryBean outArticleGroupQueryBean;
    //启用标志
    private List<SelectCsBean> enableFlags;

    //图文组类型
    private List<SelectCsBean> articleTypes;
    //最大条目数
    private List<SelectCsBean> maxItemCounts;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(inArticleGroupQueryBean.getArticle_group_id())) {
                //无效的访问
                setErrorMessage("无效的访问");
                return ERROR;
            }
            logger.info("EditTArticleGroupPage.execute---start");

            //图文组基本信息
            outArticleGroupQueryBean = tArticleGroupService.getArticleGroup(inArticleGroupQueryBean);

            //启用标志下拉框
            enableFlags = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG, outArticleGroupQueryBean.getEnable_agflag());
            //图文组类型
            articleTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_TYPE, outArticleGroupQueryBean.getArticle_type());
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
                if (outArticleGroupQueryBean.getMax_items_count().intValue() == i) {
                    csBean.setSelected(true);
                }
                maxItemCounts.add(csBean);
            }

            logger.info("EditTArticleGroupPage.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("EditTArticleGroupPage.execute---error");
        }

        return ERROR;
    }

    public TArticleGroupQueryBean getInArticleGroupQueryBean() {
        return inArticleGroupQueryBean;
    }

    public void setInArticleGroupQueryBean(TArticleGroupQueryBean inArticleGroupQueryBean) {
        this.inArticleGroupQueryBean = inArticleGroupQueryBean;
    }

    public TArticleGroupQueryBean getOutArticleGroupQueryBean() {
        return outArticleGroupQueryBean;
    }

    public List<SelectCsBean> getEnableFlags() {
        return enableFlags;
    }

    public List<SelectCsBean> getArticleTypes() {
        return articleTypes;
    }

    public List<SelectCsBean> getMaxItemCounts() {
        return maxItemCounts;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
