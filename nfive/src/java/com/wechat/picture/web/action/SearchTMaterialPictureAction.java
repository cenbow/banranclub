package com.wechat.picture.web.action;

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
 * 类功能:查询图片资源列表
 * <p>创建者:周要领</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTMaterialPictureAction")
@Scope("prototype")
public class SearchTMaterialPictureAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITMaterialPictureService tMaterialPictureService;	
	//入参，存储查询条件
	private TMaterialPictureQueryBean tMaterialPictureQueryBean = new TMaterialPictureQueryBean();
	// 日志对象
	private static final Logger logger = Logger.getLogger(AddTMaterialPictureAction.class);
	//是否缓存下拉列表
	private List<SelectCsBean> cache_FlagList;
	private String errorMessage;//错误信息
	public String execute() throws Exception {
		
		//页面跳转至图片资源一览画面
		try {			
			//查询是否缓存下拉列表
			cache_FlagList = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,"");
			return SUCCESS;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		errorMessage = "连接好像出问题了。。。";
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
				// 2.自定义按钮设置在此处：分别为删除、修改、详细、名称（预览）
				d.put("DELETE","<a href='javascript:return void(0);' onClick=\"tMaterialPicture_list.removeImgForm('"+ d.get("PICTURE_ID")+ "','"+d.get("FILE_ID")+"');return false;\"><i class='icon-no'></i></a>");
				d.put("EDIT","<a href='javascript:return void(0);' onClick=\"tMaterialPicture_list.updateFormSubmit('"+ d.get("PICTURE_ID")+ "');return false;\"><i class='icon-edit'></i></a>");
				d.put("DETAIL","<a href='javascript:return void(0);'onClick=\"tMaterialPicture_list.detailFormSubmit('"+ d.get("PICTURE_ID")+ "');return false;\"><i class='icon-search'></i></a>");
				d.put("PICTURE_SIZE", String.format("%.2f",Double.valueOf(d.get("PICTURE_SIZE").toString())/1024/1024)+"MB");
				
				// 图片描述截断显示 点击显示全部，防止描述过长显示不完全或画面变形
				if(null != d.get("PICTURE_DESC")){	
					String desc = d.get("PICTURE_DESC").toString();
					if(desc.length()>=21){
						desc = desc.substring(0,20)+"...";
					}
					String str ="<a href='javascript:return void(0);'onClick=\"tMaterialPicture_list.detailPictureDesc('"+ d.get("PICTURE_DESC")+ "');return false;\"><font color='blue'>"+desc+"</font></a>";					
					d.put("PICTURE_DESC", str);										
				}
				//预览弹出框大小限制
				d.put("PICTURE_WSIZE", Integer.parseInt(d.get("PICTURE_WSIZE").toString())>900?900:Integer.parseInt(d.get("PICTURE_WSIZE").toString()));
				d.put("PICTURE_HSIZE", Integer.parseInt(d.get("PICTURE_HSIZE").toString())>500?500:Integer.parseInt(d.get("PICTURE_HSIZE").toString()));
				// 为图片名称加上鼠标停留事件
				String picture_name = "<div style=\"cursor:pointer;display:block\"  onmouseout=\"tMaterialPicture_list.closeView();\" onmousemove=\"tMaterialPicture_list.pictureView('"+ FileUploadUtil.domain_weburl+Html2TxtUtil.filePath2UrlPath(d.get("PICTURE_URL").toString())+ "','"+d.get("PICTURE_WSIZE")+"','"+d.get("PICTURE_HSIZE")+"');\"><font color='blue'>"+d.get("PICTURE_NAME").toString()+"</font></div>";
				d.remove("PICTURE_NAME");
				d.put("PICTURE_NAME", picture_name);				
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

	public List<SelectCsBean> getCache_FlagList() {
		return cache_FlagList;
	}

	public void setCache_FlagList(List<SelectCsBean> cacheFlagList) {
		cache_FlagList = cacheFlagList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
