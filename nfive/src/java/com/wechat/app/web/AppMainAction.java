package com.wechat.app.web;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by jinzhihong on 16/11/26.
 */
@Controller("appMainAction")
@Scope("prototype")
public class AppMainAction extends BaseAction {


    public String execute() throws Exception {
        try {
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ERROR;
    }
}
