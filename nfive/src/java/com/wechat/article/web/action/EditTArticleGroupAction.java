package com.wechat.article.web.action;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.service.ITArticleGroupService;

 /**
 * 类功能:编辑图文组
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("editTArticleGroupAction")
@Scope("prototype")
public class EditTArticleGroupAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EditTArticleGroupAction.class);

    @Autowired
    private ITArticleGroupService tArticleGroupService;

    //入参
    //图文组
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

            logger.info("EditTArticleGroupAction.execute---start");

            //使用空的TArticleGroupDto来组装参数，保证只更新必要字段
            TArticleGroupDto tmpTArticleGroupDto = new TArticleGroupDto();
            tmpTArticleGroupDto.setArticle_group_id(tArticleGroupDto.getArticle_group_id());
            //tmpTArticleGroupDto.setAg_code(tArticleGroupDto.getAg_code());
            tmpTArticleGroupDto.setAg_name(tArticleGroupDto.getAg_name());
            tmpTArticleGroupDto.setAg_manager(tArticleGroupDto.getAg_manager());
            tmpTArticleGroupDto.setArticle_type(tArticleGroupDto.getArticle_type());
            tmpTArticleGroupDto.setEnable_agflag(tArticleGroupDto.getEnable_agflag());
            tmpTArticleGroupDto.setMax_items_count(tArticleGroupDto.getMax_items_count());
            tmpTArticleGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());

            //1.更新图文组信息
            tArticleGroupService.updatePK(tArticleGroupDto);

            //2.拼装成功消息
            String result = "{\"success\":true,\"data\":\"\"}";
            outJSOND(this.getResponse(), result);
            logger.info("EditTArticleGroupAction.execute---end");
        } catch (Exception ex) {
            ex.printStackTrace();
            //3.拼装异常消息
            String result = "{\"success\":false,\"message\":\"保存失败\"}";
            outJSOND(this.getResponse(), result);
            logger.info("EditTArticleGroupAction.execute---error");
        }

        return null;
    }

    public final TArticleGroupDto getTArticleGroupDto() {
        return tArticleGroupDto;
    }

    public final void setStudentDto(TArticleGroupDto tArticleGroupDto) {
        this.tArticleGroupDto = tArticleGroupDto;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
