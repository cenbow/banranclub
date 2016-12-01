// JavaScript Document
$(function(){
		//显示二维码
		$(".wx").mouseover(function(){
			$(".ewm").show(500);
		});
		$(".wx").mouseout(function(){
			$(".ewm").hide(500);
		});
		//首页导航
		
			$(".hoverBarLine").show();
			var wi=$(".nav_center ul li").first().width();
			//var ofle=$(".nav_center ul li").first().offsetLeft+10;
			$(".nav_center ul li").first().find("a").addClass("nav_ahovecolor").parent().siblings().find("a").removeClass("nav_ahovecolor");
			$(".hoverBarLine").css("width",wi);
			$(".hoverBarLine").animate({"left": 9}, 60, function(){});
	
		$(".nav_center ul li").mouseover(function(){
		//index页面， 鼠标放到导航栏事件
			var wi=$(this).width();
			var ofle=$(this).get(0).offsetLeft+9;
			$(".hoverBarLine1").show();
			$(".hoverBarLine1").css("width",wi);
			$(this).find("a").addClass("nav_ahovecolor1").parent().siblings().find("a").removeClass("nav_ahovecolor1");
			$(".hoverBarLine1").animate({"left": ofle}, 60, function() {});
		});
		$(".nav_center ul li").mouseout(function(){
		//index页面， 鼠标放到导航栏事件
			$(".hoverBarLine1").hide();
			$(this).parent().find("a").removeClass("nav_ahovecolor1");
			
		});
		$(".nav_center ul li").bind("click",function(){
		//index页面， 鼠标点击导航栏事件
			var wi=$(this).width();
			var ofle=$(this).get(0).offsetLeft+9;
			$(this).find("a").addClass("nav_ahovecolor").parent().siblings().find("a").removeClass("nav_ahovecolor");
			$(".hoverBarLine").css("width",wi);
			$(".hoverBarLine").animate({"left": ofle}, 60, function(){});
		});
		//遮罩层    立即预约   点击商务通按钮
		$(".img2").click(function(){
			var wi=$(document.body).width();
			var he=$(document.body).height();
			$(".loading-mask").css({"width":wi,"height":he});
			$(".loading-mask").show();
			$(".loading").show();
		});
		//关闭遮罩层
		$(".li_yyclean").click(function(){
			$(".loading-mask").hide();
			$(".loading").hide();
		});
		
		//input 姓名 获得焦点icon改变
		$(".li_yyname").focus(function(){
			$(this).prev().css({"background":"url('img/focusname.png') no-repeat 1px 0px"});
		});
		//input 姓名失去焦点
		$(".li_yyname").blur(function(){
			$(this).prev().css({"background":"url('img/zheyy.png') no-repeat scroll -12px -381px rgba(0, 0, 0, 0)"});
		});
		//input 手机号获得焦点时字体改变
		$(".li_yyphone").focus(function(){
			$(this).addClass("li_yyphonefamily");
			$(this).prev().css({"background":"url('img/focusphone.png') no-repeat  -1px 0px"});
		});
		//input 手机号失去焦点时字体改变
		$(".li_yyphone").blur(function(){
			$(this).prev().css({"background":"url('img/zheyy.png') no-repeat scroll -12px -407px rgba(0, 0, 0, 0)"});
		});
		//背景图片慢慢显示all
	$(".li_speccentdi").hover(function(){
	});	
	//广告位图片慢慢显示
	$(".li_tzlc_h").parent().hover(
			  function(){
			   $(this).find(".li_tzlc_h").stop().animate({opacity: '1'},600);
			  },
			  function(){
			   $(this).find(".li_tzlc_h").stop().animate({opacity: '0'},600);
	});
	
		
});
// 右下角定位 hover变红色背景
$(document).ready(function(){
	$(".litingbg").css('opacity', 0);//先不显示
	$(".litingbg").parent().hover(
	  function(){
	   $(this).find(".litingbg").stop().animate({opacity: '1'},200);
	  },
	  function(){
	   $(this).find(".litingbg").stop().animate({opacity: '0'},200);
	});
});
//返回顶部按钮
goTopEx();

function goTopEx(){
        var obj=document.getElementById("toTop");
        function getScrollTop(){
                return document.documentElement.scrollTop;
            }
        function setScrollTop(value){
                document.documentElement.scrollTop=value;
            }   
        window.onscroll=function(){getScrollTop()>0?obj.style.display="":obj.style.display="none";}
        obj.onclick=function(){
            var goTop=setInterval(scrollMove,10);
            function scrollMove(){
                    setScrollTop(getScrollTop()/1.1);
                    if(getScrollTop()<1)clearInterval(goTop);
                }
        }
    }