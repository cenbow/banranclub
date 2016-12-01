<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<%@ include file="/jsp/common/wap_head_account.jsp"%>
		<style>
/*-16px -318px*/
* {
	margin: 0px;
	padding: 0px;
}

body {
	background: url("<%=imgPath%>/wap/errorbg2.png") no-repeat;
	background-size: cover;
	height: 100%;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
}

.error-center {
	width: 100%;
	height: 100%;
}

.errortitle {
	color: white;
	font-size: 22px;
	font-family: "宋体";
	margin-bottom: 5px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
}
</style>
	</head>
	<body>
		<div class="error-center">
			<img src="<%=imgPath%>/wap/blue.png"
				style="width: 100%; position: absolute; top: 25%; left: 0;" />
			<div style="position: absolute; top: 32%; left: 15%">
				<p class="errortitle">
					系统繁忙，请稍后再试！
				</p>
				<br/>
				<p
					style="margin-right: 20%; font-size: 14px; color: #fff; line-height: 18px; letter-spacing: 1px;">
					尊敬的会员您好，现在系统繁忙，如需帮助，请联系客服。
				</p>
			</div>
		</div>
		<img src="<%=imgPath%>/wap/ren.png"
			style="position: absolute; bottom: 10%; left: 0; width: 100%;">
		<script type="text/javascript">
	document.addEventListener('touchmove', function(e) {
		e.preventDefault();
	}, false);
</script>
	</body>
</html>
