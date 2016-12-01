package com.wechat.platform.web;

import java.util.List;

import com.platform.common.tools.codestring.CodeStringUtil;
import com.platform.common.tools.codestring.SelectCsBean;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//import com.gtcore.rmtserver.IGtCoreRmtTWxPlatformService;

/**
 * 类功能:自动代码生成模板新增   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("addTWxPlatformPage")
@Scope("prototype")
public class AddTWxPlatformPage extends BaseAction {
	private static final long serialVersionUID = 1L;

//	@Autowired
//	private IGtCoreRmtTWxPlatformService gtCoreRmtTWxPlatformService;

	private List<SelectCsBean> platform_type;

	public String execute() throws Exception {

		try {

			// 特殊回复规则下拉列表
            platform_type = CodeStringUtil.getSelectCsBeanByCsType(CodeStringConstant.ACCOUNT_TYPE,CodeStringConstant.ACCOUNT_TYPE_SERVICE,new String[]{CodeStringConstant.ACCOUNT_TYPE_NOTINPUT});

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}

    public List<SelectCsBean> getPlatform_type() {
        return platform_type;
    }

    public void setPlatform_type(List<SelectCsBean> platform_type) {
        this.platform_type = platform_type;
    }
}
