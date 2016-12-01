package com.wechat.article.web.action;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.checkmaterial.CheckMaterial;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.service.ITArticleGroupService;

 /**
 * 类功能:删除图文组、图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("delTArticleGroupAction")
@Scope("prototype")
public class DelTArticleGroupAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DelTArticleGroupAction.class);

    @Autowired
    private ITArticleGroupService tArticleGroupService;

    private TArticleGroupDto tArticleGroupDto = new TArticleGroupDto();

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleGroupDto.getArticle_group_id())) {
                //无效的访问
                setErrorMessage("无效的访问");
                return ERROR;
            }

            logger.info("DelTArticleGroupAction.execute---start");

            //1.删除检查
            String checkResult = CheckMaterial.checkMaterialExist(tArticleGroupDto.getArticle_group_id());
            if (!StringUtils.isBlank(checkResult)) {
                String result = "{\"success\":false,\"message\":\"" + checkResult + "\"}";
                outJSOND(this.getResponse(), result);
                return null;
            }

            //2.删除图文组、图文关系
            TArticleRelaDto tArticleRelaDto = new TArticleRelaDto();
            tArticleRelaDto.setArticle_group_id(tArticleGroupDto.getArticle_group_id());
            tArticleGroupService.delete(tArticleGroupDto, tArticleRelaDto);

            //3.拼装成功消息
            String result = "{\"success\":true,\"data\":\"\"}";
            outJSOND(this.getResponse(), result);
            logger.info("DelTArticleGroupAction.execute---end");
        } catch (Exception ex) {
            ex.printStackTrace();

            //4.拼装异常消息
            String result = "{\"success\":false,\"message\":\"删除失败\"}";
            outJSOND(this.getResponse(), result);
            logger.info("DelTArticleGroupAction.execute---error");
        }
        return null;
    }

    public TArticleGroupDto getTArticleGroupDto() {
        return tArticleGroupDto;
    }

    public void setTArticleGroupDto(TArticleGroupDto tArticleGroupDto) {
        this.tArticleGroupDto = tArticleGroupDto;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
