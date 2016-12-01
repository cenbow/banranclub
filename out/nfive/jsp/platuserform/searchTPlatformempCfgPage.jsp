<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String pathUrl = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
			
		//	String pathUrl1 =request.getAttribute("USER_ID_LIST").toString(); 
		//	System.out.println("pathUrl1"+pathUrl1);
%>
<!--内容-->
<div id="div_platForm_list">
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>绑定员工</a></li>
			<li><a href="javascript:;" id="remove_resource_role_btn"><i class="icon-remove"></i>解绑员工</a></li>
		</ul>
	</div>
	<article id="content" class="content" style="width:98%;">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				
				<div class="search-panel-content">
					<form id="tConfig_configForm" name="tConfig_configForm" method="post" >
						<input type=hidden id="platform_id" name="tPlatformempCfgDto.platform_id"  value="${platform_id}"  style="width:200px;"  />
					</form>
				</div>
			</div>
			<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">公众号与员工绑定列表</h2>
				</div>
			<div class="result-content">
				<table id="dg_tCodestring" style="width:auto;height:250px">
					<thead>
						<tr>
							<th data-options="field:'CUSER_ID',hidden:true">员工用户CD</th>
							<th data-options="field:'chk',checkbox:true"></th>
							<th data-options="field:'USER_NAME',width:120,sortable:'true',align:'center'">用户名称</th>
							<th data-options="field:'MOBILE',width:120,sortable:'true',align:'center'">手机</th>
							<th data-options="field:'LOCK_FLAG',width:140,sortable:'true',align:'center'">锁定状态</th>
							<th data-options="field:'TCREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
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
	    $('div#div_platForm_list #dg_tCodestring').datagrid('options')
			.queryParams = {
				'tPlatformempCfgQueryBean.platform_id':$("#div_platForm_list #platform_id").val()
			};
	};


	//重新按照条件刷新查询内容
	$('div#div_platForm_list #search_btn').click(function(){
		tConfig_list.buildQueryParams();
		$('div#div_platForm_list #dg_tCodestring').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_platForm_list #clear_btn').click(function(){
		//清空查询条件
			$("#div_platForm_list input[name='resource_code']").val("");
			$("#div_platForm_list input[name='resource_name']").val("");
			tConfig_list.buildQueryParams();
			$('div#div_platForm_list #dg_tCodestring').datagrid('load');
	});
	
	
	//新增员工 
	$('div#div_platForm_list #add_btn').click(function(){
		$('<div id="dialog_roleSel"></div>').dialog({
		    title: '员工选择',
		    width: 800,
		    height: 515,
		    href: 'selectTEmpUserInfoAction.action?platform_id='+$("#div_platForm_list #platform_id").val(),
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
		    		var checked = $("#dg_select_tPlatForm").datagrid("getChecked");
		    		if(checked.length == 0)
			   		{
			   			$.messager.alert("友情提示","请选择您要绑定的员工号","info");
			   			return;
			   		}
		    		var role_group = [];
		    		for(var i = 0; i<checked.length;i++){
		    			role_group.push(checked[i].USER_CD);
		    		}
		    		$.post("addTPlatformempCfgAction.action",
		    		  {
		    		  	"tPlatformempCfgDto.platform_id": $('div#div_platForm_list #platform_id').val(),
		    			"tPlatformempCfgDto.user_cd": role_group.join(",")	   
					  },
					  function(data){
						$('#dialog_roleSel').dialog('close');
					      tConfig_list.buildQueryParams();
						
						$('div#div_platForm_list #dg_tCodestring').datagrid('reload');
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
	
	//解绑员工号
	$('div#div_platForm_list #remove_resource_role_btn').click(function(){
   		var checked = $('div#div_platForm_list #dg_tCodestring').datagrid("getChecked");
	   		if(checked.length == 0)
	   		{
	   			$.messager.alert("友情提示","请选择您要解绑的员工号","info");
	   			return;
	   		}
	   		var role_group = [];
	   		for(var i = 0; i<checked.length;i++){
	   			role_group.push(checked[i].CUSER_ID);
	   		}
	   		$.post("delTPlatformempCfgAction.action",
	   		  {
			    "tPlatformempCfgDto.platform_id": $('div#div_platForm_list #platform_id').val(),
			    "tPlatformempCfgDto.user_cd": role_group.join(",")	      
			  },
			  function(data){
				$('#dialog_roleSel').dialog('close');
				tConfig_list.buildQueryParams();
				$('div#div_platForm_list #dg_tCodestring').datagrid('reload');
			});
	});
	
	//datagrid初始化
	$('div#div_platForm_list #dg_tCodestring').datagrid({
		sortName:'CREATED_DATE',
		sortOrder:'desc',
		pagination:'true',
		url:'<%=pathUrl%>/searchTPlatformempCfgAction!getListData.action',
		method:'post',
		queryParams: {
		    'tPlatformempCfgQueryBean.platform_id':$("#div_platForm_list input[name='tPlatformempCfgDto.platform_id']").val()
		}												
		
	});
};

</script>