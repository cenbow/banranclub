<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addStudentFrom" name="addStudentFrom" method="post" action="addStudentAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>stname</label></th>
							<td>
								<input type="text" id="studentDto.stname" name="studentDto.stname" class="easyui-validatebox" data-options="required:true,invalidMessage:'stname不能为空',missingMessage:'stname不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>stno</label></th>
							<td>
								<input type="text" id="studentDto.stno" name="studentDto.stno" class="easyui-validatebox" data-options="required:true,invalidMessage:'stno不能为空',missingMessage:'stno不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>age</label></th>
							<td>
								<input type="text" id="studentDto.age" name="studentDto.age" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'age必须为数字',missingMessage:'age必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>birthday</label></th>
							<td>
								<input type="text" id="studentDto.birthday" name="studentDto.birthday" class="easyui-datebox" data-options="required:true,invalidMessage:'birthday格式必须为yyyy-mm-dd',missingMessage:'birthday格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>money</label></th>
							<td>
								<input type="text" id="studentDto.money" name="studentDto.money" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'money必须为数字',missingMessage:'money必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>txt</label></th>
							<td>
								<input type="text" id="studentDto.txt" name="studentDto.txt" class="easyui-validatebox" data-options="required:true,invalidMessage:'txt不能为空',missingMessage:'txt不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>inschool</label></th>
							<td>
								<input type="text" id="studentDto.inschool" name="studentDto.inschool" class="easyui-datebox" data-options="required:true,invalidMessage:'inschool格式必须为yyyy-mm-dd',missingMessage:'inschool格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>