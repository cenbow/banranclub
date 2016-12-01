<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTResSelfRelaFrom" name="editTResSelfRelaFrom" method="post" action="editTResSelfRelaAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>parent_res_id</label></th>
								<td>
									<input type="text" id="tResSelfRelaDto.parent_res_id" name="tResSelfRelaDto.parent_res_id"  value="${tResSelfRelaDto.parent_res_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>child_res_id</label></th>
								<td>
									<input type="text" id="tResSelfRelaDto.child_res_id" name="tResSelfRelaDto.child_res_id"  value="${tResSelfRelaDto.child_res_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>res_type</label></th>
								<td>
									<input type="text" id="tResSelfRelaDto.res_type" name="tResSelfRelaDto.res_type"  value="${tResSelfRelaDto.res_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tResSelfRelaDto.created_date" name="tResSelfRelaDto.created_date"  value="<fmt:formatDate value='${tResSelfRelaDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tResSelfRelaDto.created_user_cd" name="tResSelfRelaDto.created_user_cd"  value="${tResSelfRelaDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>