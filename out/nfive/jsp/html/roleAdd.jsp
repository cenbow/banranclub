<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--搜索栏开始-->
<div class="search-panel-content">
	<form id="addTCodestringFrom" name="addTCodestringFrom" method="post" >
		<div class="search-panel-bd">
			<table class="search-table">
				 <tr>
						<th class="wd-20"><label>角色代码</label></th>
						<td>
							<input type="text" id="tCodestringDto.cs_type" name="search_cs_type" class="easyui-validatebox" value="" style="width:200px;" data-options="required:true" />
						</td>
						<th class="wd-20"><label>角色名称</label></th>
						<td>
							<input type="text" id="tCodestringDto.cs_sub_type" name="search_cs_sub_type" class="easyui-validatebox"  value="" style="width:200px;" data-options="required:true" />
						</td>
				  </tr>
				 <tr>
						<th class="wd-20"><label>角色组</label></th>
						<td colspan="3">
							<input type="text" id="tCodestringDto.cs_name" name="search_cs_name" class="easyui-validatebox"  value="" style="width:200px;" data-options="required:true" />
						</td>
				</tr>
				<tr>
						<th class="wd-20 vt"><label>备注信息</label></th>
						<td colspan="3">
							<textarea id="tCodestringDto.cs_value" name="search_cs_value" class="easyui-validatebox" value="" data-options="required:true"></textarea>
						</td>
				  </tr>
			</table>
		</div>
	</form>
</div>
<!--搜索栏结束-->
