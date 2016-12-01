package com.platform.persistence.web.action;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hercules.common.tool.json.JsonDateFormatUtil;
import com.platform.common.tools.opensymphony.web.action.BaseAction;
import com.platform.persistence.model.TResSelfRelaQueryBean;
import com.platform.persistence.service.ITResSelfRelaService;
import com.platform.persistence.util.resource.tree.Tree;
 /**
 * 类功能:资源树展示
 * <p>创建者:zhangzhiqiang</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
 
@Controller("searchTResSelfRelaAction")
@Scope("prototype")
public class SearchTResSelfRelaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITResSelfRelaService tResSelfRelaService;
	
	private TResSelfRelaQueryBean tResSelfRelaQueryBean = new TResSelfRelaQueryBean();
	
	private String id;
	
	public String execute() throws Exception {
		try {
			return SUCCESS;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ERROR;
	}
	
	/***
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getListData() {

		try {
			if (tResSelfRelaQueryBean == null) {
				tResSelfRelaQueryBean = new TResSelfRelaQueryBean();
			}
			tResSelfRelaQueryBean.setParent_res_id(id);
			Tree tree = this.tResSelfRelaService.queryTResSelfRelaList(tResSelfRelaQueryBean);
			// 3.组合输出列表查询所需数据
			JsonConfig config = new JsonConfig();
			JsonDateFormatUtil.formatDateForJsonConfig_type1(config);
			JSONArray json_rows = null;
			//判断是否是局部刷新
			if(id == null || id.trim().length() < 0)
			{
				json_rows = JSONArray.fromObject(tree, config);
			}else
			{
				json_rows = JSONArray.fromObject(tree.getChildren(), config);
			}
			outJSOND(this.getResponse(), json_rows.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public final TResSelfRelaQueryBean getTResSelfRelaQueryBean() {
		return tResSelfRelaQueryBean;
	}

	public final void setTResSelfRelaQueryBean(TResSelfRelaQueryBean tResSelfRelaQueryBean) {
		this.tResSelfRelaQueryBean = tResSelfRelaQueryBean;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
