<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTWcmenuFrom" name="addTWcmenuFrom" method="post"
				action="addTWcmenuAction.action">
				<input type="hidden" id="tWcmenuQueryBean.parent_id"
					name="tWcmenuQueryBean.parent_id" value="${parent_id}" />
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-20">
								<label>
									上级菜单
								</label>
							</th>
							<td>
								<input type="text" id="parent_name"
									name="tWcmenuQueryBean.parent_name" class="easyui-validatebox"
									value="" style="width: 200px;" disabled readonly
									data-options="required:true" />
							</td>
							<th class="wd-20">
								<label>
									上级菜单ID
								</label>
							</th>
							<td>
								<input type="text" id="parent_id"
									name="tWcmenuQueryBean.parent_id" class="easyui-validatebox"
									value="${parent_id}" style="width: 200px;" readonly
									disabled data-options="required:true" />
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
									style="width: 200px;" />
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
									name="tWcmenuQueryBean.rela_sort" class="easyui-numberbox"
									min="1" max="10" required="true"
									invalidMessage="权重值必须为1-10之间的数字" ,missingMessage="权重值不能为空"
									style="width: 200px;" />

							</td>
							<th class="wd-20">
								<label>
									菜单类型
								</label>
							</th>
							<td>
								<ldui:select items="${menuTypeItems}" id="action_type"
									name="tWcmenuQueryBean.action_type" class="easyui-combobox"
									style="width: 208px;" />
							</td>
						</tr>
						<tr>
					      	<th class="wd-10"><label>启用客服模式</label><span class="stRed">*</span></th>
							<td>
								<ldui:select items="${cust_srv_flag_SelList}" id="add_cust_srv_flag_select" name="tWcmenuQueryBean.cust_srv_flag" class="easyui-combobox" style="width:200px;"
								required="true" validType="select['#add_cust_srv_flag_select']" invalidMessage="启用标志不能为空" missingMessage="请填写启用标志" />
							</td>
						</tr>
					</table>
					<input type="hidden" name="tWcmenuQueryBean.action_url"        id="add_action_url"/>       <!-- 链接地址 -->
					<input type="hidden" name="tWcmenuQueryBean.text_msg"          id="add_text_msg">          <!-- 文本消息 -->
					<input type="hidden" name="tWcmenuQueryBean.material_id"       id="add_material_id">       <!-- 图片消息 /图文消息-->
                    <input type="hidden" name="tWcmenuQueryBean.action_class"       id="add_action_class">       <!-- 图片消息 /图文消息-->
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
		})
	});

	var action_type = $("#action_type").val();	// 菜单类型
	// 调用共通
	changePage(action_type);
</script>
