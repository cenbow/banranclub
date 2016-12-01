package com.pub.demo.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.pub.common.tools.opensymphony.web.action.BaseAction;
import com.pub.persistence.service.ITCommonAttachsService;

/**
 * 文件上传Demo
 */
@Controller("fileUploadDemoAction")
@Scope("prototype")
public class FileUploadDemoAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String addFiles1;
	private String delFiles1;

	private String addFiles2;
	private String delFiles2;

	@Autowired
	private ITCommonAttachsService commonAttachsService;

	public String execute() throws Exception {
		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}

	/***
	 * 保存上传文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submit() {

		try {

			commonAttachsService.processUpload("referenceCode001", "functionCode001", addFiles1, delFiles1);
			commonAttachsService.processUpload("referenceCode002", "functionCode002", addFiles2, delFiles2);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return SUCCESS;
	}

	public String getAddFiles1() {
		return addFiles1;
	}

	public void setAddFiles1(String addFiles1) {
		this.addFiles1 = addFiles1;
	}

	public String getDelFiles1() {
		return delFiles1;
	}

	public void setDelFiles1(String delFiles1) {
		this.delFiles1 = delFiles1;
	}

	public String getAddFiles2() {
		return addFiles2;
	}

	public void setAddFiles2(String addFiles2) {
		this.addFiles2 = addFiles2;
	}

	public String getDelFiles2() {
		return delFiles2;
	}

	public void setDelFiles2(String delFiles2) {
		this.delFiles2 = delFiles2;
	}

}
