<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Portal - jQuery EasyUI</title>
<%@ include file="/jsp/common/easyui_head.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/easyui/portal.css">
<style type="text/css">
	.title{
		font-size:16px;
		font-weight:bold;
		padding:20px 10px;
		background:#eee;
		overflow:hidden;
		border-bottom:1px solid #ccc;
	}
	.t-list{
		padding:5px;
	}
</style>
<script type="text/javascript" src="<%=jsPath%>/jquery.easyui/jquery.portal.js"></script>
<script>
	$(function(){
		$('#pp').portal({
			border:false,
			fit:false
		});
		add();
	});
	function add(){
		for(var i=0; i<3; i++){
			var p = $('<div/>').appendTo('body');
			p.panel({
				title:'Title'+i,
				content:'<div style="padding:5px;">Content'+(i+1)+'</div>',
				height:100,
				closable:true,
				collapsible:true
			});
			$('#pp').portal('add', {
				panel:p,
				columnIndex:i
			});
		}
		$('#pp').portal('resize');
	}
	function remove(){
		$('#pp').portal('remove',$('#pgrid'));
		$('#pp').portal('resize');
	}
</script>
</head>

<body class="easyui-layout">
	<%@ include file="/jsp/common/topmenu.jsp"%>
	<!--内容-->
	<div id="div_tCodestring_list" class="container">
		<header class="page-title">
			<h1>员工信息一览</h1>
		</header>
		<div class="page-toolbar clearfix">
			<ul class="page-toolbar-list">
				<li><a href="javascript:;" id="back_btn"><i class="icon-back"></i>返回</a></li>
				<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>添加员工</a></li>
			</ul>
		</div>
		<article id="content" class="content content-wide">
			
			<div class="content-body">
			
<!--				<div region="north" class="title" border="false" style="height:60px;">-->
<!--					jQuery EasyUI Portal-->
<!--				</div>-->
				<div region="center" border="false">
					<div id="pp" style="position:relative">
						<div style="width:30%;">
							<div title="Clock" style="text-align:center;background:#f3eeaf;height:150px;padding:5px;">
								<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="100" height="100">
							      <param name="movie" value="http://www.respectsoft.com/onlineclock/analog.swf">
							      <param name=quality value=high>
							      <param name="wmode" value="transparent">
							      <embed src="http://www.respectsoft.com/onlineclock/analog.swf" width="100" height="100" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" wmode="transparent"></embed>
							    </object>
						    </div>
						    <div title="Tutorials" collapsible="true" closable="true" style="height:200px;padding:5px;">
						    	<div class="t-list"><a href="http://www.jeasyui.com/tutorial/datagrid/datagrid1.php">Build border layout for Web Pages</a></div>
						    	<div class="t-list"><a href="http://www.jeasyui.com/tutorial/layout/panel.php">Complex layout on Panel</a></div>
						    	<div class="t-list"><a href="http://www.jeasyui.com/tutorial/layout/accordion.php">Create Accordion</a></div>
						    	<div class="t-list"><a href="http://www.jeasyui.com/tutorial/layout/tabs.php">Create Tabs</a></div>
						    	<div class="t-list"><a href="http://www.jeasyui.com/tutorial/layout/tabs2.php">Dynamically add tabs</a></div>
						    	<div class="t-list"><a href="http://www.jeasyui.com/tutorial/layout/panel2.php">Create XP style left panel</a></div>
						    </div>
						</div>
						<div style="width:40%;">
							<div id="pgrid" title="DataGrid" closable="true" style="height:200px;">
								<table class="easyui-datagrid" style="width:650px;height:auto"
										fit="true" border="false"
										singleSelect="true"
										idField="itemid" url="datagrid_data.json">
									<thead>
										<tr>
											<th field="itemid" width="60">Item ID</th>
											<th field="productid" width="60">Product ID</th>
											<th field="listprice" width="80" align="right">List Price</th>
											<th field="unitcost" width="80" align="right">Unit Cost</th>
											<th field="attr1" width="120">Attribute</th>
											<th field="status" width="50" align="center">Status</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div style="width:30%;">
							<div title="Searching" iconCls="icon-search" closable="true" style="height:80px;padding:10px;">
								<input class="easyui-searchbox">
							</div>
							<div title="Graph" closable="true" style="height:200px;text-align:center;">
								<img height="160" src="http://knol.google.com/k/-/-/3mudqpof935ww/ip4n5y/web-graph.png"></img>
							</div>
						</div>
					</div>
				</div>
	
		</div>
	</article>
</div>

<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>