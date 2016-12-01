<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<%
	String pathUrl = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容    div_selectPlatForm_list -->
<div id="div_selectPlatForm_list">
	<article id="content" class="content" style="width:98%">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="search-panel-content">
					<form id="searchForm" name="searchForm" method="post">
						<input type="hidden" id="platform_id" name="platform_id" value="${platform_id}"  />
                        </form>
				</div>
			</div>
			<div class="result-content">
				<table id="dg_select_tPlatForm" style="width:auto;height:250px">
					<thead>
						<tr>
							<th data-options="field:'USER_CD',hidden:true">员工CD</th>
							<th data-options="field:'chk',checkbox:true">USER_CD</th>
							<th data-options="field:'USER_NAME',width:200,sortable:'true',align:'center'">用户名</th>
							<th data-options="field:'MOBILE',width:200,sortable:'true',align:'center'">手机</th>
							<th data-options="field:'CREATED_DATE',width:190,sortable:'true',align:'center'">创建日期</th>
							<th data-options="field:'CREATED_USER_CD',width:190,sortable:'true',align:'center'">创建人</th>

						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tSelectUser_list = {};
jQuery(function($){

	//定义构造查询
	tSelectUser_list.buildQueryParams=function(){
	    $('div#div_selectPlatForm_list #dg_select_tPlatForm').datagrid('options')
			.queryParams = {
				    'tPlatformempCfgQueryBean.user_id':$("div#div_selectPlatForm_list #user_id").val(),
			};
	};
	//重新按照条件刷新查询内容
	$('div#div_selectPlatForm_list #search_btn').click(function(){
		tSelectUser_list.buildQueryParams();
		$('div#div_selectPlatForm_list #dg_select_tPlatForm').datagrid('reload');
	});
	
    //重置查询条件并刷新内容
	$('div#div_selectPlatForm_list #clear_btn').click(function(){
		//清空查询条件
			$("div#div_selectPlatForm_list input[name='search_user_cd']").val("");
			$("div#div_selectPlatForm_list input[name='search_user_name']").val("");
			$("div#div_selectPlatForm_list input[name='search_mobile']").val("");
			$("div#div_selectPlatForm_list input[name='search_email']").val("");
			tSelectRole_list.buildQueryParams();
			$('div#div_selectPlatForm_list #dg_select_tPlatForm').datagrid('reload');
	});
	

	var singleSelect = "${singleSelect}";
	if(singleSelect == '')
	{
		singleSelect = false;
	}
	$("div#div_selectPlatForm_list #dg_select_tPlatForm").datagrid({
		sortName:'CREATED_DATE',
		sortOrder:'desc',
		pagination:'true',
		singleSelect:singleSelect,
		//url:'<%=pathUrl%>/selectTPlatformempAction!getListData.action',
		url:'<%=pathUrl%>/selectTEmpUserAction!getListData.action',
		method:'post',
		queryParams: {
			platform_id:$("div#div_selectPlatForm_list input[name='platform_id']").val()
		}
	});
	
	
});

</script>