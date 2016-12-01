<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/jsp/common/easyui_head.jsp"%>
		<title>音频资源一览</title>
	</head>

	<body>
		<!--菜单-->
		<%@ include file="/jsp/common/topmenu.jsp"%>
		<!--内容-->
		<div id="div_tMaterialVoice_list" class="container">
			<header class="page-title">
			<h1>音频资源一览</h1>
			</header>
			<div class="page-toolbar clearfix">
				<ul class="page-toolbar-list">
					<li>
						<a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a>
					</li>
				</ul>
			</div>
			<article id="content" class="content">
			<div class="content-body">
				<!--搜索栏开始-->
				<div class="search-panel toggle-panel">
					<div class="panel-header" data-role="toggle-handle">
						<h2 class="panel-title">
							查询条件
						</h2>
					</div>
					<div class="search-panel-content">
						<form id="searchForm" name="searchForm" method="post">
							<div class="search-panel-bd">
								<table class="search-table">
									<tr>
										<th class="wd-10">
											<label>
												音频名称
											</label>
										</th>
										<td>
											<input type="text" id="tMaterialVoiceDto.voice_name" class="easyui-validatebox" name="search_voice_name" style="width: 150px;"/>
										</td>
										<th class="wd-10">
											<label>音频描述</label>
										</th>
										<td>
											<input type="text" id="tMaterialVoiceDto.voice_desc" name="search_voice_desc" style="width: 150px;"/>
										</td>
										<th class="wd-10">
											<label>创建人:</label>
										</th>
										<td>
											<input type="text" id="tMaterialVoiceDto.created_user_cd" class="easyui-validatebox" name="search_created_user_cd" style="width: 150px;"/>
										</td>
									</tr>
									<tr>
										<th class="wd-10">
											<label>更新人:</label>
										</th>
										<td>
											<input type="text" id="tMaterialVoiceDto.updated_user_cd" class="easyui-validatebox" name="search_updated_user_cd" style="width: 150px;"/>
										</td>
										<th class="wd-10">
										   <label>是否缓存</label>
										   </th>
									  <td>
									   <ldui:select items="${cache_flagList}" class="easyui-combobox"  id="search_cache_flag" name="search_cache_flag" style="width:140px;"/>	
									  </td>	
										
										<th class="wd-10">
											<label>最后更新时间:</label>
										</th>
										<td colspan="3">
											<input type="text" id="search_updated_date" class="easyui-datebox" name="search_updated_date" validType="againFocus['#search_updated_date1']" style="width: 150px;" />
											&nbsp;
											<i class="icon-clear-date" style="cursor: pointer;"></i>~&nbsp;
											<input type="text" id="search_updated_date1" class="easyui-datebox" name="search_updated_date1" validType="minFirstDate['#search_updated_date']" style="width: 150px;" />
											&nbsp;
											<i class="icon-clear-date" style="cursor: pointer;"></i>
										</td>
									</tr>
								</table>
							</div>
							<div class="search-btn-area">
								<input id="search_btn" type="button" value="查 询" />
								<input id="clear_btn" type="button" value="清 除" />
							</div>
						</form>
					</div>
				  <div  class="easyui-dialog"  style="width: 500px;height: 100px;"
							data-options="title:'音频预览',left:500" id="preview_dia" closed="true">
					        <div id="preview_div" style="width: 500px;height: 100px;text-align: center;"></div>
					</div>
				</div>
				<!--搜索栏结束-->

				<!--搜索结果开始-->
				<div class="result-content">
					<table id="dg_tMaterialVoice" class="easyui-datagrid" title="音频一览表" style="width: auto; height: 300px"
						data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMaterialVoiceAction!getListData.action',method:'post'">
						<thead>
							<tr>
								<th data-options="field:'DETAIL',width:60,align:'center'">详细</th>
								<th data-options="field:'EDIT',width:60,align:'center'">编辑</th>
								<th data-options="field:'DEL',width:60,align:'center'">删除</th>
								<th data-options="field:'VOICE_ID',width:120,hidden:'true',align:'center'">音频ID</th>
								<th data-options="field:'VOICE_NAME',width:230,sortable:'true',align:'center'">音频名称</th>
								<th data-options="field:'VOICE_DESC',width:320,sortable:'true',align:'center'">音频描述</th>
								<th data-options="field:'VOICE_SIZE',width:100,sortable:'true',align:'center'">音频大小</th>
								<th data-options="field:'CACHE_FLAG',width:100,sortable:'true',align:'center'">是否缓存</th>
						    	<th data-options="field:'CREATED_USER_CD',width:100,sortable:'true',align:'center'">创建人</th>
							  	<th data-options="field:'UPDATED_USER_CD',width:100,sortable:'true',align:'center'">更新人</th>
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
var tMaterialVoice_list ={};
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
	tMaterialVoice_list.buildQueryParams=function(){
	     $('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid("options")
			.queryParams = {
					'tMaterialVoiceQueryBean.voice_name':$.trim($("input[name='search_voice_name']").val()),
					'tMaterialVoiceQueryBean.voice_desc':$.trim($("input[name='search_voice_desc']").val()),
				    'tMaterialVoiceQueryBean.updated_date_start':$("input[name='search_updated_date']").val(),
					'tMaterialVoiceQueryBean.updated_date_end':$("input[name='search_updated_date1']").val(),
					'tMaterialVoiceQueryBean.created_user_cd':$.trim($("input[name='search_created_user_cd']").val()),
					'tMaterialVoiceQueryBean.updated_user_cd':$.trim($("input[name='search_updated_user_cd']").val()),
					'tMaterialVoiceQueryBean.cache_flag':$("input[name='search_cache_flag']").val()
			};
	};


    //定义构造查询JSON
    tMaterialVoice_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('options').pageNumber,
		  		//页面查询框部分
					voice_name :$("input[name='search_voice_name']").val(), 
					voice_desc :$("input[name='search_voice_desc']").val(), 
					updated_date_start :$("input[name='search_updated_date']").val(), 
					updated_date_endt :$("input[name='search_updated_date1']").val(),
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
					updated_user_cd :$("input[name='search_updated_user_cd']").val(), 
					cache_flag :$("input[name='search_cache_flag']").val() 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tMaterialVoice_list #search_btn').click(function(){
	  //判断查询的更新日期区间
	    if($('#searchForm').form("validate")==true){ 
		tMaterialVoice_list.buildQueryParams();
		$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
		}
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialVoice_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_voice_id']").val("");
			$("input[name='search_voice_name']").val("");
			$("input[name='search_voice_url']").val("");
			$("input[name='search_voice_desc']").val("");
			$("input[name='search_file_id']").val("");
			$("input[name='search_cache_flag']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find("input.easyui-datetimebox").datetimebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialVoice_list.buildQueryParams();
		$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
	});
	
	
	//音频新增
	$('div#div_tMaterialVoice_list #add_btn').click(function(){
	   $("#voicepreview").attr('src','');
	      $('#preview_div').empty();
		var add_form_id ='#addTMaterialVoiceFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '音频新增',
		    width: 800,
		    height: 500,
		    href: 'addTMaterialVoicePage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTMaterialVoiceFrom').form({   
						 url:'addTMaterialVoiceAction.action',    
							     onSubmit: function(){
							      	var voice= $("#add_voice_name").val();
							    	if('undefined' == voice || "" == $.trim(voice) ){
							    		$.messager.alert('提示','音频名称不能为空！','info');
							    		return false;
							    	}
							       	var src=$('#voiceFile').val();
							       	if(src =='' || src == 'undefined' ){
							       	   	$.messager.alert('提示','请上传mp3格式的音频！');
							       	      return false;
							       	}
							    	//校验音频大小
							   	     if($("#voiceFile")[0].files[0].size >  2*1024*1024){
						    			$.messager.alert("提示信息","上传文件不得大于2MB!","info");
						    			return false;
						    	     } 
						    		if( !src.match( /.mp3/i ) ){  
							   			$.messager.alert('提示','音频格式无效！');  
							   			$("#add_voiceViewURL").attr('src','');
							   			return false;  
							  		}  
							    	
							       $.messager.progress(); 
							      
							     },    
							     success:function(data){
							       //do some
							          $.messager.progress('close');
							       var result = JSON.parse(data);
							        if(result.status==true){
									    tMaterialVoice_list.buildQueryParams();
									    $('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
										$.messager.alert("提示信息",result.message,"success");
										 $('#dialog_holder').dialog('close');
									}else{
									    $.messager.alert("提示信息",result.message,"error");
									     $('#dialog_holder').dialog('close');
									}
							     }
						});// 
					    //validate and sbumit
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
	tMaterialVoice_list.updateFormSubmit = function(pkid){
	    $("#voicepreview").attr('src','');
	       $('#preview_div').empty();
	    var edit_form_id ='#editTMaterialVoiceFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '音频编辑',
		    width: 800,
		    height: 500,
		    href: 'editTMaterialVoicePage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTMaterialVoiceFrom').form({   
						 url:'editTMaterialVoiceAction.action',    
							     onSubmit: function(){
							     	var voice= $("#edit_voice_name").val();
							    	if('undefined' == voice || "" == $.trim(voice) ){
							    		$.messager.alert('提示','音频名称不能为空！','info');
							    		return false;
							    	}
							       var src=$('#voiceFile').val();
							    	if('undefined' != src && '' !=src){
								    	//校验音频大小
								    	if($("#voiceFile")[0].files[0].size >  2*1024*1024){
							    			$.messager.alert("提示信息","上传文件不得大于2MB!","info");
							    			return false;
							    		} 
							    		if( !src.match( /.mp3/i ) ){  
								   			$.messager.alert('提示','音频格式无效！');  
								   			$("#edit_voiceViewURL").attr('src','');
								   			return false;  
								  		}
							  		}
							    
							       $.messager.progress(); 
							     },    
							     success:function(data){
							       //do some
							         $.messager.progress('close');
							        var result = JSON.parse(data);
							        if(result.status==true){
										tMaterialVoice_list.buildQueryParams();
										$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
										$('#dialog_holder').dialog('close');
										 	$.messager.alert("提示信息",result.message,"success");
									}else{
									    $('#dialog_holder').dialog('close');
									    $.messager.alert("提示信息",result.message,"error");
									}
							     }
						});// 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
							$(edit_form_id).submit();
						};   
		    		
		    	}
		    },{
		    	text: "删  除",
		     	handler: function(e){
		     	  	$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
		     			$('#delTMaterialVoiceFrom').form({   
						 url:'delTMaterialVoiceAction.action',    
							     onSubmit: function(){
							       $.messager.progress();  
							     },    
							     success:function(data){
							       //do some
							           $.messager.progress('close');
							        var result = JSON.parse(data);
							        if(result.status==true){
										tMaterialVoice_list.buildQueryParams();
										$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
										$('#dialog_holder').dialog('close');
										$.messager.alert("提示信息",result.message,"success");
									}else{
									    $('#dialog_holder').dialog('close');
									    $.messager.alert("提示信息",result.message,"error");
									 }
							     }
						   });// 
					    $('#delTMaterialVoiceFrom').submit();
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
	tMaterialVoice_list.deleteFormSubmit=function(pkid,file_id){
	 		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("delTMaterialVoiceAction.action",{pkid:pkid,file_id:file_id}, function(data){
                 				var result = JSON.parse(data);
                 				if(result.status==true){
				                	tMaterialVoice_list.buildQueryParams();
									$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
									$('#dialog_holder').dialog('close');
									  $.messager.alert("提示信息",result.message,"success");
				                }else{
				                    $('#dialog_holder').dialog('close');
				                   	$.messager.alert("提示信息",result.message,"error");
				                }
                       		});
                 		}
 		});
	};
	
	//详细
	tMaterialVoice_list.detailFormSubmit = function(pkid){
	     $("#voicepreview").attr('src','');
	     $('#preview_div').empty();
		$('<div id="dialog_holder"></div>').dialog({
		    title: '音频详细',
		    width: 800,
		    height: 500,
		    href: 'detailTMaterialVoicePage.action',
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
						    tMaterialVoice_list.updateFormSubmit();
						     tMaterialVoice_list.editspecial();
						}
		    	
		    	},{
		    	text: "删  除",
		     	handler: function(e){
		     	  	$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
		     			$('#delTMaterialVoiceFrom').form({   
						 url:'delTMaterialVoiceAction.action',    
							     onSubmit: function(){
							       $.messager.progress();  
							     },    
							     success:function(data){
							       //do some
							           $.messager.progress('close');
							        var result = JSON.parse(data);
							        if(result.status==true){
										tMaterialVoice_list.buildQueryParams();
										$('div#div_tMaterialVoice_list #dg_tMaterialVoice').datagrid('load');
										$('#dialog_holder').dialog('close');
										$.messager.alert("提示信息",result.message,"success");
									}else{
									    $('#dialog_holder').dialog('close');
									    $.messager.alert("提示信息",result.message,"error");
									 }
							     }
						   });// 
					    $('#delTMaterialVoiceFrom').submit();
					 }});
		    	}
		     },{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}

});
tMaterialVoice_list.doplay= function(url){
	      if (window.navigator.userAgent.indexOf("MSIE")>= 0) {
		      	//预览本地文件
		    	$("#preview_div").html( '<object height=100 width=500 classid=CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6>'
													+'<param name="URL" value="'+url+'" /> ' 
													+'<param name="currentPosition" value="0" />'
													+'<param name="playCount" value="1" />'
													+'<param name="autoStart" value="true" />'
													+'<param name="currentMarker" value="0" />'
			                                       +' </object>'); 	
    		//支持HTML5标签的 浏览器
    		} else {
	    		$('#preview_div').panel({content:'<audio width="500" height="100px;" controls="controls" src="'+url+'" playing="mginfo();"></audio>'}); 
    		}
			//$('#preview_div').css('text-align','center');
			$('#preview_dia').dialog('open');
	}

//弹出音频描述
tMaterialVoice_list.detailVoiceDesc=function(desc){
	$.messager.alert('音频描述','<div style="word-wrap: break-word; word-break: normal;text-indent: 2em; ">'+desc+'</div>');
}
</script>
		<%@ include file="/jsp/common/footer.jsp"%>
	</body>
</html>