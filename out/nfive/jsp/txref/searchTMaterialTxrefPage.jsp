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
<div id="div_tMaterialTxref_list" class="container">
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
									 <tr>
										<th class="wd-20"><label>material_type</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.material_type" name="search_material_type"  value="${tMaterialTxrefDto.material_type}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>material_id</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.material_id" name="search_material_id"  value="${tMaterialTxrefDto.material_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>tx_thumb_mdeia_id</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.tx_thumb_mdeia_id" name="search_tx_thumb_mdeia_id"  value="${tMaterialTxrefDto.tx_thumb_mdeia_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>tx_media_id</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.tx_media_id" name="search_tx_media_id"  value="${tMaterialTxrefDto.tx_media_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>tx_created_at</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.tx_created_at" name="search_tx_created_at"  value="${tMaterialTxrefDto.tx_created_at}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>tx_expiration_at</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.tx_expiration_at" name="search_tx_expiration_at"  value="${tMaterialTxrefDto.tx_expiration_at}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>tx_type</label></th>
										<td>
											<input type="text" id="tMaterialTxrefDto.tx_type" name="search_tx_type"  value="${tMaterialTxrefDto.tx_type}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
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
				<table  id="dg_tMaterialTxref" class="easyui-datagrid" title="DataGrid Title" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMaterialTxrefAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">detail</th>
							<th data-options="field:'EDIT',width:50,align:'center'">edit</th>
							<th data-options="field:'TXREF_ID',width:120,sortable:'true',align:'right'">txref_id</th>
							<th data-options="field:'MATERIAL_TYPE',width:120,sortable:'true',align:'right'">material_type</th>
							<th data-options="field:'MATERIAL_ID',width:120,sortable:'true',align:'right'">material_id</th>
							<th data-options="field:'TX_THUMB_MDEIA_ID',width:120,sortable:'true',align:'right'">tx_thumb_mdeia_id</th>
							<th data-options="field:'TX_MEDIA_ID',width:120,sortable:'true',align:'right'">tx_media_id</th>
							<th data-options="field:'TX_CREATED_AT',width:120,sortable:'true',align:'right'">tx_created_at</th>
							<th data-options="field:'TX_EXPIRATION_AT',width:120,sortable:'true',align:'right'">tx_expiration_at</th>
							<th data-options="field:'TX_TYPE',width:120,sortable:'true',align:'right'">tx_type</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tMaterialTxref_list ={};
jQuery(function($){
	//定义构造查询
	tMaterialTxref_list.buildQueryParams=function(){
	     $('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid("options")
			.queryParams = {
					'tMaterialTxrefQueryBean.txref_id':$("input[name='search_txref_id']").val(),
					'tMaterialTxrefQueryBean.material_type':$("input[name='search_material_type']").val(),
					'tMaterialTxrefQueryBean.material_id':$("input[name='search_material_id']").val(),
					'tMaterialTxrefQueryBean.tx_thumb_mdeia_id':$("input[name='search_tx_thumb_mdeia_id']").val(),
					'tMaterialTxrefQueryBean.tx_media_id':$("input[name='search_tx_media_id']").val(),
					'tMaterialTxrefQueryBean.tx_created_at':$("input[name='search_tx_created_at']").val(),
					'tMaterialTxrefQueryBean.tx_expiration_at':$("input[name='search_tx_expiration_at']").val(),
					'tMaterialTxrefQueryBean.tx_type':$("input[name='search_tx_type']").val(),
			};
	};


    //定义构造查询JSON
    tMaterialTxref_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid('options').pageNumber,
		  		//页面查询框部分
					txref_id :$("input[name='search_txref_id']").val(), 
					material_type :$("input[name='search_material_type']").val(), 
					material_id :$("input[name='search_material_id']").val(), 
					tx_thumb_mdeia_id :$("input[name='search_tx_thumb_mdeia_id']").val(), 
					tx_media_id :$("input[name='search_tx_media_id']").val(), 
					tx_created_at :$("input[name='search_tx_created_at']").val(), 
					tx_expiration_at :$("input[name='search_tx_expiration_at']").val(), 
					tx_type :$("input[name='search_tx_type']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tMaterialTxref_list #search_btn').click(function(){
		tMaterialTxref_list.buildQueryParams();
		$('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialTxref_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_txref_id']").val("");
			$("input[name='search_material_type']").val("");
			$("input[name='search_material_id']").val("");
			$("input[name='search_tx_thumb_mdeia_id']").val("");
			$("input[name='search_tx_media_id']").val("");
			$("input[name='search_tx_created_at']").val("");
			$("input[name='search_tx_expiration_at']").val("");
			$("input[name='search_tx_type']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialTxref_list.buildQueryParams();
		$('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid('load');
	});
	
	
	//新增
	$('div#div_tMaterialTxref_list #add_btn').click(function(){
		var add_form_id ='#addTMaterialTxrefFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'T_MATERIAL_TXREF',
		    width: 800,
		    height: 500,
		    href: 'addTMaterialTxrefPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTMaterialTxrefFrom').form({   
						 url:'addTMaterialTxrefAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tMaterialTxref_list.buildQueryParams();
									$('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid('load');
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
	tMaterialTxref_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTMaterialTxrefFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TMaterialTxref',
		    width: 800,
		    height: 500,
		    href: 'editTMaterialTxrefPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTMaterialTxrefFrom').form({   
						 url:'editTMaterialTxrefAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tMaterialTxref_list.buildQueryParams();
									$('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid('load');
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
		     			$('#delTMaterialTxrefFrom').form({   
						 url:'delTMaterialTxrefAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tMaterialTxref_list.buildQueryParams();
									$('div#div_tMaterialTxref_list #dg_tMaterialTxref').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    $('#delTMaterialTxrefFrom').submit();
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
	tMaterialTxref_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TMaterialTxref',
		    width: 800,
		    height: 500,
		    href: 'detailTMaterialTxrefPage.action',
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