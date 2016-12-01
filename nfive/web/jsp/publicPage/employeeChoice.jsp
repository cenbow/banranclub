<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<div id="div_add_student"  class="content-body">
	<!--搜索栏开始-->
	<div class="search-panel toggle-panel">
				<div class="search-panel-content">
					<form id="searchForm" name="searchForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
								<tr>
									<th class="wd-20"><label>编号:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="empNum"  name="empNum" style="width:150px;"/>
									</td>
									<th class="wd-20"><label>姓名:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="empName"  name="empName" style="width:150px;"/>
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
	
		<div class="result-content">
				<table  id="dg_student" class="easyui-datagrid" title="查询结果" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,pagination:'true',url:'',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'DETAIL1',width:50,align:'center'">选择</th>
							<th data-options="field:'EDIT1',width:150,align:'center'">编号</th>
							<th data-options="field:'EDIT2',width:190,align:'center'">姓名</th>
							<th data-options="field:'STUDENT_ID1',width:200,sortable:'true',align:'center'">电子邮件</th>
							<th data-options="field:'STNAME1',width:150,sortable:'true',align:'center'">手机</th>
						</tr>
					</thead>
						<tr>
							<td><input type="radio" name="radion" id="radion1"/></td>
							<td>1</td>
							<td>xxx</td>
							<td>163</td>
							<td>1231231</td>
						</tr>
						<tr>
							<td><input type="radio" name="radion" id="radion1"/></td>
							<td>2</td>
							<td>xxx</td>
							<td>162</td>
							<td>123231</td>
						</tr>
				</table>
			</div>
</div>
<script type="text/javascript">
//图文组类型选择效果
function aa(){
	var va=$("#imgtType").combobox("getValue");
	if(va==0||va==1){
		$("#isTrDis").hide();
	}else if(va==2){
		$("#isTrDis").show();
	}
}
</script>
