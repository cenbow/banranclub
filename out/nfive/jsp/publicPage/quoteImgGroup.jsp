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
									<th class="wd-20"><label>图文组编号:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="numGr"  name="numGr" style="width: 150px;"/>
									</td>
									<th class="wd-20"><label>图文组名称:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="nameGr" name="nameGr" style="width: 160px;"/>	
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>图文组类型:</label></th>
									<td>
										<input type="radio" name="typeGr" id="typeGr1"/>&nbsp;单图文
										<input type="radio" name="typeGr" id="typeGr2"/>&nbsp;多图文
									</td>
									<th class="wd-20"><label>图文组管理人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="per" id="per"  style="width: 150px;"/>
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>创建人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="creaper" id="creaper"  style="width:150px;"/>
									</td>
									<th class="wd-20"><label>更新人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="gePer" id="gePer"  style="width: 150px;" />
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>最后更新时间:</label></th>
									<td colspan="3">
										<input type="text" class="easyui-datetimebox" name="zuDa1" id="zuDa1" style="width: 150px;" />&nbsp;~&nbsp;
										<input type="text" class="easyui-datetimebox" name="zuDa2" id="zuDa2" style="width: 150px;" />
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
							<th data-options="field:'EDIT1',width:90,align:'center'">图文组编号</th>
							<th data-options="field:'EDIT2',width:90,align:'center'">图文组名称</th>
							<th data-options="field:'STUDENT_ID1',width:90,sortable:'true',align:'center'">图文组类型</th>
							<th data-options="field:'STNAME1',width:100,sortable:'true',align:'center'">启用标志</th>
							<th data-options="field:'EDIT3',width:100,align:'center'">图文组管理人</th>
							<th data-options="field:'STUDENT_ID2',width:100,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'STUDENT_ID3',width:100,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'STNAME2',width:160,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
						<tr>
							<td><input type="radio" name="radion" id="radion1"/></td>
							<td>1</td>
							<td>亞</td>
							<td>1</td>
							<td>1</td>
							<td>xxx</td>
							<td>xxx</td>
							<td>xxx</td>
							<td>2014/8/7 15:00:00</td>
						</tr>
						<tr>
							<td><input type="radio" name="radion" id="radion1"/></td>
							<td>1</td>
							<td>亞</td>
							<td>1</td>
							<td>1</td>
							<td>xxx</td>
							<td>xxx</td>
							<td>xxx</td>
							<td>2014/8/7 15:00:00</td>
						</tr>
				</table>
			</div>
</div>
