<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTStudentFrom" name="addTStudentFrom" method="post" action="addTStudentAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>stname</label></th>
							<td>
								<input type="text" id="tStudentDto.stname" name="tStudentDto.stname" class="easyui-validatebox" data-options="required:true,invalidMessage:'stname不能为空',missingMessage:'stname不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>stno</label></th>
							<td>
								<input type="text" id="tStudentDto.stno" name="tStudentDto.stno" class="easyui-validatebox" data-options="required:true,invalidMessage:'stno不能为空',missingMessage:'stno不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>age</label></th>
							<td>
								<input type="text" id="tStudentDto.age" name="tStudentDto.age" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'age必须为数字',missingMessage:'age必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>birthday</label></th>
							<td>
								<input type="text" id="tStudentDto.birthday" name="tStudentDto.birthday" class="easyui-datebox" data-options="required:true,invalidMessage:'birthday格式必须为yyyy-mm-dd',missingMessage:'birthday格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>money</label></th>
							<td>
								<input type="text" id="tStudentDto.money" name="tStudentDto.money" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'money必须为数字',missingMessage:'money必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>created_date</label></th>
							<td>
								<input type="text" id="tStudentDto.created_date" name="tStudentDto.created_date" class="easyui-datebox" data-options="required:true,invalidMessage:'created_date格式必须为yyyy-mm-dd',missingMessage:'created_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>created_user_cd</label></th>
							<td>
								<input type="text" id="tStudentDto.created_user_cd" name="tStudentDto.created_user_cd" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>