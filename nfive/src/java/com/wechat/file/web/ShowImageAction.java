package com.wechat.file.web;
import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.service.ITAttachMaterialService;

@Controller("showImageAction")
@Scope("prototype")
public class ShowImageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
    private static  Logger logger  = Logger.getLogger(ShowImageAction.class);	
    //in
    private String imgid;
 
    @Autowired
    private ITAttachMaterialService tAttachMaterialService;
   
	public String execute() throws Exception {
	
			String fileName= tAttachMaterialService.getPubFileVisitPathByFileId(imgid);
			File file = new File(fileName);
			if (file.exists()) {
				this.getResponse().resetBuffer();
				this.getResponse().reset();
				this.getResponse().setContentType("image/jpeg");
				this.getResponse().addHeader("Content-Length",
						String.valueOf(file.length()));
				try {
					byte[] bytes = fileName.getBytes();
					String encoded = new String(bytes, "ISO-8859-1");
					this.getResponse().addHeader("Content-Disposition",
							"attachment; filename=" + encoded);
					FileInputStream fis = new FileInputStream(file);
					FileCopyUtils.copy(fis, this.getResponse().getOutputStream());
					// this.getResponse().getOutputStream ().flush ();
					// this.getResponse().getOutputStream ().close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			
		
		return null;
	}

	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	
	
}
