<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTFansGroupFrom" name="addTFansGroupFrom" method="post" action="addTFansGroupAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
									 <tr>
										<th class="wd-20"><label class="label-required">群名称</label></th>
										<td>
											<input type="text" id="add_group_name" name="in_tFansGroupDto.group_name" style="width:577px;" class="easyui-validatebox" maxlength="50" data-options="validType:'length[0,50]',required:true,invalidMessage:'长度不能大于50',missingMessage:'群名称不能为空'" />
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label>群备注</label></th>
										<td>
											<textarea rows="15" cols="6" name="in_tFansGroupDto.remark" id="add_remark" style="width:577px; max-width: 610px;max-height: 50px;height: 30px;" class="easyui-validatebox" maxlength="500" data-options="validType:'length[0,500]',invalidMessage:'长度不能大于500'"></textarea>
										</td>
									</tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>
