<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editStudentFrom" name="editStudentFrom" method="post" action="editStudentAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>stname</label></th>
								<td>
									<input type="text" id="studentDto.stname" name="studentDto.stname"  value="${studentDto.stname}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>stno</label></th>
								<td>
									<input type="text" id="studentDto.stno" name="studentDto.stno"  value="${studentDto.stno}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>age</label></th>
								<td>
									<input type="text" id="studentDto.age" name="studentDto.age"  value="${studentDto.age}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>birthday</label></th>
								<td>
									<input type="text" id="studentDto.birthday" name="studentDto.birthday"  value="<fmt:formatDate value='${studentDto.birthday}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>money</label></th>
								<td>
									<input type="text" id="studentDto.money" name="studentDto.money"  value="${studentDto.money}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>txt</label></th>
								<td>
									<input type="text" id="studentDto.txt" name="studentDto.txt"  value="${studentDto.txt}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>inschool</label></th>
								<td>
									<input type="text" id="studentDto.inschool" name="studentDto.inschool"  value="<fmt:formatDate value='${studentDto.inschool}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>