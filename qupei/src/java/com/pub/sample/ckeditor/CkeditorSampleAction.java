package com.pub.sample.ckeditor;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;

@Controller("ckeditorSampleAction")
@Scope("prototype")
public class CkeditorSampleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

    private static  Logger logger  = Logger.getLogger(CkeditorSampleAction.class);	



	public String execute() throws Exception {
		try {

			
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return ERROR;

	}
}


