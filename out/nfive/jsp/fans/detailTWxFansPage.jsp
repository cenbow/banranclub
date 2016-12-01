<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWxFansFrom" name="editTWxFansFrom" method="post" action="editTWxFansAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>openid</label></th>
								<td>
									<input type="text" id="tWxFansDto.openid" name="tWxFansDto.openid"  value="${tWxFansDto.openid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>nickname</label></th>
								<td>
									<input type="text" id="tWxFansDto.nickname" name="tWxFansDto.nickname"  value="${tWxFansDto.nickname}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>subscribe</label></th>
								<td>
									<input type="text" id="tWxFansDto.subscribe" name="tWxFansDto.subscribe"  value="${tWxFansDto.subscribe}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>sex</label></th>
								<td>
									<input type="text" id="tWxFansDto.sex" name="tWxFansDto.sex"  value="${tWxFansDto.sex}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>language</label></th>
								<td>
									<input type="text" id="tWxFansDto.language" name="tWxFansDto.language"  value="${tWxFansDto.language}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>city</label></th>
								<td>
									<input type="text" id="tWxFansDto.city" name="tWxFansDto.city"  value="${tWxFansDto.city}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>province</label></th>
								<td>
									<input type="text" id="tWxFansDto.province" name="tWxFansDto.province"  value="${tWxFansDto.province}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>country</label></th>
								<td>
									<input type="text" id="tWxFansDto.country" name="tWxFansDto.country"  value="${tWxFansDto.country}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>headimgurl</label></th>
								<td>
									<input type="text" id="tWxFansDto.headimgurl" name="tWxFansDto.headimgurl"  value="${tWxFansDto.headimgurl}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>subscribe_time</label></th>
								<td>
									<input type="text" id="tWxFansDto.subscribe_time" name="tWxFansDto.subscribe_time"  value="<fmt:formatDate value='${tWxFansDto.subscribe_time}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>unionid</label></th>
								<td>
									<input type="text" id="tWxFansDto.unionid" name="tWxFansDto.unionid"  value="${tWxFansDto.unionid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>remark</label></th>
								<td>
									<input type="text" id="tWxFansDto.remark" name="tWxFansDto.remark"  value="${tWxFansDto.remark}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>