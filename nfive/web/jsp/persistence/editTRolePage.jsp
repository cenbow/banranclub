<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTRoleFrom" name="editTRoleFrom" method="post" action="editTRoleAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tRoleDto.role_id" name="tRoleDto.role_id" value="${tRoleDto.role_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>角色名称</label></th>
									<td>
										<input type="text" id="tRoleDto.role_name" name="tRoleDto.role_name" value="${tRoleDto.role_name}" class="easyui-validatebox" data-options="required:true,invalidMessage:'角色名称不能为空',missingMessage:'角色名称不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								  <tr>
									<th class="wd-20"><label>角色分组</label></th>
									<td>
										<input type="text" id="tRoleDto.role_group" name="tRoleDto.role_group" value="${tRoleDto.role_group}" class="easyui-validatebox" data-options="required:true,invalidMessage:'角色分组不能为空',missingMessage:'角色分组不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>角色描述</label></th>
									<td>
										<textarea id="tRoleDto.role_desc" name="tRoleDto.role_desc" rows="" cols="">${tRoleDto.role_desc}</textarea>
									</td>
								  </tr>
					</table>
				</div>
			</form>
			<form id="delTRoleFrom" name="delTRoleFrom" method="post" action="delTRoleAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tRoleDto.role_id}" />
			</form>
		</div>
	</div>
</div>