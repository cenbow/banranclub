package com.wechat.replycfg.web.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.wechat.replycfg.service.ITReplyKeywordService;

 /**
 * 类功能:更新关键字回复规则编辑
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTReplyKeywordAction")
@Scope("prototype")
public class EditTReplyKeywordAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final Log log = LogFactory.getLog(EditTReplyKeywordAction.class);


	@Autowired
	private ITReplyKeywordService tReplyKeywordService;
	
	//入参  关键字回复规则
	private TReplyKeywordDto in_tReplyKeywordDto = new TReplyKeywordDto();

	public String execute() throws Exception {
		
		
		JSONObject jsonInfo = new JSONObject();
		try {
		    
			// 检查该关键字是否存在  1.关键字不能相同；2.公众号不能相同；3.发布时间期间；4.匹配类型
			TReplyKeywordQueryBean param_tReplyKeywordQueryBean = new TReplyKeywordQueryBean();
			param_tReplyKeywordQueryBean.setKeyword(in_tReplyKeywordDto.getKeyword().trim().toUpperCase());//关键字
			param_tReplyKeywordQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());//当前公众号号
			param_tReplyKeywordQueryBean.setPub_startdate(new Date());//发布开始时间
			param_tReplyKeywordQueryBean.setPub_enddate(new Date());//发布结束时间
			param_tReplyKeywordQueryBean.setMatch_type(in_tReplyKeywordDto.getMatch_type());//匹配类型
			List<TReplyKeywordDto>  tReplyKeywordDtoList = tReplyKeywordService.checkTReplyKeywordDto(param_tReplyKeywordQueryBean);
			if (null != tReplyKeywordDtoList && tReplyKeywordDtoList.size() > 0){
				for(TReplyKeywordDto tReplyKeywordDto:tReplyKeywordDtoList){
					if (!tReplyKeywordDto.getKreply_id().equalsIgnoreCase(in_tReplyKeywordDto.getKreply_id())){
						jsonInfo.put("message", "此记录已存在,请重新填写！");
						jsonInfo.put("success", false);
						outJSOND(response, jsonInfo.toString());
						return null;
					}
				}
			}
			
			//*************校验通过***************//
			
			//查询出数据库已存在的该数据,将页面的值设置到此数据中
			TReplyKeywordDto paeamTReplyKeywordDto = new TReplyKeywordDto();
			paeamTReplyKeywordDto.setKreply_id(in_tReplyKeywordDto.getKreply_id());
			TReplyKeywordDto tmpTReplyKeywordDto = tReplyKeywordService.getRow(paeamTReplyKeywordDto);
			
			//校验是否已经被删除
			if (null == tmpTReplyKeywordDto){
				jsonInfo.put("message", "此记录已被其他用户删除,请重新选择！");
				jsonInfo.put("success", false);
				outJSOND(response, jsonInfo.toString());
			}
				      
			//更新关键字(去空格后全部转换为大写)
			tmpTReplyKeywordDto.setKeyword(in_tReplyKeywordDto.getKeyword().trim().toUpperCase());
			//更新匹配类型
			tmpTReplyKeywordDto.setMatch_type(in_tReplyKeywordDto.getMatch_type());
			//更新回复类型
			tmpTReplyKeywordDto.setReply_type(in_tReplyKeywordDto.getReply_type());
			  
		  	//判断是否为文本回复类型  文本回复类型 需要将素材ID设置为空
			if(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG.equalsIgnoreCase(in_tReplyKeywordDto.getReply_type())){
				
				//设置是否动态模板    页面是否动态模板的输入值  1.CodeStringConstant.CS_1000_FALSE==未选中  2.CodeStringConstant.CS_1000_TRUE==选中
				if (CodeStringConstant.CS_1000_TRUE.equalsIgnoreCase(in_tReplyKeywordDto.getTemplet_flag())){
					
					tmpTReplyKeywordDto.setTemplet_flag(CodeStringConstant.CS_1000_TRUE);
					
				}else {
					
					tmpTReplyKeywordDto.setTemplet_flag(CodeStringConstant.CS_1000_FALSE);
					
				}

				//文本类型不需要素材ID字段,需要设置素材ID为null,同时设置文本信息
				tmpTReplyKeywordDto.setMaterial_id(null);
				//设置文本值  转码
				if(null != in_tReplyKeywordDto.getText_msg()){
					tmpTReplyKeywordDto.setText_msg(URLDecoder.decode(in_tReplyKeywordDto.getText_msg(),"UTF-8"));
				}
				
			//其他类型的回复类型,需要将是否动态模板和回复文本内容设置为null	
			}else{
				
				//设置动态模板值
				tmpTReplyKeywordDto.setTemplet_flag(null);
				//设置文本值
				tmpTReplyKeywordDto.setText_msg(null);
				//设置素材ID
				tmpTReplyKeywordDto.setMaterial_id(in_tReplyKeywordDto.getMaterial_id());
				
			}
			
			//设置生效标志
			tmpTReplyKeywordDto.setEffect_flag(in_tReplyKeywordDto.getEffect_flag());
		
			//发布开始时间  
			tmpTReplyKeywordDto.setPub_startdate(in_tReplyKeywordDto.getPub_startdate());
			//发布结束时间 
			tmpTReplyKeywordDto.setPub_enddate(in_tReplyKeywordDto.getPub_enddate());
			
			//设置更新人
			tmpTReplyKeywordDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			//设置更新时间
			tmpTReplyKeywordDto.setUpdated_date(new Timestamp(new Date().getTime()));
			//设置公众号
			tmpTReplyKeywordDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			//设置启用客服模式
			tmpTReplyKeywordDto.setCust_srv_flag(in_tReplyKeywordDto.getCust_srv_flag());
			
			Integer result = tReplyKeywordService.updateTReplyKeywordDto(tmpTReplyKeywordDto);
			
			//更新条数大于0时说明更新成功
			if (result > 0 ){
				jsonInfo.put("message", "编辑操作成功！");
				jsonInfo.put("success", true);
				outJSOND(response, jsonInfo.toString());
				return null;
			}
			
			jsonInfo.put("message", "编辑操作失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			return null;
			
		} catch (Exception ex) {
			
			jsonInfo.put("message", "编辑操作失败！");
			jsonInfo.put("success", false);
			outJSOND(response, jsonInfo.toString());
			log.error(ex.getMessage(),ex);
		} 
		return ERROR;

	}
	
	
	public TReplyKeywordDto getIn_tReplyKeywordDto() {
		return in_tReplyKeywordDto;
	}

	public void setIn_tReplyKeywordDto(TReplyKeywordDto inTReplyKeywordDto) {
		in_tReplyKeywordDto = inTReplyKeywordDto;
	}

	

}
