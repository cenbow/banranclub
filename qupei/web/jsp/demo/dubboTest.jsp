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
<div id="dubboTest" class="container">
	<div class="search-panel-bd">
			<table class="search-table">
				<tr>
					<th class="wd-20"><label>调用反馈信息</label></th>
					<td>
						${helloStr}
					</td>
				</tr>
			</table>
    </div>
</div>

<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>