<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTPubPlatformFrom" name="editTPubPlatformFrom" method="post" action="editTPubPlatformAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>wechart_account</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.wechart_account" name="tPubPlatformDto.wechart_account"  value="${tPubPlatformDto.wechart_account}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>platform_email</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_email" name="tPubPlatformDto.platform_email"  value="${tPubPlatformDto.platform_email}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>platform_name</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_name" name="tPubPlatformDto.platform_name"  value="${tPubPlatformDto.platform_name}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>platform_type</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_type" name="tPubPlatformDto.platform_type"  value="${tPubPlatformDto.platform_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>platform_desc</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_desc" name="tPubPlatformDto.platform_desc"  value="${tPubPlatformDto.platform_desc}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>appid</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.appid" name="tPubPlatformDto.appid"  value="${tPubPlatformDto.appid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>appsecret</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.appsecret" name="tPubPlatformDto.appsecret"  value="${tPubPlatformDto.appsecret}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.created_date" name="tPubPlatformDto.created_date"  value="<fmt:formatDate value='${tPubPlatformDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.created_user_cd" name="tPubPlatformDto.created_user_cd"  value="${tPubPlatformDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>