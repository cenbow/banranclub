// JavaScript Document
$(document).ready(function(){
	//设置提示框高度
	$(".tan_div").height($("body").width()*428/640);
	//设置提示框定位top高度
	var top2=$(window).height()-$(".tan_div").height();
	$(".tan_div").css("top",top2/2);
	
	//提示框 显示
	$(".anniu").click(function(){
		$(".tan_div").fadeIn();
		$(".tanbg").fadeIn();
	});
	//提示框 关闭
	$(".tan_div_anniu").click(function(){
		$(".tan_div").fadeOut();
		$(".tanbg").fadeOut();
	});
});