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
									<th class="wd-20"><label>标题:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="title"  name="title" style="width: 150px;"/>
									</td>
									<th class="wd-20"><label>发布状态:</label></th>
									<td>
										<input type="text" class="easyui-combobox" id="start" name="start" style="width: 160px;" data-options="
											valueField:'value',
											textField:'label',
											data:[{
												value:'未输入',
												label:'未输入'
											},{
												value:'草稿状态',
												label:'草稿状态'
											},{
												label:'完稿待审核',
												value:'完稿待审核'
											},{
												label:'审核通过',
												value:'审核通过'
											},{
												label:'审核未通过',
												value:'审核未通过'
											},{
												label:'强制无效',
												value:'强制无效'
											}],value:'未输入'
											"/>
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>图文组名称:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="name" id="name" style="width: 150px;" />
									</td>
									<th class="wd-20"><label>图文组管理人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="per" id="per"  style="width: 150px;"/>
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>发布开始时间:</label></th>
									<td>
										<input  class="easyui-datetimebox" name="stDa" id="stDa"   style="width: 160px;" />
									</td>
									<th class="wd-20"><label>发布结束时间:</label></th>
									<td>
										<input type="text" class="easyui-datetimebox" name="enDa" id="enDa" style="width: 160px;" />
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>项目类型:</label></th>
									<td>
										<input type="text" class="easyui-combobox" style="width:160px;" id="type" name="type" data-options="
											valueField:'value',
											textField:'label',
											data:[{
												label:'未输入',
												value:'未输入'
											},{
												label:'产品资料',
												value:'产品资料'
											},{
												label:'活动宣传',
												value:'活动宣传'
											}],value:'未输入'
											"/>
									</td>
									<th class="wd-20"><label>创建人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="creaper" id="creaper"  style="width:150px;"/>
									</td>
								</tr>
								<tr>
									<th class="wd-20"><label>更新人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" name="gePer" id="gePer"  style="width: 150px;" />
									</td>
									<th class="wd-20"><label>最后更新时间:</label></th>
									<td>
										<input type="text" class="easyui-datetimebox" name="zuDa" id="zuDa" style="width: 160px;" />
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
							<th data-options="field:'EDIT1',width:140,align:'center'">标题</th>
							<th data-options="field:'EDIT2',width:100,align:'center'">发布状态</th>
							<th data-options="field:'STUDENT_ID1',width:150,sortable:'true',align:'center'">发布开始时间</th>
							<th data-options="field:'STNAME1',width:150,sortable:'true',align:'center'">发布结束时间</th>
							<th data-options="field:'EDIT3',width:100,align:'center'">创建人</th>
							<th data-options="field:'STUDENT_ID2',width:100,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'STNAME2',width:150,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
						<tr>
							<td><input type="radio" name="radion" id="radion1"/></td>
							<td>1</td>
							<td>1</td>
							<td>2014/8/7 10:00:00</td>
							<td>2014/8/7 12:00:00</td>
							<td>xxx</td>
							<td>xxx</td>
							<td>2014/8/7 15:00:00</td>
						</tr>
						<tr>
							<td><input type="radio" name="radion" id="radion1"/></td>
							<td>1</td>
							<td>1</td>
							<td>2014/8/7 10:00:00</td>
							<td>2014/8/7 12:00:00</td>
							<td>xxx</td>
							<td>xxx</td>
							<td>2014/8/7 15:00:00</td>
						</tr>
				</table>
			</div>
</div>
