<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<style type="text/css">

.imggroup_div{width: 96%;height: auto;margin: 6px;}
.group_div_top{height: 150px;padding:10px;}
.group_div_top p img{width:100%;height:100px}
.group_p{margin-top: 10px;background: #5A5A5A;height: 30px;padding:10px 0 0 15px;}
.group_p a{color: white;}
.group_p a:hover{text-decoration: underline;}

.group_div_bottom{width: 100%;height: auto;}
.group_div_bottom div{border-top: 1px solid #E4E4E9;width: auto;height:65px;padding:13px;}
.group_div_bottom div img{line-display:block;float: right;width: 70px;height: 70px;}
.group_div_bottom div p{float: left;padding-top: 15px;}
</style>
<div id="div_add_student"  class="content-body">
	<!--搜索栏开始-->
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addImgGroupFrom" name="addImgGroupFrom" method="post" action="">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-30"><label>基准日期:</label></th>
							<td>
								<input type="text" class="easyui-datetimebox" name="standard" id="standard" style="width:150px;"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0);" class="easyui-linkbutton" id="refresh" style="width:70px;height: 25px;">刷新</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
			
			<div class="imggroup_div">
				<div class="group_div_top">
					<p class="group_img"><img src="img/0.jpg"/></p>
					<p class="group_p"><a href="javascript:;">第一只基金赚了多少钱?</a></p>
				</div>
			</div>
			<div class="group_div_bottom">
				<div>
					<p><a href="javascript:void(0);">债券基金逆袭记</a></p>
					<img src="img/1.jpg"/>
				</div>
				<div>
					<p><a href="javascript:void(0);">猜指数，赢话费</a></p>
					<img src="img/2.jpg"/>
				</div>
				<div>
					<p><a href="javascript:void(0);">只需14天</a></p>
					<img src="img/1.jpg"/>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	//点击员工选择dialog
	$("#employeeSel").click(function(){
		$('<div id="dialog_employee"></div>').dialog({
			title:"员工选择",
			width:800,
			height:500,
			href:'employeePage.action',
			modal:true,
			method:"POST",
			onClose:function(){
				$(this).dialog('destroy');
			}
		});
	});
});

</script>
