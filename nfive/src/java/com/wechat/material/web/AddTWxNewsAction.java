package com.wechat.material.web;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.material.datasync.NewsSyncUtil;
import com.wechat.material.model.TWxNewsDto;
import com.wechat.material.service.ITWxNewsService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能:自动代码生成模板新增   action 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Controller("addTWxNewsAction")
@Scope("prototype")
public class AddTWxNewsAction extends BaseAction {

    @Autowired
    private ITWxNewsService wxNewsService;

    private TWxNewsDto tWxNewsDto = new TWxNewsDto();

    public String execute() throws Exception {
        try {
           if(NewsSyncUtil.newscount==-1&&NewsSyncUtil.syncprogress==-1){
               new Thread(new NewsSyncUtil()).start();
           }
            Map map = new HashMap();
            map.put("newscount", NewsSyncUtil.newscount);
            map.put("syncprogress",NewsSyncUtil.syncprogress);
            JsonConfig config = new JsonConfig();
            JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
            JSONObject json = JSONObject.fromObject(map, config);
            String out =  json.toString();
            outJSOND(this.getResponse(), out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    //获取同步进度
    public String syncProgress() throws Exception {
        try {

            Map map  = new HashMap();
            map.put("newscount", NewsSyncUtil.newscount);
            map.put("syncprogress",NewsSyncUtil.syncprogress);

            // 2.组合输出列表查询所需数据
            // JsonConfig 用于解析转换的设置
            JsonConfig config = new JsonConfig();
            JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
            JSONObject json = JSONObject.fromObject(map, config);
            String out =  json.toString();
            outJSOND(this.getResponse(), out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public final TWxNewsDto getTWxNewsDto() {
        return tWxNewsDto;
    }

    public final void setTWxNewsDto(TWxNewsDto tWxNewsDto) {
        this.tWxNewsDto = tWxNewsDto;
    }

}
