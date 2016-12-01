package com.wechat.fans.web.action;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.TFansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;
import com.wechat.fans.service.ITFansSyncHistoryService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

 /**
 * 类功能:同步履历
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTFansSyncHistoryAction")
@Scope("prototype")
public class SearchTFansSyncHistoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITFansSyncHistoryService tFansSyncHistoryService;
	//入参
	private TFansSyncHistoryQueryBean tFansSyncHistoryQueryBean = new TFansSyncHistoryQueryBean();
    //出参
	private String sync_flag;
	  //出错信息
    private String errorMessage;
    private static final Logger logger = Logger.getLogger(SearchTFansSyncHistoryAction.class);
    
	public String execute() throws Exception {
		try {
			//如果没有登录公众号  则不能查看此页面
			if(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id() == null){
				setErrorMessage("没有登录公众号");
				
				return ERROR;
			}
			TFansSyncHistoryDto TFansSyncHistoryDto = new TFansSyncHistoryDto();
			List<TFansSyncHistoryDto> tFansSyncHistoryDto= tFansSyncHistoryService.getAll(TFansSyncHistoryDto);
			for(int i=0; i<tFansSyncHistoryDto.size(); i++){
				if(CodeStringConstant.CS_5065_SYNC_ONGING.equals(tFansSyncHistoryDto.get(i).getSync_state())){
					sync_flag=CodeStringConstant.CS_5065_SYNC_ONGING;
				}
			}
			
			return SUCCESS;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
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
			
			if (tFansSyncHistoryQueryBean == null) {
				tFansSyncHistoryQueryBean = new TFansSyncHistoryQueryBean();
			}
			tFansSyncHistoryQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tFansSyncHistoryService.queryTFansSyncHistoryListPage(
					tFansSyncHistoryQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				// 2.自定义按钮设置在此处
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tFansSyncHistory_list.updateFormSubmit('"+ d.get("HISTORY_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tFansSyncHistory_list.detailFormSubmit('"+ d.get("HISTORY_ID")+ "');return false;\"><i class='icon-search'></i></a>");
       		  
				if(d.get("REMARK") !=null && d.get("REMARK").toString().length() > 100){
					String remark ="<a href='javascript:return void(0);'onClick=\"tFansSyncHistory_list.detailRemark('"+ d.get("REMARK")+ "');return false;\"><font color='blue'>"+d.get("REMARK").toString().substring(0, 100)+"...</font></a>";														
					d.put("REMARK", remark);
				}else if(d.get("REMARK") ==null){
					d.put("REMARK", "");
			     }else{
					String remark ="<a href='javascript:return void(0);'onClick=\"tFansSyncHistory_list.detailRemark('"+ d.get("REMARK")+ "');return false;\"><font color='blue'>"+d.get("REMARK")+"</font></a>";														
					d.put("REMARK", remark);
				}
			
				d.put("SYNC_STATE",CodeStringUtil.tranCsValueByCsCode(d.get("SYNC_STATE").toString()));
	
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
			
			return null;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}
		
		 return ERROR;
	}
	
	public final TFansSyncHistoryQueryBean getTFansSyncHistoryQueryBean() {
		return tFansSyncHistoryQueryBean;
	}

	public final void setTFansSyncHistoryQueryBean(TFansSyncHistoryQueryBean tFansSyncHistoryQueryBean) {
		this.tFansSyncHistoryQueryBean = tFansSyncHistoryQueryBean;
	}

	public String getSync_flag() {
		return sync_flag;
	}

	public void setSync_flag(String syncFlag) {
		sync_flag = syncFlag;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
