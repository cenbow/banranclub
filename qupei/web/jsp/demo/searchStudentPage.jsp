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
<div id="div_student_list" class="container">
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
										<th class="wd-20"><label>stname</label></th>
										<td>
											<input type="text" id="studentDto.stname" name="search_stname"  value="${studentDto.stname}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>stno</label></th>
										<td>
											<input type="text" id="studentDto.stno" name="search_stno"  value="${studentDto.stno}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>age</label></th>
										<td>
											<input type="text" id="studentDto.age" name="search_age"  value="${studentDto.age}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									<tr>
										<th class="wd-20"><label>birthday</label></th>
										<td>
											<input type="text" id="studentDto.birthday" name="search_birthday"  value="${studentDto.birthday}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />
										</td>
									 </tr>
									 <tr>
										<th class="wd-20"><label>money</label></th>
										<td>
											<input type="text" id="studentDto.money" name="search_money"  value="${studentDto.money}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>txt</label></th>
										<td>
											<input type="text" id="studentDto.txt" name="search_txt"  value="${studentDto.txt}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									<tr>
										<th class="wd-20"><label>inschool</label></th>
										<td>
											<input type="text" id="studentDto.inschool" name="search_inschool"  value="${studentDto.inschool}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />
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
				<table  id="dg_student" class="easyui-datagrid" title="DataGrid Title" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchStudentAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">detail</th>
							<th data-options="field:'EDIT',width:50,align:'center'">edit</th>
							<th data-options="field:'STUDENT_ID',width:120,sortable:'true',align:'right'">student_id</th>
							<th data-options="field:'STNAME',width:120,sortable:'true',align:'right'">stname</th>
							<th data-options="field:'STNO',width:120,sortable:'true',align:'right'">stno</th>
							<th data-options="field:'AGE',width:120,sortable:'true',align:'right'">age</th>
							<th data-options="field:'BIRTHDAY',width:120,sortable:'true',align:'right'">birthday</th>
							<th data-options="field:'MONEY',width:120,sortable:'true',align:'right'">money</th>
							<th data-options="field:'TXT',width:120,sortable:'true',align:'right'">txt</th>
							<th data-options="field:'INSCHOOL',width:120,sortable:'true',align:'right'">inschool</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var student_list ={};
jQuery(function($){
	//定义构造查询
	student_list.buildQueryParams=function(){
	     $('div#div_student_list #dg_student').datagrid("options")
			.queryParams = {
					'studentQueryBean.student_id':$("input[name='search_student_id']").val(),
					'studentQueryBean.stname':$("input[name='search_stname']").val(),
					'studentQueryBean.stno':$("input[name='search_stno']").val(),
					'studentQueryBean.age':$("input[name='search_age']").val(),
					'studentQueryBean.birthday':$("input[name='search_birthday']").val(),
					'studentQueryBean.money':$("input[name='search_money']").val(),
					'studentQueryBean.txt':$("input[name='search_txt']").val(),
					'studentQueryBean.inschool':$("input[name='search_inschool']").val(),
			};
	};


    //定义构造查询JSON
    student_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_student_list #dg_student').datagrid('options').pageNumber,
		  		//页面查询框部分
					student_id :$("input[name='search_student_id']").val(), 
					stname :$("input[name='search_stname']").val(), 
					stno :$("input[name='search_stno']").val(), 
					age :$("input[name='search_age']").val(), 
					birthday :$("input[name='search_birthday']").val(), 
					money :$("input[name='search_money']").val(), 
					txt :$("input[name='search_txt']").val(), 
					inschool :$("input[name='search_inschool']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_student_list #search_btn').click(function(){
		student_list.buildQueryParams();
		$('div#div_student_list #dg_student').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_student_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_student_id']").val("");
			$("input[name='search_stname']").val("");
			$("input[name='search_stno']").val("");
			$("input[name='search_age']").val("");
			$("input[name='search_birthday']").val("");
			$("input[name='search_money']").val("");
			$("input[name='search_txt']").val("");
			$("input[name='search_inschool']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		student_list.buildQueryParams();
		$('div#div_student_list #dg_student').datagrid('load');
	});
	
	
	//新增
	$('div#div_student_list #add_btn').click(function(){
		var add_form_id ='#addStudentFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'STUDENT',
		    width: 800,
		    height: 500,
		    href: 'addStudentPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addStudentFrom').form({   
						 url:'addStudentAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									student_list.buildQueryParams();
									$('div#div_student_list #dg_student').datagrid('load');
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
	student_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editStudentFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'Student',
		    width: 800,
		    height: 500,
		    href: 'editStudentPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editStudentFrom').form({   
						 url:'editStudentAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									student_list.buildQueryParams();
									$('div#div_student_list #dg_student').datagrid('load');
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
		     			$('#delStudentFrom').form({   
						 url:'delStudentAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									student_list.buildQueryParams();
									$('div#div_student_list #dg_student').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    $('#delStudentFrom').submit();
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
	student_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细Student',
		    width: 800,
		    height: 500,
		    href: 'detailStudentPage.action',
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