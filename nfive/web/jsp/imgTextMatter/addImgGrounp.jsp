<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<style type="text/css">
	.stRed{color: red}
</style>
<div id="div_add_student"  class="content-body">
	<!--搜索栏开始-->
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addImgGroupFrom" name="addImgGroupFrom" method="post" action="">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-20"><label>图文组编号:</label></th>
							<td colspan="3">
								AG20140806001
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图文组名称:<span class="stRed">*</span></label></th>
							<td>
								<input type="text" id="a1" name="a1"  class="easyui-validatebox" value="1" data-options="required:true,invalidMessage:'不能为空',missingMessage:'不能为空'"  style="width:200px;" />
							</td>
							<th class="wd-20"><label>图文组管理人:<span class="stRed">*</span></label></th>
							<td>
								<input type="text" id="a2" name="a2" class="easyui-validatebox" value="2" data-options="required:true,invalidMessage:'不能为空',missingMessage:'不能为空'"  style="width:160px;" />
								&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" id="employeeSel" name="employeeSel"><i class="icon-search"></i></a>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>启用标志:<span class="stRed">*</span></label></th>
							<td>
								<input type="text" class="easyui-combobox" id="a3"  name="a3" data-options="
																				valueField: 'label',
																				textField: 'value',
																				data: [{
																					label: '未输入',
																					value: '未输入'
																				},{
																					label: '是',
																					value: '是'
																				},{
																					label: '否',
																					value: '否'
																				}]" style="width: 200px;"/>
							</td>
							<th class="wd-20"><label>图文组类型:<span class="stRed">*</span></label></th>
							<td>
								<input type="text" class="easyui-combobox" id="imgtType"  name="a4" data-options="
																				valueField: 'value',
																				textField: 'label',
																				data: [{
																					label: '未输入',
																					value: '0'
																				},{
																					label: '单图文',
																					value: '1'
																				},{
																					label: '双图文',
																					value: '2'
																				}],value:'0',onSelect:aa" style="width: 200px;"/>
							</td>
						</tr>
						<tr id="isTrDis" style="display: none;">
							<th class="wd-20"><label>最大条目数:</label></th>
							<td  colspan="3">
								<input type="text" class="easyui-combobox" id="a5"  name="a5" data-options="
																				valueField: 'label',
																				textField: 'value',
																				data: [{
																					label: '2',
																					value: '2'
																				},{
																					label: '3',
																					value: '3'
																				},{
																					label: '4',
																					value: '4'
																				}],value:'2'" style="width: 200px;"/>
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
