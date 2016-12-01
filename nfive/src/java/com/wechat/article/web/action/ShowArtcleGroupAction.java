package com.wechat.article.web.action;

import java.util.Date;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.properties.UtilConfig;
import com.wechat.article.model.TArticleRelaQueryBean;
import com.wechat.article.model.vo.ArticleGroupVo;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.core.utils.Html2TxtUtil;

 /**
 * 类功能:通过图文组ID与图文组类型进行预览图文组预览：直接输出html
 * <p>创建者: 周要领</p>
 * <p>创建时间:2014-8-28</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("showArtcleGroupAction")
@Scope("prototype")
public class ShowArtcleGroupAction extends BaseAction {
    private static final long serialVersionUID = 1L;

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

    public String execute() throws Exception {
        try {
            if(StringUtils.isBlank(tArticleRelaQueryBean.getArticle_group_id())) {
                //无效的访问
                return ERROR;
            }
            //设定发布基准日期
            if (tArticleRelaQueryBean.getPub_base_date() == null) {
                tArticleRelaQueryBean.setPub_base_date(new Date());
            }

            //取得可发布列表
            articleItemVos = tArticleGroupService.getArticleGroupPublishableItems(tArticleRelaQueryBean.getArticle_group_id(), tArticleRelaQueryBean.getPub_base_date());
            if (null == articleItemVos || articleItemVos.size() == 0){
                JSONObject json =   new JSONObject();
                json.put("html","<h1 style=\"font-size:20px;\">当前无可显示数据!</h1>");
                outJSOND(response, json.toString());
                return null;
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
                    articleItemVo.setArticle_url("showArticleItemContentAction.action?article_item_id=" + articleItemVo.getArticle_item_id());
                }
            }

           //缓存html内容
           StringBuffer buff = new StringBuffer();
           buff.append("<div class=\"m_div\">");

           //单图文
           if(articleItemVos.size() == 1){

               ArticleItemVo articleItemVo = articleItemVos.get(0);
                    buff.append("<div class=\"m_singlegraphic\"><div class=\"m_moreconten\">"
                            +"<p class=\"m_moretitle\"><a target=\"_blank\" href=\""+articleItemVo.getArticle_url()+"\">"+articleItemVo.getTitle()+"</a></p>"
                            +"<div class=\"m_moreimg\"><img src=\""+articleItemVo.getPic_cover_url()+"\" /></div>"
                            +"<div class=\"m_moretext\">"+StringUtils.trimToEmpty(articleItemVo.getSummary())+"</div></div></div></div>");

           //多图文
           }else{
               buff.append("<div class=\"m_moregraphic\"><div class=\"m_singleconten\">");

               for(int i=0; i<articleItemVos.size(); i++){

                   //多图文首条
                   if(0 ==  i){
                       ArticleItemVo articleItemVo = articleItemVos.get(0);
                       buff.append("<div class=\"m_singlecoveritem\"><div class=\"m_single_img\"><img src=\""+articleItemVo.getPic_cover_url()+"\" /></div>"
                    +"<h4 class=\"m_single_title\"><a target=\"_blank\" href=\""+articleItemVo.getArticle_url()+"\">"+articleItemVo.getTitle()+"</a></h4></div>");
                   }else{
                       ArticleItemVo articleItemVo = articleItemVos.get(i);
                       buff.append("<div class=\"m_single_item\"><img class=\"m_single_itemimg\" src=\""+articleItemVo.getPic_thumbnail_url()+"\" />"
                               +" <h4 class=\"m_single_itemtext\"><a target=\"_blank\" href=\""+articleItemVo.getArticle_url()+"\">"+articleItemVo.getTitle()+"</a></h4></div>");
                   }
               }
               buff.append("</div></div></div>");
           }
           JSONObject json  =   new JSONObject();
           json.put("html", buff.toString());
           outJSOND(response, json.toString());

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
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
}
