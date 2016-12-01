<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTStudentFrom" name="editTStudentFrom" method="post" action="editTStudentAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>stname</label></th>
								<td>
									<input type="text" id="tStudentDto.stname" name="tStudentDto.stname"  value="${tStudentDto.stname}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>stno</label></th>
								<td>
									<input type="text" id="tStudentDto.stno" name="tStudentDto.stno"  value="${tStudentDto.stno}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>age</label></th>
								<td>
									<input type="text" id="tStudentDto.age" name="tStudentDto.age"  value="${tStudentDto.age}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>birthday</label></th>
								<td>
									<input type="text" id="tStudentDto.birthday" name="tStudentDto.birthday"  value="<fmt:formatDate value='${tStudentDto.birthday}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>money</label></th>
								<td>
									<input type="text" id="tStudentDto.money" name="tStudentDto.money"  value="${tStudentDto.money}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tStudentDto.created_date" name="tStudentDto.created_date"  value="<fmt:formatDate value='${tStudentDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tStudentDto.created_user_cd" name="tStudentDto.created_user_cd"  value="${tStudentDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>