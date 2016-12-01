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
<title>特殊回复规则一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tReplySpecial_list" class="container">
	<header class="page-title">
		<h1>特殊回复规则一览</h1>
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
										<th class="wd-10"><label>特殊回复规则:</label></th>
										<td>
										<ldui:select items="${rule_typeList}" class="easyui-combobox" id="tReplySpecialDto.rule_type" name="search_rule_type" style="width:140px;"/>
										</td>
										<th class="wd-10"><label>回复类型:</label></th>
										<td>
											<ldui:select items="${reply_typeList}" class="easyui-combobox" id="tReplySpecialDto.reply_type" name="search_reply_type" style="width:140px;"/>
										</td>
										<th class="wd-10"><label>启用标志:</label></th>
										<td>
											<ldui:select items="${effect_flagList}"  class="easyui-combobox" id="tReplySpecialDto.effect_flag" name="search_effect_flag" style="width:140px;"/>
										</td>
									</tr>
									<tr>
										<th class="wd-10"><label>创建人:</label></th>
										<td>
											<input type="text" id="tReplySpecialDto.created_user_cd"  class="easyui-validatebox" name="search_created_user_cd"/>  
										</td>
										<th class="wd-10"><label>更新人:</label></th>
										<td>
											<input type="text" id="tReplySpecialDto.updated_user_cd"  class="easyui-validatebox" name="search_updated_user_cd"/>  
										</td>
										<th class="wd-10"><label>最后更新时间:</label></th>
										<td colspan="3">
											<input type="text" id="search_updated_date"  class="easyui-datebox" name="search_updated_date"  validType="againFocus['#search_updated_date1']" style="width: 120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp;
											<input type="text" id="search_updated_date1"  class="easyui-datebox" name="search_updated_date1"  validType="minFirstDate['#search_updated_date']" style="width: 120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
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
				<table  id="dg_tReplySpecial" class="easyui-datagrid" title="特殊回复规则一览表" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTReplySpecialAction!getListData.action',method:'post'">
					<thead>
						<tr>
						    <th data-options="field:'FREPLY_ID',hidden:'true',sortable:'true',align:'right'">freply_id</th>
						    <th data-options="field:'DETAIL',width:60,align:'center'">预览</th>
							<th data-options="field:'EDIT',width:60,align:'center'">编辑</th>
							<th data-options="field:'DEL',width:60,align:'center'">删除</th>
							<th data-options="field:'RULE_TYPE',width:180,sortable:'true',align:'center'">特殊回复规则</th>
							<th data-options="field:'REPLY_TYPE',width:160,sortable:'true',align:'center'">回复类型</th>
							<th data-options="field:'EFFECT_FLAG',width:100,sortable:'true',align:'center'">启用标志</th>
							<th data-options="field:'PLATFORM_ID',width:160,sortable:'true',align:'center'">公众号名称</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'UPDATED_DATE',width:180,sortable:'true',align:'center'">最后更新时间</th>	
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tReplySpecial_list ={};
jQuery(function($){   
   //清除日期
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
	//定义构造查询
	tReplySpecial_list.buildQueryParams=function(){
	     $('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid("options")
			.queryParams = {
					'tReplySpecialQueryBean.reply_type':$("input[name='search_reply_type']").val(),
					'tReplySpecialQueryBean.effect_flag':$("input[name='search_effect_flag']").val(),
					'tReplySpecialQueryBean.rule_type':$("input[name='search_rule_type']").val(),
					'tReplySpecialQueryBean.updated_date_start':$("input[name='search_updated_date']").val(),
					'tReplySpecialQueryBean.updated_date_end':$("input[name='search_updated_date1']").val(),
					'tReplySpecialQueryBean.created_user_cd':$.trim($("input[name='search_created_user_cd']").val()),
					'tReplySpecialQueryBean.updated_user_cd':$.trim($("input[name='search_updated_user_cd']").val())
			};
	};
    //定义构造查询JSON
    tReplySpecial_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			  	pageNumber:$('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('options').pageNumber,
		  		//页面查询框部分
					reply_type :$("input[name='search_reply_type']").val(), 
					effect_flag :$("input[name='search_effect_flag']").val(), 
					rule_type :$("input[name='search_rule_type']").val(), 
					platform_id :$("input[name='search_platform_id']").val(), 
					updated_date_start :$("input[name='search_updated_date']").val(),
					updated_date_end :$("input[name='search_updated_date1']").val(),  
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
					updated_user_cd :$("input[name='search_updated_user_cd']").val()
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tReplySpecial_list #search_btn').click(function(){
          if($('#searchForm').form("validate")==true){ 
			tReplySpecial_list.buildQueryParams();
			$('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tReplySpecial_list #clear_btn').click(function(){
		//清空查询条件         
			$("input[name='search_freply_id']").val("");  
			$("input[name='search_updated_date']").val("");
			$("input[name='search_created_user_cd']").val("");
			$("input[name='search_updated_user_cd']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find("input.easyui-datetimebox").datetimebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tReplySpecial_list.buildQueryParams();
		$('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
	});
	
	
	//新增
	$('div#div_tReplySpecial_list #add_btn').click(function(){
		var add_form_id ='#addTReplySpecialFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '特殊回复规则新增',
		    width: 960,
		    height: 564,
		    href: 'addTReplySpecialPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){	
		    
		   					$('#addTReplySpecialFrom').form({   
						     url:'addTReplySpecialAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
							         var result = JSON.parse(data);
							        if(result.success==true){
									     $.messager.alert("提示信息",result.message,"success");
									     $('#dialog_holder').dialog('close');
									     tReplySpecial_list.buildQueryParams();
									     $('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
									  }else{
									    $.messager.alert("提示信息",result.message,"error");
									      $('#dialog_holder').dialog('close');
									  }
							     }
						});// 
					    //validate and sbumit
					    if($(add_form_id).form("validate")==true){
				                  //验证是否选择素材
					     if($("#add_reply_type").combobox("getValue") != '505200000002'){
					         if($("#material_id_hid").val() == ''){
					           $.messager.alert("提示信息","请选择素材!","info");
					           	return false;
					         } 
					       }else{
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
						    	$("#text_msg_hid").val($.trim(templet_flag_TextArea_val));
					    	}
					    	//验证首次关注和关键字无匹配	
					    	 var rule_type=$("#add_rule_type").combobox("getValue");	
					    	 var effect_flag=$("#add_effect_flag").combobox("getValue");
					    	 if(effect_flag == '100000000002'){
					    	       $(add_form_id).submit();
					    	 }else{		 
				         		$.post("checkTReplySpecialAction.action",{check_rule_type:rule_type}, 
				         		function(data){
				         			  data = JSON.parse(data);
					               if(data.status==true){
								     $(add_form_id).submit();
								     return true;
								   }else{               
					                 $.messager.alert("提示信息",data.message,"error");
					                 return false;
					           }
							});
					     }
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
	tReplySpecial_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTReplySpecialFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '特殊回复规则编辑',
		    width: 960,
		    height: 564,
		    href: 'editTReplySpecialPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTReplySpecialFrom').form({   
						 url:'editTReplySpecialAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							       var result = JSON.parse(data);
							       if(result.success == true){
							            $.messager.progress('close');
										$.messager.alert("提示信息",result.message,"success");
										$('#dialog_holder').dialog('close');
										tReplySpecial_list.buildQueryParams();
										$('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
							       }else{
							         	$('#dialog_holder').dialog('close');
							           $.messager.alert("提示信息",result.message,"error");   
							       }							     
							     }
						});// 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
					        //验证是否选择素材
					         if($("#edit_reply_type").combobox("getValue") != '505200000002'){
						         if($("#material_id_hid").val() == ''){
						           $.messager.alert("提示信息","请选择素材!","info");
						           	return false;
						         } 
                            }else{
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
						    	$("#text_msg_hid").val($.trim(templet_flag_TextArea_val));

					    	}
					    		//验证首次关注和关键字无匹配	
					    	    var rule_type=$("#edit_rule_type").combobox("getValue");
						    	var edit_effect_flag=$("#edit_effect_flag").combobox("getValue");
						    	var effect_flag = $("#effect_flag").val();	
								if(effect_flag == '100000000002' && edit_effect_flag =='100000000001' ){
							         		$.post("checkTReplySpecialAction.action",{check_rule_type:rule_type},
							         		function(data){
							         			  data = JSON.parse(data);
								               if(data.status==true){
											     	$(edit_form_id).submit();
											     return true;
											   }else{               
								                 $.messager.alert("提示信息",data.message,"error");
								                 return false;
								               }
						              });
							   	}else{
							     	  $(edit_form_id).submit();
							   	}
						}  
		    		
		    	}
		    },{text: "删  除",
		     	handler: function(e){
		     		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
		     			$('#delTReplySpecialFrom').form({   
						 url:'delTReplySpecialAction.action',    
							     onSubmit: function(){
							       $.messager.progress();  
							     },    
							     success:function(data){
                 			      $.messager.progress('close');   
                 				  data = JSON.parse(data);
                 				 if(data.status){
									 $.messager.alert("提示信息",data.message,"success");
									 $('#dialog_holder').dialog('close');
									 tReplySpecial_list.buildQueryParams();
									 $('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
				                }else{
				                   	$.messager.alert("提示信息",data.message,"error");
				                   	 $('#dialog_holder').dialog('close');
				                }
				              }
                       		});
                       		$('#delTReplySpecialFrom').submit();
                 		}});
                 	 }
		    	},{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//删除
	tReplySpecial_list.deleteFormSubmit=function(pkid){
	 var a = $('#dg_tReplySpecial').datagrid('getSelected');
	 		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("delTReplySpecialAction.action",{pkid: pkid}, function(data){
                 				
                 				data = JSON.parse(data);
                 				if(data.status==true){
									  $.messager.alert("提示信息",data.message,"success");
									  $('#dialog_holder').dialog('close');
									  tReplySpecial_list.buildQueryParams();
									  $('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
				                }else{
				                   	$.messager.alert("提示信息",data.message,"error");
				                   	  $('#dialog_holder').dialog('close');
				                }
                       		});
                 		}
 		});
	};
		//详细特殊回复规则
	tReplySpecial_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '特殊回复规则详细',
		    width: 980,
		    height: 600,
		    href: 'detailTReplySpecialPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		   	    buttons: [{
		    	text: "编  辑",
		    	handler: function(e){
		    				//关闭详情页面
						    $('#dialog_holder').dialog('close');
						    //显示编辑页面
						    tReplySpecial_list.updateFormSubmit();
						     tReplySpecial_list.editspecial();
						}
		    	
		    	},{text: "删  除",
		     	handler: function(e){
		     		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
		     			$('#delTReplySpecialFrom').form({   
						 url:'delTReplySpecialAction.action',    
							     onSubmit: function(){
							       $.messager.progress();  
							     },    
							     success:function(data){
                 			      $.messager.progress('close');   
                 				  data = JSON.parse(data);
                 				 if(data.status){
									 $.messager.alert("提示信息",data.message,"success");
									 $('#dialog_holder').dialog('close');
									tReplySpecial_list.buildQueryParams();
									$('div#div_tReplySpecial_list #dg_tReplySpecial').datagrid('load');
				                }else{
				                   	$.messager.alert("提示信息",data.message,"error");
				                   	  $('#dialog_holder').dialog('close');
				                }
				              }
                       		});
                       		$('#delTReplySpecialFrom').submit();
                 		}});
                 	 }
		    	},{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});	
	}	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>