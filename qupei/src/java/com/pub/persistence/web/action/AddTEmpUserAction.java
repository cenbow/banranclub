package com.pub.persistence.web.action;

import com.pub.common.local.model.dto.TEmpUserDto;
import com.pub.common.tools.constant.CodeStringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.encryption.Encrypter;
import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.common.tools.permission.LoginUserInfoUtil;
import com.pub.persistence.service.ITEmpUserService;

 /**
 * 类功能:添加csr系统用户
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("addTEmpUserAction")
@Scope("prototype")
public class AddTEmpUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITEmpUserService tEmpUserService;
	private TEmpUserDto tEmpUserDto= new TEmpUserDto();

	public String execute() throws Exception {
		try {
			//密码加密
			tEmpUserDto.setPassword(Encrypter.encrypt(tEmpUserDto.getPassword()));
			String userCd = LoginUserInfoUtil.getLoginUserCD();
			//获取锁定状态
			if(tEmpUserDto.getLock_flag() == null || tEmpUserDto.getLock_flag().trim().length() <= 0)
			{
				tEmpUserDto.setLock_flag(CodeStringConstant.CS_3000_LOCK_FLAG_KEY);
			}
			tEmpUserDto.setCreated_user_cd(userCd);//保存新建用户
			tEmpUserDto.setUpdated_user_cd(userCd);//保存更新用户
			tEmpUserService.save(tEmpUserDto);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}


	public final TEmpUserDto getTEmpUserDto() {
		return tEmpUserDto;
	}

	public final void setTEmpUserDto(TEmpUserDto tEmpUserDto) {
		this.tEmpUserDto = tEmpUserDto;
	}

}
