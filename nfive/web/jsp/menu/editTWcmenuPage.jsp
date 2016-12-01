<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWcmenuFrom" name="editTWcmenuFrom" method="post"
				action="editTWcmenuAction.action">
				<div class="search-panel-bd">
					<input type="hidden" id="tWcmenuQueryBean.menu_id"
						name="tWcmenuQueryBean.menu_id" value="${tWcmenuQueryBean.menu_id}" />
					<table class="search-table">
						<tr>
							<th class="wd-20">
								<label>
									公众号ID
								</label>
							</th>
							<td>
								<input type="text" id="platform_id"
									name="tWcmenuQueryBean.platform_id" class="easyui-validatebox"
									value="${tWcmenuQueryBean.platform_id}" style="width: 200px;" readonly disabled
									/>
							</td>
							<th class="wd-20">
								<label>
									菜单代码
								</label>
							</th>
							<td>
								<input type="text" id="menu_code"
									name="tWcmenuQueryBean.menu_code" class="easyui-validatebox"
									value="${tWcmenuQueryBean.menu_code}" style="width: 200px;"
									disabled readonly />
							</td>
						</tr>
						<tr>
							<th class="wd-20">
								<label>
									菜单名称
								</label>
								<span class="stRed">*</span>
							</th>
							<td>
								<input type="text" id="menu_name"
									name="tWcmenuQueryBean.menu_name" class="easyui-validatebox"
									value="${tWcmenuQueryBean.menu_name}"
									data-options="required:true,validType:'length[1,10]'"
									style="width: 200px;" />

							</td>
							<th class="wd-20">
								<label>
									菜单描述
								</label>
							</th>
							<td>
								<input type="text" id="menu_desc"
									name="tWcmenuQueryBean.menu_desc" class="easyui-validatebox"
									data-options="validType:'length[1,100]'"
									value="${tWcmenuQueryBean.menu_desc}" style="width: 200px;" />
							</td>
						</tr>
						<tr>
							<th class="wd-20">
								<label>
									菜单权重
								</label>
								<span class="stRed">*</span>
							</th>
							<td>
								<input type="text" id="rela_sort"
									name="tWcmenuQueryBean.rela_sort" class="easyui-numberbox" min="1"
									max="10" required="true" value="${tWcmenuQueryBean.rela_sort}"
									missingMessage="权重值必须为1-10之间的数字" ,missingMessage="权重值不能为空"
									style="width: 200px;" />

							</td>
							<th class="wd-20">
								<label>
									菜单类型
								</label>
							</th>
							<td>
								<ldui:select items="${menuTypeItems}"
									id="action_type" name="tWcmenuQueryBean.action_type"
									class="easyui-combobox" style="width: 208px;" />
							</td>
						</tr>
						<tr>
					      	 	<th class="wd-10"><label>启用客服模式</label><span class="stRed">*</span></th>
							<td>
								<ldui:select items="${cust_srv_flag_SelList}" id="edit_cust_srv_flag_select" name="tWcmenuQueryBean.cust_srv_flag" class="easyui-combobox" style="width:200px;"
								required="true" validType="select['#edit_cust_srv_flag_select']" invalidMessage="启用标志不能为空" missingMessage="请填写启用标志" />
							</td>
						</tr>
					</table>
					<input type="hidden" name="tWcmenuQueryBean.action_url"        id="edit_action_url"/>       <!-- 链接地址 -->
					<input type="hidden" name="tWcmenuQueryBean.text_msg"          id="edit_text_msg">          <!-- 文本消息 -->
					<input type="hidden" name="tWcmenuQueryBean.material_id"       id="edit_material_id">       <!-- 图片消息/图文消息 -->
                    <input type="hidden" name="tWcmenuQueryBean.action_class"      id="edit_action_class">          <!-- 自定义消息接口 -->
					<%@ include file="../commonselect/commonTMaterialSelect.jsp"%>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	//页面加载绑定事件
	$(document).ready(function(){
	 $("#templet_flag_hid").attr("name","tWcmenuQueryBean.templet_flag");//是否动态模板
		$("#action_type").combobox({
			onSelect : keySelect
		});
		var  action_type = $("#action_type").val();	// 菜单类型
		var  action_url = '${tWcmenuQueryBean.action_url}';	// 链接地址
		var  text_msg = decodeURIComponent('${tWcmenuQueryBean.text_msg}',"UTF-8");	// 文本
		var  material_id = '${tWcmenuQueryBean.material_id}';	// 图片/图文ID
		var  material_name = '${material_name}';//素材名称
		var  material_url = '${material_url}';//素材路径
		var  material_tmpFlag = '${tWcmenuQueryBean.templet_flag}';//是否动态模板
        var  action_class = '${tWcmenuQueryBean.action_class}' //自定义业务类路径
		//如果是链接地址，素材路径放的是链接地址
		if(action_type=="501100000001"){
			material_url=action_url;
		}
		CommonSelect.initEdit(action_type,material_id,material_name,material_url,material_tmpFlag,text_msg,action_class);
	});
</script>