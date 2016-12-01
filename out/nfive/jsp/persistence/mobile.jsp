<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html><html lang="zh_CN">    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Style-Type" content="text/css" />
        <meta http-equiv="Content-Script-Type" content="text/javascript" />
        <meta name="author" content="NTT DATA INTRAMART CORPORATION" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1">

        <base href='http://10.1.1.249:8080/imart/' target="_self">

        <!--im CSS-->
        <link rel="stylesheet" type="text/css" href="smart/css/themes/i.css">
        <!--jQM CSS-->
        
<link rel="stylesheet" type="text/css" href="smart/libs/jquery.mobile-1.3.0/jquery.mobile-1.3.0.min.css" />

        <!--3rd CSS-->
        <link rel="stylesheet" href="smart/libs/mobiscroll-2.0/css/mobiscroll-2.0.full.min.css" />
        <link rel="stylesheet" href="smart/libs/hiroprotagonist-jquery.mobile.actionsheet-v1-17-gf766423/jquery.mobile.actionsheet.css" />
        <!--im CSS-->
        
<link rel="stylesheet" type="text/css" href="smart/css/theme.min.css" />

        
<link rel="stylesheet" type="text/css" href="smart/css/smart.min.css" />


        <!--jQuery js-->
        
<script type="text/javascript" src="ui/libs/jquery-1.7.2.min.js" ></script>

        <!--jQM js-->
        
        
<script type="text/javascript" src="smart/libs/jquery.mobile-1.3.0/jquery.mobile-1.3.0.min.js" ></script>

        <!--3rd js-->
        
<script type="text/javascript" src="smart/libs/jquery-validation-1.9.0/jquery.validate.min.js" ></script>

        <script type="text/javascript" src="smart/libs/mobiscroll-2.0/js/mobiscroll-2.0.full.min.js"></script>
        <script type="text/javascript" src="smart/libs/hiroprotagonist-jquery.mobile.actionsheet-v1-17-gf766423/jquery.mobile.actionsheet.js"></script>
        <script type="text/javascript" src="ui/libs/jquery.tmpl.min.js"></script>
        <!--im js-->
        

        
        
        
<script type="text/javascript" src="smart/js/i18n/smart-zh_CN.min.js" ></script>

        
<script type="text/javascript" src="smart/js/smart.min.js" ></script>


        <script type="text/javascript" src="smart/js/imsp-form-util.js"></script>

        
<title>销售预约（申请）</title>
<script type="text/javascript">
var imwsp = imwsp || {};
</script>
<script type="text/javascript" src="workflow/csjs/common.js"></script>
<script type="text/javascript" src="workflow/csjs/workflow_open_page.js"></script>
<script type="text/javascript" src="workflow/csjs/workflow_open_page_matter_detail.js"></script>
<script type="text/javascript" src="csjs/im_json.js"></script>
<script language="javascript" src="/imart/csjs/im_json.js"></script>
<script language="javascript" src="/imart/csjs/im_ajax_request.js"></script>
<script language="javascript" src="/imart/csjs/im_jssp_rpc.js"></script>
<script language="javascript">
    var jsAction = new Object();
    jsAction["bizCheck"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction.bizCheck.arguments.length; idx < max; idx++){argsArray[idx] = jsAction.bizCheck.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=n-q5n2j1*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=bizCheck", argsArray, null, null, "post" );};
    jsAction["_getUserDataForDisplay"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction._getUserDataForDisplay.arguments.length; idx < max; idx++){argsArray[idx] = jsAction._getUserDataForDisplay.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=n-u0bi59*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=(5f)getUserDataForDisplay", argsArray, null, null, "post" );};
    jsAction["_setSelected"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction._setSelected.arguments.length; idx < max; idx++){argsArray[idx] = jsAction._setSelected.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=nw2aw0o*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=(5f)setSelected", argsArray, null, null, "post" );};
    jsAction["doDiscontinue"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction.doDiscontinue.arguments.length; idx < max; idx++){argsArray[idx] = jsAction.doDiscontinue.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=nmrebfw*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=doDiscontinue", argsArray, null, null, "post" );};
    jsAction["doReapply"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction.doReapply.arguments.length; idx < max; idx++){argsArray[idx] = jsAction.doReapply.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=nvn4p92*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=doReapply", argsArray, null, null, "post" );};
    jsAction["doApply"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction.doApply.arguments.length; idx < max; idx++){argsArray[idx] = jsAction.doApply.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=n-zgvxjv*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=doApply", argsArray, null, null, "post" );};
    jsAction["init"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction.init.arguments.length; idx < max; idx++){argsArray[idx] = jsAction.init.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=n-5rhycq*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=init", argsArray, null, null, "post" );};
    jsAction["CRMCommonConstants"] = function(){var argsArray = new Array();for(var idx = 0, max = jsAction.CRMCommonConstants.arguments.length; idx < max; idx++){argsArray[idx] = jsAction.CRMCommonConstants.arguments[idx]}return ImJsspRpc.sendJsspRpcRequest("/imart/crm(2f)fcorder(2f)standard(2f)fcorderApply.jssprpc?im_mark=n-p4jv40*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m&im_func=CRMCommonConstants", argsArray, null, null, "post" );};
</script>
<script type="text/javascript">var ssjsRules = ({party_name:{caption:"MSG.IM.TBCN.fcorder.party_name", maxlength:100, required:true, __caption__:"\u5ba2\u6237"}, goods_name:{caption:"MSG.IM.TBCN.fcorder.goods_name", maxlength:100, required:true, __caption__:"\u4ea7\u54c1\u540d\u79f0"}, order_money:{caption:"MSG.IM.TBCN.fcorder.order_money", decimal:true, required:true, digits:[13, 2], __caption__:"\u8ba2\u5355\u91d1\u989d "}, paymoneytime:{caption:"MSG.IM.TBCN.fcorder.paymoneytime", date:true, required:true, __caption__:"\u9884\u8ba1\u6253\u6b3e\u65e5"}, process_remark:{caption:"MSG.IM.TBCN.fcorder.process_remark", maxlength:500, required:true, __caption__:"\u5907\u6ce8"}, pay_deadline:{caption:"MSG.IM.TBCN.fcorder.pay_deadline", date:true, required:true, __caption__:"\u51ed\u8bc1\u622a\u6b62\u65f6\u95f4\uff08\u65e5\u671f\uff09"}, pay_deadline_h:{caption:"MSG.IM.TBCN.fcorder.pay_deadline_h", required:true, __caption__:"\u51ed\u8bc1\u622a\u6b62\u65f6\u95f4\uff08\u65f6\uff09"}, pay_deadline_m:{caption:"MSG.IM.TBCN.fcorder.pay_deadline_m", required:true, __caption__:"\u51ed\u8bc1\u622a\u6b62\u65f6\u95f4\uff08\u5206\uff09"}, order_audit_voucher:{caption:"MSG.IM.TBCN.fcorder.order_audit_voucher", required:true, __caption__:"\u8ba2\u5355\u51ed\u8bc1\u5ba1\u6838\u64cd\u4f5c"}, returnentity:{caption:"MSG.IM.TBCN.fcorder.returnentity", required:true, __caption__:"\u9000\u5355\u4e3b\u4f53"}, party_bank_name:{caption:"\u5f00\u6237\u884c", required:true, maxlength:50, __caption__:"\u5f00\u6237\u884c"}, party_account_name:{caption:"\u5f00\u6237\u540d", required:true, maxlength:50, __caption__:"\u5f00\u6237\u540d"}, party_account_number:{caption:"\u8d26\u6237", required:true, maxlength:25, numeric:true, __caption__:"\u8d26\u6237"}, real_rate:{caption:"\u6e20\u9053\u8d39\u7387", decimal:true, digits:[13, 2], __caption__:"\u6e20\u9053\u8d39\u7387"}});var imuiDateTimeFormatPattern = 'yyyy/MM/dd HH:mm';var imuiDateFormatPattern = 'yyyy/MM/dd';var imuiTimeFormatPattern = 'HH:mm';</script><script type="text/javascript">var ssjsMessage = ({regex:"{0}\u7684\u683c\u5f0f\u4e0d\u6b63\u786e\u3002", userCd:"\u8bf7\u4ee5\u7528\u6237\u4ee3\u7801\u7684\u5f62\u5f0f\u8f93\u5165{0}\u3002", multiple:"\u4e0d\u53ef\u6307\u5b9a\u591a\u4e2a{0}\u3002", decimal:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u6570\u503c\u3002", equals:"{0}\u5fc5\u987b\u4e0e{1}\u76f8\u7b49\u3002", digits:"\u8bf7\u4ee5{1}\u4f4d\u7684\u6574\u6570\u4ee5\u53ca{2}\u4f4d\u7684\u5c0f\u6570\u6765\u8f93\u5165{0}\u3002", datetime:"{0}\u662f\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u65e5\u671f\u548c\u65f6\u95f4\u683c\u5f0f({1})\u3002", range:"\u8bf7\u8f93\u5165{0}\u5728{1}\u5230{2}\u7684\u8303\u56f4\u4e4b\u95f4\u3002", date:"{0}\u662f\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u65e5\u671f\u683c\u5f0f({1})\u3002", mimeType:"{0}\u4e3a\u672a\u7ecf\u8bb8\u53ef\u7684\u6587\u4ef6\u683c\u5f0f\u3002", file:"{0}\u4e0d\u662f\u6587\u4ef6\u3002", url:"\u8bf7\u4ee5URL\u7684\u5f62\u5f0f\u8f93\u5165{0}\u3002", alpha:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u82f1\u6587\u5b57\u6bcd\u3002", lowercase:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u5c0f\u5199\u82f1\u6587\u5b57\u6bcd\u3002", email:"\u8bf7\u4ee5\u90ae\u4ef6\u5730\u5740\u7684\u5f62\u5f0f\u8f93\u5165{0}\u3002", minlength:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165{1}\u4f4d\u4ee5\u4e0a\u7684\u5b57\u7b26\u3002", alphanumeric:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u82f1\u6587\u6570\u5b57\u3002", id:"\u8bf7\u4ee5ID\u7684\u5f62\u5f0f\u6765\u8f93\u5165{0}\u3002", integer:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u6574\u6570\u3002", uppercase:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u5927\u5199\u82f1\u6587\u5b57\u6bcd\u3002", numeric:"\u8bf7\u5728{0}\u4e2d\u8f93\u5165\u6570\u5b57\u3002", time:"{0}\u662f\u4e0d\u662f\u4e00\u4e2a\u6709\u6548\u7684\u65f6\u95f4\u683c\u5f0f({1})\u3002", isIn:"\u8bf7\u9009\u62e9\u201c{1}\u201d\u7684\u8303\u56f4\u5185\u7684\u5176\u4e2d\u4e00\u4e2a\u6765\u8f93\u5165{0}\u3002", equalTo:"{0}\u5fc5\u987b\u4e0e{1}\u76f8\u7b49\u3002", maxlength:"\u8bf7\u8f93\u5165{0}\u5728{1}\u5b57\u7b26\u4ee5\u5185\u3002", contains:"{0}\u5fc5\u987b\u5305\u542b{1}\u3002", required:"{0}\u4e3a\u5fc5\u987b\u9879\u76ee\u3002", max:"\u8bf7\u8f93\u5165\u5c0f\u4e8e{1}\u7684\u6570\u503c\u6765\u8f93\u5165{0}\u3002", min:"\u8bf7\u8f93\u5165{0}\u6bd4{1}\u5927\u7684\u6570\u503c\u3002"});</script>
<script>
    var fcorderApply = {};

    //封装订单预约数据
    fcorderApply.getOrderData = function(action) {
        var orderData = {};
        orderData.order_id = $('input[name="order_id"]').val();
        orderData.remark = $('textarea[name="process_remark"]').val();//备注
        orderData.goods_name = $('input[name="goods_name"]').val();//产品名称
        if ("apply" == action || "reapply" == action ) {
            orderData.goods_id = $('input[name="goods_id"]').val();//作为业务检查时用，订单中没有该字段
            orderData.yieldrate_id = $('input[name="goods_yieldrate_id"]').val();//产品收益率ID
            orderData.party_type = $('input[name="party_type"]').val();//参与方类型[CS:1020], 企业客户、个人客户
            orderData.party_code = $('input[name="party_code"]').val();//客户号
            orderData.goods_code = $('input[name="goods_code"]').val();//产品编号
            orderData.appoint_usercd = $('input[name="appoint_usercd"]').val();//预约人编号
            orderData.order_money = isNaN(parseFloat($('input[name="order_money"]').val())) ? 0 : parseFloat($('input[name="order_money"]').val()) * 10000;//订单金额(画面是万元，数据库是元)
            orderData.paymoneytime = $('input[name="paymoneytime"]').val();//预计打款时间
            orderData.order_rate = $('input[name="goods_yieldrate_eff_ate"]').val();//实际年化收益率
        }

        //附件
        //TODO

        return orderData;
    }

    //工作流申请处理
    fcorderApply.apply = function() {
        var applyParam = {};
        //工作流申请的必须项目
        applyParam.flowId = $('input[name="imwFlowId"]').val();
        applyParam.matterName = "XXXX-销售预约";
        applyParam.applyBaseDate = $('input[name="imwApplyBaseDate"]').val();
        applyParam.applyExecuteUserCode = $('input[name="appoint_usercd"]').val();
        applyParam.applyAuthUserCode = $('input[name="imwAuthUserCode"]').val();
        //业务执行需要的项目
        applyParam.processComment = $('textarea[name="process_remark"]').val();
        applyParam.userDataId = $('input[name="imwUserDataId"]').val();//如果不传入，则同于systemMatterId

        //画面数据
        var userParam = fcorderApply.getOrderData("apply");

        //画面检查
        if (!fcorderApply.inputCheck(userParam)) {
            return false;
        }

        //业务检查
        var bizCheck = jsAction.bizCheck(userParam);
        if (!bizCheck.resultFlag) {
            if ("0" == bizCheck.data) {
                alert(bizCheck.message.join("\n"));
                return false;
            } else {v
                if (!confirm(bizCheck.message.join("\n"))) {
                    return false;
                }
            }
        }

        //执行
        delete userParam.goods_id;//订单中没有该字段
        var applyResult = jsAction.doApply(applyParam, userParam);
        if (!applyResult.resultFlag) {
            imspShowErrorMessage(applyResult.message, [], true, 3000, false);
        } else {
            $('#backForm').submit();
        }
    }

    //工作流再申请处理
    fcorderApply.reapply = function() {

      var reapplyParam = {};
      //工作流再申请的必须项目
      reapplyParam.applyExecuteUserCode = $('input[name="appoint_usercd"]').val();
      reapplyParam.applyAuthUserCode = $('input[name="imwAuthUserCode"]').val();
      //业务执行需要的项目
      reapplyParam.processComment = $('textarea[name="process_remark"]').val();
      //其它
      reapplyParam.systemMatterId = $('input[name="imwSystemMatterId"]').val();
      reapplyParam.nodeId = $('input[name="imwNodeId"]').val();

      //画面数据
      var userParam = fcorderApply.getOrderData("reapply");

      //画面检查
      if (!fcorderApply.inputCheck(userParam)) {
        return false;
      }
      //业务检查
      var bizCheck = jsAction.bizCheck(userParam);
      if (!bizCheck.resultFlag) {
        if ("0" == bizCheck.data) {
            alert(bizCheck.message.join("\n"));
            return false;
        } else {
            if (!confirm(bizCheck.message.join("\n"))) {
                return false;
            }
        }
      }
        //执行
        delete userParam.goods_id;//订单中没有该字段
        var reapplyResult = jsAction.doReapply(reapplyParam, userParam);
        if (!reapplyResult.resultFlag) {
            imspShowErrorMessage(reapplyResult.message, [], true, 3000, false);
        } else {
            $('#backForm').submit();
        }
    }
    
    //工作流废弃处理
    fcorderApply.discontinue = function() {

      var discontinueParam = {};
      //工作流必须项目
      discontinueParam.executeUserCode = $('input[name="appoint_usercd"]').val();
      discontinueParam.authUserCode = $('input[name="imwAuthUserCode"]').val();
      //业务执行需要的项目
      discontinueParam.processComment = $('textarea[name="process_remark"]').val();
      //其它（取所在部门组织）
      discontinueParam.systemMatterId = $('input[name="imwSystemMatterId"]').val();
      discontinueParam.nodeId = $('input[name="imwNodeId"]').val();

      //画面数据
      var userParam = fcorderApply.getOrderData("discontinue");

      //执行
      var discontinueResult = jsAction.doDiscontinue(discontinueParam, userParam);
      if (!discontinueResult.resultFlag) {
        imspShowErrorMessage(discontinueResult.message, [], true, 3000, false);
      } else {
        $('#backForm').submit();
      }
    }    

    //输入检查
    fcorderApply.inputCheck = function(userData) {

        //打开disabled，使用框架imuiValidate方法
        $("input[name='party_name']").prop("disabled", false);
        $("input[name='goods_name']").prop("disabled", false);

        var validateResult = imspValidate('#workflowOpenPageForm', ssjsRules, ssjsMessage);

        //validate后关闭disabled
        $("input[name='party_name']").prop("disabled", true);
        $("input[name='goods_name']").prop("disabled", true);

        var checkMessage = [];
        var checkResult = true;
        if (""==$('input[name="goods_yieldrate_id"]').val()) {
            checkResult = false;
            checkMessage.push("收益率信息为必须项目");
        }

        //if (!fcorderApply.checkIdPhotos()) {
        //    checkResult = false;
        //    checkMessage.push("客户信息中没有证件照片时需要在附件中添加照片");
        //}

        if (!checkResult) {
            alert(checkMessage.join("\n"));
        }

        return validateResult && checkResult;
    }
    
    $(document).on('pageinit', '#imw-sp-fcorder', function() {

        //form 防止2 次提交
        imspDisableOnSubmit('form[name="workflowOpenPageForm"]', 100);

        //取消
        $('#cancel').click(function(e){
            //imspConfirm('确定放弃预约吗？', '放弃预约', function(e) {
            //    $('#backForm').submit();
            //});
            if (confirm('确定放弃预约吗？')) {
                $('#backForm').submit();
            }
        });

        //申请
        $('#apply').click(function(){
            //imspConfirm('确定预约申请吗？', '预约申请', function() {
            //    try {
            //        fcorderApply.apply();
            //    } catch (e) {
            //        imspShowErrorMessage(e.message, [e.code], true, 3000, false);
            //    }}
            //);

            if (confirm('确定预约申请吗？')) {
                try {
                    fcorderApply.apply();
                  } catch (e) {
                    imspShowErrorMessage(e.message, [e.code], true, 3000, false);
                  }
            }

        });

        //再申请
        $('#reapply').click(function(){
            //imspConfirm('确定预约再申请吗？', '预约再申请', function() {
            //  try {
            //    fcorderApply.reapply();
            //  } catch (e) {
            //    imspShowErrorMessage(e.message, [e.code], true, 3000, false);
            //  }
            //});
            if (confirm('确定预约再申请吗？')) {
                try {
                    fcorderApply.reapply();
                  } catch (e) {
                    imspShowErrorMessage(e.message, [e.code], true, 3000, false);
                  }
            }
        });

        //放弃
        $('#discontinue').click(function(){
            //imspConfirm('确定放弃预约吗？', '放弃预约', function() {
            //  try {
            //    fcorderApply.discontinue();
            //  } catch (e) {
            //    imspShowErrorMessage(e.message, [e.code], true, 3000, false);
            //  }
            //});
            if (confirm('确定放弃预约吗？')) {
                try {
                    fcorderApply.discontinue();
                  } catch (e) {
                    imspShowErrorMessage(e.message, [e.code], true, 3000, false);
                  }
            }
        });


        //返回
        $('#back').click(function(e) {
            e.preventDefault();
            $('#backForm').submit();
            return false;
        });

    });
</script>

        <script>
            (function($) {
                $(document).ready(function() {
                    var message = '';
                    var options = ({});
                    $.imspFormUtil.showSuccessMessage(message, options);
                });
            })(jQuery);
        </script>
    </head>

<body>    

<div data-role="page" data-theme="a" id="imw-sp-fcorder" >
    <div data-role="header" data-theme="a" data-position="fixed" >
        <a data-role="button" data-icon="back" id="back">返回</a>
        <h1>销售预约（申请）</h1>
    </div>
   
    <div data-role="content" >
        <form name="workflowOpenPageForm" id="workflowOpenPageForm" method="POST" onsubmit="return false;" action="/imart/im(5f)workflow(5f)smartphone(2f)common(2f)proc(2f)proc(5f)frame.jssps?im_mark=w-gxiajj*-ra07gj&im_from=crm(2f)fcorder(2f)standard(2f)fcorderApply(5f)m">
<INPUT type="hidden" name="imwOpenPageFormName" id="imwOpenPageFormName" value="workflowOpenPageForm">
<INPUT type="hidden" name="imwOpenPageFormTarget" id="imwOpenPageFormTarget" value="_top">
<INPUT type="hidden" name="imwPageType" id="imwPageType" value="">
<INPUT type="hidden" name="imwAuthUserCode" id="imwAuthUserCode" value="">
<INPUT type="hidden" name="imwNodeId" id="imwNodeId" value="">
<INPUT type="hidden" name="imwPageTypeTempSave" id="imwPageTypeTempSave" value="11">



            <ul data-role="listview">
                <li data-role="fieldcontain">
                    <label for="order_code">订单编号</label>
                    <input type="text" id="order_code" name="order_code" value='' data-theme="c" disabled="disabled" />
                    <input type="hidden" id="order_id" name="order_id" value='' />
                </li>
                <li data-role="fieldcontain">
                    <label for="appoint_username">预约人</label>
                    <input type="text" id="appoint_username" name="appoint_username" value='' data-theme="c" disabled="disabled" />
                    <input type="hidden" id="appoint_usercd" name="appoint_usercd" value='' />
                </li>
                <li data-role="fieldcontain" class="imui-smart-ui-btn-up-dummy-left">
                    <input type="hidden" id="party_code" name="party_code" value='' />
                    <input type="hidden" id="party_type" name="party_type" value='' />
                    <input type="hidden" id="certificate_id" name="certificate_id" value='' />
                    <a href="http://10.1.1.114:8080/ld_crm_tsr/jsp/persistence/apply_list_search.jsp?type=1" >
                        <label class="imui-smart-ui-required">客户信息</label>
                    </a>
                    <a href="http://10.1.1.114:8080/ld_crm_tsr/jsp/persistence/apply_list_search.jsp?type=1" class="imui-smart-ui-btn-up-dummy-right" data-icon="search" data-theme="c"></a>
                </li>
                <li data-role="fieldcontain">
                    <label for="party_name">客户</label>
                    <input type="text" id="party_name" name="party_name" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="reg_type">证件类型</label>
                    <input type="text" id="reg_type" name="reg_type" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="reg_no">证件号</label>
                    <input type="text" id="reg_no" name="reg_no" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain" class="imui-smart-ui-btn-up-dummy-left">
                    <input type="hidden" id="goods_id" name="goods_id" value='' />
                    <a href="http://10.1.1.114:8080/ld_crm_tsr/jsp/persistence/apply_list_search.jsp?type=2" >
                        <label class="imui-smart-ui-required">产品信息</label>
                    </a>
                    <a href="http://10.1.1.114:8080/ld_crm_tsr/jsp/persistence/apply_list_search.jsp?type=2" class="imui-smart-ui-btn-up-dummy-right" data-icon="search" data-theme="c"></a>
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_name">产品名称</label>
                    <input type="text" id="goods_name" name="goods_name" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_code">产品编号</label>
                    <input type="text" id="goods_code" name="goods_code" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_state">产品阶段</label>
                    <input type="text" id="goods_state" name="goods_state" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain" class="imui-smart-ui-btn-up-dummy-left">
                    <input type="hidden" id="goods_yieldrate_id" name="goods_yieldrate_id" value='' />
                    <a href="http://10.1.1.114:8080/ld_crm_tsr/jsp/persistence/apply_list_search.jsp?type=3" >
                        <label class="imui-smart-ui-required">收益率信息</label>
                    </a>
                    <a href="http://10.1.1.114:8080/ld_crm_tsr/jsp/persistence/apply_list_search.jsp?type=3" class="imui-smart-ui-btn-up-dummy-right" data-icon="search" data-theme="c"></a>
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_yieldrate_minvalue">金额范围（万元）</label>
                    <input type="text" id="goods_yieldrate_minvalue" name="goods_yieldrate_minvalue" value='' data-theme="c" disabled="disabled" />
                    ~
                    <input type="text" id="goods_yieldrate_maxvalue" name="goods_yieldrate_maxvalue" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_yieldrate_timelimit">期间范围（月）</label>
                    <input type="text" id="goods_yieldrate_timelimit" name="goods_yieldrate_timelimit" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_yieldrate_l_exp_rate">预期年化收益率</label>
                    <input type="text" id="goods_yieldrate_l_exp_rate" name="goods_yieldrate_l_exp_rate" value='' data-theme="c" disabled="disabled" />
                    ~
                    <input type="text" id="goods_yieldrate_m_exp_rate" name="goods_yieldrate_m_exp_rate" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_yieldrate_eff_ate">实际年化收益率</label>
                    <input type="text" id="goods_yieldrate_eff_ate" name="goods_yieldrate_eff_ate" value='' data-theme="c" disabled="disabled" />
                </li>
                <li data-role="fieldcontain">
                    <label for="goods_rateforyears">收益描述</label>
                    <textarea id="goods_rateforyears" name="goods_rateforyears" data-theme="c" disabled="disabled"></textarea>
                </li>
                <li data-role="fieldcontain">
                    <label for="order_money" class="imui-smart-ui-required">订单金额（万元）</label>
                    <input type="number" id="order_money" name="order_money" value='' min="0" data-theme="c" />
                </li>
                <li data-role="fieldcontain">
                    <label for="paymoneytime" class="imui-smart-ui-required">预计打款日</label>
                    <div id="paymoneytime_form" style="display:inline-block;width:100%"><span style="float:left;width:60%"><INPUT readonly name="paymoneytime" type="text" id="paymoneytime"></span><span style="float:left;padding-left:10px"><a data-role="button" href="#paymoneytime_pop" data-rel="dialog" data-icon="grid" data-iconpos="notext"></a></span><span style="float:left;"><a data-imsp-role="datePicker-remove" data-role="button" data-icon="delete" data-inline="true" data-iconpos="notext"></a></span></div><div data-role="page" id="paymoneytime_pop" ><div data-role="header" ><h3>日期设定</h3><a data-role="button" data-iconpos="notext" data-icon="gear" data-imsp-role="datePicker-change"></a></div><div data-role="content"><div data-imsp-role="calendarLayer"><span class="imui-smart-ui-calendar-header"><a data-role="button" data-imsp-role="previous" data-icon="minus" data-inline="true" data-iconpos="notext"></a></span><span class="imui-smart-ui-calendar-header imui-smart-ui-calendar-title" data-imsp-role="calendar-title"></span><span class="imui-smart-ui-calendar-header imui-smart-ui-calendar-header-right"><a data-role="button" data-imsp-role="next" data-icon="plus" data-inline="true" data-iconpos="notext"></a></span><table width="100%"  cellpadding=3 cellspacing=0 style="font-size:9pt;color:black" data-imsp-role="calendarBody"></table><a data-role="button" data-imsp-role="desideDate">决定<a></div><div data-imsp-role="dateSelectLayer" style="display:none;width:100%"><div style="float:left;width:40%;"><div style="text-align:center"><a data-role="button" data-imsp-role="datePicker-addYear" data-inline="true" data-icon="plus" data-iconpos="bottom"/></a></div><div style="text-align:center"><INPUT TYPE="text" readonly style="text-align:center"/></div><div style="text-align:center"><a data-role="button" data-imsp-role="datePicker-pullYear" data-inline="true" data-icon="minus" data-iconpos="bottom" /></a></div></div><div  style="float:left;width:40%;margin-right:0px;padding-left:15%"><div style="text-align:center"><a  data-role="button" data-imsp-role="datePicker-addMonth" data-inline="true" data-icon="plus" data-iconpos="bottom" /></a></div><div style="text-align:center"><INPUT TYPE="text" readonly  style="text-align:center"/></div><div style="text-align:center"><a data-role="button"  data-imsp-role="datePicker-pullMonth" data-inline="true" data-icon="minus" data-iconpos="bottom" /></a></div></div><a data-role="button"  data-imsp-role="datePicker-jumpDate" data-icon="check" data-inline="true" style="width:100%">设定</a></div></div></div><script>$("#paymoneytime_pop").appendTo(document.body);(function($) {$(document).bind("pagecreate", function() {$("#paymoneytime_form").find("a[data-imsp-role='datePicker-remove']").unbind("tap").tap(function() {$("input[name='paymoneytime']").val("");});$("#paymoneytime_pop").imspDialogCalendar({   calendarId:"PRC_CAL",   format:    "yyyy/MM/dd",   displayFormat: "yyyy-M-d",   dayLabels:["日", "一", "二", "三", "四", "五", "六"],   actionGetHoliday:"http://localhost:8080/ld_crm_tsr/jsp/persistence/getHoliday.json",   actionFormatDate:"http://localhost:8080/ld_crm_tsr/jsp/persistence/formatDate.json",   firstDay:0,   date:new Date("2014/04/25"),   callbackFunction:function(calendarLayer) {   var datePickerValue = calendarLayer.imspCalendar("getDateLabel");$("input[name='paymoneytime']").val(datePickerValue);   }});});})(jQuery);</script>
                </li>
                <li data-role="fieldcontain">
                    <label for="process_remark" class="imui-smart-ui-required">备注</label>
                    <textarea id="process_remark" name="process_remark" data-theme="c"></textarea>
                </li>
                
                
            </ul>
        

</form>
    </div>
    <div data-role="footer" class="imui-smart-footer" data-position="fixed" >
        <div data-role="navbar">
            <ul>
                <li><a href='http://10.1.1.188:8080/imart/home' data-icon="custom" class="im-smart-icon-common-32-home-navbar" data-iconpos="top" data-ajax="false"></a></li>
            </ul>
        </div>
    </div>

    <!--返回Form //-->
    <form data-ajax="false" method="POST" id="backForm">
        <INPUT type="hidden" name="imwCallOriginalParams" value="undefined">
    </form>

</div>
</body></html>