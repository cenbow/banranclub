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
 * 类功能:编辑图文项
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTArticleItemAction")
@Scope("prototype")
public class EditTArticleItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EditTArticleItemAction.class);

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
            if(StringUtils.isBlank(tArticleItemDto.getArticle_item_id()) || StringUtils.isBlank(tArticleRelaDto.getRela_id())) {
                //无效的访问
                setErrorMessage("无效的访问");
                return ERROR;
            }

            logger.info("EditTArticleItemAction.execute---start");

            JSONObject jsonResult = new JSONObject();
            if (StringUtils.isNotBlank(uploadPicFileName)) {
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
            }

            //使用空对象转存数据，保存只更新必要的字段
            //1.图文项
            TArticleItemDto tmpTArticleItemDto = new TArticleItemDto();
            tmpTArticleItemDto.setArticle_item_id(tArticleItemDto.getArticle_item_id());
            tmpTArticleItemDto.setTitle(tArticleItemDto.getTitle());
            tmpTArticleItemDto.setAuthor(tArticleItemDto.getAuthor());
            tmpTArticleItemDto.setArticle_type(tArticleItemDto.getArticle_type());
            tmpTArticleItemDto.setArticle_state(tArticleItemDto.getArticle_state());
            tmpTArticleItemDto.setSummary(tArticleItemDto.getSummary());
            tmpTArticleItemDto.setPic_org(tArticleItemDto.getPic_org());
            tmpTArticleItemDto.setPic_cover(tArticleItemDto.getPic_cover());
            tmpTArticleItemDto.setPic_thumbnail(tArticleItemDto.getPic_thumbnail());
            tmpTArticleItemDto.setOrg_flag(tArticleItemDto.getOrg_flag());
            tmpTArticleItemDto.setOrg_url(tArticleItemDto.getOrg_url());
            tmpTArticleItemDto.setContent(tArticleItemDto.getContent());
            tmpTArticleItemDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

            //2.图文关系
            TArticleRelaDto tmpTArticleRelaDto = new TArticleRelaDto();
            tmpTArticleRelaDto.setRela_id(tArticleRelaDto.getRela_id());
            tmpTArticleRelaDto.setRela_sort(tArticleRelaDto.getRela_sort());
            tmpTArticleRelaDto.setPub_startdate(tArticleRelaDto.getPub_startdate());
            tmpTArticleRelaDto.setPub_enddate(tArticleRelaDto.getPub_enddate());
            tmpTArticleRelaDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

            if (StringUtils.isNotBlank(uploadPicFileName)) {
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
                tArticleItemService.updatePK(tmpTArticleItemDto, tmpTArticleRelaDto, orgTAttachMaterialDto, coverTAttachMaterialDto, thumbnailTAttachMaterialDto);
            } else {
                //保存（不更新图片）
                tArticleItemService.updatePK(tmpTArticleItemDto, tmpTArticleRelaDto);
            }

            jsonResult.put("success", true);
            jsonResult.put("data", "");
            outJSOND(getResponse(), jsonResult.toString());

            logger.info("EditTArticleItemAction.execute---end");
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();

            JSONObject jsonResult = new JSONObject();
            jsonResult.put("success", false);
            jsonResult.put("message", "保存失败");
            outJSOND(getResponse(), jsonResult.toString());
            logger.info("EditTArticleItemAction.execute---error");
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
