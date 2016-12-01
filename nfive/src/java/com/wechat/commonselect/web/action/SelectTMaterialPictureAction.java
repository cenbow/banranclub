package com.wechat.commonselect.web.action;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;


import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.picture.model.TMaterialPictureQueryBean;
import com.wechat.picture.service.ITMaterialPictureService;
 /**
 * 类功能:图片资源公用选择弹窗
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014-8-23</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("selectTMaterialPictureAction")
@Scope("prototype")
public class SelectTMaterialPictureAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	
	//入参，存储查询条件
	private TMaterialPictureQueryBean tMaterialPictureQueryBean = new TMaterialPictureQueryBean();
	 //前台回调函数
    private String jsCallback;
	//是否缓存下拉列表
	private List<SelectCsBean> cache_FlagList;
	// 日志对象
	private static final Logger logger = Logger.getLogger(SelectTMaterialPictureAction.class);
	
	public String execute() throws Exception {
		
		//页面跳转至图片资源一览画面
		try {			
			//查询是否缓存下拉列表
			cache_FlagList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,"");
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		return ERROR;
	}
	
	/***
	 * 图片资源查询
	 * @return	图片资源列表
	 * @throws Exception	查询图片时发生异常
	 */
	public String getListData() {

		try {
			if (tMaterialPictureQueryBean == null) {
				tMaterialPictureQueryBean = new TMaterialPictureQueryBean();
			}

			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			PageResult pageResult = tMaterialPictureService.queryTMaterialPictureListPage(
					tMaterialPictureQueryBean, init_pg);

			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();
			for (Map d : rows) {
				
				//拼装URL   项目路径+url  转换反斜杠
				String WebUrl = Html2TxtUtil.filePath2UrlPath(FileUploadUtil.domain_weburl+d.get("PICTURE_URL").toString());
				
				// 2.自定义按钮设置在此处：用于选择
			     d.put("RADIO","<INPUT type=\"radio\" name=\"selectradio\" onclick=\"tMaterialPicture_list.doSelect('"+ d.get("PICTURE_ID") +"','"+ d.get("PICTURE_NAME") + "','"+WebUrl +"')\"/>");
			 	 d.put("PICTURE_SIZE", String.format("%.2f",Double.valueOf(d.get("PICTURE_SIZE").toString())/1024/1024)+"MB");
			 	//是否缓存
					if(null != d.get("CACHE_FLAG")){
						String cache_flag=CodeStringUtil.tranCsValueByCsCode(d.get("CACHE_FLAG").toString());					
						d.put("CACHE_FLAG", cache_flag);
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
			logger.error(ex.getMessage(),ex);
		}

		return null;
	}
	
	public final TMaterialPictureQueryBean getTMaterialPictureQueryBean() {
		return tMaterialPictureQueryBean;
	}

	public final void setTMaterialPictureQueryBean(TMaterialPictureQueryBean tMaterialPictureQueryBean) {
		this.tMaterialPictureQueryBean = tMaterialPictureQueryBean;
	}

	public final String getJsCallback() {
		return jsCallback;
	}

	public final void setJsCallback(String jsCallback) {
		this.jsCallback = jsCallback;
	}

	public List<SelectCsBean> getCache_FlagList() {
		return cache_FlagList;
	}

	public void setCache_FlagList(List<SelectCsBean> cacheFlagList) {
		cache_FlagList = cacheFlagList;
	}
   
}
