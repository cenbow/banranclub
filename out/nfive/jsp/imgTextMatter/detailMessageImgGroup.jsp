<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<title>图文组详细信息编辑</title>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_detimggroup_list" class="container">
	<header class="page-title">
		<h1>图文组详细信息编辑</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="back_btn"><i class="icon-back"></i></a></li>
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增图文项</a></li>
			<li><a href="javascript:;" id="tip_btn"><i class="icon-tip"></i>引用图文项</a></li>
			<li><a href="javascript:;" id="edit_btn"><i class="icon-edit"></i>编辑图文组基本信息</a></li>
			<li><a href="javascript:;" id="search_btn"><i class="icon-search"></i>预览图文组</a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">图文组详细信息</h2>
				</div>
				<div class="search-panel-content">
						<div class="search-panel-bd">
							<table class="search-table">
								<tr>
									<th class="wd-10"><label>图文组编号:</label></th>
									<td>
										S09384039393934
									</td>
									<th class="wd-10"><label>图文组名称:</label></th>
									<td>
										在售产品
									</td>
									<th class="wd-10"><label>图文组类型:</label></th>
									<td>
										xxx
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>启用标志:</label></th>
									<td>
										是
									</td>
									<th class="wd-10"><label>图文组管理人</label></th>
									<td>
										多图文
									</td>
									<th class="wd-10"><label>最大条目数:</label></th>
									<td>
										5
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>创建人:</label></th>
									<td>
										xxx
									</td>
									<th class="wd-10"><label>更新人:</label></th>
									<td>
										xxx
									</td>
									<th class="wd-10"><label>最后更新时间:</label></th>
									<td>
										2014-07-30:11:30
									</td>
								</tr>
							</table>
						</div>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_student" class="easyui-datagrid" title="图文组所包含图文项信息" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,pagination:'true',url:'',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'DETAIL1',width:50,align:'center'">预览</th>
							<th data-options="field:'EDIT1',width:50,align:'center'">编辑</th>
							<th data-options="field:'EDIT2',width:50,align:'center'">删除</th>
							<th data-options="field:'STUDENT_ID1',width:180,sortable:'true',align:'center'">标题</th>
							<th data-options="field:'STNAME1',width:100,sortable:'true',align:'center'">发布状态</th>
							<th data-options="field:'STNO1',width:200,sortable:'true',align:'center'">发布开始期间</th>
							<th data-options="field:'AGE1',width:200,sortable:'true',align:'center'">发布结束期间</th>
							<th data-options="field:'BIRTHDAY1',width:140,sortable:'true',align:'center'"> 创建人</th>
							<th data-options="field:'UPDATED_DATE1',width:130,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'CREATED_USER_CD1',width:200,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
						<tr>
							<td><a href="javascript:void(0);" onclick="detimggroup_list.detailImgItem()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="detimggroup_list.updateFormSubmit()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="detimggroup_list.removeFormSubmit()"><i class="icon-no"></i></a></td>
							<td>1</td>
							<td>1</td>
							<td>2014-07-28:09:00</td>
							<td>2014-07-28:10:00</td>
							<td>xx</td>
							<td>xx</td>
							<td>2014/8/5</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0);" onclick="detimggroup_list.detailImgItem()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="detimggroup_list.updateFormSubmit()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="detimggroup_list.removeFormSubmit()"><i class="icon-no"></i></a></td>
							<td>1</td>
							<td>1</td>
							<td>2014-08-05:09:00</td>
							<td>2014-08-05:10:00</td>
							<td>xx</td>
							<td>xx</td>
							<td>2014/8/5</td>
						</tr>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var detimggroup_list = {};
jQuery(function($){



	//定义构造查询
	detimggroup_list.buildQueryParams=function(){
	    $('div#div_detimggroup_list #dg_student').datagrid({
			queryParams: {
					  'studentQueryBean.student_id':$("input[name='search_student_id']").val(),//学生主键
					  'studentQueryBean.stname':$("input[name='search_stname']").val(),		   //姓名
					  'studentQueryBean.stno': $("input[name='search_stno']").val(),		   //学号
					  'studentQueryBean.birthday': $("input[name='search_birthday']").val()    //生日
			}
		});
	}


    //定义构造查询JSON

    detimggroup_list.buildJsonQueryParams = function(){

		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_detimggroup_list #dg_student').datagrid('options').pageNumber,
		  		//页面查询框部分
				student_id :$("input[name='search_student_id']").val(), //学生主键
				stname :$("input[name='search_stname']").val(), //姓名
				stno :$("input[name='search_stno']").val(), //学号
				birthday :$("input[name='search_birthday']").val(), //生日
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_detimggroup_list #search_btn').click(function(){
		detimggroup_list.buildQueryParams();
		$('div#div_detimggroup_list #dg_student').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_detimggroup_list #clear_btn').click(function(){
		//清空查询条件
		$("input[name='search_student_id']").val("");//学生主键
	  	$("input[name='search_stname']").val("");//姓名
	    $("input[name='search_stno']").val("");//学号
		$("#search_birthday").datebox('setValue', '');//生日
		detimggroup_list.buildQueryParams();
		$('div#div_detimggroup_list #dg_student').datagrid('load');
	});
	
	//返回按钮，返回 页面
	$("div#div_detimggroup_list #back_btn").click(function(){
		window.location='searchImgGroupPage.action';
	});
	
	//新增图文项
	$('div#div_detimggroup_list #add_btn').click(function(){
	   window.location='addImgItemPage.action';
	});
	
	//编辑图文项
	detimggroup_list.updateFormSubmit=function(){
		window.location='editImgItemPage.action';
	};
	
	//详细图文项
	detimggroup_list.detailImgItem=function(){
		$('<div id="dialog_holder"></div>').dialog({
			title:'图文项详细',
			width:1000,
			height:600,
			href:'detailImgItemPage.action',
			onClose:function(){
				$("#dialog_holder").dialog("destory");
			},
		    buttons: [{
		    	text: "编辑",
		    	handler: function(e){
		   			//关闭详细页面
		   			//$("#dialog_holder").dialog('close');
		   			//打开编辑页面 
		   			window.location='editImgItemPage.action';
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
		     			if(ret){
			     		    var del_form_id ='#delImgGroupFrom';
			     			$(del_form_id).form({   
							 url:'',    
								     onSubmit: function(){
								       $.messager.progress(); 
								        // do some check  return false to prevent submit;
								     },    
								     success:function(data){
								       //do some
								        $.messager.progress('close');
										detimggroup_list.buildQueryParams();
										$('div#div_detimggroup_list #dg_student').datagrid('load');
										$('#dialog_holder').dialog('close');
								     }
							});
						    $(del_form_id).submit();
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
	};
		
		
	//删除图文项
	detimggroup_list.removeFormSubmit=function(){
		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("", function(data){
                 				data = JSON.parse(data);
                 				if(data.isDel=='SUCCESS'){
				                		$('#dialog_holder').dialog('close');
										$('div#div_tCleDatClient_list #dg_tCleDatClient').datagrid('load');
					                }else{
					                   	$.messager.alert("提示信息",data.message,"error");
					                }
                       		});
                 		}
 			});
	}
	//引用图文项
	$("div#div_detimggroup_list #tip_btn").click(function(){
		$('<div id="dialog_holder"></div>').dialog({
			title:'图文项选择',
			width:1000,
			height:600,
			href:'quoteImgItemPage.action',
			modal:true,
			method:"POST",
			onClose:function(){
				$(this).dialog('destory');
			}
		});	
	});
	//预览图文组
	$("div#div_detimggroup_list #search_btn").click(function(){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图文组预览',
		    width: 500,
		    height: 550,
		    href: 'previewImgItemPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			}
		});
	});
	
	//编辑图文组
	$('div#div_detimggroup_list #edit_btn').click(function(pkid){
	    var edit_form_id ='#editImgGroupFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图文组基本信息更新',
		    width: 800,
		    height: 250,
		    href: 'editImgGroupPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$(edit_form_id).form({   
						 url:'',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check  return false to prevent submit; 
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									detimggroup_list.buildQueryParams();
									$('div#div_detimggroup_list #dg_student').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});
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
		     		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
		     			if(ret){
			     		    var del_form_id ='#delImgGroupFrom';
			     			$(del_form_id).form({   
							 url:'',    
								     onSubmit: function(){
								       $.messager.progress(); 
								        // do some check  return false to prevent submit;
								     },    
								     success:function(data){
								       //do some
								        $.messager.progress('close');
										detimggroup_list.buildQueryParams();
										$('div#div_detimggroup_list #dg_student').datagrid('load');
										$('#dialog_holder').dialog('close');
								     }
							});
						    $(del_form_id).submit();
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
	});
	
});
</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>