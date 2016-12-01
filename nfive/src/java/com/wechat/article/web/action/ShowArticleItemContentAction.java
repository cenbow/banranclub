package com.wechat.article.web.action;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.article.model.dto.TArticleItemDto;
import com.wechat.article.service.ITArticleItemService;
import com.wechat.pfcfg.model.dto.TPubPlatformDto;
import com.wechat.pfcfg.service.ITPubPlatformService;

/**
 * 类功能:图文项正文内容输出
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-2</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Controller("showArticleItemContentAction")
@Scope("prototype")
public class ShowArticleItemContentAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ShowArticleItemContentAction.class);

    //入参
    //图文项ID
    private String article_item_id;
    private String platform_id;

    //出参
    private String pageContent;

    //spring自动装配
    @Autowired
    private ITArticleItemService tArticleItemService;
    @Autowired
    private ITPubPlatformService tPubPlatformService;

    //出错信息
    private String errorMessage;

    public String execute() throws Exception {
        if(StringUtils.isBlank(article_item_id)) {
            //无效的访问
            setErrorMessage("无效的访问，关键数据缺失");
            return ERROR;
        }

        logger.info("ShowArticleItemContentAction.execute---start");

        try {
            //图文项内容
            TArticleItemDto param = new TArticleItemDto();
            param.setArticle_item_id(article_item_id);
            TArticleItemDto articleItem = tArticleItemService.getRow(param);
            if (articleItem != null) {
                pageContent = articleItem.getContent();

                //添加样式
                if(StringUtils.isNotBlank(platform_id)) {
                    TPubPlatformDto paramTPubPlatformDto = new TPubPlatformDto();
                    paramTPubPlatformDto.setPlatform_id(platform_id);
                    TPubPlatformDto tPubPlatformDto = tPubPlatformService.getRow(paramTPubPlatformDto);
                    if (tPubPlatformDto !=null
                            && CodeStringConstant.CS_1000_TRUE.equalsIgnoreCase(tPubPlatformDto.getIs_display_title())
                            && StringUtils.isNotBlank(tPubPlatformDto.getTitle_style())) {
                        //标题
                        tPubPlatformDto.setTitle_style(tPubPlatformDto.getTitle_style().replaceAll("\\{title\\}", articleItem.getTitle()==null ? "" : articleItem.getTitle()));
                        //时间
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String publishedTime = simpleFormat.format(new Date());
                        if (tPubPlatformDto.getDisplay_time().equalsIgnoreCase("CREATED_DATE")) {
                            publishedTime = simpleFormat.format(articleItem.getCreated_date());
                        } else if (tPubPlatformDto.getDisplay_time().equalsIgnoreCase("CREATED_DATE")) {
                            publishedTime = simpleFormat.format(articleItem.getUpdated_date());
                        }
                        tPubPlatformDto.setTitle_style(tPubPlatformDto.getTitle_style().replaceAll("\\{time\\}", publishedTime));
                        //作者
                        tPubPlatformDto.setTitle_style(tPubPlatformDto.getTitle_style().replaceAll("\\{author\\}", articleItem.getAuthor()==null ? "" : articleItem.getAuthor()));
                        //平台名称
                        tPubPlatformDto.setTitle_style(tPubPlatformDto.getTitle_style().replaceAll("\\{platform_name\\}", tPubPlatformDto.getPlatform_name()==null ? "" : tPubPlatformDto.getPlatform_name()));
                        //正文内容
                        tPubPlatformDto.setTitle_style(tPubPlatformDto.getTitle_style().replaceAll("\\{content\\}", articleItem.getContent()==null ? "" : articleItem.getContent()));

                        pageContent = tPubPlatformDto.getTitle_style();
                    }
                }
            }
            //if (StringUtils.isNotEmpty(pageContent)) {
            //    pageContent = URLEncoder.encode(pageContent, "UTF-8").replace("+", "%20");
            //}

            logger.info("ShowArticleItemContentAction.execute---end");
            return SUCCESS;
        } catch (Exception ex) {
            ex.printStackTrace();
            setErrorMessage(ex.getMessage());
            logger.info("ShowArticleItemContentAction.execute---error");
        }

        return ERROR;
    }

    public void setArticle_item_id(String articleItemId) {
        article_item_id = articleItemId;
    }

    public void setPlatform_id(String platformId) {
        platform_id = platformId;
    }

    public String getPageContent() {
        return pageContent;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
