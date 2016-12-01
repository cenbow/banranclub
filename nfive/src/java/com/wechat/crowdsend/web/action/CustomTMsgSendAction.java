package com.wechat.crowdsend.web.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.vo.ArticleItemVo;
import com.wechat.article.service.ITArticleGroupService;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.core.beans.other.MassMessageResult;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.core.utils.Html2TxtUtil;
import com.wechat.crowdsend.model.dto.TMsgSendDto;
import com.wechat.crowdsend.model.dto.TMsgSendGroupDto;
import com.wechat.crowdsend.model.dto.TMsgSendResultDto;
import com.wechat.crowdsend.model.dto.TMsgSendUesrDto;
import com.wechat.crowdsend.sendutils.SendMassMessageUtils;
import com.wechat.crowdsend.service.ITMsgSendService;
import com.wechat.fans.dao.ITLatestFansDao;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.dto.TLatestFansDto;
import com.wechat.picture.model.dto.TMaterialPictureDto;
import com.wechat.picture.service.ITMaterialPictureService;


 /**
 * 类功能:群发管理-自定义群发处理类
 * 客服接口一个批次号，高级接口每一万条一个批次号，不足一万按一万处理
 * <p>创建者:周要领</p>
 * <p>创建时间:2014-09-19</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("customTMsgSendAction")
@Scope("prototype")
public class CustomTMsgSendAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CustomTMsgSendAction.class);
	@Autowired
	private ITMsgSendService tMsgSendService;
	@Autowired
	private ITArticleGroupService tArticleGroupService;
	@Autowired
	private ITMaterialPictureService tMaterialPictureService;
	@Autowired
	private ITArticleItemService tArticleItemService;

	private TMsgSendDto tmpMsgSendDto= new TMsgSendDto();

	@Autowired
	private ITLatestFansDao  tLatestFansDao;//最新粉丝DAO

	private String[] fans_group;	//本地粉丝群
	private String errorMessage;//统一报错信息
//	public String execute() throws Exception {
//
//		if(null == WechatInfoUtil.getCurPubPlatformBeanFromSession()){
//			errorMessage = "当前无可用公众帐号，发送失败！";
//			return ERROR;
//		}
//		JSONObject pageReturn = new JSONObject();
//
//		//待保存对象
//		List<TMsgSendDto> toSaveSend = new ArrayList<TMsgSendDto>();
//		TMsgSendDto tmpTMsgSendDto;
//		List<TMsgSendResultDto> toSaveResult = new ArrayList<TMsgSendResultDto>();
//		TMsgSendResultDto tmpTMsgSendResultDto;
//		List<TMsgSendGroupDto> toSaveGroup = new ArrayList<TMsgSendGroupDto>();
//		TMsgSendGroupDto tmpTMsgSendGroupDto ;
//		List<TMsgSendUesrDto> toSaveUser = new ArrayList<TMsgSendUesrDto>();
//		TMsgSendUesrDto tmpTMsgSendUesrDto;
//
//		List<String> openIdList = null;//发送openid列表
//		String batch_no = null;//批次号
//
//		try {
//			// 1 取得发送对象列表
//			// 1.1   选择粉丝群，获取所选粉丝群内粉丝id
//			if(CodeStringConstant.CS_1000_TRUE.equals(tmpMsgSendDto.getFans_group_flg())){
//
//				//所有粉丝发送
//				if( CodeStringConstant.CS_5060_CROWD_SEND_ALL.equals(tmpMsgSendDto.getSend_target()) ){
//					TLatestFansDto queryDto = new TLatestFansDto();
//					queryDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
//					List<TLatestFansDto> latestFans = tLatestFansDao.getAll(queryDto);
//					openIdList = new ArrayList<String>();
//					for(TLatestFansDto fan: latestFans){
//						openIdList.add(fan.getOpenid());
//					}
//				}else{//粉丝组发送
//					TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean = new TFansGroupMemberRealQueryBean();
//					StringBuffer grouplist = new StringBuffer();
//					if(null != fans_group && 0 < fans_group.length){
//						for(String str:fans_group){
//							grouplist.append(",").append("'"+str+"'");
//						}
//					}
//					//获取待发送openid
//					tFansGroupMemberRealQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id()); //平台ID
//					tFansGroupMemberRealQueryBean.setFans_group_id(grouplist.delete(0, 1).toString());
//					openIdList = tMsgSendService.querySendTarget(tFansGroupMemberRealQueryBean);
//				}
//			// 1.2 选择活动组，获取该组内粉丝id
//			}else{
//				//TODO 活动组功能开发完成后处理
//			}
//
//			//发送列表为空
//			if(null == openIdList || 0 >= openIdList.size()){
//				pageReturn.put("success", false);
//				pageReturn.put("msg", "发送列表为空，请确认后重新发送！");
//				outJSOND(response, pageReturn.toString());
//				return null;
//			}
//
//			// 2.  发送信息，处理待保存结果
//			// 2.1  使用客服接口，对列表循环，单个发送
//			if(CodeStringConstant.CS_5061_SEND_IF_CUSIN.equals(tmpMsgSendDto.getSend_if())){
//				int succss_count=0;	//成功记录
//				int error_count=0;	//失败记录数
//				String error_code = null;
//				String error_msg = null;
//				batch_no = tMsgSendService.newBatchNo();//客服接口一个批次号足够使用
//
//				try{
//					SmsBackTask task = new SmsBackTask(openIdList,tmpMsgSendDto);
//					task.run();
//					//微信返回错误代码
//					error_code = task.getError_code();
//					error_msg = task.getError_msg();
//					List<String> sendedUserIds = task.getToSaveUser();
//
//					succss_count = sendedUserIds.size();
//					error_count = openIdList.size() - sendedUserIds.size();
//					//已发送的用户列表
//					for(String openId: sendedUserIds){
//						tmpTMsgSendUesrDto = new TMsgSendUesrDto();
//						tmpTMsgSendUesrDto.setBatch_no(batch_no);
//						tmpTMsgSendUesrDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						tmpTMsgSendUesrDto.setOpen_id(openId);
//						tmpTMsgSendUesrDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						toSaveUser.add(tmpTMsgSendUesrDto);
//					}
//				}catch(Exception ex){
//					String msg = ex.getMessage();
//					//本地异常，不保存数据库，部发送数据到微信服务器
//					pageReturn.put("success", false);
//					pageReturn.put("msg", msg);
//					outJSOND(response, pageReturn.toString());
//					return null;
//
//				}
//
//				//粉丝群组发送履历表
//				if( CodeStringConstant.CS_5060_CROWD_SEND_ALL.equals(tmpMsgSendDto.getSend_target()) ){
//					tmpTMsgSendGroupDto = new TMsgSendGroupDto();
//					tmpTMsgSendGroupDto.setBatch_no(batch_no);
//					tmpTMsgSendGroupDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//					tmpTMsgSendGroupDto.setGroup_id(CodeStringConstant.CS_5060_CROWD_SEND_ALL);
//					tmpTMsgSendGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//					toSaveGroup.add(tmpTMsgSendGroupDto);
//				}else{
//					for(String str:fans_group){
//						tmpTMsgSendGroupDto = new TMsgSendGroupDto();
//						tmpTMsgSendGroupDto.setBatch_no(batch_no);
//						tmpTMsgSendGroupDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						tmpTMsgSendGroupDto.setGroup_id(str);
//						tmpTMsgSendGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						toSaveGroup.add(tmpTMsgSendGroupDto);
//					}
//				}
//
//
//				int total = AdvancedUtil.getUserList(WechatInfoUtil.getAccessToken(CacheKeyUtil.getAccessTokenKey(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id())), "").getTotal();
//				//发送结果表
//				tmpTMsgSendResultDto = new TMsgSendResultDto();
//				tmpTMsgSendResultDto.setBatch_no(batch_no);
//				tmpTMsgSendResultDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//				tmpTMsgSendResultDto.setError_count(new Long(error_count));
//				tmpTMsgSendResultDto.setFilter_count(new Long(openIdList.size()));
//				tmpTMsgSendResultDto.setSend_status(succss_count == 0?CodeStringConstant.CS_5063_SEND_STATUS_FAILED:CodeStringConstant.CS_5063_SEND_STATUS_SUCCESS);
//				tmpTMsgSendResultDto.setSent_count(new Long(succss_count));
//				tmpTMsgSendResultDto.setTotal_count(new Long(total));//粉丝总数量
//				tmpTMsgSendResultDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//				tmpTMsgSendResultDto.setErr_code(error_code);
//				tmpTMsgSendResultDto.setErr_msg(error_msg);
//				toSaveResult.add(tmpTMsgSendResultDto);
//
//				//发送履历表
//				tmpTMsgSendDto = new TMsgSendDto();
//				tmpTMsgSendDto.setBatch_no(batch_no);
//				tmpTMsgSendDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//				tmpTMsgSendDto.setError_count(new Long(error_count));
//				tmpTMsgSendDto.setFans_group_flg(tmpMsgSendDto.getFans_group_flg());
//				tmpTMsgSendDto.setFilter_count(new Long(openIdList.size()));
//				tmpTMsgSendDto.setMaterial_id(tmpMsgSendDto.getMaterial_id());
//				tmpTMsgSendDto.setMsg_type(tmpMsgSendDto.getMsg_type());
//				tmpTMsgSendDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
//				tmpTMsgSendDto.setRemark(tmpMsgSendDto.getRemark());
//				tmpTMsgSendDto.setSend_dist(tmpMsgSendDto.getSend_dist());
//				tmpTMsgSendDto.setSend_if(tmpMsgSendDto.getSend_if());
//				tmpTMsgSendDto.setSend_status(succss_count==0?CodeStringConstant.CS_5063_SEND_STATUS_FAILED:CodeStringConstant.CS_5063_SEND_STATUS_SUCCESS);
//				tmpTMsgSendDto.setSend_target(tmpMsgSendDto.getSend_target());
//				tmpTMsgSendDto.setSend_time(new Date());
//				tmpTMsgSendDto.setSent_count(new Long(succss_count));
//				tmpTMsgSendDto.setTemplet_flag(tmpMsgSendDto.getTemplet_flag());
//				tmpTMsgSendDto.setText_msg(URLDecoder.decode(tmpMsgSendDto.getText_msg(),"UTF-8"));
//				tmpTMsgSendDto.setTotal_count(new Long(total));//粉丝总数量
//				tmpTMsgSendDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//				toSaveSend.add(tmpTMsgSendDto);
//
//			// 2.2 使用高级群发接口，每次发送上限为1W条，每1W条一个批次
//			}else{
//
//				String msgType = tmpMsgSendDto.getMsg_type();
//
//				//拼装openid
//				String[] openIds= new String[10000>openIdList.size()?openIdList.size():10000];
//				int index=0;
//				batch_no = tMsgSendService.newBatchNo();
//				MassMessageResult massMessageResult = null;
//				for(int i=0;i<openIdList.size();i++){
//					openIds[index]=openIdList.get(i);
//					index+=1;
//					//发送对象表记录，对单个Id的记录
//					tmpTMsgSendUesrDto = new TMsgSendUesrDto();
//					tmpTMsgSendUesrDto.setBatch_no(batch_no);
//					tmpTMsgSendUesrDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//					tmpTMsgSendUesrDto.setOpen_id(openIdList.get(i));
//					tmpTMsgSendUesrDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//					toSaveUser.add(tmpTMsgSendUesrDto);
//
//					//每1W条发送一次，剩余数量不足一万也发送
//					if((i>=9999&&i%9999==0) || i==openIdList.size()-1){
//						index = 0;
//
//							//文本
//							if(CodeStringConstant.CS_5062_SEND_TYPE_TEXT.equals(msgType)){
//								massMessageResult = SendMassMessageUtils.sendMessageMassSend(openIds, Html2TxtUtil.removeHTMLTag(URLDecoder.decode(tmpMsgSendDto.getText_msg(),"UTF-8")));
//							//图文
//							}else if(CodeStringConstant.CS_5062_SEND_TYPE_ARTCLE.equals(msgType)){
//								List<ArticleItemVo> articleItems = tArticleGroupService.getArticleGroupPublishableItems(tmpMsgSendDto.getMaterial_id());
//								 for (ArticleItemVo articleItem : articleItems) {
//									 TArticleItemDto tmp = new TArticleItemDto();
//									 tmp.setArticle_item_id(articleItem.getArticle_item_id());
//									 tmp = tArticleItemService.getRow(tmp);
//									 articleItem.setContent(null==tmp.getContent()?"":tmp.getContent());
//								 }
//								massMessageResult = SendMassMessageUtils.sendMessageMassSend(openIds, articleItems);
//							//图片
//							}else if(CodeStringConstant.CS_5062_SEND_TYPE_IMAGE.equals(msgType)){
//								TMaterialPictureDto tMaterialPictureDto = new TMaterialPictureDto();
//								tMaterialPictureDto.setPicture_id(tmpMsgSendDto.getMaterial_id());
//								tMaterialPictureDto = tMaterialPictureService.getRow(tMaterialPictureDto);
//								massMessageResult = SendMassMessageUtils.sendMessageMassSend(openIds, tMaterialPictureDto);
//							}
//						//粉丝群组发送履历表
//						if( CodeStringConstant.CS_5060_CROWD_SEND_ALL.equals(tmpMsgSendDto.getSend_target()) ){
//
//							tmpTMsgSendGroupDto = new TMsgSendGroupDto();
//							tmpTMsgSendGroupDto.setBatch_no(batch_no);
//							tmpTMsgSendGroupDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//							tmpTMsgSendGroupDto.setGroup_id(CodeStringConstant.CS_5060_CROWD_SEND_ALL);
//							tmpTMsgSendGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//							toSaveGroup.add(tmpTMsgSendGroupDto);
//
//						}else{
//							for(String str:fans_group){
//								tmpTMsgSendGroupDto = new TMsgSendGroupDto();
//								tmpTMsgSendGroupDto.setBatch_no(batch_no);
//								tmpTMsgSendGroupDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//								tmpTMsgSendGroupDto.setGroup_id(str);
//								tmpTMsgSendGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//								toSaveGroup.add(tmpTMsgSendGroupDto);
//							}
//						}
//
//						//发送结果表数据
//						tmpTMsgSendResultDto = new TMsgSendResultDto();
//						tmpTMsgSendResultDto.setBatch_no(batch_no);
//						tmpTMsgSendResultDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						tmpTMsgSendResultDto.setErr_code(massMessageResult.getErrcode());
//						tmpTMsgSendResultDto.setErr_msg(massMessageResult.getErrmsg());
//						tmpTMsgSendResultDto.setMsg_code(massMessageResult.getMsg_id());
//						tmpTMsgSendResultDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						tmpTMsgSendResultDto.setSend_status("0".equals(massMessageResult.getErrcode())?CodeStringConstant.CS_5063_SEND_STATUS_PROCESSING:CodeStringConstant.CS_5063_SEND_STATUS_AUDITFAILURE);
//						toSaveResult.add(tmpTMsgSendResultDto);
//
//						//发送表记录
//						tmpTMsgSendDto = new TMsgSendDto();
//						tmpTMsgSendDto.setActivity_group_id(tmpMsgSendDto.getActivity_group_id());
//						tmpTMsgSendDto.setBatch_no(batch_no);
//						tmpTMsgSendDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						tmpTMsgSendDto.setFans_group_flg(tmpMsgSendDto.getFans_group_flg());
//						tmpTMsgSendDto.setMaterial_id(tmpMsgSendDto.getMaterial_id());
//						tmpTMsgSendDto.setMsg_type(tmpMsgSendDto.getMsg_type());
//						tmpTMsgSendDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
//						tmpTMsgSendDto.setRemark(tmpMsgSendDto.getRemark());
//						tmpTMsgSendDto.setSend_dist(tmpMsgSendDto.getSend_dist());
//						tmpTMsgSendDto.setSend_if(tmpMsgSendDto.getSend_if());
//						tmpTMsgSendDto.setSend_status("0".equals(massMessageResult.getErrcode())?CodeStringConstant.CS_5063_SEND_STATUS_PROCESSING:CodeStringConstant.CS_5063_SEND_STATUS_AUDITFAILURE);
//						tmpTMsgSendDto.setSend_target(tmpMsgSendDto.getSend_target());
//						tmpTMsgSendDto.setTemplet_flag(tmpMsgSendDto.getTemplet_flag());
//						tmpTMsgSendDto.setText_msg(URLDecoder.decode(tmpMsgSendDto.getText_msg(),"UTF-8"));
//						tmpTMsgSendDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
//						tmpTMsgSendDto.setWeixin_group_id(tmpMsgSendDto.getWeixin_group_id());
//						toSaveSend.add(tmpTMsgSendDto);
//
//						//循环发送为下一组生成批次号
//						batch_no = tMsgSendService.newBatchNo();
//					}
//
//				}
//
//				if (StringUtils.isNotBlank(massMessageResult.getErrcode()) && !"0".equals(massMessageResult.getErrcode())) {
//					pageReturn.put("success", false);
//					pageReturn.put("msg", "[" + massMessageResult.getErrcode() + "]" + massMessageResult.getErrmsg());
//					outJSOND(response, pageReturn.toString());
//					return null;
//				}
//			}
//
//			// 3. 保存结果至数据库
//			tMsgSendService.saveupdateTMsgSendDto(toSaveSend, toSaveResult, toSaveGroup, toSaveUser);
//			pageReturn.put("success", true);
//			pageReturn.put("msg", "发送成功，具体结果请在群发日志一览页面查看，确认前往查看？");
//			outJSOND(response, pageReturn.toString());
//			return null;
//		} catch (Exception ex) {
//			logger.error(ex.getMessage(),  ex);
//			pageReturn.put("msg", "系统异常，请稍后重试。");
//			pageReturn.put("success", false);
//			outJSOND(response, pageReturn.toString());
//			return null;
//		}
//	}


	public String[] getFans_group() {
		return fans_group;
	}

	public void setFans_group(String[] fansGroup) {
		fans_group = fansGroup;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public TMsgSendDto getTmpMsgSendDto() {
		return tmpMsgSendDto;
	}

	public void setTmpMsgSendDto(TMsgSendDto tmpMsgSendDto) {
		this.tmpMsgSendDto = tmpMsgSendDto;
	}


	public final TMsgSendDto getTMsgSendDto() {
		return tmpMsgSendDto;
	}

	public final void setTMsgSendDto(TMsgSendDto tMsgSendDto) {
		this.tmpMsgSendDto = tMsgSendDto;
	}



}
