package com.wechat.fans.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.fans.model.TFansGroupMemberRealQueryBean;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TFansGroupMemberRealDto;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.service.ITFansGroupMemberRealService;
import com.wechat.fans.service.ITWeixinFansService;

/**
 * 类功能:粉丝编辑
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.16</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("editTWeixinFansAction")
@Scope("prototype")
public class EditTWeixinFansAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITWeixinFansService tWeixinFansService;
	@Autowired
	private ITFansGroupMemberRealService tFansGroupMemberRealService;
	//入参
	private TWeixinFansQueryBean tWeixinFansQueryBean = new TWeixinFansQueryBean();
	  //出错信息
    private String errorMessage;
	private static final Logger logger = Logger.getLogger(EditTWeixinFansAction.class);
    
	public String execute() throws Exception {
		 //返回结果
		String result = null;
		try {
			TWeixinFansDto tWeixinFansDto = new TWeixinFansDto();
			tWeixinFansDto.setFans_id(tWeixinFansQueryBean.getFans_id());
			TWeixinFansDto tmpWeixinFansDto = tWeixinFansService.getRow(tWeixinFansDto);
			tmpWeixinFansDto.setRemark(tWeixinFansQueryBean.getRemark());
			int relt = tWeixinFansService.updatePK(tmpWeixinFansDto);
			if (relt == 0) {
				result = "{\"status\":false,\"message\":\"粉丝编辑失败\"}";
				outJSOND(this.getResponse(), result);
			} else {
				List<TWeixinFansGroupVo> tWeixinFansGroupVo = new ArrayList<TWeixinFansGroupVo>();
				if (tWeixinFansQueryBean.getFans_grouplist().length() != 0) {
					String[] fans_groupList = tWeixinFansQueryBean.getFans_grouplist().split(",");
					// 将页面选择的粉丝群ID放入tmpWeixinFansGroupVo
					for (int i = 0; i < fans_groupList.length; i++) {
						TWeixinFansGroupVo tmpWeixinFansGroupVo = new TWeixinFansGroupVo();
						tmpWeixinFansGroupVo.setFans_group_id(fans_groupList[i]);
						tWeixinFansGroupVo.add(tmpWeixinFansGroupVo);
					}
				} 
				// 获得已添加的群
				TFansGroupMemberRealQueryBean tFansGroupMemberRealQueryBean = new TFansGroupMemberRealQueryBean();
				tFansGroupMemberRealQueryBean.setFans_id(tWeixinFansQueryBean.getFans_id());
				tFansGroupMemberRealQueryBean.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
				List<TFansGroupMemberRealDto> tmpFansGroupMemberRealDto  =tFansGroupMemberRealService.getFansGroup(tFansGroupMemberRealQueryBean);
				
				// 去重
				Set<String> setOld = new TreeSet<String>();
				Set<String> setNew = new TreeSet<String>();
				// 将fans_group_id的放入set，
				for (int i = 0; i < tmpFansGroupMemberRealDto.size(); i++) {
					setOld.add(tmpFansGroupMemberRealDto.get(i).getFans_group_id());
				}
				for (int i = 0; i < tWeixinFansGroupVo.size(); i++) {
					setNew.add(tWeixinFansGroupVo.get(i).getFans_group_id());
				}
				//粉丝清除原来所在的粉丝群
				if(tWeixinFansGroupVo.size() == 0 && tmpFansGroupMemberRealDto.size() > 0){
					for(int x=0; x<tmpFansGroupMemberRealDto.size();x++)
					  tFansGroupMemberRealService.delete(tmpFansGroupMemberRealDto.get(x));
				}
				for (int i = 0; i < tWeixinFansGroupVo.size(); i++) {
					// 在原来群数上添加
					if (setOld.add(tWeixinFansGroupVo.get(i).getFans_group_id())&& tWeixinFansGroupVo.get(i).getFans_group_id().length() != 0) {
						TFansGroupMemberRealDto FansGroupMemberRealDto = new TFansGroupMemberRealDto();
						// 设置粉丝群ID
						FansGroupMemberRealDto.setFans_group_id(tWeixinFansGroupVo.get(i).getFans_group_id());
						// 设置粉丝id
						FansGroupMemberRealDto.setFans_id(tWeixinFansQueryBean.getFans_id());
						// 设置成员名称
						FansGroupMemberRealDto.setMember_name(tWeixinFansQueryBean.getNick_name());
						//设置标星成员
						FansGroupMemberRealDto.setStar_member(CodeStringConstant.CS_1000_FALSE);
						// 设置创建人
						FansGroupMemberRealDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
						// 设置更新人
						FansGroupMemberRealDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
						// 设置公众号平台号id
						FansGroupMemberRealDto.setPlatform_id(WechatInfoUtil.getCurPubPlatformBeanFromSession().getPlatform_id());
						tFansGroupMemberRealService.save(FansGroupMemberRealDto);
                      
					} else {
						for (int j = 0; j < tmpFansGroupMemberRealDto.size(); j++) {
							//删除减少的群
							if (!tWeixinFansGroupVo.get(i).getFans_group_id().equals(tmpFansGroupMemberRealDto.get(j).getFans_group_id())&& setNew.add(tmpFansGroupMemberRealDto.get(j).getFans_group_id())) {
								tFansGroupMemberRealService.delete(tmpFansGroupMemberRealDto.get(j));
							//更新操作人
							} else if(tWeixinFansGroupVo.get(i).getFans_group_id().equals(tmpFansGroupMemberRealDto.get(j).getFans_group_id())&& !setNew.add(tmpFansGroupMemberRealDto.get(j).getFans_group_id())) {
								// 设置更新人
								tmpFansGroupMemberRealDto.get(j).setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
								tFansGroupMemberRealService.updatePK(tmpFansGroupMemberRealDto.get(j));
							}
						}
					}
				}
			}
			result = "{\"status\":true,\"message\":\"粉丝编辑成功\"}";
			outJSOND(this.getResponse(), result);
			
			return null;
		} catch (Exception ex) {
			result = "{\"status\":false,\"message\":\"粉丝编辑失败\"}";
			outJSOND(this.getResponse(), result);
			setErrorMessage(ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		
		return ERROR;

	}

	public TWeixinFansQueryBean getTWeixinFansQueryBean() {
		return tWeixinFansQueryBean;
	}

	public void setTWeixinFansQueryBean(TWeixinFansQueryBean tWeixinFansQueryBean) {
		this.tWeixinFansQueryBean = tWeixinFansQueryBean;
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
