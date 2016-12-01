package com.wechat.article.web.action;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.TArticleItemQueryBean;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.article.service.ITArticleItemService;
 /**
 * 类功能:图文组详细
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("searchTArticleItemAction")
@Scope("prototype")
public class SearchTArticleItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SearchTArticleItemAction.class);

    @Autowired
    private ITArticleItemService tArticleItemService;
    @Autowired
    private ITArticleGroupService tArticleGroupService;

    //入参
    //图文项查询条件
    private TArticleItemQueryBean tArticleItemQueryBean = new TArticleItemQueryBean();
    //图文组查询条件
    private TArticleGroupQueryBean tArticleGroupQueryBean = new TArticleGroupQueryBean();

    //出参
    //图文组基本信息
    private TArticleGroupQueryBean outArticleGroupQueryBean;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleGroupQueryBean.getArticle_group_id())) {
                //无效的访问
                setErrorMessage("无效的访问，关键数据缺失");
                return ERROR;
            }

            logger.info("SearchTArticleItemAction.execute---start");

            //图文组基本信息
            outArticleGroupQueryBean = tArticleGroupService.getArticleGroup(tArticleGroupQueryBean);
            if (null != outArticleGroupQueryBean) {
                //图文类型转换
                outArticleGroupQueryBean.setArticle_type_name(CodeStringUtil.tranCsValueByCsCode(outArticleGroupQueryBean.getArticle_type()));
                //启用标志转换
                outArticleGroupQueryBean.setEnable_agflag(CodeStringUtil.tranCsValueByCsCode(outArticleGroupQueryBean.getEnable_agflag()));
            }

            logger.info("SearchTArticleItemAction.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("SearchTArticleItemAction.execute---error");
        }
        return ERROR;
    }

    /***
    * 返回数据列表
    *
    * @return
    * @throws Exception
    */
    public String getListData() {

        try {
            logger.info("SearchTArticleItemAction.execute---start");
            if (tArticleItemQueryBean == null) {
                tArticleItemQueryBean = new TArticleItemQueryBean();
            }

            PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
            PageResult pageResult = tArticleItemService.queryTArticleGroupItemListPage(tArticleItemQueryBean, init_pg);

            // 1.遍历增加自定义内容
            List<Map> rows = pageResult.getResultList();
            for (Map d : rows) {
                // 2.自定义按钮设置在此处
                d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tArticleItem_list.updateFormSubmit('"+ d.get("RELA_ID") + "', '" + d.get("ARTICLE_GROUP_ID") + "', '" + d.get("ARTICLE_ITEM_ID") + "');return false;\"><i class='icon-edit'></i></a>");
                d.put("DETAIL","<a href='javascript:return void(0);' onClick=\"tArticleItem_list.detailFormSubmit('"+ d.get("RELA_ID") + "', '" + d.get("ARTICLE_GROUP_ID") + "', '" + d.get("ARTICLE_ITEM_ID") + "');return false;\"><i class='icon-search'></i></a>");
                //3.CS转换
                d.put("ARTICLE_TYPE", CodeStringUtil.tranCsValueByCsCode((String)d.get("ARTICLE_TYPE")));
                d.put("ARTICLE_STATE", CodeStringUtil.tranCsValueByCsCode((String)d.get("ARTICLE_STATE")));
            }

            // 3.组合输出列表查询所需数据
            // JsonConfig 用于解析转换的设置
            JsonConfig config = new JsonConfig();
            JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

            JSONArray json_rows = JSONArray.fromObject(rows, config);
            String json = "{\"total\":\""
                + pageResult.getPagingObject().getTotal_record()
                + "\",\"rows\":"
                + json_rows.toString()
                + "}";
            //System.out.println(json);
            outJSOND(this.getResponse(), json);
            logger.info("SearchTArticleItemAction.execute---end");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("SearchTArticleItemAction.execute---error");
        }

        return null;
    }

    public final TArticleItemQueryBean getTArticleItemQueryBean() {
        return tArticleItemQueryBean;
    }

    public final void setTArticleItemQueryBean(TArticleItemQueryBean tArticleItemQueryBean) {
        this.tArticleItemQueryBean = tArticleItemQueryBean;
    }

    public TArticleGroupQueryBean getTArticleGroupQueryBean() {
        return tArticleGroupQueryBean;
    }

    public void setTArticleGroupQueryBean(TArticleGroupQueryBean tArticleGroupQueryBean) {
        this.tArticleGroupQueryBean = tArticleGroupQueryBean;
    }

    public TArticleGroupQueryBean getOutArticleGroupQueryBean() {
        return outArticleGroupQueryBean;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
