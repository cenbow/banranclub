<!--THIS IS COMMON HEAD CODE-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"   uri="/struts-tags"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="expires" content="0">

<meta charset="UTF-8">
<meta name="description" content="css3 flexbox, horizontal" />
<meta name="viewport" content="width=640" />
<meta name="viewport" content="width=1536" />
<meta name="viewport" content="initial-scale=1,maximum-scale=1,minimum-scale=1" />
<meta name="viewport" content="initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5" />
<meta name="viewport" content="width=device-width" />
<meta name="keywords" content="css3, html5" />
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
<!--------------------------------------------------------------->
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
	String basePath = path + "/";
	String cssPath = path + "/css";
	String jsPath = path + "/js";
	String jspPath = path + "/jsp";
	String imgPath = path + "/images";
	
	String requestUrl = request.getRequestURL().toString();
	//研报
	String reportIconPath =  "images/wap/report.png";
	//产品
	String productIconPath = "images/wap/product.png";
	//预约
	String appointIconPath = "images/wap/appoint.png";
	//账户
	String accountIconPath = "images/wap/account.png";
	
	if (requestUrl.indexOf("report") > 0){
	
		reportIconPath = "images/wap/report_sel.png";
		
	}else if (requestUrl.indexOf("/product") > 0){
	
		productIconPath = "images/wap/product_sel.png";
	}
	else if (requestUrl.indexOf("/appoint") > 0){
	
		appointIconPath = "images/wap/appoint_sel.png";
	}
	else if (requestUrl.indexOf("/account") > 0){
	
		accountIconPath = "images/wap/account_sel.png";
	}
%>
<!--------------------------------------------------------------->
<!--[if lt IE 9]>
<script type="text/javascript" src="/js/html5shiv.js"></script>
<![endif]-->
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/wap/share.js"></script>
<script type="text/javascript">
	// 判断是手机访问还是电脑访问
	if(navigator.userAgent.indexOf("iPhone") != -1||navigator.userAgent.indexOf("Linux") != -1) {	// 移动设备
		$("<link>").attr({ rel: "stylesheet", type: "text/css", href: "css/wap/mobilePhone.css"}).appendTo("head");
        //window.scrollTo(0,0);	//回到顶部
		//function preventDefault(e) { e.preventDefault(); };  
		//document.addEventListener('touchmove', preventDefault, false); 
		
	}
	if(navigator.userAgent.indexOf("iPad") != -1){	// iPad
		$("<link>").attr({ rel: "stylesheet", type: "text/css", href: "css/wap/mobileIpad.css"}).appendTo("head");
		//function preventDefault(e) { e.preventDefault(); };  
		//document.addEventListener('touchmove', preventDefault, false); 
	}
	if(navigator.userAgent.indexOf("Win") != -1){  	// PC
		$("<link>").attr({ rel: "stylesheet", type: "text/css", href: "css/wap/mobilePhone.css"}).appendTo("head");
	}
</script>
