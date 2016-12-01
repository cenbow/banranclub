<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>微信管理平台错误提示页</title>
<%@ include file="/jsp/common/easyui_head.jsp"%>

</head>

<body>
	<!--菜单-->
	<%@ include file="/jsp/common/topmenu.jsp"%>
	<div id="div_tEmpUser_list" class="container">
	
		<header class="page-title">
        	<h1>微信管理平台错误提示页</h1>
        </header>
		<article id="content" class="content">
           <div style="width:100px;margin:0 auto;">   
            	<!-- 内容显示区 -->
            	<img src="images/error.jpg"> 
            </div>
            <center><font size="10px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errorMessage} </font></center>
        </article>
   </div>

<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>

