<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>图片资源一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tMaterialPicture_list" class="container">
	<header class="page-title"><h1>图片资源一览</h1></header>
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
									<th class="wd-10"><label>图片名称:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="search_picture_name"  name="search_picture_name" style="width:150px;" data-options="validType:'length[0,50]',invalidMessage:'图片名称不能超过50个字符！',missingMessage:'图片名称不能超过50个字符！'"/>
									</td>
									<th class="wd-10"><label>图片描述:</label></th>
									<td >
										<input type="text" class="easyui-validatebox" id="search_picture_desc"  name="search_picture_desc" style="width:150px;" data-options="validType:'length[0,80]',invalidMessage:'图片描述不能超过80个字符！',missingMessage:'图片描述不能超过80个字符！'"/>
									</td>
									<th class="wd-10"><label>创建人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="search_created_user_cd" name="search_created_user_cd"  style="width:150px;"  data-options="validType:'length[0,12]',invalidMessage:'创建人名称不能超过12个字符！',missingMessage:'创建人名称不能超过12个字符！'"/>
									</td>
								</tr>
								<tr>
									<th class="wd-10"><label>更新人:</label></th>
									<td>
										<input type="text" class="easyui-validatebox" id="search_updated_user_cd"  name="search_updated_user_cd" style="width:150px;" data-options="validType:'length[0,12]',invalidMessage:'更新人名称不能超过12个字符！',missingMessage:'更新人名称不能超过12个字符！'"/>
									</td>
									<th class="wd-20"><label>是否缓存:</label></th>
									<td>
										<ldui:select items="${cache_FlagList}" id="search_effect_flag_select" name="search_effect_flag_select" class="easyui-combobox" style="width:160px;" />
									</td>
									<th class="wd-10"><label>最后更新时间:</label></th>
									<td colspan="3">
										<input type="text" class="easyui-datebox" id="search_updated_date"   name="search_updated_date"  style="width:150px;" value="${tMaterialPictureQueryBean.search_updated_date }"  class="easyui-validatebox"  validType="againFocus['#search_updated_date1']"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										&nbsp;~&nbsp; 
										<input type="text" class="easyui-datebox" id="search_updated_date1"  name="search_updated_date1"  style="width:150px;" value="${tMaterialPictureQueryBean.search_updated_date1 }" class="easyui-validatebox" validType="minFirstDate['#search_updated_date']" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										
									</td>
								</tr>
							</table>
						</div>
						<div class="easyui-dialog" data-options="title:'图片预览',left:500,top:50" id="preview_div" closed="true">
								<img alt="" src="" id="preview">  
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
				<table  id="dg_tMaterialPicture" class="easyui-datagrid" title="图片资源一览" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMaterialPictureAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'DETAIL',width:50,align:'center'">预览</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'DELETE',width:50,align:'center'">删除</th>
							<th data-options="field:'PICTURE_NAME',width:200,sortable:'true',align:'center'">图片名称</th>
							<th data-options="field:'PICTURE_DESC',width:350,sortable:'true',align:'center'">图片描述</th>
							<th data-options="field:'PICTURE_SIZE',width:60,sortable:'true',align:'center'">图片大小</th>
							<th data-options="field:'CACHE_FLAG',width:80,align:'center'">是否缓存</th>							
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'UPDATED_DATE',width:200,sortable:'true',align:'center'">最后更新时间</th>							
						</tr>						
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var parseDate = function(date){
date = $.fn.datebox.defaults.parser(date);
alert(date)
	//alert($.fn.datebox.defaults.parser(date));
}
var tMaterialPicture_list ={};
jQuery(function($){
	
	//定义构造查询
	tMaterialPicture_list.buildQueryParams=function(){
	     $('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid("options")
			.queryParams = {
					'tMaterialPictureQueryBean.picture_name':$.trim($("input[name='search_picture_name']").val()),
					'tMaterialPictureQueryBean.picture_desc':$.trim($("input[name='search_picture_desc']").val()),
					'tMaterialPictureQueryBean.search_updated_date':$("input[name='search_updated_date']").val(),
					'tMaterialPictureQueryBean.search_updated_date1':$("input[name='search_updated_date1']").val(),
					'tMaterialPictureQueryBean.created_user_cd':$.trim($("input[name='search_created_user_cd']").val()),
					'tMaterialPictureQueryBean.updated_user_cd':$.trim($("input[name='search_updated_user_cd']").val()),
					'tMaterialPictureQueryBean.cache_flag':$("#search_effect_flag_select").combobox("getValue")
			}
	}


    //定义构造查询JSON
    tMaterialPicture_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('options').pageNumber,
		  		//页面查询框部分
					picture_name :$("input[name='search_picture_name']").val(), 
					picture_desc :$("input[name='search_picture_desc']").val(), 
					search_created_date :$("input[name='search_created_date']").val(), 
					search_updated_date1 :$("input[name='search_updated_date1']").val(), 
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
					updated_user_cd :$("input[name='search_updated_user_cd']").val() 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tMaterialPicture_list #search_btn').click(function(){
	    if($("#searchForm").form("validate")==true){
			tMaterialPicture_list.buildQueryParams();
			$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
	    }
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMaterialPicture_list #clear_btn').click(function(){
		//清空查询条件
			$("#search_created_date").datebox("setValue","");
     		$("#search_created_date").datebox("clear");
     		$("#search_created_date1").datebox("setValue","");
     		$("#search_created_date1").datebox("clear");
     		
			$("input[name='search_picture_name']").val("");
			$("input[name='search_picture_desc']").val("");
			$("input[name='search_updated_date']").val("");
			$("input[name='search_updated_date1']").val("");
			$("input[name='search_created_user_cd']").val("");
			$("input[name='search_updated_user_cd']").val("");
			$("#search_effect_flag_select").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tMaterialPicture_list.buildQueryParams();
		$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
	});
	
	
	//新增
	$('div#div_tMaterialPicture_list #add_btn').click(function(){
		var add_form_id ='#addTMaterialPictureFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图片资源新增',
		    width: 800,
		    height: 530,
		    href: 'addTMaterialPicturePage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){	
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    	
		    	$('#addTMaterialPictureFrom').form({   
						 url:'addTMaterialPictureAction.action',    
							     onSubmit: function(){							    							    	
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							     	data = JSON.parse(data);							 
							        $.messager.alert('提示',data.msg,'',function(){	
							        	if(data.success){							        		
											tMaterialPicture_list.buildQueryParams();
											$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
											$('#dialog_holder').dialog('close');
							        	}						       	                 					
                 					});
                 					$.messager.progress('close');
							     }
						});// 
					    //validate and sbumit
					    if($(add_form_id).form("validate")==true){
					    			//文件名称不能为空校验：easyui不能识别空格
							    	var picture= $("input[name='tMaterialPictureDto.picture_name']").val();
							    	if(undefined == picture || "" == $.trim(picture) ){
							    		$.messager.alert('提示','图片名称不能为空！');
							    		return false;
							    	}
							    	
							    	//文件必须选择校验
							    	var docObj=$("#imgFile")[0]; 
							    	// 文件格式校验
    								if( !docObj.value.match( /.jpg|.JPG/i ) ){  
							   			$.messager.alert('提示','请上传JPG、jpg格式的文件！');  
							   			return ;  
							  		}    
							    	if(undefined == docObj.value||''==docObj.value){
    									$.messager.alert("提示","请选择图片！");
    									return false;
    								}
									if(docObj.files	&&	docObj.files[0].size>1*1024*1024){
    									$.messager.alert("提示","图片大小不能超过1M！");
    									return false;
    								}else{
    									// IE10以下版本浏览器文件大小校验										
										var ImgObj=new Image();   //建立一个图像对象	
										ImgObj.src=$("#imgFile").val();
										ImgObj.onreadystatechange=function(){
											if(ImgObj.readyState=="complete")
											{
												var ImgFileSize=Math.round(ImgObj.fileSize/1024*100)/100;//取得图片文件的大小		
												if(ImgFileSize!=0){
													if(ImgFileSize>1*1024*1024){
														$.messager.alert("提示","图片文件大小不能超过1M！");
														return false;
													}
													if( !docObj.value.match( /.jpg|.JPG/i ) ){  
								   						$.messager.alert('提示','请上传JPG、jpg格式的文件！');  
								   						return ;  
								  					}	
												}																																	}											
										}
    								}
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
	tMaterialPicture_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTMaterialPictureFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图片资源编辑',
		    width: 800,
		    height: 530,
		    href: 'editTMaterialPicturePage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   		$('#editTMaterialPictureFrom').form({   
						 url:'editTMaterialPictureAction.action?pkid='+pkid,    
							     onSubmit: function(){
							    
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       data = JSON.parse(data);							 
							        $.messager.alert('提示',data.msg,'',function(){	
							        	if(data.success){
											tMaterialPicture_list.buildQueryParams();
											$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
											$('#dialog_holder').dialog('close');
							        	}						       	                 					
                 					});
                 					$.messager.progress('close');
							     }
						});// 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
						  	
							    	//图片名称不能为空校验：easyui不能识别空格
							     	var picture= $("input[name='tMaterialPictureDto.picture_name']").val();
							    	if(undefined == picture || "" == $.trim(picture) ){
							    		$.messager.alert('提示','图片名称不能为空！');
							    		return false;
							    	}							    								    	
							    						    	
							    	var docObj=$("#imgFile")[0]; 
							    	
							    	//文件大小校验						    	
									if(docObj.files &&	docObj.files[0]){
										// 文件格式校验
										if( !docObj.value.match( /.jpg|.JPG/i ) ){  
							   				$.messager.alert('提示','请上传JPG、jpg格式的文件！');  
							   				return false;  
							  			}
							  			if(docObj.files[0].size>1*1024*1024){
							  				$.messager.alert("提示","图片大小不能超过1M！");
    										return false;
							  			}   
    									
    								}else{
    									   
    									// IE10以下版本浏览器文件大小校验
										var ImgObj=new Image();   //建立一个图像对象	
										ImgObj.src=$("#imgFile").val();
										ImgObj.onreadystatechange=function(){
											if(ImgObj.readyState=="complete")
											{
												var ImgFileSize=Math.round(ImgObj.fileSize/1024*100)/100;//取得图片文件的大小		
												if(ImgFileSize!=0){
													if(ImgFileSize>1*1024*1024){
														$.messager.alert("提示","图片文件大小不能超过1M！");
														return false;
													}
													if( !docObj.value.match( /.jpg|.JPG/i ) ){  
								   						$.messager.alert('提示','请上传JPG、jpg格式的文件！');  
								   						return ;  
								  					}	
												}																																	}											
										}
    								}
							$(edit_form_id).submit();
						};   
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     			$('#delTMaterialPictureFrom').form({   
						 url:'delTMaterialPictureAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							     	data = JSON.parse(data);							 
							        $.messager.alert('提示',data.msg,'',function(){	
							        	if(data.success){
											tMaterialPicture_list.buildQueryParams();
											$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
											$('#dialog_holder').dialog('close');
							        	}						       	                 					
                 					});
                 					$.messager.progress('close');
							     }
						});// 
					    $('#delTMaterialPictureFrom').submit();
		    	}
		     }
		    ,{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog('close');
		    	}
		    }]
		});
	}
	
	//详细
	tMaterialPicture_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '图片资源预览',
		    width: 800,
		    height: 548,
		    href: 'detailTMaterialPicturePage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");			
			},
		    buttons: [{
		    	text: "编辑",
		    	handler: function(e){
		    		var editId =	$("#pkid").val();
		    		$('#dialog_holder').dialog('close');
		    		
		    		tMaterialPicture_list.updateFormSubmit(editId);
		    	}
		    },{
		    	text: "删  除",
		     	handler: function(e){

		     			$('#delTMaterialPictureFrom').form({   
						 url:'delTMaterialPictureAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							      	data = JSON.parse(data);							 
							        $.messager.alert('提示',data.msg,'',function(){	
							        	if(data.success){
											tMaterialPicture_list.buildQueryParams();
											$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
											$('#dialog_holder').dialog('close');
							        	}						       	                 					
                 					});
                 					$.messager.progress('close');
							     }
						});
						
					    $('#delTMaterialPictureFrom').submit();
		    	}
		     },{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}	

	//删除图片
	tMaterialPicture_list.removeImgForm=function(pkid,file_id){
		$.messager.confirm("提示信息","确实要删除吗?",function(flg){
			if(flg){   
				$.post("delTMaterialPictureAction.action",{pkid:pkid,file_id:file_id}, function(data){
					data = JSON.parse(data);							 
					$.messager.alert('提示',data.msg,'',function(){	
						if(data.success){							
							tMaterialPicture_list.buildQueryParams();
							$('div#div_tMaterialPicture_list #dg_tMaterialPicture').datagrid('load');
							$('#dialog_holder').dialog('close');
						}						       	                 					
                 	});
                 	$.messager.progress('close');
		 		});
		 	}
		});
	}	
	
	//弹出图片描述
	tMaterialPicture_list.detailPictureDesc=function(desc){
		$.messager.alert('图片描述','<div style="word-wrap: break-word; word-break: normal;text-indent: 2em; ">'+desc+'</div>');
	}
	
	//图片预览
	tMaterialPicture_list.pictureView = function (picture_url,wid,hei){
	//设置图片属性
		$('#preview').attr('src',picture_url);
		$('#preview').css('width',wid);
		$('#preview').css('height',hei);
		$('#preview_div').dialog('open');
		//设置弹出框属性
		$('#preview_div').panel('resize',{width:eval(wid*1+14),height:eval(hei*1+36),left:350,top:50});
	};
	
	//图片预览关闭
	tMaterialPicture_list.closeView = function (){
		$('#preview_div').dialog('close');
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

});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>