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
<!--内容-->
<div id="div_selectUser_list">
	<article id="content" class="content" style="width:98%">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				
				<div class="search-panel-content">
					<form id="searchForm" name="searchForm" method="post">
						<input type="hidden" id="role_id" name="role_id" value="${tEmpUserQueryBean.role_id}"  />
                            <div class="search-panel-bd">
                                <table class="search-table">
                                    <tr>
                                        <th class="wd-20">
                                            <label>员工账户</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.user_cd" name="search_user_cd" value="${tEmpUserDto.user_cd}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                        <th class="wd-20">
                                            <label>员工名称</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.user_name" name="search_user_name" value="${tEmpUserDto.user_name}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="wd-20">
                                            <label>手机号码</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.mobile" name="search_mobile" value="${tEmpUserDto.mobile}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                        <th class="wd-20">
                                            <label>电子邮箱</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.email" name="search_email" value="${tEmpUserDto.email}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="search-btn-area">
                                <input id="search_btn" type="button"  value="查 询" />
                                <input id="clear_btn" type="button"  value="清 除" />
                            </div>
                        </form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table id="dg_select_tUser" style="width:auto;height:250px">
					<thead>
						<tr>
							<th data-options="field:'USER_ID',hidden:true">role_id</th>
							<th data-options="field:'chk',checkbox:true">role_id</th>
							<th data-options="field:'USER_CD',width:190,sortable:'true',align:'center'">员工账户</th>
							<th data-options="field:'USER_NAME',width:200,sortable:'true',align:'center'">员工名称</th>
							<th data-options="field:'MOBILE',width:200,sortable:'true',align:'center'">手机</th>
							<th data-options="field:'EMAIL',width:200,sortable:'true',align:'center'">邮箱</th>
							<th data-options="field:'LOCK_FLAG',width:200,sortable:'true',align:'center'">锁定状态</th>
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
	    $('div#div_selectUser_list #dg_select_tUser').datagrid('options')
			.queryParams = {
				    'tEmpUserQueryBean.user_id':$("div#div_selectUser_list input[name='search_user_id']").val(),
					'tEmpUserQueryBean.user_cd':$("div#div_selectUser_list input[name='search_user_cd']").val(),
					'tEmpUserQueryBean.user_name':$("div#div_selectUser_list input[name='search_user_name']").val(),
					'tEmpUserQueryBean.password':$("div#div_selectUser_list input[name='search_password']").val(),
					'tEmpUserQueryBean.mobile':$("div#div_selectUser_list input[name='search_mobile']").val(),
					'tEmpUserQueryBean.email':$("div#div_selectUser_list input[name='search_email']").val(),
					'tEmpUserQueryBean.lock_flag':$("div#div_selectUser_list input[name='search_lock_flag']").val(),
					'tEmpUserQueryBean.created_date':$("div#div_selectUser_list input[name='search_created_date']").val(),
					'tEmpUserQueryBean.created_user_cd':$("div#div_selectUser_list input[name='search_created_user_cd']").val(),
					'tEmpUserQueryBean.role_id':$("div#div_selectUser_list input[name='role_id']").val()
			};
	};



	//重新按照条件刷新查询内容
	$('div#div_selectUser_list #search_btn').click(function(){
		tSelectUser_list.buildQueryParams();
		$('div#div_selectUser_list #dg_select_tUser').datagrid('reload');
	});
	
    //重置查询条件并刷新内容
	$('div#div_selectUser_list #clear_btn').click(function(){
		//清空查询条件
			$("div#div_selectUser_list input[name='search_user_cd']").val("");
			$("div#div_selectUser_list input[name='search_user_name']").val("");
			$("div#div_selectUser_list input[name='search_mobile']").val("");
			$("div#div_selectUser_list input[name='search_email']").val("");
			tSelectRole_list.buildQueryParams();
			$('div#div_selectUser_list #dg_select_tUser').datagrid('reload');
	});
	
	var singleSelect = "${singleSelect}";
	if(singleSelect == '')
	{
		singleSelect = false;
	}
	$("div#div_selectUser_list #dg_select_tUser").datagrid({
		sortName:'CREATED_DATE',
		sortOrder:'desc',
		pagination:'true',
		singleSelect:singleSelect,
		url:'<%=pathUrl%>/selectTEmpUserAction!getListData.action',
		method:'post',
		queryParams: {
			'tEmpUserQueryBean.role_id':$("div#div_selectUser_list input[name='role_id']").val()
		}
	});
	
	
});

</script>