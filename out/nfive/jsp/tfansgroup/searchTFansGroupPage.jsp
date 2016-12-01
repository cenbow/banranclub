<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>粉丝群一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tFansGroup_list" class="container">
	<header class="page-title">
		<h1>粉丝群一览</h1>
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
										<th class="wd-10"><label>群名称</label></th>
										<td>
											<input type="text" name="search_group_name" style="width:200px;" maxlength="50"/>
										</td>
										<th class="wd-10"></th>
										<td>
										</td>
										<th class="wd-10"></th>
										<td>
										</td>
									</tr>
									 <tr>
										<th class="wd-10"><label>创建人</label></th>
										<td>
											<input type="text"  name="search_created_user_cd" style="width:200px;" />
										</td>
										<th class="wd-10"><label>更新人</label></th>
										<td>
											<input type="text" name="search_updated_user_cd" style="width:200px;" />
										</td>
										<th class="wd-10"><label>最后更新时间</label></th>
										<td>
											<input type="text" id="tFansGroupDtoCxStartdate" name="search_cx_startdate" style="width:108px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;"></i>
											~											
											<input type="text" id="tFansGroupDtoCxEnddate" name="search_cx_enddate" validType="minFirstDate['#tFansGroupDtoCxStartdate']" style="width:108px;" class="easyui-datebox"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
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
				<table  id="dg_tFansGroup" class="easyui-datagrid" title="粉丝群一览列表" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTFansGroupAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'TOFANS',width:70,align:'center'">成员管理</th>
							<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'DEL',width:50,align:'center'">删除</th>
							<th data-options="field:'FANS_GROUP_ID',width:120,sortable:'true',align:'center',hidden:'true'">群ID</th>
							<th data-options="field:'GROUP_NAME',width:230,sortable:'true',align:'center'">群名称</th>
							<th data-options="field:'REMARK',width:430,sortable:'true',align:'center'">群说明</th>
							<th data-options="field:'CREATED_DATE',width:120,sortable:'true',align:'right',hidden:'true'">创建日期</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'UPDATED_DATE',width:150,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tFansGroup_list ={};
jQuery(function($){

	//定义清空日期
	$(".icon-clear-date").mousedown(function(){
		var id=$(this).prev().prev().attr("id");
		$("#"+id).datebox("setValue","");
    	$("#"+id).datebox("reset");
	});	
	//定义构造查询
	tFansGroup_list.buildQueryParams=function(){
	     $('div#div_tFansGroup_list #dg_tFansGroup').datagrid("options")
			.queryParams = {
					'in_tFansGroupQueryBean.group_name':$.trim($("input[name='search_group_name']").val()),//群名称
					
					'in_tFansGroupQueryBean.created_user_cd':$.trim($("input[name='search_created_user_cd']").val()),//创建人
					'in_tFansGroupQueryBean.updated_user_cd':$.trim($("input[name='search_updated_user_cd']").val()),//更新人
					
					'in_tFansGroupQueryBean.cxstartdate':$.trim($('#tFansGroupDtoCxStartdate').datebox('getValue')),//最后更新开始时间
					'in_tFansGroupQueryBean.cxenddate':$.trim($('#tFansGroupDtoCxEnddate').datebox('getValue'))//最后更新结束时间
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tFansGroup_list #search_btn').click(function(){
		if ($("div#div_tFansGroup_list #searchForm").form("validate")==true){
			tFansGroup_list.buildQueryParams();
			$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tFansGroup_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tFansGroup_list.buildQueryParams();
		$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
	});
	
	
	//新增
	$('div#div_tFansGroup_list #add_btn').click(function(){
		var add_form_id ='#addTFansGroupFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝群新增',
		    width: 800,
		    height: 500,
		    href: 'addTFansGroupPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTFansGroupFrom').form({   
						 url:'addTFansGroupAction.action',    
							     onSubmit: function(){
								     //校验粉丝群名称是否全部是空格
								     if('' == $.trim($("#add_group_name").val())){
								     		$("#add_group_name").val('');
								     		$("#add_group_name").focus();
								     		return false;
								     }
							         $.messager.progress(); 
							     },    
							     success:function(data){
							       	$.messager.progress('close');
							     	$.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroup_list.buildQueryParams();
										$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
										$('#dialog_holder').dialog('close');	
							     	}
							     }
						});// 
					    if($(add_form_id).form("validate")==true){
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
	tFansGroup_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTFansGroupFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝群编辑',
		    width: 800,
		    height: 500,
		    href: 'editTFansGroupPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTFansGroupFrom').form({   
						 url:'editTFansGroupAction.action',    
							     onSubmit: function(){
							      	 //校验粉丝群名称是否全部是空格
								     if('' == $.trim($("#edit_group_name").val())){
								     		$("#edit_group_name").val('');
								     		$("#edit_group_name").focus();
								     		return false;
								     }
							         $.messager.progress(); 	   
							     },    
							     success:function(data){
									$.messager.progress('close');
							     	$.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroup_list.buildQueryParams();
										$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
										$('#dialog_holder').dialog('close');	
							     	}
							     }
						});// 
					    if($(edit_form_id).form("validate")==true){
							$(edit_form_id).submit();
						};   
		    		
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     			$('#delTFansGroupFrom').form({   
						 url:'delTFansGroupAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
							        $.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroup_list.buildQueryParams();
										$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
										$('#dialog_holder').dialog('close');	
							     	}
							     }
						});//
						$.messager.confirm("提示信息","删除该分组同样将删除该组下所有成员信息！",function(flg){ 
								if(flg){
										$('#delTFansGroupFrom').submit();
								} 
					  	});
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
	tFansGroup_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝群详细',
		    width: 800,
		    height: 500,
		    href: 'detailTFansGroupPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "编  辑",
		    	handler: function(e){
		    		$('#dialog_holder').dialog('close');
		    		tFansGroup_list.updateFormSubmit(pkid);
		    	}
		    },{
		    	text: "删  除",
		    	handler: function(e){
		    		$('#delTFansGroupFrom').form({   
						 url:'delTFansGroupAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
							        $.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroup_list.buildQueryParams();
										$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
										$('#dialog_holder').dialog('close');	
							     	}
							     }
						}); 
						$.messager.confirm("提示信息","删除该分组同样将删除该组下所有成员信息！",function(flg){
									if (flg){
										 $('#delTFansGroupFrom').submit();
									}
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
	
	//删除
	tFansGroup_list.delFormSubmit = function (pkid){
		$.messager.confirm("提示信息","删除该分组同样将删除该组下所有成员信息！",function(flg){
			if(flg){
				$.post('delTFansGroupAction.action',{pkid:pkid},function (data){
								$.messager.alert("提示信息",JSON.parse(data).message,"info");
								if (JSON.parse(data).success){
										$('div#div_tFansGroup_list #dg_tFansGroup').datagrid('load');
										$('#dialog_holder').dialog('close');
								}
				});
			}
		});
	}
	
	//成员管理
	tFansGroup_list.tofansList = function(group_id,group_name){
		$('<div id="dialog_holder"></div>').dialog({
		    title: group_name+'粉丝群成员管理',
		    width: 1000,
		    height: 585,
		    href: 'searchTFansGroupMemberRealAction.action',//查询成员管的的Action
		    modal: true,
		    method: "POST",
		    params:{tfansgroup_pkid:group_id},
			onClose: function(){
				$(this).dialog("destroy");
			},
		});
	}
	
	tFansGroup_list.alertremark = function (remark){
		$.messager.alert("群说明",'<div style="word-wrap: break-word; word-break: normal;text-indent: 2em; ">'+remark+'</div>',"");
	}
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>