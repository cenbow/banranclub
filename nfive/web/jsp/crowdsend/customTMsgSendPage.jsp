<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.msgsend.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<title>自定义群发</title>
</head>
<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tMsgSend_list" class="container">
<form action="" method="post" id="customTMsgSendFrom" name="">
	<header class="page-title">
		<h1>自定义群发</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:toList();" id="add_btn">群发日志一览</a></li>
		</ul>
	</div>
	<article id="content" class="content">
	<table class="search-table" >
		<tr>
			<th style="border: solid 0px black;" class="wd-10"><label>群发接口<span class="label-required"></span>:</label></th>
			<td style="border: solid 0px black;line-height: 23px;position: relative;" width="950px;">
				<input type="radio" name="tmpMsgSendDto.send_if" value="506100000001" checked="checked" style="display: block;float: left;margin-top: 5px;"/>客服消息接口
				<p style="width:auto;border: 1px dashed red;float: left;position: absolute;left:100px;top:0px;margin-top: 5px;line-height: 20px;">&nbsp;可以发送消息给与该公众账号48小时内发生过交互的粉丝，且不限制发送次数。</p><br>
				<input  type="radio" name="tmpMsgSendDto.send_if" value="506100000002" style="display: block;float: left;margin-top: 5px;"/>高级群发接口
				<p style="width:auto;border: 1px dashed red;float: left;position: absolute;left:100px;top:0px;margin-top: 31px;line-height: 20px;">&nbsp;仅服务号可以使用，用户每月只能接受到4条消息，多余4条的群发将对该用户发送失败。</p>
			</td>
		</tr>
		<tr>
			<th style="border: solid 0px black;" class="wd-10"><label>群发对象<span class="label-required"></span>:</label></th>
			<td style="border: solid 0px black;" width="250px;">
				<ldui:select items="${bulk_target}" id="send_target" name="tmpMsgSendDto.send_target" class="easyui-combobox" style="width:200px;" 
				required="true" validType="select['#send_target']" invalidMessage="请选择群发对象" missingMessage="请选择群发对象"/>
				<div id="send_target_msg"></div>
			</td>
		</tr>
		<tr id="trfans_id">
			<th style="border: solid 0px black;" class="wd-10"><label id="fans_activity">粉丝群</label><span class="label-required"></span>:</th>
			<td style="border: solid 0px black;">
				<div id="fans" class="easyui-panel" style="width: 890px;height: 120px;">
											<ul>
											<c:forEach items="${fans_crowd}" var="group">
											 <li style="width: 280px;height: auto;float: left;margin-left: 5px;word-warp:break-word;word-break:break-all"><input name="fans_group" type="checkbox" value="${group.fans_group_id}"/>${group.group_name}</li>
											</c:forEach>
											</ul>
				</div>
				<div id="activity"><input type="text" disabled="disabled"/><i class="icon-search"></i>
				<input type="hidden" name="tmpMsgSendDto.activity_group_id" id="activity_group_id"></div>
			</td>
		</tr>
		<tr>
			<th style="border: solid 0px black;" class="wd-10"><label>消息备注<span class="label-required"></span>:</label></th>
			<td style="border: solid 0px black;" colspan="3">
				<input type="text" id="remark" name="tmpMsgSendDto.remark" value="${tMsgSendDto.remark}" class="easyui-validatebox" style="width:590px;"
				maxlength="500" required="true" validType="length[1,500]" invalidMessage="消息备注不能超过500个字符" missingMessage="请填写备注" />
			</td>			
		</tr>
		<tr>
			<th style="border: solid 0px black;" class="wd-10"><label>消息类型<span class="label-required"></span>:</label></th>
			<td style="border: solid 0px black;" colspan="3">
				<ldui:select items="${msg_type}" id="msg_type" name="tmpMsgSendDto.msg_type" class="easyui-combobox" style="width:200px;" 
				required="true" validType="select['#msg_type']" invalidMessage="请选择消息类型" missingMessage="请选择消息类型"/>
			</td>			
		</tr>
		<tr height="200px;" >
			<td style="border: solid 0px black;" colspan="4" ><div style="width: 738px;">
			<div style="width: 738px;height: 325px;">
				 <%@ include file="../commonselect/commonTMaterialSelect.jsp"%></div></div>
			</td>
		</tr>
		
		<tr>
			<td style="border: solid 0px black;" align="right"><input id="add_btn" type="button" style="background:#FFDFBD;width: 100px;" value="发送" onclick="sub();"/></td>
			<td style="border: solid 0px black;" colspan="3"><font color="red"><b>该服务号今天还能使用高级群发${last_send}次。</b></font></td>
		</tr>
	</table>
		</article>
		<input type="hidden" id="send_dist" name="tmpMsgSendDto.send_dist" value="${tMsgSendDto.send_dist}"/>
		<s:hidden id="fans_group_flg" name="tMsgSendDto.fans_group_flg"></s:hidden>
		</form>
	</div>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
<script type="text/javascript">
//页面加载初始化事件
	$(document).ready(function(){
	
		//消息类型绑定切换效果事件
		$("#msg_type").combobox({
			onSelect:keySelect
		});
		$("#send_target").combobox({
			onSelect:changgeTarget
		});
		changgeTarget();
		changePage('505200000002');
		$("#title_th").attr("class","wd-20");
		$("#media_name").css("width","600px");
		$("#preview").css("width","738px");
		$(".panel").css("width","738px");
		$("#fans").parent().css("width","890px");
		$("#other_msg").css("height","325px");
		$("#preview").css("height","258px");
		$("#preview").panel({ doSize:false});
		$("#text_msg").css("margin-bottom","0px");
		$("#text_msg").css("line-height","25px");
		//设置隐藏标签的name
	  	$("#text_msg_hid").attr("name","tMsgSendDto.text_msg")//设置隐藏文本NAME
	  	$("#material_id_hid").attr("name","tMsgSendDto.material_id");//素材ID
	  	$("#templet_flag_hid").attr("name","tMsgSendDto.templet_flag");//是否动态模板
	  	//如果该帐号本日发送超过100条，则禁选高级接口
	  	if("0" == ${last_send}){
	  		$("input[name='tmpMsgSendDto.send_if']").attr("disabled","disabled");
	  	}
	});
	function changgeTarget(){
		$("#trfans_id").show();
		$("#send_target_msg").html("");
		//切换到所有用户时不显示(全部粉丝)
		if("506000000001" == $("#send_target").combobox("getValue")){
			$("#trfans_id").hide();
			$("#fans_group_flg").val("100000000001");			
			$("#send_target_msg").html("<font color='red'>确保发送对象的准确性,请确认是否已更新了关注列表</font>");
		}	
		//粉丝群
		else if("506000000003" == $("#send_target").combobox("getValue")){
			$("#fans_activity").html("粉丝群");
			$("#fans").show();
			$("#activity").hide();
			$("#activity_group_id").val("");
			$("#fans_group_flg").val("100000000001");
		//活动组
		}else if("506000000004" == $("#send_target").combobox("getValue")){
			$("#fans_activity").html("活动组");
			$("#activity").show();
			$("#fans").hide();
			$("input[name='fans_group']").removeAttr("checked");
			$("#fans_group_flg").val("100000000002");
		}
	}
	
	function sub(){
		var flg=true;
		//群发对象未选
		if("" == $("#send_target").combobox("getValue")){
			$.messager.alert("提示","请选择群发对象！");
			flg = false;
		//群发对象为粉丝群且未选
		}else if("506000000003" == $("#send_target").combobox("getValue")&&undefined ==  $("input:checkbox[name='fans_group']:checked").val()){
			$.messager.alert("提示","请选择粉丝群！");
			flg = false;
		//群发对象为活动组且未选
		}else if("506000000004" == $("#send_target").combobox("getValue")	&&	"" == $("#activity_group_id").val()){
			$.messager.alert("提示","请选择活动组！");
			flg = false;
		//消息备注为空
		}else if("" == $.trim($("#remark").val())){
			$.messager.alert("提示","请填写消息备注！");
			flg = false;
		}else if(500 < $.trim($("#remark").val()).length){
			$.messager.alert("提示","消息备注不能超过500个字符！");
			flg = false;	
		//消息类型为空
		}else if("" == $("#msg_type").combobox("getValue")){
			$.messager.alert("提示","请选择消息类型！");
			flg = false;
		//消息类型为文本，且无文本内容
		}else if("506200000001" == $("#msg_type").combobox("getValue") ){
			var content = ue.getContent();
			if(0 == $.trim(content).length){
				$.messager.alert("提示","请输入消息内容！");
				flg = false;
			}else if(600<content.length){
				$.messager.alert("提示","消息内容不能超过600个字符！");
				flg = false;
			}					
		//消息类型为素材且无相应ID
		}else if("506200000001" != $("#msg_type").combobox("getValue") && "" == $("#material_id_hid").val()){
			$.messager.alert("提示","请选择素材！");
			flg = false;
		}
	
		if(flg){
			$('#customTMsgSendFrom').form({   
				url:'customTMsgSendAction.action',    
					onSubmit: function(){				
						$.messager.progress(); 
						//文本时清空素材ID
						if("506200000001" == $("#msg_type").combobox("getValue")){
							$("#text_msg_hid").val(encodeURIComponent($.trim(ue.getContent(),"UTF-8")));
							$("#material_id_hid").val("");
						//素材时清空文本内容和是否动态模版
						}else{
							$("#text_msg_hid").val("");
							$("#templet_flag_hid").val("");
						}							         
					},    
					success:function(data){					
						$.messager.progress('close');
						data = JSON.parse(data);
						//成功是转向群发日志一览画面
						if(data.success){
							$.messager.confirm("提示",data.msg,function(flg){if(flg){toList();}});
						}else{
							$.messager.alert("提示",data.msg,"info");
						}
				 }
			});// 
			$('#customTMsgSendFrom').submit();
		}else{
			return false;
		}
	}
	
	//发送成功或者点击链接转向【群发日志一览】
	function toList(){
		window.location.href='searchTMsgSendAction.action';
		//$("<form method='post' action='searchTMsgSendAction.action'></form>").submit()
	}
</script>
</html>