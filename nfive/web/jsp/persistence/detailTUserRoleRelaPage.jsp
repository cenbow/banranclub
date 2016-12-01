<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTUserRoleRelaFrom" name="editTUserRoleRelaFrom" method="post" action="editTUserRoleRelaAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>role_id</label></th>
								<td>
									<input type="text" id="tUserRoleRelaDto.role_id" name="tUserRoleRelaDto.role_id"  value="${tUserRoleRelaDto.role_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>user_id</label></th>
								<td>
									<input type="text" id="tUserRoleRelaDto.user_id" name="tUserRoleRelaDto.user_id"  value="${tUserRoleRelaDto.user_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tUserRoleRelaDto.created_date" name="tUserRoleRelaDto.created_date"  value="<fmt:formatDate value='${tUserRoleRelaDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tUserRoleRelaDto.created_user_cd" name="tUserRoleRelaDto.created_user_cd"  value="${tUserRoleRelaDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>