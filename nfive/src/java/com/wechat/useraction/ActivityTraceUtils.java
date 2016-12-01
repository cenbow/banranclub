package com.wechat.useraction;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wechat.useraction.model.dto.TUserActionDto;


/**
 * 类功能:用户行为跟踪
 * <p>创建者: liyi.wang</p>
 * <p>创建时间:2014-9-29</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 *
 */

public class ActivityTraceUtils {

    public static final String msgFormat = "[%s$%s$%s$%s$%s$%s$%s$%s]";

    /**
     * 打印指定log
     *
     * @param userAction
     */
    public static void trace(TUserActionDto userAction) {
        Logger traceLogger = Logger.getLogger("activityTrace");

        // 替换KeyWord中"$"为"*"，避免解析时错误
        String keyword = userAction.getKeyword() != null ? userAction.getKeyword().replace("$", "*").replace(System.getProperty("line.separator"), " ") : "";

        if (traceLogger.isInfoEnabled()) {
            String msg = String.format(msgFormat,
                    StringUtils.trimToEmpty(userAction.getPlatform_id()),           // 公众平台号ID
                    userAction.getAction_type(),                                    // 操作类型[CS:5064]菜单，图文项，关键字
                    StringUtils.trimToEmpty(userAction.getOpen_id()),               // OPEN_ID
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), // 操作时间
                    StringUtils.trimToEmpty(userAction.getMenu_id()),               // 菜单ID
                    StringUtils.trimToEmpty(userAction.getUrl()),                   // URL
                    keyword,                                                        // 关键字
                    StringUtils.trimToEmpty(userAction.getAction_value())           // 操作对应值
                    );
            traceLogger.info(msg);
        }
    }
}
