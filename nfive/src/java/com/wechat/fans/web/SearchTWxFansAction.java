package com.wechat.fans.web;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.TWxFansQueryBean;
import com.wechat.fans.service.ITWxFansService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;

/**
 * 类功能:自动代码生成模板查询   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("searchTWxFansAction")
@Scope("prototype")
public class SearchTWxFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWxFansService wxFansService;

	private TWxFansQueryBean tWxFansQueryBean = new TWxFansQueryBean();

    private List<SelectCsBean> sexList;
    private List<SelectCsBean> subscribeList;

	public String execute() throws Exception {
		try {
            //性别选择下拉列表
            sexList  =  CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.CS_1015_SEX_FLAG);
            //是关注用户下拉列表
            subscribeList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG);
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
	public String getListData() {

		try {
			if (tWxFansQueryBean == null) {
				tWxFansQueryBean = new TWxFansQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = wxFansService.queryTWxFansListPage(tWxFansQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tWxFans_list.updateFormSubmit('"+ d.get("FANS_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tWxFans_list.detailFormSubmit('"+ d.get("FANS_ID")+ "');return false;\"><i class='icon-search'></i></a>");

                if(d.get("SUBSCRIBE") != null){
                    d.put("SUBSCRIBE",  CodeStringUtil.tranCsValueByCsCode(d.get("SUBSCRIBE").toString()));
                }
                if(d.get("SEX")!=null) {
                    d.put("SEX", CodeStringUtil.tranCsValueByCsCode(d.get("SEX").toString()));
                }
                if(d.get("HEADIMGURL") != null){
                    d.put("HEADIMGURL", "<img width="+30+" height="+20+" src='"+d.get("HEADIMGURL")+"'>");
                }
                d.put("NICKNAME",URLDecoder.decode(d.get("NICKNAME").toString(), "utf-8"));
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

	public final TWxFansQueryBean getTWxFansQueryBean() {
		return tWxFansQueryBean;
	}

	public final void setTWxFansQueryBean(TWxFansQueryBean tWxFansQueryBean) {
		this.tWxFansQueryBean = tWxFansQueryBean;
	}

    public List<SelectCsBean> getSexList() {
        return sexList;
    }

    public void setSexList(List<SelectCsBean> sexList) {
        this.sexList = sexList;
    }

    public List<SelectCsBean> getSubscribeList() {
        return subscribeList;
    }

    public void setSubscribeList(List<SelectCsBean> subscribeList) {
        this.subscribeList = subscribeList;
    }
}
