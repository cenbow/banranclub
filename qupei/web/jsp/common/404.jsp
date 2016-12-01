<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/commonhead.jsp"%>
<title>404</title>
<link type="text/css" rel="stylesheet" href="<%=cssPath%>/page-messager.css" />
</head>

<body>
<div class="message-wrapper">
	<div class="message-content">
		<i class="icon icon-404"></i>
		<span class="message-text">你访问的页面不存在！&nbsp;&nbsp;<a href="javascript:history.back();">返回&gt;&gt;</a></span>
	</div>
</div>

</body>
</html>
