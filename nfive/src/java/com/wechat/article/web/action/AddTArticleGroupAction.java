package com.wechat.article.web.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.common.tools.permission.LoginUserInfoUtil;
import com.wechat.article.model.dto.TArticleGroupDto;
import com.wechat.article.service.ITArticleGroupService;

 /**
 * 类功能:新增图文组
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
@Controller("addTArticleGroupAction")
@Scope("prototype")
public class AddTArticleGroupAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(AddTArticleGroupAction.class);

    @Autowired
    private ITArticleGroupService tArticleGroupService;

    //图文组项目信息传入
    private TArticleGroupDto tArticleGroupDto= new TArticleGroupDto();

    public String execute() throws Exception {

        try {
            logger.info("AddTArticleGroupAction.execute---start");

            //保存
            tArticleGroupDto.setCreated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            tArticleGroupDto.setUpdated_user_cd(LoginUserInfoUtil.getLoginUserCD());
            tArticleGroupService.save(tArticleGroupDto);

            //拼装成功消息
            String result = "{\"success\":true,\"data\":\"" + tArticleGroupDto.getArticle_group_id() + "\"}";
            outJSOND(this.getResponse(), result);
            logger.info("AddTArticleGroupAction.execute---end");
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            //拼装异常消息
            String result = "{\"success\":false,\"message\":\"保存失败\"}";
            outJSOND(this.getResponse(), result);
            logger.info("AddTArticleGroupAction.execute---error");
        }

        return null;
    }

    public final TArticleGroupDto getTArticleGroupDto() {
        return tArticleGroupDto;
    }

    public final void setStudentDto(TArticleGroupDto tArticleGroupDto) {
        this.tArticleGroupDto = tArticleGroupDto;
    }

}
