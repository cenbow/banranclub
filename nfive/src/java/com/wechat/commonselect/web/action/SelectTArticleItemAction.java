package com.wechat.commonselect.web.action;

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
import com.wechat.article.model.TArticleItemQueryBean;
import com.wechat.article.service.ITArticleItemService;
 /**
 * 类功能:图文项公用选择弹窗
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-25</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("selectTArticleItemAction")
@Scope("prototype")
public class SelectTArticleItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(SelectTArticleItemAction.class);

    @Autowired
    private ITArticleItemService tArticleItemService;

    //入参
    //图文项查询条件
    private TArticleItemQueryBean tArticleItemQueryBean = new TArticleItemQueryBean();
    //前台回调函数
    private String jsCallback;

    //出参
    //发布状态
    private List<SelectCsBean> articleStates;
    //项目类型
    private List<SelectCsBean> articleTypes;

    public String execute() throws Exception {
        try {

            logger.info("SelectTArticleItemAction.execute---start");

            //CS取得
            articleTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_PEOJECT_TYPE);
            articleStates = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_RELEASE_STATE);

            logger.info("SelectTArticleItemAction.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("SelectTArticleItemAction.execute---error");
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
            logger.info("SelectTArticleItemAction.getListData---start");

            if (tArticleItemQueryBean == null) {
                tArticleItemQueryBean = new TArticleItemQueryBean();
            }

            PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
            PageResult pageResult = tArticleItemService.queryTArticleItemListPage(tArticleItemQueryBean, init_pg);

            // 1.遍历增加自定义内容
            List<Map> rows = pageResult.getResultList();
            for (Map d : rows) {
                // 2.自定义按钮设置在此处
                d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"articleItemSelectPage.doSelect('"+ d.get("ARTICLE_ITEM_ID") +"')\"/>");
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
            System.out.println(json);
            outJSOND(this.getResponse(), json);

            logger.info("SelectTArticleItemAction.getListData---end");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("SelectTArticleItemAction.getListData---error");
        }

        return null;
    }

    public final TArticleItemQueryBean getTArticleItemQueryBean() {
        return tArticleItemQueryBean;
    }

    public final void setTArticleItemQueryBean(TArticleItemQueryBean tArticleItemQueryBean) {
        this.tArticleItemQueryBean = tArticleItemQueryBean;
    }

    public String getJsCallback() {
        return jsCallback;
    }

    public void setJsCallback(String jsCallback) {
        this.jsCallback = jsCallback;
    }

    public List<SelectCsBean> getArticleStates() {
        return articleStates;
    }

    public List<SelectCsBean> getArticleTypes() {
        return articleTypes;
    }
}
