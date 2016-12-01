package com.pub.sample.fileupload;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.sample.ckeditor.CkeditorSampleAction;

@Controller("showFileSampleAction")
@Scope("prototype")
public class ShowFileSampleAction extends BaseAction {
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


