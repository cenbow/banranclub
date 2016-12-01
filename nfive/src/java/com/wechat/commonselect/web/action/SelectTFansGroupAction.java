package com.wechat.commonselect.web.action;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;


import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wechat.fans.model.TFansGroupQueryBean;
import com.wechat.fans.service.ITFansGroupService;
 /**
 * 类功能:粉丝群资源公用选择弹窗
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.18</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("selectTFansGroupAction")
@Scope("prototype")
public class SelectTFansGroupAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansGroupService tFansGroupService;
	
	private TFansGroupQueryBean tFansGroupQueryBean = new TFansGroupQueryBean();
	 
    //前台回调函数
    private String jsCallback;
    
	protected static final Log log = LogFactory.getLog(SelectTFansGroupAction.class);

	public String execute() throws Exception {
		try {
			//  如果没有登录公众号  则不能查看此页面
			if(null == WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()){
				return ERROR;
			}
			return SUCCESS;
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
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
			if (tFansGroupQueryBean == null) {
				tFansGroupQueryBean = new TFansGroupQueryBean();
			}
			
			//设置默认查询条件  《公众号ID》
			tFansGroupQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tFansGroupService.queryTFansGroupListPage(
					tFansGroupQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				  d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"tFansGroup_list.doSelect('"+ d.get("FANS_GROUP_ID") +"','"+ d.get("GROUP_NAME") +"')\"/>");

				   //群组说明的长度大于15，进行处理
					if( d.get("REMARK")!=null && d.get("REMARK").toString().length()>15){
						d.put("REMARK",d.get("REMARK").toString().substring(0, 15)+"...");
					}
			}

			// 3.组合输出列表查询所需数据
			// JsonConfig 用于解析转换的设置
			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);

			JSONArray json_rows = JSONArray.fromObject(rows, config);
			String json = "{\"total\":\""
					+ pageResult.getPagingObject().getTotal_record()
					+ "\",\"rows\":" + json_rows.toString() + "}";
			System.out.println(json);
			outJSOND(this.getResponse(), json);

		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}

		return null;
	}
	public TFansGroupQueryBean getTFansGroupQueryBean() {
		return tFansGroupQueryBean;
	}

	public void setTFansGroupQueryBean(TFansGroupQueryBean tFansGroupQueryBean) {
		this.tFansGroupQueryBean = tFansGroupQueryBean;
	}

	public String getJsCallback() {
		return jsCallback;
	}

	public void setJsCallback(String jsCallback) {
		this.jsCallback = jsCallback;
	}

   
}
