package com.wechat.article.web.action;

import java.io.File;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.fileupload.FileUploadUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.material.model.dto.TAttachMaterialDto;

 /**
 * 类功能:新增图文项
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@SuppressWarnings("serial")
@Controller("addTArticleItemAction")
@Scope("prototype")
public class AddTArticleItemAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(AddTArticleItemAction.class);

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
            if(StringUtils.isBlank(tArticleRelaDto.getArticle_group_id()) || StringUtils.isBlank(uploadPicFileName)
                    || uploadPic==null) {

                setErrorMessage("无效的访问");
                return ERROR;
            }

            logger.info("AddTArticleItemAction.execute---start");

            JSONObject jsonResult = new JSONObject();
            //检查原文件（1M，jpg）
            int seperatorIndex = uploadPicFileName.lastIndexOf(".");
            if (seperatorIndex == -1) {
                jsonResult.put("success", false);
                jsonResult.put("message", "文件只支持JPG格式");
                outJSOND(getResponse(), jsonResult.toString());
                return null;
            }
            if(seperatorIndex != -1){
                String suffix = uploadPicFileName.substring(seperatorIndex + 1);
                if (!suffix.equalsIgnoreCase("jpg")) {
                    jsonResult.put("success", false);
                    jsonResult.put("message", "文件只支持JPG格式");
                    outJSOND(getResponse(), jsonResult.toString());
                    return null;
                }
            }
            if (uploadPic.length() > 1024*1024) {
                jsonResult.put("success", false);
                jsonResult.put("message", "图片大小不能超过1M");
                outJSOND(getResponse(), jsonResult.toString());
                return null;
            }

            //1 图文项
            tArticleItemDto.setContent(tArticleItemDto.getContent());
            tArticleItemDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            tArticleItemDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

            //2 图文关系
            tArticleRelaDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            tArticleRelaDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

            //3 图片文件
            //3.1 原图
            TAttachMaterialDto orgTAttachMaterialDto = FileUploadUtil.bulidAttachMaterialDtoByTmpFile(uploadPic, uploadPicFileName, CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
            orgTAttachMaterialDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            orgTAttachMaterialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            //3.2 封面图（900*500）
            TAttachMaterialDto coverTAttachMaterialDto = FileUploadUtil.bulidAttachMaterialDtoByTmpFile(FileUploadUtil.zipImageFile(uploadPic, 900, 500), uploadPicFileName, CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
            coverTAttachMaterialDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            coverTAttachMaterialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            //3.3 缩略图（200*200）
            TAttachMaterialDto thumbnailTAttachMaterialDto = FileUploadUtil.bulidAttachMaterialDtoByTmpFile(FileUploadUtil.zipImageFile(uploadPic, 200, 200), uploadPicFileName, CodeStringConstant.CS_5012_MATERIAL_TYPE_IMG);
            thumbnailTAttachMaterialDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            thumbnailTAttachMaterialDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

            //保存
            tArticleItemService.save(tArticleItemDto, tArticleRelaDto, orgTAttachMaterialDto, coverTAttachMaterialDto, thumbnailTAttachMaterialDto);

            jsonResult.put("success", true);
            jsonResult.put("data", "");
            outJSOND(getResponse(), jsonResult.toString());

            logger.info("AddTArticleItemAction.execute---end");

        } catch (Exception ex) {
            ex.printStackTrace();

            JSONObject jsonResult = new JSONObject();
            jsonResult.put("success", false);
            jsonResult.put("message", "保存失败");
            outJSOND(getResponse(), jsonResult.toString());

            logger.info("AddTArticleItemAction.execute---error");
        }

        return null;
    }

    public TArticleItemDto getTArticleItemDto() {
        return tArticleItemDto;
    }

    public void setTArticleItemDto(TArticleItemDto tArticleItemDto) {
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
