var accountCommon ={};

accountCommon.interValObj;			// timer变量，控制时间
accountCommon.curCount = 1;				// 当前剩余秒数
accountCommon.count = 90;			// 间隔函数，1秒执行
accountCommon.getCodeFlg = true;	// 是否允许获取验证码
accountCommon.isGetSmsCode = false;	// 是否获取过短信验证码

accountCommon._telReg = /^1[3|4|5|7|8][0-9]\d{8}$/;	// 手机合法性验证
accountCommon._telPwd = /^[a-zA-Z0-9]{8,20}$/;	// 密码合法性验证
accountCommon._imgVer = /[a-zA-Z0-9]{4}/;	// 图形验证码合法性验证
accountCommon._crm = /[a-zA-Z0-9]+$/;	// crm账户密码合法性验证
jQuery(function($){

	// 手机号Check
	accountCommon.mobileCheck = function(mobile_no){
		if (mobile_no == "") {
			return " "; // 请输入手机号码
		} else if (!(accountCommon._telReg.test(mobile_no))) {
			return "手机号码无效，请重新输入";
        }
		return "";
	};

	// 验证码Check
	accountCommon.mobValidCodeCheck = function(mob_valid_code){
		// 验证码Check
		if (!accountCommon.isGetSmsCode) {
			return " "; // 请先获取验证码
		} else if (mob_valid_code == "") {
			return " "; // 请输入短信验证码
		} else if (isNaN(mob_valid_code) || mob_valid_code.length != 6 ) {
			return "验证码格式不正确，请重新输入";
		}
		return "";
	};
	
	// 图片验证码Check
	accountCommon.imageVerifyCodeCheck = function(image_verify_code){
		// 验证码Check
		if (image_verify_code == "") {
			return " "; // 请输入图片验证码
		} else if (!(accountCommon._imgVer.test(image_verify_code)) ) {
			return "验证码格式不正确，请重新输入";
		}
		return "";
	};

	// 密码Check
	accountCommon.passwordCheck = function(password, confirm_password){
		if (password == "") {
			return " "; // 请输入密码
		} else if (password.length < 8 || password.length > 20) {
			return "密码为8~20位字母或数字，请重新输入";
		} else if (!(accountCommon._telPwd.test(password))) {
			return "密码格式不正确，请重新输入";
		}
		return "";
	};

	// 密码确认Check
	accountCommon.passwordConfirmCheck = function(password, confirm_password){
		if (confirm_password == "") {
			return " "; // 请输入密码
		} else if (confirm_password.length < 8 || confirm_password.length > 20) {
			return "密码为8~20位字母或数字，请重新输入";
		} else if (!(accountCommon._telPwd.test(confirm_password))) {
			return "密码格式不正确，请重新输入";
		} else if (password != confirm_password) {
			return "两次密码不一致，请重新输入";
		}
		return "";
	};

	// crm账户密码校验
	accountCommon.crmCheck = function(account,attr){
		if(""==account){
			return "请输入CRM"+attr;
		}else if(!(accountCommon._crm.test(account))) {
			return "CRM"+attr+"格式错误";
		} 
		return "";
	};
	
	// 设置验证码发送模式
	accountCommon.setMobValidMode = function(){
		accountCommon.curCount = accountCommon.count;
		//设置button效果，开始计时
		$("#get_mob_valid_code").html("重新获取(" + accountCommon.curCount + ")");
		$("#mob_valid_code").val("");	// 验证码

		$("#get_mob_valid_code").addClass("yzmcolo");
		$("#get_mob_valid_code").addClass("yzmco");

		accountCommon.getCodeFlg = false;
		accountCommon.interValObj = window.setInterval(setRemainTime, 1000); // 启动计时器，1秒执行一次
		accountCommon.isGetSmsCode = true;
	};

	// 发送手机验证码（发送给指定手机号）
	accountCommon.sendMobValidCodeByNew = function(mobile_no, funcCode){
		if (accountCommon.getCodeFlg) {
			if (typeof(mobile_no) != "undefined" && mobile_no != null && mobile_no != "") {
				if (accountCommon.mobileCheck(mobile_no) == "") {
					$.get("mobileVerifyCodeAction!checkMobileExist.action?time=" + new Date().getMilliseconds(), { "mobile_no": mobile_no }, function (data) {
						if (data.error) {
							var msgObj = data.msgList[0];
							var msg_id_hint = "#" + msgObj.key + "_hint";
							$(msg_id_hint).html(msgObj.msg);
						} else {
							accountCommon.setMobValidMode();	// 设置验证码发送模式
							$.post("mobileVerifyCodeAction!sendByNew.action?time=" + new Date().getMilliseconds(), { "mobile_no": mobile_no, "code": funcCode }, function (result) {
								if (data.error) {
									var msgObj = data.msgList[0];
									var msg_id_hint = "#" + msgObj.key + "_hint";
									$(msg_id_hint).html(msgObj.msg);
								}
							},"json");
						}
					},"json");
				}
			}
		}
	};

	// 发送手机验证码（发送给预留手机号）
	accountCommon.sendMobValidCodeBySys = function(funcCode){
		if (accountCommon.getCodeFlg) {
			accountCommon.setMobValidMode();	// 设置验证码发送模式
			$.ajax({
				type	: 'POST',
				url		: 'mobileVerifyCodeAction!sendBySys.action',
				async	: true,
				cache	: false,
				dataType: 'json',
				data	: {"code": funcCode},
				success : function(data){
				}
			});
		}
	};

	// Timer处理函数
	function setRemainTime() {
		if (accountCommon.curCount == 1) {
			window.clearInterval(accountCommon.interValObj);//停止计时器
			$("#get_mob_valid_code").html("重新获取");
			accountCommon.getCodeFlg = true;

			var mobile_no = $("#mobile_no").val();	// 手机号
			var errorMsg = (typeof(mobile_no) != "undefined") ? accountCommon.mobileCheck(mobile_no) : "";
			if (errorMsg == "") {
				$("#get_mob_valid_code").removeClass("yzmcolo");
				$("#get_mob_valid_code").removeClass("yzmco");
			}
		} else {
			accountCommon.curCount--;
			$("#get_mob_valid_code").html("重新获取(" + accountCommon.curCount + ")");
		}
	};

});
