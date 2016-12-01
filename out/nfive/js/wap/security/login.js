// JavaScript Document
$(document).ready(function(){
	//点直接登录
	$(".check_login").click(function(){
		$(".login").show();
		$("#yizhuce").hide();
	});
	$(".clickyzm").click(function(){
		$(this).hide();
		$(this).next().show();
	});
	$(".disableyzm").click(function(){
		$(this).hide();
		$(this).prev().show();
	});
});