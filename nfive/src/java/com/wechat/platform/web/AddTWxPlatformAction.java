package com.wechat.platform.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.wechat.WechatUtil;
import com.wechat.menucfg.model.dto.TWcmSelfRelaDto;
import com.wechat.menucfg.service.ITWcmSelfRelaService;
import com.wechat.platform.dao.ITWxPlatformDao;
import com.wechat.platform.model.TWxPlatformDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


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
@Controller("addTWxPlatformAction")
@Scope("prototype")
public class AddTWxPlatformAction extends BaseAction {

	@Autowired
	private ITWxPlatformDao wxPlatformDao;
    @Autowired
    private ITWcmSelfRelaService tWcmSelfRelaService;

	private TWxPlatformDto tWxPlatformDto = new TWxPlatformDto();

	public String execute() throws Exception {
		try {
            wxPlatformDao.save(tWxPlatformDto);

			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;
	}

    /**
     * 创建菜单根节点
     * @return
     */
    public String createRootMenu() throws Exception{
        boolean result = true;
        String message = "";
        try {
            TWxPlatformDto wxPlatformDto = wxPlatformDao.getRow(new TWxPlatformDto());
            //检查是否已有跟节点存在
            TWcmSelfRelaDto tWcmSelfRelaDto = new TWcmSelfRelaDto();
            tWcmSelfRelaDto.setParent_id("ROOT");
            tWcmSelfRelaDto.setPlatform_id(wxPlatformDto.getPlatform_id());
            tWcmSelfRelaDto = tWcmSelfRelaService.getRow(tWcmSelfRelaDto);
            if(tWcmSelfRelaDto==null) {
                tWcmSelfRelaService.createTWcmenu(wxPlatformDto.getPlatform_id(), wxPlatformDto.getPlatform_name());
            }else{
                result = false;
                message="菜单已存在";
            }
            outJSOND(response, "{\"status\":" + result + ",\"message\":\""
                    + message + "\"}");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

	public final TWxPlatformDto getTWxPlatformDto() {
		return tWxPlatformDto;
	}

	public final void setTWxPlatformDto(TWxPlatformDto tWxPlatformDto) {
		this.tWxPlatformDto = tWxPlatformDto;
	}

}
