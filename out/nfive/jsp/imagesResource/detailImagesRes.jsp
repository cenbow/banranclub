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
			<form id="addImgGroupFrom" name="addImgGroupFrom" method="post" action="">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-20"><label>图片名称:</label></th>
							<td colspan="3">
								<input type="text" class="easyui-validatebox" class="r1" name="r1" style="width: 97%;"/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图片规格（宽）:</label></th>
							<td>
								<input type="text" id="r2" name="r2"  class="easyui-validatebox" />
							</td>
							<th class="wd-20"><label>图片规格（高）:</label></th>
							<td>
								<input type="text" id="r3" name="r3"  class="easyui-validatebox" />
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图片描述:</label></th>
							<td colspan="3">
								<textarea id="r4" name="r4" style="width: 100%;height: 100px;"></textarea>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><a href="javascript:void(0);" name="r5" id="r5" class="easyui-linkbutton">上传</a></th>
							<td  colspan="3">
								<div style="width: 90%;height: 200px;border: 1px solid blue;"></div>
							</td>
						</tr>
						<tr>
							<th class="wd-20">图片地址:</th>
							<td  colspan="3">
								D:\Users\Administrator\Workspaces\wechat\web\jsp
							</td>
						</tr>
						<tr>
							<th class="wd-20">创建人:</th>
							<td>
								XXX
							</td>
							<th class="wd-20">更新人:</th>
							<td >
								XXX
							</td>
						</tr>
						<tr>
							<th class="wd-20">最后更新时间:</th>
							<td  colspan="3">
								2014-08-11 17:30:00
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
	<!--搜索栏结束-->
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
