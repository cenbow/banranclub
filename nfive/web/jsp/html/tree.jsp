<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>员工信息一览</title>
<style>
.content-wide {width:95%;}
.content-aside .easyui-tree {padding:10px 5px;}
</style>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tCodestring_list" class="container">
	<header class="page-title">
		<h1>员工信息一览</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="back_btn"><i class="icon-back"></i>返回</a></li>
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>添加员工</a></li>
		</ul>
	</div>
	<article id="content" class="content content-wide">
		
		<div class="content-body">
		
			<div class="content-aside">
				<div class="easyui-panel" title="资源结构树操作面板">
					<ul id="resource_panel_tree" class="easyui-tree" data-options="url:'tree_data2.json',method:'get',animate:true,lines:true,
						checkbox: true,
						dnd:true,
						onDblClick: function(node){
			                $(this).tree('beginEdit',node.target);
			            },
			            onCheck: function(node,check){
			            	console.log(node);
			            },
			            onSelect: function(node){
			            	console.log(node);
			            },
			            onAfterEdit: function(node){
			            	console.log(node.text);
			            },
						onContextMenu: function(e,node){
							if(e.target.tagName != 'INPUT'){
				                e.preventDefault();
				                $(this).tree('select',node.target);
				                $('#tree_menu_panel').menu('show',{
				                    left: e.pageX,
				                    top: e.pageY
				                });
			                }
			            }">
           		   </ul>
				</div>
			</div>
			
			
		</div>
	</article>
</div>

<div id="tree_menu_panel" class="easyui-menu" style="width:120px;display:none;">
     <div onclick="tree_method.append()" data-options="iconCls:'icon-add'">资源新增</div>
     <div onclick="tree_method.removeit()" data-options="iconCls:'icon-remove'">资源编辑</div>
     <div onclick="tree_method.editit()" data-options="iconCls:'icon-edit'">资源详细</div>
     <div onclick="tree_method.expand()">资源删除</div>
     <div onclick="tree_method.collapse()">资源上级调整</div>
     <div onclick="tree_method.resConfig()">资源分配权限</div>
</div>

<script type="text/javascript">
var tree_method = {};
    tree_method.append = function( treeJson ){
        tCodestring_list.addResource();
   };
   tree_method.removeit = function(){
        //var treeNode = $('#resource_panel_tree');
        //var node = treeNode.tree('getSelected');
        //treeNode.tree('remove', node.target);
        var node = $('#resource_panel_tree').tree('find', 5);
		$('#resource_panel_tree').tree('scrollTo', node.target);
   };
   tree_method.editit = function(){
        var treeNode = $('#resource_panel_tree');
        var node = treeNode.tree('getSelected');
        treeNode.tree('beginEdit', node.target);
   };
   tree_method.collapse = function(){
    	var treeNode = $('#resource_panel_tree');
        var node = treeNode.tree('getSelected');
        treeNode.tree('collapse',node.target);
   };
   tree_method.expand = function(){
    	var treeNode = $('#resource_panel_tree');
        var node = treeNode.tree('getSelected');
        treeNode.tree('expand',node.target);
   };
</script>

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
					created_user_cd :$("input[name='search_created_user_cd']").val()
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
	tCodestring_list.addResource = function(){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '角色新增',
		    width: 800,
		    height: 400,
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
					    var add_form_id ='#addTCodestringFrom';
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
	};
	
	tree_method.resConfig = function(pkid){
		$('<div id="dialog_resConfig"></div>').dialog({
		    title: '资源配置角色',
		    width: 800,
		    height: 550,
		    href: 'resConfigMini.jsp',
		    modal: true,
		    method: "POST",
		   rams:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			}
		});
	};
	
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
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>