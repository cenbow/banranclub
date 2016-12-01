<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTPlatformempCfgFrom" name="editTPlatformempCfgFrom" method="post" action="editTPlatformempCfgAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tPlatformempCfgDto.cfg_id" name="tPlatformempCfgDto.cfg_id" value="${tPlatformempCfgDto.cfg_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>platform_id</label></th>
									<td>
										<input type="text" id="tPlatformempCfgDto.platform_id" name="tPlatformempCfgDto.platform_id" value="${tPlatformempCfgDto.platform_id}" class="easyui-validatebox" data-options="required:true,invalidMessage:'platform_id不能为空',missingMessage:'platform_id不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>user_cd</label></th>
									<td>
										<input type="text" id="tPlatformempCfgDto.user_cd" name="tPlatformempCfgDto.user_cd" value="${tPlatformempCfgDto.user_cd}" class="easyui-validatebox" data-options="required:true,invalidMessage:'user_cd不能为空',missingMessage:'user_cd不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>is_use</label></th>
									<td>
										<input type="text" id="tPlatformempCfgDto.is_use" name="tPlatformempCfgDto.is_use" value="${tPlatformempCfgDto.is_use}" class="easyui-validatebox" data-options="required:true,invalidMessage:'is_use不能为空',missingMessage:'is_use不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>is_valid</label></th>
									<td>
										<input type="text" id="tPlatformempCfgDto.is_valid" name="tPlatformempCfgDto.is_valid" value="${tPlatformempCfgDto.is_valid}" class="easyui-validatebox" data-options="required:true,invalidMessage:'is_valid不能为空',missingMessage:'is_valid不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								<tr>
									<th class="wd-20"><label>created_date</label></th>
									<td>
										<input type="text" id="tPlatformempCfgDto.created_date" name="tPlatformempCfgDto.created_date" value="${tPlatformempCfgDto.created_date}" class="easyui-datebox" data-options="required:true,invalidMessage:'created_date格式必须为yyyy-mm-dd',missingMessage:'created_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
									</td>
								 </tr>
								 <tr>
									<th class="wd-20"><label>created_user_cd</label></th>
									<td>
										<input type="text" id="tPlatformempCfgDto.created_user_cd" name="tPlatformempCfgDto.created_user_cd" value="${tPlatformempCfgDto.created_user_cd}" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
									</td>
								  </tr>
					</table>
				</div>
			</form>
			<form id="delTPlatformempCfgFrom" name="delTPlatformempCfgFrom" method="post" action="delTPlatformempCfgAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tPlatformempCfgDto.cfg_id}" />
			</form>
		</div>
	</div>
</div>