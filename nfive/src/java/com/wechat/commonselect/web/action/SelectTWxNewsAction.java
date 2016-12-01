package com.wechat.commonselect.web.action;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.model.TWxNewsQueryBean;
import com.wechat.material.service.ITWxNewsService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * Created by jinzhihong on 16/11/19.
 */
@Controller("selectTWxNewsAction")
@Scope("prototype")
public class SelectTWxNewsAction extends BaseAction{
    private static final long serialVersionUID = 1L;

    @Autowired
    private ITWxNewsService wxNewsService;

    private TWxNewsQueryBean tWxNewsQueryBean = new TWxNewsQueryBean();

    //前台回调函数
    private String jsCallback;

    public String execute() throws Exception {
        try {
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ERROR;
    }


    /***
     *图文素材选择画面
     * @return
     * @throws Exception
     */
    public String getListData() {
        try {
            if (tWxNewsQueryBean == null) {
                tWxNewsQueryBean = new TWxNewsQueryBean();
            }

            PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
            PageResult pageResult = wxNewsService.queryTWxNewsListPage(tWxNewsQueryBean, init_pg);

            // 1.遍历增加自定义内容
            List<Map> rows = pageResult.getResultList();
            for (Map d : rows) {
                if(d.get("URL") != null){
                    d.put("URL", "<a href='"+d.get("URL")+"'>"+d.get("URL")+"</a>");
//                    d.put("URL", "123");
                }
                // 2.自定义按钮设置在此处：用于选择
                d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"tWxNews_list.doSelect('"+ d.get("NEWS_ID") +"','"+ d.get("TITLE") + "','"+ d.get("ARTICLE_TYPE") +"')\"/>");
            }

            // 3.组合输出列表查询所需数据
            // JsonConfig 用于解析转换的设置
            JsonConfig config = new JsonConfig();
            JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

            JSONArray json_rows = JSONArray.fromObject(rows, config);
            String json = "{\"total\":\"" + pageResult.getPagingObject().getTotal_record() + "\",\"rows\":" + json_rows.toString() + "}";
            outJSOND(this.getResponse(), json);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public final TWxNewsQueryBean getTWxNewsQueryBean() {
        return tWxNewsQueryBean;
    }

    public final void setTWxNewsQueryBean(TWxNewsQueryBean tWxNewsQueryBean) {
        this.tWxNewsQueryBean = tWxNewsQueryBean;
    }

    public String getJsCallback() {
        return jsCallback;
    }

    public void setJsCallback(String jsCallback) {
        this.jsCallback = jsCallback;
    }
}
