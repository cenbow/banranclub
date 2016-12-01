<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width,user-scalable=0">
	<style type="text/css">
		*{margin:0; padding:0}
		table{border:1px dashed #B9B9DD;font-size:12pt}
		td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
	</style>
</head>
<title>OAuth2.0网页授权</title>
<body>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td>${openIdUserInfo.openId}</td></tr>
		<tr><td>昵称</td><td>${openIdUserInfo.nickname}</td></tr>
		<tr><td>性别</td><td>${openIdUserInfo.sex}</td></tr>
		<tr><td>国家</td><td>${openIdUserInfo.country}</td></tr>
		<tr><td>省份</td><td>${openIdUserInfo.province}</td></tr>
		<tr><td>城市</td><td>${openIdUserInfo.city}</td></tr>
		<tr><td>头像</td><td>${openIdUserInfo.headImgUrl}</td></tr>
		<tr><td>特权</td><td>${openIdUserInfo.privilegeList}</td></tr>
	</table>
</body>
</html>
