<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTCodestringFrom" name="editTCodestringFrom" method="post" action="editTCodestringAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>cs_type</label></th>
								<td>
									<input type="text" id="tCodestringDto.cs_type" name="tCodestringDto.cs_type"  value="${tCodestringDto.cs_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>cs_sub_type</label></th>
								<td>
									<input type="text" id="tCodestringDto.cs_sub_type" name="tCodestringDto.cs_sub_type"  value="${tCodestringDto.cs_sub_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>cs_name</label></th>
								<td>
									<input type="text" id="tCodestringDto.cs_name" name="tCodestringDto.cs_name"  value="${tCodestringDto.cs_name}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>cs_value</label></th>
								<td>
									<input type="text" id="tCodestringDto.cs_value" name="tCodestringDto.cs_value"  value="${tCodestringDto.cs_value}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>cs_desc</label></th>
								<td>
									<input type="text" id="tCodestringDto.cs_desc" name="tCodestringDto.cs_desc"  value="${tCodestringDto.cs_desc}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tCodestringDto.created_date" name="tCodestringDto.created_date"  value="<fmt:formatDate value='${tCodestringDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tCodestringDto.created_user_cd" name="tCodestringDto.created_user_cd"  value="${tCodestringDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>