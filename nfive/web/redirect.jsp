<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<%@ include file="/jsp/common/commonhead.jsp"%>
<title>跳转中...</title>
<link type="text/css" rel="stylesheet" href="<%=cssPath%>/page-messager.css" />
</head>

<body>
<style>
.panel-body .message-wrapper{margin-top:30px; font-size:14px; color:#ff0000;}
.panel-body .message-content{ text-align:center;}
.panel-body .icon-error {display:none;}
.panel-body .message-text { padding:10px 15px; border:2px solid #cc0000; display:inline-block;}
</style>
<div class="message-wrapper">
	<div class="message-content">
		<i class="icon icon-error"></i>
		<span class="message-text">请求无效,请重新登录,谢谢!</span>
	</div>
</div>
<p style="text-align:center; margin-top:15px; font-size:14px; margin-bottom:10px;"><span id="timer">5</span>秒后跳转到登录页..., <a href="<%=request.getContextPath()%>/login.jsp">立即跳转&gt;&gt;</a></p>

<script type="text/javascript">
	function counterDown(callback){
		var count = 6;
		var span = document.getElementById("timer");
		var time;
		var start = function(){
			if( count == 1 ){
				clearTimeout(time);
				typeof callback == 'function' && callback();
				return;
			}
			count--;
			span.innerText = count;
			time = setTimeout(arguments.callee,1000);
		}
		start();
	}
	counterDown(function(){
		window.location.href = "<%=request.getContextPath()%>/login.jsp";
	});
</script>

</body>
</html>