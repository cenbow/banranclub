package com.wechat.article.web.action;

import java.io.File;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.service.ITArticleItemService;

 /**
 * 类功能:引用图文项
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("refTArticleItemAction")
@Scope("prototype")
public class RefTArticleItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(RefTArticleItemAction.class);

    @Autowired
    private ITArticleItemService tArticleItemService;

    //入参
    //图文项
    private TArticleItemDto tArticleItemDto= new TArticleItemDto();
    //图文关系
    private TArticleRelaDto tArticleRelaDto= new TArticleRelaDto();

    //图片文件
    private File uploadPic;
    private String uploadPicFileName;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleItemDto.getArticle_item_id()) || StringUtils.isBlank(tArticleRelaDto.getArticle_group_id())) {
                //无效的访问
                setErrorMessage("无效的访问，关键数据缺失");
                return ERROR;
            }

            logger.info("RefTArticleItemAction.execute---start");

            JSONObject jsonResult = new JSONObject();

            //插入图文关系
            tArticleRelaDto.setArticle_item_id(tArticleItemDto.getArticle_item_id());
            tArticleRelaDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            tArticleRelaDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            tArticleItemService.refArticleItem(tArticleRelaDto);

            jsonResult.put("success", true);
            jsonResult.put("data", "");
            outJSOND(getResponse(), jsonResult.toString());

            logger.info("RefTArticleItemAction.execute---end");
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject jsonResult = new JSONObject();
            jsonResult.put("success", false);
            jsonResult.put("message", "保存失败");
            outJSOND(getResponse(), jsonResult.toString());

            logger.info("RefTArticleItemAction.execute---error");
        }

        return null;
    }

    public final TArticleItemDto getTArticleItemDto() {
        return tArticleItemDto;
    }

    public final void setStudentDto(TArticleItemDto tArticleItemDto) {
        this.tArticleItemDto = tArticleItemDto;
    }

    public TArticleRelaDto getTArticleRelaDto() {
        return tArticleRelaDto;
    }

    public void setTArticleRelaDto(TArticleRelaDto tArticleRelaDto) {
        this.tArticleRelaDto = tArticleRelaDto;
    }

    public File getUploadPic() {
        return uploadPic;
    }

    public void setUploadPic(File uploadPic) {
        this.uploadPic = uploadPic;
    }

    public String getUploadPicFileName() {
        return uploadPicFileName;
    }

    public void setUploadPicFileName(String uploadPicFileName) {
        this.uploadPicFileName = uploadPicFileName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
