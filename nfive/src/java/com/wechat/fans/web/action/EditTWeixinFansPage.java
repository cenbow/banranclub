package com.wechat.fans.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.model.vo.TWeixinFansVo;
import com.wechat.fans.service.ITWeixinFansService;

/**
 * 类功能:粉丝编辑页面
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTWeixinFansPage")
@Scope("prototype")
public class EditTWeixinFansPage extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWeixinFansService tWeixinFansService;
	//入参
	private  String     pkid;
	//出参
	private List<TWeixinFansGroupVo> tWeixinFansGroupVo;
	private TWeixinFansVo tWeixinFansVo;
	
	private static final Logger logger = Logger.getLogger(EditTWeixinFansPage.class);
	 //出错信息
    private String errorMessage;
    
	public String execute() throws Exception {
		try {
		
			TWeixinFansQueryBean paramTWeixinFansDto = new TWeixinFansQueryBean();
		    //设置主键
			paramTWeixinFansDto.setFans_id(pkid);
			paramTWeixinFansDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			tWeixinFansVo = tWeixinFansService.getRowFans(paramTWeixinFansDto);
			tWeixinFansVo.setSex(CodeStringUtil.tranCsValueByCsCode(tWeixinFansVo.getSex()));
	    	tWeixinFansVo.setOpenid_verify(CodeStringUtil.tranCsValueByCsCode(tWeixinFansVo.getOpenid_verify()));
			tWeixinFansGroupVo	= tWeixinFansService.queryAllWeixinFansGroup(paramTWeixinFansDto);
			
			return SUCCESS;
		} catch (Exception ex) {
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;

	}
	
	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}

	
	public TWeixinFansVo getTWeixinFansVo() {
		return tWeixinFansVo;
	}

	public void setTWeixinFansVo(TWeixinFansVo tWeixinFansVo) {
		this.tWeixinFansVo = tWeixinFansVo;
	}

	public List<TWeixinFansGroupVo> getTWeixinFansGroupVo() {
		return tWeixinFansGroupVo;
	}

	public void setTWeixinFansGroupVo(List<TWeixinFansGroupVo> tWeixinFansGroupVo) {
		this.tWeixinFansGroupVo = tWeixinFansGroupVo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
