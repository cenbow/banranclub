package com.wechat.fans.web.action;

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
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.opensymphony.page.PangingUtils;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.vo.TWeixinFansVo;
import com.wechat.fans.service.ITFansGroupMemberRealService;
import com.wechat.fans.service.ITWeixinFansService;

 /**
 * 类功能:粉丝详细
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("detailTWeixinFansPage")
@Scope("prototype")
public class DetailTWeixinFansPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWeixinFansService tWeixinFansService;
	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	//入参
	private  String  pkid;
	private TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean;
	//出参
	private TWeixinFansVo tWeixinFansVo;
	
	private static final Logger logger = Logger.getLogger(DetailTWeixinFansPage.class);
	 //出错信息
    private String errorMessage;
    
	public String execute() throws Exception {
		try {
		    TWeixinFansDto paramTWeixinFansDto = new TWeixinFansDto();
		   //设置主键
			paramTWeixinFansDto.setFans_id(pkid);
			tWeixinFansVo = tWeixinFansService.getRowFans(paramTWeixinFansDto);
			tWeixinFansVo.setSex(CodeStringUtil.tranCsValueByCsCode(tWeixinFansVo.getSex()));

			return SUCCESS;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;

	}
   
	
	/**
	 * 获取粉丝的粉丝群
	 * @return
	 */
	public String getListData() {

		try {
			PagingObject init_pg = PangingUtils.getPagingObjectFormRequest();
			TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean= new TFansGroupMemberRealQueryBean();
			tFansGroupMemberRealQueryBean.setFans_id(pkid);
			//设置默认查询条件  《公众号ID》
			tFansGroupMemberRealQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			
			PageResult pageResult = tFansGroupMemberRealService.queryTFansGroupList(tFansGroupMemberRealQueryBean, init_pg);
			// 1.遍历增加自定义内容
			List<Map> rows = pageResult.getResultList();

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
	

	public TWeixinFansVo getTWeixinFansVo() {
		return tWeixinFansVo;
	}


	public void setTWeixinFansVo(TWeixinFansVo tWeixinFansVo) {
		this.tWeixinFansVo = tWeixinFansVo;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public TFansGroupMemberRealQueryBean getTFansGroupMemberRealQueryBean() {
		return tFansGroupMemberRealQueryBean;
	}


	public void setTFansGroupMemberRealQueryBean(
			TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean) {
		this.tFansGroupMemberRealQueryBean = tFansGroupMemberRealQueryBean;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
   
}
