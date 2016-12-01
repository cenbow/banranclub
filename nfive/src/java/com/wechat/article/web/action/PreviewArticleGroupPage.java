package com.wechat.article.web.action;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.properties.UtilConfig;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.vo.ArticleGroupVo;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.utils.Html2TxtUtil;

 /**
 * 类功能:图文组预览
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-28</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("previewArticleGroupPage")
@Scope("prototype")
public class PreviewArticleGroupPage extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PreviewArticleGroupPage.class);

    @Autowired
    private ITArticleGroupService tArticleGroupService;
    @Autowired
    private UtilConfig utilConfig;
    //入参
    private TArticleRelaQueryBean tArticleRelaQueryBean = new TArticleRelaQueryBean();
    private ArticleGroupVo articleGroupVo = new ArticleGroupVo();//前台画面中，多图文、单图文的样式不一样

    //出参
    private List<ArticleItemVo> articleItemVos;
    //图文组类型
    private String article_type_single = CodeStringConstant.IMAGE_TEXT_TYPE_SINGLE;
    private String article_type_multi = CodeStringConstant.IMAGE_TEXT_TYPE_MULTI;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleRelaQueryBean.getArticle_group_id())) {
                //无效的访问
                setErrorMessage("无效的访问，关键数据缺失");
                return ERROR;
            }

            logger.info("PreviewArticleGroupPage.execute---start");

            //设定发布基准日期
            if (tArticleRelaQueryBean.getPub_base_date() == null) {
                tArticleRelaQueryBean.setPub_base_date(new Date());
            }

            //取得可发布列表
            articleItemVos = tArticleGroupService.getArticleGroupPublishableItems(tArticleRelaQueryBean.getArticle_group_id(), tArticleRelaQueryBean.getPub_base_date());
            if (articleItemVos.size() == 0) {
                //无
                articleGroupVo.setArticle_type("");
            } else if (articleItemVos.size() == 1) {
                //单图文
                articleGroupVo.setArticle_type(CodeStringConstant.IMAGE_TEXT_TYPE_SINGLE);
            } else {
                //多图文
                articleGroupVo.setArticle_type(CodeStringConstant.IMAGE_TEXT_TYPE_MULTI);
            }

            for (ArticleItemVo articleItemVo : articleItemVos) {
                //拼装图片文件URL
                //原图
                articleItemVo.setPic_org_url(utilConfig.getDomain_weburl()
                        + Html2TxtUtil.filePath2UrlPath(articleItemVo.getOrg_store_path() + articleItemVo.getOrg_store_name()));
                //封面图
                articleItemVo.setPic_cover_url(utilConfig.getDomain_weburl()
                        + Html2TxtUtil.filePath2UrlPath(articleItemVo.getCover_store_path() + articleItemVo.getCover_store_name()));
                //缩略畋
                articleItemVo.setPic_thumbnail_url(utilConfig.getDomain_weburl()
                        + Html2TxtUtil.filePath2UrlPath(articleItemVo.getThumbnail_store_path() + articleItemVo.getThumbnail_store_name()));
                //设定图文URL
                if (articleItemVo.getOrg_flag().equalsIgnoreCase(CodeStringConstant.CS_1000_TRUE)) {
                    //使用外部原生链接
                    articleItemVo.setArticle_url(articleItemVo.getOrg_url());
                } else {
                    //使用自定义内容
                    String platform_id = WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id();
                    articleItemVo.setArticle_url("showArticleItemContentAction.action?article_item_id=" + articleItemVo.getArticle_item_id() + "&platform_id=" + platform_id);
                }
            }

            logger.info("PreviewArticleGroupPage.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("PreviewArticleGroupPage.execute---error");
        }

        return ERROR;

    }

    public TArticleRelaQueryBean getTArticleRelaQueryBean() {
        return tArticleRelaQueryBean;
    }

    public void setTArticleRelaQueryBean(TArticleRelaQueryBean tArticleRelaQueryBean) {
        this.tArticleRelaQueryBean = tArticleRelaQueryBean;
    }

    public List<ArticleItemVo> getArticleItemVos() {
        return articleItemVos;
    }

    public ArticleGroupVo getArticleGroupVo() {
        return articleGroupVo;
    }

    public void setArticleGroupVo(ArticleGroupVo articleGroupVo) {
        this.articleGroupVo = articleGroupVo;
    }

    public String getArticle_type_single() {
        return article_type_single;
    }

    public String getArticle_type_multi() {
        return article_type_multi;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
