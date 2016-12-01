<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
				<div class="search-panel-bd">
			<form id="addTPubPlatformFrom" name="addTPubPlatformFrom" method="post"  action="addTPubPlatformAction.action" >
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>公众微信号</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_wechart_account" name="tPubPlatformDto.wechart_account" class="easyui-validatebox" data-options="required:true,invalidMessage:'公众微信号不能为空',missingMessage:'公众微信号不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>公众登录邮箱</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_platform_email" name="tPubPlatformDto.platform_email" class="easyui-validatebox" data-options="required:true,invalidMessage:'邮件不能为空',missingMessage:'邮件不能为空'" missingMessage="邮件必须填写" validType="email" invalidMessage="请填写正确的邮件格式" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>公众号名称</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_platform_name" name="tPubPlatformDto.platform_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'公众号名称不能为空',missingMessage:'公众号名称不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>公众号类型</label></th>
							<td>
                              <ldui:select items="${accountTypes}" id="platform_type" name="tPubPlatformDto.platform_type" 
                              class="easyui-combobox" style="width:200px;" />
                            </td>

						  </tr>
						 <tr>
							<th class="wd-20"><label>说明信息</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_platform_desc" name="tPubPlatformDto.platform_desc" class="easyui-validatebox"  style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>应用ID</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_appid" name="tPubPlatformDto.appid" class="easyui-validatebox" data-options="required:true,invalidMessage:'应用ID不能为空',missingMessage:'应用ID不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>应用密钥</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_appsecret" name="tPubPlatformDto.appsecret" class="easyui-validatebox" data-options="required:true,invalidMessage:'应用密钥不能为空',missingMessage:'应用密钥不能为空'" style="width:200px;"/>
							</td>
						 </tr>
						<tr>
							<th class="wd-20"><label>令牌</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_token" name="tPubPlatformDto.token" class="easyui-validatebox" data-options="required:true,invalidMessage:'令牌不能为空',missingMessage:'令牌不能为空'" style="width:200px;"/>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>原始ID</label></th>
							<td>
								<input type="text" id="tPubPlatformDto_org_id" name="tPubPlatformDto.org_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'原始ID不能为空',missingMessage:'原始ID令牌不能为空'" style="width:200px;"/>
							</td>
						</tr>
				  </table>
				  </form>
				</div>
		</div>
	</div>
</div>