<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTRoleResRelaFrom" name="editTRoleResRelaFrom" method="post" action="editTRoleResRelaAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>role_id</label></th>
								<td>
									<input type="text" id="tRoleResRelaDto.role_id" name="tRoleResRelaDto.role_id"  value="${tRoleResRelaDto.role_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>res_id</label></th>
								<td>
									<input type="text" id="tRoleResRelaDto.res_id" name="tRoleResRelaDto.res_id"  value="${tRoleResRelaDto.res_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tRoleResRelaDto.created_date" name="tRoleResRelaDto.created_date"  value="<fmt:formatDate value='${tRoleResRelaDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tRoleResRelaDto.created_user_cd" name="tRoleResRelaDto.created_user_cd"  value="${tRoleResRelaDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>