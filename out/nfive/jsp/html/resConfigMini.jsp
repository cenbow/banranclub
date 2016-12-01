<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--内容-->

	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>添加角色</a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">员工信息</h2>
				</div>
				<div class="search-panel-content">
					<div class="search-panel-bd">
						<table class="search-table">
							 <tr>
									<th class="wd-20"><label>员工CD</label></th>
									<td>
										<span>wangzhiguo</span>
									</td>
									<th class="wd-20"><label>员工名称</label></th>
									<td>
										<span>王治国</span>
									</td>
							  </tr>
						</table>
					</div>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tCodestring" class="easyui-datagrid" title="角色信息一览查询结果" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,sortName:'',sortOrder:'desc',pagination:'true',url:'searchTCodestringAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'CS_CODE',width:120,sortable:'true',align:'right'">资源配置</th>
							<th data-options="field:'CS_TYPE',width:120,sortable:'true',align:'right'">角色ID</th>
							<th data-options="field:'CS_SUB_TYPE',width:120,sortable:'true',align:'right'">角色名称</th>
							<th data-options="field:'CS_NAME',width:120,sortable:'true',align:'right'">标识组</th>
							<th data-options="field:'CS_VALUE',width:120,sortable:'true',align:'right'">备注</th>
							<th data-options="field:'CS_DESC',width:120,sortable:'true',align:'right'">创建人</th>
							<th data-options="field:'CREATED_DATE',width:120,sortable:'true',align:'right'">创建时间</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'right'">更新人</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'right'">更新时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>


<script>
var tCodestring_list ={};
jQuery(function($){
	//定义构造查询
	tCodestring_list.buildQueryParams=function(){
	    $('div#div_tCodestring_list #dg_tCodestring').datagrid('options')
			.queryParams = {
						 'tCodestringQueryBean.cs_code':$("input[name='search_cs_code']").val(),
						 'tCodestringQueryBean.cs_type':$("input[name='search_cs_type']").val(),
						 'tCodestringQueryBean.cs_sub_type':$("input[name='search_cs_sub_type']").val(),
						 'tCodestringQueryBean.cs_name':$("input[name='search_cs_name']").val(),
						 'tCodestringQueryBean.cs_value':$("input[name='search_cs_value']").val(),
						 'tCodestringQueryBean.cs_desc':$("input[name='search_cs_desc']").val(),
						 'tCodestringQueryBean.created_date':$("input[name='search_created_date']").val(),
						 'tCodestringQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val()
			};
	};


    //定义构造查询JSON
    tCodestring_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tCodestring_list #dg_tCodestring').datagrid('options').pageNumber,
		  		//页面查询框部分
					cs_code :$("input[name='search_cs_code']").val(), 
					cs_type :$("input[name='search_cs_type']").val(), 
					cs_sub_type :$("input[name='search_cs_sub_type']").val(), 
					cs_name :$("input[name='search_cs_name']").val(), 
					cs_value :$("input[name='search_cs_value']").val(), 
					cs_desc :$("input[name='search_cs_desc']").val(), 
					created_date :$("input[name='search_created_date']").val(), 
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tCodestring_list #search_btn').click(function(){
		tCodestring_list.buildQueryParams();
		$('div#div_tCodestring_list #dg_tCodestring').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tCodestring_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_cs_code']").val("");
			$("input[name='search_cs_type']").val("");
			$("input[name='search_cs_sub_type']").val("");
			$("input[name='search_cs_name']").val("");
			$("input[name='search_cs_value']").val("");
			$("input[name='search_cs_desc']").val("");
			$("input[name='search_created_date']").val("");
			$("input[name='search_created_user_cd']").val("");
		tCodestring_list.buildQueryParams();
		$('div#div_tCodestring_list #dg_tCodestring').datagrid('load');
	});
	
	
	//新增
	$('div#div_tCodestring_list #add_btn').click(function(){
		var add_form_id ='#addTCodestringFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '角色新增',
		    width: 800,
		    height: 350,
		    href: 'roleAdd.jsp',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTCodestringFrom').form({   
						 url:'addTCodestringAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tCodestring_list.buildQueryParams();
									$('div#div_tCodestring_list #dg_tCodestring').datagrid('load');
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
	tCodestring_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTCodestringFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TCodestring',
		    width: 800,
		    height: 500,
		    href: 'editTCodestringPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTCodestringFrom').form({   
						 url:'editTCodestringAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tCodestring_list.buildQueryParams();
									$('div#div_tCodestring_list #dg_tCodestring').datagrid('load');
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
		     			$('#delTCodestringFrom').form({   
						 url:'delTCodestringAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tCodestring_list.buildQueryParams();
									$('div#div_tCodestring_list #dg_tCodestring').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    $('#delTCodestringFrom').submit();
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
	tCodestring_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TCodestring',
		    width: 800,
		    height: 500,
		    href: 'detailTCodestringPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	
});

</script>