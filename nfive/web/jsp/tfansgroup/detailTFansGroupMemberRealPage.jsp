<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTFansGroupMemberRealFrom" name="editTFansGroupMemberRealFrom" method="post" action="editTFansGroupMemberRealAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
									<tr>
										<th class="wd-20"><label>昵称</label></th>
										<td colspan="3">
											${out_tWeixinFansDto.nick_name }
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label>备注名</label></th>
										<td colspan="3">
											${out_tWeixinFansDto.remark_name}
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label>入群时间</label></th>
										<td>
											<fmt:formatDate value='${out_tFansGroupMemberRealDto.created_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label class="label-required">成员名称</label></th>
										<td colspan="3">
											${out_tFansGroupMemberRealDto.member_name }
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label class="label-required">标星成员</label></th>
										<td colspan="3">
											${out_tFansGroupMemberRealDto.star_member}
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label>成员备注</label></th>
										<td colspan="3" style="width: 500px;height:107px;word-break: break-all;word-wrap: break-word;">
											${out_tFansGroupMemberRealDto.member_memo}
										</td>
								</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>