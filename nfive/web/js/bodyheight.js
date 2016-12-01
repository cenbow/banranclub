// JavaScript Document
$(document).ready(function(){
	//定义body高度和背景图高度一样
	$hei=(1206/750)*$(window).width();
	$(".body").height($hei);
	//alert($(document.body).height());
	
	//分享画面 预约详细高度
	$mybg=(429/750)*$(window).width();
	$(".sharebody").height($mybg);
});