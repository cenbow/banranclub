<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>最新粉丝同步</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tLatestFans_list" class="container">
	<header class="page-title">
		<h1>有效关注粉丝一览</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="sync_btn" class="easyui-linkbutton" onclick="tLatestFans_list.sync();"><i class="icon-add"></i>已关注粉丝同步</a></li>
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
										<th class="wd-10"><label>昵称</label></th>
										<td class="wd-20">
											<input type="text" id="search_nick_name" name="search_nick_name" style="width:200px;" data-options="required:true" placeholder="模糊查询" />
										</td>
										<th class="wd-10"><label>OPENID</label></th>
										<td class="wd-20">
											<input type="text" id="search_openid" name="search_openid" style="width:200px;" data-options="required:true" placeholder="模糊查询" />
										</td>
										<th class="wd-10"><label>备注名</label></th>
										<td class="wd-30">
											<input type="text" id="search_remark_name" name="search_remark_name" style="width:200px;" data-options="required:true" placeholder="模糊查询" />
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
				<table  id="dg_tLatestFans" class="easyui-datagrid" title="有效关注粉丝一览" style="width:auto;height:360px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'CREATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTLatestFansAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<!-- <th data-options="field:'DETAIL',width:50,align:'center'">详细</th> -->
							<th data-options="field:'OPENID',width:200,sortable:'true',align:'center'">OPENID</th>
							<th data-options="field:'HEAD_IMG_URL',width:80,sortable:'true',align:'center'">头像</th>
							<th data-options="field:'NICK_NAME',width:80,sortable:'true',align:'center'">昵称</th>
							<th data-options="field:'SEX',width:80,sortable:'true',align:'center'">性别</th>
							<th data-options="field:'REMARK_NAME',width:120,sortable:'true',align:'center'">备注名</th>
							<th data-options="field:'GROUP_NAME',width:120,sortable:'true',align:'center'">微信组</th>
							<th data-options="field:'COUNTRY',width:120,sortable:'true',align:'center'">国家</th>
							<th data-options="field:'PROVINCE',width:120,sortable:'true',align:'center'">省份</th>
							<th data-options="field:'CITY',width:120,sortable:'true',align:'center'">城市</th>
							<th data-options="field:'CREATED_DATE',width:160,sortable:'true',align:'center'">更新时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>
<input type="hidden" id="total" value="" />
<script>
var tLatestFans_list ={};
jQuery(function($){
	//定义构造查询
	tLatestFans_list.buildQueryParams=function(){
	     $('div#div_tLatestFans_list #dg_tLatestFans').datagrid("options")
			.queryParams = {
					'tLatestFansQueryBean.nick_name':$("input[name='search_nick_name']").val(),
					'tLatestFansQueryBean.openid':$("input[name='search_openid']").val(),
					'tLatestFansQueryBean.remark_name':$("input[name='search_remark_name']").val()
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tLatestFans_list #search_btn').click(function(){
		tLatestFans_list.buildQueryParams();
		$('div#div_tLatestFans_list #dg_tLatestFans').datagrid('load');
	});

    //重置查询条件并刷新内容
	$('div#div_tLatestFans_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_nick_name']").val("");
			$("input[name='search_openid']").val("");
			$("input[name='search_remark_name']").val("");
		tLatestFans_list.buildQueryParams();
		$('div#div_tLatestFans_list #dg_tLatestFans').datagrid('load');
	});


    //开始同步
	tLatestFans_list.sync=function(){
	      $('#sync_btn').linkbutton('disable');
	      $.post("addTLatestFansAction.action",function(data){
	          data = eval("("+data+")")
	          //console.log(data);
	          if(data.status){
		          	//$.messager.alert("提示信息",data.total+"条数据正在同步","info")

		          	$.messager.show({
						title:'提示信息',
						msg: data.total+"条数据正在同步",
						timeout:3000,
						showType:'slide'
					});
					$("#total").val(data.total);
		          	snycfans();
		          	//setInterval("snycfans()",1000*60*1);
	          }else{
	          		$.messager.alert("提示信息",data.message,"info")
	          }
	      });
	 }

	//详细
	tLatestFans_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TLatestFans',
		    width: 800,
		    height: 500,
		    href: 'detailTLatestFansPage.action',
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
	function snycfans(){
       //console.log(2)
       $('div#div_tLatestFans_list #dg_tLatestFans').datagrid('load');
       var currentTotal = $('div#div_tLatestFans_list #dg_tLatestFans').datagrid('getData').total;
       var total = $("#total").val();

       if(currentTotal == total){
       		//window.clearInterval();
       		$('#sync_btn').linkbutton('enable');
       		$.messager.alert("提示信息","数据同步完成","info")
       }else{
       		setTimeout("snycfans()",1000);
       }
    }
</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>