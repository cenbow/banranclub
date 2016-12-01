<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.config.js"></script>

<title>公众平台号</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tPubPlatform_list" class="container">
	<header class="page-title">
		<h1>公众平台号</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a></li>
		</ul>
	</div>
	<article id="content" class="content">
		<div class="content-body">
	
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tPubPlatform" class="easyui-datagrid" title="公众号列表" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTPubPlatformAction!getListData.action',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:50,align:'center'">编辑</th>
							<th data-options="field:'USER_CONFIG',width:50,align:'center'">员工配置</th>
							<th data-options="field:'TITLE_SETTING',width:100,align:'center'">图文项标题设置</th>
							<th data-options="field:'PLATFORM_ID',width:120,sortable:'true',align:'right'">公众平台号ID</th>
							<th data-options="field:'WECHART_ACCOUNT',width:120,sortable:'true',align:'right'">公众微信号</th>
							<th data-options="field:'PLATFORM_NAME',width:120,sortable:'true',align:'right'">公众号名称</th>
							<th data-options="field:'PLATFORM_TYPE',width:120,sortable:'true',align:'right'">公众号类型</th>
							<th data-options="field:'PLATFORM_DESC',width:120,sortable:'true',align:'right'">说明信息</th>
							<th data-options="field:'TOKEN',width:120,sortable:'true',align:'right'">令牌</th>
							<th data-options="field:'ORG_ID',width:120,sortable:'true',align:'right'">原始ID</th>
							<th data-options="field:'CREATED_DATE',width:120,sortable:'true',align:'right'">创建日期</th>
							<th data-options="field:'UPDATED_DATE',width:120,sortable:'true',align:'right'">修改日期</th>
							<th data-options="field:'CREATED_USER_CD',width:120,sortable:'true',align:'right'">创建人</th>
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'right'">最后修改人</th>
							
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tPubPlatform_list ={};
jQuery(function($){
	//定义构造查询
	tPubPlatform_list.buildQueryParams=function(){
	     $('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid("options")
			.queryParams = {
					'tPubPlatformQueryBean.platform_id':$("input[name='search_platform_id']").val(),
					'tPubPlatformQueryBean.wechart_account':$("input[name='search_wechart_account']").val(),
					'tPubPlatformQueryBean.platform_email':$("input[name='search_platform_email']").val(),
					'tPubPlatformQueryBean.platform_name':$("input[name='search_platform_name']").val(),
					'tPubPlatformQueryBean.platform_type':$("input[name='search_platform_type']").val(),
					'tPubPlatformQueryBean.platform_desc':$("input[name='search_platform_desc']").val(),
					'tPubPlatformQueryBean.token':$("input[name='search_platform_token']").val(),
					'tPubPlatformQueryBean.org_id':$("input[name='search_platform_org_id']").val(),
					'tPubPlatformQueryBean.appid':$("input[name='search_appid']").val(),
					'tPubPlatformQueryBean.appsecret':$("input[name='search_appsecret']").val(),
					'tPubPlatformQueryBean.created_date':$("input[name='search_created_date']").val(),
					'tPubPlatformQueryBean.update_date':$("input[name='search_updated_date']").val(),
					'tPubPlatformQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val()
			};
	};


	//重新按照条件刷新查询内容
	$('div#div_tPubPlatform_list #search_btn').click(function(){
		tPubPlatform_list.buildQueryParams();
		$('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tPubPlatform_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_platform_id']").val("");
			$("input[name='search_wechart_account']").val("");
			$("input[name='search_platform_email']").val("");
			$("input[name='search_platform_name']").val("");
			$("input[name='search_platform_type']").val("");
			$("input[name='search_platform_desc']").val("");
			$("input[name='search_platform_token']").val("");
			$("input[name='search_appid']").val("");
			$("input[name='search_appsecret']").val("");
			$("input[name='search_created_date']").val("");
			$("input[name='search_created_user_cd']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tPubPlatform_list.buildQueryParams();
		$('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid('load');
	});
	

	//新增
	$('div#div_tPubPlatform_list #add_btn').click(function(){
		var add_form_id ='#addTPubPlatformFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '新增公众微信号',
		    width: 800,
		    height: 500,
		    href: 'addTPubPlatformPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    	  if($("#tPubPlatformDto_wechart_account").val().length>50)
	              {
	                $.messager.alert("友情提示","公众微信号不能超过50","info");
	                return false;
	              }
	              if($("#tPubPlatformDto_platform_name").val().length>50)
	              {
	                 $.messager.alert("友情提示","公众号名称不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_platform_desc").val().length>50)
	              {
	                 $.messager.alert("友情提示","说明信息不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_appid").val().length>50)
	              {
	                 $.messager.alert("友情提示","应用ID不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_appsecret").val().length>50)
	              {
	                 $.messager.alert("友情提示","应用密钥不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_token").val().length>100)
	              {
	                 $.messager.alert("友情提示","令牌不能超过100","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_org_id").val().length>30)
	              {
	                 $.messager.alert("友情提示","原始ID不能超过30","info");
	                 return false;
	              }
	              
		    		$('#addTPubPlatformFrom').form({   
						 url:'addTPubPlatformAction.action',    
							     onSubmit: function(){
							         $.messager.progress();
							     },
							     success:function(data){
								     data = $.parseJSON(data);
								     if(data.status){
								     $.messager.progress('close');
									 tPubPlatform_list.buildQueryParams();
									 $('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid('load');
									 $('#dialog_holder').dialog('close');
									 }else{
									 $.messager.progress('close');
									 $.messager.alert("友情提示!",data.mess);			    					
			    				}
								}
						});
					    //validate and sbumit
					    if($(add_form_id).form("validate")==true){
						    	$.messager.progress();
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
	tPubPlatform_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTPubPlatformFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '编辑公众微信号',
		    width: 600,
		    height: 400,
		    href: 'editTPubPlatformPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		    	  if($("#tPubPlatformDto_wechart_account").val().length>50)
	              {
	                $.messager.alert("友情提示","公众微信号不能超过50","info");
	                return false;
	              }
	              if($("#tPubPlatformDto_platform_name").val().length>50)
	              {
	                 $.messager.alert("友情提示","公众号名称不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_platform_desc").val().length>50)
	              {
	                 $.messager.alert("友情提示","说明信息不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_appid").val().length>50)
	              {
	                 $.messager.alert("友情提示","应用ID不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_appsecret").val().length>50)
	              {
	                 $.messager.alert("友情提示","应用密钥不能超过50","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_token").val().length>100)
	              {
	                 $.messager.alert("友情提示","令牌不能超过100","info");
	                 return false;
	              }
	              if($("#tPubPlatformDto_org_id").val().length>30)
	              {
	                 $.messager.alert("友情提示","原始ID不能超过30","info");
	                 return false;
	              }
		   				$('#editTPubPlatformFrom').form({   
						 url:'editTPubPlatformAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },
							     success:function(data){
							     data = $.parseJSON(data);
								    if(data.status){
							        $.messager.progress('close');
									tPubPlatform_list.buildQueryParams();
									$('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
							     else{
							     	$.messager.progress('close');
									$.messager.alert("友情提示",data.mess,"error");	
							     }
							     }
						}); 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
						  	$.messager.progress();
							$(edit_form_id).submit();
						};
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     	  $.messager.confirm('是否删除', '是否确认要删除这条记录', function(info)
				  {
				  if(info)
				  {
		     			$('#delTPubPlatformFrom').form({   
						 url:'delTPubPlatformAction.action',    
							     onSubmit: function(){
							       $.messager.progress();  
							     },    
							     success:function(data){
									data = $.parseJSON(data);
								    if(data.status){
							        $.messager.progress('close');
									tPubPlatform_list.buildQueryParams();
									$('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
							     else{
							     	$.messager.progress('close');
									$.messager.alert("友情提示","公众号已绑定，请先解绑!","info");	
							     }	
							     }
						});
					    $('#delTPubPlatformFrom').submit();
				   }
				   })
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
	

	  //详细 detailFormSubmit
      tPubPlatform_list.detailFormSubmit = function(platformid){
          $('<div id="dialog_holder"></div>').dialog({
          title: '公众微信号详细页',
          width: 800,
          height: 500,
          href: 'detailTPubPlatformPage.action',
          modal: true,
          method: "POST",
          params:{pkid:platformid},
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
       
      //标题设置
      tPubPlatform_list.titleSettingSubmit = function(pkid){
            var edit_form_id ='#titleSettingForm';
			$('<div id="dialog_holder_title"></div>').dialog({
			    title: '图文项标题设置',
			    width: 800,
			    height: 620,
			    href: 'titleSettingPage.action',
			    modal: true,
			    method: "POST",
			    params:{pkid:pkid},
				onClose: function(){
					$(this).dialog("destroy");
				},
			    buttons: [{
			    	text: "确  定",
			    	handler: function(e){
				    	if($("input[name='is_display_title_check']").attr("checked") == "checked"){
					  		$("input[name='tPubPlatformDto.is_display_title']").val("100000000001");
					  	}else{
					  		$("input[name='tPubPlatformDto.is_display_title']").val("100000000002");
					  	}
				    	$("input[name='tPubPlatformDto.title_style']").val(UE.getEditor('editor').getContent());
				    	
		   				$('#titleSettingForm').form({   
						     url:'titleSettingAction.action',    
						     onSubmit: function(){
						       $.messager.progress(); 
						     },
						     success:function(data){
							     data = $.parseJSON(data);
							     if(data.status){
							        $.messager.progress('close');
									tPubPlatform_list.buildQueryParams();
									$('div#div_tPubPlatform_list #dg_tPubPlatform').datagrid('load');
									$('#dialog_holder_title').dialog('close');
							     }else{
							     	$.messager.progress('close');
									$.messager.alert("友情提示",data.mess,"error");	
							     }
						     }
						}); 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
						  	$.messager.progress();
							$(edit_form_id).submit();
						};
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
       
	    //用户绑定公众号
      tPubPlatform_list.allotUserFormSubmit = function(pkid){
          $('<div id="dialog_holder"></div>').dialog({
              title: '员工分配',
              width: 800,
              height: 500,
              href: 'searchTPlatformempCfgAction.action',
              modal: true,
              method: "POST",
              params:{platform_id:pkid},
              onLoad: function(){
			$(this).find("#div_platForm_list #platform_id").val(pkid);
			tConfig_list.trigger();
		},
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
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>