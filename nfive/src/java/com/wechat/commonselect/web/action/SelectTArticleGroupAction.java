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
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.service.ITArticleGroupService;
 /**
 * 类功能:图文组公用选择弹窗
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("selectTArticleGroupAction")
@Scope("prototype")
public class SelectTArticleGroupAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ITArticleGroupService tArticleGroupService;
    
    //入参
    //查询条件
    private TArticleGroupQueryBean tArticleGroupQueryBean = new TArticleGroupQueryBean();
    //出参
    //前台回调函数
    private String jsCallback;
    //启用标志
    private List<SelectCsBean> enableFlags;
    //图文组类型
    private List<SelectCsBean> articleTypes;
	private static final Logger logger = Logger.getLogger(SelectTArticleGroupAction.class);

	public String execute() throws Exception {
        try {
            //下拉框：启用标志 
            enableFlags = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
            //下拉框：图文组类型 
            articleTypes = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.IMAGE_TEXT_TYPE);

            return SUCCESS;
        } catch (Exception ex) {
        	logger.error(ex.getMessage(),ex);
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
            if (tArticleGroupQueryBean == null) {
                tArticleGroupQueryBean = new TArticleGroupQueryBean();
            }

            PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
            PageResult pageResult = tArticleGroupService.queryTArticleGroupListPage(tArticleGroupQueryBean, init_pg);

            // 1.遍历增加自定义内容
            List<Map> rows = pageResult.getResultList();
            for (Map d : rows) {
                // 1.1.自定义按钮设置在此处
            	
            	
            	// 2.自定义按钮设置在此处：用于选择
                d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"tArticleGroup_list.doSelect('"+ d.get("ARTICLE_GROUP_ID") +"','"+ d.get("AG_NAME") + "','"+ d.get("ARTICLE_TYPE") +"')\"/>");
			
                // 1.2.CS转换
                d.put("ARTICLE_TYPE", CodeStringUtil.tranCsValueByCsCode((String)d.get("ARTICLE_TYPE")));
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

        } catch (Exception ex) {
        	logger.error(ex.getMessage(),ex);
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
    
    public List<SelectCsBean> getArticleTypes() {
		return articleTypes;
	}

	public final String getJsCallback() {
		return jsCallback;
	}

	public final void setJsCallback(String jsCallback) {
		this.jsCallback = jsCallback;
	}  
}
