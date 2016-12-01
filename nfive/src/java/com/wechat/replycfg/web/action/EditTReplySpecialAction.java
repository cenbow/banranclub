package com.wechat.replycfg.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;

 /**
 * 类功能:特殊回复编辑
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTReplySpecialAction")
@Scope("prototype")
public class EditTReplySpecialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplySpecialService tReplySpecialService;
    @Autowired
    private ITArticleGroupService tArticleGroupService;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;

	//入参
	private TReplySpecialDto tReplySpecialDto =new TReplySpecialDto();
	//出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(EditTReplySpecialAction.class);

	public String execute() throws Exception {
		  //返回结果
		String result = null;
		try {
		      TReplySpecialDto tmpTReplySpecialDto = new TReplySpecialDto();
			  tmpTReplySpecialDto.setFreply_id(tReplySpecialDto.getFreply_id());
			  TReplySpecialDto tmpReplySpecialDto = tReplySpecialService.getRow(tmpTReplySpecialDto);
			  if(tmpReplySpecialDto == null){
					//更新失败
					result = "{\"success\":false,\"message\":\"特殊回复规则编辑失败\"}";
					outJSOND(this.getResponse(), result);

					return null;
			  }


			  tmpTReplySpecialDto.setReply_type(tReplySpecialDto.getReply_type());
			  tmpTReplySpecialDto.setEffect_flag(tReplySpecialDto.getEffect_flag());
			  /*
			   * 1.回复类型是文本进行处理；    2.回复类型是图片、图文、音频、视频类型不进行处理
			   */
			 //判断文本还是素材
			  if(!tReplySpecialDto.getReply_type().equals(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG) ){
				  tmpTReplySpecialDto.setMaterial_id(tReplySpecialDto.getMaterial_id());

                    //查看所引用的素材是否存在
		    		if(CodeStringConstant.CS_5052_REPLAY_ARTICLE_MSG.equals(tReplySpecialDto.getReply_type())){
		    			TArticleGroupDto tArticleGroupDto= new TArticleGroupDto();
		    			tArticleGroupDto.setArticle_group_id(tReplySpecialDto.getMaterial_id());
		    			TArticleGroupDto tmpTArticleGroupDto = tArticleGroupService.getRow(tArticleGroupDto);
		    			if(tmpTArticleGroupDto == null){
		    				result = "{\"success\":false,\"message\":\"引用的图文组不存在\"}";
		    				outJSOND(this.getResponse(), result);

		    				return null;
		    			}
		    		}else if(CodeStringConstant.CS_5052_REPLAY_PICTURE_MSG.equals(tReplySpecialDto.getReply_type())){
		    			TMaterialPictureDto tMaterialPictureDto= new TMaterialPictureDto();
		    			tMaterialPictureDto.setPicture_id(tReplySpecialDto.getMaterial_id());
		    			TMaterialPictureDto tmpTMaterialPictureDto=tMaterialPictureService.getRow(tMaterialPictureDto);
		    			if(tmpTMaterialPictureDto == null){
		    				result = "{\"success\":false,\"message\":\"引用的图片不存在\"}";
		    				outJSOND(this.getResponse(), result);

		    				return null;
		    			}
		    		}

				  tmpTReplySpecialDto.setText_msg(null);
				  tReplySpecialDto.setTemplet_flag(null);
			  }else{
				  tmpTReplySpecialDto.setText_msg(tReplySpecialDto.getText_msg());
				//设置动态模板值
			    	if(CodeStringUtil.tranCsValueByCsCode(CodeStringConstant.CS_1000_TRUE).equals(tReplySpecialDto.getTemplet_flag())){
			    		tReplySpecialDto.setTemplet_flag(CodeStringUtil.tranCsValueByCsCode(CodeStringConstant.CS_1000_TRUE));
			    	}else{
			    		tReplySpecialDto.setTemplet_flag(CodeStringUtil.tranCsValueByCsCode(CodeStringConstant.CS_1000_FALSE));
			    	}
			    	tmpTReplySpecialDto.setText_msg(tReplySpecialDto.getText_msg());
			  }
			  tmpTReplySpecialDto.setRule_type(tReplySpecialDto.getRule_type());
			  tmpTReplySpecialDto.setPlatform_id(tReplySpecialDto.getPlatform_id());
				//设置更新人
			  tmpTReplySpecialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

			  tReplySpecialService.updatePK(tmpTReplySpecialDto);
			//更新成功
		    result = "{\"success\":true,\"message\":\"特殊回复规则编辑成功\"}";
			outJSOND(this.getResponse(), result);

			return null;
		} catch (Exception ex) {
			//更新失败
			result = "{\"success\":false,\"message\":\"特殊回复规则编辑失败\"}";
			outJSOND(this.getResponse(), result);
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(),ex);
		}

		return ERROR;
	}

	public final TReplySpecialDto getTReplySpecialDto() {
		return tReplySpecialDto;
	}

	public final void setStudentDto(TReplySpecialDto tReplySpecialDto) {
		this.tReplySpecialDto = tReplySpecialDto;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
