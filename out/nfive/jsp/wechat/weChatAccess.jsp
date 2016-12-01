<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
</head>
<title>WeChat</title>
<!--菜单-->
<body>
	<table>
		<tr>
		<td>signature</td>
		<td>${signature}</td>
		</tr>
		<tr>
		<td>timestamp</td>
		<td>${timestamp}</td>
		</tr>
		<tr>
		<td>nonce</td> 
		<td>${nonce}</td>
		</tr>
		<tr>
		<td>echostr</td>
		<td>${echostr}</td>
		</tr>
		<tr>
		<td>pwd</td>
		<td>${pwd}</td>
		</tr>
	</table>
</body>
</html>
