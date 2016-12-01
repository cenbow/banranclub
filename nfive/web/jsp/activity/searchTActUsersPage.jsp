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
<div id="div_tActUsers_list" class="container">
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
										<th class="wd-20"><label>openid</label></th>
										<td>
											<input type="text" id="tActUsersDto.openid" name="search_openid"  value="${tActUsersDto.openid}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>nickname</label></th>
										<td>
											<input type="text" id="tActUsersDto.nickname" name="search_nickname"  value="${tActUsersDto.nickname}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>subscribe</label></th>
										<td>
											<input type="text" id="tActUsersDto.subscribe" name="search_subscribe"  value="${tActUsersDto.subscribe}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>sex</label></th>
										<td>
											<input type="text" id="tActUsersDto.sex" name="search_sex"  value="${tActUsersDto.sex}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>language</label></th>
										<td>
											<input type="text" id="tActUsersDto.language" name="search_language"  value="${tActUsersDto.language}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>city</label></th>
										<td>
											<input type="text" id="tActUsersDto.city" name="search_city"  value="${tActUsersDto.city}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>province</label></th>
										<td>
											<input type="text" id="tActUsersDto.province" name="search_province"  value="${tActUsersDto.province}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>country</label></th>
										<td>
											<input type="text" id="tActUsersDto.country" name="search_country"  value="${tActUsersDto.country}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>headimgurl</label></th>
										<td>
											<input type="text" id="tActUsersDto.headimgurl" name="search_headimgurl"  value="${tActUsersDto.headimgurl}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									<tr>
										<th class="wd-20"><label>subscribe_time</label></th>
										<td>
											<input type="text" id="tActUsersDto.subscribe_time" name="search_subscribe_time"  value="${tActUsersDto.subscribe_time}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />
										</td>
									 </tr>
									 <tr>
										<th class="wd-20"><label>unionid</label></th>
										<td>
											<input type="text" id="tActUsersDto.unionid" name="search_unionid"  value="${tActUsersDto.unionid}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>remark</label></th>
										<td>
											<input type="text" id="tActUsersDto.remark" name="search_remark"  value="${tActUsersDto.remark}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>address</label></th>
										<td>
											<input type="text" id="tActUsersDto.address" name="search_address"  value="${tActUsersDto.address}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>mobile</label></th>
										<td>
											<input type="text" id="tActUsersDto.mobile" name="search_mobile"  value="${tActUsersDto.mobile}" style="width:200px;" data-options="required:true" />
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
				<table  id="dg_tActUsers" class="easyui-datagrid" title="DataGrid Title" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTActUsersAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">detail</th>
							<th data-options="field:'EDIT',width:50,align:'center'">edit</th>
							<th data-options="field:'USER_ID',width:120,sortable:'true',align:'right'">user_id</th>
							<th data-options="field:'OPENID',width:120,sortable:'true',align:'right'">openid</th>
							<th data-options="field:'NICKNAME',width:120,sortable:'true',align:'right'">nickname</th>
							<th data-options="field:'SUBSCRIBE',width:120,sortable:'true',align:'right'">subscribe</th>
							<th data-options="field:'SEX',width:120,sortable:'true',align:'right'">sex</th>
							<th data-options="field:'LANGUAGE',width:120,sortable:'true',align:'right'">language</th>
							<th data-options="field:'CITY',width:120,sortable:'true',align:'right'">city</th>
							<th data-options="field:'PROVINCE',width:120,sortable:'true',align:'right'">province</th>
							<th data-options="field:'COUNTRY',width:120,sortable:'true',align:'right'">country</th>
							<th data-options="field:'HEADIMGURL',width:120,sortable:'true',align:'right'">headimgurl</th>
							<th data-options="field:'SUBSCRIBE_TIME',width:120,sortable:'true',align:'right'">subscribe_time</th>
							<th data-options="field:'UNIONID',width:120,sortable:'true',align:'right'">unionid</th>
							<th data-options="field:'REMARK',width:120,sortable:'true',align:'right'">remark</th>
							<th data-options="field:'ADDRESS',width:120,sortable:'true',align:'right'">address</th>
							<th data-options="field:'MOBILE',width:120,sortable:'true',align:'right'">mobile</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tActUsers_list ={};
jQuery(function($){
	//定义构造查询
	tActUsers_list.buildQueryParams=function(){
	     $('div#div_tActUsers_list #dg_tActUsers').datagrid("options")
			.queryParams = {
					'tActUsersQueryBean.user_id':$("input[name='search_user_id']").val(),
					'tActUsersQueryBean.openid':$("input[name='search_openid']").val(),
					'tActUsersQueryBean.nickname':$("input[name='search_nickname']").val(),
					'tActUsersQueryBean.subscribe':$("input[name='search_subscribe']").val(),
					'tActUsersQueryBean.sex':$("input[name='search_sex']").val(),
					'tActUsersQueryBean.language':$("input[name='search_language']").val(),
					'tActUsersQueryBean.city':$("input[name='search_city']").val(),
					'tActUsersQueryBean.province':$("input[name='search_province']").val(),
					'tActUsersQueryBean.country':$("input[name='search_country']").val(),
					'tActUsersQueryBean.headimgurl':$("input[name='search_headimgurl']").val(),
					'tActUsersQueryBean.subscribe_time':$("input[name='search_subscribe_time']").val(),
					'tActUsersQueryBean.unionid':$("input[name='search_unionid']").val(),
					'tActUsersQueryBean.remark':$("input[name='search_remark']").val(),
					'tActUsersQueryBean.address':$("input[name='search_address']").val(),
					'tActUsersQueryBean.mobile':$("input[name='search_mobile']").val(),
			};
	};


    //定义构造查询JSON
    tActUsers_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tActUsers_list #dg_tActUsers').datagrid('options').pageNumber,
		  		//页面查询框部分
					user_id :$("input[name='search_user_id']").val(),
					openid :$("input[name='search_openid']").val(),
					nickname :$("input[name='search_nickname']").val(),
					subscribe :$("input[name='search_subscribe']").val(),
					sex :$("input[name='search_sex']").val(),
					language :$("input[name='search_language']").val(),
					city :$("input[name='search_city']").val(),
					province :$("input[name='search_province']").val(),
					country :$("input[name='search_country']").val(),
					headimgurl :$("input[name='search_headimgurl']").val(),
					subscribe_time :$("input[name='search_subscribe_time']").val(),
					unionid :$("input[name='search_unionid']").val(),
					remark :$("input[name='search_remark']").val(),
					address :$("input[name='search_address']").val(),
					mobile :$("input[name='search_mobile']").val(),
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tActUsers_list #search_btn').click(function(){
		tActUsers_list.buildQueryParams();
		$('div#div_tActUsers_list #dg_tActUsers').datagrid('load');
	});

    //重置查询条件并刷新内容
	$('div#div_tActUsers_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_user_id']").val("");
			$("input[name='search_openid']").val("");
			$("input[name='search_nickname']").val("");
			$("input[name='search_subscribe']").val("");
			$("input[name='search_sex']").val("");
			$("input[name='search_language']").val("");
			$("input[name='search_city']").val("");
			$("input[name='search_province']").val("");
			$("input[name='search_country']").val("");
			$("input[name='search_headimgurl']").val("");
			$("input[name='search_subscribe_time']").val("");
			$("input[name='search_unionid']").val("");
			$("input[name='search_remark']").val("");
			$("input[name='search_address']").val("");
			$("input[name='search_mobile']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tActUsers_list.buildQueryParams();
		$('div#div_tActUsers_list #dg_tActUsers').datagrid('load');
	});


	//新增
	$('div#div_tActUsers_list #add_btn').click(function(){
		var add_form_id ='#addTActUsersFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'T_ACT_USERS',
		    width: 800,
		    height: 500,
		    href: 'addTActUsersPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTActUsersFrom').form({
						 url:'addTActUsersAction.action',
							     onSubmit: function(){
							       $.messager.progress();
							        // do some check
							        // return false to prevent submit;
							     },
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tActUsers_list.buildQueryParams();
									$('div#div_tActUsers_list #dg_tActUsers').datagrid('load');
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
	tActUsers_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTActUsersFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TActUsers',
		    width: 800,
		    height: 500,
		    href: 'editTActUsersPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTActUsersFrom').form({
						 url:'editTActUsersAction.action',
							     onSubmit: function(){
							       $.messager.progress();
							        // do some check
							        // return false to prevent submit;
							     },
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tActUsers_list.buildQueryParams();
									$('div#div_tActUsers_list #dg_tActUsers').datagrid('load');
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
		     			$('#delTActUsersFrom').form({
						 url:'delTActUsersAction.action',
							     onSubmit: function(){
							       $.messager.progress();
							        // do some check
							        // return false to prevent submit;
							     },
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tActUsers_list.buildQueryParams();
									$('div#div_tActUsers_list #dg_tActUsers').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});//
					    $('#delTActUsersFrom').submit();
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
	tActUsers_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TActUsers',
		    width: 800,
		    height: 500,
		    href: 'detailTActUsersPage.action',
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