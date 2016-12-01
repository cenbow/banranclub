<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTResSelfRelaFrom" name="addTResSelfRelaFrom" method="post" action="addTResSelfRelaAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>parent_res_id</label></th>
							<td>
								<input type="text" id="tResSelfRelaDto.parent_res_id" name="tResSelfRelaDto.parent_res_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'parent_res_id不能为空',missingMessage:'parent_res_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>child_res_id</label></th>
							<td>
								<input type="text" id="tResSelfRelaDto.child_res_id" name="tResSelfRelaDto.child_res_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'child_res_id不能为空',missingMessage:'child_res_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>res_type</label></th>
							<td>
								<input type="text" id="tResSelfRelaDto.res_type" name="tResSelfRelaDto.res_type" class="easyui-validatebox" data-options="required:true,invalidMessage:'res_type不能为空',missingMessage:'res_type不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>created_date</label></th>
							<td>
								<input type="text" id="tResSelfRelaDto.created_date" name="tResSelfRelaDto.created_date" class="easyui-datebox" data-options="required:true,invalidMessage:'created_date格式必须为yyyy-mm-dd',missingMessage:'created_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>created_user_cd</label></th>
							<td>
								<input type="text" id="tResSelfRelaDto.created_user_cd" name="tResSelfRelaDto.created_user_cd" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>