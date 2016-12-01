<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>微信公众号管理</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tWxPlatform_list" class="container">
	<header class="page-title">
		<h1>微信公众号管理</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a></li>
            <li><a href="javascript:;" id="add_menu"><i class="icon-add"></i>初始化菜单</a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->

			<!--搜索栏结束-->

			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tWxPlatform" class="easyui-datagrid" title="微信公众号" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTWxPlatformAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'UPDATED_DATE',width:150,sortable:'true',align:'right'">更新时间</th>
							<th data-options="field:'PLATFORM_ACCOUNT',width:120,sortable:'true',align:'right'">公众号账号</th>
							<th data-options="field:'PLATFORM_NAME',width:100,sortable:'true',align:'right'">公众号名称</th>
							<th data-options="field:'PLATFORM_TYPE',width:100,sortable:'true',align:'right'">公众号类型</th>
							<th data-options="field:'APP_ID',width:150,sortable:'true',align:'right'">APPID</th>
							<th data-options="field:'APP_SECRET',width:250,sortable:'true',align:'right'">秘钥</th>
							<th data-options="field:'TOKEN',width:100,sortable:'true',align:'right'">口令</th>
                            <th data-options="field:'ACCESS_TOKEN',width:400,sortable:'true',align:'right'">ACCESS_TOKEN</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tWxPlatform_list ={};
jQuery(function($){
	//定义构造查询
	tWxPlatform_list.buildQueryParams=function(){
	     $('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid("options")
			.queryParams = {
					'tWxPlatformQueryBean.platform_id':$("input[name='search_platform_id']").val(),
					'tWxPlatformQueryBean.platform_account':$("input[name='search_platform_account']").val(),
					'tWxPlatformQueryBean.platform_name':$("input[name='search_platform_name']").val(),
					'tWxPlatformQueryBean.platform_type':$("input[name='search_platform_type']").val(),
					'tWxPlatformQueryBean.app_id':$("input[name='search_app_id']").val(),
					'tWxPlatformQueryBean.app_secret':$("input[name='search_app_secret']").val(),
					'tWxPlatformQueryBean.token':$("input[name='search_token']").val(),
			};
	};


    //定义构造查询JSON
    tWxPlatform_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid('options').pageNumber,
		  		//页面查询框部分
					platform_id :$("input[name='search_platform_id']").val(),
					platform_account :$("input[name='search_platform_account']").val(),
					platform_name :$("input[name='search_platform_name']").val(),
					platform_type :$("input[name='search_platform_type']").val(),
					app_id :$("input[name='search_app_id']").val(),
					app_secret :$("input[name='search_app_secret']").val(),
					token :$("input[name='search_token']").val(),
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tWxPlatform_list #search_btn').click(function(){
		tWxPlatform_list.buildQueryParams();
		$('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid('load');
	});

    //重置查询条件并刷新内容
	$('div#div_tWxPlatform_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_platform_id']").val("");
			$("input[name='search_platform_account']").val("");
			$("input[name='search_platform_name']").val("");
			$("input[name='search_platform_type']").val("");
			$("input[name='search_app_id']").val("");
			$("input[name='search_app_secret']").val("");
			$("input[name='search_token']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tWxPlatform_list.buildQueryParams();
		$('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid('load');
	});


	//新增
	$('div#div_tWxPlatform_list #add_btn').click(function(){
		var add_form_id ='#addTWxPlatformFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'T_WX_PLATFORM',
		    width: 800,
		    height: 500,
		    href: 'addTWxPlatformPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTWxPlatformFrom').form({
						 url:'addTWxPlatformAction.action',
							     onSubmit: function(){
							       $.messager.progress();
							        // do some check
							        // return false to prevent submit;
							     },
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tWxPlatform_list.buildQueryParams();
									$('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid('load');
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
	tWxPlatform_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTWxPlatformFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TWxPlatform',
		    width: 800,
		    height: 500,
		    href: 'editTWxPlatformPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTWxPlatformFrom').form({
						 url:'editTWxPlatformAction.action',
							     onSubmit: function(){
							       $.messager.progress();
							        // do some check
							        // return false to prevent submit;
							     },
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tWxPlatform_list.buildQueryParams();
									$('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid('load');
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
//		    ,{
//		    	text: "删  除",
//		     	handler: function(e){
//		     			$('#delTWxPlatformFrom').form({
//						 url:'delTWxPlatformAction.action',
//							     onSubmit: function(){
//							       $.messager.progress();
//							        // do some check
//							        // return false to prevent submit;
//							     },
//							     success:function(data){
//							       //do some
//							        $.messager.progress('close');
//									tWxPlatform_list.buildQueryParams();
//									$('div#div_tWxPlatform_list #dg_tWxPlatform').datagrid('load');
//									$('#dialog_holder').dialog('close');
//							     }
//						});//
//					    $('#delTWxPlatformFrom').submit();
//		    	}
//		     }
		    ,{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}

	//详细
	tWxPlatform_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TWxPlatform',
		    width: 800,
		    height: 500,
		    href: 'detailTWxPlatformPage.action',
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

    $('div#div_tWxPlatform_list #add_menu').click(function(){
        $.get("addTWxPlatformAction!createRootMenu.action?time=" + new Date().getTime(), {}, function (result) {
            if(!result.status){
                $.messager.alert("提示信息",result.message,"info")
            }
        },"json");
    });
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>