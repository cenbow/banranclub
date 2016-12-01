package com.wechat.replycfg.web.action;


import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.platform.common.tools.wechat.WechatUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.replycfg.model.TReplyKeywordQueryBean;
import com.wechat.replycfg.model.dto.TReplyKeywordDto;
import com.wechat.replycfg.service.ITReplyKeywordService;

 /**
 * 类功能:新增关键字回复规则
 * <p>创建者:gy</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTReplyKeywordAction")
@Scope("prototype")
public class AddTReplyKeywordAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplyKeywordService tReplyKeywordService;

	//入参  关键字回复规则
	private TReplyKeywordDto in_tReplyKeywordDto= new TReplyKeywordDto();

	protected static final Log log = LogFactory.getLog(AddTReplyKeywordAction.class);

	public String execute() throws Exception {


		//返回结果提示
		JSONObject jsonInfo = new JSONObject();
		try {

			// 检查该关键字是否存在  1.关键字不能相同；2.公众号不能相同；3.发布时间期间；4.匹配类型
			TReplyKeywordQueryBean param_tReplyKeywordQueryBean = new TReplyKeywordQueryBean();
			param_tReplyKeywordQueryBean.setKeyword(in_tReplyKeywordDto.getKeyword().trim().toUpperCase());//关键字
			param_tReplyKeywordQueryBean.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());//当前公众号号
			param_tReplyKeywordQueryBean.setPub_startdate(new Date());//发布开始时间
			param_tReplyKeywordQueryBean.setPub_enddate(new Date());//发布结束时间
			param_tReplyKeywordQueryBean.setMatch_type(in_tReplyKeywordDto.getMatch_type());//匹配类型
			List<TReplyKeywordDto>  tReplyKeywordDtoList = tReplyKeywordService.checkTReplyKeywordDto(param_tReplyKeywordQueryBean);
			if (null != tReplyKeywordDtoList && tReplyKeywordDtoList.size() > 0){
				jsonInfo.put("message", "此记录已存在,请重新填写！");
				jsonInfo.put("success", false);
				outJSOND(response, jsonInfo.toString());
				return null;

			}


			//关键字设置为大写，以便用户请求关键字时不区分大小写匹配
			in_tReplyKeywordDto.setKeyword(in_tReplyKeywordDto.getKeyword().trim().toUpperCase());

			//判断是否为文本回复类型   文本回复类型需要将素材ID设置为空
			if(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG.equalsIgnoreCase(in_tReplyKeywordDto.getReply_type())){

				//设置是否动态模板    页面是否动态模板的输入值  1.CodeStringConstant.CS_1000_FALSE==未选中  2.CodeStringConstant.CS_1000_TRUE==选中
				if (CodeStringConstant.CS_1000_TRUE.equalsIgnoreCase(in_tReplyKeywordDto.getTemplet_flag())){
					in_tReplyKeywordDto.setTemplet_flag(CodeStringConstant.CS_1000_TRUE);
				}else {
					in_tReplyKeywordDto.setTemplet_flag(CodeStringConstant.CS_1000_FALSE);
				}

				//设置文本值  解码
				in_tReplyKeywordDto.setText_msg(URLDecoder.decode(in_tReplyKeywordDto.getText_msg(), "UTF-8"));

				//文本类型不需要独裁ID字段,需要设置素材ID为null
				in_tReplyKeywordDto.setMaterial_id(null);

			//其他类型的回复类型,需要将是否动态模板和回复文本内容设置为null
			}else{
				//设置动态模板值
				in_tReplyKeywordDto.setTemplet_flag(null);
				//设置文本值
				in_tReplyKeywordDto.setText_msg(null);

			}


			//设置创建人
			in_tReplyKeywordDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());

			//设置公众号
			in_tReplyKeywordDto.setPlatform_id(WechatUtil.getWxPlatform().getPlatform_id());

			//保存数据
			tReplyKeywordService.save(in_tReplyKeywordDto);


			jsonInfo.put("message", "新增操作成功！");
			jsonInfo.put("success", true);
			outJSOND(response, jsonInfo.toString());

			return null;
		} catch (Exception ex) {

			jsonInfo.put("message", "新增操作失败！");
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
