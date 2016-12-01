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
<div id="div_tRoleResRela_list" class="container">
	<header class="page-title">
		<h1></h1>
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
									 <tr>
										<th class="wd-20"><label>role_id</label></th>
										<td>
											<input type="text" id="tRoleResRelaDto.role_id" name="search_role_id"  value="${tRoleResRelaDto.role_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>res_id</label></th>
										<td>
											<input type="text" id="tRoleResRelaDto.res_id" name="search_res_id"  value="${tRoleResRelaDto.res_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									<tr>
										<th class="wd-20"><label>created_date</label></th>
										<td>
											<input type="text" id="tRoleResRelaDto.created_date" name="search_created_date"  value="${tRoleResRelaDto.created_date}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />
										</td>
									 </tr>
									 <tr>
										<th class="wd-20"><label>created_user_cd</label></th>
										<td>
											<input type="text" id="tRoleResRelaDto.created_user_cd" name="search_created_user_cd"  value="${tRoleResRelaDto.created_user_cd}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button"  value="查 询" />
							<input id="clear_btn" type="button"  value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tRoleResRela" class="easyui-datagrid" title="DataGrid Title" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTRoleResRelaAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">detail</th>
							<th data-options="field:'EDIT',width:50,align:'center'">edit</th>
							<th data-options="field:'ROLE_RES_ID',width:120,sortable:'true',align:'right'">role_res_id</th>
							<th data-options="field:'ROLE_ID',width:120,sortable:'true',align:'right'">role_id</th>
							<th data-options="field:'RES_ID',width:120,sortable:'true',align:'right'">res_id</th>
							<th data-options="field:'CREATED_DATE',width:120,sortable:'true',align:'right'">created_date</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'right'">created_user_cd</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>
<script>
var tRoleResRela_list ={};
jQuery(function($){
	//定义构造查询
	tRoleResRela_list.buildQueryParams=function(){
	    $('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid({
			queryParams: {
						 'tRoleResRelaQueryBean.role_res_id':$("input[name='search_role_res_id']").val(),
						 'tRoleResRelaQueryBean.role_id':$("input[name='search_role_id']").val(),
						 'tRoleResRelaQueryBean.res_id':$("input[name='search_res_id']").val(),
						 'tRoleResRelaQueryBean.created_date':$("input[name='search_created_date']").val(),
						 'tRoleResRelaQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val(),
			}
		});
	}


    //定义构造查询JSON
    tRoleResRela_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid('options').pageNumber,
		  		//页面查询框部分
					role_res_id :$("input[name='search_role_res_id']").val(), 
					role_id :$("input[name='search_role_id']").val(), 
					res_id :$("input[name='search_res_id']").val(), 
					created_date :$("input[name='search_created_date']").val(), 
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tRoleResRela_list #search_btn').click(function(){
		tRoleResRela_list.buildQueryParams();
		$('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tRoleResRela_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_role_res_id']").val("");
			$("input[name='search_role_id']").val("");
			$("input[name='search_res_id']").val("");
			$("input[name='search_created_date']").val("");
			$("input[name='search_created_user_cd']").val("");
		tRoleResRela_list.buildQueryParams();
		$('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid('load');
	});
	
	
	//新增
	$('div#div_tRoleResRela_list #add_btn').click(function(){
		var add_form_id ='#addTRoleResRelaFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'T_ROLE_RES_RELA',
		    width: 800,
		    height: 500,
		    href: 'addTRoleResRelaPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTRoleResRelaFrom').form({   
						 url:'addTRoleResRelaAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tRoleResRela_list.buildQueryParams();
									$('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid('load');
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
	tRoleResRela_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTRoleResRelaFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TRoleResRela',
		    width: 800,
		    height: 500,
		    href: 'editTRoleResRelaPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTRoleResRelaFrom').form({   
						 url:'editTRoleResRelaAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tRoleResRela_list.buildQueryParams();
									$('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid('load');
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
		     			$('#delTRoleResRelaFrom').form({   
						 url:'delTRoleResRelaAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tRoleResRela_list.buildQueryParams();
									$('div#div_tRoleResRela_list #dg_tRoleResRela').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    $('#delTRoleResRelaFrom').submit();
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
	tRoleResRela_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TRoleResRela',
		    width: 800,
		    height: 500,
		    href: 'detailTRoleResRelaPage.action',
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