package com.wechat.article.web.action;
import java.net.URLEncoder;
import java.util.Calendar;
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
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.material.model.dto.TAttachMaterialDto;
import com.wechat.material.service.ITAttachMaterialService;

 /**
 * 类功能:引用图文项
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("refTArticleItemPage")
@Scope("prototype")
public class RefTArticleItemPage extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RefTArticleItemPage.class);

    @Autowired
    private ITArticleItemService tArticleItemService;
    @Autowired
    private UtilConfig utilConfig;
    @Autowired
    private ITAttachMaterialService tAttachMaterialService;

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
            if(StringUtils.isBlank(tArticleRelaQueryBean.getArticle_group_id())
                    || StringUtils.isBlank(tArticleRelaQueryBean.getArticle_item_id())) {
                //无效的访问
                setErrorMessage("无效的访问，关键数据缺失");
                return ERROR;
            }

            logger.info("RefTArticleItemPage.execute---start");

            //图文项
            TArticleItemDto tempTArticleItemDto = new TArticleItemDto();
            tempTArticleItemDto.setArticle_item_id(tArticleRelaQueryBean.getArticle_item_id());
            tempTArticleItemDto = tArticleItemService.getRow(tempTArticleItemDto);
            articleItemVo = new ArticleItemVo();
            articleItemVo.setArticle_group_id(tArticleRelaQueryBean.getArticle_group_id());
            articleItemVo.setArticle_item_id(tempTArticleItemDto.getArticle_item_id());
            articleItemVo.setTitle(tempTArticleItemDto.getTitle());
            articleItemVo.setAuthor(tempTArticleItemDto.getAuthor());
            articleItemVo.setArticle_type(tempTArticleItemDto.getArticle_type());
            articleItemVo.setArticle_state(tempTArticleItemDto.getArticle_state());
            articleItemVo.setPic_org(tempTArticleItemDto.getPic_org());
            articleItemVo.setPic_cover(tempTArticleItemDto.getPic_cover());
            articleItemVo.setPic_thumbnail(tempTArticleItemDto.getPic_thumbnail());
            articleItemVo.setSummary(tempTArticleItemDto.getSummary());
            articleItemVo.setOrg_flag(tempTArticleItemDto.getOrg_flag());
            articleItemVo.setOrg_url(tempTArticleItemDto.getOrg_url());
            if (StringUtils.isNotBlank(tempTArticleItemDto.getContent())) {
            	articleItemVo.setContent(URLEncoder.encode(tempTArticleItemDto.getContent(), "UTF-8").replace("+", "%20"));//URLEncoder.encode 方法将空格替换成了+号，但是JS端编号方法是替换成"%20"的，所以为了保持一致，需要替换一下
            }
            //图片文件
            TAttachMaterialDto tAttachMaterialDto = new TAttachMaterialDto();
            tAttachMaterialDto.setFile_id(tempTArticleItemDto.getPic_org());
            tAttachMaterialDto = tAttachMaterialService.getRow(tAttachMaterialDto);
            articleItemVo.setPic_org_url(utilConfig.getDomain_weburl()
                    + Html2TxtUtil.filePath2UrlPath(tAttachMaterialDto.getStore_path() + tAttachMaterialDto.getStoret_name()));

            //设置默认发布期间
            Calendar rightNow = Calendar.getInstance();
            //当前日期
            //rightNow.add(Calendar.DAY_OF_YEAR, 1);
            articleItemVo.setPub_startdate(rightNow.getTime());
            //9999-12-31
            rightNow.set(9999, 11, 31);
            articleItemVo.setPub_enddate(rightNow.getTime());

            //图文项目状态
            articleStates = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_RELEASE_STATE, articleItemVo.getArticle_state());
            //图文项类型
            articleTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_PEOJECT_TYPE, articleItemVo.getArticle_type());

            logger.info("RefTArticleItemPage.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("RefTArticleItemPage.execute---error");
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
