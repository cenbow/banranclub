<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String pathUrl = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_platFormCfg_list">
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>绑定公众号</a></li>
			<li><a href="javascript:;" id="remove_resource_role_btn"><i class="icon-remove"></i>解绑公众号</a></li>
		</ul>
	</div>
	<article id="content" class="content" style="width:98%;">
		<div class="content-body">
			<div class="search-panel toggle-panel">
					<div class="search-panel-content">
					<form id="tConfig_configForm" name="tConfig_configForm" method="post" >
						<input type="hidden" id="user_cd" name="tPlatformempCfgDto.user_cd" style="width:200px;" value="${userCd}" } />
					</form>
				</div>
			</div>
			
			<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">已绑定公众号</h2>
				</div>
			<div class="result-content">
				<table id="dg_tplatFormCfg" style="width:auto;height:250px">
					<thead>
						<tr>
							<th data-options="field:'PLATFORM_ID',hidden:true">公众号ID</th>
							<th data-options="field:'chk',checkbox:true"></th>
							<th data-options="field:'CUSER_ID',width:120,sortable:'true',align:'center'">员工CD</th>
							<th data-options="field:'TWECHART_ACCOUNT',width:120,sortable:'true',align:'center'">公众号名称</th>
							<th data-options="field:'CIS_USE',width:140,sortable:'true',align:'center'">当前使用公众号</th>
							<th data-options="field:'CIS_VALID',width:140,sortable:'true',align:'center'">是否绑定</th>
							<th data-options="field:'CREATED_DATE',width:150,sortable:'true',align:'center'">创建时间</th>
							<th data-options="field:'TCREATED_USER_CD',width:150,sortable:'true',align:'center'">创建人</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</article>
</div>
		  



<script>
var tConfig_list ={};

tConfig_list.trigger = function(){
	//定义构造查询
	tConfig_list.buildQueryParams=function(){
	    $('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid('options')
			.queryParams = {
				'tPlatformempCfgQueryBean.user_cd':$("#div_platFormCfg_list input[name='tPlatformempCfgDto.user_cd']").val()
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_platFormCfg_list #search_btn').click(function(){
		tConfig_list.buildQueryParams();
		$('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_platFormCfg_list #clear_btn').click(function(){
		//清空查询条件
			$("#div_platFormCfg_list input[name='resource_code']").val("");
			$("#div_platFormCfg_list input[name='resource_name']").val("");
			tConfig_list.buildQueryParams();
			$('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid('load');
	});
	
	
	//新增公众号
	$('div#div_platFormCfg_list #add_btn').click(function(){
		$('<div id="dialog_roleSel"></div>').dialog({
		    title: '未绑定公众号选择',
		    width: 800,
		    height: 515,
		    href: 'selectTPlatformempAction.action?tPubPlatformQueryBean.user_cd='+$('div#div_platFormCfg_list #user_cd').val(),
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
			   			$.messager.alert("友情提示","请选择您要 绑定的公众号","info");
			   			return;
			   		}
		    		var user_group = [];
		    		for(var i = 0; i<checked.length;i++){
		    			user_group.push(checked[i].PLATFORM_ID);
		    		}
		    		$.post("addTPlatformempCfgAction.action",
		    		  {
		    		  	"tPlatformempCfgDto.platform_id": user_group.join(","),	
		    			"tPlatformempCfgDto.user_cd": $('div#div_platFormCfg_list #user_cd').val()
					  },
					  function(data){
						$('#dialog_roleSel').dialog('close');
						tConfig_list.buildQueryParams();
						$('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid('reload');
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
	$('div#div_platFormCfg_list #remove_resource_role_btn').click(function(){
   		var checked = $('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid("getChecked");
   		if(checked.length == 0)
   		{
   			$.messager.alert("友情提示","请选择您要解绑的公众号","info");
   			return;
   		}
   		var user_group = [];
   		for(var i = 0; i<checked.length;i++){
   			user_group.push(checked[i].PLATFORM_ID);
   		}
   		
   		$.post("delTPlatformempCfgAction.action",
   		  {
   		  	"tPlatformempCfgDto.platform_id": user_group.join(","),	
		    "tPlatformempCfgDto.user_cd": $('div#div_platFormCfg_list #user_cd').val()
		  },
		  function(data){
		  tConfig_list.buildQueryParams();
		  $('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid('reload');
		});
	});
	
	//datagrid初始化
	$('div#div_platFormCfg_list #dg_tplatFormCfg').datagrid({
		sortName:'CREATED_DATE',
		sortOrder:'desc',
		pagination:'true',
		url:'<%=pathUrl%>/searchTPlatformempCfgAction!getListData.action',
		method:'post',
		queryParams: {
			  'tPlatformempCfgQueryBean.user_cd':$("#div_platFormCfg_list input[name='tPlatformempCfgDto.user_cd']").val()
		}
	});
};

</script>