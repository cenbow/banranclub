<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTUserRoleRelaFrom" name="editTUserRoleRelaFrom" method="post" action="editTUserRoleRelaAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tUserRoleRelaDto.user_role_id" name="tUserRoleRelaDto.user_role_id" value="${tUserRoleRelaDto.user_role_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>role_id</label></th>
									<td>
										<input type="text" id="tUserRoleRelaDto.role_id" name="tUserRoleRelaDto.role_id" value="${tUserRoleRelaDto.role_id}" class="easyui-validatebox" data-options="required:true,invalidMessage:'role_id不能为空',missingMessage:'role_id不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>user_id</label></th>
									<td>
										<input type="text" id="tUserRoleRelaDto.user_id" name="tUserRoleRelaDto.user_id" value="${tUserRoleRelaDto.user_id}" class="easyui-validatebox" data-options="required:true,invalidMessage:'user_id不能为空',missingMessage:'user_id不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								<tr>
									<th class="wd-20"><label>created_date</label></th>
									<td>
										<input type="text" id="tUserRoleRelaDto.created_date" name="tUserRoleRelaDto.created_date" value="${tUserRoleRelaDto.created_date}" class="easyui-datebox" data-options="required:true,invalidMessage:'created_date格式必须为yyyy-mm-dd',missingMessage:'created_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
									</td>
								 </tr>
								 <tr>
									<th class="wd-20"><label>created_user_cd</label></th>
									<td>
										<input type="text" id="tUserRoleRelaDto.created_user_cd" name="tUserRoleRelaDto.created_user_cd" value="${tUserRoleRelaDto.created_user_cd}" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
									</td>
								  </tr>
					</table>
				</div>
			</form>
			<form id="delTUserRoleRelaFrom" name="delTUserRoleRelaFrom" method="post" action="delTUserRoleRelaAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tUserRoleRelaDto.user_role_id}" />
			</form>
		</div>
	</div>
</div>