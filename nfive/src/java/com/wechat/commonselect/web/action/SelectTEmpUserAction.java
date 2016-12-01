package com.wechat.commonselect.web.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.TEmpUserQueryBean;
import com.platform.persistence.service.ITEmpUserService;
 /**
 * 类功能:员工公用选择弹窗
 * <p>创建者:liyi.wang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("selectUserAction")
@Scope("prototype")
public class SelectTEmpUserAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ITEmpUserService tEmpUserService;
    private TEmpUserQueryBean tEmpUserQueryBean = new TEmpUserQueryBean();
    
    //前台回调函数
    private String jsCallback;

	/**
     * 员工查询选择页面。
     * @return
     */
    public String execute() throws Exception {
        try {
            //查询未锁定的记录，公共页面会员选择出参。
            request.setAttribute("lock_flag", CodeStringConstant.CS_3000_LOCK_FLAG_KEY);
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ERROR;
    }

    /***
     * 
     * @return
     * @throws Exception
     */
    public String getListData(){

        try {
            if (tEmpUserQueryBean == null) {
                tEmpUserQueryBean = new TEmpUserQueryBean();
            }
            PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
            PageResult pageResult = tEmpUserService.queryTEmpUserListPage(tEmpUserQueryBean, init_pg);

            // 1.遍历增加自定义内容
            List<Map> rows = pageResult.getResultList();
            for (Map d : rows) {
                // 2.自定义按钮设置在此处
                d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"empSelect_list.doSelect('"+ d.get("USER_CD") +"','"+ d.get("USER_NAME") +"')\"/>");
            }

            // 3.组合输出列表查询所需数据
            // JsonConfig 用于解析转换的设置
            JsonConfig config = new JsonConfig();
            JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

            JSONArray json_rows = JSONArray.fromObject(rows, config);
            String json = "{\"total\":\""
                    + pageResult.getPagingObject().getTotal_record()
                    + "\",\"rows\":" + json_rows.toString() + "}";
            //System.out.println(json);
            outJSOND(this.getResponse(), json);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public final TEmpUserQueryBean getTEmpUserQueryBean() {
        return tEmpUserQueryBean;
    }

    public final void setTEmpUserQueryBean(TEmpUserQueryBean tEmpUserQueryBean) {
        this.tEmpUserQueryBean = tEmpUserQueryBean;
    }
    public String getJsCallback() {
		return jsCallback;
	}

	public void setJsCallback(String jsCallback) {
		this.jsCallback = jsCallback;
	}
}
