// JavaScript Document
$(function(){
		
//返回顶部按钮

goTopEx();

function goTopEx(){
		
	$(window).scroll( function() { 
		var scrollValue=$(window).scrollTop();
		scrollValue > 0 ? $('.act_pos').fadeIn("fast"):$('.act_pos').fadeOut("fast");
	} );	
        
	document.getElementById("toTop").onclick=function(){
        	 $("html, body").animate({
                 scrollTop : 0
             }, 300);
        }
    }
});