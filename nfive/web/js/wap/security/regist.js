// JavaScript Document
$(document).ready(function(){
	
	
	
	//点下一步 到填写校验码
	/*$("#check_phonenumb").click(function(){
		$("#phonenum").hide();
		$("#jiaoyanma").show();
	});*/
	//点下一步 到填写登录密码
	$("#check_jymnumb").click(function(){
		$("#jiaoyanma").hide();
		$("#login_password").show();
	});
	//点确定 到填写账户名
	$("#check_pwd").click(function(){
		$("#login_password").hide();
		$("#zhanghu_name").show();
	});
	//点确定 到注册成功 
	$("#check_zhname").click(function(){
		$("#zhanghu_name").hide();
		$("#reg_success").show();
	});
	
	
	//点< 返回到填写手机号画面
	$("#return_phone").click(function(){
		$("#jiaoyanma").hide();
		$("#phonenum").show();
	});
	//点< 返回到填写校验码画面
	$("#return_jym").click(function(){
		$("#jiaoyanma").show();
		$("#login_password").hide();
	});
	//点< 返回到填写密码画面
	$("#return_pwd").click(function(){
		$("#zhanghu_name").hide();
		$("#login_password").show();
	});
	//点< 返回到填写账户名画面
	$("#return_zhname").click(function(){
		$("#reg_success").hide();
		$("#zhanghu_name").show();
	});
	
	
});