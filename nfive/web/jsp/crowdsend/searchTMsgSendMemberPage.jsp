<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
	String jsPath = path + "/js";
%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="tMsgSendMember_list" class="container">
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="searchMemberForm" name="searchMemberForm" method="post" >
						<input type="hidden" id="batch_no" name="batch_no" value="${batch_no}">
						<div class="search-panel-bd">
							<table class="search-table">
									 <tr>
										<th class="wd-10"><label>是否订阅</label></th>
										<td>
											<ldui:select items="${subscribe_flg}" id="subscribe_flg" name="subscribe_flg" class="easyui-combobox" style="width:188px;" />
										</td>
										<th class="wd-15"><label>已绑定一账通</label></th>
										<td>
											<ldui:select items="${oneAccount_flg}" id="oneAccount_flg" name="oneAccount_flg" class="easyui-combobox" style="width:188px;" />
										</td>
										<th class="wd-10"><label>国家</label></th>
										<td>
											<input type="text" style="width: 180px;" id="country" name="country" class="easyui-validatebox" maxlength="50" data-options="validType:length[0,50],invalidMessage:'国家名称不能超过50个字符',missingMessage:'国家名称不能超过50个字符'"/>
										</td>
									  </tr>
									  <tr>
										<th class="wd-10"><label>省份</label></th>
										<td>
											<input type="text" style="width: 180px;" id="province" name="province"  class="easyui-validatebox" maxlength="50" data-options="validType:length[0,50],invalidMessage:'省份名称不能超过50个字符',missingMessage:'省份名称不能超过50个字符'"/>
										</td>
										<th class="wd-10"><label>城市</label></th>
										<td>
											<input type="text" style="width: 180px;" id="city" name="city"  class="easyui-validatebox" maxlength="50" data-options="validType:length[0,50],invalidMessage:'城市名称不能超过50个字符',missingMessage:'城市名称不能超过50个字符'"/>
										</td>
										<th class="wd-10"><label>昵称</label></th>
										<td>
											<input type="text" style="width: 180px;" id="nick_name" name="nick_name"  class="easyui-validatebox" maxlength="25" data-options="validType:length[0,25],invalidMessage:'昵称不能超过25个字符',missingMessage:'昵称不能超过25个字符'"/>
										</td>
									  </tr>
									  <tr>
										<th class="wd-10"><label>性别</label></th>
										<td>
											<ldui:select items="${sex_flg}" id="sex" name="sex" class="easyui-combobox" style="width:188px;" />
										</td>
										<th class="wd-10"><label>备注名</label></th>
										<td>
											<input type="text" style="width: 180px;" id="remark_name" name="remark_name"  maxlength="50" data-options="validType:length[0,50],invalidMessage:'备注名不能超过50个字符',missingMessage:'备注名不能超过50个字符'"/>
										</td>
										<th class="wd-10"><label>关注时间</label></th>
										<td>
											<input type="text" id="subscribe_time_start" name="subscribe_time_start" style="width:92px;" class="easyui-datebox" validType="againFocus['#subscribe_time_end']"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
											~ 
											<input type="text" id="subscribe_time_end"   name="subscribe_time_end"   style="width:92px;" class="easyui-datebox" validType="minFirstDate['#subscribe_time_start']"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
									  </tr>
									  <tr>
										<th class="wd-10"><label>微信分组</label></th>
										<td colspan="5">
										<ldui:select items="${weixin_group}" id="weixin_group" name="weixin_group" class="easyui-combobox" style="width:188px;" />
										</td>
									 </tr>
									 <tr>
										<th class="wd-10"><label>用户群组</label></th>
										<td colspan="5" id="fans_group_td">
										<div class="easyui-panel" style="width: 890px;height: 120px;">
											<ul>
											<c:forEach items="${fans_group}" var="group">
											 <li style="width: 280px;height: auto;float: left;margin-left: 5px;word-warp:break-word;word-break:break-all"><input type="checkbox" value="${group.fans_group_id}" id="fans_group_id"/>${group.group_name}</li>
											</c:forEach>
											</ul>
										</div>
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
				<table  id="dg_tMsgSendMember" class="easyui-datagrid" title="群发成员列表" style="width:auto;height:295px;line-height: 30px;"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'SUBSCRIBE_TIME',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMsgSendMemberAction!getListData.action?batch_no=${batch_no}',method:'post'">
					<thead>
						<tr height="30px;">
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'HEAD_IMG',width:80,align:'center'">头像</th>
							<th data-options="field:'NICK_NAME',width:80,sortable:'true',align:'center'">昵称</th>
							<th data-options="field:'SEX',width:40,sortable:'true',align:'center'">性别</th>
							<th data-options="field:'REMARK_NAME',width:100,sortable:'true',align:'center'">备注名</th>
							<th data-options="field:'COUNTRY',width:70,sortable:'true',align:'center'">国家</th>
							<th data-options="field:'PROVINCE',width:80,sortable:'true',align:'center'">省份</th>
							<th data-options="field:'CITY',width:100,sortable:'true',align:'center'">城市</th>
							<th data-options="field:'WEIXIN_GROUP',width:80,sortable:'true',align:'center'">微信分组</th>
							<th data-options="field:'SUBSCRIBE_TIME',width:120,sortable:'true',align:'center'">关注时间</th>
							<th data-options="field:'VERIFY',sortable:'true',width:80,align:'center',formatter:function(value,row,index){if('null'==value || ''==value || undefined == value ){ return '否';} return value; }">已绑定一账通</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>
<script type="text/javascript">
var tMsgSendMember_list ={};
 var fansgroup_id =[];
jQuery(function($){
	//定义构造查询
	tMsgSendMember_list.buildQueryParams=function(){
	//拼装checkbox值
	
				     for(var i=0;i<$("input[type='checkbox']").length;i++){
				        if($("input[type='checkbox']")[i].checked){
				         fansgroup_id .push($("input[type='checkbox']")[i].value);
				        }
				     }	
	     $('div#tMsgSendMember_list #dg_tMsgSendMember').datagrid("options")
			.queryParams = {
				//'tWeixinFansQueryBean.batch_no':$.trim($("input[name='batch_no']").val()),	//批次号
					'tWeixinFansQueryBean.subscribe':$.trim($("input[name='subscribe_flg']").val()),	//是否订阅
					'tWeixinFansQueryBean.openid_verify':$.trim($("input[name='oneAccount_flg']").val()),		//是否绑定一账通
					'tWeixinFansQueryBean.country':$.trim($("input[name='country']").val()),	//国家
					'tWeixinFansQueryBean.province':$.trim($("input[name='province']").val()),	//省份
					'tWeixinFansQueryBean.city':$.trim($("input[name='city']").val()),			//城市
					'tWeixinFansQueryBean.nick_name':$.trim($("input[name='nick_name']").val()),//昵称
					'tWeixinFansQueryBean.sex':$.trim($("input[name='sex']").val()),			//性别
					'tWeixinFansQueryBean.remark_name':$.trim($("input[name='remark_name']").val()),//备注名
					'tWeixinFansQueryBean.weixin_group_id':$.trim($("input[name='weixin_group']").val()),//微信分组
					'tWeixinFansQueryBean.fans_grouplist':fansgroup_id.join(","),
					'tWeixinFansQueryBean.subscribe_time_start':$("input[name='subscribe_time_start']").val(),//关注时间（开始）
					'tWeixinFansQueryBean.subscribe_time_end':$("input[name='subscribe_time_end']").val()//关注时间（结束）
			}
			fansgroup_id =[];
	}
	//重新按照条件刷新查询内容
	$('div#tMsgSendMember_list #search_btn').click(function(){
		if($('#searchMemberForm').form("validate")==true){ 
			tMsgSendMember_list.buildQueryParams();
			$('div#tMsgSendMember_list #dg_tMsgSendMember').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#tMsgSendMember_list #clear_btn').click(function(){
	$("#fans_group_td input[type='checkbox']").each(function(index,element){
    	     element.checked=false;
 	      });
			$("#subscribe_time_start").datebox("setValue","");
     		$("#subscribe_time_start").datebox("clear");
     		$("#subscribe_time_end").datebox("setValue","");
     		$("#subscribe_time_end").datebox("clear");
		var batch_no = $("input[name='batch_no']").val();
		//清空查询条件
		var form = $(searchMemberForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
	    $("input[name='batch_no']").val(batch_no);
		tMsgSendMember_list.buildQueryParams();
		$('div#tMsgSendMember_list #dg_tMsgSendMember').datagrid('load');
	});
	
	//粉丝详细信息
	tMsgSendMember_list.fansDetail = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝详细',
		    width: 800,
		    height: 443,
		    href: 'detailTWeixinFansPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
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
	
})

</script>