package com.wechat.fans.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.service.ITFansGroupMemberRealService;
import com.wechat.fans.service.ITWeixinFansService;

 /**
 * 类功能:跳转到编辑粉丝群关系页面
 * <p>创建者:gy</p>
 * <p>创建时间:2014-09-15</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTFansGroupMemberRealPage")
@Scope("prototype")
public class EditTFansGroupMemberRealPage extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(EditTFansGroupMemberRealPage.class);

	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	
	@Autowired
	private ITWeixinFansService tWeixinFansService;
	
	//入参
	private  String     pkid;
	
	//出参 关系表
	private TFansGroupMemberRealDto out_tFansGroupMemberRealDto = new TFansGroupMemberRealDto();
	
	//出参微信粉丝表
	private TWeixinFansDto out_tWeixinFansDto = new TWeixinFansDto();
	
	//星标成员
	private List<SelectCsBean> star_member_select;

	
	public String execute() throws Exception {
		try {
		    TFansGroupMemberRealDto paramTFansGroupMemberRealDto = new TFansGroupMemberRealDto();
		    //设置主键
		    paramTFansGroupMemberRealDto.setReal_id(pkid);
		    
		    //查询关系表内容
		    out_tFansGroupMemberRealDto = tFansGroupMemberRealService.getRow(paramTFansGroupMemberRealDto );
		    
		    //获取粉丝信息
		    if (null != out_tFansGroupMemberRealDto.getFans_id()){
		    	 	TWeixinFansDto paramtTWeixinFansDto = new TWeixinFansDto();
				    paramtTWeixinFansDto.setFans_id(out_tFansGroupMemberRealDto.getFans_id());
				    //查询关联的微信粉丝的内容
				    out_tWeixinFansDto = tWeixinFansService.getRow(paramtTWeixinFansDto);
		    }
		    
			//构造下拉列表<标星成员CS:1000>
			star_member_select = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.TRUE_FALSE_FLAG,out_tFansGroupMemberRealDto.getStar_member());
		   
			return SUCCESS;
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}

		return ERROR;

	}

	
	public TFansGroupMemberRealDto getOut_tFansGroupMemberRealDto() {
		return out_tFansGroupMemberRealDto;
	}


	public void setOut_tFansGroupMemberRealDto(
			TFansGroupMemberRealDto outTFansGroupMemberRealDto) {
		out_tFansGroupMemberRealDto = outTFansGroupMemberRealDto;
	}


	public TWeixinFansDto getOut_tWeixinFansDto() {
		return out_tWeixinFansDto;
	}


	public void setOut_tWeixinFansDto(TWeixinFansDto outTWeixinFansDto) {
		out_tWeixinFansDto = outTWeixinFansDto;
	}


	public final String getPkid() {
		return pkid;
	}

	public final void setPkid(String pkid) {
		this.pkid = pkid;
	}


	public List<SelectCsBean> getStar_member_select() {
		return star_member_select;
	}


	public void setStar_member_select(List<SelectCsBean> starMemberSelect) {
		star_member_select = starMemberSelect;
	}

}
