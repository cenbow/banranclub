package com.wechat.article.web.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.service.ITArticleGroupService;
 /**
 * 类功能:图文组查询
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("searchTArticleGroupAction")
@Scope("prototype")
public class SearchTArticleGroupAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SearchTArticleGroupAction.class);

    @Autowired
    private ITArticleGroupService tArticleGroupService;
    //入参
    //查询条件
    private TArticleGroupQueryBean tArticleGroupQueryBean = new TArticleGroupQueryBean();
    //出参
    //启用标志
    private List<SelectCsBean> enableFlags;
    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {

            logger.info("SearchTArticleGroupAction.execute---start");

            //下拉框：启用标志
            enableFlags = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);

            logger.info("SearchTArticleGroupAction.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("SearchTArticleGroupAction.execute---error");
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
            logger.info("SearchTArticleGroupAction.getListData---start");

            if (tArticleGroupQueryBean == null) {
                tArticleGroupQueryBean = new TArticleGroupQueryBean();
            }

            PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
            PageResult pageResult = tArticleGroupService.queryTArticleGroupListPage(tArticleGroupQueryBean, init_pg);

            // 1.遍历增加自定义内容
            List<Map> rows = pageResult.getResultList();
            for (Map d : rows) {
                // 1.1.自定义按钮设置在此处
                d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tArticleGroup_list.updateFormSubmit('" + d.get("ARTICLE_GROUP_ID") + "');return false;\"><i class='icon-edit'></i></a>");
                d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tArticleGroup_list.previewFormSubmit('" + d.get("ARTICLE_GROUP_ID") + "', '"+ d.get("ARTICLE_TYPE")+ "');return false;\"><i class='icon-search'></i></a>");
                // 1.2.CS转换
                d.put("ENABLE_AGFLAG", CodeStringUtil.tranCsValueByCsCode((String)d.get("ENABLE_AGFLAG")));
            }

            // 2.组合输出列表查询所需数据
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
            logger.info("SearchTArticleGroupAction.getListData---end");

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("SearchTArticleGroupAction.getListData---error");
        }

        return null;
    }

    public final TArticleGroupQueryBean getTArticleGroupQueryBean() {
        return tArticleGroupQueryBean;
    }

    public final void setTArticleGroupQueryBean(TArticleGroupQueryBean tArticleGroupQueryBean) {
        this.tArticleGroupQueryBean = tArticleGroupQueryBean;
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
