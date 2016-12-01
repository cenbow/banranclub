<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.txt.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<title>关键字回复规则一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tReplyKeyword_list" class="container">
	<header class="page-title">
		<h1>关键字回复规则一览</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a></li>
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
					<form id="searchForm" name="searchForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
									 <tr>
										<th class="wd-10"><label>关键字</label></th>
										<td>
											<input type="text" id="tReplyKeywordDto.keyword" name="search_keyword" style="width:200px;" maxlength="50"/>
										</td>
										<th class="wd-10"><label>创建人</label></th>
										<td>
											<input type="text" id="tReplyKeywordDto.created_user_cd" name="search_created_user_cd" style="width:200px;" />
										</td>
										<th class="wd-10"><label>更新人</label></th>
										<td>
											<input type="text" id="tReplyKeywordDto.updated_user_cd" name="search_updated_user_cd" style="width:200px;" />
										</td>
									 <tr>
										<th class="wd-10"><label>启用标志</label></th>
										<td>
											<ldui:select items="${effect_flag_SelList}" id="search_effect_flag_select" name="search_effect_flag" class="easyui-combobox" style="width:200px;" />
										</td>
										<th class="wd-10"><label>匹配类型</label></th>
										<td>
											<ldui:select items="${match_SelList}" id="search_match_type_select" name="search_match_type" class="easyui-combobox" style="width:200px;" />
										</td>
										
										
										<th class="wd-10"><label>回复类型</label></th>
										<td>
											<ldui:select items="${reply_SelList}" id="search_reply_type_select" name="search_reply_type" class="easyui-combobox" style="width:200px;" />
										</td>
									  </tr>
									  </tr>
									   <tr>
										<th class="wd-10"><label>发布期间</label></th>
										<td>
											<input type="text" id="tReplyKeywordDtoPub_Startdate" name="search_pub_startdate" style="width:108px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;"></i>
											~											
											<input type="text" id="tReplyKeywordDtoPub_Enddate" name="search_pub_enddate" style="width:108px;" validType="minFirstDate['#tReplyKeywordDtoPub_Startdate']" class="easyui-datebox"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
										<th class="wd-10"><label>最后更新时间</label></th>
										<td>
											<input type="text" id="tReplyKeywordDtoCxStartdate" name="search_cx_startdate" style="width:108px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;"></i>
											~											
											<input type="text" id="tReplyKeywordDtoCxEnddate" name="search_cx_enddate" validType="minFirstDate['#tReplyKeywordDtoCxStartdate']" style="width:108px;" class="easyui-datebox"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
										<th class="wd-10"><label>启用客服模式</label></th>
										<td>
											<ldui:select items="${cust_srv_flag_SelList}" id="search_cust_srv_flag_select" name="search_cust_srv_flag" class="easyui-combobox" style="width:200px;" />
										</td>
										<!-- 
										<th class="wd-10"><label>公众号</label></th>
										<td>
											<ldui:select items="${platform_id_SelList}" id="search_platform_id_select" name="tReplyKeywordDtoPub_platform_id" class="easyui-combobox" style="width:200px;" />
										</td>
										 -->
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
				<table  id="dg_tReplyKeyword" class="easyui-datagrid" title="关键字回复规则列表" style="width:auto;height:300px;"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTReplyKeywordAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'PLATFORM_ID',width:120,sortable:'true',align:'right',hidden:'true'">公众号ID</th>
							
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'DEL',width:50,align:'center'">删除</th>
							<th data-options="field:'KEYWORD',width:120,sortable:'true',align:'center'">关键字</th>
							<th data-options="field:'PLATFORM_NAME',width:120,sortable:'true',align:'center'">公众号</th>
							<th data-options="field:'MATCH_TYPE',width:90,sortable:'true',align:'center'">匹配类型</th>
							<th data-options="field:'REPLY_TYPE',width:90,sortable:'true',align:'center'">回复类型</th>
							<th data-options="field:'EFFECT_FLAG',width:90,sortable:'true',align:'center'">启用标志</th>
							<th data-options="field:'CUST_SRV_FLAG',width:90,sortable:'true',align:'center'">启用客服模式</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'PUB_STARTDATE',width:110,sortable:'true',align:'center'">发布开始时间</th>
							<th data-options="field:'PUB_ENDDATE',width:110,sortable:'true',align:'center'">发布结束时间</th>
							<th data-options="field:'UPDATED_DATE',width:150,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tReplyKeyword_list ={};
var win_height = $(window).height(); //浏览器时下窗口可视区域高度 
var win_width = $(window).width(); //浏览器时下窗口可视区域宽度 

jQuery(function($){
	//定义清空日期
	$(".icon-clear-date").mousedown(function(){
		var id=$(this).prev().prev().attr("id");
		$("#"+id).datebox("setValue","");
    	$("#"+id).datebox("reset");
	});
	//定义构造查询
	tReplyKeyword_list.buildQueryParams=function(){
	     $('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid("options")
			.queryParams = {
					'in_tReplyKeywordQueryBean.kreply_id':$.trim($("input[name='search_kreply_id']").val()),//ID
					'in_tReplyKeywordQueryBean.keyword':$.trim($("input[name='search_keyword']").val()),//关键字
					
					'in_tReplyKeywordQueryBean.effect_flag':$.trim($("#search_effect_flag_select").combobox("getValue")),//启用标志
					'in_tReplyKeywordQueryBean.cust_srv_flag':$.trim($("#search_cust_srv_flag_select").combobox("getValue")),//启用客服模式
					'in_tReplyKeywordQueryBean.match_type':$.trim($("#search_match_type_select").combobox("getValue")),//匹配类型
					'in_tReplyKeywordQueryBean.reply_type':$.trim($("#search_reply_type_select").combobox("getValue")),//回复类型
					
					'in_tReplyKeywordQueryBean.cxstartdate':$.trim($('#tReplyKeywordDtoCxStartdate').datebox('getValue')),//最后更新开始时间
					'in_tReplyKeywordQueryBean.cxenddate':$.trim($('#tReplyKeywordDtoCxEnddate').datebox('getValue')),//最后更新结束时间
					
					'in_tReplyKeywordQueryBean.pub_startdate':$.trim($('#tReplyKeywordDtoPub_Startdate').datebox('getValue')),//发布开始时间
					'in_tReplyKeywordQueryBean.pub_enddate':$.trim($('#tReplyKeywordDtoPub_Enddate').datebox('getValue')),//发布结束时间
					
					'in_tReplyKeywordQueryBean.created_user_cd':$.trim($("input[name='search_created_user_cd']").val()),//创建人
					'in_tReplyKeywordQueryBean.updated_user_cd':$.trim($("input[name='search_updated_user_cd']").val()),//更新人
					'in_tReplyKeywordQueryBean.created_date':$.trim($("input[name='search_created_date']").val())//创建时间
					
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tReplyKeyword_list #search_btn').click(function(){
	
		if ($(searchForm).form("validate")==true){
			tReplyKeyword_list.buildQueryParams();
			$('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tReplyKeyword_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
	    
		tReplyKeyword_list.buildQueryParams();
		$('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid('load');
	});
	
	//新增
	$('div#div_tReplyKeyword_list #add_btn').click(function(){
		var add_form_id ='#addTReplyKeywordFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '关键字回复规则新增',
		    top:(win_height-600)/2,
		    left:(win_width-980)/2,
		    width: 980,
		    height: 600,
		    href: 'addTReplyKeywordPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTReplyKeywordFrom').form({   
						 url:'addTReplyKeywordAction.action',    
							     onSubmit: function(){
							        var  keyword = $("input[name='in_tReplyKeywordDto.keyword']"); 
							     	if($.trim(keyword.val()).length == 0){
							     		$.messager.alert("提示信息","关键字不可以只输入空字符","info");
							     		keyword.val('');
							     		keyword.select();
							     		return false;
							     	}
									//判断回复类型  和内容是否为空(文本消息)
									if($("#search_reply_type").combobox("getValue") == '505200000002'){
										//手动校验CKEDITOR文本域
								    	 //获取Editor编辑内容
					                    var templet_flag_TextArea_val = ue.getContent();
						    	        if (!ue.hasContents()){
								    		$.messager.alert("提示信息","请输入回复文本的内容！","info");
								    		return false;
								    	}
								    	
								    	if ($.trim(templet_flag_TextArea_val).length > 500){
								    		$.messager.alert("提示信息","回复文本的内容最大长度为500个字符!","info");
								    		return false;
								    	}
								    	//设置解码后的ck传入后台
								    	$("#text_msg_hid").val(encodeURIComponent($.trim(templet_flag_TextArea_val),"UTF-8"));
								    //(素材消息)	
									}else {
										if ('' == $("#material_id_hid").val()){
											$.messager.alert("提示信息","请选择一个素材!","info");
											return false;
										}
									}
						  			$.messager.progress();
							     },    
							     success:function(data){
							        $.messager.progress('close');
							        $.messager.alert("提示信息",JSON.parse(data).message,"info");
									//判断操作是否成功
									if (JSON.parse(data).success){
										tReplyKeyword_list.buildQueryParams();
										$('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid('load');
										$('#dialog_holder').dialog('close');
									}
							     }
						});// 
					    if($(add_form_id).form("validate")==true){
							$(add_form_id).submit();
						};   
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
	//更新
	tReplyKeyword_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTReplyKeywordFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '关键字回复规则编辑',
		    width: 980,
		    height: 600,
		    top:(win_height-600)/2,
		    left:(win_width-980)/2,
		    href: 'editTReplyKeywordPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTReplyKeywordFrom').form({   
						 url:'editTReplyKeywordAction.action',    
							     onSubmit: function(){
								     	 var  keyword = $("input[name='in_tReplyKeywordDto.keyword']"); 
								     	if($.trim(keyword.val()).length == 0){
								     		$.messager.alert("提示信息","关键字不可以只输入空字符","info");
								     		keyword.val('');
								     		keyword.select();
								     		return false;
								     	}
										 //判断回复类型  和内容是否为空(文本消息)
										if($("#search_reply_type").combobox("getValue") == '505200000002'){
									     var templet_flag_TextArea_val = ue.getContent();
						    	           if (!ue.hasContents()){
									    		$.messager.alert("提示信息","请输入回复文本的内容！","info");
									    		return false;
								    		}	
									    	if ($.trim(templet_flag_TextArea_val).length > 500){
									    		$.messager.alert("提示信息","回复文本的内容最大长度为500个字符!","info");
									    		return false;
									    	}
									    	//判断是否选中动态模板
									    	if($("#templet_flag").attr("checked")){
									    		$("#templet_flag").val("100000000001");
									    	}else{
									    		$("#templet_flag").val("100000000000");
									    	}
									    	
									    	//设置解码后的ck传入后台
									    	$("#text_msg_hid").val(encodeURIComponent($.trim(templet_flag_TextArea_val),"UTF-8"));
									    	
										}else {
											if ('' == $("#material_id_hid").val()){
												$.messager.alert("提示信息","请选择一个素材!","info");
												return false;
											}
										}
										  
						  			$.messager.progress();
							     },    
							     success:function(data){
							        $.messager.progress('close');
							        $.messager.alert("提示信息",JSON.parse(data).message,"info");
							        //判断操作是否成功
							        if (JSON.parse(data).success){
										tReplyKeyword_list.buildQueryParams();
										$('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid('load');
										$('#dialog_holder').dialog('close');
									}
							     }
						});
					    if($(edit_form_id).form("validate")==true){
					    
							$(edit_form_id).submit();
						};   
		    		
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     			$('#delTReplyKeywordFrom').form({   
						 url:'delTReplyKeywordAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
							        $.messager.alert("提示信息",JSON.parse(data).message,"info");
							        //判断操作是否成功
							        if (JSON.parse(data).success){
										tReplyKeyword_list.buildQueryParams();
										$('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid('load');
										$('#dialog_holder').dialog('close');
									}
							     }
						}); 
						$.messager.confirm("提示信息","确定要删除此条目？",function(flg){
							if(flg){
								$('#delTReplyKeywordFrom').submit();
							} 
						});
					    
		    	}
		     }
		    ,{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//详细
	tReplyKeyword_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '关键字回复规则详细',
		    width: 980,
		    height: 605,
		    top:(win_height-600)/2,
		    left:(win_width-980)/2,
		    href: 'detailTReplyKeywordPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "编   辑",
		    	handler: function(e){
		    		tReplyKeyword_list.updateFormSubmit(pkid);
		    		$('#dialog_holder').dialog('close');
		    	}
		    },{
		    	text: "删    除",
		    	handler: function(e){
		    		tReplyKeyword_list.delFormSubmit(pkid);
		    	}
		    },{
		    	text: "关  闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//删除
	tReplyKeyword_list.delFormSubmit = function (pkid){
		$.messager.confirm("提示信息","确定要删除此条目？",function(flg){
			if(flg){
				$.post('delTReplyKeywordAction.action',{"pkid":pkid},function (data){
							$.messager.alert("提示信息",JSON.parse(data).message,"info");
							if (JSON.parse(data).success){
								$('div#div_tReplyKeyword_list #dg_tReplyKeyword').datagrid('load');
				    			$('#dialog_holder').dialog('close');
							}
				});
			}
		});
	}
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>