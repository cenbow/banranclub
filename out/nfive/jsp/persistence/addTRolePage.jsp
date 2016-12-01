<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTRoleFrom" name="addTRoleFrom" method="post" action="addTRoleAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>角色名称</label></th>
							<td>
								<input type="text" id="tRoleDto.role_name" name="tRoleDto.role_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'角色名称不能为空',missingMessage:'角色名称不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						  <tr>
							<th class="wd-20"><label>角色分组</label></th>
							<td>
								<input type="text" id="tRoleDto.role_group" name="tRoleDto.role_group" class="easyui-validatebox" data-options="required:true,invalidMessage:'角色分组不能为空',missingMessage:'角色分组不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>角色描述</label></th>
							<td>
								<textarea id="tRoleDto.role_desc" name="tRoleDto.role_desc" rows="" cols="" ></textarea>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>