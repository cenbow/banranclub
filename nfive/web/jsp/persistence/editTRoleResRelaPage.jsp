<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTRoleResRelaFrom" name="editTRoleResRelaFrom" method="post" action="editTRoleResRelaAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tRoleResRelaDto.role_res_id" name="tRoleResRelaDto.role_res_id" value="${tRoleResRelaDto.role_res_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>role_id</label></th>
									<td>
										<input type="text" id="tRoleResRelaDto.role_id" name="tRoleResRelaDto.role_id" value="${tRoleResRelaDto.role_id}" class="easyui-validatebox" data-options="required:true,invalidMessage:'role_id不能为空',missingMessage:'role_id不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>res_id</label></th>
									<td>
										<input type="text" id="tRoleResRelaDto.res_id" name="tRoleResRelaDto.res_id" value="${tRoleResRelaDto.res_id}" class="easyui-validatebox" data-options="required:true,invalidMessage:'res_id不能为空',missingMessage:'res_id不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								<tr>
									<th class="wd-20"><label>created_date</label></th>
									<td>
										<input type="text" id="tRoleResRelaDto.created_date" name="tRoleResRelaDto.created_date" value="${tRoleResRelaDto.created_date}" class="easyui-datebox" data-options="required:true,invalidMessage:'created_date格式必须为yyyy-mm-dd',missingMessage:'created_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
									</td>
								 </tr>
								 <tr>
									<th class="wd-20"><label>created_user_cd</label></th>
									<td>
										<input type="text" id="tRoleResRelaDto.created_user_cd" name="tRoleResRelaDto.created_user_cd" value="${tRoleResRelaDto.created_user_cd}" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
									</td>
								  </tr>
					</table>
				</div>
			</form>
			<form id="delTRoleResRelaFrom" name="delTRoleResRelaFrom" method="post" action="delTRoleResRelaAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tRoleResRelaDto.role_res_id}" />
			</form>
		</div>
	</div>
</div>