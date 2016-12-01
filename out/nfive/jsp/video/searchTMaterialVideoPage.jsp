<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>视频资源一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tMaterialVideo_list" class="container">
	<header class="page-title">
		<h1>视频资源一览</h1>
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
										<th class="wd-10"><label>视频名称</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_name" name="search_video_name"  value="${tMaterialVideoDto.video_name}" maxlength="50" style="width:200px;" data-options="required:true" />
										</td>
									 	 <th class="wd-10"><label>是否缓存</label></th>
										<td>
											<ldui:select items="${cache_flag_Select}" id="search_is_cache_Select" name="search_is_cache" class="easyui-combobox" style="width:200px;" />
										</td>
										<th class="wd-10"><label>视频描述</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_desc" name="search_video_desc"  value="${tMaterialVideoDto.video_desc}" maxlength="50" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-10"><label>最后更新时间</label></th>
										<td>
											<input type="text" id="tMaterialVideoDtoCxStartdate" name="search_startdate" validType="againFocus['#tMaterialVideoDtoCxEnddate']" style="width:108px;" class="easyui-datebox" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;"></i>
											~											
											<input type="text" id="tMaterialVideoDtoCxEnddate" name="search_enddate" validType="minFirstDate['#tMaterialVideoDtoCxStartdate']" style="width:108px;" class="easyui-datebox"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
										<th class="wd-10"><label>创建人</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_desc" name="search_created_user_cd"  value="${tMaterialVideoDto.video_desc}" style="width:200px;" data-options="required:true" />
										</td>
										<th class="wd-10"><label>更新人</label></th>
										<td>
											<input type="text" id="tMaterialVideoDto.video_desc" name="search_updated_user_cd"  value="${tMaterialVideoDto.video_desc}" style="width:200px;" data-options="required:true" />
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
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tMaterialVideo" class="easyui-datagrid" title="视频资源一览列表" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMaterialVideoAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'VIDEO_ID',width:120,sortable:'true',align:'right',hidden:'true'">video_id</th>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'DEL',width:50,align:'center'">删除</th>
							<th data-options="field:'VIDEO_NAME',width:200,sortable:'true',align:'center'">视频名称</th>
							<th data-options="field:'VIDEO_DESC',width:360,sortable:'true',align:'center'">视频描述</th>
							<th data-options="field:'CONTENT_SIZE',width:90,sortable:'true',align:'center'">视频大小</th>
							<th data-options="field:'CACHE_FLAG',width:90,sortable:'true',align:'center'">是否缓存</th>
							<th data-options="field:'FILE_ID',width:10,sortable:'true',align:'center',hidden:'true'">关联文件ID</th>
							
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
							
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'center'">更新人</th>
							
							<th data-options="field:'UPDATED_DATE',width:150,sortable:'true',align:'center'">最后更新时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>
<div  class="easyui-dialog"  style="width: 545px;height: 365px;"
							data-options="title:'视频预览',left:500" id="preview_dia" closed="true">
							<div id="preview_div" style="width: 528px;height: 326px;text-align: center;"></div>
</div>
<script>
var tMaterialVideo_list ={};
jQuery(function($){
	//定义清空日期
	$(".icon-clear-date").mousedown(function(){
		var id=$(this).prev().prev().attr("id");
		$("#"+id).datebox("setValue","");
    	$("#"+id).datebox("reset");
	});
	
	//定义构造查询
	tMaterialVideo_list.buildQueryParams=function(){
	     $('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid("options")
			.queryParams = {
					'in_tMaterialVideoQueryBean.video_id':$.trim($("input[name='search_video_id']").val()),
					'in_tMaterialVideoQueryBean.video_name':$.trim($("input[name='search_video_name']").val()),
					'in_tMaterialVideoQueryBean.video_desc':$.trim($("input[name='search_video_desc']").val()),
					'in_tMaterialVideoQueryBean.file_id':$.trim($("input[name='search_file_id']").val()),
					
					
					'in_tMaterialVideoQueryBean.cache_flag':$.trim($("input[name='search_is_cache']").val()),//是否缓存
					'in_tMaterialVideoQueryBean.cxstartdate':$.trim($("input[name='search_startdate']").val()),//开始时间
					'in_tMaterialVideoQueryBean.cxenddate':$.trim($("input[name='search_enddate']").val()),//结束时间
					'in_tMaterialVideoQueryBean.created_user_cd':$.trim($("input[name='search_created_user_cd']").val()),//创建人
					'in_tMaterialVideoQueryBean.updated_user_cd':$.trim($("input[name='search_updated_user_cd']").val()),//更新人
					'in_tMaterialVideoQueryBean.created_date':$.trim($("input[name='search_created_date']").val())//创建时间
			};
	};

	//重新按照条件刷新查询内容
	$('div#div_tMaterialVideo_list #search_btn').click(function(){
		if($(searchForm).form("validate")==true){
			tMaterialVideo_list.buildQueryParams();
			$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
		}; 
		
		
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialVideo_list #clear_btn').click(function(){
		//清空查询条件
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialVideo_list.buildQueryParams();
		$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
	});
	
	
	//新增
	$('div#div_tMaterialVideo_list #add_btn').click(function(){
		var add_form_id ='#addTMaterialVideoFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '视频资源新增',
		    width: 800,
		    height: 550,
		    href: 'addTMaterialVideoPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTMaterialVideoFrom').form({   
						 url:'addTMaterialVideoAction.action',    
							     onSubmit: function(){
							       	var src=$('#videoFile').val();
							    	if('undefined' == src || '' ==src){
							    		$.messager.alert('提示','请选择要上传的视频！','info')
							    		return false;
							    	}
							    	var video= $("input[name='in_tMaterialVideoDto.video_name']");
							    	if('undefined' == video.val() || "" == $.trim(video.val()) ){
							    		video.val('');
							    		video.select();
							    		$.messager.alert('提示','视频名称不能为空！','info');
							    		return false;
							    	}
							    	
							    	$.messager.progress(); 
							     },    
							     success:function(data){
							     	$.messager.progress('close');
							     	$.messager.alert("提示信息",JSON.parse(data).message,"info");
							     	//判断操作结果
							     	if (JSON.parse(data).success){
										tMaterialVideo_list.buildQueryParams();
										$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
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
	tMaterialVideo_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTMaterialVideoFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '视频资源编辑',
		    width: 800,
		    height: 550,
		    href: 'editTMaterialVideoPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTMaterialVideoFrom').form({   
						 url:'editTMaterialVideoAction.action',    
							     onSubmit: function(){
							    		//校驗空格
							    		var video= $("input[name='in_tMaterialVideoDto.video_name']");
								    	if('undefined' == video.val() || "" == $.trim(video.val()) ){
								    		video.val('');
								    		video.select();
								    		$.messager.alert('提示','视频名称不能为空！','info');
								    		return false;
								    	}
							      		$.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
									$.messager.alert("提示信息",JSON.parse(data).message,"info");
							        if(JSON.parse(data).success){
							        	tMaterialVideo_list.buildQueryParams();
										$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
										$('#dialog_holder').dialog('close');
							        }
							     }
						});// 
					    if($(edit_form_id).form("validate")==true){
							$(edit_form_id).submit();
						}; 
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     			$('#delTMaterialVideoFrom').form({   
						 url:'delTMaterialVideoAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
									$.messager.alert("提示信息",JSON.parse(data).message,"info");
							        if(JSON.parse(data).success){
							        	tMaterialVideo_list.buildQueryParams();
										$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
										$('#dialog_holder').dialog('close');
							        }
							     }
						});
					    $.messager.confirm("提示信息","确定要删除此条目？",function(flg){
							if(flg){
								$('#delTMaterialVideoFrom').submit();
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
	tMaterialVideo_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '视频素材详细',
		    width: 800,
		    height: 550,
		    href: 'detailTMaterialVideoPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "编  辑",
		    	handler: function(e){
		    		$(this).dialog('close');
		    		tMaterialVideo_list.updateFormSubmit(pkid);
		    	}
		    },{
		    	text: "删  除",
		    	handler: function(e){
		    		$('#delTMaterialVideoFrom').form({   
						 url:'delTMaterialVideoAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
									$.messager.alert("提示信息",JSON.parse(data).message,"info");
							        if(JSON.parse(data).success){
							        	tMaterialVideo_list.buildQueryParams();
										$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
										$('#dialog_holder').dialog('close');
							        }
							     }
						});
					    $.messager.confirm("提示信息","确定要删除此条目？",function(flg){
							if(flg){
								$('#delTMaterialVideoFrom').submit();
							}
						}); 
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
	tMaterialVideo_list.delFormSubmit = function (pkid,file_id){
		$.messager.confirm("提示信息","确定要删除此条目？",function(flg){
			if(flg){
				$.post('delTMaterialVideoAction.action',{pkid:pkid,file_id:file_id},function (data){
								$('div#div_tMaterialVideo_list #dg_tMaterialVideo').datagrid('load');
								$.messager.alert("提示信息",JSON.parse(data).message,"info");
								if (JSON.parse(data).success){
										$('#dialog_holder').dialog('close');
								}
				});
			}
		});
	}
	
	//点击名称弹出视频
	tMaterialVideo_list.lookVideoView =  function (videoSrc){
		  //ie
	      if (window.navigator.userAgent.indexOf("MSIE")>= 0) {
		      	//预览本地文件
		    	$("#preview_div").html('<object width="100%" height="100%" type="video/x-ms-asf" ' 
		    								+' url="'+videoSrc+'" data="'+videoSrc+'" classid="CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6">'
											+' <param id="param_url" name="url" value="'+videoSrc+'">'
											+' <param id="param_filename" name="filename" value="'+videoSrc+'" >'
											+' <param name="autostart" value="0">'
											+' <param name="uiMode" value="full" />'
											+' <param name="autosize" value="1">'
											+' <param name="playcount" value="1">'
											+' <embed type="application/x-mplayer2" src="'+videoSrc+'" width="100%" height="100%" ' 
											+' autostart="true" showcontrols="true" '
											+' pluginspage="http://www.microsoft.com/Windows/MediaPlayer/"></embed></object>');	
    		//支持HTML5标签的 浏览器
    		} else {
	    		$('#preview_div').panel({content:'<video src="'+videoSrc+'" width="510" height="310px;" controls="controls">'
				+'<source src="movie.ogg" type="video/ogg"><source src="movie.mp4" type="video/mp4">您的浏览器不支持此属性</video>'}); 
    		}
			//$('#preview_div').css('text-align','center');
			$('#preview_dia').dialog('open');
	}
	
	tMaterialVideo_list.alertdesc = function (video_desc){
		$.messager.alert('视频描述','<div style="word-wrap: break-word; word-break: normal;text-indent: 2em; ">'+video_desc+'</div>','');
	}
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>