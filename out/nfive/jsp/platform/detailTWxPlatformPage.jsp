<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWxPlatformFrom" name="editTWxPlatformFrom" method="post" action="editTWxPlatformAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>platform_account</label></th>
								<td>
									<input type="text" id="tWxPlatformDto.platform_account" name="tWxPlatformDto.platform_account"  value="${tWxPlatformDto.platform_account}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>platform_name</label></th>
								<td>
									<input type="text" id="tWxPlatformDto.platform_name" name="tWxPlatformDto.platform_name"  value="${tWxPlatformDto.platform_name}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>platform_type</label></th>
								<td>
									<input type="text" id="tWxPlatformDto.platform_type" name="tWxPlatformDto.platform_type"  value="${tWxPlatformDto.platform_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>app_id</label></th>
								<td>
									<input type="text" id="tWxPlatformDto.app_id" name="tWxPlatformDto.app_id"  value="${tWxPlatformDto.app_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>app_secret</label></th>
								<td>
									<input type="text" id="tWxPlatformDto.app_secret" name="tWxPlatformDto.app_secret"  value="${tWxPlatformDto.app_secret}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>token</label></th>
								<td>
									<input type="text" id="tWxPlatformDto.token" name="tWxPlatformDto.token"  value="${tWxPlatformDto.token}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>