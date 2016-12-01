package com.wechat.article.web.action;
import java.net.URLEncoder;
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
import com.platform.common.tools.properties.UtilConfig;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.core.utils.Html2TxtUtil;

 /**
 * 类功能:编辑图文项
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTArticleItemPage")
@Scope("prototype")
public class EditTArticleItemPage extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EditTArticleItemPage.class);

    @Autowired
    private ITArticleItemService tArticleItemService;
    @Autowired
    private UtilConfig utilConfig;

    //图文组列表查询（入/出）参数
    private TArticleGroupQueryBean tArticleGroupQueryBean = new TArticleGroupQueryBean();

    //入参
    private TArticleRelaQueryBean tArticleRelaQueryBean = new TArticleRelaQueryBean();

    //出参
    //图文项目状态
    private List<SelectCsBean> articleStates;
    //图文项类型
    private List<SelectCsBean> articleTypes;
    //使用原生链接
    private String orgFlagTrue = CodeStringConstant.CS_1000_TRUE;
    private String orgFlagFalse = CodeStringConstant.CS_1000_FALSE;
    //图文关系、图文项
    private ArticleItemVo articleItemVo;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleRelaQueryBean.getRela_id()) || StringUtils.isBlank(tArticleRelaQueryBean.getArticle_group_id())
                    || StringUtils.isBlank(tArticleRelaQueryBean.getArticle_item_id())) {
                //无效的访问
                setErrorMessage("无效的访问");
                return ERROR;
            }

            logger.info("EditTArticleItemPage.execute---start");

            //图文关系、图文项
            articleItemVo = tArticleItemService.getArticleGroupItem(tArticleRelaQueryBean );
            if (StringUtils.isNotBlank(articleItemVo.getContent())) {
            	articleItemVo.setContent(URLEncoder.encode(articleItemVo.getContent(), "UTF-8").replace("+", "%20"));//URLEncoder.encode 方法将空格替换成了+号，但是JS端编号方法是替换成"%20"的，所以为了保持一致，需要替换一下
            }
            articleItemVo.setPic_org_url(utilConfig.getDomain_weburl()
                    + Html2TxtUtil.filePath2UrlPath(articleItemVo.getOrg_store_path() + articleItemVo.getOrg_store_name()));
            //图文项目状态
            articleStates = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_RELEASE_STATE, articleItemVo.getArticle_state());
            //图文项类型
            articleTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_PEOJECT_TYPE, articleItemVo.getArticle_type());

            logger.info("EditTArticleItemPage.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("EditTArticleItemPage.execute---error");
        }

        return ERROR;
    }

    public TArticleGroupQueryBean getTArticleGroupQueryBean() {
        return tArticleGroupQueryBean;
    }

    public void setTArticleGroupQueryBean(TArticleGroupQueryBean tArticleGroupQueryBean) {
        this.tArticleGroupQueryBean = tArticleGroupQueryBean;
    }

    public TArticleRelaQueryBean getTArticleRelaQueryBean() {
        return tArticleRelaQueryBean;
    }

    public void setTArticleRelaQueryBean(TArticleRelaQueryBean tArticleRelaQueryBean) {
        this.tArticleRelaQueryBean = tArticleRelaQueryBean;
    }

    public List<SelectCsBean> getArticleStates() {
        return articleStates;
    }

    public List<SelectCsBean> getArticleTypes() {
        return articleTypes;
    }

    public String getOrgFlagTrue() {
        return orgFlagTrue;
    }

    public String getOrgFlagFalse() {
        return orgFlagFalse;
    }

    public ArticleItemVo getArticleItemVo() {
        return articleItemVo;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
