var securityCommon ={};
securityCommon._telReg = /^1[3|4|5|7|8][0-9]\d{8}$/; // 手机合法性验证
securityCommon._telPwd = /^[a-zA-Z0-9]{6,20}$/; // 密码合法性验证
securityCommon.seconds_count = 60; // 短信重发间隔时长
securityCommon.redirect_count=6; //页面自动跳转时长
jQuery(function($){

	// 隐藏微信中网页右上角按钮
	function onBridgeReady(){
		WeixinJSBridge.call('hideOptionMenu');
	}

	if (typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		}else if (document.attachEvent){
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	}else{
		onBridgeReady();
	}

    // 手机号Check
    securityCommon.mobileCheck = function(mobile_no){
        if (mobile_no == "") {
            return "请输入手机号码";
        } else if (!(securityCommon._telReg.test(mobile_no))) {
            return "手机号码无效，请重新输入";
        }
        return "";
    };

    // 错误信息提示
    securityCommon.showErrorMessage = function(errorMessage){
    	$(".wrong").stop(true);
        $(".wrong").css("opacity","1");
        $(".wrong").html(errorMessage);
        $(".wrong").animate({opacity: '0'},3000);
    }

    $(window).resize(function(){
        $('.wrong').css({
            position:'absolute',
            left: ($(window).width() - $('.wrong').outerWidth())/2,
            top: ($(window).height() - $('.wrong').outerHeight())/2
        });
    });
    // 最初运行函数
    $(window).resize();

    //发送手机验证码
    securityCommon.sendMobVerifyCode = function(mobile_no, funcCode){
        $.post("mobileVerifyCodeAction!sendByNew.action?time=" + new Date().getMilliseconds(),
               { "mobile_no": mobile_no, "code": funcCode },
               function (result){

               }
                ,"json");
    };

    // 图片验证码
    securityCommon.imageVerifyCodeCheck = function(image_verify_code){
        if (image_verify_code == "") {
            return "请输入图形验证码";
        } else if (!(/^[a-zA-Z0-9]{4}$/.test(image_verify_code)) ) {
            return "图形验证码格式不正确，请重新输入";
        }
        return "";
    };

    // 短信验证码
    securityCommon.smsVerifyCodeCheck = function(sms_verify_code){
        if (sms_verify_code == "") {
            return "请输入短信验证码";
        } else if (!(/^[0-9]{6}$/.test(sms_verify_code)) ) {
            return "短信验证码格式不正确，请重新输入";
        }
        return "";
    };

    // 密码
    securityCommon.passwordCheck = function(password){
        if (password == "") {
            return "请输入密码";
        } else if (!(/[a-zA-Z0-9]{6,20}$/.test(password))) {
            return "密码由6-20位英文字母，数字或符号组成";
        }
        return "";
    };

    //发送手机验证码（通过财富农场接口）
    securityCommon.sendMobVerifyCodeByBao = function() {
        $.post("retrievingByMobileNumberS2!ajaxSendSms.action");
    };
});
