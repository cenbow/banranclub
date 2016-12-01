package com.platform.persistence.web.action;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hercules.common.encryption.Encrypter;
import com.platform.common.tools.activity.ActivityUtil;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.platform.persistence.dao.ITEmpExtinfoDao;
import com.platform.persistence.model.dto.TEmpExtinfoDto;
import com.platform.persistence.model.dto.TEmpUserDto;
import com.platform.persistence.service.ITEmpUserService;

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

	@Autowired
    private ITEmpExtinfoDao   tEmpExtinfoDao; //用户扩展表

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
			//用户扩展信息表中插入数据
			TEmpExtinfoDto empExtInfo = new TEmpExtinfoDto();
			empExtInfo.setUser_cd(tEmpUserDto.getUser_cd());//用户CD
			empExtInfo.setLogin_try_times(BigDecimal.ZERO);//尝试次数
			empExtInfo.setIp_address(ActivityUtil.getIpAddress(request));//登录IP
			empExtInfo.setLast_login_time(new Date());//登录时间
			tEmpExtinfoDao.save(empExtInfo);
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}


     public TEmpUserDto gettEmpUserDto() {
         return tEmpUserDto;
     }

     public void settEmpUserDto(TEmpUserDto tEmpUserDto) {
         this.tEmpUserDto = tEmpUserDto;
     }
 }
