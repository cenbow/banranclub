package com.wechat.fans.web;

import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.wechat.fans.datasync.FansSyncUtil;
import com.wechat.fans.service.ITWxFansService;
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
@Controller("addTWxFansAction")
@Scope("prototype")
public class AddTWxFansAction extends BaseAction {

	@Autowired
	private ITWxFansService wxFansService;


	public String execute() throws Exception {
		try {
            //当前未同步时才允许同步
            if(FansSyncUtil.fanscount==-1&&FansSyncUtil.syncprogress==-1){
                //粉丝同步
                new Thread(new FansSyncUtil()).start();
            }

            Map map = new HashMap();
            map.put("fanscount",FansSyncUtil.fanscount);
            map.put("syncprogress",FansSyncUtil.syncprogress);
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
            map.put("fanscount",FansSyncUtil.fanscount);
            map.put("syncprogress",FansSyncUtil.syncprogress);

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
}
