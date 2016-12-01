<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<title>图片列表</title>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_imagesRes_list" class="container">
	<header class="page-title">
		<h1>图片列表</h1>
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
									<th class="wd-10"><label>图片名称:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="imgName"  name="imgName" style="width:150px;"/>
									</td>
									<th class="wd-10"><label>图片描述:</label></th>
									<td >
										<input type="text" class="easyui-validatebox" id="imgMi"  name="imgMi" style="width:150px;"/>
									</td>
									<th class="wd-10"><label>创建人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="founder" name="founder"/>
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>更新人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="updatePer"  name="updatePer" style="width:150px;"/>
									</td>
									<th class="wd-10"><label>最后更新时间:</label></th>
									<td colspan="3">
										<input type="text" class="easyui-datebox" id="updateDate1"  name="updateDate1" style="width:150px;"/>&nbsp;~&nbsp; 
										<input type="text" class="easyui-datebox" id="updateDate2"  name="updateDate2" style="width:150px;"/>
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
				<table  id="dg_student" class="easyui-datagrid" title="查询结果" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,pagination:'true',url:'',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'DETAIL1',width:50,align:'center'">预览</th>
							<th data-options="field:'EDIT1',width:50,align:'center'">编辑</th>
							<th data-options="field:'EDIT2',width:50,align:'center'">删除</th>
							<th data-options="field:'STUDENT_ID1',width:200,sortable:'true',align:'center'">图片名称</th>
							<th data-options="field:'STNAME1',width:450,sortable:'true',align:'center'">图片描述</th>
							<th data-options="field:'STNO1',width:150,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'AGE1',width:150,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'BIRTHDAY1',width:200,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
						<tr>
							<td><a href="javascript:void(0);" onclick="searImager_list.detailImager()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searImager_list.editImages()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searImager_list.removeImgForm()"><i class="icon-no"></i></a></td>
							<td>名称</td>
							<td>1fdsfasdfafd</td>
							<td>xx</td>
							<td>xx</td>
							<td>2014/8/5</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0);" onclick="searImager_list.detailImager()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searImager_list.editImages()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searImager_list.removeImgForm()"><i class="icon-no"></i></a></td>
							<td>名称</td>
							<td>1fdsfafdas</td>
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

var searImager_list = {};
jQuery(function($){


	//定义构造查询
	searImager_list.buildQueryParams=function(){
	    $('div#div_imagesRes_list #dg_student').datagrid({
			queryParams: {
					  'studentQueryBean.student_id':$("input[name='search_student_id']").val(),//学生主键
					  'studentQueryBean.stname':$("input[name='search_stname']").val(),		   //姓名
					  'studentQueryBean.stno': $("input[name='search_stno']").val(),		   //学号
					  'studentQueryBean.birthday': $("input[name='search_birthday']").val()    //生日
			}
		});
	};


    //定义构造查询JSON

   searImager_list.buildJsonQueryParams = function(){

		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_imagesRes_list #dg_student').datagrid('options').pageNumber,
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
	 };


	//重新按照条件刷新查询内容
	$('div#div_imagesRes_list #search_btn').click(function(){
		searImager_list.buildQueryParams();
		$('div#div_imagesRes_list #dg_student').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_imagesRes_list #clear_btn').click(function(){
		//清空查询条件
		$("input[name='search_student_id']").val("");//学生主键
	  	$("input[name='search_stname']").val("");//姓名
	    $("input[name='search_stno']").val("");//学号
		$("#search_birthday").datebox('setValue', '');//生日
		searImager_list.buildQueryParams();
		$('div#div_imagesRes_list #dg_student').datagrid('load');
	});
	
	
	//新增图片
	$('div#div_imagesRes_list #add_btn').click(function(){
		 var add_form_id ='#addImargesResFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图片资源新增',
		    width: 800,
		    height: 550,
		    href: 'addImagesRes.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    	 var add_form = $('#addImargesResFrom');
		    		$(add_form_id).form({   
						 url:'',    
							     onSubmit: function(){
							        // do some check  return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some page load while success 
							        $.messager.progress('close');
									searImager_list.buildQueryParams();
									$('div#div_imagesRes_list #dg_student').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});
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
	searImager_list.addImages=function(){
		alert(0);
	};
		//编辑图片资源
	searImager_list.editImages=function(pkid){
	    var edit_form_id ='#editImgGroupFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图片基本信息更新',
		    width: 800,
		    height: 550,
		    href: 'editImagesRes.action',
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
	};
	
	//预览图文组
	searImager_list.detailImager=function(){
	
		$("<div id='dialog_holder'></div>").dialog({
		    title: '图片预览',
		    width: 800,
		    height: 650,
		    href: 'detailImagesRes.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
			 buttons: [{
		    	text: "编辑",
		    	handler: function(e){
		    		//关闭详细页面
		   			$("#dialog_holder").dialog("close");
		   			//弹出编辑页面
		   			searImager_list.editImages();
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
	
	
	//删除图片
	searImager_list.removeImgForm=function(){
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
		
	};
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>