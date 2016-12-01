<!--THIS IS UEDITOR HEAD CODE-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String path1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	String jsPath1 = path1 + "/js/";
%>
<script>window.UEDITOR_HOME_URL = "<%=jsPath1%>/ueditor/";</script>
<script type="text/javascript" src="<%=jsPath1%>ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=jsPath1%>ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="<%=jsPath1%>ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=jsPath1%>/uehelper.js"></script>
