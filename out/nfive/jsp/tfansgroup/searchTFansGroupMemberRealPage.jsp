<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<!--内容-->
<div id="div_tFansGroupMemberReal_list" class="container">
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:tFansGroupMemberReal_list.addmember();" id="add_btn"><i class="icon-add"></i>添加成员</a></li>
			<li><a href="javascript:tFansGroupMemberReal_list.removemember();" id="remove_btn"><i class="icon-remove"></i>移除成员</a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="member_searchForm" name="member_searchForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
								 <tr>
										<th class="wd-10"><label>成员名称</label></th>
										<td>
											<input type="text" id="search_member_name" name="search_member_name" style="width:120px;" maxlength="50"/>
										</td>
										<th class="wd-10"><label>星标成员</label></th>
										<td>
											<ldui:select items="${star_member_select}" id="search_star_member_select" name="search_star_member" class="easyui-combobox" style="width:120px;" />
										</td>
										<th class="wd-10"><label>入群时间</label></th>
										<td>
											<input type="text" id="in_group_startdate" name="in_group_startdate" style="width:108px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;"></i>
											~											
											<input type="text" id="in_group_enddate" name="in_group_enddate" validType="minFirstDate['#in_group_startdate']" style="width:108px;" class="easyui-datebox"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
									</tr>
									 <tr>
										<th class="wd-10"><label>昵称</label></th>
										<td>
											<input type="text"  name="nick_name" id="nick_name" style="width:120px;" maxlength="25" />
										</td>
										<th class="wd-10"><label>备注名</label></th>
										<td>
											<input type="text" name="remark_name" id="remark_name" style="width:120px;" maxlength="50" />
										</td>
										<th class="wd-10"><label></label></th>
										<td>
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
				<table  id="dg_tFansGroupMemberReal" class="easyui-datagrid" title="粉丝群成员管理" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:false,collapsible:true,sortName:'T_FANS_GROUP_MEMBER_REAL.UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTFansGroupMemberRealAction!getListData.action?tfansgroup_pkid=${tfansgroup_pkid}',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'CHK',checkbox:'true'">REAL_ID</th>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'MEMBER_NAME',width:90,sortable:'true',align:'center'">成员名称</th>
							<th data-options="field:'CREATED_DATE',width:150,sortable:'true',align:'center'">入群时间</th>
							<th data-options="field:'HEAD_IMG_URL',width:90,sortable:'true',align:'center'">头像</th>
							<th data-options="field:'NICK_NAME',width:90,sortable:'true',align:'center'">昵称</th>
							<th data-options="field:'SEX',width:50,sortable:'true',align:'center'">性别</th>
							<th data-options="field:'REMARK_NAME',width:90,sortable:'true',align:'center'">备注名</th>
							<th data-options="field:'COUNTRY',width:90,sortable:'true',align:'center'">国家</th>
							<th data-options="field:'PROVINCE',width:90,sortable:'true',align:'center'">省份</th>
							<th data-options="field:'CITY',width:90,sortable:'true',align:'center'">城市</th>
							<th data-options="field:'SUBSCRIBE_TIME',width:150,sortable:'true',align:'center'">关注时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
		<!-- 隐藏的分组ID 用于添加群成员使用 -->
		<form id="addTFansGroupMemberRealFrom">
			<input type="hidden" name="tfansgroup_pkid" value="${tfansgroup_pkid}">
			<input  type="hidden" name="fans_pkids" id="fans_pkids">
		</form>
	</article>
</div>

<script>
var tFansGroupMemberReal_list ={};
var tfansgroup_pkid = '${tfansgroup_pkid}';//粉丝群组ID
jQuery(function($){
	//定义清空日期
	$(".icon-clear-date").mousedown(function(){
		var id=$(this).prev().prev().attr("id");
		$("#"+id).datebox("setValue","");
    	$("#"+id).datebox("reset");
	});
	//定义构造查询
	tFansGroupMemberReal_list.buildQueryParams=function(){
	     $('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid("options")
			.queryParams = {
					'in_tFansGroupMemberRealQueryBean.fans_group_id':tfansgroup_pkid,//粉丝群ID
					'in_tFansGroupMemberRealQueryBean.fans_id':$.trim($("input[name='search_fans_id']").val()),//粉丝ID
					'in_tFansGroupMemberRealQueryBean.member_name':$.trim($("input[name='search_member_name']").val()),//成员名称
					'in_tFansGroupMemberRealQueryBean.star_member':$.trim($("#search_star_member_select").combobox('getValue')),//标星成员
					'in_tFansGroupMemberRealQueryBean.in_group_startdate':$.trim($("#in_group_startdate").datebox('getValue')),//入群开始时间
					'in_tFansGroupMemberRealQueryBean.in_group_enddate':$.trim($("#in_group_enddate").datebox('getValue')),//入群结束时间
					
					'in_tFansGroupMemberRealQueryBean.nick_name':$.trim($("input[name='nick_name']").val()),//昵称
					'in_tFansGroupMemberRealQueryBean.remark_name':$.trim($("input[name='remark_name']").val())//备注名
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tFansGroupMemberReal_list #search_btn').click(function(){
		if ($(member_searchForm).form("validate")==true){
			tFansGroupMemberReal_list.buildQueryParams();
			
		    $('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('clearChecked');
			$('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tFansGroupMemberReal_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(member_searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
	    
	    tFansGroupMemberReal_list.buildQueryParams();
	    $('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('clearChecked');
		$('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('load');
	});
	
	//更新
	tFansGroupMemberReal_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTFansGroupMemberRealFrom';
		$('<div id="edit_dialog_holder"></div>').dialog({
		    title: '粉丝群成员编辑',
		    width: 800,
		    height: 500,
		    href: 'editTFansGroupMemberRealPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTFansGroupMemberRealFrom').form({   
						 url:'editTFansGroupMemberRealAction.action',    
							     onSubmit: function(){
							       //校验成员名称
							       if('' == $.trim($("#edit_member_name").val())){
								     		$("#edit_member_name").val('');
								     		$("#edit_member_name").focus();
								     		return false;
								   }
								   $.messager.progress();
							     },    
							     success:function(data){
							        $.messager.progress('close');
							     	$.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroupMemberReal_list.buildQueryParams();
										$('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('load');
										$('#edit_dialog_holder').dialog('close');
									}
							     }
						});// 
					    if($(edit_form_id).form("validate")==true){
							$(edit_form_id).submit();
						};   
		    		
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//详细
	tFansGroupMemberReal_list.detailFormSubmit = function(pkid){
		$('<div id="detial_dialog_holder"></div>').dialog({
		    title: '粉丝群成员详细',
		    width: 800,
		    height: 500,
		    href: 'detailTFansGroupMemberRealPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "编  辑",
		    	handler: function(e){
		    		$('#detial_dialog_holder').dialog('close');
		    		tFansGroupMemberReal_list.updateFormSubmit(pkid);
		    	}
		    },{
		    	text: "关  闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//移除成员
	tFansGroupMemberReal_list.removemember = function(){
		 var checked = $("#dg_tFansGroupMemberReal").datagrid("getChecked");
		 if (checked.length == 0){
		 	$.messager.alert("提示信息","请选择至少一位要移除的成员！","info");
		 	return false;
		 }
		 $.messager.confirm("提示信息","确定要移除这"+checked.length+"位群成员？",function(flg){
							if(flg){
								//拼装所有的PKID
								var pkids = checked[0].REAL_ID;
								for(var i=1;i<checked.length;i++){
									pkids = pkids + "$" + checked[i].REAL_ID;
								}
								//请求移除粉丝群操作
								$.post('delTFansGroupMemberRealAction.action',{pkids:pkids},function (data){
										$.messager.alert("提示信息",JSON.parse(data).message,"info");
										$('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('load');
								});
							}
		 });
		
	
	}
	//添加群成员
	tFansGroupMemberReal_list.addmember = function (){
		var addmember_form_id ='#addTFansGroupMemberRealFrom';
		$('<div id="addmember_dialog_holder"></div>').dialog({
		    title: '选择成员',
		    width: 1050,
		    height: 630,
		    href: 'selectTWeixinFansAction.action',
		    modal: true,
		    method: "POST",
		    params:{tfansgroup_pkid:tfansgroup_pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#addTFansGroupMemberRealFrom').form({   
						url:'addTFansGroupMemberRealAction.action',    
							     onSubmit: function(){
									//1.获取选择的成员
						     		var addmemberArr = $('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('getChecked');
						     		if (addmemberArr.length == 0){
										$("#addmember_dialog_holder").dialog('close');
						    		 	return false;
						    		}
						     		//拼装所有的粉丝PKID
									var pkids = addmemberArr[0].FANS_ID;
									for(var i=1;i<addmemberArr.length;i++){
										pkids = pkids + "$" + addmemberArr[i].FANS_ID;
									}

									$("#fans_pkids").val(pkids);
									
								    $.messager.progress();
							     },    
							     success:function(data){
							        $.messager.progress('close');
							     	$.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroupMemberReal_list.buildQueryParams();
										$('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('load');
										$("#addmember_dialog_holder").dialog('close');
									}
							     }
						});// 
					    if($(addmember_form_id).form("validate")==true){
							$(addmember_form_id).submit();
						};   
		    		
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }		    
		    ,{
		    	text: "保存所有",
		    	handler: function(e){
		    		
		    		$('#addTFansGroupMemberRealFrom').form({   
						url:'addAllFansToGroupMemberAction.action',    
							     onSubmit: function(){
									//1.获取选择的成员
						     		var addmemberArr = $('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('getChecked');
						     		var data= $('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('getData');
						     		if (data.total == addmemberArr.length ){
						     			//OK
						     		}else {
							     		if ( addmemberArr.length != 10 && addmemberArr.length != 20 &&
							     		     addmemberArr.length != 30 && addmemberArr.length != 40 && addmemberArr.length != 50
							     		 ){
										    $.messager.alert("提示信息","请选择所有成员","info");								
							    		 	return false;
							    		}
						     		}
						     		//拼装所有的粉丝PKID
								    $.messager.progress();
							     },    
							     success:function(data){
							        $.messager.progress('close');
							     	$.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tFansGroupMemberReal_list.buildQueryParams();
										$('div#div_tFansGroupMemberReal_list #dg_tFansGroupMemberReal').datagrid('load');
										$("#addmember_dialog_holder").dialog('close');
									}
							     }
						});// 
					    if($(addmember_form_id).form("validate")==true){
							$(addmember_form_id).submit();
						}; 
		    	}
		    }]
		});
	}
});

</script>