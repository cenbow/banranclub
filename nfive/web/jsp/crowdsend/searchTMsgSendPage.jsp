<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<script type="text/javascript" src="<%=jsPath%>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.msgsend.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
<title>群发日志一览</title>
<style type="text/css">
.imgicon{cursor:pointer;margin-left:-28px;}
</style>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tMsgSend_list" class="container">
	<header class="page-title">
		<h1>群发日志一览</h1>
	</header>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="searchForm" name="searchForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
									 <tr>
										<th class="wd-10"><label>批次号</label></th>
										<td>
											<input type="text" id="batch_no" name="batch_no"  value="${tMsgSendDto.batch_no}" style="width:200px;" class="easyui-validatebox"
											maxlength="30"	validType="length[0,30]" invalidMessage="批次号不能超过30个字符" missingMessage="批次号不能超过30个字符"/>
										</td>
										<th class="wd-10"><label>消息备注</label></th>
										<td>
											<input type="text" id="remark" name="remark"  value="${tMsgSendDto.remark}" style="width:200px;" class="easyui-validatebox"
												maxlength="500"	validType="length[0,500]" invalidMessage="消息备注不能超过500个字符" missingMessage="消息备注不能超过500个字符"/>
										</td>
										<th class="wd-10"><label>发送状态</label></th>
										<td>
											<ldui:select items="${send_status}" id="send_target" name="send_status" class="easyui-combobox" style="width:208px;" />
										</td>
									  </tr>
									  <tr>
										<th class="wd-10"><label>群发区分</label></th>
										<td>
											<ldui:select items="${send_dist}" id="send_target" name="send_dist" class="easyui-combobox" style="width:208px;" />
										</td>
										<th class="wd-10"><label>群发对象</label></th>
										<td>
											<ldui:select items="${send_target}" id="send_target" name="send_target" class="easyui-combobox" style="width:208px;" />
										</td>
										<th class="wd-10"><label>群发接口</label></th>
										<td>
											<ldui:select items="${send_if}" id="send_target" name="send_if" class="easyui-combobox" style="width:208px;" />
										</td>
									  </tr>
									  <tr>
										<th class="wd-10"><label>粉丝群</label></th>
										<td>
											<input type="text" id="fans_crowd_name" style="width:200px;" onfocus="icon_select('fans_crowd')"/>
											<input type="hidden" id="fans_crowd_id" name="fans_crowd"/>
											<img name="clean_icon" src="<%=jspPath%>/crowdsend/input_clean.png" alt="清除内容" class="imgicon"/>
										</td>
										<th class="wd-10"><label>活动组</label></th>
										<td>
											<input type="text" id="activity_group_name" style="width:200px;" onfocus="$.messager.alert('提示','暂未实现，等待活动组功能开发。');"/>
											<input type="hidden" id="activity_group_id" name="activity_group_id"/>
											<img name="clean_icon" src="<%=jspPath%>/crowdsend/input_clean.png" alt="清除内容" class="imgicon"/>
										</td>
										<th class="wd-10"><label>微信分组</label></th>
										<td>
											<ldui:select items="${weixin_group}" id="weixin_group" name="weixin_group" class="easyui-combobox" style="width:208px;" />
										</td>
									  </tr>
									  <tr>
									  	<th class="wd-10"><label>消息类型</label></th>
										<td>
											<ldui:select items="${msg_type}" id="send_target" name="msg_type" class="easyui-combobox" style="width:208px;" />
										</td>
										<th class="wd-10"><label>发送人</label></th>
										<td>
											<input type="text" id="created_user_name" style="width:200px;"  onfocus="icon_select('created_user')"/>
											<input type="hidden" id="created_user_id" name="created_user_cd"/>
											<img name="clean_icon" src="<%=jspPath%>/crowdsend/input_clean.png" alt="清除内容" class="imgicon"/>
										</td>
										<th class="wd-10"><label>发送时间</label></th>
										<td colspan="3">
											<input type="text" id="search_sendTime_start" name="search_sendTime_start" style="width:99px;" class="easyui-datebox" validType="againFocus['#search_sendTime_end']"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
											&nbsp;~&nbsp; 
											<input type="text" id="search_sendTime_end"   name="search_sendTime_end"   style="width:99px;" class="easyui-datebox" validType="minFirstDate['#search_sendTime_start']"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
									  </tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" class="input-btn-small" value="查 询" />
							<input id="clear_btn" type="button" class="input-btn-small" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tMsgSend" class="easyui-datagrid" title="群发日志列表" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'SEND_TIME',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMsgSendAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:40,align:'center'">详细</th>
							<th data-options="field:'MEMBER',width:40,align:'center'">明细</th>
							<th data-options="field:'BATCH_NO',width:170,sortable:'true',align:'center'">批次号</th>
							<th data-options="field:'SEND_DIST',width:70,sortable:'true',align:'center'">群发区分</th>
							<th data-options="field:'SEND_TARGET',width:60,sortable:'true',align:'center'">群发对象</th>
							<th data-options="field:'GROUP_NAME',width:100,sortable:'true',align:'center'">群组名称</th>
							<th data-options="field:'SEND_IF',width:80,sortable:'true',align:'center'">群发接口</th>
							<th data-options="field:'SEND_STATUS',width:70,sortable:'true',align:'center'">发送状态</th>
							<th data-options="field:'MSG_TYPE',width:70,sortable:'true',align:'center'">消息类型</th>
							<th data-options="field:'CREATED_USER_CD',width:80,sortable:'true',align:'center'">发送人</th>
							<th data-options="field:'SEND_TIME',width:120,sortable:'true',align:'center'">发送时间</th>
							<th data-options="field:'REMARK',width:200,sortable:'true',align:'center'">消息备注</th>
							<th data-options="field:'MSG',width:281,sortable:'true',align:'center',formatter: function(value,row,index){ return decodeURIComponent(value,'UTF-8');}">消息内容</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tMsgSend_list ={};
jQuery(function($){
	//定义构造查询
	tMsgSend_list.buildQueryParams=function(){
	     $('div#div_tMsgSend_list #dg_tMsgSend').datagrid("options")
			.queryParams = {
					'tMsgSendQueryBean.batch_no':$.trim($("input[name='batch_no']").val()),
					'tMsgSendQueryBean.remark':$.trim($("input[name='remark']").val()),
					'tMsgSendQueryBean.send_status':$.trim($("input[name='send_status']").val()),
					'tMsgSendQueryBean.send_dist':$.trim($("input[name='send_dist']").val()),
					'tMsgSendQueryBean.send_target':$.trim($("input[name='send_target']").val()),
					'tMsgSendQueryBean.send_if':$.trim($("input[name='send_if']").val()),
					'tMsgSendQueryBean.fans_crowd':$.trim($("input[name='fans_crowd']").val()),
					'tMsgSendQueryBean.activity_group_id':$.trim($("input[name='activity_group_id']").val()),
					'tMsgSendQueryBean.weixin_group_id':$.trim($("input[name='weixin_group']").val()),
					'tMsgSendQueryBean.msg_type':$.trim($("input[name='msg_type']").val()),
					'tMsgSendQueryBean.created_user_cd':$.trim($("input[name='created_user_cd']").val()),
					'tMsgSendQueryBean.search_sendTime_start':$("input[name='search_sendTime_start']").val(),
					'tMsgSendQueryBean.search_sendTime_end':$("input[name='search_sendTime_end']").val()
			}
	}

	//重新按照条件刷新查询内容
	$('div#div_tMsgSend_list #search_btn').click(function(){
	 if($('#searchForm').form("validate")==true){ 
		tMsgSend_list.buildQueryParams();
		$('div#div_tMsgSend_list #dg_tMsgSend').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMsgSend_list #clear_btn').click(function(){
			$("#search_sendTime_start").datebox("setValue","");
     		$("#search_sendTime_start").datebox("clear");
     		$("#search_sendTime_end").datebox("setValue","");
     		$("#search_sendTime_end").datebox("clear");
		//清空查询条件
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find("input[type='hidden']").val("");
	    form.find("input[type='text'][readonly!='readonly']").val("");
		tMsgSend_list.buildQueryParams();
		$('div#div_tMsgSend_list #dg_tMsgSend').datagrid('load');
	});
	
	tMsgSend_list.memberListForm = function(batch_no){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '发送明细一览',
		    width: 1090,
		    height: 650,
		    href: 'searchTMsgSendMemberAction.action',
		    modal: true,
		    method: "POST",
		    params:{batch_no:batch_no},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	//详细
	tMsgSend_list.detailFormSubmit = function(msg_id,pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '消息详细',
		    width: 880,
		    height: 603,
		    href: 'detailTMsgSendPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid,msg_id:msg_id},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	//选择图标点击回调
	tMsgSend_list.fans_crowd_select = function(id,name){
		$('#'+icon_dist+'_id').val(id);
		$('#'+icon_dist+'_name').val(name);
		$('#'+icon_dist+'_id').next().show();
		$('#dialog_holder').dialog("destroy");
	}
	
	//点击 清除时间时期的值
	 $(".icon-clear-date").mousedown(function(){
		var id1=$(this).prev().prev().attr("id");
		$("#"+id1).datebox("clear");
       var id2=$(this).next().attr("id");
       if(id2 != undefined){
	       $("#"+id2).datebox("validate");
       }
        if(id2 == undefined){
	       $("#"+id1).datebox("validate");
       }
	});
	
	//点击 清除选择框选择内容
	 $("img[name='clean_icon']").mousedown(function(){
		$(this).prev().val("");
		$(this).prev().prev().val("");
		$(this).hide();
		});
	$("img[name=clean_icon]").hide();
});

//选择图标标识（'_id'与'_name'必须一致，方便管理）
var icon_dist='';

//选择图标点击事件
function icon_select(name){
	icon_dist=name;
	var action='';
	if('created_user'==name){
		action='selectUserAction.action'
	}
	else if('fans_crowd'==name){
		action='selectTFansGroupAction.action';
	}
	$('<div id="dialog_holder"></div>').dialog({
		    title: ' ',
		    width: 900,
		    height: 600,
		    href:action,
		    modal: true,
		    method: "POST",
		    params:{'jsCallback':'tMsgSend_list.fans_crowd_select'},//页面回调
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
}
</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>