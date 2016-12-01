<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<title>模板内容页</title>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="dubboTest" class="container">
<!--内容-->
<div id="dubboTest" class="container">
		<form id="dubboTestFrom" name="dubboTestFrom" method="post" action="dubboTestAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>
							<th class="wd-20"><label>dubboKey</label></th>
							<td>
								<input type="text" id="name" name="name"  class="easyui-validatebox"  data-options="required:true,invalidMessage:'name不能为空',missingMessage:'dubboKey不能为空'"  style="width:200px;" />
							</td>
						</tr>
						
						<tr>
							<td colspan=2>
							  <input id="submitdubbo" type="submit" class="input-btn-small" value="提交" />
							</td>
						</tr>
							  
					
					</table>
					
					
				</div>
			</form>
			
</div>

</div>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>