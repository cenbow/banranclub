<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/jsp/common/easyui_head.jsp"%>
		<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.msgsend.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
		<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
		<title>微信群发</title>
	</head>
	<body>
		<!--菜单-->
		<%@ include file="/jsp/common/topmenu.jsp"%>
		<!--内容-->
		<div id="div_tMsgSend_list" class="container">
			<form action="" method="post" id="crowdTMsgSendFrom" name="">
				<header class="page-title">
				<h1>
					微信群发
				</h1>
				</header>
				<div class="page-toolbar clearfix">
					<ul class="page-toolbar-list">
						<li>
							<a href="javascript:toList();" id="add_btn">群发日志一览</a>
						</li>
					</ul>
				</div>
				<article id="content" class="content">
				<table class="search-table">
					<tr>
						<th style="border: solid 0px black;" class="wd-10">
							<label>
								群发对象
								<span class="label-required"></span>:
							</label>
						</th>
						<td style="border: solid 0px black;" width="250px;">
							<ldui:select items="${bulk_target}" id="send_target"
								name="tMsgSendDto.send_target" class="easyui-combobox"
								style="width:200px;" required="true"
								validType="select['#send_target']" invalidMessage="请选择群发对象"
								missingMessage="请选择群发对象" />
						</td>
						<th  style="border: solid 0px black;" class="wd-10" id="group_flg">
							<label>
								分组名称
							</label>
						</th>
						<td style="border: solid 0px black;"  id="group_flg1">
							<ldui:select items="${weixin_group}" id="weixin_group"
								name="tMsgSendDto.weixin_group_id" class="easyui-combobox"
								style="width:200px;" validType="select['#weixin_group']" invalidMessage="请选择分组"
								missingMessage="请选择分组" />
						</td>
					</tr>
					<tr>
						<th style="border: solid 0px black;" class="wd-10">
							<label>
								消息备注
								<span class="label-required"></span>:
							</label>
						</th>
						<td style="border: solid 0px black;" colspan="3">
							<input type="text" id="remark" name="tMsgSendDto.remark"
								value="${tMsgSendDto.remark}" class="easyui-validatebox"
								style="width: 590px;" maxlength="500" required="true"
								validType="length[1,500]" invalidMessage="消息备注不能超过500个字符"
								missingMessage="请填写备注" />
						</td>
					</tr>
					<tr>
						<th style="border: solid 0px black;" class="wd-10">
							<label>
								消息类型
								<span class="label-required"></span>:
							</label>
						</th>
						<td style="border: solid 0px black;" colspan="3">
							<ldui:select items="${msg_type}" id="msg_type"
								name="tMsgSendDto.msg_type" class="easyui-combobox"
								style="width:200px;" required="true"
								validType="select['#msg_type']" invalidMessage="请选择消息类型"
								missingMessage="请选择消息类型" />
						</td>
					</tr>
					<tr height="200px;">
						<td style="border: solid 0px black;" colspan="4">
							<div style="width: 738px;">
								<%@ include file="../commonselect/commonTMaterialSelect.jsp"%></div>
						</td>
					</tr>

					<tr>
						<td style="border: solid 0px black;" align="right">
							<input id="add_btn" type="button"
								style="background: #FFDFBD; width: 100px;" value="发送"
								onclick="sub();" />
						</td>
						<td style="border: solid 0px black;" colspan="3">
							<font color="red"><b>该服务号今天还能发送消息${last_send}次。</b>
							</font>
						</td>
					</tr>
				</table>
				</article>
				<s:hidden id="send_dist" name="tMsgSendDto.send_dist"></s:hidden>
				<s:hidden name="tMsgSendDto.send_if"></s:hidden>
				<s:hidden name="tMsgSendDto.fans_group_flg"></s:hidden>
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
		})
		//群发对象改变
		$("#send_target").combobox({
			onSelect:targetChange
		})
		$("#group_flg").css("display","none");
		$("#group_flg1").css("display","none");
		changePage('505200000002');
		$("#title_th").attr("class","wd-20");
		$("#media_name").css("width","600px");
		$("#preview").css("width","738px");
		$(".panel").css("width","738px");
		$("#text_msg").css("margin-bottom","0px");
		$("#text_msg").css("line-height","25px");
		//设置隐藏标签的name
	  	$("#text_msg_hid").attr("name","tMsgSendDto.text_msg")//设置隐藏文本NAME
	  	$("#material_id_hid").attr("name","tMsgSendDto.material_id");//素材ID
	  	$("#templet_flag_hid").attr("name","tMsgSendDto.templet_flag");//是否动态模板
	});
	//发送对象改变
	function targetChange(){
		var combox_value=$(this).combobox("getValue");
		if('506000000001'==combox_value){
			$("#group_flg").hide();
			$("#group_flg1").hide();
			$("#weixin_group").removeAttr("required");
		}else{			
			$("#weixin_group").attr("data-options","required:true");
			$("#group_flg").show();
			$("#group_flg1").show();
		}
	}
	function sub(){
		var flg=true;
		//群发对象未选
		if("" == $("#send_target").combobox("getValue")){
			$.messager.alert("提示","请选择群发对象！");
			flg = false;
		//群发对象为微信分组且未选分组
		}else if("506000000002" == $("#send_target").combobox("getValue")	&&	"" == $("#weixin_group").combobox("getValue")){
			$.messager.alert("提示","请选择微信分组！");
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
				$.messager.alert("提示","请输入回复内容！");
				flg = false;
			}else if(600<content.length){
				$.messager.alert("提示","回复内容不能超过600个字符！");
				flg = false;
			}					
		//消息类型为素材且无相应ID
		}else if("506200000001" != $("#msg_type").combobox("getValue") && "" == $("#material_id_hid").val()){
			$.messager.alert("提示","请选择素材！");
			flg = false;
		}
	
		if(flg){
			$('#crowdTMsgSendFrom').form({   
				url:'crowdTMsgSendAction.action',    
					onSubmit: function(){
						$.messager.progress(); 
						//文本时清空素材ID
						if("506200000001" == $("#msg_type").combobox("getValue")){
							$("#text_msg_hid").val(encodeURIComponent(ue.getContent(),"UTF-8"));
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
			$('#crowdTMsgSendFrom').submit();
		}else{
			return false;
		}
	}
	
	function toList(){
	window.location.href='searchTMsgSendAction.action';
		//$("<form method='post' action='searchTMsgSendAction.action'></form>").submit()
	}
</script>
</html>