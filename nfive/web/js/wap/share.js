	
	
/****   微信分享事件***/

var shareUtil={};

jQuery(function($){
	
	//构造分享参数
	shareUtil.buildShareInfo = function(imgUrl,lineLink,descContent,shareTitle,appid,lineTitle){

		// 显示微信中网页右上角按钮
		shareUtil.showOptionMenu();
		
		shareUtil.imgUrl = imgUrl;//图片
		shareUtil.lineLink =lineLink;//链接路径
		shareUtil.descContent = descContent;//描述
		shareUtil.shareTitle = shareTitle;//标题
		shareUtil.appid = appid;//appid
		shareUtil.lineTitle = lineTitle;//分享到朋友圈的文案
		
		// lineTitle为空时，使用shareTitle替换
		if (typeof(shareUtil.lineTitle) == "undefined" || shareUtil.lineTitle == null || shareUtil.lineTitle == "") {
			shareUtil.lineTitle = shareTitle;
		}
	}
	
	//发送给朋友
	shareUtil.shareFriend = function () {
		
	    WeixinJSBridge.invoke('sendAppMessage',{
	                            "appid": shareUtil.appid,
	                            "img_url": shareUtil.imgUrl,
	                            "img_width": "640",
	                            "img_height": "640",
	                            "link": shareUtil.lineLink,
	                            "desc": shareUtil.descContent,
	                            "title": shareUtil.shareTitle
	                            }, function(res) {
	                            _report('send_msg', res.err_msg);
	                            });
	}
	//分享到朋友圈
	shareUtil.shareTimeline = function () {
	    WeixinJSBridge.invoke('shareTimeline',{
	                            "img_url": shareUtil.imgUrl,
	                            "img_width": "640",
	                            "img_height": "640",
	                            "link": shareUtil.lineLink,
	                            "desc": shareUtil.descContent,
	                            "title": shareUtil.lineTitle
	                            }, function(res) {
	                            _report('timeline', res.err_msg);
	                            });
	}
	
	// 隐藏微信中网页右上角按钮
	shareUtil.hideOptionMenu = function () {
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
	}

	// 显示微信中网页右上角按钮
	shareUtil.showOptionMenu = function () {
		function onBridgeReady(){
			WeixinJSBridge.call('showOptionMenu');
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
	}
	
	//判断是否为微信浏览器
	var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
		// 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
		document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		
		        // 发送给好友
		        WeixinJSBridge.on('menu:share:appmessage', function(argv){
		        		shareUtil.shareFriend();
		            });
		
		        // 分享到朋友圈
		        WeixinJSBridge.on('menu:share:timeline', function(argv){
		        		shareUtil.shareTimeline();
		            });

		        // 隐藏微信中网页右上角按钮
		        WeixinJSBridge.call('hideOptionMenu');

		        }, false);
	}
});