<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTPlatformempCfgFrom" name="editTPlatformempCfgFrom" method="post" action="editTPlatformempCfgAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>platform_id</label></th>
								<td>
									<input type="text" id="tPlatformempCfgDto.platform_id" name="tPlatformempCfgDto.platform_id"  value="${tPlatformempCfgDto.platform_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>user_cd</label></th>
								<td>
									<input type="text" id="tPlatformempCfgDto.user_cd" name="tPlatformempCfgDto.user_cd"  value="${tPlatformempCfgDto.user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>is_use</label></th>
								<td>
									<input type="text" id="tPlatformempCfgDto.is_use" name="tPlatformempCfgDto.is_use"  value="${tPlatformempCfgDto.is_use}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>is_valid</label></th>
								<td>
									<input type="text" id="tPlatformempCfgDto.is_valid" name="tPlatformempCfgDto.is_valid"  value="${tPlatformempCfgDto.is_valid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>created_date</label></th>
								<td>
									<input type="text" id="tPlatformempCfgDto.created_date" name="tPlatformempCfgDto.created_date"  value="<fmt:formatDate value='${tPlatformempCfgDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>created_user_cd</label></th>
								<td>
									<input type="text" id="tPlatformempCfgDto.created_user_cd" name="tPlatformempCfgDto.created_user_cd"  value="${tPlatformempCfgDto.created_user_cd}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>