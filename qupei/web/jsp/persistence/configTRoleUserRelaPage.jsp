<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String pathUrl = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_roleUser_list">
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>添加员工</a></li>
			<li><a href="javascript:;" id="remove_resource_role_btn"><i class="icon-remove"></i>移除员工</a></li>
		</ul>
	</div>
	<article id="content" class="content" style="width:98%;">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				
				<div class="search-panel-content">
					<form id="tConfig_configForm" name="tConfig_configForm" method="post" >
						<input type="hidden" id="role_id" name="tUserRoleRelaDto.role_id" style="width:200px;"  />
						<div class="search-panel-bd">
							<table class="search-table">
								 <tr>
										<th class="wd-20"><label>角色名称</label></th>
										<td>
											<input type="text" id="role_name" name="role_name" style="width:200px;" readonly="readonly" />
										</td>
								  </tr>
							</table>
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">已配置员工</h2>
				</div>
			<div class="result-content">
				<table id="dg_tUserRolestring" style="width:auto;height:250px">
					<thead>
						<tr>
							<th data-options="field:'USER_ID',hidden:true">员工ID</th>
							<th data-options="field:'chk',checkbox:true"></th>
							<th data-options="field:'U_USER_CD',width:120,sortable:'true',align:'center'">员工编号</th>
							<th data-options="field:'U_USER_NAME',width:120,sortable:'true',align:'center'">员工名</th>
							<th data-options="field:'U_MOBILE',width:140,sortable:'true',align:'center'">手机号码</th>
							<th data-options="field:'U_EMAIL',width:140,sortable:'true',align:'center'">电子邮箱</th>
							<th data-options="field:'CREATED_DATE',width:150,sortable:'true',align:'center'">创建时间</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tConfig_list ={};

tConfig_list.trigger = function(){
	//定义构造查询
	tConfig_list.buildQueryParams=function(){
	    $('div#div_roleUser_list #dg_tUserRolestring').datagrid('options')
			.queryParams = {
				'tUserRoleRelaQueryBean.role_id':$("#div_roleUser_list input[name='tUserRoleRelaDto.role_id']").val()
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_roleUser_list #search_btn').click(function(){
		tConfig_list.buildQueryParams();
		$('div#div_roleUser_list #dg_tUserRolestring').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_roleUser_list #clear_btn').click(function(){
		//清空查询条件
			$("#div_roleUser_list input[name='resource_code']").val("");
			$("#div_roleUser_list input[name='resource_name']").val("");
			tConfig_list.buildQueryParams();
			$('div#div_roleUser_list #dg_tUserRolestring').datagrid('load');
	});
	
	
	//新增角色
	$('div#div_roleUser_list #add_btn').click(function(){
		$('<div id="dialog_roleSel"></div>').dialog({
		    title: '员工选择',
		    width: 800,
		    height: 515,
		    href: 'selectTEmpUserAction.action?tEmpUserQueryBean.role_id='+$('div#div_roleUser_list #role_id').val(),
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	iconCls: 'icon-ok',
		    	text: "确定选择",
		    	handler: function(e){
		    		var add_form = $('#addTCodestringFrom');
		    		var checked = $("#dg_select_tUser").datagrid("getChecked");
		    		if(checked.length == 0)
			   		{
			   			$.messager.alert("提示信息","请选择您要添加的员工","info");
			   			return;
			   		}
		    		var user_group = [];
		    		for(var i = 0; i<checked.length;i++){
		    			user_group.push(checked[i].USER_ID);
		    		}
		    		$.post("addTUserRoleRelaAction.action",
		    		  {
		    		  	"tUserRoleRelaDto.user_id": user_group.join(","),	
		    			"tUserRoleRelaDto.role_id": $('div#div_roleUser_list #role_id').val()
					  },
					  function(data){
						$('#dialog_roleSel').dialog('close');
						tConfig_list.buildQueryParams();
						$('div#div_roleUser_list #dg_tUserRolestring').datagrid('reload');
					});
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
	//移除角色
	$('div#div_roleUser_list #remove_resource_role_btn').click(function(){
   		var checked = $('div#div_roleUser_list #dg_tUserRolestring').datagrid("getChecked");
   		if(checked.length == 0)
   		{
   			$.messager.alert("提示信息","请选择您要移除的员工","info");
   			return;
   		}
   		var user_group = [];
   		for(var i = 0; i<checked.length;i++){
   			user_group.push(checked[i].USER_ID);
   		}
   		$.post("delTUserRoleRelaAction.action",
   		  {
   		  	"tUserRoleRelaDto.user_id": user_group.join(","),	
		    "tUserRoleRelaDto.role_id": $('div#div_roleUser_list #role_id').val()
		  },
		  function(data){
			tConfig_list.buildQueryParams();
			$('div#div_roleUser_list #dg_tUserRolestring').datagrid('reload');
		});
	});
	
	//datagrid初始化
	$('div#div_roleUser_list #dg_tUserRolestring').datagrid({
		sortName:'CREATED_DATE',
		sortOrder:'desc',
		pagination:'true',
		url:'<%=pathUrl%>/searchTUserRoleRelaAction!getListData.action',
		method:'post',
		queryParams: {
			'tUserRoleRelaQueryBean.role_id':$("#div_roleUser_list input[name='tUserRoleRelaDto.role_id']").val()
		}
	});
};

</script>