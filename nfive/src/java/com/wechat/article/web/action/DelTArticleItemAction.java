package com.wechat.article.web.action;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.TArticleGroupQueryBean;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.model.dto.TArticleRelaDto;
import com.wechat.article.service.ITArticleItemService;

 /**
 * 类功能:删除图文项、图文关系
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-8-14</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("delTArticleItemAction")
@Scope("prototype")
public class DelTArticleItemAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DelTArticleItemAction.class);

    @Autowired
    private ITArticleItemService tArticleItemService;

    //入参
    //图文组查询条件
    private TArticleGroupQueryBean tArticleGroupQueryBean = new TArticleGroupQueryBean();
    //图文项
    private TArticleItemDto tArticleItemDto= new TArticleItemDto();

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {

        try {
            if(StringUtils.isBlank(tArticleItemDto.getArticle_item_id())) {
                //无效的访问
                setErrorMessage("无效的访问");
                return ERROR;
            }

            logger.info("DelTArticleItemAction.execute---start");

            //1.删除图文项对象
            //2.删除图文项ID关联的图文关系
            TArticleRelaDto tArticleRelaDto = new TArticleRelaDto();
            tArticleRelaDto.setArticle_item_id(tArticleItemDto.getArticle_item_id());
            tArticleItemService.delete(tArticleItemDto, tArticleRelaDto);

            logger.info("DelTArticleItemAction.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("DelTArticleItemAction.execute---error");
        }
        return ERROR;
    }

    public TArticleGroupQueryBean getTArticleGroupQueryBean() {
        return tArticleGroupQueryBean;
    }

    public void setTArticleGroupQueryBean(TArticleGroupQueryBean tArticleGroupQueryBean) {
        this.tArticleGroupQueryBean = tArticleGroupQueryBean;
    }

    public TArticleItemDto getTArticleItemDto() {
        return tArticleItemDto;
    }

    public void setTArticleItemDto(TArticleItemDto tArticleItemDto) {
        this.tArticleItemDto = tArticleItemDto;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
