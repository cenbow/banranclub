<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWxFansFrom" name="editTWxFansFrom" method="post" action="editTWxFansAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tWxFansDto.fans_id" name="tWxFansDto.fans_id" value="${tWxFansDto.fans_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>openid</label></th>
									<td>
										<input type="text" id="tWxFansDto.openid" name="tWxFansDto.openid" value="${tWxFansDto.openid}" class="easyui-validatebox" data-options="required:true,invalidMessage:'openid不能为空',missingMessage:'openid不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>nickname</label></th>
									<td>
										<input type="text" id="tWxFansDto.nickname" name="tWxFansDto.nickname" value="${tWxFansDto.nickname}" class="easyui-validatebox" data-options="required:true,invalidMessage:'nickname不能为空',missingMessage:'nickname不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>subscribe</label></th>
									<td>
										<input type="text" id="tWxFansDto.subscribe" name="tWxFansDto.subscribe" value="${tWxFansDto.subscribe}" class="easyui-validatebox" data-options="required:true,invalidMessage:'subscribe不能为空',missingMessage:'subscribe不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>sex</label></th>
									<td>
										<input type="text" id="tWxFansDto.sex" name="tWxFansDto.sex" value="${tWxFansDto.sex}" class="easyui-validatebox" data-options="required:true,invalidMessage:'sex不能为空',missingMessage:'sex不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>language</label></th>
									<td>
										<input type="text" id="tWxFansDto.language" name="tWxFansDto.language" value="${tWxFansDto.language}" class="easyui-validatebox" data-options="required:true,invalidMessage:'language不能为空',missingMessage:'language不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>city</label></th>
									<td>
										<input type="text" id="tWxFansDto.city" name="tWxFansDto.city" value="${tWxFansDto.city}" class="easyui-validatebox" data-options="required:true,invalidMessage:'city不能为空',missingMessage:'city不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>province</label></th>
									<td>
										<input type="text" id="tWxFansDto.province" name="tWxFansDto.province" value="${tWxFansDto.province}" class="easyui-validatebox" data-options="required:true,invalidMessage:'province不能为空',missingMessage:'province不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>country</label></th>
									<td>
										<input type="text" id="tWxFansDto.country" name="tWxFansDto.country" value="${tWxFansDto.country}" class="easyui-validatebox" data-options="required:true,invalidMessage:'country不能为空',missingMessage:'country不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>headimgurl</label></th>
									<td>
										<input type="text" id="tWxFansDto.headimgurl" name="tWxFansDto.headimgurl" value="${tWxFansDto.headimgurl}" class="easyui-validatebox" data-options="required:true,invalidMessage:'headimgurl不能为空',missingMessage:'headimgurl不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								<tr>
									<th class="wd-20"><label>subscribe_time</label></th>
									<td>
										<input type="text" id="tWxFansDto.subscribe_time" name="tWxFansDto.subscribe_time" value="<fmt:formatDate value='${tWxFansDto.subscribe_time}' type='date'  pattern='yyyy-MM-dd'/>" class="easyui-datebox" data-options="required:true,invalidMessage:'subscribe_time格式必须为yyyy-mm-dd',missingMessage:'subscribe_time格式必须为yyyy-mm-dd'"  style="width:200px;"/>
									</td>
								 </tr>
								 <tr>
									<th class="wd-20"><label>unionid</label></th>
									<td>
										<input type="text" id="tWxFansDto.unionid" name="tWxFansDto.unionid" value="${tWxFansDto.unionid}" class="easyui-validatebox" data-options="required:true,invalidMessage:'unionid不能为空',missingMessage:'unionid不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>remark</label></th>
									<td>
										<input type="text" id="tWxFansDto.remark" name="tWxFansDto.remark" value="${tWxFansDto.remark}" class="easyui-validatebox" data-options="required:true,invalidMessage:'remark不能为空',missingMessage:'remark不能为空'" style="width:200px;"/>
									</td>
								  </tr>
					</table>
				</div>
			</form>
			<form id="delTWxFansFrom" name="delTWxFansFrom" method="post" action="delTWxFansAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tWxFansDto.fans_id}" />
			</form>
		</div>
	</div>
</div>