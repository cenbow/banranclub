<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTActUsersFrom" name="addTActUsersFrom" method="post" action="addTActUsersAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>openid</label></th>
							<td>
								<input type="text" id="tActUsersDto.openid" name="tActUsersDto.openid" class="easyui-validatebox" data-options="required:true,invalidMessage:'openid不能为空',missingMessage:'openid不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>nickname</label></th>
							<td>
								<input type="text" id="tActUsersDto.nickname" name="tActUsersDto.nickname" class="easyui-validatebox" data-options="required:true,invalidMessage:'nickname不能为空',missingMessage:'nickname不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>subscribe</label></th>
							<td>
								<input type="text" id="tActUsersDto.subscribe" name="tActUsersDto.subscribe" class="easyui-validatebox" data-options="required:true,invalidMessage:'subscribe不能为空',missingMessage:'subscribe不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>sex</label></th>
							<td>
								<input type="text" id="tActUsersDto.sex" name="tActUsersDto.sex" class="easyui-validatebox" data-options="required:true,invalidMessage:'sex不能为空',missingMessage:'sex不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>language</label></th>
							<td>
								<input type="text" id="tActUsersDto.language" name="tActUsersDto.language" class="easyui-validatebox" data-options="required:true,invalidMessage:'language不能为空',missingMessage:'language不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>city</label></th>
							<td>
								<input type="text" id="tActUsersDto.city" name="tActUsersDto.city" class="easyui-validatebox" data-options="required:true,invalidMessage:'city不能为空',missingMessage:'city不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>province</label></th>
							<td>
								<input type="text" id="tActUsersDto.province" name="tActUsersDto.province" class="easyui-validatebox" data-options="required:true,invalidMessage:'province不能为空',missingMessage:'province不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>country</label></th>
							<td>
								<input type="text" id="tActUsersDto.country" name="tActUsersDto.country" class="easyui-validatebox" data-options="required:true,invalidMessage:'country不能为空',missingMessage:'country不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>headimgurl</label></th>
							<td>
								<input type="text" id="tActUsersDto.headimgurl" name="tActUsersDto.headimgurl" class="easyui-validatebox" data-options="required:true,invalidMessage:'headimgurl不能为空',missingMessage:'headimgurl不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>subscribe_time</label></th>
							<td>
								<input type="text" id="tActUsersDto.subscribe_time" name="tActUsersDto.subscribe_time" class="easyui-datebox" data-options="required:true,invalidMessage:'subscribe_time格式必须为yyyy-mm-dd',missingMessage:'subscribe_time格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>unionid</label></th>
							<td>
								<input type="text" id="tActUsersDto.unionid" name="tActUsersDto.unionid" class="easyui-validatebox" data-options="required:true,invalidMessage:'unionid不能为空',missingMessage:'unionid不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>remark</label></th>
							<td>
								<input type="text" id="tActUsersDto.remark" name="tActUsersDto.remark" class="easyui-validatebox" data-options="required:true,invalidMessage:'remark不能为空',missingMessage:'remark不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>address</label></th>
							<td>
								<input type="text" id="tActUsersDto.address" name="tActUsersDto.address" class="easyui-validatebox" data-options="required:true,invalidMessage:'address不能为空',missingMessage:'address不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>mobile</label></th>
							<td>
								<input type="text" id="tActUsersDto.mobile" name="tActUsersDto.mobile" class="easyui-validatebox" data-options="required:true,invalidMessage:'mobile不能为空',missingMessage:'mobile不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>