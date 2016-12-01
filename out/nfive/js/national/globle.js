/**
 * Created by Williamhiler on 16/9/13.
 */
$(function (){
	$(document).on("touchstart",".close",function (){
        $(this).parents('.mask').remove();
    });
	$(document).on("touchend",".rules",function (){
        var text = '<div class="mask">' +
            '<div class="layer home">' +
            '<h1 class="title"><span class="text">活动规则 </span><span class="close">X</span></h1>' +
            '<div class="pops">' +
            '<h2>活动时间:</h2>' +
            '<p class="index">2016年09月23日-2016年10月02日;</p>' +
            '<h2>活动规则:</h2>' +
            '<p class="index">1.邀请20个好友帮忙助力,完成即可激活翻倍卡</p>' +
            '<p class="index">2.生效期间在 <a href="javascript:void(0)" class="cfncPop">财富农场</a> 购买 <a href="javascript:void(0)" class="moneyPop">零钱包</a> 都算4倍利率；</p>' +
            '<p class="index">3.此活动仅限<span class="red">首次投资</span>零钱包用户</p>' +
            '<p class="index">4.本活动最终解释权归财富农场所有</p>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('body').append(text);
    });
	//零钱包
    $(document).on("touchend",".moneyPop",function () {
        var text = '<div class="mask">' +
            '<div class="layer home">' +
            '<h1 class="title"><span class="text">关于财富农场</span><span class="close">X</span></h1>' +
            '<div class="pops">' +
            '<p>零钱包是财富农场旗下的余额增值服务和活期资金管理服务。把钱转入零钱包即购买了财富农场的固定利率产品，可获得利率。</p>' +
            '<p>零钱包内的资金还能随时用于投资平台内的产品，零手续费，灵活提取。</p>' +
            '<p class="center"><img src="images/activity/national/cfncCode.png" alt="二维码"></p>' +
            '<p class="center">更多详情请长按关注<br/>财富农场客服财富猫</p>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('body').append(text);
    });
    //财富农场
    $(document).on("touchend",".cfncPop",function () {
        var text = '<div class="mask">' +
            '<div class="layer home">' +
            '<h1 class="title"><span class="text">关于财富农场</span><span class="close">X</span></h1>' +
            '<div class="pops">' +
            '<p>依托于另类资产管理公司，财富农场（上海南相资产管理有限公司）作为德晟资本旗下独立运营的互联网金融平台，以领先的风控体系为保障，精选优质、安全的大类资产，为信用良好的机构和个人提供短期流动性支持。</p>' +
            '<p class="center"><img src="images/activity/national/cfncCode.png" alt="二维码"></p>' +
            '<p class="center">更多详情请长按关注<br/>财富农场客服财富猫</p>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('body').append(text);
    });
    //关于利率翻倍卡
    $(document).on("touchend",".explain",function (){
        var text = '<div class="mask">' +
            '<div class="layer home">' +
            '<h1 class="title"><span class="text">关于利率翻倍卡</span><span class="close">X</span></h1>' +
            '<div class="pops">' +
            '<p><img class="slogan" src="images/activity/national/aboutCard.png" alt="slogan"></p>' +
            '<h2>使用范围</h2>' +
            '<p class="index">本卡可用于购买零钱包产品；百元起投，T+1计息提现；</p>' +
            '<p class="index">投资上限：<span class="red">1个投资账户限30万；</span></p>' +
            '<h2>生效时间</h2>' +
            '<p class="index">生效后，<span class="red">10天内</span>利率翻倍</p>' +
            '<h2>查看补贴效益</h2>' +
            '<p class="index">补贴利率于每个交易日上午十点左右发放前一个交易日持有份额的补贴，<span class="red">用户登入首页-“资金流水”，即可查看；</span></p>' +
            '<p class="index">(遇节假日顺延)</p>'+
            '<p class="center"> <img src="images/activity/national/cfncCode.png" alt="二维码"></p>' +
            '<p class="center">更多详情请长按关注<br/>财富农场客服财富猫</p>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('body').append(text);
    });
});