package com.wechat.replycfg.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;
import com.wechat.replycfg.model.dto.TReplySpecialDto;
import com.wechat.replycfg.service.ITReplySpecialService;

 /**
 * 类功能:特殊回复添加
 * <p>创建者:shengsheng.zhao</p>
 * <p>创建时间:2014-8-12</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTReplySpecialAction")
@Scope("prototype")
public class AddTReplySpecialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITReplySpecialService tReplySpecialService;
    @Autowired
    private ITArticleGroupService tArticleGroupService;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;

	//入参
	private TReplySpecialDto tReplySpecialDto= new TReplySpecialDto();
	//出错信息
    private String errorMessage;
	protected static final Logger logger = Logger.getLogger(AddTReplySpecialAction.class);

	public String execute() throws Exception {
        //返回结果
		String result = null;
		try {

			    	//1.文本输入处理 2.图片、图文、音频、视频处理
			    	if(CodeStringConstant.CS_5052_REPLAY_TEXT_MSG.equals(tReplySpecialDto.getReply_type())){
			    		//设置素材ID
			    		tReplySpecialDto.setMaterial_id(null);
			    		//设置动态模板值
				    	if(CodeStringConstant.CS_1000_TRUE.equals(tReplySpecialDto.getTemplet_flag())){
				    		//设置动态模板值为是
				    		tReplySpecialDto.setTemplet_flag(CodeStringConstant.CS_1000_TRUE);
				    	}else{
				    		//设置动态模板值为否
				    		tReplySpecialDto.setTemplet_flag(CodeStringConstant.CS_1000_FALSE);
				    	}
				        tReplySpecialDto.setText_msg(tReplySpecialDto.getText_msg());
			    	}else{
			    	   //	查看所引用的素材是否存在
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
			    		//设置文本消息
			    		tReplySpecialDto.setText_msg(null);
			    		//设置是否动态
			    		tReplySpecialDto.setTemplet_flag(null);
			    	}

			    	//设置公众号
			    	tReplySpecialDto.setPlatform_id( WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
			    	//设置创建人
			    	tReplySpecialDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			    	//设置更新人
			    	tReplySpecialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
			    	//保存成功
			    	tReplySpecialService.save(tReplySpecialDto);
			        //返回结果
					result = "{\"success\":true,\"message\":\"特殊回复规则新增成功\"}";
					outJSOND(this.getResponse(), result);

                    return null;
		} catch (Exception ex) {
			  //返回结果
			result = "{\"success\":false,\"message\":\"特殊回复规则新增失败\"}";
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
