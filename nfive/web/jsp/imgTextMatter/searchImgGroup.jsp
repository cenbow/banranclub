<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<title>图文组列表</title>
<script type="text/javascript" src="<%=jsPath%>/selectImg.js"></script>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_imggroup_list" class="container">
	<header class="page-title">
		<h1>图文组列表</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增图文组</a></li>
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
									<th class="wd-10"><label>图文组编号:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="imgGroupNum"  name="imgGroupNum" style="width:150px;"/>
									</td>
									<th class="wd-10"><label>图文组名称:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="imgGroupName"  name="imgGroupName" style="width:150px;"/>
									</td>
									<th class="wd-10"><label>图文组类型:</label></th>
									<td>
										<input type="radio"/>&nbsp;多图文 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio"/>&nbsp;单图文
										
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>启用标志:</label></th>
									<td>
										<input type="text" class="easyui-combobox" id="startMark"  name="startMark" data-options="
																				valueField: 'label',
																				textField: 'value',
																				data: [{
																					label: '未输入',
																					value: '未输入'
																				},{
																					label: '是',
																					value: '是'
																				},{
																					label: '否',
																					value: '否'
																				}]"/>
									</td>
									<th class="wd-10"><label> 图文组管理人</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="imgGroupPer"  name="imgGroupPer" style="width:150px;"/>
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
							<th data-options="field:'STUDENT_ID1',width:200,sortable:'true',align:'center'">图文组编号</th>
							<th data-options="field:'STNAME1',width:120,sortable:'true',align:'center'">图文组名称</th>
							<th data-options="field:'STNO1',width:120,sortable:'true',align:'center'">图文组类型</th>
							<th data-options="field:'AGE1',width:120,sortable:'true',align:'center'">启用标志</th>
							<th data-options="field:'BIRTHDAY1',width:140,sortable:'true',align:'center'">图文组管理人</th>
							<th data-options="field:'CREATED_DATE1',width:120,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_DATE1',width:130,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'CREATED_USER_CD1',width:200,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
						<tr>
							<td><a href="javascript:void(0);" onclick="searZImgGroup_list.previewImgGroup()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searZImgGroup_list.updateFormSubmit()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searZImgGroup_list.removeFormSubmit()"><i class="icon-no"></i></a></td>
							<td>1</td>
							<td>名称</td>
							<td>1</td>
							<td>a</td>
							<td>xx</td>
							<td>xx</td>
							<td>xx</td>
							<td>2014/8/5</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0);" onclick="searZImgGroup_list.previewImgGroup()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searZImgGroup_list.updateFormSubmit();"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="searZImgGroup_list.removeFormSubmit()"><i class="icon-no"></i></a></td>
							<td>1</td>
							<td>名称</td>
							<td>1</td>
							<td>a</td>
							<td>xx</td>
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
var searZImgGroup_list = {};
jQuery(function($){


	//定义构造查询
	searZImgGroup_list.buildQueryParams=function(){
	    $('div#div_imggroup_list #dg_student').datagrid({
			queryParams: {
					  'studentQueryBean.student_id':$("input[name='search_student_id']").val(),//学生主键
					  'studentQueryBean.stname':$("input[name='search_stname']").val(),		   //姓名
					  'studentQueryBean.stno': $("input[name='search_stno']").val(),		   //学号
					  'studentQueryBean.birthday': $("input[name='search_birthday']").val()    //生日
			}
		});
	};


    //定义构造查询JSON

   searZImgGroup_list.buildJsonQueryParams = function(){

		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_imggroup_list #dg_student').datagrid('options').pageNumber,
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
	$('div#div_imggroup_list #search_btn').click(function(){
		searZImgGroup_list.buildQueryParams();
		$('div#div_imggroup_list #dg_student').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_imggroup_list #clear_btn').click(function(){
		//清空查询条件
		$("input[name='search_student_id']").val("");//学生主键
	  	$("input[name='search_stname']").val("");//姓名
	    $("input[name='search_stno']").val("");//学号
		$("#search_birthday").datebox('setValue', '');//生日
		searZImgGroup_list.buildQueryParams();
		$('div#div_imggroup_list #dg_student').datagrid('load');
	});
	
	
	//新增图文组
	$('div#div_imggroup_list #add_btn').click(function(){
	    var add_form_id ='#addImgGroupFrom';
		$("<div id='dialog_holder'></div>").dialog({
		    title: '图文组基本信息新增',
		    width: 800,
		    height: 300,
		    href: 'addImgGroupPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    	 var add_form = $('#addImgGroupFrom');
		    		$(add_form_id).form({   
						 url:'',    
							     onSubmit: function(){
							        // do some check  return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some page load while success 
							        $.messager.progress('close');
									searZImgGroup_list.buildQueryParams();
									$('div#div_imggroup_list #dg_student').datagrid('load');
									$('#dialog_holder').dialog('close');
									//保存成功后跳到图文组详细信息页面
									window.location= 'detailMessageImgGroupPage.action' ; 
									// window.open('detailMessageImgGroup.action') ;
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
	//编辑图文组
	searZImgGroup_list.updateFormSubmit=function(pkid){
		//跳到图文组详细编辑页面
		window.location='detailMessageImgGroupPage.action';
	};
	
	//预览图文组
	searZImgGroup_list.previewImgGroup=function(){
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
	};
	
	
	//删除图文组
	searZImgGroup_list.removeFormSubmit=function(){
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