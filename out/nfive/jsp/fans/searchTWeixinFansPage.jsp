<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>粉丝一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tWeixinFans_list" class="container">
	<header class="page-title">
		<h1>粉丝一览</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>添加到群</a></li>
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
										<th class="wd-10"><label>昵称</label></th>
										<td>
											<input type="text" class="easyui-validatebox" id="tWeixinFansDto.nick_name"  name="search_nick_name"  value="${tWeixinFansDto.nick_name}" style="width:200px;"/>
										</td>
										<th class="wd-10"><label>性别</label></th>
										<td>
											<ldui:select items="${sexList}" class="easyui-combobox" id="tWeixinFansDto.sex" name="search_sex"  value="${tWeixinFansDto.sex}" style="width:208px;" />
										</td>
										<th class="wd-10"><label>备注名</label></th>
										<td>
											<input type="text" class="easyui-validatebox" id="tWeixinFansDto.remark_name" name="search_remark_name"  value="${tWeixinFansDto.remark_name}" style="width:200px;"/>
										</td>
									  </tr>
							         	
									 <tr>
										<th class="wd-10"><label>国家</label></th>
										<td>
											<input type="text" class="easyui-validatebox" id="tWeixinFansDto.country" name="search_country"  value="${tWeixinFansDto.country}" style="width:200px;"/>
										</td>
										<th class="wd-10"><label>省份</label></th>
										<td>
											<input type="text" class="easyui-validatebox" id="tWeixinFansDto.province" name="search_province"  value="${tWeixinFansDto.province}" style="width:200px;"/>
										</td>
										<th class="wd-10"><label>城市</label></th>
										<td>
											<input type="text" class="easyui-validatebox" id="tWeixinFansDto.city" name="search_city"  value="${tWeixinFansDto.city}" style="width:200px;"/>
										</td>
									  </tr>
									  
						             <tr>
										<th class="wd-10"><label>关注时间</label></th>
										<td>
											<input type="text" class="easyui-datebox" id="search_subscribe_time_start" name="search_subscribe_time_start" validType="againFocus['#search_subscribe_time_end']"  style="width:120px;" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp;
										
											<input type="text" class="easyui-datebox" id="search_subscribe_time_end" name="search_subscribe_time_end" validType="minFirstDate['#search_subscribe_time_start']"  style="width:120px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
										<th class="wd-10"><label>是关注用户</label></th>
										<td>
											<ldui:select items="${subscribeList}" class="easyui-combobox" id="tWeixinFansDto.subscribe" name="search_subscribe"  value="${tWeixinFansDto.subscribe}" style="width:208px;"/>
										</td>
										<th class="wd-10"><label>一账通绑定</label></th>
										<td>
											<ldui:select items="${boundList}" class="easyui-combobox"  name="search_openid_verify"  value="${tWeixinFansDto.subscribe}" style="width:208px;"/>
										</td>
									  </tr>
								    
									 <tr>
										<th class="wd-10"><label>微信分组</label></th>
										<td colspan="5">
									    	<ldui:select items="${wixinGroup}" id="weixin_group" name="weixin_group" class="easyui-combobox" style="width:188px;" />
										</td>
									  </tr> <tr>
										<th class="wd-10"><label>粉丝群</label></th>
										<td colspan="5">
                                      <div class="easyui-panel" style="width:1121px;height:100px;padding:10px;background:#fafafa;doSize:false;word-wrap:break-word;">  
										<ul class="fans_ul" id="fans_group">
											<c:forEach items="${fansGroup}" var="fansGp" >
											<li style="width: 360px;"> <input type="checkbox" value="${fansGp.fans_group_id}">${fansGp.group_name}</input>&nbsp;&nbsp;</li>
											</c:forEach>
										</ul></div>
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
				<table  id="dg_tWeixinFans" class="easyui-datagrid" title="粉丝一览" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:false,collapsible:true,sortName:'FANS.NICK_NAME',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTWeixinFansAction!getListData.action',method:'post'">
					<thead>
						<tr>
						    <th data-options="field:'CHECK',checkbox:true,width:60,align:'center'"></th>
						 	<th data-options="field:'DETAIL',width:60,align:'center'">详细</th>
							<th data-options="field:'EDIT',width:60,align:'center'">编辑</th>
							<th data-options="field:'FANS_ID',width:120,hidden:'true',align:'center'">fans_id</th>
							<th data-options="field:'HEAD_IMG_URL',width:50,sortable:'true',align:'center'">头像</th>
							<th data-options="field:'NICK_NAME',width:120,sortable:'true',align:'center'">昵称</th>
							<th data-options="field:'SEX',width:60,sortable:'true',align:'center'">性别</th>
							<th data-options="field:'REMARK_NAME',width:100,sortable:'true',align:'center'">备注名</th>
                            <th data-options="field:'GUP_NAME',width:80,sortable:'true',align:'center'">微信组</th>
                            <th data-options="field:'OPENID_VERIFY',width:80,sortable:'true',align:'center'">一账通绑定</th>
			                <th data-options="field:'MACCOUNT_NO',width:120,sortable:'true',align:'center'">一帐通账号</th>
							<th data-options="field:'MOBILE',width:120,sortable:'true',align:'center'">手机</th>
							<th data-options="field:'FINANCIAL',width:120,sortable:'true',align:'center'">财富号</th>
							<th data-options="field:'FUND',width:120,sortable:'true',align:'center'">基金号</th>
							<th data-options="field:'COUNTRY',width:100,sortable:'true',align:'center'">国家</th>
							<th data-options="field:'PROVINCE',width:100,sortable:'true',align:'center'">省份</th>
							<th data-options="field:'CITY',width:100,sortable:'true',align:'center'">城市</th>
							<th data-options="field:'SUBSCRIBE',width:80,sortable:'true',align:'center'">是关注用户</th>
							<th data-options="field:'SUBSCRIBE_TIME',width:150,sortable:'true',align:'center'">关注时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tWeixinFans_list={};
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

jQuery(function($){
	//定义构造查询
	tWeixinFans_list.buildQueryParams=function(){
		   //获取选择的粉丝群
	       var fansgroup_id =[];
				     for(var i=0;i<$("#fans_group input[type='checkbox']").length;i++){
				        if($("#fans_group input[type='checkbox']")[i].checked){
				         fansgroup_id .push($("#fans_group input[type='checkbox']")[i].value);
				        }
				     }	
	     $('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid("options")
			.queryParams = {
					'tWeixinFansQueryBean.fans_id':$("input[name='search_fans_id']").val(),
					'tWeixinFansQueryBean.subscribe':$("input[name='search_subscribe']").val(),
					'tWeixinFansQueryBean.openid_verify':$("input[name='search_openid_verify']").val(),
					'tWeixinFansQueryBean.nick_name':$.trim($("input[name='search_nick_name']").val()),
					'tWeixinFansQueryBean.sex':$("input[name='search_sex']").val(),
					'tWeixinFansQueryBean.remark_name':$.trim($("input[name='search_remark_name']").val()),
					'tWeixinFansQueryBean.country':$.trim($("input[name='search_country']").val()),
					'tWeixinFansQueryBean.province':$.trim($("input[name='search_province']").val()),
					'tWeixinFansQueryBean.city':$.trim($("input[name='search_city']").val()),
					'tWeixinFansQueryBean.subscribe_time_start':$("input[name='search_subscribe_time_start']").val(),
					'tWeixinFansQueryBean.subscribe_time_end':$("input[name='search_subscribe_time_end']").val(),
					'tWeixinFansQueryBean.unionid':$("input[name='search_unionid']").val(),
					'tWeixinFansQueryBean.weixin_group_id':$.trim($("input[name='weixin_group']").val()),
				    'tWeixinFansQueryBean.fans_grouplist':fansgroup_id.join(",")			
			};
	};


    //定义构造查询JSON
    tWeixinFans_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('options').pageNumber,
		  		//页面查询框部分
					fans_id :$("input[name='search_fans_id']").val(), 
					subscribe :$("input[name='search_subscribe']").val(), 
					nick_name :$("input[name='search_nick_name']").val(), 
					sex :$("input[name='search_sex']").val(), 
					remark_name :$("input[name='search_remark_name']").val(), 
					country :$("input[name='search_country']").val(), 
					province :$("input[name='search_province']").val(), 
					city :$("input[name='search_city']").val(), 
					subscribe_time :$("input[name='search_subscribe_time']").val(), 
					weixin_group_id :$("input[name='search_weixin_group_id']").val()
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tWeixinFans_list #search_btn').click(function(){
	  if($('#searchForm').form("validate")==true){ 
			tWeixinFans_list.buildQueryParams();
			$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('load');
	     }
	});
	
    //重置查询条件并刷新内容
	$('div#div_tWeixinFans_list #clear_btn').click(function(){
		//清空查询条	
		$("#fans_group input[type='checkbox']").each(function(index,element){
    	     element.checked=false;
 	      });
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
		tWeixinFans_list.buildQueryParams();
		$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('load');
	});
	
	
	//新增
	$('div#div_tWeixinFans_list #add_btn').click(function(){	
    		var checked = $("#dg_tWeixinFans").datagrid("getChecked");
    		if(checked.length == 0)
	   		{
	   			$.messager.alert("提示信息","请选择您要添加的粉丝","info");
	   			return false;
	   		}
    		var fans_list = [];
    		var name_list =[];
    		for(var i = 0; i<checked.length;i++){
    			fans_list.push(checked[i].FANS_ID);
    			name_list.push(checked[i].NICK_NAME);
    		}
		var add_form_id ='#addTWeixinFansFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝群选择',
		    width: 960,
		    height: 600,
		    href: 'selectTFansGroupAction.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
			    		var checked = $("#dg_tFansGroup").datagrid("getChecked");
			    		if(checked.length == 0){
				   			$.messager.alert("提示信息","请选择您要添加的粉丝群","info");
				   			return;
				   		};
			    		var fans_group = [];
			    		for(var i = 0; i<checked.length;i++){
			    			fans_group.push(checked[i].FANS_GROUP_ID);
			    		};
			    		$.post("addTWeixinFansAction.action",
			    		  {
			    		  	"tWeixinFansGroupVo.fans_group_id": fans_group.join(","),
			    			"tWeixinFansGroupVo.fans_id": fans_list.join(","),
			    			"tWeixinFansGroupVo.member_name": name_list.join(",")	   
						  },    
					   function(data){  
			                var result = JSON.parse(data);
					        if(result.status==true){
							     $.messager.progress('close');
								$.messager.alert("提示信息",result.message,"success");
							}else{
							     $.messager.progress('close');
								$.messager.alert("提示信息",result.message,"error");
							}
							$('#dialog_holder').dialog('close');
							tWeixinFans_list.buildQueryParams();
							$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('load');
					     });
				  }, 
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
	//更新
	tWeixinFans_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTWeixinFansFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝编辑',
		    width: 800,
		    height: 500,
		    href: 'editTWeixinFansPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTWeixinFansFrom').form({   
						 url:'editTWeixinFansAction.action',    
							   onSubmit: function(){
							       $.messager.progress(); 
							     },    
							    success:function(data){
							    var result = JSON.parse(data);
						        if(result.status==true){
								     $.messager.progress('close');
									$.messager.alert("提示信息",result.message,"success");
								}else{
								     $.messager.progress('close');
									$.messager.alert("提示信息",result.message,"error");
								}
							$('#dialog_holder').dialog('close');
							tWeixinFans_list.buildQueryParams();
							$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('load');
						  }
						});// 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
					        //获取粉丝群
	                         var fansgroup_id =[];
				            for(var i=0;i<$("#fans_grouplist input[type='checkbox']").length;i++){
					            if($("#fans_grouplist input[type='checkbox']")[i].checked){
					                fansgroup_id.push($("#fans_grouplist input[type='checkbox']")[i].value);
					              }
				             }
				             var fansgplist = document.getElementById("fansgplist");
				             fansgplist.value=fansgroup_id.join(",");
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
	tWeixinFans_list.detailFormSubmit = function(pkid){
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
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>