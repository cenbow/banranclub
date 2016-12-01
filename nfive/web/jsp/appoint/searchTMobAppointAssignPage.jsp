<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>模板内容页</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tMobAppointAssign_list" class="container">
	<header class="page-title">
		<h1>产品一览</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="searchForm" name="searchForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" class="input-btn-small" value="查 询" />
							<input id="clear_btn" type="button" class="input-btn-small" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tMobAppointAssign" class="easyui-datagrid" title="DataGrid Title" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMobAppointAssignAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'DELETE',width:50,align:'center'">删除</th>
							<th data-options="field:'CUST_NAME',width:120,sortable:'true',align:'right'">CUST_NAME</th>
							<th data-options="field:'EMAIL',width:120,sortable:'true',align:'right'">EMAIL</th>
							<th data-options="field:'MOBILE',width:120,sortable:'true',align:'right'">MOBILE</th>
							<th data-options="field:'GOODSNAME',width:120,sortable:'true',align:'right'">GOODSNAME</th>
							<th data-options="field:'HEAD_IMG_URL',width:120,sortable:'true',align:'right'">HEAD_IMG_URL</th>
							<th data-options="field:'REMARK',width:120,sortable:'true',align:'right'">REMARK</th>				
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tMobAppointAssign_list ={};
jQuery(function($){
	//定义构造查询
	tMobAppointAssign_list.buildQueryParams=function(){
	     $('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid("options")
			.queryParams = {
					'tMobAppointAssignQueryBean.assign_id':$("input[name='search_assign_id']").val(),
					'tMobAppointAssignQueryBean.appoint_type':$("input[name='search_appoint_type']").val(),
					'tMobAppointAssignQueryBean.target_code':$("input[name='search_target_code']").val(),
					'tMobAppointAssignQueryBean.fc_maccount_no':$("input[name='search_fc_maccount_no']").val(),
					'tMobAppointAssignQueryBean.is_handled':$("input[name='search_is_handled']").val(),
					'tMobAppointAssignQueryBean.remark':$("input[name='search_remark']").val(),
					'tMobAppointAssignQueryBean.updated_date':$("input[name='search_updated_date']").val(),
					'tMobAppointAssignQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val(),
					'tMobAppointAssignQueryBean.updated_user_cd':$("input[name='search_updated_user_cd']").val(),
			};
	};


    //定义构造查询JSON
    tMobAppointAssign_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid('options').pageNumber,
		  		//页面查询框部分
					assign_id :$("input[name='search_assign_id']").val(), 
					appoint_type :$("input[name='search_appoint_type']").val(), 
					target_code :$("input[name='search_target_code']").val(), 
					fc_maccount_no :$("input[name='search_fc_maccount_no']").val(), 
					is_handled :$("input[name='search_is_handled']").val(), 
					remark :$("input[name='search_remark']").val(), 
					updated_date :$("input[name='search_updated_date']").val(), 
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
					updated_user_cd :$("input[name='search_updated_user_cd']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tMobAppointAssign_list #search_btn').click(function(){
		tMobAppointAssign_list.buildQueryParams();
		$('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMobAppointAssign_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_assign_id']").val("");
			$("input[name='search_appoint_type']").val("");
			$("input[name='search_target_code']").val("");
			$("input[name='search_fc_maccount_no']").val("");
			$("input[name='search_is_handled']").val("");
			$("input[name='search_remark']").val("");
			$("input[name='search_updated_date']").val("");
			$("input[name='search_created_user_cd']").val("");
			$("input[name='search_updated_user_cd']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tMobAppointAssign_list.buildQueryParams();
		$('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid('load');
	});
	
	
	//新增
	$('div#div_tMobAppointAssign_list #add_btn').click(function(){
		var add_form_id ='#addTMobAppointAssignFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'T_MOB_APPOINT_ASSIGN',
		    width: 800,
		    height: 500,
		    href: 'addTMobAppointAssignPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){ 
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTMobAppointAssignFrom').form({   
						 url:'addTMobAppointAssignAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tMobAppointAssign_list.buildQueryParams();
									$('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    //validate and sbumit
					    if($(add_form_id).form("validate")==true){
						  	$.messager.progress();
							$(add_form_id).submit();
						};   
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
	//更新
	tMobAppointAssign_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTMobAppointAssignFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TMobAppointAssign',
		    width: 800,
		    height: 500,
		    href: 'editTMobAppointAssignPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTMobAppointAssignFrom').form({   
						 url:'editTMobAppointAssignAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tMobAppointAssign_list.buildQueryParams();
									$('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
						  	$.messager.progress();
							$(edit_form_id).submit();
						};   
		    		
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     			$('#delTMobAppointAssignFrom').form({   
						 url:'delTMobAppointAssignAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tMobAppointAssign_list.buildQueryParams();
									$('div#div_tMobAppointAssign_list #dg_tMobAppointAssign').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    $('#delTMobAppointAssignFrom').submit();
		    	}
		     }
		    ,{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//详细
	tMobAppointAssign_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TMobAppointAssign',
		    width: 800,
		    height: 500,
		    href: 'detailTMobAppointAssignPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		var ThisForm = $(this).find("form");
		    	}
		    },{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>