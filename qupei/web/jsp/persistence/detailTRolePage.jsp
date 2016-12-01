<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTRoleFrom" name="editTRoleFrom" method="post" action="editTRoleAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>角色名称</label></th>
								<td>
									<input type="text" id="tRoleDto.role_name" name="tRoleDto.role_name"  value="${tRoleDto.role_name}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							   <tr>
									<th class="wd-20"><label>角色分组</label></th>
									<td>
										<input type="text" id="tRoleDto.role_group" name="tRoleDto.role_group" value="${tRoleDto.role_group}" class="easyui-validatebox" readonly="true" style="width:200px;"/>
									</td>
								  </tr>
							 <tr>
								<th class="wd-20"><label>角色描述</label></th>
								<td>
									<textarea id="tRoleDto.role_desc" name="tRoleDto.role_desc" rows="" cols="" readonly="true">${tRoleDto.role_desc}</textarea>
								</td>
							  </tr>
							  <tr>
							<th class="wd-20"><label>创建日期</label></th>
							<td>
								<input type="text" id="tRoleDto.created_date"  name="tRoleDto.created_date" readonly="true" value="<fmt:formatDate value='${tRoleDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>创建人</label></th>
							<td>
								<input type="text" id="tRoleDto.created_user_cd" name="tRoleDto.created_user_cd" readonly="true" value="${tRoleDto.created_user_cd}"  style="width:200px;"/>
							</td>
						  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>