<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editStudentFrom" name="editStudentFrom" method="post" action="editStudentAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="studentDto.student_id" name="studentDto.student_id" value="${studentDto.student_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>stname</label></th>
									<td>
										<input type="text" id="studentDto.stname" name="studentDto.stname" value="${studentDto.stname}" class="easyui-validatebox" data-options="required:true,invalidMessage:'stname不能为空',missingMessage:'stname不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>stno</label></th>
									<td>
										<input type="text" id="studentDto.stno" name="studentDto.stno" value="${studentDto.stno}" class="easyui-validatebox" data-options="required:true,invalidMessage:'stno不能为空',missingMessage:'stno不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>age</label></th>
									<td>
										<input type="text" id="studentDto.age" name="studentDto.age" value="${studentDto.age}" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'age必须为数字',missingMessage:'age必须为数字'" style="width:200px;"/>
									</td>
								  </tr>
								<tr>
									<th class="wd-20"><label>birthday</label></th>
									<td>
										<input type="text" id="studentDto.birthday" name="studentDto.birthday" value="<fmt:formatDate value='${studentDto.birthday}' type='date'  pattern='yyyy-MM-dd'/>" class="easyui-datebox" data-options="required:true,invalidMessage:'birthday格式必须为yyyy-mm-dd',missingMessage:'birthday格式必须为yyyy-mm-dd'"  style="width:200px;"/>
									</td>
								 </tr>
								 <tr>
									<th class="wd-20"><label>money</label></th>
									<td>
										<input type="text" id="studentDto.money" name="studentDto.money" value="${studentDto.money}" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'money必须为数字',missingMessage:'money必须为数字'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>txt</label></th>
									<td>
										<input type="text" id="studentDto.txt" name="studentDto.txt" value="${studentDto.txt}" class="easyui-validatebox" data-options="required:true,invalidMessage:'txt不能为空',missingMessage:'txt不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								<tr>
									<th class="wd-20"><label>inschool</label></th>
									<td>
										<input type="text" id="studentDto.inschool" name="studentDto.inschool" value="<fmt:formatDate value='${studentDto.inschool}' type='date'  pattern='yyyy-MM-dd'/>" class="easyui-datebox" data-options="required:true,invalidMessage:'inschool格式必须为yyyy-mm-dd',missingMessage:'inschool格式必须为yyyy-mm-dd'"  style="width:200px;"/>
									</td>
								 </tr>
					</table>
				</div>
			</form>
			<form id="delStudentFrom" name="delStudentFrom" method="post" action="delStudentAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${studentDto.student_id}" />
			</form>
		</div>
	</div>
</div>