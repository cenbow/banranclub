<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTActUsersFrom" name="editTActUsersFrom" method="post" action="editTActUsersAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>openid</label></th>
								<td>
									<input type="text" id="tActUsersDto.openid" name="tActUsersDto.openid"  value="${tActUsersDto.openid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>nickname</label></th>
								<td>
									<input type="text" id="tActUsersDto.nickname" name="tActUsersDto.nickname"  value="${tActUsersDto.nickname}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>subscribe</label></th>
								<td>
									<input type="text" id="tActUsersDto.subscribe" name="tActUsersDto.subscribe"  value="${tActUsersDto.subscribe}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>sex</label></th>
								<td>
									<input type="text" id="tActUsersDto.sex" name="tActUsersDto.sex"  value="${tActUsersDto.sex}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>language</label></th>
								<td>
									<input type="text" id="tActUsersDto.language" name="tActUsersDto.language"  value="${tActUsersDto.language}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>city</label></th>
								<td>
									<input type="text" id="tActUsersDto.city" name="tActUsersDto.city"  value="${tActUsersDto.city}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>province</label></th>
								<td>
									<input type="text" id="tActUsersDto.province" name="tActUsersDto.province"  value="${tActUsersDto.province}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>country</label></th>
								<td>
									<input type="text" id="tActUsersDto.country" name="tActUsersDto.country"  value="${tActUsersDto.country}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>headimgurl</label></th>
								<td>
									<input type="text" id="tActUsersDto.headimgurl" name="tActUsersDto.headimgurl"  value="${tActUsersDto.headimgurl}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>subscribe_time</label></th>
								<td>
									<input type="text" id="tActUsersDto.subscribe_time" name="tActUsersDto.subscribe_time"  value="<fmt:formatDate value='${tActUsersDto.subscribe_time}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>unionid</label></th>
								<td>
									<input type="text" id="tActUsersDto.unionid" name="tActUsersDto.unionid"  value="${tActUsersDto.unionid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>remark</label></th>
								<td>
									<input type="text" id="tActUsersDto.remark" name="tActUsersDto.remark"  value="${tActUsersDto.remark}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>address</label></th>
								<td>
									<input type="text" id="tActUsersDto.address" name="tActUsersDto.address"  value="${tActUsersDto.address}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>mobile</label></th>
								<td>
									<input type="text" id="tActUsersDto.mobile" name="tActUsersDto.mobile"  value="${tActUsersDto.mobile}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>