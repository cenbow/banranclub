// JavaScript Document

	var sum=3;
	
function search(res){
		$(".banner-img div").each(function(){
			if(res>4){
				//判断是最后一张图片显示  依次循环
				$(".1").addClass("disblock1").siblings().removeClass("disblock1");
				$(".1").fadeIn("slow").siblings().fadeOut("slow");
				$(".b1").animate({"top":-15},200).siblings().animate({"top":0},200);
				$(".b1 div").removeClass("imgshadow").parent().siblings().find("div").addClass("imgshadow");
			//	$(".5").fadeOut("slow");
				sum=0;
				return false;
				}
			var isclass=$(this).attr("class");
			if(isclass.indexOf("disblock1")>-1){
					var nextclass=isclass.split(" ");
					var num=nextclass[0];
					var ne=Number(num)+1;
					
					$("."+ne).fadeIn("slow").siblings().fadeOut("slow");
				//	$("."+ne).fadeIn("slow");
					$("."+ne).addClass("disblock1").siblings().removeClass("disblock1");
					$(".b"+ne).animate({"top":-15},200).siblings().animate({"top":0},200);
					$(".b"+ne+" div").removeClass("imgshadow").parent().siblings().find("div").addClass("imgshadow");
					return false;	
				}
			});
			sum=sum+1;
};
$(document).ready(function(){
	
	//动态菜单
	$(".li_left").click(function(){
		$(".div1").css({"background":"#dbdbdb"});
		$(".div2").css({"background":"url('images/linextbg.png') no-repeat"});
		$(".div3").css({"background":"url('images/linextbg.png') no-repeat"});
		$(this).addClass("lihover").siblings().removeClass("lihover");
		$(this).find("img").show().parent().siblings().find("img").hide();
		$(".clgg").fadeIn().siblings("div").hide();
	});
	$(".li_center_lf").click(function(){
		$(".div1").css({"background":"#dbdbdb"});
		$(".div2").css({"background":"#dbdbdb"});
		$(".div3").css({"background":"url('images/linextbg.png') no-repeat"});
		$(this).addClass("lihover").siblings().removeClass("lihover");
		$(this).find("img").show().parent().siblings().find("img").hide();
		$(".syfp").fadeIn().siblings("div").hide();
	});
	$(".li_center_rg").click(function(){
		$(".div1").css({"background":"url('images/linextbg.png') no-repeat"});
		$(".div2").css({"background":"#dbdbdb"});
		$(".div3").css({"background":"#dbdbdb"});
		$(this).addClass("lihover").siblings().removeClass("lihover");
		$(this).find("img").show().parent().siblings().find("img").hide();
		$(".glbg").fadeIn().siblings("div").hide();
	});
	
	
	$(".li_right").click(function(){
		$(".div1").css({"background":"url('images/linextbg.png') no-repeat"});
		$(".div2").css({"background":"url('images/linextbg.png') no-repeat"});
		$(".div3").css({"background":"#dbdbdb"});
		$(this).addClass("lihover").siblings().removeClass("lihover");
		$(this).find("img").show().parent().siblings().find("img").hide();
		$(".dqqs").fadeIn().siblings("div").hide();
	});
	
	//
	
	//页面初始化时banner设置
	var se=setInterval("search(sum)",2000);
	$(".b3").animate({"top":-15},200);
	
	//banner切换点击右边
	$(".banner-right").click(function(){
		search(sum);
		//停止图片自动切换
		clearInterval(se);
		//重新启动
		se=setInterval("search(sum)",2000);
		
	});
	//banner切换点击左边
	//var sum=3;
	$(".banner-left").click(function(){
		$(".banner-img div").each(function(i){
			//alert(sum);
			if(sum<2){
				//判断是第一张图片显示  依次循环
				$(".5").addClass("disblock1").siblings().removeClass("disblock1");
				$(".5").fadeIn("slow").siblings().fadeOut("slow");
				$(".b5").animate({"top":-15},200).siblings().animate({"top":0},200);
				$(".b5 div").removeClass("imgshadow").parent().siblings().find("div").addClass("imgshadow");
				//$(".1").fadeOut("slow");
				sum=6;
				return false;
				}
			var isclass=$(this).attr("class");
			if(isclass.indexOf("disblock")>-1){
					var nextclass=isclass.split(" ");
					var num=nextclass[0];
					var ne=Number(num)-1;
					
					$("."+ne).fadeIn("slow").siblings().fadeOut("slow");
					//$("."+ne).fadeIn("slow");
					$("."+ne).addClass("disblock1").siblings().removeClass("disblock1");
					$(".b"+ne).animate({"top":-15},200).siblings().animate({"top":0},200);
					$(".b"+ne+" div").removeClass("imgshadow").parent().siblings().find("div").addClass("imgshadow");
					
					return false;	
				}
			});
			sum=sum-1;
			//停止图片自动切换
			clearInterval(se);
			//重新启动
			se=setInterval("search(sum)",2000);
		
	});
	$(".aaa li").bind("click",function(){
			var cl=$(this).attr("class");
			var cllen=cl.substring(cl.length-1);
			$(this).animate({"top":-15},200).siblings().animate({"top":0},200);
			$(this).find("div").removeClass("imgshadow").parent().siblings().find("div").addClass("imgshadow");
			$("."+cllen).fadeIn("slow").siblings().fadeOut("slow");
			$("."+cllen).addClass("disblock1").siblings().removeClass("disblock1");
			sum=Number(cllen);
			//停止图片自动切换
			clearInterval(se);
			//重新启动
			se=setInterval("search(sum)",2000);
			//alert(sum)
		});
	
});