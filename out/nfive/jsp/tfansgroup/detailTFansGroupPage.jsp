<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTFansGroupFrom" name="editTFansGroupFrom" method="post" action="editTFansGroupAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
					 				<tr>
										<th class="wd-20"><label id="label-required">群名称</label></th>
										<td colspan="3">
											${out_tFansGroupDto.group_name }
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label>群备注</label></th>
										<td style="width: 500px;height:107px;word-break: break-all;word-wrap: break-word;">
											${out_tFansGroupDto.remark }
										</td>
									</tr>
					</table>
				</div>
			</form>
			<form id="editTFansGroupFrom" name="editTFansGroupFrom" method="post" action="editTFansGroupPageAction.action">
					<input type="hidden" name="in_tFansGroupDto.fans_group_id" value="${out_tFansGroupDto.fans_group_id}" >
			</form>
		</div>
	</div>
</div>