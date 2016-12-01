<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTFansGroupMemberRealFrom" name="editTFansGroupMemberRealFrom" method="post" action="editTFansGroupMemberRealAction.action">
					<!--  隐藏的ID  -->
					<input type="hidden" name="in_tFansGroupMemberRealDto.real_id" value="${out_tFansGroupMemberRealDto.real_id}" />
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
											<input type="text" name="in_tFansGroupMemberRealDto.member_name" id="edit_member_name" value="${out_tFansGroupMemberRealDto.member_name }" style="width: 577px;" 
												maxlength="50" class="easyui-validatebox" data-options="validType:'length[0,50]',required:'true',invalidMessage:'长度不能大于50',missingMessage:'成员名称不能为空'"/>
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label class="label-required">标星成员</label></th>
										<td colspan="3">
											<ldui:select items="${star_member_select}" id="edit_star_member_select" name="in_tFansGroupMemberRealDto.star_member" class="easyui-combobox" style="width:577px;"
												required="true" validType="select['#edit_star_member_select']" invalidMessage="匹配类型不能为空" missingMessage="请填写标星成员"  />
										</td>
									</tr>
									<tr>
										<th class="wd-20"><label>成员备注</label></th>
										<td colspan="3" style="width: 500px;height:107px;word-break: break-all;word-wrap: break-word;">
											<textarea rows="15" cols="6" name="in_tFansGroupMemberRealDto.member_memo" id="add_remark" style="width:577px; max-width: 610px;max-height: 50px;height: 30px;" 
											class="easyui-validatebox" maxlength="500" 
											data-options="validType:'length[0,500]',invalidMessage:'长度不能大于500'">${out_tFansGroupMemberRealDto.member_memo}</textarea>
										</td>
									</tr>
					</table>
				</div>
			</form>
			<form id="delTFansGroupMemberRealFrom" name="delTFansGroupMemberRealFrom" method="post" action="delTFansGroupMemberRealAction.action">
					<input type="text" hidden="true" name="in_tFansGroupDto.fans_group_id" value="${out_tFansGroupDto.fans_group_id}" >
			</form>
		</div>
	</div>
</div>