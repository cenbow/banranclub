<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTPubPlatformFrom" name="editTPubPlatformFrom" method="post" action="editTPubPlatformAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>公众微信号</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.wechart_account" name="tPubPlatformDto.wechart_account"  value="${tPubPlatformDto.wechart_account}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>公众登录邮箱</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_email" name="tPubPlatformDto.platform_email"  value="${tPubPlatformDto.platform_email}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>公众号名称</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_name" name="tPubPlatformDto.platform_name"  value="${tPubPlatformDto.platform_name}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>公众号类型</label></th>
									<td>
			                            <ldui:select items="${accountTypes}" id="platform_type" name="tPubPlatformDto.platform_type" class="easyui-combobox" style="width:200px;" readonly="true"/>
                            		</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>说明信息</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.platform_desc" name="tPubPlatformDto.platform_desc"  value="${tPubPlatformDto.platform_desc}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>应用ID</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.appid" name="tPubPlatformDto.appid"  value="${tPubPlatformDto.appid}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>应用密钥</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.appsecret" name="tPubPlatformDto.appsecret"  value="${tPubPlatformDto.appsecret}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>令牌</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.token" name="tPubPlatformDto.token"  value="${tPubPlatformDto.token}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							   <tr>
								<th class="wd-20"><label>原始ID</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.org_id" name="tPubPlatformDto.org_id"  value="${tPubPlatformDto.org_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>创建时间</label></th>
								<td>
									<input type="text" id="tPubPlatformDto.created_date" name="tPubPlatformDto.created_date"  value="<fmt:formatDate value='${tPubPlatformDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>创建用户</label></th>
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