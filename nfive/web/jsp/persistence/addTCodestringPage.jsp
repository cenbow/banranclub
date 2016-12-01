<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTCodestringFrom" name="addTCodestringFrom" method="post" action="addTCodestringAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>cs_type</label></th>
							<td>
								<input type="text" id="tCodestringDto.cs_type" name="tCodestringDto.cs_type" class="easyui-validatebox" data-options="required:true,invalidMessage:'cs_type不能为空',missingMessage:'cs_type不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>cs_sub_type</label></th>
							<td>
								<input type="text" id="tCodestringDto.cs_sub_type" name="tCodestringDto.cs_sub_type" class="easyui-validatebox" data-options="required:true,invalidMessage:'cs_sub_type不能为空',missingMessage:'cs_sub_type不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>cs_name</label></th>
							<td>
								<input type="text" id="tCodestringDto.cs_name" name="tCodestringDto.cs_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'cs_name不能为空',missingMessage:'cs_name不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>cs_value</label></th>
							<td>
								<input type="text" id="tCodestringDto.cs_value" name="tCodestringDto.cs_value" class="easyui-validatebox" data-options="required:true,invalidMessage:'cs_value不能为空',missingMessage:'cs_value不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>cs_desc</label></th>
							<td>
								<input type="text" id="tCodestringDto.cs_desc" name="tCodestringDto.cs_desc" class="easyui-validatebox" data-options="required:true,invalidMessage:'cs_desc不能为空',missingMessage:'cs_desc不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>created_date</label></th>
							<td>
								<input type="text" id="tCodestringDto.created_date" name="tCodestringDto.created_date" class="easyui-datebox" data-options="required:true,invalidMessage:'created_date格式必须为yyyy-mm-dd',missingMessage:'created_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>created_user_cd</label></th>
							<td>
								<input type="text" id="tCodestringDto.created_user_cd" name="tCodestringDto.created_user_cd" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>