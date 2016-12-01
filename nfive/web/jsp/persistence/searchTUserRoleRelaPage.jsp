<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String pathUrl = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_userRole_list">
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>添加角色</a></li>
			<li><a href="javascript:;" id="remove_resource_role_btn"><i class="icon-remove"></i>移除角色</a></li>
		</ul>
	</div>
	<article id="content" class="content" style="width:98%;">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				
				<div class="search-panel-content">
					<form id="tConfig_configForm" name="tConfig_configForm" method="post" >
						
						<input type="hidden" id="user_id" name="tUserRoleRelaDto.user_id" style="width:200px;"  />
						
						<div class="search-panel-bd">
							<table class="search-table">
								 <tr>
										<th class="wd-20"><label>员工CD</label></th>
										<td>
											<input type="text" id="user_cd" name="user_cd" style="width:200px;" readonly="readonly" />
										</td>
										<th class="wd-20"><label>员工名称</label></th>
										<td>
											<input type="text" id="user_name" name="user_name" style="width:200px;" readonly="readonly" />
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
					<h2 class="panel-title">已配置角色</h2>
				</div>
			<div class="result-content">
				<table id="dg_tCodestring" style="width:auto;height:250px">
					<thead>
						<tr>
							<th data-options="field:'ROLE_ID',hidden:true">角色ID</th>
							<th data-options="field:'chk',checkbox:true"></th>
							<th data-options="field:'ROLE_NAME',width:120,sortable:'true',align:'center'">角色名称</th>
							<th data-options="field:'ROLE_GROUP',width:120,sortable:'true',align:'center'">标识组</th>
							<th data-options="field:'ROLE_DESC',width:140,sortable:'true',align:'center'">备注</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'CREATED_DATE',width:150,sortable:'true',align:'center'">创建时间</th>
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
	    $('div#div_userRole_list #dg_tCodestring').datagrid('options')
			.queryParams = {
				'tUserRoleRelaQueryBean.user_id':$("#div_userRole_list #user_id").val()
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_userRole_list #search_btn').click(function(){
		tConfig_list.buildQueryParams();
		$('div#div_userRole_list #dg_tCodestring').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_userRole_list #clear_btn').click(function(){
		//清空查询条件
			$("#div_userRole_list input[name='resource_code']").val("");
			$("#div_userRole_list input[name='resource_name']").val("");
			tConfig_list.buildQueryParams();
			$('div#div_userRole_list #dg_tCodestring').datagrid('load');
	});
	
	
	//新增角色
	$('div#div_userRole_list #add_btn').click(function(){
		$('<div id="dialog_roleSel"></div>').dialog({
		    title: '角色选择',
		    width: 800,
		    height: 515,
		    href: 'selectTRoleAction.action?tRoleQueryBean.user_id='+$('div#div_userRole_list #user_id').val(),
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
		    		var checked = $("#dg_select_tRole").datagrid("getChecked");
		    		if(checked.length == 0)
			   		{
			   			$.messager.alert("提示信息","请选择您要添加的角色","info");
			   			return;
			   		}
		    		var role_group = [];
		    		for(var i = 0; i<checked.length;i++){
		    			role_group.push(checked[i].ROLE_ID);
		    		}
		    		$.post("addTUserRoleRelaAction.action",
		    		  {
		    		  	"tUserRoleRelaDto.user_id": $('div#div_userRole_list #user_id').val(),
		    			"tUserRoleRelaDto.role_id": role_group.join(",")	   
					  },
					  function(data){
						$('#dialog_roleSel').dialog('close');
						tConfig_list.buildQueryParams();
						$('div#div_userRole_list #dg_tCodestring').datagrid('reload');
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
	$('div#div_userRole_list #remove_resource_role_btn').click(function(){
   		var checked = $('div#div_userRole_list #dg_tCodestring').datagrid("getChecked");
   		if(checked.length == 0)
   		{
   			$.messager.alert("提示信息","请选择您要移除的角色","info");
   			return;
   		}
   		var role_group = [];
   		for(var i = 0; i<checked.length;i++){
   			role_group.push(checked[i].ROLE_ID);
   		}
   		$.post("delTUserRoleRelaAction.action",
   		  {
   		  	"tUserRoleRelaDto.user_id": $('div#div_userRole_list #user_id').val(),
		    "tUserRoleRelaDto.role_id": role_group.join(",")	      
		  },
		  function(data){
			$('#dialog_roleSel').dialog('close');
			tConfig_list.buildQueryParams();
			$('div#div_userRole_list #dg_tCodestring').datagrid('reload');
		});
	});
	
	//datagrid初始化
	$('div#div_userRole_list #dg_tCodestring').datagrid({
		sortName:'CREATED_DATE',
		sortOrder:'desc',
		pagination:'true',
		url:'<%=pathUrl%>/searchTUserRoleRelaAction!getListData.action',
		method:'post',
		queryParams: {
			'tUserRoleRelaQueryBean.user_id':$("#div_userRole_list #user_id").val()
		}
	});
};

</script>