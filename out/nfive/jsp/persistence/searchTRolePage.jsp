<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>角色管理一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tRole_list" class="container">
	<header class="page-title">
		<h1>角色管理</h1>
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
										<th class="wd-20"><label>角色名称</label></th>
										<td>
											<input type="text" id="tRoleDto.role_name" name="search_role_name"  value="" style="width:200px;" data-options="required:true" />
										</td>
										<th class="wd-20"><label>角色分组</label></th>
										<td>
											<input type="text" id="tRoleDto.role_group" name="tRoleDto.role_group"  value="" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" value="查 询" />
							<input id="clear_btn" type="button" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tRole" class="easyui-datagrid" title="角色管理" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'CREATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTRoleAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:60,align:'center'">角色详细</th>
							<th data-options="field:'EDIT',width:60,align:'center'">角色编辑</th>
							<th data-options="field:'RESOURCE_CONFIG',width:80,align:'center'">资源配置</th>
							<th data-options="field:'USER_CONFIG',width:80,align:'center'">员工配置</th>
							<th data-options="field:'ROLE_ID',width:120,sortable:'true',align:'center',hidden:true">role_id</th>
							<th data-options="field:'ROLE_NAME',width:145,sortable:'true',align:'center'">角色名称</th>
							<th data-options="field:'ROLE_GROUP',width:150,sortable:'true',align:'center'">角色分组</th>
							<th data-options="field:'ROLE_DESC',width:205,sortable:'true',align:'center'">角色描述</th>
							<th data-options="field:'CREATED_DATE',width:200,sortable:'true',align:'center'">创建日期</th>
							<th data-options="field:'CREATED_USER_CD',width:180,sortable:'true',align:'center'">创建人</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tRole_list ={};

jQuery(function($){
	//定义构造查询
	tRole_list.buildQueryParams = function(){
	    $('div#div_tRole_list #dg_tRole').datagrid("options").queryParams = {
			'tRoleQueryBean.role_id':$("input[name='search_role_id']").val(),
			'tRoleQueryBean.role_name':$("input[name='search_role_name']").val(),
			'tRoleQueryBean.role_group':$("input[name='tRoleDto.role_group']").val(),
			'tRoleQueryBean.role_desc':$("input[name='search_role_desc']").val(),
			'tRoleQueryBean.created_date':$("input[name='search_created_date']").val(),
			'tRoleQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val()
		};
	};


	//重新按照条件刷新查询内容
	$('div#div_tRole_list #search_btn').click(function(){
		tRole_list.buildQueryParams();
		$('div#div_tRole_list #dg_tRole').datagrid('reload');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tRole_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_role_id']").val("");
			$("input[name='search_role_name']").val("");
			$("input[name='tRoleDto.role_group']").val("");
			$("input[name='search_role_desc']").val("");
			$("input[name='search_created_date']").val("");
			$("input[name='search_created_user_cd']").val("");
			tRole_list.buildQueryParams();
			$('div#div_tRole_list #dg_tRole').datagrid('reload');
	});
	
	
	//新增
	$('div#div_tRole_list #add_btn').click(function(){
		var add_form_id ='#addTRoleFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '新增',
		    width: 800,
		    height: 500,
		    href: 'addTRolePage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		var ThisForm = $(this).find("form"),
		   			ACD = ThisForm[0]['tRoleDto.role_name'].value;
		    		group = ThisForm[0]['tRoleDto.role_group'].value;
		    		desc = ThisForm[0]['tRoleDto.role_desc'].value;
		    		if(ACD.length >50) {
						$.messager.alert("友情提示","角色名称不能超过50","info");
						return false;
					}
					if(group.length >50) {
						$.messager.alert("友情提示","角色分组不能超过50","info");
						return false;
					}
					if(desc.length >500) {
						$.messager.alert("友情提示","角色描述不能超过500","info");
						return false;
					}
		    		if(ThisForm.form("validate")){
		    			$.ajax({
		    				type: 'post',
		    				url: "<%=path%>/ld_crm_tsr/checkTRoleAction.action",
		    				dataType: 'json',
		    				data: {
		    					'tRoleDto.role_name': ACD,
		    					'doType':'add'
		    				},
		    				success:function(data){
		    					if(data.status){
		    						ThisForm.form('submit', {
		    							 url:"<%=path%>/ld_crm_tsr/addTRoleAction.action",
		    							 onSubmit: function(){
									       $.messager.progress(); 
									     }, 
									     success:function(data){
									    		$.messager.progress('close');
									    		$('#dialog_holder').dialog('close');
												tRole_list.buildQueryParams();
												$('div#div_tRole_list #dg_tRole').datagrid('load');
									    }
									});
		    					}else{
		    						$.messager.alert("提示信息",data.message,"error");
		    					}
		    				}
		    			});
		    		}
		    		
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
	tRole_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTRoleFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '编辑',
		    width: 800,
		    height: 350,
		    href: 'editTRolePage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   			var ThisForm = $(this).find("form");
		   			ACD = ThisForm[0]['tRoleDto.role_name'].value;
		    		group = ThisForm[0]['tRoleDto.role_group'].value;
		    		desc = ThisForm[0]['tRoleDto.role_desc'].value;
		    		if(ACD.length >50) {
					$.messager.alert("友情提示","角色名称不能超过50","info");
					return false;
					}
					if(group.length >50) {
					$.messager.alert("友情提示","角色分组不能超过50","info");
					return false;
					}
					if(desc.length >500) {
					$.messager.alert("友情提示","角色描述不能超过500","info");
					return false;
					}
		   			
		    		$.ajax({
		    			type:'post',
		    			dataType:'json',
		    			url: "<%=path%>/ld_crm_tsr/checkTRoleAction.action",
		    			data:{
		    				'tRoleDto.role_id': pkid,
		    				'tRoleDto.role_name': ThisForm[0]['tRoleDto.role_name'].value,
		    				'doType':'edit'
		    			},
		    			success: function(data){	
		    				if(data.status){
		    					ThisForm.form('submit', {
									 url:'editTRoleAction.action',    
								     onSubmit: function(){
								       $.messager.progress(); 
								     },    
								     success:function(data){
								        $.messager.progress('close');
								        $('#dialog_holder').dialog('close');
										tRole_list.buildQueryParams();
										$('div#div_tRole_list #dg_tRole').datagrid('reload');
								     }
								});
		    				}else{
		    					$.messager.alert("提示信息",data.message,"error");
		    				}
		    			}
		    		});
		    		
		    	}
		    },
		    {
                 text: "删  除",
                 handler: function(e){
                 	$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("delTRoleAction.action",{pkid: pkid}, function(data){
                 				data = JSON.parse(data);
                 				if(data.status){
		                        	$('#dialog_holder').dialog('close');
		                        	tRole_list.buildQueryParams();
		                            $('div#div_tRole_list #dg_tRole').datagrid('reload');
		                        }else{
		                           	$.messager.alert("提示信息",data.message,"error");
		                        }
                       		});
                 		}
                 	});
                 }
             },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//详细
	tRole_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详情',
		    width: 800,
		    height: 400,
		    href: 'detailTRolePage.action',
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
	
	  //角色分配权限
      tRole_list.allotResourceFormSubmit = function(pkid,roleName,roleGroup){
          $('<div id="dialog_holder"></div>').dialog({
              title: roleName + '资源分配',
              width: 800,
              height: 400,
              href: 'configResourceToRolePage.action',
              modal: true,
              method: "POST",
              params:{"tRoleResRelaDto.role_id":pkid},
              onClose: function(){
                  $(this).dialog("destroy");
              },
              buttons: [{
		    	iconCls: 'icon-ok',
		    	text: "保存设置",
		    	handler: function(e){
		    		var treeNode = $('#resource_panel_tree');
		    		var nodes = treeNode.tree('getChecked',['checked','indeterminate']);
		    		var resource_group = [];
		    		for(var i = 0; i<nodes.length;i++){
		    			resource_group.push(nodes[i].id);
		    		}
		    		$.messager.progress();
		    		$.post("configResourceToRole.action",
		    		  {
		    		  	"tRoleResRelaDto.role_id": pkid,
		    			"tRoleResRelaDto.res_id": resource_group.join(",")	   
					  },
					  function(data){
					  	 $.messager.progress('close');
						 treeNode.tree('reload');
						 
					  });
		    	}
		    },{
                  text: "关闭",
                  handler: function(e){
                      $(this).dialog("close");
                  }
              }]
          });
      }
	
	  //角色分配员工
      tRole_list.allotUserFormSubmit = function(pkid,roleName,roleGroup){
          $('<div id="dialog_holder"></div>').dialog({
              title: roleName + '角色员工分配',
              width: 800,
              height: 510,
              href: 'configUserToRolePage.action',
              modal: true,
              method: "POST",
              onLoad: function(){
				$("#div_roleUser_list #role_id").val(pkid);
				$("#div_roleUser_list #role_name").val(roleName);
				tConfig_list.trigger();
			  },
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