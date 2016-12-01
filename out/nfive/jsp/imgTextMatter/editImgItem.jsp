<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<style type="text/css">
	.stRed{color: red;}
</style>
<title>图文项编辑</title>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_addimgitem_list" class="container">
	<header class="page-title">
		<h1>图文项编辑</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="back_btn"><i class="icon-back"></i></a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
			<!--增加项开始-->
			<div class="search-panel toggle-panel">
				<div class="search-panel-content">
				<form id="addItemForm" name="addItemForm">
						<div class="search-panel-bd">
							<table class="search-table">
								<tr>
									<th class="wd-10"><label>标题<span class="stRed">*</span>:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="title" name="title" data-options="required:true"/>
									</td>
									<th class="wd-10"><label>作者:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="author" name="author"/>
									</td>
									<th class="wd-10"><label>原文链接:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="b1" name="b2"/>
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>发布状态<span class="stRed">*</span>:</label></th>
									<td>
										<input type="text" class="easyui-combobox" id="b2" name="b2" data-options="
											valueField:'value',
											textFiled:'label',
											data:[{
												label:'未输入',
												value:'未输入'
											},{
												label:'草稿状态',
												value:'草稿状态'
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
											}]
										"/>
									</td>
									<th class="wd-10"><label>发布期间<span class="stRed">*</span>:</label></th>
									<td >
										<input type="text" class="easyui-datebox" id="b3" name="b3" style="width: 130px;"/> &nbsp;~&nbsp; 
										<input type="text" class="easyui-datebox" id="b4" name="b4" style="width: 130px;"/>
									</td>
									<th class="wd-10"><label>项目类型<span class="stRed">*</span>:</label></th>
									<td>
										<input type="text" class="easyui-combobox" id="b5" name="b5" data-options="
											valueFiled:'value',
											textFiled:'label',
											data:[{
												label:'未输入',
												value:'未输入'
											},{
												label:'产品资料',
												value:'产品资料'
											},{
												label:'活动宣传',
												value:'活动宣传'
											}]
										"/>
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>权重<span class="stRed">*</span>:</label></th>
									<td><input type="text" class="easyui-validatebox" id="b8" name="b8" data-options="required:true"/></td>
									<th class="wd-10"><label>正文中显示封面</label></th>
									<td colspan="3"><input type="checkbox" name="isShow" id="isShow"/></td>
								</tr>
								<tr>
									<th class="wd-10"><label>封面图片:</label></th>
									<td colspan="4">
										<img src="" style="width:500px;height: 200px;border: 1px solid blue;" id="b6" name="b6"/>
									</td>
									<td align="center">
										<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-datepicker'">选择图片</a>&nbsp;&nbsp;&nbsp;
										<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-icon-assigned'">上传</a>
									</td>
								</tr>
									<tr>
									<th class="wd-10"><label>摘要:</label></th>
									<td colspan="5">
										<textarea id="b7" name="b7" style="height:100px;"></textarea>
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>正文:</label></th>
									<td colspan="5">
										<p style="width: 100%;height: 400px;border: 1px solid blue;" ></p>
									</td>
								</tr>
							</table>
						</div>
						<div style="width: 100%;height: 70px;">
							<input id="search_btn" type="button" class="input-btn-small" value="查 询"  style="float: left;margin-left:45%;margin-top:10px;margin-right:30px; width: 100px;height: 35px;"/>
							<input id="clear_btn" type="button" class="input-btn-small" value="删除"  style="float: left;width: 100px;margin-top:10px;height: 35px;"/>
						</div>
					</form>
				</div>
			</div>
			<!--增加项结束-->
		</div>
	</article>
</div>

<script>
var addimgitem_list = {};
jQuery(function($){
//返回按钮事件
$("div#div_addimgitem_list #back_btn").click(function(){
		window.location='detailMessageImgGroupPage.action'
	});
});
</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>