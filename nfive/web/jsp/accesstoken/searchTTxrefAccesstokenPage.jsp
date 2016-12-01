<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>ACCESSTOKEN列表页</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tTxrefAccesstoken_list" class="container">
	<header class="page-title">
		<h1>ACCESSTOKEN列表页</h1>
	</header>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tTxrefAccesstoken" class="easyui-datagrid" title="列表页" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTTxrefAccesstokenAction!getListData.action',method:'post'">
					<thead>
						<tr align="center">
							<th data-options="field:'RESET',width:120,align:'center'">重置ACCESSTOKEN</th>
							<th data-options="field:'PLATFORM_ID',width:180,sortable:'true',align:'center'">公众号ID</th>
							<th data-options="field:'ACCESSTOKEN',width:600,sortable:'true',align:'center'">数据库中ACCESSTOKEN</th>
							  <th data-options="field:'IS_EQUALS',width:150,sortable:'true',align:'center'">是否和缓存中保持一致</th>
							<th data-options="field:'TX_CREATED_TIME',width:130,sortable:'true',align:'center'">获取时间</th>
							<th data-options="field:'TX_EXPIRATION_TIME',width:130,sortable:'true',align:'center'">失效时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
	var tTxrefAccesstoken_list ={};
	jQuery(function($){
	
	});

    tTxrefAccesstoken_list.accessTokenFormSubmit = function( pkid ){
		$.messager.confirm("确认信息","您确定要重置当前公众号的ACCESSTOKEN吗？",function( ret ){
			if( ret ){
				$.post("refreshTTxrefAccesstoken.action",
				function(data){
					data = JSON.parse(data);
					if(data.status){
						$.messager.alert("提示信息","ACCESSTOKEN重置成功","info");
						$('div#div_tTxrefAccesstoken_list #dg_tTxrefAccesstoken').datagrid('load');
					}else{
						$.messager.alert("提示信息",data.message,"info");
					}
					}
				);
			}
		})
	}
</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>